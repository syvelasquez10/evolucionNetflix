// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

class BillboardView$PlayerSavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<BillboardView$PlayerSavedState> CREATOR;
    int completedLoops;
    int seekPosition;
    
    static {
        CREATOR = (Parcelable$Creator)new BillboardView$PlayerSavedState$1();
    }
    
    private BillboardView$PlayerSavedState(final Parcel parcel) {
        super(parcel);
        this.completedLoops = parcel.readInt();
        this.seekPosition = parcel.readInt();
    }
    
    public BillboardView$PlayerSavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.completedLoops);
        parcel.writeInt(this.seekPosition);
    }
}
