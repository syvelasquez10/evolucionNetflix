// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum VideoType
{
    CHARACTERS("characters"), 
    EPISODE("episodes"), 
    MOVIE("movies"), 
    SEASON("seasons"), 
    SERIES("series"), 
    SHOW("shows"), 
    SOCIAL_FRIEND("SocialFriend"), 
    SOCIAL_GROUP("SocialGroup"), 
    SOCIAL_POPULAR("socialPopular"), 
    UNAVAILABLE("unavailable"), 
    UNKNOWN("");
    
    private String value;
    
    private VideoType(final String value) {
        this.value = value;
    }
    
    public static VideoType create(final String s) {
        final VideoType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final VideoType videoType = values[i];
            if (videoType.value.equalsIgnoreCase(s)) {
                return videoType;
            }
        }
        return VideoType.UNKNOWN;
    }
    
    public static boolean isPresentationTrackingType(final VideoType videoType) {
        return VideoType.MOVIE == videoType || VideoType.SHOW == videoType || VideoType.SERIES == videoType || VideoType.SEASON == videoType || VideoType.EPISODE == videoType;
    }
    
    public String getValue() {
        return this.value;
    }
}
