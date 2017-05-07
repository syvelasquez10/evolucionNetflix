// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
import java.util.List;

public class PostPlayVideosProvider implements com.netflix.mediaclient.servicemgr.model.details.PostPlayVideosProvider
{
    private final List<PostPlayContext> contexts;
    private final List<PostPlayVideo> videos;
    
    public PostPlayVideosProvider(final List<PostPlayVideo> videos, final List<PostPlayContext> contexts) {
        this.videos = videos;
        this.contexts = contexts;
    }
    
    @Override
    public List<PostPlayContext> getPostPlayContexts() {
        return this.contexts;
    }
    
    @Override
    public List<PostPlayVideo> getPostPlayVideos() {
        return this.videos;
    }
}
