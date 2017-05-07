// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.bitrate;

public class AudioBitrateRange extends BitrateRange
{
    public AudioBitrateRange(final int n, final int n2) {
        super(n, n2);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof AudioBitrateRange)) {
                return false;
            }
            final AudioBitrateRange audioBitrateRange = (AudioBitrateRange)o;
            if (this.maximal != audioBitrateRange.maximal) {
                return false;
            }
            if (this.minimal != audioBitrateRange.minimal) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return ((this.maximal + 31) * 31 + this.minimal) * 31 + 2;
    }
    
    @Override
    public String toString() {
        return "AudioBitrateRange [minimal=" + this.minimal + ", maximal=" + this.maximal + "]";
    }
}
