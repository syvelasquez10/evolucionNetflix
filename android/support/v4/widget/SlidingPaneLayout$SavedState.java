// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

class SlidingPaneLayout$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<SlidingPaneLayout$SavedState> CREATOR;
    boolean isOpen;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SlidingPaneLayout$SavedState>)new SlidingPaneLayout$SavedState$1());
    }
    
    SlidingPaneLayout$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isOpen = (parcel.readInt() != 0);
    }
    
    SlidingPaneLayout$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        if (this.isOpen) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
}
