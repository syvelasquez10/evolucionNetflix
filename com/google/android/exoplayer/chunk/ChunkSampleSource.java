// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.upstream.Loader$Loadable;
import android.os.SystemClock;
import java.util.Collections;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import java.util.List;
import java.util.LinkedList;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.LoadControl;
import android.os.Handler;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import java.io.IOException;
import com.google.android.exoplayer.upstream.Loader$Callback;
import com.google.android.exoplayer.SampleSource$SampleSourceReader;
import com.google.android.exoplayer.SampleSource;

public class ChunkSampleSource implements SampleSource, SampleSource$SampleSourceReader, Loader$Callback
{
    private final int bufferSizeContribution;
    private final ChunkSource chunkSource;
    private long currentLoadStartTimeMs;
    private IOException currentLoadableException;
    private int currentLoadableExceptionCount;
    private long currentLoadableExceptionTimestamp;
    private final ChunkOperationHolder currentLoadableHolder;
    private DrmInitData downstreamDrmInitData;
    private Format downstreamFormat;
    private MediaFormat downstreamMediaFormat;
    private long downstreamPositionUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final ChunkSampleSource$EventListener eventListener;
    private final int eventSourceId;
    private long lastPerformedBufferOperation;
    private long lastSeekPositionUs;
    private final LoadControl loadControl;
    private Loader loader;
    private boolean loadingFinished;
    private final LinkedList<BaseMediaChunk> mediaChunks;
    private final int minLoadableRetryCount;
    private boolean pendingDiscontinuity;
    private long pendingResetPositionUs;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    protected final DefaultTrackOutput sampleQueue;
    private int state;
    
    public ChunkSampleSource(final ChunkSource chunkSource, final LoadControl loadControl, final int n, final Handler handler, final ChunkSampleSource$EventListener chunkSampleSource$EventListener, final int n2) {
        this(chunkSource, loadControl, n, handler, chunkSampleSource$EventListener, n2, 3);
    }
    
    public ChunkSampleSource(final ChunkSource chunkSource, final LoadControl loadControl, final int bufferSizeContribution, final Handler eventHandler, final ChunkSampleSource$EventListener eventListener, final int eventSourceId, final int minLoadableRetryCount) {
        this.chunkSource = chunkSource;
        this.loadControl = loadControl;
        this.bufferSizeContribution = bufferSizeContribution;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.eventSourceId = eventSourceId;
        this.minLoadableRetryCount = minLoadableRetryCount;
        this.currentLoadableHolder = new ChunkOperationHolder();
        this.mediaChunks = new LinkedList<BaseMediaChunk>();
        this.readOnlyMediaChunks = Collections.unmodifiableList((List<? extends BaseMediaChunk>)this.mediaChunks);
        this.sampleQueue = new DefaultTrackOutput(loadControl.getAllocator());
        this.state = 0;
        this.pendingResetPositionUs = Long.MIN_VALUE;
    }
    
    private void clearCurrentLoadable() {
        this.currentLoadableHolder.chunk = null;
        this.clearCurrentLoadableException();
    }
    
    private void clearCurrentLoadableException() {
        this.currentLoadableException = null;
        this.currentLoadableExceptionCount = 0;
    }
    
