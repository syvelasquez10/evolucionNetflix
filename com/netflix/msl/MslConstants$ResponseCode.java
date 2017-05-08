// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public enum MslConstants$ResponseCode
{
    ENTITYDATA_REAUTH(6), 
    ENTITY_REAUTH(3), 
    EXPIRED(8), 
    FAIL(1), 
    KEYX_REQUIRED(5), 
    REPLAYED(9), 
    SSOTOKEN_REJECTED(10), 
    TRANSIENT_FAILURE(2), 
    USERDATA_REAUTH(7), 
    USER_REAUTH(4);
    
    private final int code;
    
    private MslConstants$ResponseCode(final int code) {
        this.code = code;
    }
    
    public static MslConstants$ResponseCode valueOf(final int n) {
        final MslConstants$ResponseCode[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final MslConstants$ResponseCode mslConstants$ResponseCode = values[i];
            if (mslConstants$ResponseCode.intValue() == n) {
                return mslConstants$ResponseCode;
            }
        }
        throw new IllegalArgumentException("Unknown response code value " + n + ".");
    }
    
    public int intValue() {
        return this.code;
    }
}
