// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class AbsSpinnerCompat$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<AbsSpinnerCompat$SavedState> CREATOR;
    int position;
    long selectedId;
    
    static {
        CREATOR = (Parcelable$Creator)new AbsSpinnerCompat$SavedState$1();
    }
    
    AbsSpinnerCompat$SavedState(final Parcel parcel) {
        super(parcel);
        this.selectedId = parcel.readLong();
        this.position = parcel.readInt();
    }
    
    AbsSpinnerCompat$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public String toString() {
        return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " position=" + this.position + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeLong(this.selectedId);
        parcel.writeInt(this.position);
    }
}
