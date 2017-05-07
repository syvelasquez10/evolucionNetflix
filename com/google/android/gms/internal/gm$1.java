// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import android.app.AlertDialog$Builder;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class gm$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String ww;
    final /* synthetic */ gm wx;
    
    gm$1(final gm wx, final String ww) {
        this.wx = wx;
        this.ww = ww;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.wx.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", this.ww), (CharSequence)"Share via"));
    }
}
