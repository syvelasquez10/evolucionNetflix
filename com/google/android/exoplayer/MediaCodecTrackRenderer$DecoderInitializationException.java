// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodec$CodecException;
import com.google.android.exoplayer.util.Util;

public class MediaCodecTrackRenderer$DecoderInitializationException extends Exception
{
    public final String decoderName;
    public final String diagnosticInfo;
    public final String mimeType;
    public final boolean secureDecoderRequired;
    
    public MediaCodecTrackRenderer$DecoderInitializationException(final MediaFormat mediaFormat, final Throwable t, final boolean secureDecoderRequired, final int n) {
        super("Decoder init failed: [" + n + "], " + mediaFormat, t);
        this.mimeType = mediaFormat.mimeType;
        this.secureDecoderRequired = secureDecoderRequired;
        this.decoderName = null;
        this.diagnosticInfo = buildCustomDiagnosticInfo(n);
    }
    
    public MediaCodecTrackRenderer$DecoderInitializationException(final MediaFormat mediaFormat, final Throwable t, final boolean secureDecoderRequired, final String decoderName) {
        super("Decoder init failed: " + decoderName + ", " + mediaFormat, t);
        this.mimeType = mediaFormat.mimeType;
        this.secureDecoderRequired = secureDecoderRequired;
        this.decoderName = decoderName;
        String diagnosticInfoV21;
        if (Util.SDK_INT >= 21) {
            diagnosticInfoV21 = getDiagnosticInfoV21(t);
        }
        else {
            diagnosticInfoV21 = null;
        }
        this.diagnosticInfo = diagnosticInfoV21;
    }
    
    private static String buildCustomDiagnosticInfo(final int n) {
        String s;
        if (n < 0) {
            s = "neg_";
        }
        else {
            s = "";
        }
        return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + s + Math.abs(n);
    }
    
    @TargetApi(21)
    private static String getDiagnosticInfoV21(final Throwable t) {
        if (t instanceof MediaCodec$CodecException) {
            return ((MediaCodec$CodecException)t).getDiagnosticInfo();
        }
        return null;
    }
}
