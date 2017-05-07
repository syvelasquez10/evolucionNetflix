// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum LoMoType
{
    BILLBOARD("BillboardList"), 
    CONTINUE_WATCHING("continueWatching"), 
    INSTANT_QUEUE("queue"), 
    SOCIAL_FRIEND("SocialFriend"), 
    SOCIAL_GROUP("SocialGroup"), 
    SOCIAL_POPULAR("socialPopular"), 
    STANDARD("");
    
    private String value;
    
    private LoMoType(final String value) {
        this.value = value;
    }
    
    public static LoMoType create(final String s) {
        final LoMoType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final LoMoType loMoType = values[i];
            if (loMoType.value.equalsIgnoreCase(s)) {
                return loMoType;
            }
        }
        return LoMoType.STANDARD;
    }
    
    public String getValue() {
        return this.value;
    }
}
