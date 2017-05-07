// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.analytics.internal.zzk;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzx;
import java.util.Random;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.internal.zzok;
import com.google.android.gms.analytics.internal.zza;
import com.google.android.gms.analytics.internal.zzh;
import android.text.TextUtils;
import java.util.HashMap;
import com.google.android.gms.analytics.internal.zzc;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzam;
import java.util.Map;

class Tracker$1 implements Runnable
{
    final /* synthetic */ Map zzKN;
    final /* synthetic */ boolean zzKO;
    final /* synthetic */ String zzKP;
    final /* synthetic */ long zzKQ;
    final /* synthetic */ boolean zzKR;
    final /* synthetic */ boolean zzKS;
    final /* synthetic */ String zzKT;
    final /* synthetic */ Tracker zzKU;
    
    Tracker$1(final Tracker zzKU, final Map zzKN, final boolean zzKO, final String zzKP, final long zzKQ, final boolean zzKR, final boolean zzKS, final String zzKT) {
        this.zzKU = zzKU;
        this.zzKN = zzKN;
        this.zzKO = zzKO;
        this.zzKP = zzKP;
        this.zzKQ = zzKQ;
        this.zzKR = zzKR;
        this.zzKS = zzKS;
        this.zzKT = zzKT;
    }
    
    @Override
    public void run() {
        boolean b = true;
        if (this.zzKU.zzKK.zzhE()) {
            this.zzKN.put("sc", "start");
        }
        zzam.zzc(this.zzKN, "cid", this.zzKU.zzhu().getClientId());
        final String s = this.zzKN.get("sf");
        if (s != null) {
            final double zza = zzam.zza(s, 100.0);
            if (zzam.zza(zza, this.zzKN.get("cid"))) {
                this.zzKU.zzb("Sampling enabled. Hit sampled out. sample rate", zza);
                return;
            }
        }
        final zza zzb = this.zzKU.zzik();
        if (this.zzKO) {
            zzam.zzb(this.zzKN, "ate", zzb.zzhM());
            zzam.zzb(this.zzKN, "adid", zzb.zzhQ());
        }
        else {
            this.zzKN.remove("ate");
            this.zzKN.remove("adid");
        }
        final zzok zziL = this.zzKU.zzil().zziL();
        zzam.zzb(this.zzKN, "an", zziL.zzjZ());
        zzam.zzb(this.zzKN, "av", zziL.zzkb());
        zzam.zzb(this.zzKN, "aid", zziL.zztW());
        zzam.zzb(this.zzKN, "aiid", zziL.zzxA());
        this.zzKN.put("v", "1");
        this.zzKN.put("_v", zze.zzLB);
        zzam.zzb(this.zzKN, "ul", this.zzKU.zzim().zzjS().getLanguage());
        zzam.zzb(this.zzKN, "sr", this.zzKU.zzim().zzjT());
        boolean b2;
        if (this.zzKP.equals("transaction") || this.zzKP.equals("item")) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (!b2 && !this.zzKU.zzKJ.zzkp()) {
            this.zzKU.zzie().zzh(this.zzKN, "Too many hits sent too quickly, rate limiting invoked");
            return;
        }
        long n;
        if ((n = zzam.zzbo(this.zzKN.get("ht"))) == 0L) {
            n = this.zzKQ;
        }
        if (this.zzKR) {
            this.zzKU.zzie().zzc("Dry run enabled. Would have sent hit", new zzab(this.zzKU, this.zzKN, n, this.zzKS));
            return;
        }
        final String s2 = this.zzKN.get("cid");
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        zzam.zza(hashMap, "uid", this.zzKN);
        zzam.zza(hashMap, "an", this.zzKN);
        zzam.zza(hashMap, "aid", this.zzKN);
        zzam.zza(hashMap, "av", this.zzKN);
        zzam.zza(hashMap, "aiid", this.zzKN);
        final String zzKT = this.zzKT;
        if (TextUtils.isEmpty((CharSequence)this.zzKN.get("adid"))) {
            b = false;
        }
        this.zzKN.put("_s", String.valueOf(this.zzKU.zzhz().zza(new zzh(0L, s2, zzKT, b, 0L, hashMap))));
        this.zzKU.zzhz().zza(new zzab(this.zzKU, this.zzKN, n, this.zzKS));
    }
}
