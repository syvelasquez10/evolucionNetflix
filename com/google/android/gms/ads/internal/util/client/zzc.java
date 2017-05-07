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
    static void zza(final VersionInfoParcel versionInfoParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, versionInfoParcel.versionCode);
        zzb.zza(parcel, 2, versionInfoParcel.zzIz, false);
        zzb.zzc(parcel, 3, versionInfoParcel.zzIA);
        zzb.zzc(parcel, 4, versionInfoParcel.zzIB);
        zzb.zza(parcel, 5, versionInfoParcel.zzIC);
        zzb.zzH(parcel, zzak);
    }
    
    public VersionInfoParcel[] zzN(final int n) {
        return new VersionInfoParcel[n];
    }
    
    public VersionInfoParcel zzq(final Parcel parcel) {
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        String zzo = null;
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new VersionInfoParcel(zzg3, zzo, zzg2, zzg, zzc);
    }
}
