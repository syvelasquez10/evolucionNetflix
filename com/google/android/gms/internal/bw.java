// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;

public final class bw
{
    public static cm a(final Context context, final bz bz, final a a) {
        if (bz.ej.iM) {
            return b(context, bz, a);
        }
        return c(context, bz, a);
    }
    
    private static cm b(final Context context, final bz bz, final a a) {
        ct.r("Fetching ad response from local ad request service.");
        final bx.a a2 = new bx.a(context, bz, a);
        a2.start();
        return a2;
    }
    
    private static cm c(final Context context, final bz bz, final a a) {
        ct.r("Fetching ad response from remote ad request service.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            ct.v("Failed to connect to remote ad request service.");
            return null;
        }
        return new bx.b(context, bz, a);
    }
    
    public interface a
    {
        void a(final cb p0);
    }
}
