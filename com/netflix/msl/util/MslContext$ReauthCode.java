// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.MslConstants$ResponseCode;

public enum MslContext$ReauthCode
{
    ENTITYDATA_REAUTH(MslConstants$ResponseCode.ENTITYDATA_REAUTH), 
    ENTITY_REAUTH(MslConstants$ResponseCode.ENTITY_REAUTH);
    
    private final MslConstants$ResponseCode code;
    
    private MslContext$ReauthCode(final MslConstants$ResponseCode code) {
        this.code = code;
    }
    
    public static MslContext$ReauthCode valueOf(final MslConstants$ResponseCode mslConstants$ResponseCode) {
        final MslContext$ReauthCode[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final MslContext$ReauthCode mslContext$ReauthCode = values[i];
            if (mslContext$ReauthCode.code == mslConstants$ResponseCode) {
                return mslContext$ReauthCode;
            }
        }
        throw new IllegalArgumentException("Unknown reauthentication code value " + mslConstants$ResponseCode + ".");
    }
    
    public int intValue() {
        return this.code.intValue();
    }
}
