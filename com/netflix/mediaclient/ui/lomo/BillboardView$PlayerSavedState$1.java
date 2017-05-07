// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class BillboardView$PlayerSavedState$1 implements Parcelable$Creator<BillboardView$PlayerSavedState>
{
    public BillboardView$PlayerSavedState createFromParcel(final Parcel parcel) {
        return new BillboardView$PlayerSavedState(parcel, null);
    }
    
    public BillboardView$PlayerSavedState[] newArray(final int n) {
        return new BillboardView$PlayerSavedState[n];
    }
}
