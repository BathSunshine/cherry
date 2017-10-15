/**
 * 工程名:cherry-serializer-xml
 * 文件名:XmlSerializer.java
 * 包名:com.github.cherry.serializer.xml
 * 创建日期:2017年9月22日下午2:07:03
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.xsteam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.SerializerStrategy;
import com.github.cherry.serializer.SerializerTypeReference;
import com.thoughtworks.xstream.XStream;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class XmlSerializer implements Serializer {

    /**
     * @see com.github.cherry.serializer.Serializer#strategy()
     */
    @Override
    public SerializerStrategy strategy() {
        return SerializerStrategy.XML;
    }

    /**
     * @see com.github.cherry.serializer.Serializer#serializeAsString(java.lang.Object)
     */
    @Override
    public String serializeAsString(Object obj) {
        XStream xstream = XStreamFactory.getInstance(obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * @see com.github.cherry.serializer.Serializer#serializeAsByte(java.lang.Object)
     */
    @Override
    public byte[] serializeAsByte(Object obj) {
        XStream xstream = XStreamFactory.getInstance(obj.getClass());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xstream.toXML(obj, os);
        return os.toByteArray();
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(byte[], java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        XStream xstream = XStreamFactory.getInstance(clazz);
        return (T) xstream.fromXML(new ByteArrayInputStream(bytes));
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(byte[],
     * com.github.cherry.serializer.SerializerTypeReference)
     */
    @Override
    public <T> T deserialize(byte[] bytes, SerializerTypeReference<T> reference) {
        throw new UnsupportedOperationException("xstream unsupported generic type");
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(java.lang.String, java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(String str, Class<T> clazz) {
        XStream xstream = XStreamFactory.getInstance(clazz);
        return (T) xstream.fromXML(str);
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(java.lang.String,
     * com.github.cherry.serializer.SerializerTypeReference)
     */
    @Override
    public <T> T deserialize(String str, SerializerTypeReference<T> reference) {
        // 如果需支持必须自定义converter
        throw new UnsupportedOperationException("xstream unsupported generic type");
    }

}
