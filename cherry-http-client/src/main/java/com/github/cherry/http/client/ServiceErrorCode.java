/**
 * 工程名:cherry-http-client
 * 文件名:ServiceErrorCode.java
 * 包名:com.github.cherry.http.client
 * 创建日期:2017年9月12日上午10:16:19
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client;

/**
 * 业务级别错误码
 *
 * @author Scott
 * @since 1.0
 */
public interface ServiceErrorCode {
    /**
     * 拒绝访问。
     */
    static final String ACCESS_DENIED = "AccessDenied";

    /**
     * 禁止访问。
     */
    static final String ACCESS_FORBIDDEN = "AccessForbidden";

    /**
     * 参数格式错误。
     */
    static final String INVALID_ARGUMENT = "InvalidArgument";

    /**
     * 缺少内容长度。
     */
    static final String MISSING_CONTENT_LENGTH = "MissingContentLength";

    /**
     * 缺少必须参数。
     */
    static final String MISSING_ARGUMENT = "MissingArgument";

    /**
     * 无法处理的方法。
     */
    static final String NOT_IMPLEMENTED = "NotImplemented";

    /**
     * 预处理错误。
     */
    static final String PRECONDITION_FAILED = "PreconditionFailed";

    /**
     * 304 Not Modified。
     */
    static final String NOT_MODIFIED = "NotModified";

    /**
     * 404 Not found
     */
    static final String NOT_FOUND = "NotFound";

    /**
     * 指定的数据中心非法。
     */
    static final String INVALID_LOCATION_CONSTRAINT = "InvalidLocationConstraint";

    /**
     * 指定的数据中心与请求的终端域名不一致。
     */
    static final String ILLEGAL_LOCATION_CONSTRAINT_EXCEPTION = "IllegalLocationConstraintException";

    /**
     * 发起请求的时间和服务器时间超出15分钟。
     */
    static final String REQUEST_TIME_TOO_SKEWED = "RequestTimeTooSkewed";

    /**
     * 请求超时。
     */
    static final String REQUEST_TIMEOUT = "RequestTimeout";

    /**
     * 签名错误。
     */
    static final String SIGNATURE_DOES_NOT_MATCH = "SignatureDoesNotMatch";

    /**
     * XML格式非法。
     */
    static final String MALFORMED_XML = "MalformedXML";

    /**
     * 无效的服务器端加密编码。
     */
    static final String INVALID_ENCRYPTION_ALGORITHM_ERROR = "InvalidEncryptionAlgorithmError";

    /**
     * 实体过小。
     */
    static final String ENTITY_TOO_SMALL = "EntityTooSmall";

    /**
     * 实体过大。
     */
    static final String ENTITY_TOO_LARGE = "EntityTooLarge";

    /**
     * 无效的MD5值。
     */
    static final String INVALID_DIGEST = "InvalidDigest";

    /**
     * 无效的字节范围。
     */
    static final String INVALID_RANGE = "InvalidRange";

    /**
     * 返回结果无法解析。
     */
    static final String INVALID_RESPONSE = "InvalidResponse";

}
