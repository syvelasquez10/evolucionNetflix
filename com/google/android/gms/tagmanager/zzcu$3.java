// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler$Callback;
import android.content.Context;
import android.os.Handler;

class zzcu$3 implements Runnable
{
    final /* synthetic */ zzcu zzaSj;
    
    zzcu$3(final zzcu zzaSj) {
        this.zzaSj = zzaSj;
    }
    
    @Override
    public void run() {
        this.zzaSj.zzaRZ.dispatch();
    }
}
