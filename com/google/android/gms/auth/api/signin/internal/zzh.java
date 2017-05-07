// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.auth.api.signin.FacebookSignInConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.auth.api.signin.EmailSignInConfig;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<SignInConfiguration>
{
    static void zza(final SignInConfiguration signInConfiguration, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, signInConfiguration.versionCode);
        zzb.zza(parcel, 2, signInConfiguration.zzme(), false);
        zzb.zza(parcel, 3, signInConfiguration.zzmb(), false);
        zzb.zza(parcel, 4, (Parcelable)signInConfiguration.zzmf(), n, false);
        zzb.zza(parcel, 5, (Parcelable)signInConfiguration.zzmg(), n, false);
        zzb.zza(parcel, 6, (Parcelable)signInConfiguration.zzmh(), n, false);
        zzb.zza(parcel, 7, signInConfiguration.zzmi(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public SignInConfiguration zzS(final Parcel parcel) {
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        FacebookSignInConfig facebookSignInConfig = null;
        GoogleSignInConfig googleSignInConfig = null;
        EmailSignInConfig emailSignInConfig = null;
        String zzp2 = null;
        String zzp3 = null;
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
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    emailSignInConfig = zza.zza(parcel, zzao, EmailSignInConfig.CREATOR);
                    continue;
                }
                case 5: {
                    googleSignInConfig = zza.zza(parcel, zzao, GoogleSignInConfig.CREATOR);
                    continue;
                }
                case 6: {
                    facebookSignInConfig = zza.zza(parcel, zzao, FacebookSignInConfig.CREATOR);
                    continue;
                }
                case 7: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new SignInConfiguration(zzg, zzp3, zzp2, emailSignInConfig, googleSignInConfig, facebookSignInConfig, zzp);
    }
    
    public SignInConfiguration[] zzaJ(final int n) {
        return new SignInConfiguration[n];
    }
}
