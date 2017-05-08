// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

public class AppBarLayout$Behavior$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<AppBarLayout$Behavior$SavedState> CREATOR;
    boolean firstVisibileChildAtMinimumHeight;
    float firstVisibileChildPercentageShown;
    int firstVisibleChildIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new AppBarLayout$Behavior$SavedState$1();
    }
    
    public AppBarLayout$Behavior$SavedState(final Parcel parcel) {
        super(parcel);
        this.firstVisibleChildIndex = parcel.readInt();
        this.firstVisibileChildPercentageShown = parcel.readFloat();
        this.firstVisibileChildAtMinimumHeight = (parcel.readByte() != 0);
    }
    
    public AppBarLayout$Behavior$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.firstVisibleChildIndex);
        parcel.writeFloat(this.firstVisibileChildPercentageShown);
        if (this.firstVisibileChildAtMinimumHeight) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
    }
}
