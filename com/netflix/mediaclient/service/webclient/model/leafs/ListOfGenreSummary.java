// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.GenreList;

public class ListOfGenreSummary extends TrackableListSummary implements GenreList
{
    private LoMoType enumType;
    private String genreExperience;
    private String genreId;
    private String genreName;
    private boolean isKidsGenre;
    
    @Override
    public String getId() {
        return this.genreId;
    }
    
    @Override
    public int getNumVideos() {
        return this.getLength();
    }
    
    @Override
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.genreName);
    }
    
    @Override
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
}
