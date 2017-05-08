// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzn implements Parcelable$Creator<LocationRequestUpdateData>
{
    static void zza(final LocationRequestUpdateData locationRequestUpdateData, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationRequestUpdateData.zzaFJ);
        zzb.zzc(parcel, 1000, locationRequestUpdateData.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)locationRequestUpdateData.zzaFK, n, false);
        zzb.zza(parcel, 3, locationRequestUpdateData.zzwF(), false);
        zzb.zza(parcel, 4, (Parcelable)locationRequestUpdateData.mPendingIntent, n, false);
        zzb.zza(parcel, 5, locationRequestUpdateData.zzwG(), false);
        zzb.zza(parcel, 6, locationRequestUpdateData.zzwH(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationRequestUpdateData zzeI(final Parcel parcel) {
        IBinder zzq = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        int zzg2 = 1;
        IBinder zzq2 = null;
        PendingIntent pendingIntent = null;
        IBinder zzq3 = null;
        LocationRequestInternal locationRequestInternal = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    locationRequestInternal = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<LocationRequestInternal>)LocationRequestInternal.CREATOR);
                    continue;
                }
                case 3: {
                    zzq3 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 4: {
                    pendingIntent = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 5: {
                    zzq2 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzq = zza.zzq(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationRequestUpdateData(zzg, zzg2, locationRequestInternal, zzq3, pendingIntent, zzq2, zzq);
    }
    
    public LocationRequestUpdateData[] zzhb(final int n) {
        return new LocationRequestUpdateData[n];
    }
}
