package com.github.cherry.signature.rsa.test;

import java.security.SignatureException;

import org.junit.Test;

import com.github.cherry.signature.rsa.RsaSignature;

import junit.framework.Assert;

public class RsaSignatureTest {
    @Test
    public void testSign() {
//        String keyStorePath = "C:/Users/DELL/git/cherry/cherry-signature/cherry-signature-rsa/src/test/resources/test.jks";
        String keyStorePath = "test.jks";
        String password = "password";
        String gatewayAlias = null;
        String signResult;
        try {
            RsaSignature rsaSignature = new RsaSignature(keyStorePath, password, gatewayAlias);
            String data = "testsigndata";
            String salt = "456";
            signResult = rsaSignature.sign(salt, data);
            Assert.assertNotNull(signResult);
        } catch (SignatureException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testVerifySignature() {
        String signResultTrue = "tCc+EHxEA7ouBeU1oLMfN8uxxHNqj5zs4cHDZM+gk2DLQXKl5QQdHYiTVNFyDBLhptb6ZFzrptV1z7Kh9AbsEDrAQb0M+xl91r9KTj5Mp6KGp2g1Y/uWW0q85oNOcwchDlASZ1BJ3srSWY3xCpGh2RkjNcfgzobZxl7U+iZiF3o=";
        String signResultFalse = "tCc+EHxEA7ouBeU1oLMfN8uxxHNqj5zs4cHDZM+gk2DLQ1XKl5QQdHYiTVNFyDBLhptb6ZFzrptV1z7Kh9AbsEDrAQb0M+xl91r9KTj5Mp6KGp2g1Y/uWW0q85oNOcwchDlASZ1BJ3srSWY3xCpGh2RkjNcfgzobZxl7U+iZiF3o=";

        String keyStorePath = "test.jks";
        String password = "password";
        String data = "testsigndata";
        try {
            RsaSignature rsaSignature = new RsaSignature(keyStorePath, password);
            boolean result = rsaSignature.verifySignature(data, signResultTrue);
            Assert.assertTrue(result);
            result = rsaSignature.verifySignature(data, signResultFalse);
            Assert.assertFalse(result);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}
