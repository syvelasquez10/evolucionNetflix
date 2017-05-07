// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

public interface SubtitleBlock
{
    long getEnd();
    
    String getId();
    
    long getStart();
    
    boolean isVisible(final long p0);
    
    boolean isVisibleInGivenTimeRange(final long p0, final long p1);
}
