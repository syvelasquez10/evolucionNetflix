// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleBlock;

public class ImageSubtitleBlock extends BaseSubtitleBlock
{
    private ImageDescriptor mImage;
    
    public ImageSubtitleBlock(final ImageDescriptor mImage) {
        this.mImage = mImage;
        this.mStart = mImage.getStartTime();
        this.mEnd = mImage.getEndTime();
        this.mId = mImage.getName();
    }
    
    @Override
    public void displayed() {
        this.mImage.displayed();
    }
    
    public ImageDescriptor getImage() {
        return this.mImage;
    }
    
    @Override
    public int getNumberOfDisplays() {
        return this.mImage.getNumberOfDisplays();
    }
    
    @Override
    public void seeked(final long n) {
        this.mImage.seeked(n);
    }
    
    @Override
    public String toString() {
        return "ImageSubtitleBlock{mImage=" + this.mImage + super.toString() + "} ";
    }
    
    @Override
    public boolean wasDisplayed() {
        return this.mImage.wasDisplayed();
    }
}
