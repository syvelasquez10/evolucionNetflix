// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;

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
        final PlayLocationType unknown = PlayLocationType.UNKNOWN;
        final PlayLocationType[] values = values();
        while (true) {
            for (int length = values.length, i = 0; i < length; ++i) {
                final PlayLocationType playLocationType = values[i];
                if (playLocationType.getValue().equals(s)) {
                    final PlayLocationType playLocationType2 = playLocationType;
                    if (playLocationType2 == PlayLocationType.UNKNOWN) {
                        ErrorLoggingManager.logHandledException("PlayLocationType UNKNOWN.");
                    }
                    return playLocationType2;
                }
            }
            final PlayLocationType playLocationType2 = unknown;
            continue;
        }
    }
    
    public String getValue() {
        return this.value;
    }
}
