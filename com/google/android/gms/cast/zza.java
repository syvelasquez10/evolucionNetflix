// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.net.Uri;
import com.google.android.gms.common.images.WebImage;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ApplicationMetadata>
{
    static void zza(final ApplicationMetadata applicationMetadata, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, applicationMetadata.getVersionCode());
        zzb.zza(parcel, 2, applicationMetadata.getApplicationId(), false);
        zzb.zza(parcel, 3, applicationMetadata.getName(), false);
        zzb.zzc(parcel, 4, applicationMetadata.getImages(), false);
        zzb.zzb(parcel, 5, applicationMetadata.zzUN, false);
        zzb.zza(parcel, 6, applicationMetadata.getSenderAppIdentifier(), false);
        zzb.zza(parcel, 7, (Parcelable)applicationMetadata.zzmj(), n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public ApplicationMetadata zzW(final Parcel parcel) {
        Uri uri = null;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        int zzg = 0;
        String zzp = null;
        List<String> zzD = null;
        List<WebImage> zzc = null;
        String zzp2 = null;
        String zzp3 = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao, WebImage.CREATOR);
                    continue;
                }
                case 5: {
                    zzD = com.google.android.gms.common.internal.safeparcel.zza.zzD(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ApplicationMetadata(zzg, zzp3, zzp2, zzc, zzD, zzp, uri);
    }
    
    public ApplicationMetadata[] zzaN(final int n) {
        return new ApplicationMetadata[n];
    }
}
