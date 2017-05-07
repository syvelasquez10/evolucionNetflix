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
    
    static void a(final DetectedActivity detectedActivity, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, detectedActivity.xp);
        b.c(parcel, 1000, detectedActivity.getVersionCode());
        b.c(parcel, 2, detectedActivity.xq);
        b.D(parcel, o);
    }
    
    public DetectedActivity createFromParcel(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new DetectedActivity(g3, g2, g);
    }
    
    public DetectedActivity[] newArray(final int n) {
        return new DetectedActivity[n];
    }
}
