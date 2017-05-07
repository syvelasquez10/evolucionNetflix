// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

class SpinnerCompat$SavedState extends AbsSpinnerCompat$SavedState
{
    public static final Parcelable$Creator<SpinnerCompat$SavedState> CREATOR;
    boolean showDropdown;
    
    static {
        CREATOR = (Parcelable$Creator)new SpinnerCompat$SavedState$1();
    }
    
    private SpinnerCompat$SavedState(final Parcel parcel) {
        super(parcel);
        this.showDropdown = (parcel.readByte() != 0);
    }
    
    SpinnerCompat$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        if (this.showDropdown) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
    }
}
