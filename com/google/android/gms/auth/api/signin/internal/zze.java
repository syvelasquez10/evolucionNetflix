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

public class zze implements Parcelable$Creator<SignInConfiguration>
{
    static void zza(final SignInConfiguration signInConfiguration, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, signInConfiguration.versionCode);
        zzb.zza(parcel, 2, signInConfiguration.zzlF(), false);
        zzb.zza(parcel, 3, signInConfiguration.zzlG(), false);
        zzb.zza(parcel, 4, (Parcelable)signInConfiguration.zzlH(), n, false);
        zzb.zza(parcel, 5, (Parcelable)signInConfiguration.zzlI(), n, false);
        zzb.zza(parcel, 6, (Parcelable)signInConfiguration.zzlJ(), n, false);
        zzb.zza(parcel, 7, signInConfiguration.zzlK(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public SignInConfiguration zzQ(final Parcel parcel) {
        String zzo = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        FacebookSignInConfig facebookSignInConfig = null;
        GoogleSignInConfig googleSignInConfig = null;
        EmailSignInConfig emailSignInConfig = null;
        String zzo2 = null;
        String zzo3 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    emailSignInConfig = zza.zza(parcel, zzai, EmailSignInConfig.CREATOR);
                    continue;
                }
                case 5: {
                    googleSignInConfig = zza.zza(parcel, zzai, GoogleSignInConfig.CREATOR);
                    continue;
                }
                case 6: {
                    facebookSignInConfig = zza.zza(parcel, zzai, FacebookSignInConfig.CREATOR);
                    continue;
                }
                case 7: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new SignInConfiguration(zzg, zzo3, zzo2, emailSignInConfig, googleSignInConfig, facebookSignInConfig, zzo);
    }
    
    public SignInConfiguration[] zzaG(final int n) {
        return new SignInConfiguration[n];
    }
}
