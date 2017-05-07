// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzfe$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzfe zzAu;
    
    zzfe$2(final zzfe zzAu) {
        this.zzAu = zzAu;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzAu.zzak("User canceled the download.");
    }
}
