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

public class AppBarLayout$Behavior$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<AppBarLayout$Behavior$SavedState> CREATOR;
    boolean firstVisibleChildAtMinimumHeight;
    int firstVisibleChildIndex;
    float firstVisibleChildPercentageShown;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<AppBarLayout$Behavior$SavedState>)new AppBarLayout$Behavior$SavedState$1());
    }
    
    public AppBarLayout$Behavior$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.firstVisibleChildIndex = parcel.readInt();
        this.firstVisibleChildPercentageShown = parcel.readFloat();
        this.firstVisibleChildAtMinimumHeight = (parcel.readByte() != 0);
    }
    
    public AppBarLayout$Behavior$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.firstVisibleChildIndex);
        parcel.writeFloat(this.firstVisibleChildPercentageShown);
        if (this.firstVisibleChildAtMinimumHeight) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
    }
}
