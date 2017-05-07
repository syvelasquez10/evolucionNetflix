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

public class g implements Parcelable$Creator<CreateFileIntentSenderRequest>
{
    static void a(final CreateFileIntentSenderRequest createFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, createFileIntentSenderRequest.xH);
        b.a(parcel, 2, (Parcelable)createFileIntentSenderRequest.EZ, n, false);
        b.c(parcel, 3, createFileIntentSenderRequest.Eu);
        b.a(parcel, 4, createFileIntentSenderRequest.EB, false);
        b.a(parcel, 5, (Parcelable)createFileIntentSenderRequest.EC, n, false);
        b.F(parcel, p3);
    }
    
    public CreateFileIntentSenderRequest H(final Parcel parcel) {
        int g = 0;
        DriveId driveId = null;
        final int o = a.o(parcel);
        String n = null;
        MetadataBundle metadataBundle = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    metadataBundle = a.a(parcel, n2, MetadataBundle.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 4: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 5: {
                    driveId = a.a(parcel, n2, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CreateFileIntentSenderRequest(g2, metadataBundle, g, n, driveId);
    }
    
    public CreateFileIntentSenderRequest[] al(final int n) {
        return new CreateFileIntentSenderRequest[n];
    }
}
