// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import java.util.ArrayList;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<CompletionEvent>
{
    static void a(final CompletionEvent completionEvent, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, completionEvent.BR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)completionEvent.MO, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, completionEvent.Dd, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable)completionEvent.NF, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable)completionEvent.NG, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable)completionEvent.NH, n, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 7, completionEvent.NI, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, completionEvent.Fa);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, completionEvent.NJ, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public CompletionEvent U(final Parcel parcel) {
        int g = 0;
        IBinder p = null;
        final int c = a.C(parcel);
        ArrayList<String> c2 = null;
        MetadataBundle metadataBundle = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        String o = null;
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
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    parcelFileDescriptor2 = a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 5: {
                    parcelFileDescriptor = a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 6: {
                    metadataBundle = a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
                case 7: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 8: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 9: {
                    p = a.p(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new CompletionEvent(g2, driveId, o, parcelFileDescriptor2, parcelFileDescriptor, metadataBundle, c2, g, p);
    }
    
    public CompletionEvent[] bb(final int n) {
        return new CompletionEvent[n];
    }
}
