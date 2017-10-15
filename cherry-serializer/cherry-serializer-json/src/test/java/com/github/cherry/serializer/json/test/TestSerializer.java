/**
 * 工程名:cherry-serializer-json
 * 文件名:TestSerializer.java
 * 包名:com.github.cherry.serializer.json.test
 * 创建日期:2017年9月22日上午10:21:33
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.json.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.SerializerTypeReference;
import com.github.cherry.serializer.json.JsonSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class TestSerializer {
    private Serializer serializer = new JsonSerializer();

    @Test
    public void test1() {
        User user = new User();
        user.setId("dddd");
        user.setName("aaaa");
        String str = serializer.serializeAsString(user);
        System.out.println(str);
        Assert.assertNotNull(str);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId("dddd");
        user.setName("aaaa");
        byte[] bytes = serializer.serializeAsByte(user);
        System.out.println(bytes);
        Assert.assertNotNull(bytes);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setId("dddd");
        user.setName("aaaa");
        String str = serializer.serializeAsString(user);
        user = serializer.deserialize(str, User.class);
        Assert.assertEquals(user.getId(), "dddd");
    }

    @Test
    public void test4() {
        User user = new User();
        user.setId("dddd");
        user.setName("aaaa");
        byte[] str = serializer.serializeAsByte(user);
        user = serializer.deserialize(str, User.class);
        Assert.assertEquals(user.getId(), "dddd");
    }

    @Test
    public void test5() {
        User user = new User();
        user.setId("dddd");
        user.setName("aaaa");
        List<Address> list = new ArrayList<Address>();
        Address address = new Address();
        address.setAddress("123456");
        list.add(address);
        user.setAddressList(list);

        String str = serializer.serializeAsString(user);
        System.out.println(str);
        user = serializer.deserialize(str, User.class);
        Assert.assertEquals(user.getId(), "dddd");
        Assert.assertEquals(user.getAddressList().get(0).getAddress(), "123456");
    }

    @Test
    public void test6() {
        List<Address> list = new ArrayList<Address>();
        Address address = new Address();
        address.setAddress("123456");
        list.add(address);
        
        String str = serializer.serializeAsString(list);
        System.out.println(str);
        
        list = serializer.deserialize(str, new SerializerTypeReference<List<Address>>() {
        });
        
 
        Assert.assertEquals(list.get(0).getAddress(), "123456");
    }

}
