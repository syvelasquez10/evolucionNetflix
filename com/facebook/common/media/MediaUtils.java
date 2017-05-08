// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import java.util.Locale;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;

public class MediaUtils
{
    public static final Map<String, String> ADDITIONAL_ALLOWED_MIME_TYPES;
    
    static {
        ADDITIONAL_ALLOWED_MIME_TYPES = ImmutableMap.of("mkv", "video/x-matroska");
    }
    
    private static String extractExtension(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex < 0 || lastIndex == s.length() - 1) {
            return null;
        }
        return s.substring(lastIndex + 1);
    }
    
    public static String extractMime(String s) {
        s = extractExtension(s);
        if (s == null) {
            s = null;
        }
        else {
            final String lowerCase = s.toLowerCase(Locale.US);
            if ((s = MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase)) == null) {
                return MediaUtils.ADDITIONAL_ALLOWED_MIME_TYPES.get(lowerCase);
            }
        }
        return s;
    }
    
    public static boolean isVideo(final String s) {
        return s != null && s.startsWith("video/");
    }
}
