// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup;
import com.google.android.gms.internal.zziq$zza;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzan;
import android.widget.RelativeLayout$LayoutParams;
import java.util.Collections;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import com.google.android.gms.internal.zzby;
import android.graphics.Color;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.zzip;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzcd;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.app.Activity;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.internal.zzfe$zza;

@zzgk
public class zzd extends zzfe$zza implements zzo
{
    static final int zzAu;
    private final Activity mActivity;
    zzm zzAA;
    boolean zzAB;
    FrameLayout zzAC;
    WebChromeClient$CustomViewCallback zzAD;
    boolean zzAE;
    boolean zzAF;
    boolean zzAG;
    int zzAH;
    private boolean zzAI;
    private boolean zzAJ;
    private boolean zzAK;
    private final zzcd zzAv;
    private final zzcc zzAw;
    AdOverlayInfoParcel zzAx;
    zzk zzAy;
    zzd$zzc zzAz;
    zzip zzoL;
    RelativeLayout zzzA;
    
    static {
        zzAu = Color.argb(0, 0, 0, 0);
    }
    
    public zzd(final Activity mActivity) {
        this.zzAB = false;
        this.zzAE = false;
        this.zzAF = false;
        this.zzAG = false;
        this.zzAH = 0;
        this.zzAJ = false;
        this.zzAK = true;
        this.mActivity = mActivity;
        this.zzAv = new zzcd(zzby.zzuB.get(), "show_interstitial", "interstitial");
        this.zzAw = this.zzAv.zzdl();
    }
    
    public void close() {
        this.zzAH = 2;
        this.mActivity.finish();
    }
    
    public void onBackPressed() {
        this.zzAH = 0;
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzAE = boolean1;
        Label_0071: {
            try {
                this.zzAx = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
                if (this.zzAx == null) {
                    throw new zzd$zza("Could not get info for ad overlay.");
                }
                break Label_0071;
            }
            catch (zzd$zza zzd$zza) {
                zzb.zzaE(zzd$zza.getMessage());
                this.zzAH = 3;
                this.mActivity.finish();
            }
            return;
        }
        if (this.zzAx.zzqb.zzIB > 7500000) {
            this.zzAH = 3;
        }
        if (this.mActivity.getIntent() != null) {
            this.zzAK = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
        }
        if (this.zzAx.zzBa != null) {
            this.zzAF = this.zzAx.zzBa.zzpk;
        }
        else {
            this.zzAF = false;
        }
        if (bundle == null) {
            if (this.zzAx.zzAQ != null && this.zzAK) {
                this.zzAx.zzAQ.zzaW();
            }
            if (this.zzAx.zzAX != 1 && this.zzAx.zzAP != null) {
                this.zzAx.zzAP.onAdClicked();
            }
        }
        this.zzzA = new zzd$zzb((Context)this.mActivity, this.zzAx.zzAZ);
        switch (this.zzAx.zzAX) {
            case 1: {
                this.zzv(false);
            }
            case 2: {
                this.zzAz = new zzd$zzc(this.zzAx.zzAR);
                this.zzv(false);
            }
            case 3: {
                this.zzv(true);
            }
            case 4: {
                if (this.zzAE) {
                    this.zzAH = 3;
                    this.mActivity.finish();
                    return;
                }
                if (!zzp.zzbu().zza((Context)this.mActivity, this.zzAx.zzAO, this.zzAx.zzAW)) {
                    this.zzAH = 3;
                    this.mActivity.finish();
                }
            }
            default: {
                throw new zzd$zza("Could not determine ad overlay type.");
            }
        }
    }
    
    public void onDestroy() {
        if (this.zzAy != null) {
            this.zzAy.destroy();
        }
        if (this.zzoL != null) {
            this.zzzA.removeView((View)this.zzoL.getWebView());
        }
        this.zzeB();
    }
    
