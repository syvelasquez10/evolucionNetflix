// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<LaunchOptions>
{
    static void a(final LaunchOptions launchOptions, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, launchOptions.getVersionCode());
        b.a(parcel, 2, launchOptions.getRelaunchIfRunning());
        b.a(parcel, 3, launchOptions.getLanguage(), false);
        b.H(parcel, d);
    }
    
    public LaunchOptions[] Z(final int n) {
        return new LaunchOptions[n];
    }
    
    public LaunchOptions v(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        String o = null;
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
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new LaunchOptions(g, c, o);
    }
}
