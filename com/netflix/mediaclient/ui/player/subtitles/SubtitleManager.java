// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.content.Context;

public interface SubtitleManager
{
    void clear();
    
    void clearPendingUpdates();
    
    Context getContext();
    
    void onPlayerOverlayVisibiltyChange(final boolean p0);
    
    void onSubtitleChange(final SubtitleScreen p0);
    
    void onSubtitleRemove();
    
    void setSubtitleVisibility(final boolean p0);
}
