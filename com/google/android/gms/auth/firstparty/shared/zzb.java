// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.firstparty.shared;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<FACLData>
{
    static void zza(final FACLData faclData, final Parcel parcel, final int n) {
        final int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, faclData.version);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)faclData.zzTD, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, faclData.zzTE, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, faclData.zzTF);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, faclData.zzTG, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public FACLData zzU(final Parcel parcel) {
        boolean zzc = false;
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        String zzp2 = null;
        FACLConfig faclConfig = null;
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
                    faclConfig = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<FACLConfig>)FACLConfig.CREATOR);
                    continue;
                }
                case 3: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new FACLData(zzg, faclConfig, zzp2, zzc, zzp);
    }
    
    public FACLData[] zzaL(final int n) {
        return new FACLData[n];
    }
}
