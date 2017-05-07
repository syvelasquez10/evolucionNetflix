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

public class e implements Parcelable$Creator<CreateFileRequest>
{
    static void a(final CreateFileRequest createFileRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, createFileRequest.kg);
        b.a(parcel, 2, (Parcelable)createFileRequest.ra, n, false);
        b.a(parcel, 3, (Parcelable)createFileRequest.qZ, n, false);
        b.a(parcel, 4, (Parcelable)createFileRequest.qX, n, false);
        b.D(parcel, o);
    }
    
    public CreateFileRequest D(final Parcel parcel) {
        Contents contents = null;
        final int n = a.n(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            DriveId driveId2 = null;
            MetadataBundle metadataBundle3 = null;
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    final MetadataBundle metadataBundle2 = metadataBundle;
                    driveId2 = driveId;
                    metadataBundle3 = metadataBundle2;
                    break;
                }
                case 1: {
                    g = a.g(parcel, m);
                    final DriveId driveId3 = driveId;
                    metadataBundle3 = metadataBundle;
                    driveId2 = driveId3;
                    break;
                }
                case 2: {
                    final DriveId driveId4 = a.a(parcel, m, DriveId.CREATOR);
                    metadataBundle3 = metadataBundle;
                    driveId2 = driveId4;
                    break;
                }
                case 3: {
                    final MetadataBundle metadataBundle4 = a.a(parcel, m, MetadataBundle.CREATOR);
                    driveId2 = driveId;
                    metadataBundle3 = metadataBundle4;
                    break;
                }
                case 4: {
                    contents = a.a(parcel, m, Contents.CREATOR);
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
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CreateFileRequest(g, driveId, metadataBundle, contents);
    }
    
    public CreateFileRequest[] ad(final int n) {
        return new CreateFileRequest[n];
    }
}
