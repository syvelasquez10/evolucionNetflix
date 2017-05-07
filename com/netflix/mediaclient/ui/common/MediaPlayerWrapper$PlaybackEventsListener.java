// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

public interface MediaPlayerWrapper$PlaybackEventsListener
{
    void onCompletion();
    
    void onPrepared();
    
    void updateBookmarks(final int p0, final int p1);
}
