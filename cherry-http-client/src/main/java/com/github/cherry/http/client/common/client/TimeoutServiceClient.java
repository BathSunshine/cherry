package com.github.cherry.http.client.common.client;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;

import com.github.cherry.http.client.ClientConfiguration;
import com.github.cherry.http.client.ClientException;
import com.github.cherry.http.client.ServiceErrorCode;
import com.github.cherry.http.client.ServiceException;
import com.github.cherry.http.client.common.comm.ExecutionContext;
import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.utils.ExceptionFactory;
import com.github.cherry.http.client.common.utils.LogUtils;

/**
 * Default implementation of {@link ServiceClient}.
 */
public class TimeoutServiceClient extends DefaultServiceClient {
    protected ThreadPoolExecutor executor;

    public TimeoutServiceClient(ClientConfiguration config) {
        super(config);

        int processors = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(processors * 5, processors * 10, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(processors * 100), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executor.allowCoreThreadTimeOut(true);
    }

    @Override
    public ResponseMessage sendRequestCore(ServiceClient.Request request, ExecutionContext context) throws IOException {
        HttpRequestBase httpRequest = httpRequestFactory.createHttpRequest(request, context);
        HttpClientContext httpContext = HttpClientContext.create();
        httpContext.setRequestConfig(this.requestConfig);

        CloseableHttpResponse httpResponse = null;
        HttpRequestTask httpRequestTask = new HttpRequestTask(httpRequest, httpContext);
        Future<CloseableHttpResponse> future = executor.submit(httpRequestTask);

        try {
            httpResponse = future.get(this.config.getRequestTimeout(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LogUtils.logException("[ExecutorService]The current thread was interrupted while waiting: ", e);

            httpRequest.abort();
            throw new ClientException(e.getMessage(), e);
        } catch (ExecutionException e) {
            RuntimeException ex;
            httpRequest.abort();

            if (e.getCause() instanceof IOException) {
                ex = ExceptionFactory.createNetworkException((IOException) e.getCause());
            } else {
                ex = new ServiceException(e.getMessage(), e);
            }

            LogUtils.logException("[ExecutorService]The computation threw an exception: ", ex);
            throw ex;
        } catch (TimeoutException e) {
            LogUtils.logException("[ExecutorService]The wait " + this.config.getRequestTimeout() + " timed out: ", e);

            httpRequest.abort();
            throw new ClientException(e.getMessage(), ServiceErrorCode.REQUEST_TIMEOUT, "Unknown", e);
        }

        return buildResponse(request, httpResponse);
    }

    @Override
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(ClientConfiguration.DEFAULT_THREAD_POOL_WAIT_TIME, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
                if (!executor
                        .awaitTermination(ClientConfiguration.DEFAULT_THREAD_POOL_WAIT_TIME, TimeUnit.MILLISECONDS)) {
                    LogUtils.getLog().warn(
                            "Pool did not terminate in " + ClientConfiguration.DEFAULT_THREAD_POOL_WAIT_TIME / 1000
                                    + " seconds");
                }
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        super.shutdown();
    }

    class HttpRequestTask implements Callable<CloseableHttpResponse> {
        private HttpRequestBase httpRequest;

        private HttpClientContext httpContext;

        public HttpRequestTask(HttpRequestBase httpRequest, HttpClientContext httpContext) {
            this.httpRequest = httpRequest;
            this.httpContext = httpContext;
        }

        @Override
        public CloseableHttpResponse call() throws Exception {
            return httpClient.execute(httpRequest, httpContext);
        }
    };

}
