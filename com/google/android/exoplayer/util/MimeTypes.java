// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

public final class MimeTypes
{
    public static String getAudioMediaMimeType(final String s) {
        if (s == null) {
            return "audio/x-unknown";
        }
        final String[] split = s.split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String trim = split[i].trim();
            if (trim.startsWith("mp4a")) {
                return "audio/mp4a-latm";
            }
            if (trim.startsWith("ac-3") || trim.startsWith("dac3")) {
                return "audio/ac3";
            }
            if (trim.startsWith("ec-3") || trim.startsWith("dec3")) {
                return "audio/eac3";
            }
            if (trim.startsWith("dtsc")) {
                return "audio/vnd.dts";
            }
            if (trim.startsWith("dtsh") || trim.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (trim.startsWith("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (trim.startsWith("opus")) {
                return "audio/opus";
            }
            if (trim.startsWith("vorbis")) {
                return "audio/vorbis";
            }
        }
        return "audio/x-unknown";
    }
    
    private static String getTopLevelType(final String s) {
        final int index = s.indexOf(47);
        if (index == -1) {
            throw new IllegalArgumentException("Invalid mime type: " + s);
        }
        return s.substring(0, index);
    }
    
    public static String getVideoMediaMimeType(final String s) {
        if (s == null) {
            return "video/x-unknown";
        }
        final String[] split = s.split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String trim = split[i].trim();
            if (trim.startsWith("avc1") || trim.startsWith("avc3")) {
                return "video/avc";
            }
            if (trim.startsWith("hev1") || trim.startsWith("hvc1")) {
                return "video/hevc";
            }
            if (trim.startsWith("vp9")) {
                return "video/x-vnd.on2.vp9";
            }
            if (trim.startsWith("vp8")) {
                return "video/x-vnd.on2.vp8";
            }
        }
        return "video/x-unknown";
    }
    
    public static boolean isAudio(final String s) {
        return getTopLevelType(s).equals("audio");
    }
    
    public static boolean isText(final String s) {
        return getTopLevelType(s).equals("text");
    }
    
    public static boolean isVideo(final String s) {
        return getTopLevelType(s).equals("video");
    }
}
