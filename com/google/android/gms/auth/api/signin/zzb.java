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
        final int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, facebookSignInConfig.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)facebookSignInConfig.zzlR(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, 3, facebookSignInConfig.zzlS(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public FacebookSignInConfig zzP(final Parcel parcel) {
        ArrayList<String> zzD = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Intent intent = null;
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
                    intent = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
                    continue;
                }
                case 3: {
                    zzD = zza.zzD(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new FacebookSignInConfig(zzg, intent, zzD);
    }
    
    public FacebookSignInConfig[] zzaG(final int n) {
        return new FacebookSignInConfig[n];
    }
}
