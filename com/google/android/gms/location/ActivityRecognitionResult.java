// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import java.util.Iterator;
import android.content.Intent;
import com.google.android.gms.internal.eg;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ActivityRecognitionResult implements SafeParcelable
{
    public static final ActivityRecognitionResultCreator CREATOR;
    public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
    private final int kg;
    List<DetectedActivity> xm;
    long xn;
    long xo;
    
    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }
    
    public ActivityRecognitionResult(final int n, final List<DetectedActivity> xm, final long xn, final long xo) {
        this.kg = 1;
        this.xm = xm;
        this.xn = xn;
        this.xo = xo;
    }
    
    public ActivityRecognitionResult(final DetectedActivity detectedActivity, final long n, final long n2) {
        this(Collections.singletonList(detectedActivity), n, n2);
    }
    
    public ActivityRecognitionResult(final List<DetectedActivity> xm, final long xn, final long xo) {
        eg.b(xm != null && xm.size() > 0, "Must have at least 1 detected activity");
        this.kg = 1;
        this.xm = xm;
        this.xn = xn;
        this.xo = xo;
    }
    
    public static ActivityRecognitionResult extractResult(final Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (ActivityRecognitionResult)intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    }
    
    public static boolean hasResult(final Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getActivityConfidence(final int n) {
        for (final DetectedActivity detectedActivity : this.xm) {
            if (detectedActivity.getType() == n) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }
    
    public long getElapsedRealtimeMillis() {
        return this.xo;
    }
    
    public DetectedActivity getMostProbableActivity() {
        return this.xm.get(0);
    }
    
    public List<DetectedActivity> getProbableActivities() {
        return this.xm;
    }
    
    public long getTime() {
        return this.xn;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.xm + ", timeMillis=" + this.xn + ", elapsedRealtimeMillis=" + this.xo + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ActivityRecognitionResultCreator.a(this, parcel, n);
    }
}
