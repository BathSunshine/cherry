/**
 * 工程名:cherry-serializer-xml
 * 文件名:Pair.java
 * 包名:com.github.cherry.serializer.xml.test
 * 创建日期:2017年9月22日下午4:38:17
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.test;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@JacksonXmlRootElement(localName="pair")
public class Pair<T> {

    private T first;
    
    @JacksonXmlElementWrapper(localName = "items")
    private List<T> item;

    /**
     * first.
     *
     * @return the first
     * @since 1.0
     */
    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * list.
     *
     * @return the list
     * @since 1.0
     */
    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }

}
