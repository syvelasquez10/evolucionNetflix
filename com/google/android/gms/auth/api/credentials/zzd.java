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
    static void zza(final IdToken idToken, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, idToken.getAccountType(), false);
        zzb.zzc(parcel, 1000, idToken.mVersionCode);
        zzb.zza(parcel, 2, idToken.getIdToken(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public IdToken zzH(final Parcel parcel) {
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        String zzp2 = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new IdToken(zzg, zzp2, zzp);
    }
    
    public IdToken[] zzay(final int n) {
        return new IdToken[n];
    }
}
