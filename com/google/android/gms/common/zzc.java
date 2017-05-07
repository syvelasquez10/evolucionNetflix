// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.HashSet;
import java.util.Set;

class zzc
{
    static final zzc$zza[] zzVI;
    static final zzc$zza[] zzVJ;
    private static Set<zzc$zza> zzVK;
    private static Set<zzc$zza> zzVL;
    
    static {
        zzVI = new zzc$zza[] { zzc$zzbk.zzVQ[0], zzc$zzbl.zzVQ[0], zzc$zzbe.zzVQ[0], zzc$zzs.zzVQ[0], zzc$zzah.zzVQ[0], zzc$zzo.zzVQ[0], zzc$zzbf.zzVQ[0], zzc$zzam.zzVQ[0], zzc$zzr.zzVQ[0], zzc$zzp.zzVQ[0], zzc$zzbd.zzVQ[0], zzc$zzax.zzVQ[0], zzc$zzbh.zzVQ[0], zzc$zzaa.zzVQ[0], zzc$zzaw.zzVQ[0], zzc$zzbi.zzVQ[0], zzc$zzca.zzVQ[0], zzc$zzal.zzVQ[0], zzc$zzbv.zzVQ[0], zzc$zzbw.zzVQ[0], zzc$zzbs.zzVQ[0], zzc$zzag.zzVQ[0], zzc$zzaq.zzVQ[0], zzc$zzao.zzVQ[0], zzc$zzap.zzVQ[0], zzc$zzbu.zzVQ[0], zzc$zzf.zzVQ[0], zzc$zzae.zzVQ[0], zzc$zzaf.zzVQ[0], zzc$zzbb.zzVQ[0], zzc$zzat.zzVQ[0], zzc$zzi.zzVQ[0], zzc$zzi.zzVQ[1], zzc$zzab.zzVQ[0], zzc$zzz.zzVQ[0], zzc$zzg.zzVQ[0], zzc$zzd.zzVQ[0], zzc$zzbz.zzVQ[0], zzc$zzas.zzVQ[0], zzc$zzbp.zzVQ[0], zzc$zzbp.zzVQ[1], zzc$zzan.zzVQ[0], zzc$zzar.zzVQ[0], zzc$zzad.zzVQ[0], zzc$zzu.zzVQ[0], zzc$zzak.zzVQ[0], zzc$zzx.zzVQ[0], zzc$zzav.zzVQ[0], zzc$zzbx.zzVQ[0], zzc$zzby.zzVQ[0], zzc$zzba.zzVQ[0], zzc$zzbj.zzVQ[0], zzc$zzcb.zzVQ[0], zzc$zzbn.zzVQ[0], zzc$zzbg.zzVQ[0], zzc$zzaz.zzVQ[0], zzc$zzt.zzVQ[0], zzc$zzai.zzVQ[0], zzc$zzbm.zzVQ[0], zzc$zzj.zzVQ[0], zzc$zzw.zzVQ[0], zzc$zzaj.zzVQ[0], zzc$zzbc.zzVQ[0], zzc$zzbo.zzVQ[0], zzc$zzk.zzVQ[0], zzc$zzac.zzVQ[0], zzc$zzau.zzVQ[0], zzc$zzbt.zzVQ[0], zzc$zzy.zzVQ[0], zzc$zze.zzVQ[0], zzc$zzbr.zzVQ[0], zzc$zzh.zzVQ[0], zzc$zzay.zzVQ[0], zzc$zzbq.zzVQ[0], zzc$zzn.zzVQ[0], zzc$zzq.zzVQ[0], zzc$zzm.zzVQ[0], zzc$zzv.zzVQ[0] };
        zzVJ = zza(new zzc$zza[][] { zzc$zzbk.zzVQ, zzc$zzbl.zzVQ, zzc$zzl.zzVQ, zzc$zzbe.zzVQ, zzc$zzs.zzVQ, zzc$zzah.zzVQ, zzc$zzo.zzVQ, zzc$zzbf.zzVQ, zzc$zzam.zzVQ, zzc$zzr.zzVQ, zzc$zzp.zzVQ, zzc$zzbd.zzVQ, zzc$zzax.zzVQ, zzc$zzbh.zzVQ, zzc$zzaa.zzVQ, zzc$zzaw.zzVQ, zzc$zzbi.zzVQ, zzc$zzca.zzVQ, zzc$zzal.zzVQ, zzc$zzbv.zzVQ, zzc$zzbw.zzVQ, zzc$zzbs.zzVQ, zzc$zzag.zzVQ, zzc$zzaq.zzVQ, zzc$zzao.zzVQ, zzc$zzap.zzVQ, zzc$zzbu.zzVQ, zzc$zzf.zzVQ, zzc$zzae.zzVQ, zzc$zzaf.zzVQ, zzc$zzbb.zzVQ, zzc$zzat.zzVQ, zzc$zzi.zzVQ, zzc$zzab.zzVQ, zzc$zzz.zzVQ, zzc$zzg.zzVQ, zzc$zzd.zzVQ, zzc$zzbz.zzVQ, zzc$zzas.zzVQ, zzc$zzbp.zzVQ, zzc$zzan.zzVQ, zzc$zzar.zzVQ, zzc$zzad.zzVQ, zzc$zzu.zzVQ, zzc$zzak.zzVQ, zzc$zzx.zzVQ, zzc$zzav.zzVQ, zzc$zzbx.zzVQ, zzc$zzby.zzVQ, zzc$zzba.zzVQ, zzc$zzbj.zzVQ, zzc$zzcb.zzVQ, zzc$zzbn.zzVQ, zzc$zzbg.zzVQ, zzc$zzaz.zzVQ, zzc$zzt.zzVQ, zzc$zzai.zzVQ, zzc$zzbm.zzVQ, zzc$zzj.zzVQ, zzc$zzw.zzVQ, zzc$zzaj.zzVQ, zzc$zzbc.zzVQ, zzc$zzbo.zzVQ, zzc$zzk.zzVQ, zzc$zzac.zzVQ, zzc$zzau.zzVQ, zzc$zzbt.zzVQ, zzc$zzy.zzVQ, zzc$zze.zzVQ, zzc$zzbr.zzVQ, zzc$zzh.zzVQ, zzc$zzay.zzVQ, zzc$zzbq.zzVQ, zzc$zzn.zzVQ, zzc$zzq.zzVQ, zzc$zzm.zzVQ, zzc$zzv.zzVQ });
    }
    
    private static Set<zzc$zza> zza(final zzc$zza[] array) {
        final HashSet<zzc$zza> set = new HashSet<zzc$zza>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add(array[i]);
        }
        return set;
    }
    
    static zzc$zza[] zza(final zzc$zza[]... array) {
        final int length = array.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            n += array[i].length;
            ++i;
        }
        final zzc$zza[] array2 = new zzc$zza[n];
        final int length2 = array.length;
        int j = 0;
        int n2 = 0;
        while (j < length2) {
            final zzc$zza[] array3 = array[j];
            for (int k = 0; k < array3.length; ++k, ++n2) {
                array2[n2] = array3[k];
            }
            ++j;
        }
        return array2;
    }
    
    static Set<zzc$zza> zzmg() {
        if (zzc.zzVK == null) {
            zzc.zzVK = zza(zzc.zzVJ);
        }
        return zzc.zzVK;
    }
    
    static Set<zzc$zza> zzmh() {
        if (zzc.zzVL == null) {
            zzc.zzVL = zza(zzc.zzVI);
        }
        return zzc.zzVL;
    }
}
