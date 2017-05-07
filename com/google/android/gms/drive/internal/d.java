// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<CloseContentsAndUpdateMetadataRequest>
{
    static void a(final CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, closeContentsAndUpdateMetadataRequest.xH);
        b.a(parcel, 2, (Parcelable)closeContentsAndUpdateMetadataRequest.EV, n, false);
        b.a(parcel, 3, (Parcelable)closeContentsAndUpdateMetadataRequest.EW, n, false);
        b.a(parcel, 4, (Parcelable)closeContentsAndUpdateMetadataRequest.EX, n, false);
        b.F(parcel, p3);
    }
    
    public CloseContentsAndUpdateMetadataRequest E(final Parcel parcel) {
        Contents contents = null;
        final int o = a.o(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            DriveId driveId2 = null;
            MetadataBundle metadataBundle3 = null;
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    final MetadataBundle metadataBundle2 = metadataBundle;
                    driveId2 = driveId;
                    metadataBundle3 = metadataBundle2;
                    break;
                }
                case 1: {
                    g = a.g(parcel, n);
                    final DriveId driveId3 = driveId;
                    metadataBundle3 = metadataBundle;
                    driveId2 = driveId3;
                    break;
                }
                case 2: {
                    final DriveId driveId4 = a.a(parcel, n, DriveId.CREATOR);
                    metadataBundle3 = metadataBundle;
                    driveId2 = driveId4;
                    break;
                }
                case 3: {
                    final MetadataBundle metadataBundle4 = a.a(parcel, n, MetadataBundle.CREATOR);
                    driveId2 = driveId;
                    metadataBundle3 = metadataBundle4;
                    break;
                }
                case 4: {
                    contents = a.a(parcel, n, Contents.CREATOR);
                    final DriveId driveId5 = driveId;
                    metadataBundle3 = metadataBundle;
                    driveId2 = driveId5;
                    break;
                }
            }
            final DriveId driveId6 = driveId2;
            metadataBundle = metadataBundle3;
            driveId = driveId6;
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CloseContentsAndUpdateMetadataRequest(g, driveId, metadataBundle, contents);
    }
    
    public CloseContentsAndUpdateMetadataRequest[] ai(final int n) {
        return new CloseContentsAndUpdateMetadataRequest[n];
    }
}
