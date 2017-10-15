package com.github.cherry.signature.rsa;

import com.github.cherry.signature.SignatureService;

/**
 *〈一句话功能简述〉
 *〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class RsaSignature implements SignatureService{
    
    
    
    /**
     * @see com.github.cherry.signature.SignatureService#sign(java.lang.String, java.lang.String)
     */
    @Override
    public String sign(String salt, String data) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.github.cherry.signature.SignatureService#verifySignature(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean verifySignature(String key, String data, String sign) {
        // TODO Auto-generated method stub
        return false;
    }

}
