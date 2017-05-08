// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodecInfo$CodecProfileLevel;
import com.google.android.exoplayer.util.Assertions;
import android.media.MediaCodecInfo$VideoCapabilities;
import android.media.MediaCodecInfo;
import java.util.ArrayList;
import java.util.Collections;
import android.util.Log;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import android.media.MediaCodecInfo$CodecCapabilities;
import java.util.List;
import java.util.Map;
import android.annotation.TargetApi;

@TargetApi(16)
public final class MediaCodecUtil
{
    private static final DecoderInfo PASSTHROUGH_DECODER_INFO;
    private static final Map<MediaCodecUtil$CodecKey, List<DecoderInfo>> decoderInfosCache;
    private static int maxH264DecodableFrameSize;
    
    static {
        PASSTHROUGH_DECODER_INFO = new DecoderInfo("OMX.google.raw.decoder", null);
        decoderInfosCache = new HashMap<MediaCodecUtil$CodecKey, List<DecoderInfo>>();
        MediaCodecUtil.maxH264DecodableFrameSize = -1;
    }
    
    private static int avcLevelToMaxFrameSize(final int n) {
        int n2 = 25344;
        switch (n) {
            default: {
                n2 = -1;
                return n2;
            }
            case 1:
            case 2: {
                return n2;
            }
            case 8: {
                return 101376;
            }
            case 16: {
                return 101376;
            }
            case 32: {
                return 101376;
            }
            case 64: {
                return 202752;
            }
            case 128: {
                return 414720;
            }
            case 256: {
                return 414720;
            }
            case 512: {
                return 921600;
            }
            case 1024: {
                return 1310720;
            }
            case 2048: {
                return 2097152;
            }
            case 4096: {
                return 2097152;
            }
            case 8192: {
                return 2228224;
            }
            case 16384: {
                return 5652480;
            }
            case 32768: {
                return 9437184;
            }
        }
    }
    
    public static DecoderInfo getDecoderInfo(final String s, final boolean b) {
        final List<DecoderInfo> decoderInfos = getDecoderInfos(s, b);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }
    
