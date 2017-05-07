// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<WebImage>
{
    static void zza(final WebImage webImage, final Parcel parcel, final int n) {
        final int zzac = com.google.android.gms.common.internal.safeparcel.zzb.zzac(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)webImage.getUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzac);
    }
    
    public WebImage zzT(final Parcel parcel) {
        int zzg = 0;
        final int zzab = zza.zzab(parcel);
        Uri uri = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    uri = zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new WebImage(zzg3, uri, zzg2, zzg);
    }
    
    public WebImage[] zzbn(final int n) {
        return new WebImage[n];
    }
}
