// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class StickyGridHeadersGridView$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<StickyGridHeadersGridView$SavedState> CREATOR;
    boolean areHeadersSticky;
    
    static {
        CREATOR = (Parcelable$Creator)new StickyGridHeadersGridView$SavedState$1();
    }
    
    private StickyGridHeadersGridView$SavedState(final Parcel parcel) {
        super(parcel);
        this.areHeadersSticky = (parcel.readByte() != 0);
    }
    
    public StickyGridHeadersGridView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public String toString() {
        return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.areHeadersSticky + "}";
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        if (this.areHeadersSticky) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
    }
}
