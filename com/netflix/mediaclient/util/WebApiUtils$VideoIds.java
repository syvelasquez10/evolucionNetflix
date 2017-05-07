// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public class WebApiUtils$VideoIds
{
    public int catalogId;
    public String catalogIdUrl;
    public int episodeId;
    public String episodeIdUrl;
    public boolean isEpisode;
    
    public WebApiUtils$VideoIds() {
    }
    
    public WebApiUtils$VideoIds(final boolean isEpisode, final String episodeIdUrl, final String catalogIdUrl, final int episodeId, final int catalogId) {
        this.isEpisode = isEpisode;
        this.episodeIdUrl = episodeIdUrl;
        this.catalogIdUrl = catalogIdUrl;
        this.episodeId = episodeId;
        this.catalogId = catalogId;
    }
    
    public VideoType getVideoType() {
        if (this.isEpisode) {
            return VideoType.EPISODE;
        }
        return VideoType.MOVIE;
    }
}
