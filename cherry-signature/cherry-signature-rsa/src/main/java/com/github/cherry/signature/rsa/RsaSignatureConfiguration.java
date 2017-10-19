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
     * SIGNATURE_ENCODE_DEFAULT:character encode default value
     */
    private static final String SIGNATURE_ENCODE_DEFAULT = "UTF-8";

    /**
     * KEY_FACTORY_NAME_DEFAULT:KeyFactory algorithm default value.
     */
    private static final String KEY_FACTORY_NAME_DEFAULT = "RSA";

    /**
     * SIGNATURE_INSTANCE_NAME_DEFAULT:signature algorithm default value.
     */
    private static final String SIGNATURE_INSTANCE_NAME_DEFAULT = "SHA1WithRSA";

    /**
     * keyFactoryName:KeyFactory algorithm
     */
    private String keyFactoryName = KEY_FACTORY_NAME_DEFAULT;
    /**
     * signatureInstanceName:signature algorithm.
     */
    private String signatureInstanceName = SIGNATURE_INSTANCE_NAME_DEFAULT;

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

    public String getKeyFactoryName() {
        return keyFactoryName;
    }

    public void setKeyFactoryName(String keyFactoryName) {
        this.keyFactoryName = keyFactoryName;
    }

    public String getSignatureInstanceName() {
        return signatureInstanceName;
    }

    public void setSignatureInstanceName(String signatureInstanceName) {
        this.signatureInstanceName = signatureInstanceName;
    }

}
