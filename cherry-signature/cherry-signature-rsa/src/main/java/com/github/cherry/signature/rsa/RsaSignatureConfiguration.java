package com.github.cherry.signature.rsa;

import java.io.InputStream;

public class RsaSignatureConfiguration {
    /**
     * 加密文件
     */
    private InputStream jksInputStream;
    /**
     * password:私钥密码
     */
    private String password;
    /**
     * gatewayAlias:别名
     */
    private String gatewayAlias;

    /**
     * SIGNATURE_ENCODE_DEFAULT:字符编码默认值
     */
    private static final String SIGNATURE_ENCODE_DEFAULT = "UTF-8";

    /**
     * signatureEncode:字符编码
     */
    private String signatureEncode = SIGNATURE_ENCODE_DEFAULT;

    public InputStream getJksInputStream() {
        return jksInputStream;
    }

    public void setJksInputStream(InputStream jksInputStream) {
        this.jksInputStream = jksInputStream;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGatewayAlias() {
        return gatewayAlias;
    }

    public void setGatewayAlias(String gatewayAlias) {
        this.gatewayAlias = gatewayAlias;
    }

    public String getSignatureEncode() {
        return signatureEncode;
    }

    public void setSignatureEncode(String signatureEncode) {
        this.signatureEncode = signatureEncode;
    }
}
