// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzqp;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import android.content.Context;
import com.google.android.gms.internal.zzqp$zzc;
import com.google.android.gms.internal.zzqp$zze;
import java.util.Set;
import com.google.android.gms.internal.zzqp$zza;
import java.util.Map;
import com.google.android.gms.internal.zzag$zza;

class zzcp
{
    private static final zzbw<zzag$zza> zzaRy;
    private final DataLayer zzaOT;
    private final zzah zzaRA;
    private final Map<String, zzak> zzaRB;
    private final Map<String, zzak> zzaRC;
    private final Map<String, zzak> zzaRD;
    private final zzl<zzqp$zza, zzbw<zzag$zza>> zzaRE;
    private final zzl<String, zzcp$zzb> zzaRF;
    private final Set<zzqp$zze> zzaRG;
    private final Map<String, zzcp$zzc> zzaRH;
    private volatile String zzaRI;
    private int zzaRJ;
    private final zzqp$zzc zzaRz;
    
    static {
        zzaRy = new zzbw<zzag$zza>(zzdf.zzBg(), true);
    }
    
    public zzcp(final Context context, final zzqp$zzc zzaRz, final DataLayer zzaOT, final zzt$zza zzt$zza, final zzt$zza zzt$zza2, final zzah zzaRA) {
        if (zzaRz == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzaRz = zzaRz;
        this.zzaRG = new HashSet<zzqp$zze>(zzaRz.zzBG());
        this.zzaOT = zzaOT;
        this.zzaRA = zzaRA;
        this.zzaRE = new zzm<zzqp$zza, zzbw<zzag$zza>>().zza(1048576, new zzcp$1(this));
        this.zzaRF = new zzm<String, zzcp$zzb>().zza(1048576, new zzcp$2(this));
        this.zzaRB = new HashMap<String, zzak>();
        this.zzb(new zzj(context));
        this.zzb(new zzt(zzt$zza2));
        this.zzb(new zzx(zzaOT));
        this.zzb(new zzdg(context, zzaOT));
        this.zzb(new zzdb(context, zzaOT));
        this.zzaRC = new HashMap<String, zzak>();
        this.zzc(new zzr());
        this.zzc(new zzae());
        this.zzc(new zzaf());
        this.zzc(new zzam());
        this.zzc(new zzan());
        this.zzc(new zzbc());
        this.zzc(new zzbd());
        this.zzc(new zzcf());
        this.zzc(new zzcy());
        this.zzaRD = new HashMap<String, zzak>();
        this.zza(new zzb(context));
        this.zza(new zzc(context));
        this.zza(new zze(context));
        this.zza(new zzf(context));
        this.zza(new zzg(context));
        this.zza(new zzh(context));
        this.zza(new zzi(context));
        this.zza(new zzn());
        this.zza(new zzq(this.zzaRz.getVersion()));
        this.zza(new zzt(zzt$zza));
        this.zza(new zzv(zzaOT));
        this.zza(new zzaa(context));
        this.zza(new zzab());
        this.zza(new zzad());
        this.zza(new zzai(this));
        this.zza(new zzao());
        this.zza(new zzap());
        this.zza(new zzaw(context));
        this.zza(new zzay());
        this.zza(new zzbb());
        this.zza(new zzbi());
        this.zza(new zzbk(context));
        this.zza(new zzbx());
        this.zza(new zzbz());
        this.zza(new zzcc());
        this.zza(new zzce());
        this.zza(new zzcg(context));
        this.zza(new zzcq());
        this.zza(new zzcr());
        this.zza(new zzda());
        this.zza(new zzdh());
        this.zzaRH = new HashMap<String, zzcp$zzc>();
        for (final zzqp$zze zzqp$zze : this.zzaRG) {
            if (zzaRA.zzAb()) {
                zza(zzqp$zze.zzBO(), zzqp$zze.zzBP(), "add macro");
                zza(zzqp$zze.zzBT(), zzqp$zze.zzBQ(), "remove macro");
                zza(zzqp$zze.zzBM(), zzqp$zze.zzBR(), "add tag");
                zza(zzqp$zze.zzBN(), zzqp$zze.zzBS(), "remove tag");
            }
            for (int i = 0; i < zzqp$zze.zzBO().size(); ++i) {
                final zzqp$zza zzqp$zza = zzqp$zze.zzBO().get(i);
                String s = "Unknown";
                if (zzaRA.zzAb()) {
                    s = s;
                    if (i < zzqp$zze.zzBP().size()) {
                        s = zzqp$zze.zzBP().get(i);
                    }
                }
                final zzcp$zzc zzi = zzi(this.zzaRH, zza(zzqp$zza));
                zzi.zza(zzqp$zze);
                zzi.zza(zzqp$zze, zzqp$zza);
                zzi.zza(zzqp$zze, s);
            }
            for (int j = 0; j < zzqp$zze.zzBT().size(); ++j) {
                final zzqp$zza zzqp$zza2 = zzqp$zze.zzBT().get(j);
                String s2 = "Unknown";
                if (zzaRA.zzAb()) {
                    s2 = s2;
                    if (j < zzqp$zze.zzBQ().size()) {
                        s2 = zzqp$zze.zzBQ().get(j);
                    }
                }
                final zzcp$zzc zzi2 = zzi(this.zzaRH, zza(zzqp$zza2));
                zzi2.zza(zzqp$zze);
                zzi2.zzb(zzqp$zze, zzqp$zza2);
                zzi2.zzb(zzqp$zze, s2);
            }
        }
        for (final Map.Entry<String, List<zzqp$zza>> entry : this.zzaRz.zzBH().entrySet()) {
            for (final zzqp$zza zzqp$zza3 : entry.getValue()) {
                if (!zzdf.zzk(zzqp$zza3.zzBD().get(com.google.android.gms.internal.zzae.zzfW.toString()))) {
                    zzi(this.zzaRH, entry.getKey()).zzb(zzqp$zza3);
                }
            }
        }
    }
    
    private String zzAG() {
        if (this.zzaRJ <= 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzaRJ));
        for (int i = 2; i < this.zzaRJ; ++i) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }
    
