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

@zzgr
public class zzja extends WebViewClient
{
    private static final String[] zzJU;
    private static final String[] zzJV;
    private zzfi zzAl;
    private zzja$zza zzDn;
    private final HashMap<String, List<zzdk>> zzJW;
    private zzg zzJX;
    private zzja$zzb zzJY;
    private boolean zzJZ;
    private boolean zzKa;
    private zzn zzKb;
    private final zzfg zzKc;
    private boolean zzKd;
    private boolean zzKe;
    private boolean zzKf;
    private boolean zzKg;
    private int zzKh;
    protected zziz zzoM;
    private final Object zzpd;
    private boolean zzqW;
    private zza zzsy;
    private zzdo zzxO;
    private zze zzxQ;
    private zzfc zzxR;
    private zzdm zzxT;
    private zzdg zzxn;
    
    static {
        zzJU = new String[] { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
        zzJV = new String[] { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
    }
    
    public zzja(final zziz zziz, final boolean b) {
        this(zziz, b, new zzfg(zziz, zziz.zzha(), new zzbq(zziz.getContext())), null);
    }
    
    zzja(final zziz zzoM, final boolean zzqW, final zzfg zzKc, final zzfc zzxR) {
        this.zzJW = new HashMap<String, List<zzdk>>();
        this.zzpd = new Object();
        this.zzJZ = false;
        this.zzoM = zzoM;
        this.zzqW = zzqW;
        this.zzKc = zzKc;
        this.zzxR = zzxR;
    }
    
    private void zza(final Context context, final String s, final String s2, final String s3) {
        if (!zzby.zzvp.get()) {
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("err", s);
        bundle.putString("code", s2);
        bundle.putString("host", this.zzaK(s3));
        zzp.zzbv().zza(context, this.zzoM.zzhh().zzJu, "gmob-apps", bundle, true);
    }
    
    private String zzaK(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        final Uri parse = Uri.parse(s);
        if (parse.getHost() != null) {
            return parse.getHost();
        }
        return "";
    }
    
    private static boolean zzg(final Uri uri) {
        final String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
    
    private void zzht() {
        synchronized (this.zzpd) {
            this.zzKa = true;
            // monitorexit(this.zzpd)
            ++this.zzKh;
            this.zzhw();
        }
    }
    
    private void zzhu() {
        --this.zzKh;
        this.zzhw();
    }
    
    private void zzhv() {
        this.zzKg = true;
        this.zzhw();
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        zzb.v("Loading resource: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzh(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        synchronized (this.zzpd) {
            if (this.zzKe && "about:blank".equals(s)) {
                zzb.v("Blank page loaded, 1...");
                this.zzoM.zzhj();
                return;
            }
            // monitorexit(this.zzpd)
            this.zzKf = true;
            this.zzhw();
        }
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        String value;
        if (n < 0 && -n - 1 < zzja.zzJU.length) {
            value = zzja.zzJU[-n - 1];
        }
        else {
            value = String.valueOf(n);
        }
        this.zza(this.zzoM.getContext(), "http_err", value, s2);
        super.onReceivedError(webView, n, s, s2);
    }
    
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        if (sslError != null) {
            final int primaryError = sslError.getPrimaryError();
            String value;
            if (primaryError >= 0 && primaryError < zzja.zzJV.length) {
                value = zzja.zzJV[primaryError];
            }
            else {
                value = String.valueOf(primaryError);
            }
            this.zza(this.zzoM.getContext(), "ssl_err", value, zzp.zzbx().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
    
    public final void reset() {
        synchronized (this.zzpd) {
            this.zzJW.clear();
            this.zzsy = null;
            this.zzJX = null;
            this.zzDn = null;
            this.zzxn = null;
            this.zzJZ = false;
            this.zzqW = false;
            this.zzKa = false;
            this.zzxT = null;
            this.zzKb = null;
            this.zzJY = null;
            if (this.zzxR != null) {
                this.zzxR.zzn(true);
                this.zzxR = null;
            }
            this.zzKd = false;
        }
    }
    
    public boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            default: {
                return false;
            }
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222: {
                return true;
            }
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        zzb.v("AdWebView shouldOverrideUrlLoading: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzh(parse);
        }
        else {
            if (this.zzJZ && webView == this.zzoM.getWebView() && zzg(parse)) {
                if (!this.zzKd) {
                    this.zzKd = true;
                    if (this.zzsy != null && zzby.zzvd.get()) {
                        this.zzsy.onAdClicked();
                    }
                }
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.zzoM.getWebView().willNotDraw()) {
                while (true) {
                    try {
                        final zzan zzhg = this.zzoM.zzhg();
                        Uri zza = parse;
                        if (zzhg != null) {
                            zza = parse;
                            if (zzhg.zzb(parse)) {
                                zza = zzhg.zza(parse, this.zzoM.getContext());
                            }
                        }
                        if (this.zzxQ == null || this.zzxQ.zzbe()) {
                            this.zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", zza.toString(), null, null, null, null, null));
                            return true;
                        }
                    }
                    catch (zzao zzao) {
                        zzb.zzaH("Unable to append parameter to URL: " + s);
                        final Uri zza = parse;
                        continue;
                    }
                    break;
                }
                this.zzxQ.zzp(s);
            }
            else {
                zzb.zzaH("AdWebView unable to handle URL: " + s);
            }
        }
        return true;
    }
    
    public void zzF(final boolean zzJZ) {
        this.zzJZ = zzJZ;
    }
    
    public final void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        zzg zzJX = null;
        final boolean zzhi = this.zzoM.zzhi();
        zza zzsy;
        if (zzhi && !this.zzoM.zzaN().zztf) {
            zzsy = null;
        }
        else {
            zzsy = this.zzsy;
        }
        if (!zzhi) {
            zzJX = this.zzJX;
        }
        this.zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, zzsy, zzJX, this.zzKb, this.zzoM.zzhh()));
    }
    
    public void zza(final AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean b = false;
        final boolean b2 = this.zzxR != null && this.zzxR.zzef();
        final com.google.android.gms.ads.internal.overlay.zze zzbt = zzp.zzbt();
        final Context context = this.zzoM.getContext();
        if (!b2) {
            b = true;
        }
        zzbt.zza(context, adOverlayInfoParcel, b);
    }
    
    public void zza(final zzja$zza zzDn) {
        this.zzDn = zzDn;
    }
    
    public final void zza(final String s, final zzdk zzdk) {
        synchronized (this.zzpd) {
            List<zzdk> list;
            if ((list = this.zzJW.get(s)) == null) {
                list = new CopyOnWriteArrayList<zzdk>();
                this.zzJW.put(s, list);
            }
            list.add(zzdk);
        }
    }
    
    public final void zza(final boolean b, final int n) {
        zza zzsy;
        if (this.zzoM.zzhi() && !this.zzoM.zzaN().zztf) {
            zzsy = null;
        }
        else {
            zzsy = this.zzsy;
        }
        this.zza(new AdOverlayInfoParcel(zzsy, this.zzJX, this.zzKb, this.zzoM, b, n, this.zzoM.zzhh()));
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        zzg zzg = null;
        final boolean zzhi = this.zzoM.zzhi();
        zza zzsy;
        if (zzhi && !this.zzoM.zzaN().zztf) {
            zzsy = null;
        }
        else {
            zzsy = this.zzsy;
        }
        if (!zzhi) {
            zzg = new zzja$zzc(this.zzoM, this.zzJX);
        }
        this.zza(new AdOverlayInfoParcel(zzsy, zzg, this.zzxn, this.zzKb, this.zzoM, b, n, s, this.zzoM.zzhh(), this.zzxT));
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        final boolean zzhi = this.zzoM.zzhi();
        zza zzsy;
        if (zzhi && !this.zzoM.zzaN().zztf) {
            zzsy = null;
        }
        else {
            zzsy = this.zzsy;
        }
        zzg zzg;
        if (zzhi) {
            zzg = null;
        }
        else {
            zzg = new zzja$zzc(this.zzoM, this.zzJX);
        }
        this.zza(new AdOverlayInfoParcel(zzsy, zzg, this.zzxn, this.zzKb, this.zzoM, b, n, s, s2, this.zzoM.zzhh(), this.zzxT));
    }
    
    public void zzb(final zza zzsy, final zzg zzJX, final zzdg zzxn, final zzn zzKb, final boolean b, final zzdm zzxT, final zzdo zzxO, final zze zze, final zzfi zzAl) {
        zze zzxQ = zze;
        if (zze == null) {
            zzxQ = new zze(false);
        }
        this.zzxR = new zzfc(this.zzoM, zzAl);
        this.zza("/appEvent", new zzdf(zzxn));
        this.zza("/backButton", zzdj.zzxx);
        this.zza("/canOpenURLs", zzdj.zzxp);
        this.zza("/canOpenIntents", zzdj.zzxq);
        this.zza("/click", zzdj.zzxr);
        this.zza("/close", zzdj.zzxs);
        this.zza("/customClose", zzdj.zzxt);
        this.zza("/instrument", zzdj.zzxA);
        this.zza("/delayPageLoaded", new zzja$zzd(this, null));
        this.zza("/httpTrack", zzdj.zzxu);
        this.zza("/log", zzdj.zzxv);
        this.zza("/mraid", new zzdq(zzxQ, this.zzxR));
        this.zza("/mraidLoaded", this.zzKc);
        this.zza("/open", new zzdr(zzxT, zzxQ, this.zzxR));
        this.zza("/precache", zzdj.zzxz);
        this.zza("/touch", zzdj.zzxw);
        this.zza("/video", zzdj.zzxy);
        if (zzxO != null) {
            this.zza("/setInterstitialProperties", new zzdn(zzxO));
        }
        this.zzsy = zzsy;
        this.zzJX = zzJX;
        this.zzxn = zzxn;
        this.zzxT = zzxT;
        this.zzKb = zzKb;
        this.zzxQ = zzxQ;
        this.zzAl = zzAl;
        this.zzxO = zzxO;
        this.zzF(b);
        this.zzKd = false;
    }
    
    public boolean zzbY() {
        synchronized (this.zzpd) {
            return this.zzqW;
        }
    }
    
    public void zzd(final int n, final int n2) {
        if (this.zzxR != null) {
            this.zzxR.zzd(n, n2);
        }
    }
    
    public void zze(final zziz zzoM) {
        this.zzoM = zzoM;
    }
    
    public final void zzeG() {
        synchronized (this.zzpd) {
            this.zzJZ = false;
            this.zzqW = true;
            zzid.runOnUiThread(new zzja$1(this));
        }
    }
    
    public void zzh(final Uri uri) {
        final String path = uri.getPath();
        final List<zzdk> list = this.zzJW.get(path);
        if (list != null) {
            final Map<String, String> zze = zzp.zzbv().zze(uri);
            if (zzb.zzN(2)) {
                zzb.v("Received GMSG: " + path);
                for (final String s : zze.keySet()) {
                    zzb.v("  " + s + ": " + zze.get(s));
                }
            }
            final Iterator<zzdk> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().zza(this.zzoM, zze);
            }
        }
        else {
            zzb.v("No GMSG handler found for GMSG: " + uri);
        }
    }
    
    public zze zzhq() {
        return this.zzxQ;
    }
    
    public void zzhs() {
        synchronized (this.zzpd) {
            zzb.v("Loading blank page in WebView, 2...");
            this.zzKe = true;
            this.zzoM.zzaI("about:blank");
        }
    }
    
    public final void zzhw() {
        if (this.zzDn != null && ((this.zzKf && this.zzKh <= 0) || this.zzKg)) {
            this.zzDn.zza(this.zzoM, !this.zzKg);
            this.zzDn = null;
        }
        this.zzoM.zzhp();
    }
}
