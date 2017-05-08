// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList$GenreType;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;

@Deprecated
public class ListOfGenreSummary extends TrackableListSummary implements GenreList
{
    public static final Parcelable$Creator<ListOfGenreSummary> CREATOR;
    private LoMoType enumType;
    private final String genreExperience;
    private final String genreId;
    private final String genreName;
    private final String genreType;
    private final boolean isKidsGenre;
    
    static {
        CREATOR = (Parcelable$Creator)new ListOfGenreSummary$1();
    }
    
    public ListOfGenreSummary(final int n, final int n2, final int n3, final String s, final String genreName, final String genreId, final String genreType, final boolean isKidsGenre, final String genreExperience) {
        super(n, n2, n3, s);
        this.genreName = genreName;
        this.genreId = genreId;
        this.genreType = genreType;
        this.isKidsGenre = isKidsGenre;
        this.genreExperience = genreExperience;
    }
    
    public ListOfGenreSummary(final Parcel parcel) {
        super(parcel);
        this.genreName = parcel.readString();
        this.genreId = parcel.readString();
        this.isKidsGenre = (parcel.readByte() != 0);
        this.genreExperience = parcel.readString();
        this.genreType = parcel.readString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public GenreList$GenreType getGenreType() {
        if (this.genreType == null) {
            return GenreList$GenreType.UNKNOWN;
        }
        return GenreList$GenreType.valueOf(this.genreType.toUpperCase(Locale.ENGLISH));
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
        return "ListOfGenreSummary [genreName=" + this.genreName + ", genreId=" + this.genreId + ", genreType=" + this.genreType + ", isKidsGenre=" + this.isKidsGenre + ", genreExperience=" + this.genreExperience + ", enumType=" + this.enumType + "]";
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
        parcel.writeString(this.genreType);
    }
}
