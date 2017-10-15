package com.github.cherry.http.client.common.parser;

import com.github.cherry.http.client.common.comm.ResponseMessage;

/**
 * Used to convert an result stream to a java object.
 */
public interface ResponseParser<T> {
    /**
     * Converts the result from stream to a java object.
     * 
     * @param resultStream The stream of the result.
     * @return The java Type T object that the result stands for.
     * @throws ResponseParseException Failed to parse the result.
     */
    public T parse(ResponseMessage response) throws ResponseParseException;
}
