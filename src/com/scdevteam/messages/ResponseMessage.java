package com.scdevteam.messages;

import com.scdevteam.Utils;
import com.scdevteam.crypto.sodium.crypto.BaseCrypto;
import com.scdevteam.maps.MessageMap;

import java.nio.ByteBuffer;

public class ResponseMessage extends Message {
    private final ByteBuffer mChunks;

    public ResponseMessage(int msgId, int len, int ver) {
        super(false);
        mMessageID = msgId;
        mPayloadLength = len;
        mVersion = ver;

        mChunks = ByteBuffer.allocate(len);
    }

    public void finish(ByteBuffer payload, BaseCrypto baseCrypto) {
        mEncrypted = payload.array();
        baseCrypto.decryptPacket(this);
    }

    public String toString() {
        return "ID: " + MessageMap.getMessageType(mMessageID) +
                "\nLength: " + mPayloadLength +
                "\nVersion: " + mVersion +
                "\nDecrypted: " + Utils.toHexString(mDecrypted);
    }
}
