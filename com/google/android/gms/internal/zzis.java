// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzk;
import android.app.Activity;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import java.util.Iterator;
import android.view.View;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import com.google.android.gms.ads.internal.overlay.zzd;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

@zzgk
public class zzis extends WebView implements ViewTreeObserver$OnGlobalLayoutListener, DownloadListener, zzip
{
    private String zzBm;
    private final zziq zzCq;
    private Boolean zzHi;
    private final zzis$zza zzJk;
    private zzd zzJl;
    private boolean zzJm;
    private boolean zzJn;
    private boolean zzJo;
    private boolean zzJp;
    private int zzJq;
    private boolean zzJr;
    private String zzJs;
    private zzd zzJt;
    private Map<String, zzdr> zzJu;
    private final com.google.android.gms.ads.internal.zzd zzov;
    private final VersionInfoParcel zzpa;
    private final Object zzpc;
    private zzid zzqG;
    private final WindowManager zzqX;
    private final zzan zzwh;
    private AdSizeParcel zzyK;
    private int zzzQ;
    private int zzzR;
    private int zzzT;
    private int zzzU;
    
    protected zzis(final zzis$zza zzJk, final AdSizeParcel zzyK, final boolean zzJo, final boolean b, final zzan zzwh, final VersionInfoParcel zzpa, final com.google.android.gms.ads.internal.zzd zzov) {
        super((Context)zzJk);
        this.zzpc = new Object();
        this.zzJr = true;
        this.zzBm = "";
        this.zzzR = -1;
        this.zzzQ = -1;
        this.zzzT = -1;
        this.zzzU = -1;
        this.zzJk = zzJk;
        this.zzyK = zzyK;
        this.zzJo = zzJo;
        this.zzJq = -1;
        this.zzwh = zzwh;
        this.zzpa = zzpa;
        this.zzov = zzov;
        this.zzqX = (WindowManager)this.getContext().getSystemService("window");
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build$VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        zzp.zzbx().zza((Context)zzJk, zzpa.zzIz, settings);
        zzp.zzbz().zza(this.getContext(), settings);
        this.setDownloadListener((DownloadListener)this);
        this.setWebViewClient((WebViewClient)(this.zzCq = zzp.zzbz().zzb(this, b)));
        this.setWebChromeClient(zzp.zzbz().zzf(this));
        this.zzhk();
        if (zzlv.zzpT()) {
            this.addJavascriptInterface((Object)new zzit(this), "googleAdsJsInterface");
        }
        this.zzqG = new zzid(this.zzJk.zzgN(), (ViewTreeObserver$OnGlobalLayoutListener)this, null);
    }
    
    static zzis zzb(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, final zzan zzan, final VersionInfoParcel versionInfoParcel, final com.google.android.gms.ads.internal.zzd zzd) {
        return new zzis(new zzis$zza(context), adSizeParcel, b, b2, zzan, versionInfoParcel, zzd);
    }
    
