// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aa implements Parcelable$Creator<UpdateMetadataRequest>
{
    static void a(final UpdateMetadataRequest updateMetadataRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, updateMetadataRequest.kg);
        b.a(parcel, 2, (Parcelable)updateMetadataRequest.rr, n, false);
        b.a(parcel, 3, (Parcelable)updateMetadataRequest.rB, n, false);
        b.D(parcel, o);
    }
    
    public UpdateMetadataRequest O(final Parcel parcel) {
        MetadataBundle metadataBundle = null;
        final int n = a.n(parcel);
        int g = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, m, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    metadataBundle = a.a(parcel, m, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new UpdateMetadataRequest(g, driveId, metadataBundle);
    }
    
    public UpdateMetadataRequest[] ao(final int n) {
        return new UpdateMetadataRequest[n];
    }
}
