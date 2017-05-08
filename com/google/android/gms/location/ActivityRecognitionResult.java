// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.zzw;
import android.os.Parcel;
import android.content.Intent;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ActivityRecognitionResult implements SafeParcelable
{
    public static final ActivityRecognitionResultCreator CREATOR;
    private final int mVersionCode;
    List<DetectedActivity> zzaEb;
    long zzaEc;
    long zzaEd;
    int zzaEe;
    
    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }
    
    public ActivityRecognitionResult(final int mVersionCode, final List<DetectedActivity> zzaEb, final long zzaEc, final long zzaEd, final int zzaEe) {
        this.mVersionCode = mVersionCode;
        this.zzaEb = zzaEb;
        this.zzaEc = zzaEc;
        this.zzaEd = zzaEd;
        this.zzaEe = zzaEe;
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
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult)o;
            if (this.zzaEc != activityRecognitionResult.zzaEc || this.zzaEd != activityRecognitionResult.zzaEd || this.zzaEe != activityRecognitionResult.zzaEe || !zzw.equal(this.zzaEb, activityRecognitionResult.zzaEb)) {
                return false;
            }
        }
        return true;
    }
    
    public List<DetectedActivity> getProbableActivities() {
        return this.zzaEb;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzaEc, this.zzaEd, this.zzaEe, this.zzaEb);
    }
    
    @Override
    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.zzaEb + ", timeMillis=" + this.zzaEc + ", elapsedRealtimeMillis=" + this.zzaEd + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ActivityRecognitionResultCreator.zza(this, parcel, n);
    }
}
