// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SlidingPaneLayout$SavedState$1 implements Parcelable$Creator<SlidingPaneLayout$SavedState>
{
    public SlidingPaneLayout$SavedState createFromParcel(final Parcel parcel) {
        return new SlidingPaneLayout$SavedState(parcel, null);
    }
    
    public SlidingPaneLayout$SavedState[] newArray(final int n) {
        return new SlidingPaneLayout$SavedState[n];
    }
}
