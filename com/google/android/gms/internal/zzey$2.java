// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzey$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzey zzzH;
    
    zzey$2(final zzey zzzH) {
        this.zzzH = zzzH;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzzH.zzah("User canceled the download.");
    }
}
