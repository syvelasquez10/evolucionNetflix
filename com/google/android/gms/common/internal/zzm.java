// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;

public class zzm
{
    private static final Uri zzaaU;
    private static final Uri zzaaV;
    
    static {
        zzaaU = Uri.parse("http://plus.google.com/");
        zzaaV = zzm.zzaaU.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent zzce(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    private static Uri zzcf(final String s) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", s).build();
    }
    
    public static Intent zzcg(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(zzcf(s));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent zznV() {
        final Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
}
