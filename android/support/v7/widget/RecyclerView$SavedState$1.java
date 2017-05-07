// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class RecyclerView$SavedState$1 implements Parcelable$Creator<RecyclerView$SavedState>
{
    public RecyclerView$SavedState createFromParcel(final Parcel parcel) {
        return new RecyclerView$SavedState(parcel);
    }
    
    public RecyclerView$SavedState[] newArray(final int n) {
        return new RecyclerView$SavedState[n];
    }
}
