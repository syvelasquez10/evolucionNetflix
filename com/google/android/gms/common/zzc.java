// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.HashSet;
import java.util.Set;

class zzc
{
    static final zzc$zza[] zzYl;
    static final zzc$zza[] zzYm;
    private static Set<zzc$zza> zzYn;
    private static Set<zzc$zza> zzYo;
    
    static {
        zzYl = new zzc$zza[] { zzc$zzbu.zzYt[0], zzc$zzbv.zzYt[0], zzc$zzbn.zzYt[0], zzc$zzu.zzYt[0], zzc$zzam.zzYt[0], zzc$zzq.zzYt[0], zzc$zzbo.zzYt[0], zzc$zzas.zzYt[0], zzc$zzt.zzYt[0], zzc$zzr.zzYt[0], zzc$zzbm.zzYt[0], zzc$zzbd.zzYt[0], zzc$zzbr.zzYt[0], zzc$zzae.zzYt[0], zzc$zzbc.zzYt[0], zzc$zzbs.zzYt[0], zzc$zzcr.zzYt[0], zzc$zzar.zzYt[0], zzc$zzck.zzYt[0], zzc$zzcl.zzYt[0], zzc$zzch.zzYt[0], zzc$zzal.zzYt[0], zzc$zzaw.zzYt[0], zzc$zzau.zzYt[0], zzc$zzav.zzYt[0], zzc$zzcj.zzYt[0], zzc$zzh.zzYt[0], zzc$zzaj.zzYt[0], zzc$zzak.zzYt[0], zzc$zzbi.zzYt[0], zzc$zzaz.zzYt[0], zzc$zzk.zzYt[0], zzc$zzk.zzYt[1], zzc$zzaf.zzYt[0], zzc$zzad.zzYt[0], zzc$zzi.zzYt[0], zzc$zze.zzYt[0], zzc$zzcq.zzYt[0], zzc$zzay.zzYt[0], zzc$zzcc.zzYt[0], zzc$zzcc.zzYt[1], zzc$zzat.zzYt[0], zzc$zzax.zzYt[0], zzc$zzai.zzYt[0], zzc$zzx.zzYt[0], zzc$zzaq.zzYt[0], zzc$zzaa.zzYt[0], zzc$zzbb.zzYt[0], zzc$zzcn.zzYt[0], zzc$zzcp.zzYt[0], zzc$zzbh.zzYt[0], zzc$zzbt.zzYt[0], zzc$zzcs.zzYt[0], zzc$zzbz.zzYt[0], zzc$zzbq.zzYt[0], zzc$zzbg.zzYt[0], zzc$zzw.zzYt[0], zzc$zzao.zzYt[0], zzc$zzby.zzYt[0], zzc$zzl.zzYt[0], zzc$zzz.zzYt[0], zzc$zzap.zzYt[0], zzc$zzbk.zzYt[0], zzc$zzca.zzYt[0], zzc$zzm.zzYt[0], zzc$zzah.zzYt[0], zzc$zzba.zzYt[0], zzc$zzci.zzYt[0], zzc$zzab.zzYt[0], zzc$zzg.zzYt[0], zzc$zzcg.zzYt[0], zzc$zzj.zzYt[0], zzc$zzbf.zzYt[0], zzc$zzcd.zzYt[0], zzc$zzp.zzYt[0], zzc$zzs.zzYt[0], zzc$zzo.zzYt[0], zzc$zzy.zzYt[0], zzc$zzbj.zzYt[0], zzc$zzv.zzYt[0], zzc$zzbx.zzYt[0], zzc$zzbl.zzYt[0], zzc$zzbe.zzYt[0], zzc$zzbw.zzYt[0], zzc$zzcf.zzYt[0], zzc$zzag.zzYt[0], zzc$zzcm.zzYt[0], zzc$zzd.zzYt[0], zzc$zzac.zzYt[0], zzc$zzcb.zzYt[0], zzc$zzan.zzYt[0], zzc$zzbp.zzYt[0], zzc$zzce.zzYt[0], zzc$zzco.zzYt[0], zzc$zzf.zzYt[0] };
        zzYm = zza(new zzc$zza[][] { zzc$zzbu.zzYt, zzc$zzbv.zzYt, zzc$zzn.zzYt, zzc$zzbn.zzYt, zzc$zzu.zzYt, zzc$zzam.zzYt, zzc$zzq.zzYt, zzc$zzbo.zzYt, zzc$zzas.zzYt, zzc$zzt.zzYt, zzc$zzr.zzYt, zzc$zzbm.zzYt, zzc$zzbd.zzYt, zzc$zzbr.zzYt, zzc$zzae.zzYt, zzc$zzbc.zzYt, zzc$zzbs.zzYt, zzc$zzcr.zzYt, zzc$zzar.zzYt, zzc$zzck.zzYt, zzc$zzcl.zzYt, zzc$zzch.zzYt, zzc$zzal.zzYt, zzc$zzaw.zzYt, zzc$zzau.zzYt, zzc$zzav.zzYt, zzc$zzcj.zzYt, zzc$zzh.zzYt, zzc$zzaj.zzYt, zzc$zzak.zzYt, zzc$zzbi.zzYt, zzc$zzaz.zzYt, zzc$zzk.zzYt, zzc$zzaf.zzYt, zzc$zzad.zzYt, zzc$zzi.zzYt, zzc$zze.zzYt, zzc$zzcq.zzYt, zzc$zzay.zzYt, zzc$zzcc.zzYt, zzc$zzat.zzYt, zzc$zzax.zzYt, zzc$zzai.zzYt, zzc$zzx.zzYt, zzc$zzaq.zzYt, zzc$zzaa.zzYt, zzc$zzbb.zzYt, zzc$zzcn.zzYt, zzc$zzcp.zzYt, zzc$zzbh.zzYt, zzc$zzbt.zzYt, zzc$zzcs.zzYt, zzc$zzbz.zzYt, zzc$zzbq.zzYt, zzc$zzbg.zzYt, zzc$zzw.zzYt, zzc$zzao.zzYt, zzc$zzby.zzYt, zzc$zzl.zzYt, zzc$zzz.zzYt, zzc$zzap.zzYt, zzc$zzbk.zzYt, zzc$zzca.zzYt, zzc$zzm.zzYt, zzc$zzah.zzYt, zzc$zzba.zzYt, zzc$zzci.zzYt, zzc$zzab.zzYt, zzc$zzg.zzYt, zzc$zzcg.zzYt, zzc$zzj.zzYt, zzc$zzbf.zzYt, zzc$zzcd.zzYt, zzc$zzp.zzYt, zzc$zzs.zzYt, zzc$zzo.zzYt, zzc$zzy.zzYt, zzc$zzbj.zzYt, zzc$zzv.zzYt, zzc$zzbx.zzYt, zzc$zzbl.zzYt, zzc$zzbe.zzYt, zzc$zzbw.zzYt, zzc$zzcf.zzYt, zzc$zzag.zzYt, zzc$zzcm.zzYt, zzc$zzd.zzYt, zzc$zzac.zzYt, zzc$zzcb.zzYt, zzc$zzan.zzYt, zzc$zzbp.zzYt, zzc$zzce.zzYt, zzc$zzco.zzYt, zzc$zzf.zzYt });
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
    
    static Set<zzc$zza> zzmT() {
        if (zzc.zzYn == null) {
            zzc.zzYn = zza(zzc.zzYm);
        }
        return zzc.zzYn;
    }
    
    static Set<zzc$zza> zzmU() {
        if (zzc.zzYo == null) {
            zzc.zzYo = zza(zzc.zzYl);
        }
        return zzc.zzYo;
    }
}
