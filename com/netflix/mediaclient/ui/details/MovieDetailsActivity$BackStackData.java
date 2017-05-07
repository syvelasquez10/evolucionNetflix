// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Parcel;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class MovieDetailsActivity$BackStackData implements Parcelable
{
    public static final Parcelable$Creator<MovieDetailsActivity$BackStackData> CREATOR;
    private final PlayContext playContext;
    private final int scrollYOffset;
    private final String videoId;
    
    static {
        CREATOR = (Parcelable$Creator)new MovieDetailsActivity$BackStackData$1();
    }
    
    private MovieDetailsActivity$BackStackData(final Parcel parcel) {
        this.videoId = parcel.readString();
        this.playContext = (PlayContext)parcel.readParcelable(PlayContextImp.class.getClassLoader());
        this.scrollYOffset = parcel.readInt();
    }
    
    public MovieDetailsActivity$BackStackData(final String videoId, final PlayContext playContext, final int scrollYOffset) {
        this.videoId = videoId;
        this.playContext = playContext;
        this.scrollYOffset = scrollYOffset;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "BackStackData [videoId=" + this.videoId + ", playContext=" + this.playContext + ", scrollYOffset=" + this.scrollYOffset + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.videoId);
        parcel.writeParcelable((Parcelable)this.playContext, n);
        parcel.writeInt(this.scrollYOffset);
    }
}
