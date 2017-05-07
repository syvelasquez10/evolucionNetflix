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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, applicationMetadata.getVersionCode());
        zzb.zza(parcel, 2, applicationMetadata.getApplicationId(), false);
        zzb.zza(parcel, 3, applicationMetadata.getName(), false);
        zzb.zzc(parcel, 4, applicationMetadata.getImages(), false);
        zzb.zzb(parcel, 5, applicationMetadata.zzSY, false);
        zzb.zza(parcel, 6, applicationMetadata.getSenderAppIdentifier(), false);
        zzb.zza(parcel, 7, (Parcelable)applicationMetadata.zzlM(), n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public ApplicationMetadata zzR(final Parcel parcel) {
        Uri uri = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        String zzo = null;
        List<String> zzC = null;
        List<WebImage> zzc = null;
        String zzo2 = null;
        String zzo3 = null;
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
                    zzo3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzai, WebImage.CREATOR);
                    continue;
                }
                case 5: {
                    zzC = com.google.android.gms.common.internal.safeparcel.zza.zzC(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ApplicationMetadata(zzg, zzo3, zzo2, zzc, zzC, zzo, uri);
    }
    
    public ApplicationMetadata[] zzaH(final int n) {
        return new ApplicationMetadata[n];
    }
}
