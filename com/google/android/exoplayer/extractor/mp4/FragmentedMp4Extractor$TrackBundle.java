// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.extractor.TrackOutput;

final class FragmentedMp4Extractor$TrackBundle
{
    public int currentSampleIndex;
    public DefaultSampleValues defaultSampleValues;
    public final TrackFragment fragment;
    public final TrackOutput output;
    public Track track;
    
    public FragmentedMp4Extractor$TrackBundle(final TrackOutput output) {
        this.fragment = new TrackFragment();
        this.output = output;
    }
    
    public void init(final Track track, final DefaultSampleValues defaultSampleValues) {
        this.track = Assertions.checkNotNull(track);
        this.defaultSampleValues = Assertions.checkNotNull(defaultSampleValues);
        this.output.format(track.mediaFormat);
        this.reset();
    }
    
    public void reset() {
        this.fragment.reset();
        this.currentSampleIndex = 0;
    }
}
