// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class WebApiUtils$VideoIds
{
    public int catalogId;
    public String catalogIdUrl;
    public boolean episode;
    public int episodeId;
    public String episodeIdUrl;
    
    public WebApiUtils$VideoIds() {
    }
    
    public WebApiUtils$VideoIds(final boolean episode, final String episodeIdUrl, final String catalogIdUrl, final int episodeId, final int catalogId) {
        this.episode = episode;
        this.episodeIdUrl = episodeIdUrl;
        this.catalogIdUrl = catalogIdUrl;
        this.episodeId = episodeId;
        this.catalogId = catalogId;
    }
}
