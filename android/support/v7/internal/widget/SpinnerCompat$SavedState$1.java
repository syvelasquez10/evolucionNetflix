// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SpinnerCompat$SavedState$1 implements Parcelable$Creator<SpinnerCompat$SavedState>
{
    public SpinnerCompat$SavedState createFromParcel(final Parcel parcel) {
        return new SpinnerCompat$SavedState(parcel, null);
    }
    
    public SpinnerCompat$SavedState[] newArray(final int n) {
        return new SpinnerCompat$SavedState[n];
    }
}
