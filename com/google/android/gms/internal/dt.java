// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebSettings;
import android.content.Context;

public final class dt
{
    public static void a(final Context context, final WebSettings webSettings) {
        ds.a(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }
    
    public static String getDefaultUserAgent(final Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
}
