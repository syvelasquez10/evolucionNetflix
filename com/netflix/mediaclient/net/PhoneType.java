// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

public enum PhoneType
{
    CDMA(2, "CDMA"), 
    GSM(1, "GSM"), 
    NONE(0, "NONE"), 
    UNKNOWN(Integer.MIN_VALUE, "UNKNOWN");
    
    private String desc;
    private int type;
    
    private PhoneType(final int type, final String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public static PhoneType getPhoneType(final int n) {
        for (int i = 0; i < values().length; ++i) {
            if (values()[i].type == n) {
                return values()[i];
            }
        }
        return PhoneType.UNKNOWN;
    }
    
    public final String getDesc() {
        return this.desc;
    }
    
    public final int getType() {
        return this.type;
    }
}
