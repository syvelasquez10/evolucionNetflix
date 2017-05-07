// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<GoogleSignInConfig>
{
    static void zza(final GoogleSignInConfig googleSignInConfig, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, googleSignInConfig.versionCode);
        zzb.zzc(parcel, 2, googleSignInConfig.zzlE(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public GoogleSignInConfig zzP(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        ArrayList<Scope> zzc = null;
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
                    zzc = zza.zzc(parcel, zzai, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new GoogleSignInConfig(zzg, zzc);
    }
    
    public GoogleSignInConfig[] zzaF(final int n) {
        return new GoogleSignInConfig[n];
    }
}
