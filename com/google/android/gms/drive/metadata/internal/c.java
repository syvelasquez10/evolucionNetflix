// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<CustomProperty>
{
    static void a(final CustomProperty customProperty, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, customProperty.BR);
        b.a(parcel, 2, (Parcelable)customProperty.PB, n, false);
        b.a(parcel, 3, customProperty.mValue, false);
        b.H(parcel, d);
    }
    
    public CustomProperty aG(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        CustomPropertyKey customPropertyKey = null;
        while (parcel.dataPosition() < c) {
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
                    customPropertyKey = a.a(parcel, b, CustomPropertyKey.CREATOR);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new CustomProperty(g, customPropertyKey, o);
    }
    
    public CustomProperty[] bS(final int n) {
        return new CustomProperty[n];
    }
}
