/**
 * 工程名:cherry-http-client
 * 文件名:DealResponseParser.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午6:39:36
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.parser.ResponseParseException;
import com.github.cherry.http.client.common.parser.ResponseParser;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class DealResponseParser implements ResponseParser<DealListResult> {

    /**
     * @see com.github.cherry.http.client.common.parser.ResponseParser#parse(com.github.cherry.http.client.common.comm.ResponseMessage)
     */
    @Override
    public DealListResult parse(ResponseMessage response) throws ResponseParseException {
        return JsonUtil.parseJson(response.getContent(), DealListResult.class);
    }

}