    public static List<DecoderInfo> getDecoderInfos(final String s, final boolean b) {
    Label_0172_Outer:
        while (true) {
            while (true) {
            Label_0210:
                while (true) {
                    Label_0198: {
                        synchronized (MediaCodecUtil.class) {
                            final MediaCodecUtil$CodecKey mediaCodecUtil$CodecKey = new MediaCodecUtil$CodecKey(s, b);
                            Object o = MediaCodecUtil.decoderInfosCache.get(mediaCodecUtil$CodecKey);
                            Object unmodifiableList;
                            if (o != null) {
                                unmodifiableList = o;
                            }
                            else {
                                if (Util.SDK_INT < 21) {
                                    break Label_0198;
                                }
                                o = new MediaCodecUtil$MediaCodecListCompatV21(b);
                                final List<DecoderInfo> list = (List<DecoderInfo>)(o = getDecoderInfosInternal(mediaCodecUtil$CodecKey, (MediaCodecUtil$MediaCodecListCompat)o));
                                if (b) {
                                    o = list;
                                    if (list.isEmpty()) {
                                        o = list;
                                        if (21 <= Util.SDK_INT) {
                                            o = list;
                                            if (Util.SDK_INT <= 23) {
                                                o = getDecoderInfosInternal(mediaCodecUtil$CodecKey, new MediaCodecUtil$MediaCodecListCompatV16(null));
                                                if (!((List)o).isEmpty()) {
                                                    Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + s + ". Assuming: " + ((List<DecoderInfo>)o).get(0).name);
                                                    break Label_0210;
                                                }
                                                break Label_0210;
                                            }
                                        }
                                    }
                                }
                                unmodifiableList = Collections.unmodifiableList((List<?>)o);
                                MediaCodecUtil.decoderInfosCache.put(mediaCodecUtil$CodecKey, (List<DecoderInfo>)unmodifiableList);
                            }
                            return (List<DecoderInfo>)unmodifiableList;
                        }
                    }
                    Object o = new MediaCodecUtil$MediaCodecListCompatV16(null);
                    continue Label_0172_Outer;
                }
                continue;
            }
        }
    }
    
    private static List<DecoderInfo> getDecoderInfosInternal(final MediaCodecUtil$CodecKey mediaCodecUtil$CodecKey, final MediaCodecUtil$MediaCodecListCompat mediaCodecUtil$MediaCodecListCompat) {
        String name = null;
        String s = null;
    Label_0081_Outer:
        while (true) {
            while (true) {
                int n2 = 0;
            Label_0342:
                while (true) {
                    int n = 0;
                    Label_0335: {
                        try {
                            final ArrayList<DecoderInfo> list = new ArrayList<DecoderInfo>();
                            final String mimeType = mediaCodecUtil$CodecKey.mimeType;
                            final int codecCount = mediaCodecUtil$MediaCodecListCompat.getCodecCount();
                            final boolean secureDecodersExplicit = mediaCodecUtil$MediaCodecListCompat.secureDecodersExplicit();
                            n = 0;
                            if (n >= codecCount) {
                                return list;
                            }
                            final MediaCodecInfo codecInfo = mediaCodecUtil$MediaCodecListCompat.getCodecInfoAt(n);
                            name = codecInfo.getName();
                            if (!isCodecUsableDecoder(codecInfo, name, secureDecodersExplicit)) {
                                break Label_0335;
                            }
                            final String[] supportedTypes = codecInfo.getSupportedTypes();
                            final int length = supportedTypes.length;
                            n2 = 0;
                            if (n2 >= length) {
                                break Label_0335;
                            }
                            s = supportedTypes[n2];
                            if (!s.equalsIgnoreCase(mimeType)) {
                                break Label_0342;
                            }
                            try {
                                final MediaCodecInfo$CodecCapabilities capabilitiesForType = codecInfo.getCapabilitiesForType(s);
                                final boolean securePlaybackSupported = mediaCodecUtil$MediaCodecListCompat.isSecurePlaybackSupported(mimeType, capabilitiesForType);
                                if ((secureDecodersExplicit && mediaCodecUtil$CodecKey.secure == securePlaybackSupported) || (!secureDecodersExplicit && !mediaCodecUtil$CodecKey.secure)) {
                                    list.add(new DecoderInfo(name, capabilitiesForType));
                                    break Label_0342;
                                }
                                if (!secureDecodersExplicit && securePlaybackSupported) {
                                    list.add(new DecoderInfo(name + ".secure", capabilitiesForType));
                                    return list;
                                }
                                break Label_0342;
                            }
                            catch (Exception ex2) {
                                if (Util.SDK_INT <= 23 && !list.isEmpty()) {
                                    Log.e("MediaCodecUtil", "Skipping codec " + name + " (failed to query capabilities)");
                                    break Label_0342;
                                }
                                break;
                            }
                        }
                        catch (Exception ex) {
                            throw new MediaCodecUtil$DecoderQueryException(ex, null);
                        }
                        break;
                    }
                    ++n;
                    continue Label_0081_Outer;
                }
                ++n2;
                continue;
            }
        }
        Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + s + ")");
        throw;
    }
    
    public static DecoderInfo getPassthroughDecoderInfo() {
        return MediaCodecUtil.PASSTHROUGH_DECODER_INFO;
    }
    
    @TargetApi(21)
    private static MediaCodecInfo$VideoCapabilities getVideoCapabilitiesV21(final String s, final boolean b) {
        final DecoderInfo decoderInfo = getDecoderInfo(s, b);
        if (decoderInfo == null) {
            return null;
        }
        return decoderInfo.capabilities.getVideoCapabilities();
    }
    
    private static boolean isCodecUsableDecoder(final MediaCodecInfo mediaCodecInfo, final String s, final boolean b) {
        return !mediaCodecInfo.isEncoder() && (b || !s.endsWith(".secure")) && (Util.SDK_INT >= 21 || (!"CIPAACDecoder".equals(s) && !"CIPMP3Decoder".equals(s) && !"CIPVorbisDecoder".equals(s) && !"AACDecoder".equals(s) && !"MP3Decoder".equals(s))) && (Util.SDK_INT >= 18 || !"OMX.SEC.MP3.Decoder".equals(s)) && (Util.SDK_INT >= 18 || !"OMX.MTK.AUDIO.DECODER.AAC".equals(s) || !"a70".equals(Util.DEVICE)) && (Util.SDK_INT != 16 || Util.DEVICE == null || !"OMX.qcom.audio.decoder.mp3".equals(s) || (!"dlxu".equals(Util.DEVICE) && !"protou".equals(Util.DEVICE) && !"ville".equals(Util.DEVICE) && !"villeplus".equals(Util.DEVICE) && !"villec2".equals(Util.DEVICE) && !Util.DEVICE.startsWith("gee") && !"C6602".equals(Util.DEVICE) && !"C6603".equals(Util.DEVICE) && !"C6606".equals(Util.DEVICE) && !"C6616".equals(Util.DEVICE) && !"L36h".equals(Util.DEVICE) && !"SO-02E".equals(Util.DEVICE))) && (Util.SDK_INT != 16 || !"OMX.qcom.audio.decoder.aac".equals(s) || (!"C1504".equals(Util.DEVICE) && !"C1505".equals(Util.DEVICE) && !"C1604".equals(Util.DEVICE) && !"C1605".equals(Util.DEVICE))) && (Util.SDK_INT > 19 || Util.DEVICE == null || (!Util.DEVICE.startsWith("d2") && !Util.DEVICE.startsWith("serrano") && !Util.DEVICE.startsWith("jflte") && !Util.DEVICE.startsWith("santos")) || !"samsung".equals(Util.MANUFACTURER) || !s.equals("OMX.SEC.vp8.dec")) && (Util.SDK_INT > 19 || Util.DEVICE == null || !Util.DEVICE.startsWith("jflte") || !"OMX.qcom.video.decoder.vp8".equals(s));
    }
    
    @TargetApi(21)
    public static boolean isSizeAndRateSupportedV21(final String s, final boolean b, final int n, final int n2, final double n3) {
        Assertions.checkState(Util.SDK_INT >= 21);
        final MediaCodecInfo$VideoCapabilities videoCapabilitiesV21 = getVideoCapabilitiesV21(s, b);
        return videoCapabilitiesV21 != null && videoCapabilitiesV21.areSizeAndRateSupported(n, n2, n3);
    }
    
    @TargetApi(21)
    public static boolean isSizeSupportedV21(final String s, final boolean b, final int n, final int n2) {
        Assertions.checkState(Util.SDK_INT >= 21);
        final MediaCodecInfo$VideoCapabilities videoCapabilitiesV21 = getVideoCapabilitiesV21(s, b);
        return videoCapabilitiesV21 != null && videoCapabilitiesV21.isSizeSupported(n, n2);
    }
    
    public static int maxH264DecodableFrameSize() {
        int max = 0;
        final int n = 0;
        if (MediaCodecUtil.maxH264DecodableFrameSize == -1) {
            final DecoderInfo decoderInfo = getDecoderInfo("video/avc", false);
            if (decoderInfo != null) {
                final MediaCodecInfo$CodecProfileLevel[] profileLevels = decoderInfo.capabilities.profileLevels;
                final int length = profileLevels.length;
                int max2 = 0;
                for (int i = n; i < length; ++i) {
                    max2 = Math.max(avcLevelToMaxFrameSize(profileLevels[i].level), max2);
                }
                max = Math.max(max2, 172800);
            }
            MediaCodecUtil.maxH264DecodableFrameSize = max;
        }
        return MediaCodecUtil.maxH264DecodableFrameSize;
    }
}
