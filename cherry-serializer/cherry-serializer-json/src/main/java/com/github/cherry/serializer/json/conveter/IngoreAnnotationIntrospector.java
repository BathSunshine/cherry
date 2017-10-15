package com.github.cherry.serializer.json.conveter;

import java.lang.annotation.Annotation;
import java.util.List;

import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.github.cherry.framework.common.utils.CollectionUtils;

/**
 * 忽略隐藏字段的注解
 *
 * @author Scott
 * @since 1.0
 */
public class IngoreAnnotationIntrospector extends JacksonAnnotationIntrospector {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -2954715354293658415L;

    private List<Class<? extends Annotation>> annotations;

    @Override
    public boolean isAnnotationBundle(Annotation ann) {
        if (checkAnnotation(ann.annotationType())) {
            return super.isAnnotationBundle(ann);
        } else {
            return false;
        }
    }

    private boolean checkAnnotation(Class<? extends Annotation> type) {
        if (!CollectionUtils.isEmpty(annotations)) {
            for (Class<? extends Annotation> a : annotations) {
                if (a.equals(type)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param annotations 要设置的 annotations
     * @since 1.0
     */
    public void setAnnotations(List<Class<? extends Annotation>> annotations) {
        this.annotations = annotations;
    }
}
