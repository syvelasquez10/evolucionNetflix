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

@zzgk
public class zziq extends WebViewClient
{
    private static final String[] zzIU;
    private static final String[] zzIV;
    private zziq$zza zzCv;
    private final HashMap<String, List<zzdg>> zzIW;
    private zzg zzIX;
    private zziq$zzb zzIY;
    private boolean zzIZ;
    private boolean zzJa;
    private zzn zzJb;
    private final zzfa zzJc;
    private boolean zzJd;
    private boolean zzJe;
    private boolean zzJf;
    private boolean zzJg;
    private int zzJh;
    protected final zzip zzoL;
    private final Object zzpc;
    private boolean zzqL;
    private zza zzsn;
    private zzdd zzwH;
    private zzdk zzxh;
    private zze zzxj;
    private zzew zzxk;
    private zzdi zzxm;
    private zzfc zzzy;
    
    static {
        zzIU = new String[] { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
        zzIV = new String[] { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
    }
    
    public zziq(final zzip zzip, final boolean b) {
        this(zzip, b, new zzfa(zzip, zzip.zzgO(), new zzbq(zzip.getContext())), null);
    }
    
    zziq(final zzip zzoL, final boolean zzqL, final zzfa zzJc, final zzew zzxk) {
        this.zzIW = new HashMap<String, List<zzdg>>();
        this.zzpc = new Object();
        this.zzIZ = false;
        this.zzoL = zzoL;
        this.zzqL = zzqL;
        this.zzJc = zzJc;
        this.zzxk = zzxk;
    }
    
    private void zza(final Context context, final String s, final String s2, final String s3) {
        if (!zzby.zzva.get()) {
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("err", s);
        bundle.putString("code", s2);
        bundle.putString("host", this.zzaI(s3));
        zzp.zzbx().zza(context, this.zzoL.zzgV().zzIz, "gmob-apps", bundle, true);
    }
    
    private String zzaI(final String s) {
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
    
    private void zzhe() {
        synchronized (this.zzpc) {
            this.zzJa = true;
            // monitorexit(this.zzpc)
            ++this.zzJh;
            this.zzhh();
        }
    }
    
    private void zzhf() {
        --this.zzJh;
        this.zzhh();
    }
    
    private void zzhg() {
        this.zzJg = true;
        this.zzhh();
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        zzb.v("Loading resource: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzh(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        synchronized (this.zzpc) {
            if (this.zzJe && "about:blank".equals(s)) {
                zzb.v("Blank page loaded, 1...");
                this.zzoL.zzgX();
                return;
            }
            // monitorexit(this.zzpc)
            this.zzJf = true;
            this.zzhh();
        }
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        String value;
        if (n < 0 && -n - 1 < zziq.zzIU.length) {
            value = zziq.zzIU[-n - 1];
        }
        else {
            value = String.valueOf(n);
        }
        this.zza(this.zzoL.getContext(), "http_err", value, s2);
        super.onReceivedError(webView, n, s, s2);
    }
    
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        if (sslError != null) {
            final int primaryError = sslError.getPrimaryError();
            String value;
            if (primaryError >= 0 && primaryError < zziq.zzIV.length) {
                value = zziq.zzIV[primaryError];
            }
            else {
                value = String.valueOf(primaryError);
            }
            this.zza(this.zzoL.getContext(), "ssl_err", value, zzp.zzbz().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
    
    public final void reset() {
        synchronized (this.zzpc) {
            this.zzIW.clear();
            this.zzsn = null;
            this.zzIX = null;
            this.zzCv = null;
            this.zzwH = null;
            this.zzIZ = false;
            this.zzqL = false;
            this.zzJa = false;
            this.zzxm = null;
            this.zzJb = null;
            this.zzIY = null;
            if (this.zzxk != null) {
                this.zzxk.zzn(true);
                this.zzxk = null;
            }
            this.zzJd = false;
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
            if (this.zzIZ && webView == this.zzoL && zzg(parse)) {
                if (!this.zzJd) {
                    this.zzJd = true;
                    if (this.zzsn != null && zzby.zzuO.get()) {
                        this.zzsn.onAdClicked();
                    }
                }
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.zzoL.willNotDraw()) {
                while (true) {
                    try {
                        final zzan zzgU = this.zzoL.zzgU();
                        Uri zza = parse;
                        if (zzgU != null) {
                            zza = parse;
                            if (zzgU.zzb(parse)) {
                                zza = zzgU.zza(parse, this.zzoL.getContext());
                            }
                        }
                        if (this.zzxj == null || this.zzxj.zzbe()) {
                            this.zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", zza.toString(), null, null, null, null, null));
                            return true;
                        }
                    }
                    catch (zzao zzao) {
                        zzb.zzaE("Unable to append parameter to URL: " + s);
                        final Uri zza = parse;
                        continue;
                    }
                    break;
                }
                this.zzxj.zzp(s);
            }
            else {
                zzb.zzaE("AdWebView unable to handle URL: " + s);
            }
        }
        return true;
    }
    
    public void zzF(final boolean zzIZ) {
        this.zzIZ = zzIZ;
    }
    
    public final void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        zzg zzIX = null;
        final boolean zzgW = this.zzoL.zzgW();
        zza zzsn;
        if (zzgW && !this.zzoL.zzaN().zzsH) {
            zzsn = null;
        }
        else {
            zzsn = this.zzsn;
        }
        if (!zzgW) {
            zzIX = this.zzIX;
        }
        this.zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, zzsn, zzIX, this.zzJb, this.zzoL.zzgV()));
    }
    
    public void zza(final AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean b = false;
        final boolean b2 = this.zzxk != null && this.zzxk.zzdY();
        final com.google.android.gms.ads.internal.overlay.zze zzbv = zzp.zzbv();
        final Context context = this.zzoL.getContext();
        if (!b2) {
            b = true;
        }
        zzbv.zza(context, adOverlayInfoParcel, b);
    }
    
    public void zza(final zziq$zza zzCv) {
        this.zzCv = zzCv;
    }
    
    public final void zza(final String s, final zzdg zzdg) {
        synchronized (this.zzpc) {
            List<zzdg> list;
            if ((list = this.zzIW.get(s)) == null) {
                list = new CopyOnWriteArrayList<zzdg>();
                this.zzIW.put(s, list);
            }
            list.add(zzdg);
        }
    }
    
    public final void zza(final boolean b, final int n) {
        zza zzsn;
        if (this.zzoL.zzgW() && !this.zzoL.zzaN().zzsH) {
            zzsn = null;
        }
        else {
            zzsn = this.zzsn;
        }
        this.zza(new AdOverlayInfoParcel(zzsn, this.zzIX, this.zzJb, this.zzoL, b, n, this.zzoL.zzgV()));
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        zzg zzg = null;
        final boolean zzgW = this.zzoL.zzgW();
        zza zzsn;
        if (zzgW && !this.zzoL.zzaN().zzsH) {
            zzsn = null;
        }
        else {
            zzsn = this.zzsn;
        }
        if (!zzgW) {
            zzg = new zziq$zzc(this.zzoL, this.zzIX);
        }
        this.zza(new AdOverlayInfoParcel(zzsn, zzg, this.zzwH, this.zzJb, this.zzoL, b, n, s, this.zzoL.zzgV(), this.zzxm));
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        final boolean zzgW = this.zzoL.zzgW();
        zza zzsn;
        if (zzgW && !this.zzoL.zzaN().zzsH) {
            zzsn = null;
        }
        else {
            zzsn = this.zzsn;
        }
        zzg zzg;
        if (zzgW) {
            zzg = null;
        }
        else {
            zzg = new zziq$zzc(this.zzoL, this.zzIX);
        }
        this.zza(new AdOverlayInfoParcel(zzsn, zzg, this.zzwH, this.zzJb, this.zzoL, b, n, s, s2, this.zzoL.zzgV(), this.zzxm));
    }
    
    public void zzb(final zza zzsn, final zzg zzIX, final zzdd zzwH, final zzn zzJb, final boolean b, final zzdi zzxm, final zzdk zzxh, final zze zze, final zzfc zzzy) {
        zze zzxj = zze;
        if (zze == null) {
            zzxj = new zze(false);
        }
        this.zzxk = new zzew(this.zzoL, zzzy);
        this.zza("/appEvent", new zzdc(zzwH));
        this.zza("/backButton", zzdf.zzwR);
        this.zza("/canOpenURLs", zzdf.zzwJ);
        this.zza("/canOpenIntents", zzdf.zzwK);
        this.zza("/click", zzdf.zzwL);
        this.zza("/close", zzdf.zzwM);
        this.zza("/customClose", zzdf.zzwN);
        this.zza("/delayPageLoaded", new zziq$zzd(this, null));
        this.zza("/httpTrack", zzdf.zzwO);
        this.zza("/log", zzdf.zzwP);
        this.zza("/mraid", new zzdm(zzxj, this.zzxk));
        this.zza("/mraidLoaded", this.zzJc);
        this.zza("/open", new zzdn(zzxm, zzxj, this.zzxk));
        this.zza("/precache", zzdf.zzwT);
        this.zza("/touch", zzdf.zzwQ);
        this.zza("/video", zzdf.zzwS);
        if (zzxh != null) {
            this.zza("/setInterstitialProperties", new zzdj(zzxh));
        }
        this.zzsn = zzsn;
        this.zzIX = zzIX;
        this.zzwH = zzwH;
        this.zzxm = zzxm;
        this.zzJb = zzJb;
        this.zzxj = zzxj;
        this.zzzy = zzzy;
        this.zzxh = zzxh;
        this.zzF(b);
        this.zzJd = false;
    }
    
    public boolean zzbY() {
        synchronized (this.zzpc) {
            return this.zzqL;
        }
    }
    
    public void zzd(final int n, final int n2) {
        if (this.zzxk != null) {
            this.zzxk.zzd(n, n2);
        }
    }
    
    public final void zzeA() {
        synchronized (this.zzpc) {
            this.zzIZ = false;
            this.zzqL = true;
            zzhu.runOnUiThread(new zziq$1(this));
        }
    }
    
    public void zzh(final Uri uri) {
        final String path = uri.getPath();
        final List<zzdg> list = this.zzIW.get(path);
        if (list != null) {
            final Map<String, String> zze = zzp.zzbx().zze(uri);
            if (zzb.zzM(2)) {
                zzb.v("Received GMSG: " + path);
                for (final String s : zze.keySet()) {
                    zzb.v("  " + s + ": " + zze.get(s));
                }
            }
            final Iterator<zzdg> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().zza(this.zzoL, zze);
            }
        }
        else {
            zzb.v("No GMSG handler found for GMSG: " + uri);
        }
    }
    
    public zze zzhb() {
        return this.zzxj;
    }
    
    public void zzhd() {
        synchronized (this.zzpc) {
            zzb.v("Loading blank page in WebView, 2...");
            this.zzJe = true;
            this.zzoL.zzaF("about:blank");
        }
    }
    
    public final void zzhh() {
        if (this.zzCv != null && ((this.zzJf && this.zzJh <= 0) || this.zzJg)) {
            this.zzCv.zza(this.zzoL, !this.zzJg);
            this.zzCv = null;
        }
    }
}
