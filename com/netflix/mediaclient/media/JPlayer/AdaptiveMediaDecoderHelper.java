// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
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
    private static final int HD720_MAX_BITRATE = 3000;
    static final int SD_HEIGHT = 480;
    private static final int SD_MAX_BITRATE = 1750;
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
    
    static Pair<Integer, Integer> getRequiredMaximumResolution(final int n, final boolean b) {
        if (!b) {
            return (Pair<Integer, Integer>)Pair.create((Object)720, (Object)480);
        }
        if (n <= 3000 && n > 1750) {
            return (Pair<Integer, Integer>)Pair.create((Object)1280, (Object)720);
        }
        if (n <= 1750 && n > 0) {
            return (Pair<Integer, Integer>)Pair.create((Object)720, (Object)480);
        }
        return (Pair<Integer, Integer>)Pair.create((Object)1920, (Object)1080);
    }
    
    public static boolean isAvcDecoderSupportsAdaptivePlayback() {
        return getAdaptivePlaybackDecoderName("video/avc") != null;
    }
}
