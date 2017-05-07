// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;

class zzbk$1 implements Runnable
{
    final /* synthetic */ View zzsg;
    final /* synthetic */ zzbk zzsh;
    
    zzbk$1(final zzbk zzsh, final View zzsg) {
        this.zzsh = zzsh;
        this.zzsg = zzsg;
    }
    
    @Override
    public void run() {
        this.zzsh.zzg(this.zzsg);
    }
}
