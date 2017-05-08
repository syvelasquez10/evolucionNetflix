// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public enum VideoType
{
    CHARACTERS("characters", 4), 
    EPISODE("episodes", 3), 
    MOVIE("movies", 0), 
    SEASON("seasons", 2), 
    SERIES("series", 5), 
    SHOW("shows", 1), 
    UNAVAILABLE("unavailable", 6), 
    UNKNOWN("", 7);
    
    private final int key;
    private final String value;
    
    private VideoType(final String value, final int key) {
        this.value = value;
        this.key = key;
    }
    
    public static VideoType create(final int n) {
        final VideoType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final VideoType videoType = values[i];
            if (videoType.key == n) {
                return videoType;
            }
        }
        return VideoType.UNKNOWN;
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
    
    public int getKey() {
        return this.key;
    }
    
    public String getValue() {
        return this.value;
    }
}
