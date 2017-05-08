// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.util;

import android.provider.MediaStore$Images$Media;
import android.net.Uri;
import android.provider.ContactsContract;

public class UriUtil
{
    private static final String LOCAL_CONTACT_IMAGE_PREFIX;
    
    static {
        LOCAL_CONTACT_IMAGE_PREFIX = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo").getPath();
    }
    
    public static String getSchemeOrNull(final Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }
    
    public static boolean isDataUri(final Uri uri) {
        return "data".equals(getSchemeOrNull(uri));
    }
    
    public static boolean isLocalAssetUri(final Uri uri) {
        return "asset".equals(getSchemeOrNull(uri));
    }
    
    public static boolean isLocalCameraUri(final Uri uri) {
        final String string = uri.toString();
        return string.startsWith(MediaStore$Images$Media.EXTERNAL_CONTENT_URI.toString()) || string.startsWith(MediaStore$Images$Media.INTERNAL_CONTENT_URI.toString());
    }
    
    public static boolean isLocalContactUri(final Uri uri) {
        return isLocalContentUri(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(UriUtil.LOCAL_CONTACT_IMAGE_PREFIX);
    }
    
    public static boolean isLocalContentUri(final Uri uri) {
        return "content".equals(getSchemeOrNull(uri));
    }
    
    public static boolean isLocalFileUri(final Uri uri) {
        return "file".equals(getSchemeOrNull(uri));
    }
    
    public static boolean isLocalResourceUri(final Uri uri) {
        return "res".equals(getSchemeOrNull(uri));
    }
    
    public static boolean isNetworkUri(final Uri uri) {
        final String schemeOrNull = getSchemeOrNull(uri);
        return "https".equals(schemeOrNull) || "http".equals(schemeOrNull);
    }
}
