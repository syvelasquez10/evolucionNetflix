// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleOutputMode;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;

public final class SubtitleManagerFactory
{
    static SubtitleManager createInstance(final SubtitleConfiguration subtitleConfiguration, final PlayerActivity playerActivity) {
        if (playerActivity == null) {
            throw new IllegalArgumentException("Player activity is null!");
        }
        if (subtitleConfiguration == null) {
            throw new IllegalArgumentException("Subtitle profile can not be null!");
        }
        if (IMedia$SubtitleOutputMode.EVENTS.equals(subtitleConfiguration.getMode())) {
            return new SimpleSubtitleManager(playerActivity);
        }
        if (IMedia$SubtitleOutputMode.DATA_XML.equals(subtitleConfiguration.getMode())) {
            return new EnhancedSubtitleManager(playerActivity);
        }
        throw new IllegalArgumentException("Subtitle configuration is NOT supported " + subtitleConfiguration);
    }
}
