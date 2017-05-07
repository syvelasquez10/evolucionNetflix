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

class zzja$zzd implements zzdk
{
    final /* synthetic */ zzja zzKi;
    
    private zzja$zzd(final zzja zzKi) {
        this.zzKi = zzKi;
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        if (map.keySet().contains("start")) {
            this.zzKi.zzht();
        }
        else {
            if (map.keySet().contains("stop")) {
                this.zzKi.zzhu();
                return;
            }
            if (map.keySet().contains("cancel")) {
                this.zzKi.zzhv();
            }
        }
    }
}
