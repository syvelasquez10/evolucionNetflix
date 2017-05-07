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
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, emailSignInConfig.versionCode);
        zzb.zza(parcel, 2, (Parcelable)emailSignInConfig.zzlO(), n, false);
        zzb.zza(parcel, 3, emailSignInConfig.zzlQ(), false);
        zzb.zza(parcel, 4, (Parcelable)emailSignInConfig.zzlP(), n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public EmailSignInConfig zzO(final Parcel parcel) {
        Uri uri = null;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        int zzg = 0;
        String s = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            Uri uri3 = null;
            String s3 = null;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    final String s2 = s;
                    uri3 = uri2;
                    s3 = s2;
                    break;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    final Uri uri4 = uri2;
                    s3 = s;
                    uri3 = uri4;
                    break;
                }
                case 2: {
                    final Uri uri5 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    s3 = s;
                    uri3 = uri5;
                    break;
                }
                case 3: {
                    final String zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    uri3 = uri2;
                    s3 = zzp;
                    break;
                }
                case 4: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
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
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new EmailSignInConfig(zzg, uri2, s, uri);
    }
    
    public EmailSignInConfig[] zzaF(final int n) {
        return new EmailSignInConfig[n];
    }
}
