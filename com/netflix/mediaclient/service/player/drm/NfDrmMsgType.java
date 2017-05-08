// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

public enum NfDrmMsgType
{
    MSG_ERROR(0), 
    MSG_KEYS(2), 
    MSG_KEYS_LDL(3), 
    MSG_KEYS_OFFLINE(4), 
    MSG_PROVISION(1), 
    UNKNOWN(-1);
    
    private final int mValue;
    
    private NfDrmMsgType(final int mValue) {
        this.mValue = mValue;
    }
    
    public static NfDrmMsgType getMsgTypeFromValue(final int n) {
        final NfDrmMsgType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final NfDrmMsgType nfDrmMsgType = values[i];
            if (nfDrmMsgType.getValue() == n) {
                return nfDrmMsgType;
            }
        }
        return NfDrmMsgType.UNKNOWN;
    }
    
    public int getValue() {
        return this.mValue;
    }
}
