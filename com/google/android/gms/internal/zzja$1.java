// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import android.view.KeyEvent;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.WebView;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzg;
import java.util.List;
import java.util.HashMap;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.zzd;

class zzja$1 implements Runnable
{
    final /* synthetic */ zzja zzKi;
    
    zzja$1(final zzja zzKi) {
        this.zzKi = zzKi;
    }
    
    @Override
    public void run() {
        this.zzKi.zzoM.zzho();
        final zzd zzhc = this.zzKi.zzoM.zzhc();
        if (zzhc != null) {
            zzhc.zzeG();
        }
        if (this.zzKi.zzJY != null) {
            this.zzKi.zzJY.zzbf();
            this.zzKi.zzJY = null;
        }
    }
}
