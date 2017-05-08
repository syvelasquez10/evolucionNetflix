// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.chunk.Format;

public final class DashChunkSource$ExposedTrack
{
    private final int adaptationSetIndex;
    private final Format[] adaptiveFormats;
    public final int adaptiveMaxHeight;
    public final int adaptiveMaxWidth;
    private final Format fixedFormat;
    public final MediaFormat trackFormat;
    
    public DashChunkSource$ExposedTrack(final MediaFormat trackFormat, final int adaptationSetIndex, final Format fixedFormat) {
        this.trackFormat = trackFormat;
        this.adaptationSetIndex = adaptationSetIndex;
        this.fixedFormat = fixedFormat;
        this.adaptiveFormats = null;
        this.adaptiveMaxWidth = -1;
        this.adaptiveMaxHeight = -1;
    }
    
    public DashChunkSource$ExposedTrack(final MediaFormat trackFormat, final int adaptationSetIndex, final Format[] adaptiveFormats, final int adaptiveMaxWidth, final int adaptiveMaxHeight) {
        this.trackFormat = trackFormat;
        this.adaptationSetIndex = adaptationSetIndex;
        this.adaptiveFormats = adaptiveFormats;
        this.adaptiveMaxWidth = adaptiveMaxWidth;
        this.adaptiveMaxHeight = adaptiveMaxHeight;
        this.fixedFormat = null;
    }
    
    public boolean isAdaptive() {
        return this.adaptiveFormats != null;
    }
}
