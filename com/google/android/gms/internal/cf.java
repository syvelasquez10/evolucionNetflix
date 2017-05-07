// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;

@ez
public final class cf
{
    public static void a(final Context context, final cf$b cf$b) {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            cf$b.a(bn.bs());
            return;
        }
        new cf$a(context, cf$b);
    }
}
