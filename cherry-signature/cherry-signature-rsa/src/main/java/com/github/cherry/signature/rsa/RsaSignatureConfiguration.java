package com.github.cherry.signature.rsa;

import java.io.InputStream;

public class RsaSignatureConfiguration {
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
     * KEY_FACTORY_NAME_DEFAULT:公钥，工厂类型名称默认值
     */
    public static final String KEY_FACTORY_NAME_DEFAULT = "RSA";
    /**
     * SIGNATURE_INSTANCE_NAME_DEFAULT:加密算法名称默认值
     */
    public static final String SIGNATURE_INSTANCE_NAME_DEFAULT = "SHA1WithRSA";
    /**
     * SIGNATURE_ENCODE_DEFAULT:字符编码默认值
     */
    public static final String SIGNATURE_ENCODE_DEFAULT = "UTF-8";

    /**
     * keyFactoryName:公钥，工厂类型名称
     */
    private String keyFactoryName = KEY_FACTORY_NAME_DEFAULT;
    /**
     * signatureInstanceName:加密算法名称
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

    public String getSignatureEncode() {
        return signatureEncode;
    }

    public void setSignatureEncode(String signatureEncode) {
        this.signatureEncode = signatureEncode;
    }
}
