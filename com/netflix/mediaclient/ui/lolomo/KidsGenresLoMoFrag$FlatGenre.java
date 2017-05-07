// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;

class KidsGenresLoMoFrag$FlatGenre implements Genre
{
    final /* synthetic */ KidsGenresLoMoFrag this$0;
    
    private KidsGenresLoMoFrag$FlatGenre(final KidsGenresLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getHeroTrackId() {
        return 0;
    }
    
    public String getId() {
        return this.this$0.genreId;
    }
    
    public int getListPos() {
        return 0;
    }
    
    @Override
    public List<String> getMoreImages() {
        return null;
    }
    
    @Override
    public int getNumVideos() {
        return 0;
    }
    
    public String getRequestId() {
        return null;
    }
    
    public String getTitle() {
        return null;
    }
    
    public int getTrackId() {
        return 0;
    }
    
    public LoMoType getType() {
        return LoMoType.FLAT_GENRE;
    }
    
    @Override
    public boolean isBillboard() {
        return false;
    }
    
    public boolean isHero() {
        return false;
    }
    
    @Override
    public void setId(final String s) {
    }
    
    @Override
    public void setListPos(final int n) {
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
    }
}
