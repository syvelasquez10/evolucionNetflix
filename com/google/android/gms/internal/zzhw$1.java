// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzp;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.app.AlertDialog$Builder;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzhw$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String zzHS;
    final /* synthetic */ zzhw zzHT;
    
    zzhw$1(final zzhw zzHT, final String zzHS) {
        this.zzHT = zzHT;
        this.zzHS = zzHS;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzHT.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", this.zzHS), (CharSequence)"Share via"));
    }
}
