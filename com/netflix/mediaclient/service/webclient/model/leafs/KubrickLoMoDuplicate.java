// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;

public class KubrickLoMoDuplicate implements LoMo, Genre
{
    private final LoMo lomo;
    
    public KubrickLoMoDuplicate(final LoMo lomo) {
        this.lomo = lomo;
    }
    
    public int describeContents() {
        return this.lomo.describeContents();
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
        return this.lomo.isHero();
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
