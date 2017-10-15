package com.github.cherry.signature;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public interface SignatureService {

    String sign(String salt, String data);
    
    boolean verifySignature(String key, String data, String sign);
}
