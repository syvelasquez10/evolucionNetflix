// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class ListOfMoviesSummary$1 implements Parcelable$Creator<ListOfMoviesSummary>
{
    public ListOfMoviesSummary createFromParcel(final Parcel parcel) {
        return new ListOfMoviesSummary(parcel);
    }
    
    public ListOfMoviesSummary[] newArray(final int n) {
        return new ListOfMoviesSummary[n];
    }
}
