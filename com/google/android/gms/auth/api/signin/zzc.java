// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<GoogleSignInAccount>
{
    static void zza(final GoogleSignInAccount googleSignInAccount, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, googleSignInAccount.versionCode);
        zzb.zza(parcel, 2, googleSignInAccount.getId(), false);
        zzb.zza(parcel, 3, googleSignInAccount.getIdToken(), false);
        zzb.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        zzb.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        zzb.zza(parcel, 6, (Parcelable)googleSignInAccount.zzlT(), n, false);
        zzb.zza(parcel, 7, googleSignInAccount.zzlU(), false);
        zzb.zza(parcel, 8, googleSignInAccount.zzlV());
        zzb.zzI(parcel, zzaq);
    }
    
    public GoogleSignInAccount zzQ(final Parcel parcel) {
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        long zzi = 0L;
        Uri uri = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp5 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 6: {
                    uri = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 7: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new GoogleSignInAccount(zzg, zzp5, zzp4, zzp3, zzp2, uri, zzp, zzi);
    }
    
    public GoogleSignInAccount[] zzaH(final int n) {
        return new GoogleSignInAccount[n];
    }
}
