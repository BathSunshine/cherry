/**
 * 工程名:cherry-serializer-json
 * 文件名:Pair.java
 * 包名:com.github.cherry.serializer.json.test
 * 创建日期:2017年9月22日上午11:28:27
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.json.test;

/**
 *〈一句话功能简述〉
 *〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class Pair<T> {
    
    private T first;
    
    private T second;

    /**
     * first.
     *
     * @return  the first
     * @since   1.0
     */
    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * second.
     *
     * @return  the second
     * @since   1.0
     */
    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
    
    
}
