package com.github.cherry.serializer.xml.xsteam;

import java.util.concurrent.ConcurrentHashMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class XStreamFactory {
    private static volatile XStream xstream;

    private static ConcurrentHashMap<Class<?>, XStream> xstreamMap = new ConcurrentHashMap<Class<?>, XStream>();

    public static XStream getInstance() {
        if (xstream == null) {
            synchronized (XStreamFactory.class) {
                if (xstream == null) {
                    xstream = new XStream(new StaxDriver(new NoNameCoder()));
                }
            }
        }
        return xstream;
    }

    public static XStream getInstance(Class<?> clazz) {
        XStream xStream = xstreamMap.get(clazz);
        if (xStream == null) {
            XStream newInstance = new XStream(new StaxDriver(new NoNameCoder()));
            newInstance.processAnnotations(clazz);
            newInstance.aliasSystemAttribute(null, "class");
            newInstance.setMode(XStream.NO_REFERENCES);
            xStream = xstreamMap.putIfAbsent(clazz, newInstance);
            if (xStream == null) {
                xStream = newInstance;
            }
        }
        return xStream;
    }
}
