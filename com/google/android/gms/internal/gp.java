// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebSettings;
import android.content.Context;

@ez
public final class gp
{
    public static void a(final Context context, final WebSettings webSettings) {
        gn.a(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }
    
    public static String getDefaultUserAgent(final Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
}
