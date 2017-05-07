// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;

@ez
public final class ff
{
    public static gg a(final Context context, final fi fi, final a a) {
        if (fi.lD.wG) {
            return b(context, fi, a);
        }
        return c(context, fi, a);
    }
    
    private static gg b(final Context context, final fi fi, final a a) {
        gs.S("Fetching ad response from local ad request service.");
        final fg.a a2 = new fg.a(context, fi, a);
        a2.start();
        return a2;
    }
    
    private static gg c(final Context context, final fi fi, final a a) {
        gs.S("Fetching ad response from remote ad request service.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            gs.W("Failed to connect to remote ad request service.");
            return null;
        }
        return new fg.b(context, fi, a);
    }
    
    public interface a
    {
        void a(final fk p0);
    }
}
