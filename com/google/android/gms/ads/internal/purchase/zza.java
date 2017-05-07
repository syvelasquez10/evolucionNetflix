// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<GInAppPurchaseManagerInfoParcel>
{
    static void zza(final GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, gInAppPurchaseManagerInfoParcel.versionCode);
        zzb.zza(parcel, 3, gInAppPurchaseManagerInfoParcel.zzfj(), false);
        zzb.zza(parcel, 4, gInAppPurchaseManagerInfoParcel.zzfk(), false);
        zzb.zza(parcel, 5, gInAppPurchaseManagerInfoParcel.zzfl(), false);
        zzb.zza(parcel, 6, gInAppPurchaseManagerInfoParcel.zzfi(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public GInAppPurchaseManagerInfoParcel zzh(final Parcel parcel) {
        IBinder zzq = null;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        int zzg = 0;
        IBinder zzq2 = null;
        IBinder zzq3 = null;
        IBinder zzq4 = null;
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
                case 3: {
                    zzq4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzq3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzq2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new GInAppPurchaseManagerInfoParcel(zzg, zzq4, zzq3, zzq2, zzq);
    }
    
    public GInAppPurchaseManagerInfoParcel[] zzx(final int n) {
        return new GInAppPurchaseManagerInfoParcel[n];
    }
}
