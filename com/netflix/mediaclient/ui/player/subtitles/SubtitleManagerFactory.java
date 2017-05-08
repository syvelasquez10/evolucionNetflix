// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public final class SubtitleManagerFactory
{
    public static SubtitleManager createInstance(final IMedia$SubtitleProfile media$SubtitleProfile, final PlayerFragment playerFragment) {
        if (playerFragment == null) {
            throw new IllegalArgumentException("Player fragment is null!");
        }
        if (playerFragment.getActivity() == null) {
            throw new IllegalArgumentException("Player fragment's activity is null!");
        }
        if (media$SubtitleProfile == null) {
            throw new IllegalArgumentException("Subtitle profile can not be null!");
        }
        if (IMedia$SubtitleProfile.IMAGE.equals(media$SubtitleProfile) || IMedia$SubtitleProfile.IMAGE_ENC.equals(media$SubtitleProfile)) {
            return new ImageBasedSubtitleManager(playerFragment);
        }
        return new EnhancedSubtitleManager(playerFragment);
    }
    
    public static String getSubtitleManagerLabel(final SubtitleManager subtitleManager) {
        String string = "";
        final NetflixActivity context = subtitleManager.getContext();
        if (subtitleManager instanceof EnhancedSubtitleManager) {
            string = ((Context)context).getString(2131165807);
        }
        else if (subtitleManager instanceof ImageBasedSubtitleManager) {
            return ((Context)context).getString(2131165806);
        }
        return string;
    }
}