    private boolean discardUpstreamMediaChunks(final int n) {
        if (this.mediaChunks.size() <= n) {
            return false;
        }
        long startTimeUs = 0L;
        final long endTimeUs = this.mediaChunks.getLast().endTimeUs;
        BaseMediaChunk baseMediaChunk = null;
        while (this.mediaChunks.size() > n) {
            baseMediaChunk = this.mediaChunks.removeLast();
            startTimeUs = baseMediaChunk.startTimeUs;
            this.loadingFinished = false;
        }
        this.sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex());
        this.notifyUpstreamDiscarded(startTimeUs, endTimeUs);
        return true;
    }
    
    private void doChunkOperation() {
        this.currentLoadableHolder.endOfStream = false;
        this.currentLoadableHolder.queueSize = this.readOnlyMediaChunks.size();
        final ChunkSource chunkSource = this.chunkSource;
        final List<BaseMediaChunk> readOnlyMediaChunks = this.readOnlyMediaChunks;
        long n;
        if (this.pendingResetPositionUs != Long.MIN_VALUE) {
            n = this.pendingResetPositionUs;
        }
        else {
            n = this.downstreamPositionUs;
        }
        chunkSource.getChunkOperation(readOnlyMediaChunks, n, this.currentLoadableHolder);
        this.loadingFinished = this.currentLoadableHolder.endOfStream;
    }
    
    private long getNextLoadPositionUs() {
        if (this.isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return -1L;
        }
        return this.mediaChunks.getLast().endTimeUs;
    }
    
    private long getRetryDelayMillis(final long n) {
        return Math.min((n - 1L) * 1000L, 5000L);
    }
    
    private boolean isMediaChunk(final Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }
    
    private boolean isPendingReset() {
        return this.pendingResetPositionUs != Long.MIN_VALUE;
    }
    
    private void maybeStartLoading() {
        final Chunk chunk = this.currentLoadableHolder.chunk;
        if (chunk == null) {
            return;
        }
        this.currentLoadStartTimeMs = SystemClock.elapsedRealtime();
        if (this.isMediaChunk(chunk)) {
            final BaseMediaChunk baseMediaChunk = (BaseMediaChunk)chunk;
            baseMediaChunk.init(this.sampleQueue);
            this.mediaChunks.add(baseMediaChunk);
            if (this.isPendingReset()) {
                this.pendingResetPositionUs = Long.MIN_VALUE;
            }
            this.notifyLoadStarted(baseMediaChunk.dataSpec.length, baseMediaChunk.type, baseMediaChunk.trigger, baseMediaChunk.format, baseMediaChunk.startTimeUs, baseMediaChunk.endTimeUs);
        }
        else {
            this.notifyLoadStarted(chunk.dataSpec.length, chunk.type, chunk.trigger, chunk.format, -1L, -1L);
        }
        this.loader.startLoading(chunk, this);
    }
    
    private void notifyDownstreamFormatChanged(final Format format, final int n, final long n2) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$6(this, format, n, n2));
        }
    }
    
    private void notifyLoadCanceled(final long n) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$3(this, n));
        }
    }
    
    private void notifyLoadCompleted(final long n, final int n2, final int n3, final Format format, final long n4, final long n5, final long n6, final long n7) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$2(this, n, n2, n3, format, n4, n5, n6, n7));
        }
    }
    
    private void notifyLoadError(final IOException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$4(this, ex));
        }
    }
    
    private void notifyLoadStarted(final long n, final int n2, final int n3, final Format format, final long n4, final long n5) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$1(this, n, n2, n3, format, n4, n5));
        }
    }
    
    private void notifyUpstreamDiscarded(final long n, final long n2) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ChunkSampleSource$5(this, n, n2));
        }
    }
    
    private void restartFrom(final long pendingResetPositionUs) {
        this.pendingResetPositionUs = pendingResetPositionUs;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return;
        }
        this.sampleQueue.clear();
        this.mediaChunks.clear();
        this.clearCurrentLoadable();
        this.updateLoadControl();
    }
    
    private void resumeFromBackOff() {
        this.currentLoadableException = null;
        final Chunk chunk = this.currentLoadableHolder.chunk;
        if (!this.isMediaChunk(chunk)) {
            this.doChunkOperation();
            this.discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
            if (this.currentLoadableHolder.chunk == chunk) {
                this.loader.startLoading(chunk, this);
                return;
            }
            this.notifyLoadCanceled(chunk.bytesLoaded());
            this.maybeStartLoading();
        }
        else {
            if (chunk == this.mediaChunks.getFirst()) {
                this.loader.startLoading(chunk, this);
                return;
            }
            final BaseMediaChunk baseMediaChunk = this.mediaChunks.removeLast();
            Assertions.checkState(chunk == baseMediaChunk);
            this.doChunkOperation();
            this.mediaChunks.add(baseMediaChunk);
            if (this.currentLoadableHolder.chunk == chunk) {
                this.loader.startLoading(chunk, this);
                return;
            }
            this.notifyLoadCanceled(chunk.bytesLoaded());
            this.discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
            this.clearCurrentLoadableException();
            this.maybeStartLoading();
        }
    }
    
    private void updateLoadControl() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long nextLoadPositionUs = this.getNextLoadPositionUs();
        boolean b;
        if (this.currentLoadableException != null) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = this.loader.isLoading() || b;
        long nextLoadPositionUs2 = nextLoadPositionUs;
        Label_0118: {
            if (!b2) {
                if (this.currentLoadableHolder.chunk != null || nextLoadPositionUs == -1L) {
                    nextLoadPositionUs2 = nextLoadPositionUs;
                    if (elapsedRealtime - this.lastPerformedBufferOperation <= 2000L) {
                        break Label_0118;
                    }
                }
                this.lastPerformedBufferOperation = elapsedRealtime;
                this.doChunkOperation();
                final boolean discardUpstreamMediaChunks = this.discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
                if (this.currentLoadableHolder.chunk == null) {
                    nextLoadPositionUs2 = -1L;
                }
                else {
                    nextLoadPositionUs2 = nextLoadPositionUs;
                    if (discardUpstreamMediaChunks) {
                        nextLoadPositionUs2 = this.getNextLoadPositionUs();
                    }
                }
            }
        }
        final boolean update = this.loadControl.update(this, this.downstreamPositionUs, nextLoadPositionUs2, b2);
        if (b) {
            if (elapsedRealtime - this.currentLoadableExceptionTimestamp >= this.getRetryDelayMillis(this.currentLoadableExceptionCount)) {
                this.resumeFromBackOff();
            }
        }
        else if (!this.loader.isLoading() && update) {
            this.maybeStartLoading();
        }
    }
    
    @Override
    public boolean continueBuffering(final int n, final long downstreamPositionUs) {
        final boolean b = false;
        Assertions.checkState(this.state == 3);
        this.downstreamPositionUs = downstreamPositionUs;
        this.chunkSource.continueBuffering(downstreamPositionUs);
        this.updateLoadControl();
        if (!this.loadingFinished) {
            final boolean b2 = b;
            if (this.sampleQueue.isEmpty()) {
                return b2;
            }
        }
        return true;
    }
    
    @Override
    public void disable(int enabledTrackCount) {
        final boolean b = true;
        while (true) {
            while (true) {
                boolean b2 = false;
                Label_0012: {
                    if (this.state == 3) {
                        b2 = true;
                        break Label_0012;
                    }
                    Label_0084: {
                        break Label_0084;
                        while (true) {
                            Assertions.checkState(b2);
                            this.state = 2;
                            try {
                                this.chunkSource.disable(this.mediaChunks);
                                this.loadControl.unregister(this);
                                if (this.loader.isLoading()) {
                                    this.loader.cancelLoading();
                                    return;
                                }
                                this.sampleQueue.clear();
                                this.mediaChunks.clear();
                                this.clearCurrentLoadable();
                                this.loadControl.trimAllocator();
                                return;
                                b2 = false;
                                break;
                                b2 = false;
                            }
                            finally {
                                this.loadControl.unregister(this);
                                while (true) {
                                    if (this.loader.isLoading()) {
                                        this.loader.cancelLoading();
                                        break Label_0151;
                                    }
                                    this.sampleQueue.clear();
                                    this.mediaChunks.clear();
                                    this.clearCurrentLoadable();
                                    this.loadControl.trimAllocator();
                                    break Label_0151;
                                    continue;
                                }
                            }
                        }
                    }
                }
                Assertions.checkState(b2);
                enabledTrackCount = this.enabledTrackCount - 1;
                this.enabledTrackCount = enabledTrackCount;
                if (enabledTrackCount == 0) {
                    b2 = b;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public void enable(final int n, final long n2) {
        final boolean b = true;
        Assertions.checkState(this.state == 2);
        Assertions.checkState(this.enabledTrackCount++ == 0 && b);
        this.state = 3;
        this.chunkSource.enable(n);
        this.loadControl.register(this, this.bufferSizeContribution);
        this.downstreamFormat = null;
        this.downstreamMediaFormat = null;
        this.downstreamDrmInitData = null;
        this.downstreamPositionUs = n2;
        this.lastSeekPositionUs = n2;
        this.pendingDiscontinuity = false;
        this.restartFrom(n2);
    }
    
    @Override
    public long getBufferedPositionUs() {
        Assertions.checkState(this.state == 3);
        long n;
        if (this.isPendingReset()) {
            n = this.pendingResetPositionUs;
        }
        else {
            if (this.loadingFinished) {
                return -3L;
            }
            if ((n = this.sampleQueue.getLargestParsedTimestampUs()) == Long.MIN_VALUE) {
                return this.downstreamPositionUs;
            }
        }
        return n;
    }
    
    @Override
    public MediaFormat getFormat(final int n) {
        Assertions.checkState(this.state == 2 || this.state == 3);
        return this.chunkSource.getFormat(n);
    }
    
    @Override
    public int getTrackCount() {
        Assertions.checkState(this.state == 2 || this.state == 3);
        return this.chunkSource.getTrackCount();
    }
    
    @Override
    public void maybeThrowError() {
        if (this.currentLoadableException != null && this.currentLoadableExceptionCount > this.minLoadableRetryCount) {
            throw this.currentLoadableException;
        }
        if (this.currentLoadableHolder.chunk == null) {
            this.chunkSource.maybeThrowError();
        }
    }
    
    @Override
    public void onLoadCanceled(final Loader$Loadable loader$Loadable) {
        this.notifyLoadCanceled(this.currentLoadableHolder.chunk.bytesLoaded());
        this.clearCurrentLoadable();
        if (this.state == 3) {
            this.restartFrom(this.pendingResetPositionUs);
            return;
        }
        this.sampleQueue.clear();
        this.mediaChunks.clear();
        this.clearCurrentLoadable();
        this.loadControl.trimAllocator();
    }
    
    @Override
    public void onLoadCompleted(final Loader$Loadable loader$Loadable) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long n = elapsedRealtime - this.currentLoadStartTimeMs;
        final Chunk chunk = this.currentLoadableHolder.chunk;
        this.chunkSource.onChunkLoadCompleted(chunk);
        if (this.isMediaChunk(chunk)) {
            final BaseMediaChunk baseMediaChunk = (BaseMediaChunk)chunk;
            this.notifyLoadCompleted(chunk.bytesLoaded(), baseMediaChunk.type, baseMediaChunk.trigger, baseMediaChunk.format, baseMediaChunk.startTimeUs, baseMediaChunk.endTimeUs, elapsedRealtime, n);
        }
        else {
            this.notifyLoadCompleted(chunk.bytesLoaded(), chunk.type, chunk.trigger, chunk.format, -1L, -1L, elapsedRealtime, n);
        }
        this.clearCurrentLoadable();
        this.updateLoadControl();
    }
    
    @Override
    public void onLoadError(final Loader$Loadable loader$Loadable, final IOException currentLoadableException) {
        this.currentLoadableException = currentLoadableException;
        ++this.currentLoadableExceptionCount;
        this.currentLoadableExceptionTimestamp = SystemClock.elapsedRealtime();
        this.notifyLoadError(currentLoadableException);
        this.chunkSource.onChunkLoadError(this.currentLoadableHolder.chunk, currentLoadableException);
        this.updateLoadControl();
    }
    
    protected void onSampleRead(final MediaChunk mediaChunk, final SampleHolder sampleHolder) {
    }
    
    @Override
    public boolean prepare(final long n) {
        Assertions.checkState(this.state == 1 || this.state == 2);
        if (this.state == 2) {
            return true;
        }
        if (!this.chunkSource.prepare()) {
            return false;
        }
        if (this.chunkSource.getTrackCount() > 0) {
            this.loader = new Loader("Loader:" + this.chunkSource.getFormat(0).mimeType);
        }
        this.state = 2;
        return true;
    }
    
    @Override
    public int readData(int n, final long downstreamPositionUs, final MediaFormatHolder mediaFormatHolder, final SampleHolder sampleHolder) {
        int n2 = 0;
        final int n3 = 1;
        Assertions.checkState(this.state == 3);
        this.downstreamPositionUs = downstreamPositionUs;
        if (this.pendingDiscontinuity || this.isPendingReset()) {
            return -2;
        }
        if (!this.sampleQueue.isEmpty()) {
            n = 1;
        }
        else {
            n = 0;
        }
        BaseMediaChunk baseMediaChunk = this.mediaChunks.getFirst();
        while (n != 0 && this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex() <= this.sampleQueue.getReadIndex()) {
            this.mediaChunks.removeFirst();
            baseMediaChunk = this.mediaChunks.getFirst();
        }
        final Format format = baseMediaChunk.format;
        if (!format.equals(this.downstreamFormat)) {
            this.notifyDownstreamFormatChanged(format, baseMediaChunk.trigger, baseMediaChunk.startTimeUs);
        }
        this.downstreamFormat = format;
        if (n != 0 || baseMediaChunk.isMediaFormatFinal) {
            final MediaFormat mediaFormat = baseMediaChunk.getMediaFormat();
            final DrmInitData drmInitData = baseMediaChunk.getDrmInitData();
            if (!mediaFormat.equals(this.downstreamMediaFormat) || !Util.areEqual(this.downstreamDrmInitData, drmInitData)) {
                mediaFormatHolder.format = mediaFormat;
                mediaFormatHolder.drmInitData = drmInitData;
                this.downstreamMediaFormat = mediaFormat;
                this.downstreamDrmInitData = drmInitData;
                return -4;
            }
            this.downstreamMediaFormat = mediaFormat;
            this.downstreamDrmInitData = drmInitData;
        }
        if (n == 0) {
            if (this.loadingFinished) {
                return -1;
            }
            return -2;
        }
        else {
            if (this.sampleQueue.getSample(sampleHolder)) {
                if (sampleHolder.timeUs < this.lastSeekPositionUs) {
                    n = n3;
                }
                else {
                    n = 0;
                }
                final int flags = sampleHolder.flags;
                if (n != 0) {
                    n2 = 134217728;
                }
                sampleHolder.flags = (flags | n2);
                this.onSampleRead(baseMediaChunk, sampleHolder);
                return -3;
            }
            return -2;
        }
    }
    
    @Override
    public long readDiscontinuity(final int n) {
        if (this.pendingDiscontinuity) {
            this.pendingDiscontinuity = false;
            return this.lastSeekPositionUs;
        }
        return Long.MIN_VALUE;
    }
    
    @Override
    public SampleSource$SampleSourceReader register() {
        Assertions.checkState(this.state == 0);
        this.state = 1;
        return this;
    }
    
    @Override
    public void release() {
        Assertions.checkState(this.state != 3);
        if (this.loader != null) {
            this.loader.release();
            this.loader = null;
        }
        this.state = 0;
    }
    
    @Override
    public void seekToUs(final long n) {
        final boolean b = false;
        Assertions.checkState(this.state == 3);
        long n2;
        if (this.isPendingReset()) {
            n2 = this.pendingResetPositionUs;
        }
        else {
            n2 = this.downstreamPositionUs;
        }
        this.downstreamPositionUs = n;
        this.lastSeekPositionUs = n;
        if (n2 == n) {
            return;
        }
        int n3;
        if (!this.isPendingReset() && this.sampleQueue.skipToKeyframeBefore(n)) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        if (n3 != 0) {
            boolean b2 = b;
            if (!this.sampleQueue.isEmpty()) {
                b2 = true;
            }
            while (b2 && this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex() <= this.sampleQueue.getReadIndex()) {
                this.mediaChunks.removeFirst();
            }
        }
        else {
            this.restartFrom(n);
        }
        this.pendingDiscontinuity = true;
    }
    
    protected final long usToMs(final long n) {
        return n / 1000L;
    }
}
