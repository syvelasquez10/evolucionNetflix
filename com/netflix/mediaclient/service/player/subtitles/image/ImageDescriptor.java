// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

public interface ImageDescriptor
{
    void displayed();
    
    int getDuration();
    
    int getEndTime();
    
    short getHeight();
    
    long getImageStartPosition();
    
    String getLocalImagePath();
    
    String getName();
    
    int getNumberOfDisplays();
    
    short getOriginX();
    
    short getOriginY();
    
    int getSize();
    
    int getStartTime();
    
    int getTotalIndex();
    
    short getWidth();
    
    boolean inRange(final long p0);
    
    boolean isDownloaded();
    
    boolean isVisibleInGivenTimeRange(final long p0, final long p1);
    
    void seeked(final long p0);
    
    void setLocalImagePath(final String p0);
    
    void setTotalIndex(final int p0);
    
    boolean wasDisplayed();
}
