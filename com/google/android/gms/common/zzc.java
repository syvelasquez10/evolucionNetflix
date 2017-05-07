// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.HashSet;
import java.util.Set;

class zzc
{
    static final zzc$zza[] zzaac;
    static final zzc$zza[] zzaad;
    private static Set<zzc$zza> zzaae;
    private static Set<zzc$zza> zzaaf;
    
    static {
        zzaac = new zzc$zza[] { zzc$zzbz.zzaak[0], zzc$zzca.zzaak[0], zzc$zzbr.zzaak[0], zzc$zzv.zzaak[0], zzc$zzao.zzaak[0], zzc$zzr.zzaak[0], zzc$zzbs.zzaak[0], zzc$zzau.zzaak[0], zzc$zzu.zzaak[0], zzc$zzs.zzaak[0], zzc$zzbp.zzaak[0], zzc$zzbf.zzaak[0], zzc$zzbv.zzaak[0], zzc$zzaf.zzaak[0], zzc$zzbe.zzaak[0], zzc$zzbx.zzaak[0], zzc$zzcy.zzaak[0], zzc$zzat.zzaak[0], zzc$zzcq.zzaak[0], zzc$zzcr.zzaak[0], zzc$zzcn.zzaak[0], zzc$zzan.zzaak[0], zzc$zzay.zzaak[0], zzc$zzaw.zzaak[0], zzc$zzax.zzaak[0], zzc$zzcp.zzaak[0], zzc$zzi.zzaak[0], zzc$zzal.zzaak[0], zzc$zzam.zzaak[0], zzc$zzbl.zzaak[0], zzc$zzbb.zzaak[0], zzc$zzl.zzaak[0], zzc$zzl.zzaak[1], zzc$zzah.zzaak[0], zzc$zzae.zzaak[0], zzc$zzj.zzaak[0], zzc$zze.zzaak[0], zzc$zzcx.zzaak[0], zzc$zzba.zzaak[0], zzc$zzch.zzaak[0], zzc$zzch.zzaak[1], zzc$zzav.zzaak[0], zzc$zzaz.zzaak[0], zzc$zzak.zzaak[0], zzc$zzy.zzaak[0], zzc$zzas.zzaak[0], zzc$zzab.zzaak[0], zzc$zzbd.zzaak[0], zzc$zzct.zzaak[0], zzc$zzcw.zzaak[0], zzc$zzbk.zzaak[0], zzc$zzby.zzaak[0], zzc$zzcz.zzaak[0], zzc$zzce.zzaak[0], zzc$zzbu.zzaak[0], zzc$zzbj.zzaak[0], zzc$zzx.zzaak[0], zzc$zzaq.zzaak[0], zzc$zzcd.zzaak[0], zzc$zzm.zzaak[0], zzc$zzaa.zzaak[0], zzc$zzar.zzaak[0], zzc$zzbn.zzaak[0], zzc$zzcf.zzaak[0], zzc$zzn.zzaak[0], zzc$zzaj.zzaak[0], zzc$zzbc.zzaak[0], zzc$zzco.zzaak[0], zzc$zzac.zzaak[0], zzc$zzg.zzaak[0], zzc$zzcl.zzaak[0], zzc$zzk.zzaak[0], zzc$zzbh.zzaak[0], zzc$zzci.zzaak[0], zzc$zzq.zzaak[0], zzc$zzt.zzaak[0], zzc$zzp.zzaak[0], zzc$zzz.zzaak[0], zzc$zzbm.zzaak[0], zzc$zzw.zzaak[0], zzc$zzcc.zzaak[0], zzc$zzbo.zzaak[0], zzc$zzbg.zzaak[0], zzc$zzcb.zzaak[0], zzc$zzck.zzaak[0], zzc$zzai.zzaak[0], zzc$zzcs.zzaak[0], zzc$zzd.zzaak[0], zzc$zzad.zzaak[0], zzc$zzcg.zzaak[0], zzc$zzap.zzaak[0], zzc$zzbt.zzaak[0], zzc$zzcj.zzaak[0], zzc$zzcv.zzaak[0], zzc$zzf.zzaak[0], zzc$zzag.zzaak[0], zzc$zzbq.zzaak[0], zzc$zzcm.zzaak[0], zzc$zzbi.zzaak[0], zzc$zzcu.zzaak[0], zzc$zzh.zzaak[0], zzc$zzbw.zzaak[0] };
        zzaad = zza(new zzc$zza[][] { zzc$zzbz.zzaak, zzc$zzca.zzaak, zzc$zzo.zzaak, zzc$zzbr.zzaak, zzc$zzv.zzaak, zzc$zzao.zzaak, zzc$zzr.zzaak, zzc$zzbs.zzaak, zzc$zzau.zzaak, zzc$zzu.zzaak, zzc$zzs.zzaak, zzc$zzbp.zzaak, zzc$zzbf.zzaak, zzc$zzbv.zzaak, zzc$zzaf.zzaak, zzc$zzbe.zzaak, zzc$zzbx.zzaak, zzc$zzcy.zzaak, zzc$zzat.zzaak, zzc$zzcq.zzaak, zzc$zzcr.zzaak, zzc$zzcn.zzaak, zzc$zzan.zzaak, zzc$zzay.zzaak, zzc$zzaw.zzaak, zzc$zzax.zzaak, zzc$zzcp.zzaak, zzc$zzi.zzaak, zzc$zzal.zzaak, zzc$zzam.zzaak, zzc$zzbl.zzaak, zzc$zzbb.zzaak, zzc$zzl.zzaak, zzc$zzah.zzaak, zzc$zzae.zzaak, zzc$zzj.zzaak, zzc$zze.zzaak, zzc$zzcx.zzaak, zzc$zzba.zzaak, zzc$zzch.zzaak, zzc$zzav.zzaak, zzc$zzaz.zzaak, zzc$zzak.zzaak, zzc$zzy.zzaak, zzc$zzas.zzaak, zzc$zzab.zzaak, zzc$zzbd.zzaak, zzc$zzct.zzaak, zzc$zzcw.zzaak, zzc$zzbk.zzaak, zzc$zzby.zzaak, zzc$zzcz.zzaak, zzc$zzce.zzaak, zzc$zzbu.zzaak, zzc$zzbj.zzaak, zzc$zzx.zzaak, zzc$zzaq.zzaak, zzc$zzcd.zzaak, zzc$zzm.zzaak, zzc$zzaa.zzaak, zzc$zzar.zzaak, zzc$zzbn.zzaak, zzc$zzcf.zzaak, zzc$zzn.zzaak, zzc$zzaj.zzaak, zzc$zzbc.zzaak, zzc$zzco.zzaak, zzc$zzac.zzaak, zzc$zzg.zzaak, zzc$zzcl.zzaak, zzc$zzk.zzaak, zzc$zzbh.zzaak, zzc$zzci.zzaak, zzc$zzq.zzaak, zzc$zzt.zzaak, zzc$zzp.zzaak, zzc$zzz.zzaak, zzc$zzbm.zzaak, zzc$zzw.zzaak, zzc$zzcc.zzaak, zzc$zzbo.zzaak, zzc$zzbg.zzaak, zzc$zzcb.zzaak, zzc$zzck.zzaak, zzc$zzai.zzaak, zzc$zzcs.zzaak, zzc$zzd.zzaak, zzc$zzad.zzaak, zzc$zzcg.zzaak, zzc$zzap.zzaak, zzc$zzbt.zzaak, zzc$zzcj.zzaak, zzc$zzcv.zzaak, zzc$zzf.zzaak, zzc$zzag.zzaak, zzc$zzbq.zzaak, zzc$zzcm.zzaak, zzc$zzbi.zzaak, zzc$zzcu.zzaak, zzc$zzh.zzaak, zzc$zzbw.zzaak });
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
    
    static Set<zzc$zza> zznp() {
        if (zzc.zzaae == null) {
            zzc.zzaae = zza(zzc.zzaad);
        }
        return zzc.zzaae;
    }
    
    static Set<zzc$zza> zznq() {
        if (zzc.zzaaf == null) {
            zzc.zzaaf = zza(zzc.zzaac);
        }
        return zzc.zzaaf;
    }
}
