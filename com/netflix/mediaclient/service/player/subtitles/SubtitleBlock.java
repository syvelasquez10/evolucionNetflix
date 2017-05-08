// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

public interface SubtitleBlock
{
    void displayed();
    
    long getEnd();
    
    String getId();
    
    int getNumberOfDisplays();
    
    long getStart();
    
    boolean isVisible(final long p0);
    
    boolean isVisibleInGivenTimeRange(final long p0, final long p1);
    
    void seeked(final long p0);
    
    boolean wasDisplayed();
}
