package com.github.cherry.serializer.json.conveter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.cherry.framework.common.constants.LabeledEnum;

/**
 * 序列化枚举为json对象
 * 
 * 如{id:xx,label:xx,name:xx}
 *
 * @JsonSerialize(using = EnumSerialize.class)
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
public class EnumSerialize extends JsonSerializer<LabeledEnum> {

    /**
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
     * com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(LabeledEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeStartObject();
            gen.writeFieldName("id");
            gen.writeNumber(value.ordinal());

            gen.writeFieldName("name");
            gen.writeString(value.name());

            gen.writeFieldName("label");
            gen.writeString(value.getLabel());
            gen.writeEndObject();
        }
    }

}
