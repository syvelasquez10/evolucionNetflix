// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.firstparty.shared;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<FACLConfig>
{
    static void zza(final FACLConfig faclConfig, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, faclConfig.version);
        zzb.zza(parcel, 2, faclConfig.zzTx);
        zzb.zza(parcel, 3, faclConfig.zzTy, false);
        zzb.zza(parcel, 4, faclConfig.zzTz);
        zzb.zza(parcel, 5, faclConfig.zzTA);
        zzb.zza(parcel, 6, faclConfig.zzTB);
        zzb.zza(parcel, 7, faclConfig.zzTC);
        zzb.zzI(parcel, zzaq);
    }
    
    public FACLConfig zzT(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        String zzp = null;
        boolean zzc2 = false;
        boolean zzc3 = false;
        boolean zzc4 = false;
        boolean zzc5 = false;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzc5 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc4 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzc2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new FACLConfig(zzg, zzc5, zzp, zzc4, zzc3, zzc2, zzc);
    }
    
    public FACLConfig[] zzaK(final int n) {
        return new FACLConfig[n];
    }
}
