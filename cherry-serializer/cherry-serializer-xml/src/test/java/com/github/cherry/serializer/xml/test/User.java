/**
 * 工程名:cherry-serializer-xml
 * 文件名:User.java
 * 包名:com.github.cherry.serializer.xml.test
 * 创建日期:2017年9月22日下午2:39:12
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.test;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@XStreamAlias("user")
public class User {
    private String id;

    private String name;
    
    @JacksonXmlProperty(localName="card_no")
    @XStreamAlias("card_no")
    private String cardNo;

    /**
     * id.
     *
     * @return the id
     * @since 1.0
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * name.
     *
     * @return the name
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * cardNo.
     *
     * @return  the cardNo
     * @since   1.0
     */
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
