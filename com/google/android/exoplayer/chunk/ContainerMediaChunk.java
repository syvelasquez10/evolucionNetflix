// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;

public class ContainerMediaChunk extends BaseMediaChunk implements ChunkExtractorWrapper$SingleTrackOutput
{
    private final int adaptiveMaxHeight;
    private final int adaptiveMaxWidth;
    private volatile int bytesLoaded;
    private DrmInitData drmInitData;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private MediaFormat mediaFormat;
    private final long sampleOffsetUs;
    
    public ContainerMediaChunk(final DataSource dataSource, final DataSpec dataSpec, final int n, final Format format, final long n2, final long n3, final int n4, final long sampleOffsetUs, final ChunkExtractorWrapper extractorWrapper, final MediaFormat mediaFormat, final int adaptiveMaxWidth, final int adaptiveMaxHeight, final DrmInitData drmInitData, final boolean b, final int n5) {
        super(dataSource, dataSpec, n, format, n2, n3, n4, b, n5);
        this.extractorWrapper = extractorWrapper;
        this.sampleOffsetUs = sampleOffsetUs;
        this.adaptiveMaxWidth = adaptiveMaxWidth;
        this.adaptiveMaxHeight = adaptiveMaxHeight;
        this.mediaFormat = getAdjustedMediaFormat(mediaFormat, sampleOffsetUs, adaptiveMaxWidth, adaptiveMaxHeight);
        this.drmInitData = drmInitData;
    }
    
    private static MediaFormat getAdjustedMediaFormat(MediaFormat copyWithSubsampleOffsetUs, final long n, final int n2, final int n3) {
        if (copyWithSubsampleOffsetUs != null) {
            if (n != 0L && copyWithSubsampleOffsetUs.subsampleOffsetUs != Long.MAX_VALUE) {
                copyWithSubsampleOffsetUs = copyWithSubsampleOffsetUs.copyWithSubsampleOffsetUs(copyWithSubsampleOffsetUs.subsampleOffsetUs + n);
            }
            if (n2 == -1) {
                final MediaFormat mediaFormat = copyWithSubsampleOffsetUs;
                if (n3 == -1) {
                    return mediaFormat;
                }
            }
            return copyWithSubsampleOffsetUs.copyWithMaxVideoDimensions(n2, n3);
        }
        return null;
    }
    
    @Override
    public final long bytesLoaded() {
        return this.bytesLoaded;
    }
    
    @Override
    public final void cancelLoad() {
        this.loadCanceled = true;
    }
    
    @Override
    public final void drmInitData(final DrmInitData drmInitData) {
        this.drmInitData = drmInitData;
    }
    
    @Override
    public final void format(final MediaFormat mediaFormat) {
        this.mediaFormat = getAdjustedMediaFormat(mediaFormat, this.sampleOffsetUs, this.adaptiveMaxWidth, this.adaptiveMaxHeight);
    }
    
    @Override
    public final DrmInitData getDrmInitData() {
        return this.drmInitData;
    }
    
    @Override
    public final MediaFormat getMediaFormat() {
        return this.mediaFormat;
    }
    
    @Override
    public final boolean isLoadCanceled() {
        return this.loadCanceled;
    }
    
    @Override
    public final void load() {
        final DataSpec remainderDataSpec = Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded);
        try {
            final DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpec.absoluteStreamPosition, this.dataSource.open(remainderDataSpec));
            if (this.bytesLoaded == 0) {
                this.extractorWrapper.init(this);
            }
            int read = 0;
            while (true) {
                if (read != 0) {
                    return;
                }
                try {
                    if (!this.loadCanceled) {
                        read = this.extractorWrapper.read(defaultExtractorInput);
                        continue;
                    }
                }
                finally {
                    this.bytesLoaded = (int)(defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                }
            }
        }
        finally {
            this.dataSource.close();
        }
    }
    
    @Override
    public final int sampleData(final ExtractorInput extractorInput, final int n, final boolean b) {
        return this.getOutput().sampleData(extractorInput, n, b);
    }
    
    @Override
    public final void sampleData(final ParsableByteArray parsableByteArray, final int n) {
        this.getOutput().sampleData(parsableByteArray, n);
    }
    
    @Override
    public final void sampleMetadata(final long n, final int n2, final int n3, final int n4, final byte[] array) {
        this.getOutput().sampleMetadata(this.sampleOffsetUs + n, n2, n3, n4, array);
    }
    
    @Override
    public final void seekMap(final SeekMap seekMap) {
    }
}
