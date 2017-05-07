// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.AlertDialog$Builder;
import com.google.android.gms.R$string;
import com.google.android.gms.ads.internal.zzp;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzev$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ zzev zzzn;
    
    zzev$1(final zzev zzzn) {
        this.zzzn = zzzn;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzzn.mContext.startActivity(this.zzzn.createIntent());
    }
}
