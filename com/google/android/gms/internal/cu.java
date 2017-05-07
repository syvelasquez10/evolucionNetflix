// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;

public final class cu
{
    public static do a(final Context context, final cx cx, final a a) {
        if (cx.kK.rt) {
            return b(context, cx, a);
        }
        return c(context, cx, a);
    }
    
    private static do b(final Context context, final cx cx, final a a) {
        dw.v("Fetching ad response from local ad request service.");
        final cv.a a2 = new cv.a(context, cx, a);
        a2.start();
        return a2;
    }
    
    private static do c(final Context context, final cx cx, final a a) {
        dw.v("Fetching ad response from remote ad request service.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            dw.z("Failed to connect to remote ad request service.");
            return null;
        }
        return new cv.b(context, cx, a);
    }
    
    public interface a
    {
        void a(final cz p0);
    }
}
