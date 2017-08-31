package com.scdevteam.messages;

public class Message {
    private final boolean mOutgoing;

    protected int mMessageID;
    protected int mPayloadLength;
    protected int mVersion;
    protected byte[] mEncrypted;
    protected byte[] mDecrypted;

    public Message(boolean outgoing) {
        mOutgoing = outgoing;
    }

    public boolean isOutgoing() {
        return mOutgoing;
    }

    public int getMessageID() {
        return mMessageID;
    }

    public int getPayloadLength() {
        return mPayloadLength;
    }

    public int getVersion() {
        return mVersion;
    }

    public byte[] getEncryptedPayload() {
        return mEncrypted;
    }

    public byte[] getDecryptedPayload() {
        return mDecrypted;
    }

    public void setEncryptedPayload(byte[] encrypted) {
        mEncrypted = encrypted;
    }

    public void setDecryptedPayload(byte[] decrypted) {
        mDecrypted = decrypted;
    }
}
