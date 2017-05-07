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

public class d implements Parcelable$Creator<CreateFileIntentSenderRequest>
{
    static void a(final CreateFileIntentSenderRequest createFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, createFileIntentSenderRequest.kg);
        b.a(parcel, 2, (Parcelable)createFileIntentSenderRequest.qZ, n, false);
        b.c(parcel, 3, createFileIntentSenderRequest.qE);
        b.a(parcel, 4, createFileIntentSenderRequest.qL, false);
        b.a(parcel, 5, (Parcelable)createFileIntentSenderRequest.qM, n, false);
        b.D(parcel, o);
    }
    
    public CreateFileIntentSenderRequest C(final Parcel parcel) {
        int g = 0;
        DriveId driveId = null;
        final int n = a.n(parcel);
        String m = null;
        MetadataBundle metadataBundle = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    metadataBundle = a.a(parcel, i, MetadataBundle.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 5: {
                    driveId = a.a(parcel, i, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CreateFileIntentSenderRequest(g2, metadataBundle, g, m, driveId);
    }
    
    public CreateFileIntentSenderRequest[] ac(final int n) {
        return new CreateFileIntentSenderRequest[n];
    }
}
