// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.content.Context;

@ez
public class gc
{
    private final Object mw;
    private final String vL;
    private int vX;
    private long vY;
    private long vZ;
    private int wa;
    private int wb;
    
    public gc(final String vl) {
        this.mw = new Object();
        this.vX = 0;
        this.vY = -1L;
        this.vZ = -1L;
        this.wa = 0;
        this.wb = -1;
        this.vL = vl;
    }
    
    public static boolean m(final Context context) {
        final int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        final ComponentName componentName = new ComponentName(context.getPackageName(), "com.google.android.gms.ads.AdActivity");
        try {
            if (identifier == context.getPackageManager().getActivityInfo(componentName, 0).theme) {
                return true;
            }
            gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        catch (PackageManager$NameNotFoundException ex) {
            gs.W("Fail to fetch AdActivity theme");
            gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
    
    public Bundle b(final Context context, final String s) {
        synchronized (this.mw) {
            final Bundle bundle = new Bundle();
            bundle.putString("session_id", this.vL);
            bundle.putLong("basets", this.vZ);
            bundle.putLong("currts", this.vY);
            bundle.putString("seq_num", s);
            bundle.putInt("preqs", this.wb);
            bundle.putInt("pclick", this.vX);
            bundle.putInt("pimp", this.wa);
            bundle.putBoolean("support_transparent_background", m(context));
            return bundle;
        }
    }
    
    public void b(final av av, final long n) {
        synchronized (this.mw) {
            if (this.vZ == -1L) {
                this.vZ = n;
                this.vY = this.vZ;
            }
            else {
                this.vY = n;
            }
            if (av.extras != null && av.extras.getInt("gw", 2) == 1) {
                return;
            }
        }
        ++this.wb;
    }
    // monitorexit(o)
    
    public void cP() {
        synchronized (this.mw) {
            ++this.wa;
        }
    }
    
    public void cQ() {
        synchronized (this.mw) {
            ++this.vX;
        }
    }
    
    public long di() {
        return this.vZ;
    }
}
