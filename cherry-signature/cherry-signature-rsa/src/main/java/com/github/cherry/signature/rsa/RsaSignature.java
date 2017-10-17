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
    private String keyStorePath;
    private String password;
    private String gatewayAlias;

    private PrivateKey gatewayPrivateKey;

    private PublicKey gatewayPublicKey;

    /**
     * Creates a new instance of RsaSignature.
     *
     * @param keyStorePath 证书路径
     * @param password 私钥密码
     * @param gatewayAlias 别名
     * @throws SignatureException
     */
    public RsaSignature(String keyStorePath, String password, String gatewayAlias) throws SignatureException {
        super();
        this.keyStorePath = keyStorePath;
        this.password = password;
        this.gatewayAlias = gatewayAlias;
        init();
    }

    /**
     * Creates a new instance of RsaSignature.
     *
     * @param keyStorePath 证书路径
     * @param password 私钥密码
     * @throws SignatureException
     */
    public RsaSignature(String keyStorePath, String password) throws SignatureException {
        this(keyStorePath, password, null);
    }

    protected void init() throws SignatureException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(keyStorePath);
        try {
            KeyStore jks = KeyStore.getInstance(KeyStore.getDefaultType());
            jks.load(is, password.toCharArray());
            if (StringUtils.isEmpty(gatewayAlias)) {
                Enumeration<String> en = jks.aliases();
                for (; en.hasMoreElements(); en.nextElement()) {
                    String aliase = en.nextElement();
                    if (jks.isKeyEntry(aliase)) {
                        gatewayAlias = aliase;
                        break;
                    }
                }
            }

            if (!jks.isKeyEntry(gatewayAlias)) {
                LOG.error("parameter [gatewayAlias] is not key entry!");
            }

            gatewayPrivateKey = (PrivateKey) jks.getKey(gatewayAlias, password.toCharArray());
            String str = Base64.encodeBase64String(gatewayPrivateKey.getEncoded());
            LOG.debug("gatewayPrivateKey base64:" + str);

            Certificate cert = jks.getCertificate(gatewayAlias);
            gatewayPublicKey = cert.getPublicKey();
            str = Base64.encodeBase64String(gatewayPublicKey.getEncoded());
            LOG.debug("gatewayPublicKey base64:" + str);
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
            Signature signature = Signature.getInstance("SHA1WithRSA");
            SecureRandom random = null;
            if (salt != null) {
                random = new SecureRandom(salt.getBytes());
            }
            signature.initSign(gatewayPrivateKey, random);
            signature.update(data.getBytes("UTF-8"));
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
    public boolean verifySignature(String key, String data, String sign) throws SignatureException {
        try {
            PublicKey publicKey = string2PublicKey(key);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.verify(Base64.decodeBase64(sign.getBytes("UTF-8")));
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("验签失败", e);
        }
    }

    /**
     * 对象初始化时设置，若未设置，将自动从jks文件中读取公钥
     * 
     * @see com.github.cherry.signature.SignatureService#verifySignature(java.lang.String, java.lang.String)
     */
    @Override
    public boolean verifySignature(String data, String sign) throws SignatureException {
        try {
            PublicKey publicKey = getGatewayPublicKey();
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.verify(Base64.decodeBase64(sign.getBytes("UTF-8")));
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("验签失败", e);
        }
    }

    private PublicKey string2PublicKey(String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509KeySpec);
    }

    public PublicKey getGatewayPublicKey() {
        return gatewayPublicKey;
    }

    public String publicKey2String(PublicKey key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
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
}
