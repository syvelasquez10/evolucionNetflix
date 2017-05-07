// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import java.util.List;

public interface PostPlayVideosProvider
{
    List<PostPlayContext> getPostPlayContexts();
    
    List<PostPlayVideo> getPostPlayVideos();
}
