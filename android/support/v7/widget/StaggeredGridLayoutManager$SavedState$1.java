// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class StaggeredGridLayoutManager$SavedState$1 implements Parcelable$Creator<StaggeredGridLayoutManager$SavedState>
{
    public StaggeredGridLayoutManager$SavedState createFromParcel(final Parcel parcel) {
        return new StaggeredGridLayoutManager$SavedState(parcel);
    }
    
    public StaggeredGridLayoutManager$SavedState[] newArray(final int n) {
        return new StaggeredGridLayoutManager$SavedState[n];
    }
}
