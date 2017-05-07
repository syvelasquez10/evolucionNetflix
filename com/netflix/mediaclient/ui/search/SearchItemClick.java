// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

class SearchItemClick implements Parcelable
{
    public static final Parcelable$Creator<SearchItemClick> CREATOR;
    public String displayName;
    public long id;
    public int position;
    public SearchResultsFrag.SearchCategory searchCategory;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SearchItemClick>() {
            public SearchItemClick createFromParcel(final Parcel parcel) {
                return new SearchItemClick(parcel);
            }
            
            public SearchItemClick[] newArray(final int n) {
                return new SearchItemClick[n];
            }
        };
    }
    
    public SearchItemClick(final Parcel parcel) {
        this.searchCategory = SearchResultsFrag.SearchCategory.valueOf(parcel.readString());
        this.displayName = parcel.readString();
        this.position = parcel.readInt();
        this.id = parcel.readLong();
    }
    
    SearchItemClick(final SearchResultsFrag.SearchCategory searchCategory, final int position, final long id, final String displayName) {
        this.searchCategory = searchCategory;
        this.displayName = displayName;
        this.position = position;
        this.id = id;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.searchCategory.name());
        parcel.writeString(this.displayName);
        parcel.writeInt(this.position);
        parcel.writeLong(this.id);
    }
}
