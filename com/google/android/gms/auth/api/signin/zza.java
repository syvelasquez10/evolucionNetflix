// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<EmailSignInConfig>
{
    static void zza(final EmailSignInConfig emailSignInConfig, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, emailSignInConfig.versionCode);
        zzb.zza(parcel, 2, (Parcelable)emailSignInConfig.zzlA(), n, false);
        zzb.zza(parcel, 3, emailSignInConfig.zzlC(), false);
        zzb.zza(parcel, 4, (Parcelable)emailSignInConfig.zzlB(), n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public EmailSignInConfig zzN(final Parcel parcel) {
        Uri uri = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        String s = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            Uri uri3 = null;
            String s3 = null;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    final String s2 = s;
                    uri3 = uri2;
                    s3 = s2;
                    break;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    final Uri uri4 = uri2;
                    s3 = s;
                    uri3 = uri4;
                    break;
                }
                case 2: {
                    final Uri uri5 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    s3 = s;
                    uri3 = uri5;
                    break;
                }
                case 3: {
                    final String zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    uri3 = uri2;
                    s3 = zzo;
                    break;
                }
                case 4: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    final Uri uri6 = uri2;
                    s3 = s;
                    uri3 = uri6;
                    break;
                }
            }
            final Uri uri7 = uri3;
            s = s3;
            uri2 = uri7;
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new EmailSignInConfig(zzg, uri2, s, uri);
    }
    
    public EmailSignInConfig[] zzaD(final int n) {
        return new EmailSignInConfig[n];
    }
}
