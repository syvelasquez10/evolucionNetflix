// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

class g implements l
{
    private static g xP;
    private static Object xz;
    protected String xL;
    protected String xM;
    protected String xN;
    protected String xO;
    
    static {
        g.xz = new Object();
    }
    
    protected g() {
    }
    
    private g(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        this.xN = context.getPackageName();
        this.xO = packageManager.getInstallerPackageName(this.xN);
        final String xn = this.xN;
        final String s = null;
        String string = xn;
        while (true) {
            try {
                final PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                String xm = s;
                string = xn;
                if (packageInfo != null) {
                    string = xn;
                    final String s2 = string = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                    final String versionName = packageInfo.versionName;
                    string = s2;
                    xm = versionName;
                }
                this.xL = string;
                this.xM = xm;
            }
            catch (PackageManager$NameNotFoundException ex) {
                z.T("Error retrieving package info: appName set to " + string);
                final String xm = s;
                continue;
            }
            break;
        }
    }
    
    public static g dQ() {
        return g.xP;
    }
    
    public static void y(final Context context) {
        synchronized (g.xz) {
            if (g.xP == null) {
                g.xP = new g(context);
            }
        }
    }
    
    public boolean ac(final String s) {
        return "&an".equals(s) || "&av".equals(s) || "&aid".equals(s) || "&aiid".equals(s);
    }
    
    @Override
    public String getValue(final String s) {
        if (s != null) {
            if (s.equals("&an")) {
                return this.xL;
            }
            if (s.equals("&av")) {
                return this.xM;
            }
            if (s.equals("&aid")) {
                return this.xN;
            }
            if (s.equals("&aiid")) {
                return this.xO;
            }
        }
        return null;
    }
}
