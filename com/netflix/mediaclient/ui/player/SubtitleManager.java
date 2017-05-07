// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;

public interface SubtitleManager
{
    void clear();
    
    void clearPendingUpdates();
    
    void onPlayerOverlayVisibiltyChange(final boolean p0);
    
    void onSubtitleChange(final SubtitleScreen p0);
    
    void onSubtitleRemove();
    
    void onSubtitleShow(final String p0);
    
    void setSubtitleVisibility(final boolean p0);
}
