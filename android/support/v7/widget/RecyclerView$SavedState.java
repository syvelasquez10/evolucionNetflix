// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class RecyclerView$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<RecyclerView$SavedState> CREATOR;
    Parcelable mLayoutState;
    
    static {
        CREATOR = (Parcelable$Creator)new RecyclerView$SavedState$1();
    }
    
    RecyclerView$SavedState(final Parcel parcel) {
        super(parcel);
        this.mLayoutState = parcel.readParcelable(RecyclerView$LayoutManager.class.getClassLoader());
    }
    
    RecyclerView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    private void copyFrom(final RecyclerView$SavedState recyclerView$SavedState) {
        this.mLayoutState = recyclerView$SavedState.mLayoutState;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable(this.mLayoutState, 0);
    }
}
