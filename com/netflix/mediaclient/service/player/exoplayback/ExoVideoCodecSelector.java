// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.google.android.exoplayer.MediaCodecUtil$DecoderQueryException;
import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.DecoderInfo;
import com.google.android.exoplayer.MediaCodecSelector;

public class ExoVideoCodecSelector implements MediaCodecSelector
{
    private static final String TAG;
    private static final boolean hasHEVCHardwareDecoder;
    private static final boolean hasSecureHEVCDecoder;
    private static final boolean hasSecureVP9Decoder;
    private static final boolean hasVP9HardwareDecoder;
    private static final DecoderInfo sHEVCSoftwareDecoder;
    private static final DecoderInfo sVP9SoftwareDecoder;
    private static final boolean supportVP9;
    private static boolean useSoftwareDecoder;
    
    static {
        TAG = ExoVideoCodecSelector.class.getSimpleName();
        ExoVideoCodecSelector.useSoftwareDecoder = false;
        supportVP9 = isVideoFormatSupported("video/x-vnd.on2.vp9");
        hasSecureVP9Decoder = hasSecureDecoder("video/x-vnd.on2.vp9");
        hasSecureHEVCDecoder = hasSecureDecoder("video/hevc");
        sVP9SoftwareDecoder = getSoftwareDecoder("video/x-vnd.on2.vp9");
        sHEVCSoftwareDecoder = getSoftwareDecoder("video/hevc");
        hasVP9HardwareDecoder = hasHardwareDecoder("video/x-vnd.on2.vp9", ExoVideoCodecSelector.sVP9SoftwareDecoder);
        hasHEVCHardwareDecoder = hasHardwareDecoder("video/hevc", ExoVideoCodecSelector.sHEVCSoftwareDecoder);
    }
    
    private static DecoderInfo getSoftwareDecoder(final String s) {
        List<DecoderInfo> decoderInfos = null;
        Label_0109: {
            try {
                decoderInfos = MediaCodecUtil.getDecoderInfos(s, false);
                if (decoderInfos != null) {
                    if (Log.isLoggable()) {
                        Log.d(ExoVideoCodecSelector.TAG, "list all decoder for " + s);
                        final Iterator<DecoderInfo> iterator = decoderInfos.iterator();
                        while (iterator.hasNext()) {
                            Log.d(ExoVideoCodecSelector.TAG, "=> " + iterator.next().name);
                        }
                    }
                    break Label_0109;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }
        for (final DecoderInfo decoderInfo2 : decoderInfos) {
            if (decoderInfo2.name.toLowerCase().contains(".google.")) {
                final DecoderInfo decoderInfo = decoderInfo2;
                if (Log.isLoggable()) {
                    Log.d(ExoVideoCodecSelector.TAG, "has a sotfware decoder " + decoderInfo2.name);
                    return decoderInfo2;
                }
                return decoderInfo;
            }
        }
        return null;
    }
    
    private static boolean hasHardwareDecoder(final String s, final DecoderInfo decoderInfo) {
        final boolean b = false;
        try {
            final DecoderInfo decoderInfo2 = MediaCodecUtil.getDecoderInfo(s, false);
            boolean b2 = b;
            if (decoderInfo2 != null) {
                if (decoderInfo != null) {
                    final boolean equals = decoderInfo2.name.equals(decoderInfo.name);
                    b2 = b;
                    if (equals) {
                        return b2;
                    }
                }
                b2 = true;
            }
            return b2;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
    
    private static boolean hasSecureDecoder(final String s) {
        try {
            final DecoderInfo decoderInfo = MediaCodecUtil.getDecoderInfo(s, true);
            if (decoderInfo == null) {
                if (Log.isLoggable()) {
                    Log.d(ExoVideoCodecSelector.TAG, "has no secured decoder for " + s);
                }
                return false;
            }
            if (Log.isLoggable()) {
                Log.d(ExoVideoCodecSelector.TAG, "has a secured decoder " + decoderInfo.name + " for " + s);
                return true;
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean isHasHEVCHardwareDecoder() {
        return ExoVideoCodecSelector.hasHEVCHardwareDecoder;
    }
    
    public static boolean isHasHEVCSoftwareDecoder() {
        return ExoVideoCodecSelector.sHEVCSoftwareDecoder != null;
    }
    
    public static boolean isHasSecureHEVCDecoder() {
        return ExoVideoCodecSelector.hasSecureHEVCDecoder;
    }
    
    public static boolean isHasSecureVP9Decoder() {
        return ExoVideoCodecSelector.hasSecureVP9Decoder;
    }
    
    public static boolean isHasVP9HardwareDecoder() {
        return ExoVideoCodecSelector.hasVP9HardwareDecoder;
    }
    
    public static boolean isHasVP9SoftwareDecoder() {
        return ExoVideoCodecSelector.sVP9SoftwareDecoder != null;
    }
    
    public static boolean isSupportVP9() {
        return ExoVideoCodecSelector.supportVP9;
    }
    
    public static boolean isUseSoftwareDecoder() {
        return ExoVideoCodecSelector.useSoftwareDecoder;
    }
    
    private static boolean isVideoFormatSupported(final String s) {
        boolean b = false;
        try {
            final DecoderInfo decoderInfo = MediaCodecUtil.getDecoderInfo(s, false);
            if (decoderInfo != null) {
                if (Log.isLoggable()) {
                    Log.d(ExoVideoCodecSelector.TAG, s + "is supported, with decoder " + decoderInfo.name);
                }
                b = true;
            }
            return b;
        }
        catch (MediaCodecUtil$DecoderQueryException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static void setUseSoftwareDecoder(final boolean useSoftwareDecoder) {
        ExoVideoCodecSelector.useSoftwareDecoder = useSoftwareDecoder;
    }
    
    @Override
    public DecoderInfo getDecoderInfo(final String s, boolean b) {
        final boolean b2 = true;
        if ("video/x-vnd.on2.vp9".equals(s)) {
            if (ExoVideoCodecSelector.useSoftwareDecoder) {
                return ExoVideoCodecSelector.sVP9SoftwareDecoder;
            }
            if (b && ExoVideoCodecSelector.hasSecureVP9Decoder) {
                b = b2;
            }
            else {
                b = false;
            }
        }
        else if ("video/hevc".equals(s)) {
            if (ExoVideoCodecSelector.useSoftwareDecoder) {
                return ExoVideoCodecSelector.sHEVCSoftwareDecoder;
            }
            if (b) {
                b = b2;
                if (ExoVideoCodecSelector.hasSecureHEVCDecoder) {
                    return MediaCodecUtil.getDecoderInfo(s, b);
                }
            }
            b = false;
        }
        return MediaCodecUtil.getDecoderInfo(s, b);
    }
    
    @Override
    public DecoderInfo getPassthroughDecoderInfo() {
        return MediaCodecUtil.getPassthroughDecoderInfo();
    }
}
