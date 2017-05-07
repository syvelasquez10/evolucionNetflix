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
    static void zza(final GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, gInAppPurchaseManagerInfoParcel.versionCode);
        zzb.zza(parcel, 3, gInAppPurchaseManagerInfoParcel.zzfd(), false);
        zzb.zza(parcel, 4, gInAppPurchaseManagerInfoParcel.zzfe(), false);
        zzb.zza(parcel, 5, gInAppPurchaseManagerInfoParcel.zzff(), false);
        zzb.zza(parcel, 6, gInAppPurchaseManagerInfoParcel.zzfc(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public GInAppPurchaseManagerInfoParcel zzi(final Parcel parcel) {
        IBinder zzp = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        IBinder zzp2 = null;
        IBinder zzp3 = null;
        IBinder zzp4 = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzp4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzp3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new GInAppPurchaseManagerInfoParcel(zzg, zzp4, zzp3, zzp2, zzp);
    }
    
    public GInAppPurchaseManagerInfoParcel[] zzx(final int n) {
        return new GInAppPurchaseManagerInfoParcel[n];
    }
}
