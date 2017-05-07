// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.R$string;
import android.app.AlertDialog$Builder;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class dc$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ dc qS;
    
    dc$1(final dc qs) {
        this.qS = qs;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.qS.mContext.startActivity(this.qS.bH());
    }
}
