// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleBlock;

public class ImageSubtitleBlock extends BaseSubtitleBlock
{
    private SegmentIndex$ImageDescriptor mImage;
    
    public ImageSubtitleBlock(final SegmentIndex$ImageDescriptor mImage) {
        this.mImage = mImage;
        this.mStart = mImage.getStartTime();
        this.mEnd = mImage.getEndTime();
        this.mId = mImage.getName();
    }
    
    public SegmentIndex$ImageDescriptor getImage() {
        return this.mImage;
    }
}
