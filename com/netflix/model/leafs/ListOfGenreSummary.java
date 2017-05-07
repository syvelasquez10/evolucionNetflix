// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class ListOfGenreSummary extends TrackableListSummary implements JsonPopulator, GenreList
{
    public static final Parcelable$Creator<ListOfGenreSummary> CREATOR;
    private static final String TAG = "ListOfGenreSummary";
    private LoMoType enumType;
    private String genreExperience;
    private String genreId;
    private String genreName;
    private boolean isKidsGenre;
    
    static {
        CREATOR = (Parcelable$Creator)new ListOfGenreSummary$1();
    }
    
    public ListOfGenreSummary() {
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
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ListOfGenreSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0130: {
                switch (s.hashCode()) {
                    case 646545070: {
                        if (s.equals("genreName")) {
                            n = 0;
                            break Label_0130;
                        }
                        break;
                    }
                    case -79774210: {
                        if (s.equals("genreId")) {
                            n = 1;
                            break Label_0130;
                        }
                        break;
                    }
                    case 1562605389: {
                        if (s.equals("genreExperience")) {
                            n = 2;
                            break Label_0130;
                        }
                        break;
                    }
                    case 1282907404: {
                        if (s.equals("isKidsGenre")) {
                            n = 3;
                            break Label_0130;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.genreName = entry.getValue().getAsString();
                    continue;
                }
                case 1: {
                    this.genreId = entry.getValue().getAsString();
                    continue;
                }
                case 2: {
                    this.genreExperience = entry.getValue().getAsString();
                    continue;
                }
                case 3: {
                    this.isKidsGenre = entry.getValue().getAsBoolean();
                    continue;
                }
            }
        }
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
