// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class ProgressBarCompat$SavedState$1 implements Parcelable$Creator<ProgressBarCompat$SavedState>
{
    public ProgressBarCompat$SavedState createFromParcel(final Parcel parcel) {
        return new ProgressBarCompat$SavedState(parcel, null);
    }
    
    public ProgressBarCompat$SavedState[] newArray(final int n) {
        return new ProgressBarCompat$SavedState[n];
    }
}
