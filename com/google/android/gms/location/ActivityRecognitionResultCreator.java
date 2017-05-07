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
    
    static void a(final ActivityRecognitionResult activityRecognitionResult, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, activityRecognitionResult.adQ, false);
        b.c(parcel, 1000, activityRecognitionResult.getVersionCode());
        b.a(parcel, 2, activityRecognitionResult.adR);
        b.a(parcel, 3, activityRecognitionResult.adS);
        b.H(parcel, d);
    }
    
    public ActivityRecognitionResult createFromParcel(final Parcel parcel) {
        long i = 0L;
        final int c = a.C(parcel);
        int g = 0;
        List<DetectedActivity> c2 = null;
        long j = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<DetectedActivity>)DetectedActivity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ActivityRecognitionResult(g, c2, j, i);
    }
    
    public ActivityRecognitionResult[] newArray(final int n) {
        return new ActivityRecognitionResult[n];
    }
}
