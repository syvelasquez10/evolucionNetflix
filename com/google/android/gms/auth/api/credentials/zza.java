// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<Credential>
{
    static void zza(final Credential credential, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zza(parcel, 1001, credential.zzkZ(), false);
        zzb.zza(parcel, 1, credential.getId(), false);
        zzb.zzc(parcel, 1000, credential.zzCY);
        zzb.zza(parcel, 2, credential.getName(), false);
        zzb.zza(parcel, 3, (Parcelable)credential.getProfilePictureUri(), n, false);
        zzb.zza(parcel, 1002, credential.zzla(), false);
        zzb.zzc(parcel, 4, credential.getIdTokens(), false);
        zzb.zza(parcel, 5, credential.getPassword(), false);
        zzb.zza(parcel, 6, credential.getAccountType(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public Credential zzC(final Parcel parcel) {
        String zzo = null;
        final int zzab = com.google.android.gms.common.internal.safeparcel.zza.zzab(parcel);
        int zzg = 0;
        String zzo2 = null;
        List<IdToken> zzc = null;
        Uri uri = null;
        String zzo3 = null;
        String zzo4 = null;
        String zzo5 = null;
        String zzo6 = null;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = com.google.android.gms.common.internal.safeparcel.zza.zzaa(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbA(zzaa)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1001: {
                    zzo6 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzo4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 1000: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 3: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 1002: {
                    zzo5 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzaa, IdToken.CREATOR);
                    continue;
                }
                case 5: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 6: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new Credential(zzg, zzo6, zzo5, zzo4, zzo3, uri, zzc, zzo2, zzo);
    }
    
    public Credential[] zzas(final int n) {
        return new Credential[n];
    }
}
