// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class BottomSheetBehavior$SavedState$1 implements ParcelableCompatCreatorCallbacks<BottomSheetBehavior$SavedState>
{
    @Override
    public BottomSheetBehavior$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new BottomSheetBehavior$SavedState(parcel, classLoader);
    }
    
    @Override
    public BottomSheetBehavior$SavedState[] newArray(final int n) {
        return new BottomSheetBehavior$SavedState[n];
    }
}
