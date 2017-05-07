// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import android.content.Context;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public final class SubtitleManagerFactory
{
    public static SubtitleManager createInstance(final IMedia$SubtitleProfile media$SubtitleProfile, final PlayerActivity playerActivity) {
        if (playerActivity == null) {
            throw new IllegalArgumentException("Player activity is null!");
        }
        if (media$SubtitleProfile == null) {
            throw new IllegalArgumentException("Subtitle profile can not be null!");
        }
        if (IMedia$SubtitleProfile.IMAGE.equals(media$SubtitleProfile)) {
            return new ImageBasedSubtitleManager(playerActivity);
        }
        return new EnhancedSubtitleManager(playerActivity);
    }
    
    public static String getSubtitleManagerLabel(final SubtitleManager subtitleManager) {
        String string = "";
        final Context context = subtitleManager.getContext();
        if (subtitleManager instanceof EnhancedSubtitleManager) {
            string = context.getString(2131493287);
        }
        else if (subtitleManager instanceof ImageBasedSubtitleManager) {
            return context.getString(2131493288);
        }
        return string;
    }
}
