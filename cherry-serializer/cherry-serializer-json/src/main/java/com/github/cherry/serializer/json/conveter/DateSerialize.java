package com.github.cherry.serializer.json.conveter;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.cherry.framework.common.utils.DateUtils;
import com.github.cherry.framework.common.utils.StringUtils;
import com.github.cherry.serializer.json.annotation.DateFormat;

/**
 * 日期格式化
 *
 * @author Scott
 * @since 1.0
 */
public class DateSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private String original;

    private String target;

    public DateSerialize(String original, String target) {
        this.original = original;
        this.target = target;
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
                DateFormat dateFormat = property.getAnnotation(DateFormat.class);
                if (dateFormat == null) {
                    dateFormat = property.getContextAnnotation(DateFormat.class);
                }
                if (dateFormat != null) {
                    return new DateSerialize(dateFormat.original(), dateFormat.target());
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
            Date d = DateUtils.string2Date(value, original);
            gen.writeString(DateUtils.formatDate(d, target));
        } else {
            gen.writeNull();
        }

    }

}
