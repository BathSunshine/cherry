/**
 * 工程名:fintech-common
 * 文件名:MaskField.java
 * 包名:com.zxhy.frame.common.converter
 * 创建日期:2016年11月2日上午10:13:03
 * Copyright (c) 2016 ZXHY Information Technology Corporation, All rights reserved.
 * The Software is owned by ZXHY and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains ZXHY's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.cherry.serializer.json.conveter.HideFieldSerialize;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author Scott
 * @since 1.0
 */
@Target(
{ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = HideFieldSerialize.class)
@JacksonAnnotationsInside
public @interface MaskField {
    int mask() default 0;
    
    int maskType() default 0;
}
