package com.github.cherry.serializer.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.cherry.framework.common.utils.DateUtils;
import com.github.cherry.serializer.json.conveter.DateSerialize;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author Scott
 * @since 1.0
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = DateSerialize.class)
@JacksonAnnotationsInside
public @interface DateFormat {
    String original();

    String target() default DateUtils.GENERAL_FULL_DATE_FORMAT;
}
