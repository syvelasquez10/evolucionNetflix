// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<LaunchOptions>
{
    static void zza(final LaunchOptions launchOptions, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, launchOptions.getVersionCode());
        zzb.zza(parcel, 2, launchOptions.getRelaunchIfRunning());
        zzb.zza(parcel, 3, launchOptions.getLanguage(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public LaunchOptions zzL(final Parcel parcel) {
        boolean zzc = false;
        final int zzab = zza.zzab(parcel);
        String zzo = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzc = zza.zzc(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new LaunchOptions(zzg, zzc, zzo);
    }
    
    public LaunchOptions[] zzaB(final int n) {
        return new LaunchOptions[n];
    }
}
