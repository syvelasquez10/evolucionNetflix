// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

public final class SubtitleTextNode
{
    private int mLineBreaks;
    private TextStyle mStyle;
    private String mText;
    
    SubtitleTextNode(final TextStyle mStyle, final String mText, final int mLineBreaks) {
        this.mStyle = mStyle;
        this.mText = mText;
        this.mLineBreaks = mLineBreaks;
    }
    
    public int getLineBreaks() {
        return this.mLineBreaks;
    }
    
    public TextStyle getStyle() {
        return this.mStyle;
    }
    
    public String getText() {
        return this.mText;
    }
    
    @Override
    public String toString() {
        return "SubtitleTextNode [mStyle=" + this.mStyle + ", mText=" + this.mText + ", mLineBreaks=" + this.mLineBreaks + "]";
    }
}
