// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.debug;

import android.app.Application;
import com.android.volley.RequestQueue;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class DebugOverlay
{
    public static void attachOn(final NetflixActivity netflixActivity) {
    }
    
    public static RequestQueue getDebugRequestQueue(final Context context) {
        throw new UnsupportedOperationException("getDebugRequestQueue should not be called on release build !");
    }
    
    public static void init(final Application application) {
    }
    
    public static boolean isEnabled(final Context context) {
        return false;
    }
    
    public static void setEnabled(final Context context, final boolean b) {
    }
    
    public static void showToast(final Context context, final CharSequence charSequence) {
    }
}
