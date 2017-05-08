// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

public enum PlayLocationType
{
    DIRECT_PLAY("directPlay"), 
    EPISODE("episode"), 
    STORY_ART("storyArt"), 
    UNKNOWN("");
    
    private final String value;
    
    private PlayLocationType(final String value) {
        this.value = value;
    }
    
    public static PlayLocationType create(final String s) {
        final PlayLocationType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PlayLocationType playLocationType = values[i];
            if (playLocationType.getValue().equals(s)) {
                return playLocationType;
            }
        }
        return PlayLocationType.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
