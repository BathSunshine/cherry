package com.github.cherry.http.client;


public class ServiceException extends WebAccessException {

    private static final long serialVersionUID = 430933593095358673L;

    private String errorMessage;

    private String errorCode;

    private String requestId;

    private String hostId;

    private String rawResponseError;

    private String resourceType;

    private String header;

    private String method;

    /**
     * 用给定的异常信息构造新实例。
     * 
     * @param errorMessage 异常信息。
     */
    public ServiceException(String errorMessage) {
        super((String) null);
        this.errorMessage = errorMessage;
    }

    /**
     * 用表示异常原因的对象构造新实例。
     * 
     * @param cause 异常原因。
     */
    public ServiceException(Throwable cause) {
        super(null, cause);
    }

    /**
     * 用异常消息和表示异常原因的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param cause 异常原因。
     */
    public ServiceException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param errorCode 错误代码。
     * @param requestId Request ID。
     * @param hostId Host ID。
     */
    public ServiceException(String errorMessage, String errorCode, String requestId, String hostId) {
        this(errorMessage, errorCode, requestId, hostId, null);
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param errorCode 错误代码。
     * @param requestId Request ID。
     * @param hostId Host ID。
     * @param cause 异常原因。
     */
    public ServiceException(String errorMessage, String errorCode, String requestId, String hostId, Throwable cause) {
        this(errorMessage, errorCode, requestId, hostId, null, cause, null, null, null);
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     * 
     * @param errorMessage 异常信息。
     * @param errorCode 错误代码。
     * @param requestId Request ID。
     * @param hostId Host ID。
     * @param rawResponseError 错误响应体。
     * @param cause 异常原因。
     */
    public ServiceException(String errorMessage, String errorCode, String requestId, String hostId,
            String rawResponseError, Throwable cause, String resourceType, String header, String method) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
        this.requestId = requestId;
        this.hostId = hostId;
        this.rawResponseError = rawResponseError;
        this.resourceType = resourceType;
        this.header = header;
        this.method = method;
    }

    /**
     * 返回异常信息。
     * 
     * @return 异常信息。
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 返回错误代码的字符串表示。
     * 
     * @return 错误代码的字符串表示。
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 返回Request标识。
     * 
     * @return Request标识。
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * 返回Host标识。
     * 
     * @return Host标识。
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * 返回错误响应体的字符串表示。
     * 
     * @return 错误响应体的字符串表示
     */
    public String getRawResponseError() {
        return rawResponseError;
    }

    /**
     * 设置错误响应体的字符串表示。
     * 
     * @param rawResponseError 错误响应体的字符串表示
     */
    public void setRawResponseError(String rawResponseError) {
        this.rawResponseError = rawResponseError;
    }

    private String formatRawResponseError() {
        if (rawResponseError == null || rawResponseError.equals("")) {
            return "";
        }
        return String.format("\n[ResponseError]:\n%s", this.rawResponseError);
    }

    @Override
    public String getMessage() {
        return getErrorMessage() + "\n[ErrorCode]: " + getErrorCode() + "\n[RequestId]: " + getRequestId()
                + "\n[HostId]: " + getHostId() + formatRawResponseError()
                + (resourceType == null ? "" : "\n[ResourceType]: " + resourceType)
                + (header == null ? "" : "\n[Header]: " + header) + (method == null ? "" : "\n[Method]: " + method);
    }
}
