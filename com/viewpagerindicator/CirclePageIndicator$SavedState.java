// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class CirclePageIndicator$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<CirclePageIndicator$SavedState> CREATOR;
    int currentPage;
    
    static {
        CREATOR = (Parcelable$Creator)new CirclePageIndicator$SavedState$1();
    }
    
    private CirclePageIndicator$SavedState(final Parcel parcel) {
        super(parcel);
        this.currentPage = parcel.readInt();
    }
    
    public CirclePageIndicator$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.currentPage);
    }
}
