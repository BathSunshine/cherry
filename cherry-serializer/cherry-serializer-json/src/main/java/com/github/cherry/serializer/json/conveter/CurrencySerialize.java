package com.github.cherry.serializer.json.conveter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.cherry.framework.common.utils.NumberUtils;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author Scott
 * @since 1.0
 */
public class CurrencySerialize extends JsonSerializer<BigDecimal> {

    /**
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
     * com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        if (value != null) {
            gen.writeString(NumberUtils.formatCurrency(value, Locale.CHINA));
        } else {
            gen.writeNull();
        }
    }

}
