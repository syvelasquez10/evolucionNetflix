// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class DetectedActivityCreator implements Parcelable$Creator<DetectedActivity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final DetectedActivity detectedActivity, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, detectedActivity.adU);
        b.c(parcel, 1000, detectedActivity.getVersionCode());
        b.c(parcel, 2, detectedActivity.adV);
        b.H(parcel, d);
    }
    
    public DetectedActivity createFromParcel(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        int g2 = 0;
        int g3 = 0;
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
                case 1000: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new DetectedActivity(g3, g2, g);
    }
    
    public DetectedActivity[] newArray(final int n) {
        return new DetectedActivity[n];
    }
}
