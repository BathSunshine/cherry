package com.github.cherry.serializer.json.conveter;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.cherry.framework.common.utils.MaskUtils;
import com.github.cherry.framework.common.utils.StringUtils;
import com.github.cherry.serializer.json.annotation.MaskField;

/**
 * 隐藏关键信息
 *
 * @author Scott
 * @since 1.0
 */
public class HideFieldSerialize extends JsonSerializer<String> implements ContextualSerializer {

    public static final int FromHead = 0;

    public static final int FromLast = 1;

    private int mask;

    private int maskType;

    public HideFieldSerialize() {
        this(0, FromHead);
    }

    public HideFieldSerialize(int mask, int maskType) {
        this.mask = mask;
        this.maskType = maskType;
    }

    /**
     * @see com.fasterxml.jackson.databind.ser.ContextualSerializer#createContextual(com.fasterxml.jackson.databind.SerializerProvider,
     * com.fasterxml.jackson.databind.BeanProperty)
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        if (property != null) {
            if (Objects.equals(property.getType().getRawClass(), String.class)) {
                MaskField maskField = property.getAnnotation(MaskField.class);
                if (maskField == null) {
                    maskField = property.getContextAnnotation(MaskField.class);
                }
                if (maskField != null) {
                    return new HideFieldSerialize(maskField.mask(), maskField.maskType());
                }
            }
            return prov.findValueSerializer(property.getType(), property);
        }
        return prov.findNullValueSerializer(property);
    }

    /**
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
     * com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        if (!StringUtils.isEmpty(value)) {
            int[] index = MaskUtils.maskIndex(value, maskType, mask);
            gen.writeString(MaskUtils.mask(value, index[0], index[1]));
        } else {
            gen.writeNull();
        }

    }

}
