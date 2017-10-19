package com.github.cherry.signature.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.TreeMap;

import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.ReflectionUtils;
import com.github.cherry.framework.common.utils.ReflectionUtils.FieldCallback;
import com.github.cherry.framework.common.utils.StringUtils;
import com.github.cherry.signature.annotation.IgnoreSign;

public abstract class SignatureUtils {
    private static final String SEPARATOR = "&";
    private static final String LINK_STRING = "=";
    private static final String DEFAULT_CHARACTOR_ENCODE = "UTF-8";

    /**
     * 将DTO对象转化为有序的TreeMap
     * @param dto
     * @return
     */
    public static TreeMap<String, String> buildParamterMap(Object dto) {
        Assert.notNull(dto, "object cannot be null");
        TreeMap<String, String> parameterMap = new TreeMap<>();
        ReflectionUtils.doWithFields(dto.getClass(), new SignatureBuildMapFieldCallback(dto, parameterMap));
        return parameterMap;
    }

    private static final class SignatureBuildMapFieldCallback implements FieldCallback {
        private Object data;
        private TreeMap<String, String> parameterMap;

        public SignatureBuildMapFieldCallback(Object data, TreeMap<String, String> parameterMap) {
            this.data = data;
            this.parameterMap = parameterMap;
        }

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            IgnoreSign clazz = field.getAnnotation(IgnoreSign.class);
            if (clazz == null) {
                if (!field.isAccessible()) {
                    ReflectionUtils.makeAccessible(field);
                }

                Object value = ReflectionUtils.getField(field, data);
                if (value == null) {
                    return;
                }
                parameterMap.put(field.getName(), value.toString());
            }
        }
    }

    /**
     * 将TreeMap转化为
     * @param parameterMap
     * @return
     */
    public static String buildData(TreeMap<String, String> parameterMap) {
        StringBuilder sb = new StringBuilder();
        for (String key : parameterMap.keySet()) {
            String value = parameterMap.get(key);
            sb.append(key).append(LINK_STRING).append(urlEncode(value, DEFAULT_CHARACTOR_ENCODE)).append(SEPARATOR);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String urlEncode(String value, String encoding) {
        if (value == null) {
            return "";
        }

        try {
            String encoded = URLEncoder.encode(value, encoding);
            return encoded.replace("+", "%20").replace("*", "%2A").replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("encode failed", e);
        }
    }

    public static String urlDecode(String value, String encoding) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }

        try {
            return URLDecoder.decode(value, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("decode failed", e);
        }
    }
}
