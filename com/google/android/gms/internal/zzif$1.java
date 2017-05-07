// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.app.AlertDialog$Builder;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzp;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzif$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String zzIM;
    final /* synthetic */ zzif zzIN;
    
    zzif$1(final zzif zzIN, final String zzIM) {
        this.zzIN = zzIN;
        this.zzIM = zzIM;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        zzp.zzbv().zzb(this.zzIN.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", this.zzIM), (CharSequence)"Share via"));
    }
}
