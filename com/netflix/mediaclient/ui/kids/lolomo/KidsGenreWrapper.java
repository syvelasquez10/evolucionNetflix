// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoMo;

public class KidsGenreWrapper implements LoMo
{
    private final String genreId;
    
    public KidsGenreWrapper(final String genreId) {
        this.genreId = genreId;
    }
    
    public int describeContents() {
        return -1;
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return null;
    }
    
    public String getId() {
        return this.genreId;
    }
    
    public int getListPos() {
        return -1;
    }
    
    @Override
    public int getNumVideos() {
        return -1;
    }
    
    public String getRequestId() {
        return null;
    }
    
    public String getTitle() {
        return this.getId();
    }
    
    public int getTrackId() {
        return -1;
    }
    
    public LoMoType getType() {
        return LoMoType.FLAT_GENRE;
    }
    
    @Override
    public boolean isBillboard() {
        return false;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        throw new UnsupportedOperationException("Can't write to parcel");
    }
}
