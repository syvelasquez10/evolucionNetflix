// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup;
import com.google.android.gms.internal.zzja$zza;
import com.google.android.gms.internal.zzfi;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzan;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.util.Collections;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import android.content.Context;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import android.graphics.Color;
import com.google.android.gms.internal.zziz;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.app.Activity;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzfk$zza;

@zzgr
public class zzd extends zzfk$zza implements zzo
{
    static final int zzBh;
    private final Activity mActivity;
    RelativeLayout zzAn;
    AdOverlayInfoParcel zzBi;
    zzd$zzc zzBj;
    zzm zzBk;
    boolean zzBl;
    FrameLayout zzBm;
    WebChromeClient$CustomViewCallback zzBn;
    boolean zzBo;
    boolean zzBp;
    boolean zzBq;
    int zzBr;
    private boolean zzBs;
    private boolean zzBt;
    private boolean zzBu;
    zziz zzoM;
    
    static {
        zzBh = Color.argb(0, 0, 0, 0);
    }
    
    public zzd(final Activity mActivity) {
        this.zzBl = false;
        this.zzBo = false;
        this.zzBp = false;
        this.zzBq = false;
        this.zzBr = 0;
        this.zzBt = false;
        this.zzBu = true;
        this.mActivity = mActivity;
    }
    
    public void close() {
        this.zzBr = 2;
        this.mActivity.finish();
    }
    
    public void onBackPressed() {
        this.zzBr = 0;
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzBo = boolean1;
        Label_0071: {
            try {
                this.zzBi = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
                if (this.zzBi == null) {
                    throw new zzd$zza("Could not get info for ad overlay.");
                }
                break Label_0071;
            }
            catch (zzd$zza zzd$zza) {
                zzb.zzaH(zzd$zza.getMessage());
                this.zzBr = 3;
                this.mActivity.finish();
            }
            return;
        }
        if (this.zzBi.zzqj.zzJw > 7500000) {
            this.zzBr = 3;
        }
        if (this.mActivity.getIntent() != null) {
            this.zzBu = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
        }
        if (this.zzBi.zzBM != null) {
            this.zzBp = this.zzBi.zzBM.zzpt;
        }
        else {
            this.zzBp = false;
        }
        if (zzby.zzvz.get() && this.zzBp && this.zzBi.zzBM.zzpv != null) {
            new zzd$zzd(this, null).zzgz();
        }
        if (bundle == null) {
            if (this.zzBi.zzBC != null && this.zzBu) {
                this.zzBi.zzBC.zzaW();
            }
            if (this.zzBi.zzBJ != 1 && this.zzBi.zzBB != null) {
                this.zzBi.zzBB.onAdClicked();
            }
        }
        this.zzAn = new zzd$zzb((Context)this.mActivity, this.zzBi.zzBL);
        switch (this.zzBi.zzBJ) {
            case 1: {
                this.zzv(false);
            }
            case 2: {
                this.zzBj = new zzd$zzc(this.zzBi.zzBD);
                this.zzv(false);
            }
            case 3: {
                this.zzv(true);
            }
            case 4: {
                if (this.zzBo) {
                    this.zzBr = 3;
                    this.mActivity.finish();
                    return;
                }
                if (!zzp.zzbs().zza((Context)this.mActivity, this.zzBi.zzBA, this.zzBi.zzBI)) {
                    this.zzBr = 3;
                    this.mActivity.finish();
                }
            }
            default: {
                throw new zzd$zza("Could not determine ad overlay type.");
            }
        }
    }
    
    public void onDestroy() {
        if (this.zzoM != null) {
            this.zzAn.removeView(this.zzoM.getView());
        }
        this.zzeH();
    }
    
