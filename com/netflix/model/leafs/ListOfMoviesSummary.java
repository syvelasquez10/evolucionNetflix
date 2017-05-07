// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import android.os.Parcel;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class ListOfMoviesSummary extends TrackableListSummary implements JsonPopulator, LoMo, Genre
{
    public static final Parcelable$Creator<ListOfMoviesSummary> CREATOR;
    private static final String TAG = "ListOfMoviesSummary";
    private String displayName;
    private LoMoType enumType;
    private String id;
    private List<String> moreImgs;
    private SocialEvidence socialEvidence;
    private String type;
    
    static {
        CREATOR = (Parcelable$Creator)new ListOfMoviesSummary$1();
    }
    
    public ListOfMoviesSummary() {
    }
    
    public ListOfMoviesSummary(final Parcel parcel) {
        super(parcel);
        this.id = parcel.readString();
        this.type = parcel.readString();
        this.displayName = parcel.readString();
    }
    
    public int describeContents() {
        return 0;
    }
    
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
    public List<String> getMoreImages() {
        return this.moreImgs;
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
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("ListOfMoviesSummary", 2)) {
            Log.v("ListOfMoviesSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0154: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0154;
                        }
                        break;
                    }
                    case 1714148973: {
                        if (s.equals("displayName")) {
                            n = 1;
                            break Label_0154;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 2;
                            break Label_0154;
                        }
                        break;
                    }
                    case 609377508: {
                        if (s.equals("socialEvidence")) {
                            n = 3;
                            break Label_0154;
                        }
                        break;
                    }
                    case -219259387: {
                        if (s.equals("moreImgs")) {
                            n = 4;
                            break Label_0154;
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
                    this.id = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.displayName = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.type = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    (this.socialEvidence = new SocialEvidence()).populate(entry.getValue().getAsJsonObject());
                    continue;
                }
                case 4: {
                    this.moreImgs = JsonUtils.createStringArray(entry.getValue().getAsJsonArray());
                    continue;
                }
            }
        }
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "ListOfMoviesSummary [id=" + this.id + ", type=" + this.type + ", displayName=" + this.displayName + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.id);
        parcel.writeString(this.type);
        parcel.writeString(this.displayName);
    }
}