    private void zzhj() {
        synchronized (this.zzpc) {
            this.zzHi = zzp.zzbA().zzgg();
            if (this.zzHi != null) {
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
    
    private void zzhk() {
        while (true) {
            synchronized (this.zzpc) {
                if (this.zzJo || this.zzyK.zzsH) {
                    if (Build$VERSION.SDK_INT < 14) {
                        zzb.zzaC("Disabling hardware acceleration on an overlay.");
                        this.zzhl();
                    }
                    else {
                        zzb.zzaC("Enabling hardware acceleration on an overlay.");
                        this.zzhm();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                zzb.zzaC("Disabling hardware acceleration on an AdView.");
                this.zzhl();
                return;
            }
            zzb.zzaC("Enabling hardware acceleration on an AdView.");
            this.zzhm();
        }
    }
    
    private void zzhl() {
        synchronized (this.zzpc) {
            if (!this.zzJp) {
                zzp.zzbz().zzl((View)this);
            }
            this.zzJp = true;
        }
    }
    
    private void zzhm() {
        synchronized (this.zzpc) {
            if (this.zzJp) {
                zzp.zzbz().zzk((View)this);
            }
            this.zzJp = false;
        }
    }
    
    private void zzhn() {
        synchronized (this.zzpc) {
            if (this.zzJu != null) {
                final Iterator<zzdr> iterator = this.zzJu.values().iterator();
                while (iterator.hasNext()) {
                    iterator.next().release();
                }
            }
        }
    }
    // monitorexit(o)
    
    public void destroy() {
        synchronized (this.zzpc) {
            this.zzqG.zzgE();
            if (this.zzJl != null) {
                this.zzJl.close();
                this.zzJl.onDestroy();
                this.zzJl = null;
            }
            this.zzCq.reset();
            if (this.zzJn) {
                return;
            }
            zzp.zzbK().zza(this);
            this.zzhn();
            this.zzJn = true;
            zzb.v("Initiating WebView self destruct sequence in 3...");
            this.zzCq.zzhd();
        }
    }
    
    public void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzpc) {
            if (this.isDestroyed()) {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
                return;
            }
            super.evaluateJavascript(s, (ValueCallback)valueCallback);
        }
    }
    
    public String getRequestId() {
        synchronized (this.zzpc) {
            return this.zzBm;
        }
    }
    
    public int getRequestedOrientation() {
        synchronized (this.zzpc) {
            return this.zzJq;
        }
    }
    
    public WebView getWebView() {
        return this;
    }
    
    public boolean isDestroyed() {
        synchronized (this.zzpc) {
            return this.zzJn;
        }
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                super.loadData(s, s2, s3);
            }
            else {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s2, s3, s4, s5);
            }
            else {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadUrl(final String s) {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                super.loadUrl(s);
            }
            else {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    protected void onAttachedToWindow() {
        synchronized (this.zzpc) {
            super.onAttachedToWindow();
            if (!this.isDestroyed()) {
                this.zzqG.onAttachedToWindow();
            }
        }
    }
    
    protected void onDetachedFromWindow() {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                this.zzqG.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            this.getContext().startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            zzb.zzaC("Couldn't find an Activity to view url/mimetype: " + s + " / " + s4);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        if (!this.isDestroyed() && (Build$VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || this.isAttachedToWindow())) {
            super.onDraw(canvas);
        }
    }
    
    public void onGlobalLayout() {
        final boolean zzhi = this.zzhi();
        final zzd zzgQ = this.zzgQ();
        if (zzgQ != null && zzhi) {
            zzgQ.zzeC();
        }
    }
    
    protected void onMeasure(int n, int n2) {
        final int n3 = Integer.MAX_VALUE;
        synchronized (this.zzpc) {
            if (this.isDestroyed()) {
                this.setMeasuredDimension(0, 0);
                return;
            }
            if (this.isInEditMode() || this.zzJo || this.zzyK.zzsJ) {
                super.onMeasure(n, n2);
                return;
            }
        }
        if (this.zzyK.zzsH) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzqX.getDefaultDisplay().getMetrics(displayMetrics);
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
            Label_0380: {
                if (mode2 == Integer.MIN_VALUE) {
                    break Label_0380;
                }
                n2 = n3;
                if (mode2 == 1073741824) {
                    break Label_0380;
                }
                if (this.zzyK.widthPixels > n || this.zzyK.heightPixels > n2) {
                    final float density = this.zzJk.getResources().getDisplayMetrics().density;
                    zzb.zzaE("Not enough space to show ad. Needs " + (int)(this.zzyK.widthPixels / density) + "x" + (int)(this.zzyK.heightPixels / density) + " dp, but only has " + (int)(size / density) + "x" + (int)(size2 / density) + " dp.");
                    if (this.getVisibility() != 8) {
                        this.setVisibility(4);
                    }
                    this.setMeasuredDimension(0, 0);
                }
                else {
                    if (this.getVisibility() != 8) {
                        this.setVisibility(0);
                    }
                    this.setMeasuredDimension(this.zzyK.widthPixels, this.zzyK.heightPixels);
                }
                return;
            }
            n2 = size2;
            continue;
        }
    }
    
    public void onPause() {
        if (this.isDestroyed()) {
            return;
        }
        try {
            super.onPause();
        }
        catch (Exception ex) {
            zzb.zzb("Could not pause webview.", ex);
        }
    }
    
    public void onResume() {
        if (this.isDestroyed()) {
            return;
        }
        try {
            super.onResume();
        }
        catch (Exception ex) {
            zzb.zzb("Could not resume webview.", ex);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.zzwh != null) {
            this.zzwh.zza(motionEvent);
        }
        return !this.isDestroyed() && super.onTouchEvent(motionEvent);
    }
    
    public void setContext(final Context baseContext) {
        this.zzJk.setBaseContext(baseContext);
        this.zzqG.zzl(this.zzJk.zzgN());
    }
    
    public void setRequestedOrientation(final int zzJq) {
        synchronized (this.zzpc) {
            this.zzJq = zzJq;
            if (this.zzJl != null) {
                this.zzJl.setRequestedOrientation(this.zzJq);
            }
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
    
    public void zzC(final boolean zzJo) {
        synchronized (this.zzpc) {
            this.zzJo = zzJo;
            this.zzhk();
        }
    }
    
    public void zzD(final boolean zzJm) {
        synchronized (this.zzpc) {
            if (this.zzJl != null) {
                this.zzJl.zza(this.zzCq.zzbY(), zzJm);
            }
            else {
                this.zzJm = zzJm;
            }
        }
    }
    
    public void zzE(final boolean zzJr) {
        synchronized (this.zzpc) {
            this.zzJr = zzJr;
        }
    }
    
    public void zza(final AdSizeParcel zzyK) {
        synchronized (this.zzpc) {
            this.zzyK = zzyK;
            this.requestLayout();
        }
    }
    
    public void zza(final zzd zzJl) {
        synchronized (this.zzpc) {
            this.zzJl = zzJl;
        }
    }
    
    protected void zza(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                this.evaluateJavascript(s, valueCallback);
            }
            else {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
        }
    }
    
    public void zzaF(final String s) {
        synchronized (this.zzpc) {
            super.loadUrl(s);
        }
    }
    
    protected void zzaJ(final String s) {
        synchronized (this.zzpc) {
            if (!this.isDestroyed()) {
                this.loadUrl(s);
            }
            else {
                zzb.zzaE("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    protected void zzaK(final String s) {
        if (!zzlv.zzpV()) {
            this.zzaJ("javascript:" + s);
            return;
        }
        if (this.zzgg() == null) {
            this.zzhj();
        }
        if (this.zzgg()) {
            this.zza(s, null);
            return;
        }
        this.zzaJ("javascript:" + s);
    }
    
    public AdSizeParcel zzaN() {
        synchronized (this.zzpc) {
            return this.zzyK;
        }
    }
    
    public void zzb(final zzd zzJt) {
        synchronized (this.zzpc) {
            this.zzJt = zzJt;
        }
    }
    
    void zzb(final Boolean zzHi) {
        this.zzHi = zzHi;
        zzp.zzbA().zzb(zzHi);
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
        this.zzaK(sb.toString());
    }
    
    public void zzc(final String s, final Map<String, ?> map) {
        try {
            this.zzb(s, zzp.zzbx().zzx(map));
        }
        catch (JSONException ex) {
            zzb.zzaE("Could not convert parameters to JSON.");
        }
    }
    
    public void zzeD() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzpa.zzIz);
        this.zzc("onshow", hashMap);
    }
    
    public void zzgM() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzpa.zzIz);
        this.zzc("onhide", hashMap);
    }
    
    public Activity zzgN() {
        return this.zzJk.zzgN();
    }
    
    public Context zzgO() {
        return this.zzJk.zzgO();
    }
    
    public com.google.android.gms.ads.internal.zzd zzgP() {
        return this.zzov;
    }
    
    public zzd zzgQ() {
        synchronized (this.zzpc) {
            return this.zzJl;
        }
    }
    
    public zzd zzgR() {
        synchronized (this.zzpc) {
            return this.zzJt;
        }
    }
    
    public zziq zzgS() {
        return this.zzCq;
    }
    
    public boolean zzgT() {
        return this.zzJm;
    }
    
    public zzan zzgU() {
        return this.zzwh;
    }
    
    public VersionInfoParcel zzgV() {
        return this.zzpa;
    }
    
    public boolean zzgW() {
        synchronized (this.zzpc) {
            return this.zzJo;
        }
    }
    
    public void zzgX() {
        synchronized (this.zzpc) {
            zzb.v("Destroying WebView!");
            zzhu.zzHK.post((Runnable)new zzis$1(this));
        }
    }
    
    public boolean zzgY() {
        synchronized (this.zzpc) {
            return this.zzJr;
        }
    }
    
    public void zzgZ() {
        this.zzqG.zzgD();
    }
    
    Boolean zzgg() {
        synchronized (this.zzpc) {
            return this.zzHi;
        }
    }
    
    public String zzha() {
        synchronized (this.zzpc) {
            return this.zzJs;
        }
    }
    
    public boolean zzhi() {
        if (this.zzgS().zzbY()) {
            final DisplayMetrics zza = zzp.zzbx().zza(this.zzqX);
            final int zzb = zzk.zzcE().zzb(zza, zza.widthPixels);
            final int zzb2 = zzk.zzcE().zzb(zza, zza.heightPixels);
            final Activity zzgN = this.zzgN();
            int zzb3;
            int zzb4;
            if (zzgN == null || zzgN.getWindow() == null) {
                zzb3 = zzb2;
                zzb4 = zzb;
            }
            else {
                final int[] zzg = zzp.zzbx().zzg(zzgN);
                zzb4 = zzk.zzcE().zzb(zza, zzg[0]);
                zzb3 = zzk.zzcE().zzb(zza, zzg[1]);
            }
            if (this.zzzQ != zzb || this.zzzR != zzb2 || this.zzzT != zzb4 || this.zzzU != zzb3) {
                final boolean b = this.zzzQ != zzb || this.zzzR != zzb2;
                this.zzzQ = zzb;
                this.zzzR = zzb2;
                this.zzzT = zzb4;
                this.zzzU = zzb3;
                new zzfb(this).zza(zzb, zzb2, zzb4, zzb3, zza.density, this.zzqX.getDefaultDisplay().getRotation());
                return b;
            }
        }
        return false;
    }
    
    public void zzv(final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("closetype", String.valueOf(n));
        hashMap.put("version", this.zzpa.zzIz);
        this.zzc("onhide", hashMap);
    }
}
