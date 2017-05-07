// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<IdToken>
{
    static void zza(final IdToken idToken, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1, idToken.getAccountType(), false);
        zzb.zzc(parcel, 1000, idToken.mVersionCode);
        zzb.zza(parcel, 2, idToken.zzlv(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public IdToken zzG(final Parcel parcel) {
        String zzo = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        String zzo2 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new IdToken(zzg, zzo2, zzo);
    }
    
    public IdToken[] zzaw(final int n) {
        return new IdToken[n];
    }
}
