// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.ui.player.PlayerFragment;

public interface InteractiveMomentsManager
{
    void hide();
    
    void init(final PlayerFragment p0);
    
    boolean isManagerReady();
    
    void onDestroy();
    
    void onMomentsFetched(final InteractivePlaybackMoments p0);
    
    void onVideoDetailsFetched(final VideoDetails p0);
    
    void playerOverlayVisibility(final boolean p0);
    
    void setTimelineProgress(final int p0, final boolean p1);
}
