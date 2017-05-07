// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.util.DisplayMetrics;
import android.content.Context;

class ae implements m
{
    private static Object sf;
    private static ae vH;
    private final Context mContext;
    
    static {
        ae.sf = new Object();
    }
    
    protected ae(final Context mContext) {
        this.mContext = mContext;
    }
    
    public static ae cZ() {
        synchronized (ae.sf) {
            return ae.vH;
        }
    }
    
    public static void n(final Context context) {
        synchronized (ae.sf) {
            if (ae.vH == null) {
                ae.vH = new ae(context);
            }
        }
    }
    
    public boolean C(final String s) {
        return "&sr".equals(s);
    }
    
    protected String da() {
        final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }
    
    @Override
    public String getValue(final String s) {
        if (s != null && s.equals("&sr")) {
            return this.da();
        }
        return null;
    }
}
