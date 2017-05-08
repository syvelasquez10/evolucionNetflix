// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import java.util.Arrays;
import java.io.IOException;

public abstract class SampleSourceTrackRenderer extends TrackRenderer
{
    private long durationUs;
    private SampleSource$SampleSourceReader enabledSource;
    private int enabledSourceTrackIndex;
    private int[] handledSourceIndices;
    private int[] handledSourceTrackIndices;
    private final SampleSource$SampleSourceReader[] sources;
    
    public SampleSourceTrackRenderer(final SampleSource... array) {
        this.sources = new SampleSource$SampleSourceReader[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.sources[i] = array[i].register();
        }
    }
    
    private long checkForDiscontinuity(long n) {
        final long discontinuity = this.enabledSource.readDiscontinuity(this.enabledSourceTrackIndex);
        if (discontinuity != Long.MIN_VALUE) {
            this.onDiscontinuity(discontinuity);
            n = discontinuity;
        }
        return n;
    }
    
    private void maybeThrowError(final SampleSource$SampleSourceReader sampleSource$SampleSourceReader) {
        try {
            sampleSource$SampleSourceReader.maybeThrowError();
        }
        catch (IOException ex) {
            throw new ExoPlaybackException(ex);
        }
    }
    
    @Override
    protected final boolean doPrepare(long max) {
        boolean b = true;
        for (int i = 0; i < this.sources.length; ++i) {
            b &= this.sources[i].prepare(max);
        }
        if (!b) {
            return false;
        }
        int n = 0;
        for (int j = 0; j < this.sources.length; ++j) {
            n += this.sources[j].getTrackCount();
        }
        final int[] array = new int[n];
        final int[] array2 = new int[n];
        final int length = this.sources.length;
        int n2 = 0;
        long durationUs = 0L;
        for (int k = 0; k < length; ++k) {
            final SampleSource$SampleSourceReader sampleSource$SampleSourceReader = this.sources[k];
            int n3;
            for (int trackCount = sampleSource$SampleSourceReader.getTrackCount(), l = 0; l < trackCount; ++l, n2 = n3, durationUs = max) {
                while (true) {
                    final MediaFormat format = sampleSource$SampleSourceReader.getFormat(l);
                    while (true) {
                        int n4 = 0;
                        Label_0232: {
                            try {
                                final boolean handlesTrack = this.handlesTrack(format);
                                n3 = n2;
                                max = durationUs;
                                if (handlesTrack) {
                                    array[n2] = k;
                                    array2[n2] = l;
                                    n4 = n2 + 1;
                                    if (durationUs != -1L) {
                                        break Label_0232;
                                    }
                                    max = durationUs;
                                    n3 = n4;
                                }
                                break;
                            }
                            catch (MediaCodecUtil$DecoderQueryException ex) {
                                throw new ExoPlaybackException(ex);
                            }
                        }
                        final long durationUs2 = format.durationUs;
                        if (durationUs2 == -1L) {
                            max = -1L;
                            n3 = n4;
                            continue;
                        }
                        n3 = n4;
                        max = durationUs;
                        if (durationUs2 != -2L) {
                            max = Math.max(durationUs, durationUs2);
                            n3 = n4;
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
        this.durationUs = durationUs;
        this.handledSourceIndices = Arrays.copyOf(array, n2);
        this.handledSourceTrackIndices = Arrays.copyOf(array2, n2);
        return true;
    }
    
    @Override
    protected final void doSomeWork(long shiftInputPosition, final long n) {
        shiftInputPosition = this.shiftInputPosition(shiftInputPosition);
        this.doSomeWork(this.checkForDiscontinuity(shiftInputPosition), n, this.enabledSource.continueBuffering(this.enabledSourceTrackIndex, shiftInputPosition));
    }
    
    protected abstract void doSomeWork(final long p0, final long p1, final boolean p2);
    
    @Override
    protected long getBufferedPositionUs() {
        return this.enabledSource.getBufferedPositionUs();
    }
    
    @Override
    protected long getDurationUs() {
        return this.durationUs;
    }
    
    @Override
    protected final MediaFormat getFormat(final int n) {
        return this.sources[this.handledSourceIndices[n]].getFormat(this.handledSourceTrackIndices[n]);
    }
    
    @Override
    protected final int getTrackCount() {
        return this.handledSourceTrackIndices.length;
    }
    
    protected abstract boolean handlesTrack(final MediaFormat p0);
    
    @Override
    protected void maybeThrowError() {
        if (this.enabledSource != null) {
            this.maybeThrowError(this.enabledSource);
        }
        else {
            for (int length = this.sources.length, i = 0; i < length; ++i) {
                this.maybeThrowError(this.sources[i]);
            }
        }
    }
    
    @Override
    protected void onDisabled() {
        this.enabledSource.disable(this.enabledSourceTrackIndex);
        this.enabledSource = null;
    }
    
    protected abstract void onDiscontinuity(final long p0);
    
    @Override
    protected void onEnabled(final int n, long shiftInputPosition, final boolean b) {
        shiftInputPosition = this.shiftInputPosition(shiftInputPosition);
        this.enabledSource = this.sources[this.handledSourceIndices[n]];
        this.enabledSourceTrackIndex = this.handledSourceTrackIndices[n];
        this.enabledSource.enable(this.enabledSourceTrackIndex, shiftInputPosition);
        this.onDiscontinuity(shiftInputPosition);
    }
    
    @Override
    protected void onReleased() {
        for (int length = this.sources.length, i = 0; i < length; ++i) {
            this.sources[i].release();
        }
    }
    
    protected final int readSource(final long n, final MediaFormatHolder mediaFormatHolder, final SampleHolder sampleHolder) {
        return this.enabledSource.readData(this.enabledSourceTrackIndex, n, mediaFormatHolder, sampleHolder);
    }
    
    @Override
    protected final void seekTo(long shiftInputPosition) {
        shiftInputPosition = this.shiftInputPosition(shiftInputPosition);
        this.enabledSource.seekToUs(shiftInputPosition);
        this.checkForDiscontinuity(shiftInputPosition);
    }
    
    protected long shiftInputPosition(final long n) {
        return n;
    }
}
