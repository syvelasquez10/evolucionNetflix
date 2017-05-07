// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;

class zzat$1 implements Runnable
{
    final /* synthetic */ zzas zzaQl;
    final /* synthetic */ long zzaQm;
    final /* synthetic */ zzat zzaQn;
    final /* synthetic */ String zzxv;
    
    zzat$1(final zzat zzaQn, final zzas zzaQl, final long zzaQm, final String zzxv) {
        this.zzaQn = zzaQn;
        this.zzaQl = zzaQl;
        this.zzaQm = zzaQm;
        this.zzxv = zzxv;
    }
    
    @Override
    public void run() {
        if (this.zzaQn.zzaQk == null) {
            final zzcu zzAP = zzcu.zzAP();
            zzAP.zza(this.zzaQn.mContext, this.zzaQl);
            this.zzaQn.zzaQk = zzAP.zzAS();
        }
        this.zzaQn.zzaQk.zzg(this.zzaQm, this.zzxv);
    }
}
