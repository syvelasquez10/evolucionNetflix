// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.app.Activity;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import android.content.Context;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@zzgr
class zzjc extends FrameLayout implements zziz
{
    private final zziz zzKk;
    private final zziy zzKl;
    
    public zzjc(final zziz zzKk) {
        super(zzKk.zzha());
        this.zzKk = zzKk;
        this.zzKl = new zziy(zzKk.zzha(), (ViewGroup)this, this);
        final zzja zzhe = this.zzKk.zzhe();
        if (zzhe != null) {
            zzhe.zze(this);
        }
        this.addView(this.zzKk.getView());
    }
    
    public View getView() {
        return (View)this;
    }
    
    public WebView getWebView() {
        return this.zzKk.getWebView();
    }
    
    public boolean isDestroyed() {
        return this.zzKk.isDestroyed();
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.zzKk.loadDataWithBaseURL(s, s2, s3, s4, s5);
    }
    
    public void loadUrl(final String s) {
        this.zzKk.loadUrl(s);
    }
    
    public void setBackgroundColor(final int backgroundColor) {
        this.zzKk.setBackgroundColor(backgroundColor);
    }
    
    public void setContext(final Context context) {
        this.zzKk.setContext(context);
    }
    
    public void setOnClickListener(final View$OnClickListener onClickListener) {
        this.zzKk.setOnClickListener(onClickListener);
    }
    
    public void setOnTouchListener(final View$OnTouchListener onTouchListener) {
        this.zzKk.setOnTouchListener(onTouchListener);
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.zzKk.setRequestedOrientation(requestedOrientation);
    }
    
    public void setWebChromeClient(final WebChromeClient webChromeClient) {
        this.zzKk.setWebChromeClient(webChromeClient);
    }
    
    public void setWebViewClient(final WebViewClient webViewClient) {
        this.zzKk.setWebViewClient(webViewClient);
    }
    
    public void zzC(final boolean b) {
        this.zzKk.zzC(b);
    }
    
    public void zzD(final boolean b) {
        this.zzKk.zzD(b);
    }
    
    public void zzE(final boolean b) {
        this.zzKk.zzE(b);
    }
    
    public void zza(final AdSizeParcel adSizeParcel) {
        this.zzKk.zza(adSizeParcel);
    }
    
    public void zzaI(final String s) {
        this.zzKk.zzaI(s);
    }
    
    public AdSizeParcel zzaN() {
        return this.zzKk.zzaN();
    }
    
    public void zzb(final zzd zzd) {
        this.zzKk.zzb(zzd);
    }
    
    public void zzb(final String s, final Map<String, ?> map) {
        this.zzKk.zzb(s, map);
    }
    
    public void zzb(final String s, final JSONObject jsonObject) {
        this.zzKk.zzb(s, jsonObject);
    }
    
    public void zzc(final zzd zzd) {
        this.zzKk.zzc(zzd);
    }
    
    public void zzeJ() {
        this.zzKk.zzeJ();
    }
    
    public void zzgY() {
        this.zzKk.zzgY();
    }
    
    public Activity zzgZ() {
        return this.zzKk.zzgZ();
    }
    
    public Context zzha() {
        return this.zzKk.zzha();
    }
    
    public com.google.android.gms.ads.internal.zzd zzhb() {
        return this.zzKk.zzhb();
    }
    
    public zzd zzhc() {
        return this.zzKk.zzhc();
    }
    
    public zzd zzhd() {
        return this.zzKk.zzhd();
    }
    
    public zzja zzhe() {
        return this.zzKk.zzhe();
    }
    
    public boolean zzhf() {
        return this.zzKk.zzhf();
    }
    
    public zzan zzhg() {
        return this.zzKk.zzhg();
    }
    
    public VersionInfoParcel zzhh() {
        return this.zzKk.zzhh();
    }
    
    public boolean zzhi() {
        return this.zzKk.zzhi();
    }
    
    public void zzhj() {
        this.zzKl.onDestroy();
        this.zzKk.zzhj();
    }
    
    public boolean zzhk() {
        return this.zzKk.zzhk();
    }
    
    public zziy zzhl() {
        return this.zzKl;
    }
    
    public zzce zzhm() {
        return this.zzKk.zzhm();
    }
    
    public zzcf zzhn() {
        return this.zzKk.zzhn();
    }
    
    public void zzho() {
        this.zzKk.zzho();
    }
    
    public void zzhp() {
        this.zzKk.zzhp();
    }
    
    public void zzv(final int n) {
        this.zzKk.zzv(n);
    }
}
