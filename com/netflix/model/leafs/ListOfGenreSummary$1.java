// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class ListOfGenreSummary$1 implements Parcelable$Creator<ListOfGenreSummary>
{
    public ListOfGenreSummary createFromParcel(final Parcel parcel) {
        return new ListOfGenreSummary(parcel);
    }
    
    public ListOfGenreSummary[] newArray(final int n) {
        return new ListOfGenreSummary[n];
    }
}
