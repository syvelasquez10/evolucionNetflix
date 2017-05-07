// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

public final class FetchErrorUtils
{
    public static final String ACTION_DELETED_PROFILE = "com.netflix.mediaclient.intent.action.DELETED_PROFILE";
    private static final String TAG = "nf_fetcherrorutils";
    
    public static boolean isAccountError(final StatusCode statusCode) {
        return statusCode == StatusCode.DELETED_PROFILE;
    }
    
    public static void notifyOthersOfAccountErrors(final Context context, final StatusCode statusCode) {
        Log.d("nf_fetcherrorutils", "Broadcasting DELETED_PROFILE intent");
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.DELETED_PROFILE"));
    }
}
