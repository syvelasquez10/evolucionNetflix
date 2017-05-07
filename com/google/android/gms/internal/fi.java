// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;

public class fi
{
    private static final Uri DF;
    private static final Uri DG;
    
    static {
        DF = Uri.parse("http://plus.google.com/");
        DG = fi.DF.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent am(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    private static Uri an(final String s) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", s).build();
    }
    
    public static Intent ao(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(an(s));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent eS() {
        return new Intent("android.settings.DATE_SETTINGS");
    }
}
