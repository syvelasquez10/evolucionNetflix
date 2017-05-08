// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class RelatedTitleState$1 implements Parcelable$Creator<RelatedTitleState>
{
    public RelatedTitleState createFromParcel(final Parcel parcel) {
        return new RelatedTitleState(parcel, null);
    }
    
    public RelatedTitleState[] newArray(final int n) {
        return new RelatedTitleState[n];
    }
}
