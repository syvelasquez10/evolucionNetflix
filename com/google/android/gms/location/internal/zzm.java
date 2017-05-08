// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzm implements Parcelable$Creator<LocationRequestInternal>
{
    static void zza(final LocationRequestInternal locationRequestInternal, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, (Parcelable)locationRequestInternal.zzasN, n, false);
        zzb.zzc(parcel, 1000, locationRequestInternal.getVersionCode());
        zzb.zza(parcel, 2, locationRequestInternal.zzaFE);
        zzb.zza(parcel, 3, locationRequestInternal.zzaFF);
        zzb.zza(parcel, 4, locationRequestInternal.zzaFG);
        zzb.zzc(parcel, 5, locationRequestInternal.zzaFH, false);
        zzb.zza(parcel, 6, locationRequestInternal.mTag, false);
        zzb.zza(parcel, 7, locationRequestInternal.zzaFI);
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationRequestInternal zzeH(final Parcel parcel) {
        String zzp = null;
        boolean zzc = true;
        boolean zzc2 = false;
        final int zzap = zza.zzap(parcel);
        Object o = LocationRequestInternal.zzaFD;
        boolean zzc3 = true;
        boolean zzc4 = false;
        LocationRequest locationRequest = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    locationRequest = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<LocationRequest>)LocationRequest.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzc4 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc3 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    o = zza.zzc(parcel, zzao, (android.os.Parcelable$Creator<Object>)ClientIdentity.CREATOR);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationRequestInternal(zzg, locationRequest, zzc4, zzc3, zzc, (List<ClientIdentity>)o, zzp, zzc2);
    }
    
    public LocationRequestInternal[] zzha(final int n) {
        return new LocationRequestInternal[n];
    }
}
