package com.github.cherry.http.client;


/**
 * <p>
 * 表示尝试访问外部服务时遇到的异常。
 * </p>
 * 
 * <p>
 * {@link ClientException}表示的则是在向外部服务发送请求时出现的错误，以及客户端无法处理返回结果。 例如，在发送请求时网络连接不可用，则会抛出{@link ClientException}的异常。
 * </p>
 * 
 * 
 * 
 */
public class ClientException extends WebAccessException {

    private static final long serialVersionUID = 1870835486798448798L;

    private String errorMessage;

    private String requestId;

    private String errorCode;

 

    /**
     * 用给定的异常信息构造新实例。
     * 
     * @param errorMessage 异常信息。
     */
    public ClientException(String errorMessage) {
        this(errorMessage, null);
    }

    /**
     * 用表示异常原因的对象构造新实例。
     * 
     * @param cause 异常原因。
     */
    public ClientException(Throwable cause) {
        this(null, cause);
    }

    /**
     * 用异常消息和表示异常原因的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param cause 异常原因。
     */
    public ClientException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
        this.errorCode = ClientErrorCode.UNKNOWN;
        this.requestId = "Unknown";
    }

    /**
     * 用异常消息构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param errorCode 错误码。
     * @param requestId 请求编号。
     */
    public ClientException(String errorMessage, String errorCode, String requestId) {
        this(errorMessage, errorCode, requestId, null);
    }

    /**
     * 用异常消息和表示异常原因的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param errorCode 错误码。
     * @param requestId 请求编号。
     * @param cause 异常原因。
     */
    public ClientException(String errorMessage, String errorCode, String requestId, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
        this.requestId = requestId;
    }

    /**
     * 获取异常信息。
     * 
     * @return 异常信息。
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 获取异常的错误码
     * 
     * @return 异常错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 获取本次异常的 RequestId
     * 
     * @return 本次异常的 RequestId
     */
    public String getRequestId() {
        return requestId;
    }

    @Override
    public String getMessage() {
        return getErrorMessage() + "\n[ErrorCode]: " + errorCode != null ? errorCode : "" + "\n[RequestId]: "
                + requestId != null ? requestId : "";
    }
}
