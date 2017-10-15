/**
 * 工程名:cherry-http-client
 * 文件名:EmptyResponseParser.java
 * 包名:com.github.cherry.http.client.common.parser
 * 创建日期:2017年9月12日下午2:53:43
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.common.parser;

import com.github.cherry.http.client.ClientUtil;
import com.github.cherry.http.client.common.comm.ResponseMessage;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class EmptyResponseParser implements ResponseParser<ResponseMessage> {

    /**
     * @see com.github.cherry.http.client.common.parser.ResponseParser#parse(com.github.cherry.http.client.common.comm.ResponseMessage)
     */
    @Override
    public ResponseMessage parse(ResponseMessage response) throws ResponseParseException {
        ClientUtil.safeCloseResponse(response);
        return response;
    }

}
