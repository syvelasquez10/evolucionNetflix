// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<LaunchOptions>
{
    static void zza(final LaunchOptions launchOptions, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, launchOptions.getVersionCode());
        zzb.zza(parcel, 2, launchOptions.getRelaunchIfRunning());
        zzb.zza(parcel, 3, launchOptions.getLanguage(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public LaunchOptions zzZ(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        String zzp = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LaunchOptions(zzg, zzc, zzp);
    }
    
    public LaunchOptions[] zzaQ(final int n) {
        return new LaunchOptions[n];
    }
}
