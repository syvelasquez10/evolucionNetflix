// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Collections;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

class zzk$1 implements Runnable
{
    final /* synthetic */ String zzQ;
    final /* synthetic */ long zzR;
    final /* synthetic */ zzk zzS;
    
    zzk$1(final zzk zzS, final String zzQ, final long zzR) {
        this.zzS = zzS;
        this.zzQ = zzQ;
        this.zzR = zzR;
    }
    
    @Override
    public void run() {
        this.zzS.zzD.zza(this.zzQ, this.zzR);
        this.zzS.zzD.zzd(this.toString());
    }
}
