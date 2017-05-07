// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import android.app.Activity;
import java.util.Map;
import com.google.android.gms.ads.internal.client.zzk;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;

@zzgk
public class zzfa extends zzfb implements zzdg
{
    private final Context mContext;
    private final zzip zzoL;
    private final WindowManager zzqX;
    private final zzbq zzzN;
    DisplayMetrics zzzO;
    private float zzzP;
    int zzzQ;
    int zzzR;
    private int zzzS;
    int zzzT;
    int zzzU;
    int zzzV;
    int zzzW;
    
    public zzfa(final zzip zzoL, final Context mContext, final zzbq zzzN) {
        super(zzoL);
        this.zzzQ = -1;
        this.zzzR = -1;
        this.zzzT = -1;
        this.zzzU = -1;
        this.zzzV = -1;
        this.zzzW = -1;
        this.zzoL = zzoL;
        this.mContext = mContext;
        this.zzzN = zzzN;
        this.zzqX = (WindowManager)mContext.getSystemService("window");
    }
    
    private void zzeb() {
        this.zzzO = new DisplayMetrics();
        final Display defaultDisplay = this.zzqX.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzzO);
        this.zzzP = this.zzzO.density;
        this.zzzS = defaultDisplay.getRotation();
    }
    
    private void zzeg() {
        final int[] array = new int[2];
        this.zzoL.getLocationOnScreen(array);
        this.zze(zzk.zzcE().zzc(this.mContext, array[0]), zzk.zzcE().zzc(this.mContext, array[1]));
    }
    
    private zzez zzej() {
        return new zzez$zza().zzp(this.zzzN.zzcV()).zzo(this.zzzN.zzcW()).zzq(this.zzzN.zzda()).zzr(this.zzzN.zzcX()).zzs(this.zzzN.zzcY()).zzea();
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        this.zzee();
    }
    
    public void zze(final int n, final int n2) {
        int n3;
        if (this.mContext instanceof Activity) {
            n3 = zzp.zzbx().zzj((Activity)this.mContext)[0];
        }
        else {
            n3 = 0;
        }
        this.zzc(n, n2 - n3, this.zzzV, this.zzzW);
        this.zzoL.zzgS().zzd(n, n2);
    }
    
    void zzec() {
        this.zzzQ = zzk.zzcE().zzb(this.zzzO, this.zzzO.widthPixels);
        this.zzzR = zzk.zzcE().zzb(this.zzzO, this.zzzO.heightPixels);
        final Activity zzgN = this.zzoL.zzgN();
        if (zzgN == null || zzgN.getWindow() == null) {
            this.zzzT = this.zzzQ;
            this.zzzU = this.zzzR;
            return;
        }
        final int[] zzg = zzp.zzbx().zzg(zzgN);
        this.zzzT = zzk.zzcE().zzb(this.zzzO, zzg[0]);
        this.zzzU = zzk.zzcE().zzb(this.zzzO, zzg[1]);
    }
    
    void zzed() {
        if (this.zzoL.zzaN().zzsH) {
            this.zzzV = this.zzzQ;
            this.zzzW = this.zzzR;
            return;
        }
        this.zzoL.measure(0, 0);
        this.zzzV = zzk.zzcE().zzc(this.mContext, this.zzoL.getMeasuredWidth());
        this.zzzW = zzk.zzcE().zzc(this.mContext, this.zzoL.getMeasuredHeight());
    }
    
    public void zzee() {
        this.zzeb();
        this.zzec();
        this.zzed();
        this.zzeh();
        this.zzei();
        this.zzeg();
        this.zzef();
    }
    
    void zzef() {
        if (zzb.zzM(2)) {
            zzb.zzaD("Dispatching Ready Event.");
        }
        this.zzai(this.zzoL.zzgV().zzIz);
    }
    
    void zzeh() {
        this.zza(this.zzzQ, this.zzzR, this.zzzT, this.zzzU, this.zzzP, this.zzzS);
    }
    
    void zzei() {
        this.zzoL.zzb("onDeviceFeaturesReceived", this.zzej().toJson());
    }
}
