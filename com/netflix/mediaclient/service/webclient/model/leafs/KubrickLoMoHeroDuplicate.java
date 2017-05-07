// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public class KubrickLoMoHeroDuplicate implements LoMo, Genre
{
    private final LoMo lomo;
    
    public KubrickLoMoHeroDuplicate(final LoMo lomo) {
        this.lomo = lomo;
    }
    
    public int describeContents() {
        return this.lomo.describeContents();
    }
    
    public List<FriendProfile> getFacebookFriends() {
        return this.lomo.getFacebookFriends();
    }
    
    public int getHeroTrackId() {
        return this.lomo.getHeroTrackId();
    }
    
    public String getId() {
        return this.lomo.getId();
    }
    
    public int getListPos() {
        return this.lomo.getListPos();
    }
    
    @Override
    public List<String> getMoreImages() {
        return this.lomo.getMoreImages();
    }
    
    @Override
    public int getNumVideos() {
        return this.lomo.getNumVideos();
    }
    
    public String getRequestId() {
        return this.lomo.getRequestId();
    }
    
    public String getTitle() {
        return this.lomo.getTitle();
    }
    
    public int getTrackId() {
        return this.lomo.getTrackId();
    }
    
    public LoMoType getType() {
        return this.lomo.getType();
    }
    
    @Override
    public boolean isBillboard() {
        return this.lomo.isBillboard();
    }
    
    public boolean isHero() {
        return true;
    }
    
    @Override
    public void setId(final String id) {
        this.lomo.setId(id);
    }
    
    @Override
    public void setListPos(final int listPos) {
        this.lomo.setListPos(listPos);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        this.lomo.writeToParcel(parcel, n);
    }
}
