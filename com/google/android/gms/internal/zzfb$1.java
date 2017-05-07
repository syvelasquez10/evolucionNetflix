// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.AlertDialog$Builder;
import com.google.android.gms.R$string;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzfb$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzfb zzAa;
    
    zzfb$1(final zzfb zzAa) {
        this.zzAa = zzAa;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        zzp.zzbv().zzb(this.zzAa.mContext, this.zzAa.createIntent());
    }
}
