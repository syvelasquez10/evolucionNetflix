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
import android.webkit.WebView;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;

@zzgr
public interface zziz
{
    Context getContext();
    
    ViewGroup$LayoutParams getLayoutParams();
    
    void getLocationOnScreen(final int[] p0);
    
    int getMeasuredHeight();
    
    int getMeasuredWidth();
    
    ViewParent getParent();
    
    View getView();
    
    WebView getWebView();
    
    boolean isDestroyed();
    
    void loadDataWithBaseURL(final String p0, final String p1, final String p2, final String p3, final String p4);
    
    void loadUrl(final String p0);
    
    void measure(final int p0, final int p1);
    
    void setBackgroundColor(final int p0);
    
    void setContext(final Context p0);
    
    void setOnClickListener(final View$OnClickListener p0);
    
    void setOnTouchListener(final View$OnTouchListener p0);
    
    void setRequestedOrientation(final int p0);
    
    void setWebChromeClient(final WebChromeClient p0);
    
    void setWebViewClient(final WebViewClient p0);
    
    void zzC(final boolean p0);
    
    void zzD(final boolean p0);
    
    void zzE(final boolean p0);
    
    void zza(final AdSizeParcel p0);
    
    void zzaI(final String p0);
    
    AdSizeParcel zzaN();
    
    void zzb(final zzd p0);
    
    void zzb(final String p0, final Map<String, ?> p1);
    
    void zzb(final String p0, final JSONObject p1);
    
    void zzc(final zzd p0);
    
    void zzeJ();
    
    void zzgY();
    
    Activity zzgZ();
    
    Context zzha();
    
    com.google.android.gms.ads.internal.zzd zzhb();
    
    zzd zzhc();
    
    zzd zzhd();
    
    zzja zzhe();
    
    boolean zzhf();
    
    zzan zzhg();
    
    VersionInfoParcel zzhh();
    
    boolean zzhi();
    
    void zzhj();
    
    boolean zzhk();
    
    zziy zzhl();
    
    zzce zzhm();
    
    zzcf zzhn();
    
    void zzho();
    
    void zzhp();
    
    void zzv(final int p0);
}
