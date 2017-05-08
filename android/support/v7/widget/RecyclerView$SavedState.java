// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

public class RecyclerView$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<RecyclerView$SavedState> CREATOR;
    Parcelable mLayoutState;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<RecyclerView$SavedState>)new RecyclerView$SavedState$1());
    }
    
    RecyclerView$SavedState(final Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        if (classLoader == null) {
            classLoader = RecyclerView$LayoutManager.class.getClassLoader();
        }
        this.mLayoutState = parcel.readParcelable(classLoader);
    }
    
    RecyclerView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    void copyFrom(final RecyclerView$SavedState recyclerView$SavedState) {
        this.mLayoutState = recyclerView$SavedState.mLayoutState;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable(this.mLayoutState, 0);
    }
}
