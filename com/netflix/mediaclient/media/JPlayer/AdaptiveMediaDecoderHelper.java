// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCodecInfo;
import com.netflix.mediaclient.Log;
import java.util.Arrays;
import android.media.MediaCodecList;

public class AdaptiveMediaDecoderHelper
{
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
    
    public static boolean isAvcDecoderSupportsAdaptivePlayback() {
        return getAdaptivePlaybackDecoderName("video/avc") != null;
    }
}
