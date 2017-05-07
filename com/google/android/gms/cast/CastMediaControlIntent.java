// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.Iterator;
import android.text.TextUtils;
import java.util.Collection;

public final class CastMediaControlIntent
{
    public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
    public static final String CATEGORY_CAST = "com.google.android.gms.cast.CATEGORY_CAST";
    public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
    public static final int ERROR_CODE_REQUEST_FAILED = 1;
    public static final int ERROR_CODE_SESSION_START_FAILED = 2;
    public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
    public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
    public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
    public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
    public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
    public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
    public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";
    
    private static String a(final String s, final String s2, final Collection<String> collection) throws IllegalArgumentException {
        final StringBuffer sb = new StringBuffer(s);
        if (s2 != null) {
            if (!s2.matches("[A-F0-9]+")) {
                throw new IllegalArgumentException("Invalid application ID: " + s2);
            }
            sb.append("/").append(s2);
        }
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            for (final String s3 : collection) {
                if (TextUtils.isEmpty((CharSequence)s3) || s3.trim().equals("")) {
                    throw new IllegalArgumentException("Namespaces must not be null or empty");
                }
            }
            if (s2 == null) {
                sb.append("/");
            }
            sb.append("/").append(TextUtils.join((CharSequence)",", (Iterable)collection));
        }
        return sb.toString();
    }
    
    public static String categoryForCast(final String s) throws IllegalArgumentException {
        if (s == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        return a("com.google.android.gms.cast.CATEGORY_CAST", s, null);
    }
    
    public static String categoryForCast(final String s, final Collection<String> collection) {
        if (s == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        if (collection == null) {
            throw new IllegalArgumentException("namespaces cannot be null");
        }
        return a("com.google.android.gms.cast.CATEGORY_CAST", s, collection);
    }
    
    public static String categoryForCast(final Collection<String> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("namespaces cannot be null");
        }
        return a("com.google.android.gms.cast.CATEGORY_CAST", null, collection);
    }
    
    public static String categoryForRemotePlayback() {
        return a("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", null, null);
    }
    
    public static String categoryForRemotePlayback(final String s) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("applicationId cannot be null or empty");
        }
        return a("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", s, null);
    }
}
