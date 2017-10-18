package com.github.cherry.signature;

import java.security.SignatureException;

/**
 * 签名/验签接口
 *
 * @author Scott
 * @since 1.0
 */
public interface SignatureService {

    /**
     * 数据签名
     * 
     * @param salt 种子，可以为空
     * @param data 数据，不可为空
     * @return 签名
     */
    String sign(String salt, String data) throws SignatureException;

    /**
     * 验签
     * <p>
     * 自定义公钥
     * 
     * @param publicKey 公钥
     * @param data 数据
     * @param sign 签名
     * @return 验签是否成功
     */
    boolean verifySignature(String publicKey, String data, String sign) throws SignatureException;
}
