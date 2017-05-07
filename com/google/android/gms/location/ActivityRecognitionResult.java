// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import java.util.Iterator;
import android.content.Intent;
import com.google.android.gms.internal.fq;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ActivityRecognitionResult implements SafeParcelable
{
    public static final ActivityRecognitionResultCreator CREATOR;
    public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
    List<DetectedActivity> NP;
    long NQ;
    long NR;
    private final int xH;
    
    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }
    
    public ActivityRecognitionResult(final int n, final List<DetectedActivity> np, final long nq, final long nr) {
        this.xH = 1;
        this.NP = np;
        this.NQ = nq;
        this.NR = nr;
    }
    
    public ActivityRecognitionResult(final DetectedActivity detectedActivity, final long n, final long n2) {
        this(Collections.singletonList(detectedActivity), n, n2);
    }
    
    public ActivityRecognitionResult(final List<DetectedActivity> np, final long nq, final long nr) {
        fq.b(np != null && np.size() > 0, "Must have at least 1 detected activity");
        this.xH = 1;
        this.NP = np;
        this.NQ = nq;
        this.NR = nr;
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
        for (final DetectedActivity detectedActivity : this.NP) {
            if (detectedActivity.getType() == n) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }
    
    public long getElapsedRealtimeMillis() {
        return this.NR;
    }
    
    public DetectedActivity getMostProbableActivity() {
        return this.NP.get(0);
    }
    
    public List<DetectedActivity> getProbableActivities() {
        return this.NP;
    }
    
    public long getTime() {
        return this.NQ;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.NP + ", timeMillis=" + this.NQ + ", elapsedRealtimeMillis=" + this.NR + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ActivityRecognitionResultCreator.a(this, parcel, n);
    }
}
