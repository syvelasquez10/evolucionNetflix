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
    static void zza(final ConnectionEvent connectionEvent, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, connectionEvent.mVersionCode);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 4, connectionEvent.zzpv(), false);
        zzb.zza(parcel, 5, connectionEvent.zzpw(), false);
        zzb.zza(parcel, 6, connectionEvent.zzpx(), false);
        zzb.zza(parcel, 7, connectionEvent.zzpy(), false);
        zzb.zza(parcel, 8, connectionEvent.zzpz(), false);
        zzb.zza(parcel, 10, connectionEvent.zzpC());
        zzb.zza(parcel, 11, connectionEvent.zzpB());
        zzb.zzc(parcel, 12, connectionEvent.getEventType());
        zzb.zza(parcel, 13, connectionEvent.zzpA(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public ConnectionEvent zzau(final Parcel parcel) {
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
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
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzo4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzo5 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 10: {
                    zzi2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzai);
                    continue;
                }
                case 11: {
                    zzi3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzai);
                    continue;
                }
                case 12: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 13: {
                    zzo6 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ConnectionEvent(zzg, zzi, zzg2, zzo, zzo2, zzo3, zzo4, zzo5, zzo6, zzi2, zzi3);
    }
    
    public ConnectionEvent[] zzbT(final int n) {
        return new ConnectionEvent[n];
    }
}
