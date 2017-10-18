package com.github.cherry.signature.rsa;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.cherry.framework.common.encrypt.Base64Encrypter;
import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.IOUtils;
import com.github.cherry.framework.common.utils.StringUtils;
import com.github.cherry.signature.SignatureService;
import com.github.cherry.signature.exception.SignatureException;

/**
 * RSA签名/验签工具
 * 
 * @author Scott
 * @since 1.0
 */
public class RsaSignature implements SignatureService {
    private static Log LOG = LogFactory.getLog(RsaSignature.class);

    private static final String KEY_FACTORY_NAME_DEFAULT = "RSA";

    private static final String SIGNATURE_INSTANCE_NAME_DEFAULT = "SHA1WithRSA";

    private PrivateKey gatewayPrivateKey;

    private RsaSignatureConfiguration config;

    public RsaSignature(RsaSignatureConfiguration config) {
        Assert.notNull(config, "config cannot be null");
        Assert.notNull(config.getJksInputStream(), "jks file cannot found");
        Assert.notNull(config.getGatewayAlias(), "parameter [gatewayAlias] is not key entry!");
        this.config = config;
    }

    public void init() {
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

            this.gatewayPrivateKey = (PrivateKey) jks.getKey(config.getGatewayAlias(), config.getPassword()
                    .toCharArray());

        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("init with error", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * @throws SignatureException
     * @see com.github.cherry.signature.SignatureService#sign(java.lang.String, java.lang.String)
     */
    @Override
    public String sign(String salt, String data) throws SignatureException {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_INSTANCE_NAME_DEFAULT);
            SecureRandom random = null;
            if (salt != null) {
                random = new SecureRandom(salt.getBytes());
            }
            signature.initSign(gatewayPrivateKey, random);
            signature.update(data.getBytes(config.getSignatureEncode()));
            return Base64Encrypter.toBase64(signature.sign());
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("sign with error", e);
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
            Signature signature = Signature.getInstance(SIGNATURE_INSTANCE_NAME_DEFAULT);
            signature.initVerify(key);
            signature.update(data.getBytes(config.getSignatureEncode()));
            return signature.verify(Base64Encrypter.fromBase64(sign.getBytes(config.getSignatureEncode())));
        } catch (Exception e) {
            LOG.error(e);
            throw new SignatureException("verifySignature with error", e);
        }
    }

    private PublicKey string2PublicKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes(config
                .getSignatureEncode())));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_NAME_DEFAULT);
        return keyFactory.generatePublic(x509KeySpec);
    }

}
