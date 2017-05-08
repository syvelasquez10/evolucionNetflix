// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public enum LoMoType
{
    BILLBOARD("BillboardList"), 
    CHARACTERS("character"), 
    CONTINUE_WATCHING("continueWatching"), 
    FLAT_GENRE("defaultKidsList"), 
    INSTANT_QUEUE("queue"), 
    POPULAR_TITLES("popularTitles"), 
    SOCIAL_FRIEND("SocialFriend"), 
    SOCIAL_GROUP("SocialGroup"), 
    SOCIAL_POPULAR("socialPopular"), 
    STANDARD(""), 
    TOP_TEN("topTen");
    
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
    
    public static boolean isRegularLomoForPreApp(final LoMoType loMoType) {
        return LoMoType.STANDARD.equals(loMoType) || LoMoType.FLAT_GENRE.equals(loMoType) || LoMoType.POPULAR_TITLES.equals(loMoType) || LoMoType.TOP_TEN.equals(loMoType);
    }
}
