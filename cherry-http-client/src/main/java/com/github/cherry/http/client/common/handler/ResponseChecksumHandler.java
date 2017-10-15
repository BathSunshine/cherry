package com.github.cherry.http.client.common.handler;

import java.io.InputStream;
import java.util.zip.CheckedInputStream;

import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.utils.CRC64;

public class ResponseChecksumHandler implements ResponseHandler {

    @Override
    public void handle(ResponseMessage response) {
        InputStream originalInputStream = response.getContent();
        if (originalInputStream == null) {
            return;
        }

        CRC64 crc = new CRC64();
        CheckedInputStream checkedInputstream = new CheckedInputStream(originalInputStream, crc);
        response.setContent(checkedInputstream);
    }

}
