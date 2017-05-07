// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.util.DisplayMetrics;
import android.content.Context;

class ad implements l
{
    private static ad Bi;
    private static Object xz;
    private final Context mContext;
    
    static {
        ad.xz = new Object();
    }
    
    protected ad(final Context mContext) {
        this.mContext = mContext;
    }
    
    public static ad eR() {
        synchronized (ad.xz) {
            return ad.Bi;
        }
    }
    
    public static void y(final Context context) {
        synchronized (ad.xz) {
            if (ad.Bi == null) {
                ad.Bi = new ad(context);
            }
        }
    }
    
    public boolean ac(final String s) {
        return "&sr".equals(s);
    }
    
    protected String eS() {
        final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }
    
    @Override
    public String getValue(final String s) {
        if (s != null && s.equals("&sr")) {
            return this.eS();
        }
        return null;
    }
}
