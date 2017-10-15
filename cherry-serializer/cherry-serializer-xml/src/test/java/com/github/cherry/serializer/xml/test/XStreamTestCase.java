/**
 * 工程名:cherry-serializer-xml
 * 文件名:Test.java
 * 包名:com.github.cherry.serializer.xml.test
 * 创建日期:2017年9月22日下午2:39:01
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.test;

import junit.framework.Assert;

import org.junit.Test;

import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.xml.xsteam.XmlSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class XStreamTestCase {

    private Serializer serializer = new XmlSerializer();

    @Test
    public void test1() {
         User user = new User();
         user.setId("123");
         user.setCardNo("dsadas");
         user.setName("abc");
         String xml = serializer.serializeAsString(user);
         System.out.println(xml);
         Assert.assertNotNull(xml);
         byte[] bytes = serializer.serializeAsByte(user);
         System.out.println(bytes);
         Assert.assertNotNull(bytes);
        
         user = serializer.deserialize(bytes, User.class);
         Assert.assertEquals(user.getId(), "123");
         user = serializer.deserialize(xml, User.class);
         Assert.assertEquals(user.getId(), "123");
  
    }

}
