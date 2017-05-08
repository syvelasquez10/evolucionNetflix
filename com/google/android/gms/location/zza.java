// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.location.internal.ParcelableGeofence;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<GeofencingRequest>
{
    static void zza(final GeofencingRequest geofencingRequest, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, geofencingRequest.zzwv(), false);
        zzb.zzc(parcel, 1000, geofencingRequest.getVersionCode());
        zzb.zzc(parcel, 2, geofencingRequest.getInitialTrigger());
        zzb.zzI(parcel, zzaq);
    }
    
    public GeofencingRequest zzez(final Parcel parcel) {
        int zzg = 0;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        Object zzc = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao, (android.os.Parcelable$Creator<Object>)ParcelableGeofence.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new GeofencingRequest(zzg2, (List<ParcelableGeofence>)zzc, zzg);
    }
    
    public GeofencingRequest[] zzgN(final int n) {
        return new GeofencingRequest[n];
    }
}
