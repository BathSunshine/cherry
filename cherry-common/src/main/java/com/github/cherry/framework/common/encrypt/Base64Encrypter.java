package com.github.cherry.framework.common.encrypt;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public final class Base64Encrypter {
    /**
     * Base64 <一句话功能简述> <功能详细描述>
     * 
     * @param source
     * @return
     */
    public static String toBase64(String source) {
        try {
            return Base64.encodeBase64String(source.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException("encode error", e);
        }
    }

    /**
     * 
     * 解码Base64 <功能详细描述>
     * 
     * @param base64
     * @return
     */
    public static String fromBase64(String base64) {
        try {
            byte[] bs = Base64.decodeBase64(base64);
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException("decode error", e);
        }
    }

    /**
     * 
     * 加密Base64 <功能详细描述>
     * 
     * @param bytes
     * @return
     */
    public static String encodeUrlSafeBase64(byte[] bytes) {
        String base64 = Base64.encodeBase64String(bytes);
        base64 = base64.replace('+', '-');
        base64 = base64.replace('/', '_');
        base64 = base64.replace('=', ',');
        return base64;
    }

    /**
     * 
     * 解密Base64 <功能详细描述>
     * 
     * @param str
     * @return
     */
    public static byte[] decodeUrlSafeBase64(String str) {
        String base64 = str;
        base64 = base64.replace('-', '+');
        base64 = base64.replace('_', '/');
        base64 = base64.replace(',', '=');
        return Base64.decodeBase64(base64);
    }

    /**
     * 
     * 加密Base64 <功能详细描述>
     * 
     * @param str
     * @return
     */
    public static String toUrlSafeBase64(String str) {
        try {
            return encodeUrlSafeBase64(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException("encode error", e);
        }
    }

    /**
     * 
     * 解密Base64 <功能详细描述>
     * 
     * @param str
     * @return
     */
    public static String fromUrlSafeBase64(String str) {
        try {
            byte[] bs = decodeUrlSafeBase64(str);
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException("decode error", e);
        }
    }
}
