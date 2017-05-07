// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzev$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzev zzzn;
    
    zzev$2(final zzev zzzn) {
        this.zzzn = zzzn;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzzn.zzah("Operation denied by user.");
    }
}
