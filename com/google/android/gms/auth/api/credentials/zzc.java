// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<IdToken>
{
    static void zza(final IdToken idToken, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zza(parcel, 1, idToken.getAccountType(), false);
        zzb.zzc(parcel, 1000, idToken.zzCY);
        zzb.zza(parcel, 2, idToken.getIdToken(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public IdToken zzE(final Parcel parcel) {
        String zzo = null;
        final int zzab = zza.zzab(parcel);
        int zzg = 0;
        String zzo2 = null;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzo2 = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new IdToken(zzg, zzo2, zzo);
    }
    
    public IdToken[] zzau(final int n) {
        return new IdToken[n];
    }
}
