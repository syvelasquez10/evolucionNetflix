// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<PasswordSpecification>
{
    static void zza(final PasswordSpecification passwordSpecification, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, passwordSpecification.zzSv, false);
        zzb.zzc(parcel, 1000, passwordSpecification.mVersionCode);
        zzb.zzb(parcel, 2, passwordSpecification.zzSw, false);
        zzb.zza(parcel, 3, passwordSpecification.zzSx, false);
        zzb.zzc(parcel, 4, passwordSpecification.zzSy);
        zzb.zzc(parcel, 5, passwordSpecification.zzSz);
        zzb.zzI(parcel, zzaq);
    }
    
    public PasswordSpecification zzI(final Parcel parcel) {
        List<Integer> zzC = null;
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        int zzg2 = 0;
        List<String> zzD = null;
        String zzp = null;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzD = zza.zzD(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzC = zza.zzC(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new PasswordSpecification(zzg3, zzp, zzD, zzC, zzg2, zzg);
    }
    
    public PasswordSpecification[] zzaz(final int n) {
        return new PasswordSpecification[n];
    }
}
