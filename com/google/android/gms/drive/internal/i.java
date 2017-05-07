// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<CreateFileIntentSenderRequest>
{
    static void a(final CreateFileIntentSenderRequest createFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, createFileIntentSenderRequest.BR);
        b.a(parcel, 2, (Parcelable)createFileIntentSenderRequest.Od, n, false);
        b.c(parcel, 3, createFileIntentSenderRequest.uQ);
        b.a(parcel, 4, createFileIntentSenderRequest.No, false);
        b.a(parcel, 5, (Parcelable)createFileIntentSenderRequest.Nq, n, false);
        b.a(parcel, 6, createFileIntentSenderRequest.Oe, false);
        b.H(parcel, d);
    }
    
    public CreateFileIntentSenderRequest ab(final Parcel parcel) {
        int g = 0;
        Integer h = null;
        final int c = a.C(parcel);
        DriveId driveId = null;
        String o = null;
        MetadataBundle metadataBundle = null;
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
                    metadataBundle = a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    driveId = a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 6: {
                    h = a.h(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new CreateFileIntentSenderRequest(g2, metadataBundle, g, o, driveId, h);
    }
    
    public CreateFileIntentSenderRequest[] bl(final int n) {
        return new CreateFileIntentSenderRequest[n];
    }
}
