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

public class j implements Parcelable$Creator<CreateFileRequest>
{
    static void a(final CreateFileRequest createFileRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, createFileRequest.BR);
        b.a(parcel, 2, (Parcelable)createFileRequest.Of, n, false);
        b.a(parcel, 3, (Parcelable)createFileRequest.Od, n, false);
        b.a(parcel, 4, (Parcelable)createFileRequest.NX, n, false);
        b.a(parcel, 5, createFileRequest.Oe, false);
        b.a(parcel, 6, createFileRequest.Og);
        b.a(parcel, 7, createFileRequest.Nf, false);
        b.c(parcel, 8, createFileRequest.Oh);
        b.c(parcel, 9, createFileRequest.Oi);
        b.H(parcel, d);
    }
    
    public CreateFileRequest ac(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        int g2 = 0;
        boolean c2 = false;
        Integer h = null;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
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
                    h = a.h(parcel, b);
                    continue;
                }
                case 6: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 9: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new CreateFileRequest(g3, driveId, metadataBundle, contents, h, c2, o, g2, g);
    }
    
    public CreateFileRequest[] bm(final int n) {
        return new CreateFileRequest[n];
    }
}
