// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<CastDevice>
{
    static void zza(final CastDevice castDevice, final Parcel parcel, int zzac) {
        zzac = com.google.android.gms.common.internal.safeparcel.zzb.zzac(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, castDevice.zzQL, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, castDevice.getCapabilities());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 10, castDevice.getStatus());
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzac);
    }
    
    public CastDevice zzK(final Parcel parcel) {
        int zzg = 0;
        List<WebImage> zzc = null;
        final int zzab = zza.zzab(parcel);
        int zzg2 = 0;
        int zzg3 = 0;
        String zzo = null;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        String zzo5 = null;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg4 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzo5 = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzo4 = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzo3 = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzo2 = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 6: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzaa, WebImage.CREATOR);
                    continue;
                }
                case 9: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 10: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new CastDevice(zzg4, zzo5, zzo4, zzo3, zzo2, zzo, zzg3, zzc, zzg2, zzg);
    }
    
    public CastDevice[] zzaA(final int n) {
        return new CastDevice[n];
    }
}
