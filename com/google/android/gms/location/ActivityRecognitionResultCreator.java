// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ActivityRecognitionResultCreator implements Parcelable$Creator<ActivityRecognitionResult>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final ActivityRecognitionResult activityRecognitionResult, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.b(parcel, 1, activityRecognitionResult.xm, false);
        b.c(parcel, 1000, activityRecognitionResult.getVersionCode());
        b.a(parcel, 2, activityRecognitionResult.xn);
        b.a(parcel, 3, activityRecognitionResult.xo);
        b.D(parcel, o);
    }
    
    public ActivityRecognitionResult createFromParcel(final Parcel parcel) {
        long h = 0L;
        final int n = a.n(parcel);
        int g = 0;
        List<DetectedActivity> c = null;
        long h2 = 0L;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    c = a.c(parcel, m, (android.os.Parcelable$Creator<DetectedActivity>)DetectedActivity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    h2 = a.h(parcel, m);
                    continue;
                }
                case 3: {
                    h = a.h(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ActivityRecognitionResult(g, c, h2, h);
    }
    
    public ActivityRecognitionResult[] newArray(final int n) {
        return new ActivityRecognitionResult[n];
    }
}
