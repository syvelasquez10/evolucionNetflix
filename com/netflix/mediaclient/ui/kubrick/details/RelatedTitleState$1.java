// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Parcel;
import android.os.Parcelable$Creator;

class RelatedTitleState$1 implements Parcelable$Creator<RelatedTitleState>
{
    final /* synthetic */ RelatedTitleState this$0;
    
    RelatedTitleState$1(final RelatedTitleState this$0) {
        this.this$0 = this$0;
    }
    
    public RelatedTitleState createFromParcel(final Parcel parcel) {
        return new RelatedTitleState(parcel, null);
    }
    
    public RelatedTitleState[] newArray(final int n) {
        return new RelatedTitleState[n];
    }
}
