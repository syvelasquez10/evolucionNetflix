// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<ConnectionResult>
{
    static void zza(final ConnectionResult connectionResult, final Parcel parcel, final int n) {
        final int zzac = com.google.android.gms.common.internal.safeparcel.zzb.zzac(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, connectionResult.zzCY);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)connectionResult.getResolution(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzac);
    }
    
    public ConnectionResult zzO(final Parcel parcel) {
        int zzg = 0;
        final int zzab = zza.zzab(parcel);
        PendingIntent pendingIntent = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 3: {
                    pendingIntent = zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ConnectionResult(zzg2, zzg, pendingIntent);
    }
    
    public ConnectionResult[] zzaS(final int n) {
        return new ConnectionResult[n];
    }
}
