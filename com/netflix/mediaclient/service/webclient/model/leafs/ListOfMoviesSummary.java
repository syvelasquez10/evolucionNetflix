// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.LoMoType;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.LoMo;

public class ListOfMoviesSummary extends TrackableListSummary implements LoMo, Genre
{
    public static final Parcelable$Creator<ListOfMoviesSummary> CREATOR;
    private String displayName;
    private LoMoType enumType;
    private String id;
    private SocialEvidence socialEvidence;
    private String type;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ListOfMoviesSummary>() {
            public ListOfMoviesSummary createFromParcel(final Parcel parcel) {
                return new ListOfMoviesSummary(parcel);
            }
            
            public ListOfMoviesSummary[] newArray(final int n) {
                return new ListOfMoviesSummary[n];
            }
        };
    }
    
    public ListOfMoviesSummary(final Parcel parcel) {
        this.id = parcel.readString();
        this.type = parcel.readString();
        this.displayName = parcel.readString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        if (this.socialEvidence != null) {
            return this.socialEvidence.getFacebookFriends();
        }
        return null;
    }
    
    public String getId() {
        return this.id;
    }
    
    @Override
    public int getNumVideos() {
        return this.getLength();
    }
    
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.displayName);
    }
    
    public LoMoType getType() {
        if (this.enumType == null) {
            this.enumType = LoMoType.create(this.type);
        }
        return this.enumType;
    }
    
    @Override
    public boolean isBillboard() {
        return this.getType() == LoMoType.BILLBOARD;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "ListOfMoviesSummary [id=" + this.id + ", type=" + this.type + ", displayName=" + this.displayName + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.id);
        parcel.writeString(this.type);
        parcel.writeString(this.displayName);
    }
}
