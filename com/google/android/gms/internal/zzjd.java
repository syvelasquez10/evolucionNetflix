// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzl;
import android.app.Activity;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;
import android.webkit.WebViewClient;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.ValueCallback;
import java.util.Iterator;
import android.view.View;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import java.util.Map;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

@zzgr
class zzjd extends WebView implements ViewTreeObserver$OnGlobalLayoutListener, DownloadListener, zziz
{
    private int zzAD;
    private int zzAE;
    private int zzAG;
    private int zzAH;
    private String zzBY;
    private Boolean zzHZ;
    private Map<String, zzdv> zzKA;
    private final zzjd$zza zzKm;
    private zzja zzKn;
    private zzd zzKo;
    private boolean zzKp;
    private boolean zzKq;
    private boolean zzKr;
    private boolean zzKs;
    private int zzKt;
    private boolean zzKu;
    private zzce zzKv;
    private zzce zzKw;
    private zzce zzKx;
    private zzcf zzKy;
    private zzd zzKz;
    private final com.google.android.gms.ads.internal.zzd zzow;
    private final VersionInfoParcel zzpb;
    private final Object zzpd;
    private zzim zzqR;
    private final WindowManager zzri;
    private final zzan zzwL;
    private AdSizeParcel zzzm;
    
    protected zzjd(final zzjd$zza zzKm, final AdSizeParcel zzzm, final boolean zzKr, final boolean b, final zzan zzwL, final VersionInfoParcel zzpb, final zzcg zzcg, final com.google.android.gms.ads.internal.zzd zzow) {
        super((Context)zzKm);
        this.zzpd = new Object();
        this.zzKu = true;
        this.zzBY = "";
        this.zzAE = -1;
        this.zzAD = -1;
        this.zzAG = -1;
        this.zzAH = -1;
        this.zzKm = zzKm;
        this.zzzm = zzzm;
        this.zzKr = zzKr;
        this.zzKt = -1;
        this.zzwL = zzwL;
        this.zzpb = zzpb;
        this.zzow = zzow;
        this.zzri = (WindowManager)this.getContext().getSystemService("window");
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build$VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        zzp.zzbv().zza((Context)zzKm, zzpb.zzJu, settings);
        zzp.zzbx().zza(this.getContext(), settings);
        this.setDownloadListener((DownloadListener)this);
        this.zzhz();
        if (zzmx.zzqz()) {
            this.addJavascriptInterface((Object)new zzje(this), "googleAdsJsInterface");
        }
        this.zzqR = new zzim(this.zzKm.zzgZ(), (ViewTreeObserver$OnGlobalLayoutListener)this, null);
        this.zzd(zzcg);
    }
    
    static zzjd zzb(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, final zzan zzan, final VersionInfoParcel versionInfoParcel, final zzcg zzcg, final com.google.android.gms.ads.internal.zzd zzd) {
        return new zzjd(new zzjd$zza(context), adSizeParcel, b, b2, zzan, versionInfoParcel, zzcg, zzd);
    }
    
    private void zzd(final zzcg zzcg) {
        this.zzhD();
        this.zzKy = new zzcf(new zzcg(true, "make_wv", this.zzzm.zzte));
        this.zzKy.zzdm().zzc(zzcg);
        this.zzKw = zzcc.zzb(this.zzKy.zzdm());
        this.zzKy.zza("native:view_create", this.zzKw);
        this.zzKx = null;
        this.zzKv = null;
    }
    
    private void zzhA() {
        synchronized (this.zzpd) {
            if (!this.zzKs) {
                zzp.zzbx().zzm((View)this);
            }
            this.zzKs = true;
        }
    }
    
    private void zzhB() {
        synchronized (this.zzpd) {
            if (this.zzKs) {
                zzp.zzbx().zzl((View)this);
            }
            this.zzKs = false;
        }
    }
    
    private void zzhC() {
        synchronized (this.zzpd) {
            if (this.zzKA != null) {
                final Iterator<zzdv> iterator = this.zzKA.values().iterator();
                while (iterator.hasNext()) {
                    iterator.next().release();
                }
            }
        }
    }
    // monitorexit(o)
    
    private void zzhD() {
        if (this.zzKy != null) {
            final zzcg zzdm = this.zzKy.zzdm();
            if (zzdm != null && zzp.zzby().zzgo() != null) {
                zzp.zzby().zzgo().zza(zzdm);
            }
        }
    }
    
