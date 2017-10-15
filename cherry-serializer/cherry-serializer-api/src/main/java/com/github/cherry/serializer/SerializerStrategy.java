/**
 * 工程名:cherry-serializer-api
 * 文件名:SerializerStrategy.java
 * 包名:com.github.cherry.serializer
 * 创建日期:2017年9月22日上午9:15:39
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer;

import com.github.cherry.framework.common.constants.LabeledEnum;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public enum SerializerStrategy implements LabeledEnum<SerializerStrategy> {
    JSON("JSON"), XML("XML"), FST("FST");

    private String label;

    private SerializerStrategy(String label) {
        this.label = label;
    }

    /**
     * @see com.github.cherry.framework.common.constants.LabeledEnum#getLabel()
     */
    @Override
    public String getLabel() {
        return label;
    }

}
