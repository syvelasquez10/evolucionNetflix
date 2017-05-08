// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.MslConstants$ResponseCode;

public enum MessageContext$ReauthCode
{
    SSOTOKEN_REJECTED(MslConstants$ResponseCode.SSOTOKEN_REJECTED), 
    USERDATA_REAUTH(MslConstants$ResponseCode.USERDATA_REAUTH);
    
    private final MslConstants$ResponseCode code;
    
    private MessageContext$ReauthCode(final MslConstants$ResponseCode code) {
        this.code = code;
    }
    
    public static MessageContext$ReauthCode valueOf(final MslConstants$ResponseCode mslConstants$ResponseCode) {
        final MessageContext$ReauthCode[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final MessageContext$ReauthCode messageContext$ReauthCode = values[i];
            if (messageContext$ReauthCode.code == mslConstants$ResponseCode) {
                return messageContext$ReauthCode;
            }
        }
        throw new IllegalArgumentException("Unknown reauthentication code value " + mslConstants$ResponseCode + ".");
    }
    
    public int intValue() {
        return this.code.intValue();
    }
}
