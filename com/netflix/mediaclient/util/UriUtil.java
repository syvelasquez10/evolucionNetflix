// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;

public final class UriUtil
{
    private static final int FIRST_STILL_THRESHOLD_SECONDS = 10;
    private static final String TAG = "UriUtils";
    
    public static String buildStillUrlFromPos(final CWVideo cwVideo, final boolean b) {
        String s;
        if (b) {
            s = cwVideo.getTrickplayBigImgBaseUrl();
        }
        else {
            s = cwVideo.getTrickplayImgBaseUrl();
        }
        final int playableBookmarkPosition = cwVideo.getPlayableBookmarkPosition();
        if (StringUtils.isEmpty(s)) {
            return cwVideo.getInterestingUrl();
        }
        if (playableBookmarkPosition < cwVideo.getLogicalStart() + 10) {
            if (Log.isLoggable()) {
                Log.v("UriUtils", String.format("%s bookmark(%d) < threshold(%d),  logicalStart %d, intrUrl:%s", cwVideo.getId(), playableBookmarkPosition, 10, cwVideo.getLogicalStart(), cwVideo.getInterestingUrl()));
            }
            return cwVideo.getInterestingUrl();
        }
        final String value = String.valueOf(playableBookmarkPosition / 10);
        final StringBuilder append = new StringBuilder(s).append("/00000");
        append.replace(append.length() - value.length(), append.length(), value);
        append.append(".jpg");
        if (Log.isLoggable()) {
            Log.v("UriUtils", String.format("%s stillId: %s, stillUrl: %s", cwVideo.getId(), value, append.toString()));
        }
        return append.toString();
    }
    
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
