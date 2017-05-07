// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
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
import java.util.Map;

class zziq$zzd implements zzdg
{
    final /* synthetic */ zziq zzJi;
    
    private zziq$zzd(final zziq zzJi) {
        this.zzJi = zzJi;
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        if (map.keySet().contains("start")) {
            this.zzJi.zzhe();
        }
        else {
            if (map.keySet().contains("stop")) {
                this.zzJi.zzhf();
                return;
            }
            if (map.keySet().contains("cancel")) {
                this.zzJi.zzhg();
            }
        }
    }
}
