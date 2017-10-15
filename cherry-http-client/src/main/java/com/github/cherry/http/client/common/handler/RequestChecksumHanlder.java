package com.github.cherry.http.client.common.handler;

import java.io.InputStream;
import java.util.zip.CheckedInputStream;

import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.utils.CRC64;

public class RequestChecksumHanlder implements RequestHandler {

    @Override
    public void handle(RequestMessage request) {
        InputStream originalInputStream = request.getContent();
        if (originalInputStream == null) {
            return;
        }

        CRC64 crc = new CRC64();
        CheckedInputStream checkedInputstream = new CheckedInputStream(originalInputStream, crc);
        request.setContent(checkedInputstream);
    }

}
