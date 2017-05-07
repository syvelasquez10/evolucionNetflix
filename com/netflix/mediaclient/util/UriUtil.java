// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.UnsupportedEncodingException;
import com.netflix.mediaclient.Log;
import java.net.URLEncoder;
import android.net.Uri;

public final class UriUtil
{
    private static final String TAG = "UriUtils";
    
    public static String buildUrlParam(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder("&");
        sb.append(s);
        sb.append("=");
        sb.append(s2);
        return sb.toString();
    }
    
    public static String getParamFromUri(final String s, final String s2) {
        if (!StringUtils.isEmpty(s) && !StringUtils.isEmpty(s2)) {
            final String[] split = s2.split("[&]");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String s3 = split[i];
                final int index = s3.indexOf("=");
                if (index > 0 && s.equals(s3.substring(0, index))) {
                    return s3.substring(index + 1);
                }
            }
        }
        return null;
    }
    
    public static boolean isValidUri(final String s) {
        if (!StringUtils.isEmpty(s)) {
            try {
                final Uri parse = Uri.parse(s);
                if (parse != null && parse.getHost() != null && parse.getScheme() != null) {
                    return true;
                }
            }
            catch (Throwable t) {
                return false;
            }
        }
        return false;
    }
    
    public static String urlEncodeParam(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Log.w("UriUtils", "Could not encoded param ", ex);
            return URLEncoder.encode(s);
        }
    }
}