    private zzbw<zzag$zza> zza(final zzag$zza zzag$zza, final Set<String> set, final zzdi zzdi) {
        if (!zzag$zza.zzje) {
            return new zzbw<zzag$zza>(zzag$zza, true);
        }
        switch (zzag$zza.type) {
            default: {
                zzbg.e("Unknown type: " + zzag$zza.type);
                return zzcp.zzaRy;
            }
            case 2: {
                final zzag$zza zzo = zzqp.zzo(zzag$zza);
                zzo.zziV = new zzag$zza[zzag$zza.zziV.length];
                for (int i = 0; i < zzag$zza.zziV.length; ++i) {
                    final zzbw<zzag$zza> zza = this.zza(zzag$zza.zziV[i], set, zzdi.zzjf(i));
                    if (zza == zzcp.zzaRy) {
                        return zzcp.zzaRy;
                    }
                    zzo.zziV[i] = zza.getObject();
                }
                return new zzbw<zzag$zza>(zzo, false);
            }
            case 3: {
                final zzag$zza zzo2 = zzqp.zzo(zzag$zza);
                if (zzag$zza.zziW.length != zzag$zza.zziX.length) {
                    zzbg.e("Invalid serving value: " + zzag$zza.toString());
                    return zzcp.zzaRy;
                }
                zzo2.zziW = new zzag$zza[zzag$zza.zziW.length];
                zzo2.zziX = new zzag$zza[zzag$zza.zziW.length];
                for (int j = 0; j < zzag$zza.zziW.length; ++j) {
                    final zzbw<zzag$zza> zza2 = this.zza(zzag$zza.zziW[j], set, zzdi.zzjg(j));
                    final zzbw<zzag$zza> zza3 = this.zza(zzag$zza.zziX[j], set, zzdi.zzjh(j));
                    if (zza2 == zzcp.zzaRy || zza3 == zzcp.zzaRy) {
                        return zzcp.zzaRy;
                    }
                    zzo2.zziW[j] = zza2.getObject();
                    zzo2.zziX[j] = zza3.getObject();
                }
                return new zzbw<zzag$zza>(zzo2, false);
            }
            case 4: {
                if (set.contains(zzag$zza.zziY)) {
                    zzbg.e("Macro cycle detected.  Current macro reference: " + zzag$zza.zziY + "." + "  Previous macro references: " + set.toString() + ".");
                    return zzcp.zzaRy;
                }
                set.add(zzag$zza.zziY);
                final zzbw<zzag$zza> zza4 = zzdj.zza(this.zza(zzag$zza.zziY, set, zzdi.zzAp()), zzag$zza.zzjd);
                set.remove(zzag$zza.zziY);
                return zza4;
            }
            case 7: {
                final zzag$zza zzo3 = zzqp.zzo(zzag$zza);
                zzo3.zzjc = new zzag$zza[zzag$zza.zzjc.length];
                for (int k = 0; k < zzag$zza.zzjc.length; ++k) {
                    final zzbw<zzag$zza> zza5 = this.zza(zzag$zza.zzjc[k], set, zzdi.zzji(k));
                    if (zza5 == zzcp.zzaRy) {
                        return zzcp.zzaRy;
                    }
                    zzo3.zzjc[k] = zza5.getObject();
                }
                return new zzbw<zzag$zza>(zzo3, false);
            }
        }
    }
    