    public void onPause() {
        this.zzeD();
        if (this.zzoM != null && (!this.mActivity.isFinishing() || this.zzBj == null)) {
            zzp.zzbx().zza(this.zzoM.getWebView());
        }
        this.zzeH();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.zzBi != null && this.zzBi.zzBJ == 4) {
            if (this.zzBo) {
                this.zzBr = 3;
                this.mActivity.finish();
            }
            else {
                this.zzBo = true;
            }
        }
        if (this.zzoM != null && !this.zzoM.isDestroyed()) {
            zzp.zzbx().zzb(this.zzoM.getWebView());
            return;
        }
        zzb.zzaH("The webview does not exit. Ignoring action.");
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzBo);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.zzeH();
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.mActivity.setRequestedOrientation(requestedOrientation);
    }
    
    public void zza(final View view, final WebChromeClient$CustomViewCallback zzBn) {
        (this.zzBm = new FrameLayout((Context)this.mActivity)).setBackgroundColor(-16777216);
        this.zzBm.addView(view, -1, -1);
        this.mActivity.setContentView((View)this.zzBm);
        this.zzaE();
        this.zzBn = zzBn;
        this.zzBl = true;
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (this.zzBk != null) {
            this.zzBk.zza(b, b2);
        }
    }
    
    public void zzaE() {
        this.zzBs = true;
    }
    
    public void zzeD() {
        if (this.zzBi != null && this.zzBl) {
            this.setRequestedOrientation(this.zzBi.orientation);
        }
        if (this.zzBm != null) {
            this.mActivity.setContentView((View)this.zzAn);
            this.zzaE();
            this.zzBm.removeAllViews();
            this.zzBm = null;
        }
        if (this.zzBn != null) {
            this.zzBn.onCustomViewHidden();
            this.zzBn = null;
        }
        this.zzBl = false;
    }
    
    @Override
    public void zzeE() {
        this.zzBr = 1;
        this.mActivity.finish();
    }
    
    public boolean zzeF() {
        this.zzBr = 0;
        boolean zzhk;
        if (this.zzoM == null) {
            zzhk = true;
        }
        else {
            final boolean b = zzhk = this.zzoM.zzhk();
            if (!b) {
                this.zzoM.zzb("onbackblocked", Collections.emptyMap());
                return b;
            }
        }
        return zzhk;
    }
    
    public void zzeG() {
        this.zzAn.removeView((View)this.zzBk);
        this.zzu(true);
    }
    
    protected void zzeH() {
        if (this.mActivity.isFinishing() && !this.zzBt) {
            this.zzBt = true;
            if (this.zzoM != null) {
                this.zzv(this.zzBr);
                this.zzAn.removeView(this.zzoM.getView());
                if (this.zzBj != null) {
                    this.zzoM.setContext(this.zzBj.context);
                    this.zzoM.zzC(false);
                    this.zzBj.zzBx.addView(this.zzoM.getView(), this.zzBj.index, this.zzBj.zzBw);
                    this.zzBj = null;
                }
                this.zzoM = null;
            }
            if (this.zzBi != null && this.zzBi.zzBC != null) {
                this.zzBi.zzBC.zzaV();
            }
        }
    }
    
    public void zzeI() {
        if (this.zzBq) {
            this.zzBq = false;
            this.zzeJ();
        }
    }
    
    protected void zzeJ() {
        this.zzoM.zzeJ();
    }
    
    public void zzu(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.zzBk = new zzm((Context)this.mActivity, n, this);
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
        this.zzBk.zza(b, this.zzBi.zzBG);
        this.zzAn.addView((View)this.zzBk, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    protected void zzv(final int n) {
        this.zzoM.zzv(n);
    }
    
    protected void zzv(final boolean b) {
        if (!this.zzBs) {
            this.mActivity.requestWindowFeature(1);
        }
        final Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zzd$zza("Invalid activity, no window available.");
        }
        if (!this.zzBp || (this.zzBi.zzBM != null && this.zzBi.zzBM.zzpu)) {
            window.setFlags(1024, 1024);
        }
        final boolean zzbY = this.zzBi.zzBD.zzhe().zzbY();
        this.zzBq = false;
        if (zzbY) {
            if (this.zzBi.orientation == zzp.zzbx().zzgG()) {
                this.zzBq = (this.mActivity.getResources().getConfiguration().orientation == 1);
            }
            else if (this.zzBi.orientation == zzp.zzbx().zzgH()) {
                this.zzBq = (this.mActivity.getResources().getConfiguration().orientation == 2);
            }
        }
        zzb.zzaF("Delay onShow to next orientation change: " + this.zzBq);
        this.setRequestedOrientation(this.zzBi.orientation);
        if (zzp.zzbx().zza(window)) {
            zzb.zzaF("Hardware acceleration on the AdActivity window enabled.");
        }
        if (!this.zzBp) {
            this.zzAn.setBackgroundColor(-16777216);
        }
        else {
            this.zzAn.setBackgroundColor(zzd.zzBh);
        }
        this.mActivity.setContentView((View)this.zzAn);
        this.zzaE();
        if (b) {
            this.zzoM = zzp.zzbw().zza((Context)this.mActivity, this.zzBi.zzBD.zzaN(), true, zzbY, null, this.zzBi.zzqj);
            this.zzoM.zzhe().zzb(null, null, this.zzBi.zzBE, this.zzBi.zzBI, true, this.zzBi.zzBK, null, this.zzBi.zzBD.zzhe().zzhq(), null);
            this.zzoM.zzhe().zza(new zzd$1(this));
            if (this.zzBi.url != null) {
                this.zzoM.loadUrl(this.zzBi.url);
            }
            else {
                if (this.zzBi.zzBH == null) {
                    throw new zzd$zza("No URL or HTML to display in ad overlay.");
                }
                this.zzoM.loadDataWithBaseURL(this.zzBi.zzBF, this.zzBi.zzBH, "text/html", "UTF-8", null);
            }
            if (this.zzBi.zzBD != null) {
                this.zzBi.zzBD.zzc(this);
            }
        }
        else {
            (this.zzoM = this.zzBi.zzBD).setContext((Context)this.mActivity);
        }
        this.zzoM.zzb(this);
        final ViewParent parent = this.zzoM.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView(this.zzoM.getView());
        }
        if (this.zzBp) {
            this.zzoM.setBackgroundColor(zzd.zzBh);
        }
        this.zzAn.addView(this.zzoM.getView(), -1, -1);
        if (!b && !this.zzBq) {
            this.zzeJ();
        }
        this.zzu(zzbY);
        if (this.zzoM.zzhf()) {
            this.zza(zzbY, true);
        }
    }
}
