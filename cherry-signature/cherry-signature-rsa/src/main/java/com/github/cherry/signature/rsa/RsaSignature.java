package com.github.cherry.signature.rsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.cherry.framework.common.utils.StringUtils;
import com.github.cherry.signature.SignatureService;

/**
 * RSA签名/验签工具
 * 
 * @author Scott
 * @since 1.0
 */
public class RsaSignature implements SignatureService {
    public static Log LOG = LogFactory.getLog(RsaSignature.class);

    private RsaSignatureConfiguration config;

    public RsaSignature(RsaSignatureConfiguration config) {
        this.config = config;
    }

    public void init() throws SignatureException {
        if (config == null) {
            String errorMsg = "初始化异常,config属性未配置";
            LOG.error(errorMsg);
            throw new SignatureException(errorMsg);
        }
        if (config.getJksInputStream() == null) {
            String errorMsg = "初始化异常,config.InputStream属性未配置";
            LOG.error(errorMsg);
            throw new SignatureException(errorMsg);
        }
        InputStream is = config.getJksInputStream();
        try {
            KeyStore jks = KeyStore.getInstance(KeyStore.getDefaultType());
            jks.load(is, config.getPassword().toCharArray());
            if (StringUtils.isEmpty(config.getGatewayAlias())) {
                Enumeration<String> en = jks.aliases();
                for (; en.hasMoreElements(); en.nextElement()) {
                    String aliase = en.nextElement();
                    if (jks.isKeyEntry(aliase)) {
                        config.setGatewayAlias(aliase);
                        break;
                    }
                }
            }

            if (!jks.isKeyEntry(config.getGatewayAlias())) {
                LOG.error("初始化异常,parameter [gatewayAlias] is not key entry!");
            }

            PrivateKey gatewayPrivateKey = (PrivateKey) jks.getKey(config.getGatewayAlias(),
                    config.getPassword().toCharArray());
            this.gatewayPrivateKey = gatewayPrivateKey;
            String str = Base64.encodeBase64String(gatewayPrivateKey.getEncoded());
            LOG.debug(config.getGatewayAlias() + " privateKey base64:" + str);

            Certificate cert = jks.getCertificate(config.getGatewayAlias());
            PublicKey gatewayPublicKey = cert.getPublicKey();
            str = Base64.encodeBase64String(gatewayPublicKey.getEncoded());
            LOG.debug(config.getGatewayAlias() + "publicKey base64:" + str);
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("初始化异常", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }
    }

    /**
     * @throws SignatureException
     * @see com.github.cherry.signature.SignatureService#sign(java.lang.String, java.lang.String)
     */
    @Override
    public String sign(String salt, String data) throws SignatureException {
        try {
            Signature signature = Signature.getInstance(config.getSignatureInstanceName());
            SecureRandom random = null;
            if (salt != null) {
                random = new SecureRandom(salt.getBytes());
            }
            signature.initSign(gatewayPrivateKey, random);
            signature.update(data.getBytes(config.getSignatureEncode()));
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("签名失败", e);
        }
    }

    /**
     * @see com.github.cherry.signature.SignatureService#verifySignature(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public boolean verifySignature(String publicKey, String data, String sign) throws SignatureException {
        try {
            PublicKey key = string2PublicKey(publicKey);
            Signature signature = Signature.getInstance(config.getSignatureInstanceName());
            signature.initVerify(key);
            signature.update(data.getBytes(config.getSignatureEncode()));
            return signature.verify(Base64.decodeBase64(sign.getBytes(config.getSignatureEncode())));
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("验签失败", e);
        }
    }

    private PublicKey string2PublicKey(String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(key.getBytes(config.getSignatureEncode())));
        KeyFactory keyFactory = KeyFactory.getInstance(config.getKeyFactoryName());
        return keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * gatewayPrivateKey:私钥
     */
    private PrivateKey gatewayPrivateKey;

    public RsaSignatureConfiguration getConfig() {
        return config;
    }

    public void setConfig(RsaSignatureConfiguration config) {
        this.config = config;
    }
}
