package com.github.cherry.http.client.event;

import com.github.cherry.framework.common.constants.LabeledEnum;

public enum ProgressEventType implements LabeledEnum<ProgressEventType> {

    /**
     * Event of the content length to be sent in a request.
     */
    REQUEST_CONTENT_LENGTH_EVENT("请求字节长度"),

    /**
     * Event of the content length received in a response.
     */
    RESPONSE_CONTENT_LENGTH_EVENT("响应字节长度"),

    /**
     * Used to indicate the number of bytes sent
     */
    REQUEST_BYTE_TRANSFER_EVENT("发送字节长度"),

    /**
     * Used to indicate the number of bytes received
     */
    RESPONSE_BYTE_TRANSFER_EVENT("接收字节长度"),

    /**
     * Transfer events.
     */
    TRANSFER_PREPARING_EVENT("准备传输"), TRANSFER_STARTED_EVENT("开始传输"), TRANSFER_COMPLETED_EVENT("传输完成"), TRANSFER_FAILED_EVENT(
            "传输失败"), TRANSFER_CANCELED_EVENT("传输终止"), TRANSFER_PART_STARTED_EVENT("分段传输开始"), TRANSFER_PART_COMPLETED_EVENT(
            "分段传输完成"), TRANSFER_PART_FAILED_EVENT("分段传输失败");

    private String label;

    private ProgressEventType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
