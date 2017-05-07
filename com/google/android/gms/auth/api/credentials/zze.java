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
    static void zza(final PasswordSpecification passwordSpecification, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1, passwordSpecification.zzRq, false);
        zzb.zzc(parcel, 1000, passwordSpecification.mVersionCode);
        zzb.zzb(parcel, 2, passwordSpecification.zzRr, false);
        zzb.zza(parcel, 3, passwordSpecification.zzRs, false);
        zzb.zzc(parcel, 4, passwordSpecification.zzRt);
        zzb.zzc(parcel, 5, passwordSpecification.zzRu);
        zzb.zzH(parcel, zzak);
    }
    
    public PasswordSpecification zzH(final Parcel parcel) {
        List<Integer> zzB = null;
        int zzg = 0;
        final int zzaj = zza.zzaj(parcel);
        int zzg2 = 0;
        List<String> zzC = null;
        String zzo = null;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzC = zza.zzC(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzB = zza.zzB(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new PasswordSpecification(zzg3, zzo, zzC, zzB, zzg2, zzg);
    }
    
    public PasswordSpecification[] zzax(final int n) {
        return new PasswordSpecification[n];
    }
}
