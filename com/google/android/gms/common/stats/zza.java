// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ConnectionEvent>
{
    static void zza(final ConnectionEvent connectionEvent, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, connectionEvent.zzCY);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 4, connectionEvent.zzoE(), false);
        zzb.zza(parcel, 5, connectionEvent.zzoF(), false);
        zzb.zza(parcel, 6, connectionEvent.zzoG(), false);
        zzb.zza(parcel, 7, connectionEvent.zzoH(), false);
        zzb.zza(parcel, 8, connectionEvent.zzoI(), false);
        zzb.zza(parcel, 10, connectionEvent.zzoL());
        zzb.zza(parcel, 11, connectionEvent.zzoK());
        zzb.zzc(parcel, 12, connectionEvent.getEventType());
        zzb.zza(parcel, 13, connectionEvent.zzoJ(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public ConnectionEvent zzam(final Parcel parcel) {
        final int zzab = com.google.android.gms.common.internal.safeparcel.zza.zzab(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzo = null;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        String zzo5 = null;
        String zzo6 = null;
        long zzi2 = 0L;
        long zzi3 = 0L;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = com.google.android.gms.common.internal.safeparcel.zza.zzaa(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbA(zzaa)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 6: {
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 7: {
                    zzo4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 8: {
                    zzo5 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
                case 10: {
                    zzi2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzaa);
                    continue;
                }
                case 11: {
                    zzi3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzaa);
                    continue;
                }
                case 12: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzaa);
                    continue;
                }
                case 13: {
                    zzo6 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ConnectionEvent(zzg, zzi, zzg2, zzo, zzo2, zzo3, zzo4, zzo5, zzo6, zzi2, zzi3);
    }
    
    public ConnectionEvent[] zzbM(final int n) {
        return new ConnectionEvent[n];
    }
}
