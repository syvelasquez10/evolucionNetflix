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
        final int zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1001, credential.zzlr(), false);
        zzb.zza(parcel, 1, credential.getId(), false);
        zzb.zzc(parcel, 1000, credential.mVersionCode);
        zzb.zza(parcel, 2, credential.getName(), false);
        zzb.zza(parcel, 3, (Parcelable)credential.getProfilePictureUri(), n, false);
        zzb.zza(parcel, 1002, credential.zzls(), false);
        zzb.zzc(parcel, 4, credential.zzlt(), false);
        zzb.zza(parcel, 5, credential.getPassword(), false);
        zzb.zza(parcel, 6, credential.getAccountType(), false);
        zzb.zza(parcel, 7, credential.getGeneratedPassword(), false);
        zzb.zza(parcel, 8, credential.zzlu(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public Credential zzD(final Parcel parcel) {
        String zzo = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        List<IdToken> zzc = null;
        Uri uri = null;
        String zzo5 = null;
        String zzo6 = null;
        String zzo7 = null;
        String zzo8 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    continue;
                }
                case 1001: {
                    zzo8 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzo6 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo5 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 1002: {
                    zzo7 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzai, IdToken.CREATOR);
                    continue;
                }
                case 5: {
                    zzo4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new Credential(zzg, zzo8, zzo7, zzo6, zzo5, uri, zzc, zzo4, zzo3, zzo2, zzo);
    }
    
    public Credential[] zzat(final int n) {
        return new Credential[n];
    }
}
