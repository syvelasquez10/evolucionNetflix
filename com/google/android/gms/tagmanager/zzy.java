// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy implements zzbh
{
    private int zzMQ;
    
    public zzy() {
        this.zzMQ = 5;
    }
    
    @Override
    public void e(final String s) {
        if (this.zzMQ <= 6) {
            Log.e("GoogleTagManager", s);
        }
    }
    
    @Override
    public void v(final String s) {
        if (this.zzMQ <= 2) {
            Log.v("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzaD(final String s) {
        if (this.zzMQ <= 4) {
            Log.i("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzaE(final String s) {
        if (this.zzMQ <= 5) {
            Log.w("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzb(final String s, final Throwable t) {
        if (this.zzMQ <= 6) {
            Log.e("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void zzd(final String s, final Throwable t) {
        if (this.zzMQ <= 5) {
            Log.w("GoogleTagManager", s, t);
        }
    }
}
