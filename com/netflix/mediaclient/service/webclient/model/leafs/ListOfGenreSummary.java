// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;

public class ListOfGenreSummary extends TrackableListSummary implements GenreList
{
    public static final Parcelable$Creator<ListOfGenreSummary> CREATOR;
    private LoMoType enumType;
    private String genreExperience;
    private String genreId;
    private String genreName;
    private boolean isKidsGenre;
    
    static {
        CREATOR = (Parcelable$Creator)new ListOfGenreSummary$1();
    }
    
    public ListOfGenreSummary(final int n, final int n2, final int n3, final String s, final String genreName, final String genreId, final boolean isKidsGenre, final String genreExperience) {
        super(n, n2, n3, s);
        this.genreName = genreName;
        this.genreId = genreId;
        this.isKidsGenre = isKidsGenre;
        this.genreExperience = genreExperience;
    }
    
    public ListOfGenreSummary(final Parcel parcel) {
        super(parcel);
        this.genreName = parcel.readString();
        this.genreId = parcel.readString();
        this.isKidsGenre = (parcel.readByte() != 0);
        this.genreExperience = parcel.readString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.genreId;
    }
    
    @Override
    public int getNumVideos() {
        return this.getLength();
    }
    
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.genreName);
    }
    
    public LoMoType getType() {
        if (this.enumType == null) {
            this.enumType = LoMoType.create(this.genreExperience);
        }
        return this.enumType;
    }
    
    @Override
    public boolean isKidsGenre() {
        return this.isKidsGenre;
    }
    
    @Override
    public String toString() {
        return "ListOfGenreSummary [genreName=" + this.genreName + ", genreId=" + this.genreId + ", isKidsGenre=" + this.isKidsGenre + ", genreExperience=" + this.genreExperience + ", enumType=" + this.enumType + "]";
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.genreName);
        parcel.writeString(this.genreId);
        if (this.isKidsGenre) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
        parcel.writeString(this.genreExperience);
    }
}
