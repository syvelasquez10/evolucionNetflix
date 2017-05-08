// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class NestedScrollView$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<NestedScrollView$SavedState> CREATOR;
    public int scrollPosition;
    
    static {
        CREATOR = (Parcelable$Creator)new NestedScrollView$SavedState$1();
    }
    
    NestedScrollView$SavedState(final Parcel parcel) {
        super(parcel);
        this.scrollPosition = parcel.readInt();
    }
    
    NestedScrollView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public String toString() {
        return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.scrollPosition);
    }
}
