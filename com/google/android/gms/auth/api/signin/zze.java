// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<GoogleSignInConfig>
{
    static void zza(final GoogleSignInConfig googleSignInConfig, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, googleSignInConfig.versionCode);
        zzb.zzc(parcel, 2, googleSignInConfig.zzlS(), false);
        zzb.zza(parcel, 3, (Parcelable)googleSignInConfig.getAccount(), n, false);
        zzb.zza(parcel, 4, googleSignInConfig.zzlY());
        zzb.zza(parcel, 5, googleSignInConfig.zzlZ());
        zzb.zza(parcel, 6, googleSignInConfig.zzma());
        zzb.zza(parcel, 7, googleSignInConfig.zzmb(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public GoogleSignInConfig zzR(final Parcel parcel) {
        String zzp = null;
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        boolean zzc2 = false;
        boolean zzc3 = false;
        Account account = null;
        ArrayList<Scope> zzc4 = null;
        int zzg = 0;
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
                    zzc4 = zza.zzc(parcel, zzao, Scope.CREATOR);
                    continue;
                }
                case 3: {
                    account = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 4: {
                    zzc3 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzao);
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
        return new GoogleSignInConfig(zzg, zzc4, account, zzc3, zzc2, zzc, zzp);
    }
    
    public GoogleSignInConfig[] zzaI(final int n) {
        return new GoogleSignInConfig[n];
    }
}
