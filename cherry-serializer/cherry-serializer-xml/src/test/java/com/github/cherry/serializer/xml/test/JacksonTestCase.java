/**
 * 工程名:cherry-serializer-xml
 * 文件名:JacksonTestCase.java
 * 包名:com.github.cherry.serializer.xml.test
 * 创建日期:2017年9月25日上午12:04:20
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.test;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.SerializerTypeReference;
import com.github.cherry.serializer.xml.jackson.XmlSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class JacksonTestCase {

    private Serializer serializer = new XmlSerializer();

    @Test
    public void test1() {
        User user = new User();
        user.setId("abc");
        user.setCardNo("aaccc");
        
        List<User> list = new ArrayList<User>();
        list.add(user);

        Pair<User> pair = new Pair<User>();
        pair.setFirst(user);
        pair.setItem(list);
        
        String xml = serializer.serializeAsString(pair);
        System.out.println(xml);

        pair = serializer.deserialize(xml, new SerializerTypeReference<Pair<User>>() {
        });
        
        Assert.assertEquals(pair.getFirst().getCardNo(), "aaccc");
        Assert.assertEquals(pair.getItem().get(0).getCardNo(), "aaccc");
    }
}
