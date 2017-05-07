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

class zziq$1 implements Runnable
{
    final /* synthetic */ zziq zzJi;
    
    zziq$1(final zziq zzJi) {
        this.zzJi = zzJi;
    }
    
    @Override
    public void run() {
        this.zzJi.zzoL.zzgZ();
        final zzd zzgQ = this.zzJi.zzoL.zzgQ();
        if (zzgQ != null) {
            zzgQ.zzeA();
        }
        if (this.zzJi.zzIY != null) {
            this.zzJi.zzIY.zzbf();
            this.zzJi.zzIY = null;
        }
    }
}
