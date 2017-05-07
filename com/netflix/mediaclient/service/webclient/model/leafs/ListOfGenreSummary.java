// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.GenreList;

public class ListOfGenreSummary implements GenreList
{
    private String genreId;
    private String genreName;
    
    @Override
    public String getId() {
        return this.genreId;
    }
    
    @Override
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.genreName);
    }
    
    @Override
    public LoMoType getType() {
        return LoMoType.STANDARD;
    }
}
