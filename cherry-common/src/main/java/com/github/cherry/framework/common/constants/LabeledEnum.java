package com.github.cherry.framework.common.constants;

public interface LabeledEnum<E extends Enum<E>> {
    String name();

    String getLabel();

    int ordinal();
}
