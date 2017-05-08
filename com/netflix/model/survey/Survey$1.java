// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.survey;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class Survey$1 implements Parcelable$Creator
{
    public Survey createFromParcel(final Parcel parcel) {
        return new Survey(parcel);
    }
    
    public Survey[] newArray(final int n) {
        return new Survey[n];
    }
}