    private zzbw<zzag$zza> zza(final String s, final Set<String> set, final zzbj zzbj) {
        ++this.zzaRJ;
        final zzcp$zzb zzcp$zzb = this.zzaRF.get(s);
        if (zzcp$zzb != null && !this.zzaRA.zzAb()) {
            this.zza(zzcp$zzb.zzAI(), set);
            --this.zzaRJ;
            return zzcp$zzb.zzAH();
        }
        final zzcp$zzc zzcp$zzc = this.zzaRH.get(s);
        if (zzcp$zzc == null) {
            zzbg.e(this.zzAG() + "Invalid macro: " + s);
            --this.zzaRJ;
            return zzcp.zzaRy;
        }
        final zzbw<Set<zzqp$zza>> zza = this.zza(s, zzcp$zzc.zzAJ(), zzcp$zzc.zzAK(), zzcp$zzc.zzAL(), zzcp$zzc.zzAN(), zzcp$zzc.zzAM(), set, zzbj.zzzR());
        zzqp$zza zzAO;
        if (zza.getObject().isEmpty()) {
            zzAO = zzcp$zzc.zzAO();
        }
        else {
            if (zza.getObject().size() > 1) {
                zzbg.zzaE(this.zzAG() + "Multiple macros active for macroName " + s);
            }
            zzAO = zza.getObject().iterator().next();
        }
        if (zzAO == null) {
            --this.zzaRJ;
            return zzcp.zzaRy;
        }
        final zzbw<zzag$zza> zza2 = this.zza(this.zzaRD, zzAO, set, zzbj.zzAh());
        final boolean b = zza.zzAq() && zza2.zzAq();
        zzbw<zzag$zza> zzaRy;
        if (zza2 == zzcp.zzaRy) {
            zzaRy = zzcp.zzaRy;
        }
        else {
            zzaRy = new zzbw<zzag$zza>(zza2.getObject(), b);
        }
        final zzag$zza zzAI = zzAO.zzAI();
        if (zzaRy.zzAq()) {
            this.zzaRF.zzf(s, new zzcp$zzb(zzaRy, zzAI));
        }
        this.zza(zzAI, set);
        --this.zzaRJ;
        return zzaRy;
    }
    
