// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList$GenreType;
import android.os.Parcel;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;

@Deprecated
public class ListOfGenreSummary extends TrackableListSummary implements GenreList
{
    public static final Parcelable$Creator<ListOfGenreSummary> CREATOR;
    private final String genreId;
    private final String genreName;
    private final String genreType;
    
    static {
        CREATOR = (Parcelable$Creator)new ListOfGenreSummary$1();
    }
    
    public ListOfGenreSummary(final int n, final int n2, final int n3, final String s, final String s2, final String genreId, final String genreType) {
        super(n, n2, n3, s);
        this.genreName = StringUtils.decodeHtmlEntities(s2);
        this.genreId = genreId;
        this.genreType = genreType;
    }
    
    public ListOfGenreSummary(final Parcel parcel) {
        super(parcel);
        this.genreName = parcel.readString();
        this.genreId = parcel.readString();
        this.genreType = parcel.readString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public GenreList$GenreType getGenreType() {
        if (this.genreType == null) {
            return GenreList$GenreType.LOLOMO;
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
        return this.genreName;
    }
    
    public LoMoType getType() {
        return LoMoType.STANDARD;
    }
    
    @Override
    public String toString() {
        return "ListOfGenreSummary [genreName=" + this.genreName + ", genreId=" + this.genreId + ", genreType=" + this.genreType + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.genreName);
        parcel.writeString(this.genreId);
        parcel.writeString(this.genreType);
    }
}
