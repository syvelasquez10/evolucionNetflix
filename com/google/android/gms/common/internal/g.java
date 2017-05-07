// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;

public class g
{
    private static final Uri LV;
    private static final Uri LW;
    
    static {
        LV = Uri.parse("http://plus.google.com/");
        LW = g.LV.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent aW(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    private static Uri aX(final String s) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", s).build();
    }
    
    public static Intent aY(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(aX(s));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent gZ() {
        final Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
}