    public void onPause() {
        if (this.zzAy != null) {
            this.zzAy.pause();
        }
        this.zzex();
        if (this.zzoL != null && (!this.mActivity.isFinishing() || this.zzAz == null)) {
            zzp.zzbz().zza(this.zzoL.getWebView());
        }
        this.zzeB();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.zzAx != null && this.zzAx.zzAX == 4) {
            if (this.zzAE) {
                this.zzAH = 3;
                this.mActivity.finish();
            }
            else {
                this.zzAE = true;
            }
        }
        if (this.zzoL != null && !this.zzoL.isDestroyed()) {
            zzp.zzbz().zzb(this.zzoL.getWebView());
            return;
        }
        zzb.zzaE("The webview does not exit. Ignoring action.");
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzAE);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.zzeB();
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.mActivity.setRequestedOrientation(requestedOrientation);
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.zzAv.zza(this.zzAw, "vpr");
        final zzcc zzdl = this.zzAv.zzdl();
        if (this.zzAy == null) {
            this.zzAy = new zzk((Context)this.mActivity, this.zzoL, n5, this.zzAv, zzdl);
            this.zzzA.addView((View)this.zzAy, 0, new ViewGroup$LayoutParams(-1, -1));
            this.zzAy.zze(n, n2, n3, n4);
            this.zzoL.zzgS().zzF(false);
        }
    }
    
    public void zza(final View view, final WebChromeClient$CustomViewCallback zzAD) {
        (this.zzAC = new FrameLayout((Context)this.mActivity)).setBackgroundColor(-16777216);
        this.zzAC.addView(view, -1, -1);
        this.mActivity.setContentView((View)this.zzAC);
        this.zzaE();
        this.zzAD = zzAD;
        this.zzAB = true;
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (this.zzAA != null) {
            this.zzAA.zza(b, b2);
        }
    }
    
    public void zzaE() {
        this.zzAI = true;
    }
    
    public void zzd(final int n, final int n2, final int n3, final int n4) {
        if (this.zzAy != null) {
            this.zzAy.zze(n, n2, n3, n4);
        }
    }
    
    public void zzeA() {
        this.zzzA.removeView((View)this.zzAA);
        this.zzu(true);
    }
    
    protected void zzeB() {
        if (this.mActivity.isFinishing() && !this.zzAJ) {
            this.zzAJ = true;
            if (zzp.zzbA().zzgc() != null) {
                zzp.zzbA().zzgc().zza(this.zzAv);
            }
            if (this.zzoL != null) {
                this.zzv(this.zzAH);
                this.zzzA.removeView((View)this.zzoL.getWebView());
                if (this.zzAz != null) {
                    this.zzoL.setContext(this.zzAz.context);
                    this.zzoL.zzC(false);
                    this.zzAz.zzAN.addView((View)this.zzoL.getWebView(), this.zzAz.index, this.zzAz.zzAM);
                    this.zzAz = null;
                }
                this.zzoL = null;
            }
            if (this.zzAx != null && this.zzAx.zzAQ != null) {
                this.zzAx.zzAQ.zzaV();
            }
        }
    }
    
    public void zzeC() {
        if (this.zzAG) {
            this.zzAG = false;
            this.zzeD();
        }
    }
    
    protected void zzeD() {
        this.zzoL.zzeD();
    }
    
    public zzk zzew() {
        return this.zzAy;
    }
    
    public void zzex() {
        if (this.zzAx != null && this.zzAB) {
            this.setRequestedOrientation(this.zzAx.orientation);
        }
        if (this.zzAC != null) {
            this.mActivity.setContentView((View)this.zzzA);
            this.zzaE();
            this.zzAC.removeAllViews();
            this.zzAC = null;
        }
        if (this.zzAD != null) {
            this.zzAD.onCustomViewHidden();
            this.zzAD = null;
        }
        this.zzAB = false;
    }
    
    @Override
    public void zzey() {
        this.zzAH = 1;
        this.mActivity.finish();
    }
    
    public boolean zzez() {
        this.zzAH = 0;
        boolean zzgY;
        if (this.zzoL == null) {
            zzgY = true;
        }
        else {
            final boolean b = zzgY = this.zzoL.zzgY();
            if (!b) {
                this.zzoL.zzc("onbackblocked", Collections.emptyMap());
                return b;
            }
        }
        return zzgY;
    }
    
    public void zzu(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.zzAA = new zzm((Context)this.mActivity, n, this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.addRule(10);
        int n2;
        if (b) {
            n2 = 11;
        }
        else {
            n2 = 9;
        }
        relativeLayout$LayoutParams.addRule(n2);
        this.zzAA.zza(b, this.zzAx.zzAU);
        this.zzzA.addView((View)this.zzAA, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    protected void zzv(final int n) {
        this.zzoL.zzv(n);
    }
    
    protected void zzv(final boolean b) {
        if (!this.zzAI) {
            this.mActivity.requestWindowFeature(1);
        }
        final Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zzd$zza("Invalid activity, no window available.");
        }
        if (!this.zzAF || (this.zzAx.zzBa != null && this.zzAx.zzBa.zzpl)) {
            window.setFlags(1024, 1024);
        }
        final boolean zzbY = this.zzAx.zzAR.zzgS().zzbY();
        this.zzAG = false;
        if (zzbY) {
            if (this.zzAx.orientation == zzp.zzbz().zzgv()) {
                this.zzAG = (this.mActivity.getResources().getConfiguration().orientation == 1);
            }
            else if (this.zzAx.orientation == zzp.zzbz().zzgw()) {
                this.zzAG = (this.mActivity.getResources().getConfiguration().orientation == 2);
            }
        }
        zzb.zzaC("Delay onShow to next orientation change: " + this.zzAG);
        this.setRequestedOrientation(this.zzAx.orientation);
        if (zzp.zzbz().zza(window)) {
            zzb.zzaC("Hardware acceleration on the AdActivity window enabled.");
        }
        if (!this.zzAF) {
            this.zzzA.setBackgroundColor(-16777216);
        }
        else {
            this.zzzA.setBackgroundColor(zzd.zzAu);
        }
        this.mActivity.setContentView((View)this.zzzA);
        this.zzaE();
        if (b) {
            this.zzoL = zzp.zzby().zza((Context)this.mActivity, this.zzAx.zzAR.zzaN(), true, zzbY, null, this.zzAx.zzqb);
            this.zzoL.zzgS().zzb(null, null, this.zzAx.zzAS, this.zzAx.zzAW, true, this.zzAx.zzAY, null, this.zzAx.zzAR.zzgS().zzhb(), null);
            this.zzoL.zzgS().zza(new zzd$1(this));
            if (this.zzAx.url != null) {
                this.zzoL.loadUrl(this.zzAx.url);
            }
            else {
                if (this.zzAx.zzAV == null) {
                    throw new zzd$zza("No URL or HTML to display in ad overlay.");
                }
                this.zzoL.loadDataWithBaseURL(this.zzAx.zzAT, this.zzAx.zzAV, "text/html", "UTF-8", null);
            }
            if (this.zzAx.zzAR != null) {
                this.zzAx.zzAR.zzb(this);
            }
        }
        else {
            (this.zzoL = this.zzAx.zzAR).setContext((Context)this.mActivity);
        }
        this.zzoL.zza(this);
        final ViewParent parent = this.zzoL.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this.zzoL.getWebView());
        }
        if (this.zzAF) {
            this.zzoL.setBackgroundColor(zzd.zzAu);
        }
        this.zzzA.addView((View)this.zzoL.getWebView(), -1, -1);
        if (!b && !this.zzAG) {
            this.zzeD();
        }
        this.zzu(zzbY);
        if (this.zzoL.zzgT()) {
            this.zza(zzbY, true);
        }
    }
}
