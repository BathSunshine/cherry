package com.github.cherry.file.operate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.cherry.file.operate.convert.converter.Converter;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FileFieldDeserialize {
    @SuppressWarnings("rawtypes")
    public Class<? extends Converter> using();
}
