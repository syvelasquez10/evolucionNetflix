// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.media.VideoResolutionRange;
import android.media.MediaCodecInfo;
import com.netflix.mediaclient.Log;
import java.util.Arrays;
import android.media.MediaCodecList;

public class AdaptiveMediaDecoderHelper
{
    static final int HD1080P_HEIGHT = 1080;
    static final int HD1080P_WIDTH = 1920;
    static final int HD720P_HEIGHT = 720;
    static final int HD720P_WIDTH = 1280;
    static final int SD_HEIGHT = 480;
    static final int SD_WIDTH = 720;
    protected static final String TAG = "AdaptiveMediaDecoderHelper";
    
    public static String getAdaptivePlaybackDecoderName(final String s) {
        for (int codecCount = MediaCodecList.getCodecCount(), i = 0; i < codecCount; ++i) {
            final MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfo.isEncoder() && Arrays.asList(codecInfo.getSupportedTypes()).indexOf(s) >= 0 && codecInfo.getCapabilitiesForType(s).isFeatureSupported("adaptive-playback")) {
                if (Log.isLoggable("AdaptiveMediaDecoderHelper", 3)) {
                    Log.d("AdaptiveMediaDecoderHelper", codecInfo.getName() + " supports AdaptivePlayback");
                }
                return codecInfo.getName();
            }
        }
        if (Log.isLoggable("AdaptiveMediaDecoderHelper", 3)) {
            Log.d("AdaptiveMediaDecoderHelper", "AdaptivePlayback is not supported");
        }
        return null;
    }
    
    static Pair<Integer, Integer> getRequiredMaximumResolution(final VideoResolutionRange videoResolutionRange, final boolean b) {
        if (!b || videoResolutionRange == null) {
            return (Pair<Integer, Integer>)Pair.create((Object)720, (Object)480);
        }
        return (Pair<Integer, Integer>)Pair.create((Object)videoResolutionRange.getMaxHeight(), (Object)videoResolutionRange.getMaxWidth());
    }
    
    public static boolean isAvcDecoderSupportsAdaptivePlayback() {
        return getAdaptivePlaybackDecoderName("video/avc") != null;
    }
}
