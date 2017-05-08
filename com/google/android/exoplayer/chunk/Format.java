// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.Assertions;

public class Format
{
    public final int audioChannels;
    public final int audioSamplingRate;
    public final int bitrate;
    public final String codecs;
    public final float frameRate;
    public final int height;
    public final String id;
    public final String language;
    public final String mimeType;
    public final int width;
    
    public Format(final String s, final String mimeType, final int width, final int height, final float frameRate, final int audioChannels, final int audioSamplingRate, final int bitrate, final String language, final String codecs) {
        this.id = Assertions.checkNotNull(s);
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        this.audioChannels = audioChannels;
        this.audioSamplingRate = audioSamplingRate;
        this.bitrate = bitrate;
        this.language = language;
        this.codecs = codecs;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && ((Format)o).id.equals(this.id));
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
