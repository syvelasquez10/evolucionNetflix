// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class LinePageIndicator$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<LinePageIndicator$SavedState> CREATOR;
    int currentPage;
    
    static {
        CREATOR = (Parcelable$Creator)new LinePageIndicator$SavedState$1();
    }
    
    private LinePageIndicator$SavedState(final Parcel parcel) {
        super(parcel);
        this.currentPage = parcel.readInt();
    }
    
    public LinePageIndicator$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.currentPage);
    }
}
