/**
 * 工程名:cherry-serializer-json
 * 文件名:User.java
 * 包名:com.github.cherry.serializer.json.test
 * 创建日期:2017年9月22日上午10:41:04
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.json.test;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class User {
    private String id;

    private String name;
    
    private List<Address> addressList;

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
     * addressList.
     *
     * @return  the addressList
     * @since   1.0
     */
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
    
    
}
