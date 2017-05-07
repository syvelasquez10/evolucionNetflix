// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;

public class dz
{
    private static final Uri pK;
    private static final Uri pL;
    
    static {
        pK = Uri.parse("http://plus.google.com/");
        pL = dz.pK.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent Q(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    private static Uri R(final String s) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", s).build();
    }
    
    public static Intent S(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(R(s));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent T(final String s) {
        final Uri parse = Uri.parse("bazaar://search?q=pname:" + s);
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        intent.setFlags(524288);
        return intent;
    }
    
    public static Intent bX() {
        return new Intent("android.settings.DATE_SETTINGS");
    }
}
