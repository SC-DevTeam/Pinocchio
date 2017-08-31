package com.scdevteam.messages;

import com.scdevteam.Utils;
import com.scdevteam.crypto.sodium.crypto.BaseCrypto;

import java.nio.ByteBuffer;

public class RequestMessage extends Message {
    public RequestMessage(int messageId, int payloadLength,
                          int version, byte[] payload, BaseCrypto baseCrypto) {
        super(true);

        mMessageID = messageId;
        mPayloadLength = payloadLength;
        mVersion = version;

        mDecrypted = payload;
        baseCrypto.encryptPacket(this);
    }

    public ByteBuffer buildMessage() {
        byte[] out = new byte[7 + mEncrypted.length];
        System.arraycopy(Utils.fromInt16(mMessageID), 0, out, 0, 2);
        System.arraycopy(Utils.fromInt24(mEncrypted.length), 0, out, 2, 3);
        System.arraycopy(Utils.fromInt16(mVersion), 0, out, 5, 2);
        System.arraycopy(mEncrypted, 0, out, 7, mEncrypted.length);
        return ByteBuffer.wrap(out);
    }
}