    private zzbw<zzag$zza> zza(final Map<String, zzak> map, final zzqp$zza zzqp$zza, final Set<String> set, final zzch zzch) {
        boolean b = true;
        final zzag$zza zzag$zza = zzqp$zza.zzBD().get(com.google.android.gms.internal.zzae.zzfj.toString());
        zzbw<zzag$zza> zzaRy;
        if (zzag$zza == null) {
            zzbg.e("No function id in properties");
            zzaRy = zzcp.zzaRy;
        }
        else {
            final String zziZ = zzag$zza.zziZ;
            final zzak zzak = map.get(zziZ);
            if (zzak == null) {
                zzbg.e(zziZ + " has no backing implementation.");
                return zzcp.zzaRy;
            }
            zzaRy = this.zzaRE.get(zzqp$zza);
            if (zzaRy == null || this.zzaRA.zzAb()) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                final Iterator<Map.Entry<String, zzag$zza>> iterator = zzqp$zza.zzBD().entrySet().iterator();
                boolean b2 = true;
                while (iterator.hasNext()) {
                    final Map.Entry<String, zzag$zza> entry = iterator.next();
                    final zzbw<zzag$zza> zza = this.zza(entry.getValue(), set, zzch.zzeO(entry.getKey()).zze(entry.getValue()));
                    if (zza == zzcp.zzaRy) {
                        return zzcp.zzaRy;
                    }
                    if (zza.zzAq()) {
                        zzqp$zza.zza(entry.getKey(), zza.getObject());
                    }
                    else {
                        b2 = false;
                    }
                    hashMap.put(entry.getKey(), zza.getObject());
                }
                if (!zzak.zzf(hashMap.keySet())) {
                    zzbg.e("Incorrect keys for function " + zziZ + " required " + zzak.zzAd() + " had " + hashMap.keySet());
                    return zzcp.zzaRy;
                }
                if (!b2 || !zzak.zzzx()) {
                    b = false;
                }
                final zzbw zzbw = new zzbw<zzag$zza>(zzak.zzG((Map<String, zzag$zza>)hashMap), b);
                if (b) {
                    this.zzaRE.zzf(zzqp$zza, (zzbw<zzag$zza>)zzbw);
                }
                zzch.zzd(zzbw.getObject());
                return (zzbw<zzag$zza>)zzbw;
            }
        }
        return zzaRy;
    }
    
    private zzbw<Set<zzqp$zza>> zza(final Set<zzqp$zze> set, final Set<String> set2, final zzcp$zza zzcp$zza, final zzco zzco) {
        final HashSet<zzqp$zza> set3 = new HashSet<zzqp$zza>();
        final HashSet<zzqp$zza> set4 = new HashSet<zzqp$zza>();
        final Iterator<zzqp$zze> iterator = set.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final zzqp$zze zzqp$zze = iterator.next();
            final zzck zzAo = zzco.zzAo();
            final zzbw<Boolean> zza = this.zza(zzqp$zze, set2, zzAo);
            if (zza.getObject()) {
                zzcp$zza.zza(zzqp$zze, set3, set4, zzAo);
            }
            b = (b && zza.zzAq());
        }
        set3.removeAll(set4);
        zzco.zzg(set3);
        return new zzbw<Set<zzqp$zza>>(set3, b);
    }
    
    private static String zza(final zzqp$zza zzqp$zza) {
        return zzdf.zzg(zzqp$zza.zzBD().get(com.google.android.gms.internal.zzae.zzfu.toString()));
    }
    
    private void zza(final zzag$zza zzag$zza, final Set<String> set) {
        if (zzag$zza != null) {
            final zzbw<zzag$zza> zza = this.zza(zzag$zza, set, new zzbu());
            if (zza != zzcp.zzaRy) {
                final Object zzl = zzdf.zzl(zza.getObject());
                if (zzl instanceof Map) {
                    this.zzaOT.push((Map<String, Object>)zzl);
                    return;
                }
                if (!(zzl instanceof List)) {
                    zzbg.zzaE("pushAfterEvaluate: value not a Map or List");
                    return;
                }
                for (final Map<String, Object> next : (List<Object>)zzl) {
                    if (next instanceof Map) {
                        this.zzaOT.push(next);
                    }
                    else {
                        zzbg.zzaE("pushAfterEvaluate: value not a Map");
                    }
                }
            }
        }
    }
    
    private static void zza(final List<zzqp$zza> list, final List<String> list2, final String s) {
        if (list.size() != list2.size()) {
            zzbg.zzaD("Invalid resource: imbalance of rule names of functions for " + s + " operation. Using default rule name instead");
        }
    }
    
    private static void zza(final Map<String, zzak> map, final zzak zzak) {
        if (map.containsKey(zzak.zzAc())) {
            throw new IllegalArgumentException("Duplicate function type name: " + zzak.zzAc());
        }
        map.put(zzak.zzAc(), zzak);
    }
    
    private static zzcp$zzc zzi(final Map<String, zzcp$zzc> map, final String s) {
        zzcp$zzc zzcp$zzc;
        if ((zzcp$zzc = map.get(s)) == null) {
            zzcp$zzc = new zzcp$zzc();
            map.put(s, zzcp$zzc);
        }
        return zzcp$zzc;
    }
    
    String zzAF() {
        synchronized (this) {
            return this.zzaRI;
        }
    }
    
    zzbw<Boolean> zza(final zzqp$zza zzqp$zza, final Set<String> set, final zzch zzch) {
        final zzbw<zzag$zza> zza = this.zza(this.zzaRC, zzqp$zza, set, zzch);
        final Boolean zzk = zzdf.zzk(zza.getObject());
        zzch.zzd(zzdf.zzK(zzk));
        return new zzbw<Boolean>(zzk, zza.zzAq());
    }
    
    zzbw<Boolean> zza(final zzqp$zze zzqp$zze, final Set<String> set, final zzck zzck) {
        final Iterator<zzqp$zza> iterator = zzqp$zze.zzBL().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final zzbw<Boolean> zza = this.zza(iterator.next(), set, zzck.zzAi());
            if (zza.getObject()) {
                zzck.zzf(zzdf.zzK(false));
                return new zzbw<Boolean>(false, zza.zzAq());
            }
            b = (b && zza.zzAq());
        }
        final Iterator<zzqp$zza> iterator2 = zzqp$zze.zzBK().iterator();
        while (iterator2.hasNext()) {
            final zzbw<Boolean> zza2 = this.zza(iterator2.next(), set, zzck.zzAj());
            if (!zza2.getObject()) {
                zzck.zzf(zzdf.zzK(false));
                return new zzbw<Boolean>(false, zza2.zzAq());
            }
            b = (b && zza2.zzAq());
        }
        zzck.zzf(zzdf.zzK(true));
        return new zzbw<Boolean>(true, b);
    }
    
    zzbw<Set<zzqp$zza>> zza(final String s, final Set<zzqp$zze> set, final Map<zzqp$zze, List<zzqp$zza>> map, final Map<zzqp$zze, List<String>> map2, final Map<zzqp$zze, List<zzqp$zza>> map3, final Map<zzqp$zze, List<String>> map4, final Set<String> set2, final zzco zzco) {
        return this.zza(set, set2, new zzcp$3(this, map, map2, map3, map4), zzco);
    }
    
    zzbw<Set<zzqp$zza>> zza(final Set<zzqp$zze> set, final zzco zzco) {
        return this.zza(set, new HashSet<String>(), new zzcp$4(this), zzco);
    }
    
    void zza(final zzak zzak) {
        zza(this.zzaRD, zzak);
    }
    
    void zzb(final zzak zzak) {
        zza(this.zzaRB, zzak);
    }
    
    void zzc(final zzak zzak) {
        zza(this.zzaRC, zzak);
    }
    
    public zzbw<zzag$zza> zzeS(final String s) {
        this.zzaRJ = 0;
        final zzag zzeI = this.zzaRA.zzeI(s);
        final zzbw<zzag$zza> zza = this.zza(s, new HashSet<String>(), zzeI.zzzY());
        zzeI.zzAa();
        return zza;
    }
    
    void zzeT(final String zzaRI) {
        synchronized (this) {
            this.zzaRI = zzaRI;
        }
    }
    
    public void zzew(final String s) {
        synchronized (this) {
            this.zzeT(s);
            final zzu zzzZ = this.zzaRA.zzeJ(s).zzzZ();
            final Iterator<zzqp$zza> iterator = this.zza(this.zzaRG, zzzZ.zzzR()).getObject().iterator();
            while (iterator.hasNext()) {
                this.zza(this.zzaRB, iterator.next(), new HashSet<String>(), zzzZ.zzzQ());
            }
        }
        final zzag zzag;
        zzag.zzAa();
        this.zzeT(null);
    }
    // monitorexit(this)
}
