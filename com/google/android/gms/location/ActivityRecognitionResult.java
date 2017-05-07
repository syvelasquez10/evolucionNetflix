// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.Iterator;
import android.os.Parcel;
import android.content.Intent;
import com.google.android.gms.common.internal.n;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ActivityRecognitionResult implements SafeParcelable
{
    public static final ActivityRecognitionResultCreator CREATOR;
    public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
    private final int BR;
    List<DetectedActivity> adQ;
    long adR;
    long adS;
    
    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }
    
    public ActivityRecognitionResult(final int n, final List<DetectedActivity> adQ, final long adR, final long adS) {
        this.BR = 1;
        this.adQ = adQ;
        this.adR = adR;
        this.adS = adS;
    }
    
    public ActivityRecognitionResult(final DetectedActivity detectedActivity, final long n, final long n2) {
        this(Collections.singletonList(detectedActivity), n, n2);
    }
    
    public ActivityRecognitionResult(final List<DetectedActivity> adQ, final long adR, final long adS) {
        final boolean b = false;
        n.b(adQ != null && adQ.size() > 0, (Object)"Must have at least 1 detected activity");
        boolean b2 = b;
        if (adR > 0L) {
            b2 = b;
            if (adS > 0L) {
                b2 = true;
            }
        }
        n.b(b2, (Object)"Must set times");
        this.BR = 1;
        this.adQ = adQ;
        this.adR = adR;
        this.adS = adS;
    }
    
    public static ActivityRecognitionResult extractResult(final Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        final Object value = intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
        if (value instanceof byte[]) {
            final Parcel obtain = Parcel.obtain();
            obtain.unmarshall((byte[])value, 0, ((byte[])value).length);
            obtain.setDataPosition(0);
            return ActivityRecognitionResult.CREATOR.createFromParcel(obtain);
        }
        if (value instanceof ActivityRecognitionResult) {
            return (ActivityRecognitionResult)value;
        }
        return null;
    }
    
    public static boolean hasResult(final Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getActivityConfidence(final int n) {
        for (final DetectedActivity detectedActivity : this.adQ) {
            if (detectedActivity.getType() == n) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }
    
    public long getElapsedRealtimeMillis() {
        return this.adS;
    }
    
    public DetectedActivity getMostProbableActivity() {
        return this.adQ.get(0);
    }
    
    public List<DetectedActivity> getProbableActivities() {
        return this.adQ;
    }
    
    public long getTime() {
        return this.adR;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.adQ + ", timeMillis=" + this.adR + ", elapsedRealtimeMillis=" + this.adS + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ActivityRecognitionResultCreator.a(this, parcel, n);
    }
}
