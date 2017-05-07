// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import java.util.List;

public class SubtitleScreen
{
    public static final int DEFAULT_SUBTITLE_SCREEN_UPDATE = 2000;
    private List<SubtitleBlock> mDisplayLaterBlocks;
    private List<SubtitleBlock> mDisplayNowBlocks;
    private String mId;
    private SubtitleParser mParser;
    private int mUpdateTimeout;
    
    public SubtitleScreen(final SubtitleParser mParser, final List<SubtitleBlock> mDisplayNowBlocks, final List<SubtitleBlock> mDisplayLaterBlocks, final int mUpdateTimeout) {
        this.mId = String.valueOf(System.currentTimeMillis());
        this.mUpdateTimeout = 2000;
        this.mId = "" + this.hashCode();
        this.mParser = mParser;
        this.mDisplayLaterBlocks = mDisplayLaterBlocks;
        this.mDisplayNowBlocks = mDisplayNowBlocks;
        this.mUpdateTimeout = mUpdateTimeout;
    }
    
    public List<SubtitleBlock> getDisplayLaterBlocks() {
        return this.mDisplayLaterBlocks;
    }
    
    public List<SubtitleBlock> getDisplayNowBlocks() {
        return this.mDisplayNowBlocks;
    }
    
    public String getId() {
        return this.mId;
    }
    
    public SubtitleParser getParser() {
        return this.mParser;
    }
    
    public int getUpdateTimeout() {
        return this.mUpdateTimeout;
    }
}
