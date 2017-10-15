package com.github.cherry.http.client.internal;

public interface ClientConstants {

    public static final String DEFAULT_CHARSET_NAME = "utf-8";

    public static final String DEFAULT_XML_ENCODING = "utf-8";

    public static final String DEFAULT_OBJECT_CONTENT_TYPE = "application/octet-stream";

    public static final int KB = 1024;

    public static final int DEFAULT_BUFFER_SIZE = 8 * KB;

    public static final int DEFAULT_STREAM_BUFFER_SIZE = 512 * KB;

    public static final long DEFAULT_FILE_SIZE_LIMIT = 5 * 1024 * 1024 * 1024L;

    public static final String RESOURCE_NAME_COMMON = "common";

    public static final int OBJECT_NAME_MAX_LENGTH = 1024;

    public static final String LOGGER_PACKAGE_NAME = "com.github.cherry.http.client";

    public static final String PROTOCOL_HTTP = "http://";

    public static final String PROTOCOL_HTTPS = "https://";

    public static final String PROTOCOL_RTMP = "rtmp://";
}
