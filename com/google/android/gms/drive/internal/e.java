// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<CloseContentsAndUpdateMetadataRequest>
{
    static void a(final CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, closeContentsAndUpdateMetadataRequest.BR);
        b.a(parcel, 2, (Parcelable)closeContentsAndUpdateMetadataRequest.NV, n, false);
        b.a(parcel, 3, (Parcelable)closeContentsAndUpdateMetadataRequest.NW, n, false);
        b.a(parcel, 4, (Parcelable)closeContentsAndUpdateMetadataRequest.NX, n, false);
        b.a(parcel, 5, closeContentsAndUpdateMetadataRequest.Ng);
        b.a(parcel, 6, closeContentsAndUpdateMetadataRequest.Nf, false);
        b.c(parcel, 7, closeContentsAndUpdateMetadataRequest.NY);
        b.H(parcel, d);
    }
    
    public CloseContentsAndUpdateMetadataRequest Y(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        boolean c2 = false;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    metadataBundle = a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
                case 4: {
                    contents = a.a(parcel, b, Contents.CREATOR);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new CloseContentsAndUpdateMetadataRequest(g2, driveId, metadataBundle, contents, c2, o, g);
    }
    
    public CloseContentsAndUpdateMetadataRequest[] bh(final int n) {
        return new CloseContentsAndUpdateMetadataRequest[n];
    }
}
