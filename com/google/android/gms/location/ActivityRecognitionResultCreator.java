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
    
    static void a(final ActivityRecognitionResult activityRecognitionResult, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.b(parcel, 1, activityRecognitionResult.NP, false);
        b.c(parcel, 1000, activityRecognitionResult.getVersionCode());
        b.a(parcel, 2, activityRecognitionResult.NQ);
        b.a(parcel, 3, activityRecognitionResult.NR);
        b.F(parcel, p3);
    }
    
    public ActivityRecognitionResult createFromParcel(final Parcel parcel) {
        long i = 0L;
        final int o = a.o(parcel);
        int g = 0;
        List<DetectedActivity> c = null;
        long j = 0L;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    c = a.c(parcel, n, (android.os.Parcelable$Creator<DetectedActivity>)DetectedActivity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, n);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ActivityRecognitionResult(g, c, j, i);
    }
    
    public ActivityRecognitionResult[] newArray(final int n) {
        return new ActivityRecognitionResult[n];
    }
}
