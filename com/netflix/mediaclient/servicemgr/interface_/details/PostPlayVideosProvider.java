// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.List;
import com.netflix.model.leafs.InteractivePostplay;

public interface PostPlayVideosProvider
{
    InteractivePostplay getInteractivePostplay();
    
    List<PostPlayContext> getPostPlayContexts();
    
    List<PostPlayVideo> getPostPlayVideos();
}
