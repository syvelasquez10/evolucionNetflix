// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class ProgressBarCompat$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<ProgressBarCompat$SavedState> CREATOR;
    int progress;
    int secondaryProgress;
    
    static {
        CREATOR = (Parcelable$Creator)new ProgressBarCompat$SavedState$1();
    }
    
    private ProgressBarCompat$SavedState(final Parcel parcel) {
        super(parcel);
        this.progress = parcel.readInt();
        this.secondaryProgress = parcel.readInt();
    }
    
    ProgressBarCompat$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.progress);
        parcel.writeInt(this.secondaryProgress);
    }
}
