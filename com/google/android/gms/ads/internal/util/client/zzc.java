// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<VersionInfoParcel>
{
    static void zza(final VersionInfoParcel versionInfoParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, versionInfoParcel.versionCode);
        zzb.zza(parcel, 2, versionInfoParcel.zzJu, false);
        zzb.zzc(parcel, 3, versionInfoParcel.zzJv);
        zzb.zzc(parcel, 4, versionInfoParcel.zzJw);
        zzb.zza(parcel, 5, versionInfoParcel.zzJx);
        zzb.zzI(parcel, zzaq);
    }
    
    public VersionInfoParcel[] zzO(final int n) {
        return new VersionInfoParcel[n];
    }
    
    public VersionInfoParcel zzp(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        String zzp = null;
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new VersionInfoParcel(zzg3, zzp, zzg2, zzg, zzc);
    }
}
