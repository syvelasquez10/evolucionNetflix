// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzfb$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzfb zzAa;
    
    zzfb$2(final zzfb zzAa) {
        this.zzAa = zzAa;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzAa.zzak("Operation denied by user.");
    }
}
