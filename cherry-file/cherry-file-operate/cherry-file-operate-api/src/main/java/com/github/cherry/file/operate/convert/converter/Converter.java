package com.github.cherry.file.operate.convert.converter;

/**
 * A converter converts a source object of type {@code S} to a target of type {@code T}.
 *
 * <p>
 * Implementations of this interface are thread-safe and can be shared.
 *
 *
 * 
 * @param <S> the source type
 * @param <T> the target type
 */
public interface Converter<S, T> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     * 
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    T convert(S source);

}
