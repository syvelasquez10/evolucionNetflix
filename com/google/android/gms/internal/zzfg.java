// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import android.app.Activity;
import java.util.Map;
import com.google.android.gms.ads.internal.client.zzl;
import android.view.Display;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.content.Context;

@zzgr
public class zzfg extends zzfh implements zzdk
{
    private final Context mContext;
    private final zzbq zzAA;
    DisplayMetrics zzAB;
    private float zzAC;
    int zzAD;
    int zzAE;
    private int zzAF;
    int zzAG;
    int zzAH;
    int zzAI;
    int zzAJ;
    private final zziz zzoM;
    private final WindowManager zzri;
    
    public zzfg(final zziz zzoM, final Context mContext, final zzbq zzAA) {
        super(zzoM);
        this.zzAD = -1;
        this.zzAE = -1;
        this.zzAG = -1;
        this.zzAH = -1;
        this.zzAI = -1;
        this.zzAJ = -1;
        this.zzoM = zzoM;
        this.mContext = mContext;
        this.zzAA = zzAA;
        this.zzri = (WindowManager)mContext.getSystemService("window");
    }
    
    private void zzei() {
        this.zzAB = new DisplayMetrics();
        final Display defaultDisplay = this.zzri.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzAB);
        this.zzAC = this.zzAB.density;
        this.zzAF = defaultDisplay.getRotation();
    }
    
    private void zzen() {
        final int[] array = new int[2];
        this.zzoM.getLocationOnScreen(array);
        this.zze(zzl.zzcF().zzc(this.mContext, array[0]), zzl.zzcF().zzc(this.mContext, array[1]));
    }
    
    private zzff zzeq() {
        return new zzff$zza().zzp(this.zzAA.zzcW()).zzo(this.zzAA.zzcX()).zzq(this.zzAA.zzdb()).zzr(this.zzAA.zzcY()).zzs(this.zzAA.zzcZ()).zzeh();
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        this.zzel();
    }
    
    public void zze(final int n, final int n2) {
        int n3;
        if (this.mContext instanceof Activity) {
            n3 = zzp.zzbv().zzj((Activity)this.mContext)[0];
        }
        else {
            n3 = 0;
        }
        this.zzc(n, n2 - n3, this.zzAI, this.zzAJ);
        this.zzoM.zzhe().zzd(n, n2);
    }
    
    void zzej() {
        this.zzAD = zzl.zzcF().zzb(this.zzAB, this.zzAB.widthPixels);
        this.zzAE = zzl.zzcF().zzb(this.zzAB, this.zzAB.heightPixels);
        final Activity zzgZ = this.zzoM.zzgZ();
        if (zzgZ == null || zzgZ.getWindow() == null) {
            this.zzAG = this.zzAD;
            this.zzAH = this.zzAE;
            return;
        }
        final int[] zzg = zzp.zzbv().zzg(zzgZ);
        this.zzAG = zzl.zzcF().zzb(this.zzAB, zzg[0]);
        this.zzAH = zzl.zzcF().zzb(this.zzAB, zzg[1]);
    }
    
    void zzek() {
        if (this.zzoM.zzaN().zztf) {
            this.zzAI = this.zzAD;
            this.zzAJ = this.zzAE;
            return;
        }
        this.zzoM.measure(0, 0);
        this.zzAI = zzl.zzcF().zzc(this.mContext, this.zzoM.getMeasuredWidth());
        this.zzAJ = zzl.zzcF().zzc(this.mContext, this.zzoM.getMeasuredHeight());
    }
    
    public void zzel() {
        this.zzei();
        this.zzej();
        this.zzek();
        this.zzeo();
        this.zzep();
        this.zzen();
        this.zzem();
    }
    
    void zzem() {
        if (zzb.zzN(2)) {
            zzb.zzaG("Dispatching Ready Event.");
        }
        this.zzal(this.zzoM.zzhh().zzJu);
    }
    
    void zzeo() {
        this.zza(this.zzAD, this.zzAE, this.zzAG, this.zzAH, this.zzAC, this.zzAF);
    }
    
    void zzep() {
        this.zzoM.zzb("onDeviceFeaturesReceived", this.zzeq().toJson());
    }
}
