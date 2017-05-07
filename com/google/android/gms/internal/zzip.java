// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.app.Activity;
import java.util.Map;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.webkit.WebView;
import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;

@zzgk
public interface zzip
{
    Context getContext();
    
    ViewGroup$LayoutParams getLayoutParams();
    
    void getLocationOnScreen(final int[] p0);
    
    int getMeasuredHeight();
    
    int getMeasuredWidth();
    
    ViewParent getParent();
    
    WebView getWebView();
    
    boolean isDestroyed();
    
    void loadDataWithBaseURL(final String p0, final String p1, final String p2, final String p3, final String p4);
    
    void loadUrl(final String p0);
    
    void measure(final int p0, final int p1);
    
    void setBackgroundColor(final int p0);
    
    void setContext(final Context p0);
    
    void setRequestedOrientation(final int p0);
    
    boolean willNotDraw();
    
    void zzC(final boolean p0);
    
    void zzD(final boolean p0);
    
    void zzE(final boolean p0);
    
    void zza(final AdSizeParcel p0);
    
    void zza(final zzd p0);
    
    void zzaF(final String p0);
    
    AdSizeParcel zzaN();
    
    void zzb(final zzd p0);
    
    void zzb(final String p0, final JSONObject p1);
    
    void zzc(final String p0, final Map<String, ?> p1);
    
    void zzeD();
    
    void zzgM();
    
    Activity zzgN();
    
    Context zzgO();
    
    com.google.android.gms.ads.internal.zzd zzgP();
    
    zzd zzgQ();
    
    zzd zzgR();
    
    zziq zzgS();
    
    boolean zzgT();
    
    zzan zzgU();
    
    VersionInfoParcel zzgV();
    
    boolean zzgW();
    
    void zzgX();
    
    boolean zzgY();
    
    void zzgZ();
    
    String zzha();
    
    void zzv(final int p0);
}
