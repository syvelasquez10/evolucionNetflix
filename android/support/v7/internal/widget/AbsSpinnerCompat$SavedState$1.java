// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AbsSpinnerCompat$SavedState$1 implements Parcelable$Creator<AbsSpinnerCompat$SavedState>
{
    public AbsSpinnerCompat$SavedState createFromParcel(final Parcel parcel) {
        return new AbsSpinnerCompat$SavedState(parcel);
    }
    
    public AbsSpinnerCompat$SavedState[] newArray(final int n) {
        return new AbsSpinnerCompat$SavedState[n];
    }
}
