// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<FacebookSignInConfig>
{
    static void zza(final FacebookSignInConfig facebookSignInConfig, final Parcel parcel, final int n) {
        final int zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, facebookSignInConfig.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)facebookSignInConfig.zzlD(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, 3, facebookSignInConfig.zzlE(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }
    
    public FacebookSignInConfig zzO(final Parcel parcel) {
        ArrayList<String> zzC = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        Intent intent = null;
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
                    intent = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
                    continue;
                }
                case 3: {
                    zzC = zza.zzC(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new FacebookSignInConfig(zzg, intent, zzC);
    }
    
    public FacebookSignInConfig[] zzaE(final int n) {
        return new FacebookSignInConfig[n];
    }
}
