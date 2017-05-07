// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

class g implements m
{
    private static Object sf;
    private static g ss;
    protected String so;
    protected String sp;
    protected String sq;
    protected String sr;
    
    static {
        g.sf = new Object();
    }
    
    protected g() {
    }
    
    private g(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        this.sq = context.getPackageName();
        this.sr = packageManager.getInstallerPackageName(this.sq);
        final String sq = this.sq;
        final String s = null;
        String string = sq;
        while (true) {
            try {
                final PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                String sp = s;
                string = sq;
                if (packageInfo != null) {
                    string = sq;
                    final String s2 = string = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                    final String versionName = packageInfo.versionName;
                    string = s2;
                    sp = versionName;
                }
                this.so = string;
                this.sp = sp;
            }
            catch (PackageManager$NameNotFoundException ex) {
                aa.w("Error retrieving package info: appName set to " + string);
                final String sp = s;
                continue;
            }
            break;
        }
    }
    
    public static g ca() {
        return g.ss;
    }
    
    public static void n(final Context context) {
        synchronized (g.sf) {
            if (g.ss == null) {
                g.ss = new g(context);
            }
        }
    }
    
    public boolean C(final String s) {
        return "&an".equals(s) || "&av".equals(s) || "&aid".equals(s) || "&aiid".equals(s);
    }
    
    @Override
    public String getValue(final String s) {
        if (s != null) {
            if (s.equals("&an")) {
                return this.so;
            }
            if (s.equals("&av")) {
                return this.sp;
            }
            if (s.equals("&aid")) {
                return this.sq;
            }
            if (s.equals("&aiid")) {
                return this.sr;
            }
        }
        return null;
    }
}
