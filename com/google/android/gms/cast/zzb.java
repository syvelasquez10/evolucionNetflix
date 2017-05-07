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
    static void zza(final CastDevice castDevice, final Parcel parcel, int zzaq) {
        zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, castDevice.zzVe, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, castDevice.getCapabilities());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 10, castDevice.getStatus());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public CastDevice zzX(final Parcel parcel) {
        int zzg = 0;
        List<WebImage> zzc = null;
        final int zzap = zza.zzap(parcel);
        int zzg2 = 0;
        int zzg3 = 0;
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg4 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp5 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzao, WebImage.CREATOR);
                    continue;
                }
                case 9: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 10: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new CastDevice(zzg4, zzp5, zzp4, zzp3, zzp2, zzp, zzg3, zzc, zzg2, zzg);
    }
    
    public CastDevice[] zzaO(final int n) {
        return new CastDevice[n];
    }
}
