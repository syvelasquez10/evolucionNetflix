// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCodecInfo;
import java.util.Arrays;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.media.MediaCodecList;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaFormat;

public final class DolbyDigitalHelper
{
    private static final String TAG;
    
    static {
        TAG = DolbyDigitalHelper.class.getSimpleName();
    }
    
    public static MediaFormat getMediaFormatEAC3() {
        return MediaFormat.createAudioFormat("audio/eac3", 48000, 6);
    }
    
    public static boolean isEAC3supported() {
        if (AndroidUtils.getAndroidVersion() < 21) {
            return isEAC3supportedL();
        }
        final MediaCodecList list = new MediaCodecList(1);
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/eac3");
        final String decoderForFormat = list.findDecoderForFormat(mediaFormat);
        if (Log.isLoggable()) {
            Log.d(DolbyDigitalHelper.TAG, "device has DD+ decoder " + decoderForFormat);
        }
        return StringUtils.isNotEmpty(decoderForFormat);
    }
    
    private static boolean isEAC3supportedL() {
        final boolean b = false;
        final int codecCount = MediaCodecList.getCodecCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= codecCount) {
                break;
            }
            final MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(n);
            if (!codecInfo.isEncoder() && Arrays.asList(codecInfo.getSupportedTypes()).indexOf("audio/eac3") >= 0) {
                if (Log.isLoggable()) {
                    Log.d(DolbyDigitalHelper.TAG, "L or Lower device has DD+ decoder " + codecInfo.getName());
                }
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
}
