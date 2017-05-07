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
    static void zza(final CastDevice castDevice, final Parcel parcel, int zzak) {
        zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, castDevice.zzTo, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, castDevice.getCapabilities());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 10, castDevice.getStatus());
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }
    
    public CastDevice zzS(final Parcel parcel) {
        int zzg = 0;
        List<WebImage> zzc = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg2 = 0;
        int zzg3 = 0;
        String zzo = null;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        String zzo5 = null;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg4 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo5 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo4 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzai, WebImage.CREATOR);
                    continue;
                }
                case 9: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 10: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new CastDevice(zzg4, zzo5, zzo4, zzo3, zzo2, zzo, zzg3, zzc, zzg2, zzg);
    }
    
    public CastDevice[] zzaI(final int n) {
        return new CastDevice[n];
    }
}
