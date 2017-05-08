// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

public class BottomSheetBehavior$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<BottomSheetBehavior$SavedState> CREATOR;
    final int state;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<BottomSheetBehavior$SavedState>)new BottomSheetBehavior$SavedState$1());
    }
    
    public BottomSheetBehavior$SavedState(final Parcel parcel) {
        this(parcel, null);
    }
    
    public BottomSheetBehavior$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.state = parcel.readInt();
    }
    
    public BottomSheetBehavior$SavedState(final Parcelable parcelable, final int state) {
        super(parcelable);
        this.state = state;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.state);
    }
}
