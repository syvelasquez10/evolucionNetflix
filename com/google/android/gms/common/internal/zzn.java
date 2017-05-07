// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;

public class zzn
{
    private static final Uri zzaef;
    private static final Uri zzaeg;
    
    static {
        zzaef = Uri.parse("http://plus.google.com/");
        zzaeg = zzn.zzaef.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent zzcn(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    private static Uri zzco(final String s) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", s).build();
    }
    
    public static Intent zzcp(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(zzco(s));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent zzoM() {
        final Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
}
