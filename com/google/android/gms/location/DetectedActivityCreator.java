// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class DetectedActivityCreator implements Parcelable$Creator<DetectedActivity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final DetectedActivity detectedActivity, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, detectedActivity.NS);
        b.c(parcel, 1000, detectedActivity.getVersionCode());
        b.c(parcel, 2, detectedActivity.NT);
        b.F(parcel, p3);
    }
    
    public DetectedActivity createFromParcel(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new DetectedActivity(g3, g2, g);
    }
    
    public DetectedActivity[] newArray(final int n) {
        return new DetectedActivity[n];
    }
}
