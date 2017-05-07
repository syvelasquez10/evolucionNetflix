// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<GoogleSignInAccount>
{
    static void zza(final GoogleSignInAccount googleSignInAccount, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, googleSignInAccount.versionCode);
        zzb.zza(parcel, 2, googleSignInAccount.getId(), false);
        zzb.zza(parcel, 3, googleSignInAccount.zzlv(), false);
        zzb.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        zzb.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        zzb.zza(parcel, 6, (Parcelable)googleSignInAccount.zzzm(), n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public GoogleSignInAccount zzgi(final Parcel parcel) {
        Uri uri = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        String zzo = null;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 6: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new GoogleSignInAccount(zzg, zzo4, zzo3, zzo2, zzo, uri);
    }
    
    public GoogleSignInAccount[] zziX(final int n) {
        return new GoogleSignInAccount[n];
    }
}
