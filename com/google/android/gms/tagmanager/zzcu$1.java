// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler$Callback;
import android.content.Context;
import android.os.Handler;

class zzcu$1 implements zzav
{
    final /* synthetic */ zzcu zzaSj;
    
    zzcu$1(final zzcu zzaSj) {
        this.zzaSj = zzaSj;
    }
    
    @Override
    public void zzas(final boolean b) {
        this.zzaSj.zzd(b, this.zzaSj.connected);
    }
}