    private void zzhy() {
        synchronized (this.zzpd) {
            this.zzHZ = zzp.zzby().zzgs();
            if (this.zzHZ != null) {
                return;
            }
            try {
                this.evaluateJavascript("(function(){})()", null);
                this.zzb(Boolean.valueOf(true));
            }
            catch (IllegalStateException ex) {
                this.zzb(Boolean.valueOf(false));
            }
        }
    }
    
    private void zzhz() {
        while (true) {
            synchronized (this.zzpd) {
                if (this.zzKr || this.zzzm.zztf) {
                    if (Build$VERSION.SDK_INT < 14) {
                        zzb.zzaF("Disabling hardware acceleration on an overlay.");
                        this.zzhA();
                    }
                    else {
                        zzb.zzaF("Enabling hardware acceleration on an overlay.");
                        this.zzhB();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                zzb.zzaF("Disabling hardware acceleration on an AdView.");
                this.zzhA();
                return;
            }
            zzb.zzaF("Enabling hardware acceleration on an AdView.");
            this.zzhB();
        }
    }
    
    public void destroy() {
        synchronized (this.zzpd) {
            this.zzhD();
            this.zzqR.zzgP();
            if (this.zzKo != null) {
                this.zzKo.close();
                this.zzKo.onDestroy();
                this.zzKo = null;
            }
            this.zzKn.reset();
            if (this.zzKq) {
                return;
            }
            zzp.zzbI().zza(this);
            this.zzhC();
            this.zzKq = true;
            zzb.v("Initiating WebView self destruct sequence in 3...");
            this.zzKn.zzhs();
        }
    }
    
    public void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzpd) {
            if (this.isDestroyed()) {
                zzb.zzaH("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
                return;
            }
            super.evaluateJavascript(s, (ValueCallback)valueCallback);
        }
    }
    
    public View getView() {
        return (View)this;
    }
    
    public WebView getWebView() {
        return this;
    }
    
    public boolean isDestroyed() {
        synchronized (this.zzpd) {
            return this.zzKq;
        }
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        synchronized (this.zzpd) {
            if (!this.isDestroyed()) {
                super.loadData(s, s2, s3);
            }
            else {
                zzb.zzaH("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this.zzpd) {
            if (!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s2, s3, s4, s5);
            }
            else {
                zzb.zzaH("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadUrl(final String s) {
        while (true) {
            synchronized (this.zzpd) {
                if (!this.isDestroyed()) {
                    try {
                        super.loadUrl(s);
                        return;
                    }
                    catch (Throwable t) {
                        zzb.zzaH("Could not call loadUrl. " + t);
                    }
                }
            }
            zzb.zzaH("The webview is destroyed. Ignoring action.");
        }
    }
    
    protected void onAttachedToWindow() {
        synchronized (this.zzpd) {
            super.onAttachedToWindow();
            if (!this.isDestroyed()) {
                this.zzqR.onAttachedToWindow();
            }
        }
    }
    
    protected void onDetachedFromWindow() {
        synchronized (this.zzpd) {
            if (!this.isDestroyed()) {
                this.zzqR.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            zzp.zzbv().zzb(this.getContext(), intent);
        }
        catch (ActivityNotFoundException ex) {
            zzb.zzaF("Couldn't find an Activity to view url/mimetype: " + s + " / " + s4);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        if (!this.isDestroyed() && (Build$VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || this.isAttachedToWindow())) {
            super.onDraw(canvas);
        }
    }
    
    public void onGlobalLayout() {
        final boolean zzhx = this.zzhx();
        final zzd zzhc = this.zzhc();
        if (zzhc != null && zzhx) {
            zzhc.zzeI();
        }
    }
    
    protected void onMeasure(int n, int n2) {
        final int n3 = Integer.MAX_VALUE;
        synchronized (this.zzpd) {
            if (this.isDestroyed()) {
                this.setMeasuredDimension(0, 0);
                return;
            }
            if (this.isInEditMode() || this.zzKr || this.zzzm.zzth || this.zzzm.zzti) {
                super.onMeasure(n, n2);
                return;
            }
        }
        if (this.zzzm.zztf) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzri.getDefaultDisplay().getMetrics(displayMetrics);
            this.setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
            // monitorexit(o)
            return;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            n = Integer.MAX_VALUE;
        }
        else {
            n = size;
        }
        // monitorexit(o)
        while (true) {
            Label_0390: {
                if (mode2 == Integer.MIN_VALUE) {
                    break Label_0390;
                }
                n2 = n3;
                if (mode2 == 1073741824) {
                    break Label_0390;
                }
                if (this.zzzm.widthPixels > n || this.zzzm.heightPixels > n2) {
                    final float density = this.zzKm.getResources().getDisplayMetrics().density;
                    zzb.zzaH("Not enough space to show ad. Needs " + (int)(this.zzzm.widthPixels / density) + "x" + (int)(this.zzzm.heightPixels / density) + " dp, but only has " + (int)(size / density) + "x" + (int)(size2 / density) + " dp.");
                    if (this.getVisibility() != 8) {
                        this.setVisibility(4);
                    }
                    this.setMeasuredDimension(0, 0);
                }
                else {
                    if (this.getVisibility() != 8) {
                        this.setVisibility(0);
                    }
                    this.setMeasuredDimension(this.zzzm.widthPixels, this.zzzm.heightPixels);
                }
                return;
            }
            n2 = size2;
            continue;
        }
    }
    
    public void onPause() {
        if (!this.isDestroyed()) {
            try {
                if (zzmx.zzqu()) {
                    super.onPause();
                }
            }
            catch (Exception ex) {
                zzb.zzb("Could not pause webview.", ex);
            }
        }
    }
    
    public void onResume() {
        if (!this.isDestroyed()) {
            try {
                if (zzmx.zzqu()) {
                    super.onResume();
                }
            }
            catch (Exception ex) {
                zzb.zzb("Could not resume webview.", ex);
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.zzwL != null) {
            this.zzwL.zza(motionEvent);
        }
        return !this.isDestroyed() && super.onTouchEvent(motionEvent);
    }
    
    public void setContext(final Context baseContext) {
        this.zzKm.setBaseContext(baseContext);
        this.zzqR.zzk(this.zzKm.zzgZ());
    }
    
    public void setRequestedOrientation(final int zzKt) {
        synchronized (this.zzpd) {
            this.zzKt = zzKt;
            if (this.zzKo != null) {
                this.zzKo.setRequestedOrientation(this.zzKt);
            }
        }
    }
    
    public void setWebViewClient(final WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzja) {
            this.zzKn = (zzja)webViewClient;
        }
    }
    
    public void stopLoading() {
        if (this.isDestroyed()) {
            return;
        }
        try {
            super.stopLoading();
        }
        catch (Exception ex) {
            zzb.zzb("Could not stop loading webview.", ex);
        }
    }
    
    public void zzC(final boolean zzKr) {
        synchronized (this.zzpd) {
            this.zzKr = zzKr;
            this.zzhz();
        }
    }
    
    public void zzD(final boolean zzKp) {
        synchronized (this.zzpd) {
            if (this.zzKo != null) {
                this.zzKo.zza(this.zzKn.zzbY(), zzKp);
            }
            else {
                this.zzKp = zzKp;
            }
        }
    }
    
    public void zzE(final boolean zzKu) {
        synchronized (this.zzpd) {
            this.zzKu = zzKu;
        }
    }
    
    public void zza(final AdSizeParcel zzzm) {
        synchronized (this.zzpd) {
            this.zzzm = zzzm;
            this.requestLayout();
        }
    }
    
    protected void zza(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzpd) {
            if (!this.isDestroyed()) {
                this.evaluateJavascript(s, valueCallback);
            }
            else {
                zzb.zzaH("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
        }
    }
    
    public void zzaI(final String s) {
        synchronized (this.zzpd) {
            try {
                super.loadUrl(s);
            }
            catch (Throwable t) {
                zzb.zzaH("Could not call loadUrl. " + t);
            }
        }
    }
    
    protected void zzaL(final String s) {
        synchronized (this.zzpd) {
            if (!this.isDestroyed()) {
                this.loadUrl(s);
            }
            else {
                zzb.zzaH("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    protected void zzaM(final String s) {
        if (!zzmx.zzqB()) {
            this.zzaL("javascript:" + s);
            return;
        }
        if (this.zzgs() == null) {
            this.zzhy();
        }
        if (this.zzgs()) {
            this.zza(s, null);
            return;
        }
        this.zzaL("javascript:" + s);
    }
    
    public AdSizeParcel zzaN() {
        synchronized (this.zzpd) {
            return this.zzzm;
        }
    }
    
    public void zzb(final zzd zzKo) {
        synchronized (this.zzpd) {
            this.zzKo = zzKo;
        }
    }
    
    void zzb(final Boolean zzHZ) {
        this.zzHZ = zzHZ;
        zzp.zzby().zzb(zzHZ);
    }
    
    public void zzb(final String s, final Map<String, ?> map) {
        try {
            this.zzb(s, zzp.zzbv().zzz(map));
        }
        catch (JSONException ex) {
            zzb.zzaH("Could not convert parameters to JSON.");
        }
    }
    
    public void zzb(final String s, final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        final String string = jsonObject2.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("AFMA_ReceiveMessage('");
        sb.append(s);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        zzb.v("Dispatching AFMA event: " + sb.toString());
        this.zzaM(sb.toString());
    }
    
    public void zzc(final zzd zzKz) {
        synchronized (this.zzpd) {
            this.zzKz = zzKz;
        }
    }
    
    public void zzeJ() {
        if (this.zzKv != null) {
            zzcc.zza(this.zzKy.zzdm(), this.zzKx, "aes");
            this.zzKv = zzcc.zzb(this.zzKy.zzdm());
            this.zzKy.zza("native:view_show", this.zzKx);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzpb.zzJu);
        this.zzb("onshow", hashMap);
    }
    
    public void zzgY() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzpb.zzJu);
        this.zzb("onhide", hashMap);
    }
    
    public Activity zzgZ() {
        return this.zzKm.zzgZ();
    }
    
    Boolean zzgs() {
        synchronized (this.zzpd) {
            return this.zzHZ;
        }
    }
    
    public Context zzha() {
        return this.zzKm.zzha();
    }
    
    public com.google.android.gms.ads.internal.zzd zzhb() {
        return this.zzow;
    }
    
    public zzd zzhc() {
        synchronized (this.zzpd) {
            return this.zzKo;
        }
    }
    
    public zzd zzhd() {
        synchronized (this.zzpd) {
            return this.zzKz;
        }
    }
    
    public zzja zzhe() {
        return this.zzKn;
    }
    
    public boolean zzhf() {
        return this.zzKp;
    }
    
    public zzan zzhg() {
        return this.zzwL;
    }
    
    public VersionInfoParcel zzhh() {
        return this.zzpb;
    }
    
    public boolean zzhi() {
        synchronized (this.zzpd) {
            return this.zzKr;
        }
    }
    
    public void zzhj() {
        synchronized (this.zzpd) {
            zzb.v("Destroying WebView!");
            zzid.zzIE.post((Runnable)new zzjd$1(this));
        }
    }
    
    public boolean zzhk() {
        synchronized (this.zzpd) {
            return this.zzKu;
        }
    }
    
    public zziy zzhl() {
        return null;
    }
    
    public zzce zzhm() {
        return this.zzKx;
    }
    
    public zzcf zzhn() {
        return this.zzKy;
    }
    
    public void zzho() {
        this.zzqR.zzgO();
    }
    
    public void zzhp() {
        if (this.zzKx == null && !"about:blank".equals(this.getUrl())) {
            this.zzKx = zzcc.zzb(this.zzKy.zzdm());
            this.zzKy.zza("native:view_load", this.zzKx);
        }
    }
    
    public boolean zzhx() {
        if (this.zzhe().zzbY()) {
            final DisplayMetrics zza = zzp.zzbv().zza(this.zzri);
            final int zzb = zzl.zzcF().zzb(zza, zza.widthPixels);
            final int zzb2 = zzl.zzcF().zzb(zza, zza.heightPixels);
            final Activity zzgZ = this.zzgZ();
            int zzb3;
            int zzb4;
            if (zzgZ == null || zzgZ.getWindow() == null) {
                zzb3 = zzb2;
                zzb4 = zzb;
            }
            else {
                final int[] zzg = zzp.zzbv().zzg(zzgZ);
                zzb4 = zzl.zzcF().zzb(zza, zzg[0]);
                zzb3 = zzl.zzcF().zzb(zza, zzg[1]);
            }
            if (this.zzAD != zzb || this.zzAE != zzb2 || this.zzAG != zzb4 || this.zzAH != zzb3) {
                final boolean b = this.zzAD != zzb || this.zzAE != zzb2;
                this.zzAD = zzb;
                this.zzAE = zzb2;
                this.zzAG = zzb4;
                this.zzAH = zzb3;
                new zzfh(this).zza(zzb, zzb2, zzb4, zzb3, zza.density, this.zzri.getDefaultDisplay().getRotation());
                return b;
            }
        }
        return false;
    }
    
    public void zzv(final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("closetype", String.valueOf(n));
        hashMap.put("version", this.zzpb.zzJu);
        this.zzb("onhide", hashMap);
    }
}
