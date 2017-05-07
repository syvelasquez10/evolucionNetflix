// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.content.Intent;
import com.google.android.gms.internal.ny$c;
import com.google.android.gms.internal.ny;
import com.google.android.gms.plus.model.people.Person;
import android.util.Log;
import android.text.TextUtils;
import android.os.Bundle;
import android.net.Uri;

public final class PlusShare
{
    public static final String EXTRA_CALL_TO_ACTION = "com.google.android.apps.plus.CALL_TO_ACTION";
    public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
    public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
    public static final String EXTRA_CONTENT_URL = "com.google.android.apps.plus.CONTENT_URL";
    public static final String EXTRA_IS_INTERACTIVE_POST = "com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST";
    public static final String EXTRA_SENDER_ID = "com.google.android.apps.plus.SENDER_ID";
    public static final String KEY_CALL_TO_ACTION_DEEP_LINK_ID = "deepLinkId";
    public static final String KEY_CALL_TO_ACTION_LABEL = "label";
    public static final String KEY_CALL_TO_ACTION_URL = "url";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
    public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";
    
    protected PlusShare() {
        throw new AssertionError();
    }
    
    public static Bundle a(final String s, final String s2, final Uri uri) {
        final Bundle bundle = new Bundle();
        bundle.putString("title", s);
        bundle.putString("description", s2);
        if (uri != null) {
            bundle.putString("thumbnailUrl", uri.toString());
        }
        return bundle;
    }
    
    protected static boolean ca(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            Log.e("GooglePlusPlatform", "The provided deep-link ID is empty.");
            return false;
        }
        if (s.contains(" ")) {
            Log.e("GooglePlusPlatform", "Spaces are not allowed in deep-link IDs.");
            return false;
        }
        return true;
    }
    
    public static Person createPerson(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("MinimalPerson ID must not be empty.");
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("Display name must not be empty.");
        }
        return new ny(s2, s, null, 0, null);
    }
    
    public static String getDeepLinkId(final Intent intent) {
        String queryParameter = null;
        if (intent != null) {
            queryParameter = queryParameter;
            if (intent.getData() != null) {
                queryParameter = intent.getData().getQueryParameter("deep_link_id");
            }
        }
        return queryParameter;
    }
}
