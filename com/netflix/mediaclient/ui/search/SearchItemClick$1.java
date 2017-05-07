// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SearchItemClick$1 implements Parcelable$Creator<SearchItemClick>
{
    public SearchItemClick createFromParcel(final Parcel parcel) {
        return new SearchItemClick(parcel);
    }
    
    public SearchItemClick[] newArray(final int n) {
        return new SearchItemClick[n];
    }
}
