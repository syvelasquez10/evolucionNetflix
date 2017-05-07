// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<DrivePreferences>
{
    static void a(final DrivePreferences drivePreferences, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, drivePreferences.BR);
        b.a(parcel, 2, drivePreferences.Ne);
        b.H(parcel, d);
    }
    
    public DrivePreferences P(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new DrivePreferences(g, c);
    }
    
    public DrivePreferences[] aU(final int n) {
        return new DrivePreferences[n];
    }
}
