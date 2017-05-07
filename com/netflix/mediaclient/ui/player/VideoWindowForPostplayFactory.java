// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;

public final class VideoWindowForPostplayFactory
{
    protected static String TAG;
    
    static {
        VideoWindowForPostplayFactory.TAG = "nf_postplay";
    }
    
    static VideoWindowForPostplay createVideoWindow(final PlayerFragment playerFragment) {
        if (AndroidUtils.getAndroidVersion() < 17) {
            Log.d(VideoWindowForPostplayFactory.TAG, "Use simple scaling");
            return new VideoWindowForPostplayFullScreen(playerFragment);
        }
        Log.d(VideoWindowForPostplayFactory.TAG, "Use animation");
        return new VideoWindowForPostplayWithAnimation(playerFragment);
    }
}
