// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class RelatedTitleState implements Parcelable
{
    public final Parcelable$Creator<RelatedTitleState> CREATOR;
    int orientation;
    Parcelable recyclerViewState;
    int seasonSelectIndex;
    String titleId;
    
    private RelatedTitleState(final Parcel parcel) {
        this.CREATOR = (Parcelable$Creator<RelatedTitleState>)new RelatedTitleState$1(this);
        this.recyclerViewState = parcel.readParcelable(Parcelable.class.getClassLoader());
        this.seasonSelectIndex = parcel.readInt();
        this.orientation = parcel.readInt();
        this.titleId = parcel.readString();
    }
    
    RelatedTitleState(final String titleId, final Parcelable recyclerViewState, final int seasonSelectIndex, final int orientation) {
        this.CREATOR = (Parcelable$Creator<RelatedTitleState>)new RelatedTitleState$1(this);
        this.seasonSelectIndex = seasonSelectIndex;
        this.recyclerViewState = recyclerViewState;
        this.orientation = orientation;
        this.titleId = titleId;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable(this.recyclerViewState, n);
        parcel.writeInt(this.seasonSelectIndex);
        parcel.writeInt(this.orientation);
        parcel.writeString(this.titleId);
    }
}
