// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.drm.DrmInitData$Mapped;
import com.google.android.exoplayer.dash.mpd.ContentProtection;
import java.util.HashMap;
import com.google.android.exoplayer.chunk.ContainerMediaChunk;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer.chunk.ChunkOperationHolder;
import com.google.android.exoplayer.chunk.MediaChunk;
import java.util.Comparator;
import java.util.Arrays;
import com.google.android.exoplayer.chunk.Format$DecreasingBandwidthComparator;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import android.util.Log;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.BehindLiveWindowException;
import com.google.android.exoplayer.chunk.InitializationChunk;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer.dash.mpd.Representation;
import com.google.android.exoplayer.dash.mpd.RangedUri;
import java.util.List;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.TimeRange$DynamicTimeRange;
import com.google.android.exoplayer.TimeRange$StaticTimeRange;
import com.google.android.exoplayer.util.SystemClock;
import java.util.ArrayList;
import com.google.android.exoplayer.util.Clock;
import android.util.SparseArray;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.extractor.ChunkIndex;
import java.io.IOException;
import android.os.Handler;
import com.google.android.exoplayer.chunk.FormatEvaluator$Evaluation;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.chunk.ChunkSource;

public class DashChunkSource implements ChunkSource, DashTrackSelector$Output
{
    private final FormatEvaluator adaptiveFormatEvaluator;
    private TimeRange availableRange;
    private final long[] availableRangeValues;
    private MediaPresentationDescription currentManifest;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetUs;
    private DashChunkSource$ExposedTrack enabledTrack;
    private final FormatEvaluator$Evaluation evaluation;
    private final Handler eventHandler;
    private final DashChunkSource$EventListener eventListener;
    private final int eventSourceId;
    private IOException fatalError;
    private boolean lastChunkWasInitialization;
    private final boolean live;
    private final long liveEdgeLatencyUs;
    private ChunkIndex mInitializationChunkIndex;
    private final ManifestFetcher<MediaPresentationDescription> manifestFetcher;
    private int nextPeriodHolderIndex;
    private final SparseArray<DashChunkSource$PeriodHolder> periodHolders;
    private boolean prepareCalled;
    private MediaPresentationDescription processedManifest;
    private boolean startAtLiveEdge;
    private final Clock systemClock;
    private final DashTrackSelector trackSelector;
    private final ArrayList<DashChunkSource$ExposedTrack> tracks;
    
    public DashChunkSource(final MediaPresentationDescription mediaPresentationDescription, final DashTrackSelector dashTrackSelector, final DataSource dataSource, final FormatEvaluator formatEvaluator) {
        this(null, mediaPresentationDescription, dashTrackSelector, dataSource, formatEvaluator, new SystemClock(), 0L, 0L, false, null, null, 0);
    }
    
    DashChunkSource(final ManifestFetcher<MediaPresentationDescription> manifestFetcher, final MediaPresentationDescription currentManifest, final DashTrackSelector trackSelector, final DataSource dataSource, final FormatEvaluator adaptiveFormatEvaluator, final Clock systemClock, final long liveEdgeLatencyUs, final long elapsedRealtimeOffsetUs, final boolean startAtLiveEdge, final Handler eventHandler, final DashChunkSource$EventListener eventListener, final int eventSourceId) {
        this.manifestFetcher = manifestFetcher;
        this.currentManifest = currentManifest;
        this.trackSelector = trackSelector;
        this.dataSource = dataSource;
        this.adaptiveFormatEvaluator = adaptiveFormatEvaluator;
        this.systemClock = systemClock;
        this.liveEdgeLatencyUs = liveEdgeLatencyUs;
        this.elapsedRealtimeOffsetUs = elapsedRealtimeOffsetUs;
        this.startAtLiveEdge = startAtLiveEdge;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.eventSourceId = eventSourceId;
        this.evaluation = new FormatEvaluator$Evaluation();
        this.availableRangeValues = new long[2];
        this.periodHolders = (SparseArray<DashChunkSource$PeriodHolder>)new SparseArray();
        this.tracks = new ArrayList<DashChunkSource$ExposedTrack>();
        this.live = currentManifest.dynamic;
    }
    
    private DashChunkSource$PeriodHolder findPeriodHolder(final long n) {
        int i = 0;
        if (n < ((DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0)).getAvailableStartTimeUs()) {
            return (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0);
        }
        while (i < this.periodHolders.size() - 1) {
            final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(i);
            if (n < dashChunkSource$PeriodHolder.getAvailableEndTimeUs()) {
                return dashChunkSource$PeriodHolder;
            }
            ++i;
        }
        return (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(this.periodHolders.size() - 1);
    }
    
    private TimeRange getAvailableRange(final long n) {
        long n2 = -1L;
        final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0);
        final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder2 = (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(this.periodHolders.size() - 1);
        if (!this.currentManifest.dynamic || dashChunkSource$PeriodHolder2.isIndexExplicit()) {
            return new TimeRange$StaticTimeRange(dashChunkSource$PeriodHolder.getAvailableStartTimeUs(), dashChunkSource$PeriodHolder2.getAvailableEndTimeUs());
        }
        final long availableStartTimeUs = dashChunkSource$PeriodHolder.getAvailableStartTimeUs();
        long availableEndTimeUs;
        if (dashChunkSource$PeriodHolder2.isIndexUnbounded()) {
            availableEndTimeUs = Long.MAX_VALUE;
        }
        else {
            availableEndTimeUs = dashChunkSource$PeriodHolder2.getAvailableEndTimeUs();
        }
        final long elapsedRealtime = this.systemClock.elapsedRealtime();
        final long availabilityStartTime = this.currentManifest.availabilityStartTime;
        if (this.currentManifest.timeShiftBufferDepth != -1L) {
            n2 = this.currentManifest.timeShiftBufferDepth * 1000L;
        }
        return new TimeRange$DynamicTimeRange(availableStartTimeUs, availableEndTimeUs, elapsedRealtime * 1000L - (n - availabilityStartTime * 1000L), n2, this.systemClock);
    }
    
    private static String getMediaMimeType(final Format format) {
        final String mimeType = format.mimeType;
        String audioMediaMimeType;
        if (MimeTypes.isAudio(mimeType)) {
            audioMediaMimeType = MimeTypes.getAudioMediaMimeType(format.codecs);
        }
        else {
            if (MimeTypes.isVideo(mimeType)) {
                return MimeTypes.getVideoMediaMimeType(format.codecs);
            }
            audioMediaMimeType = mimeType;
            if (!mimeTypeIsRawText(mimeType)) {
                if ("application/mp4".equals(mimeType)) {
                    if ("stpp".equals(format.codecs)) {
                        return "application/ttml+xml";
                    }
                    if ("wvtt".equals(format.codecs)) {
                        return "application/x-mp4vtt";
                    }
                }
                return null;
            }
        }
        return audioMediaMimeType;
    }
    
    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetUs != 0L) {
            return this.systemClock.elapsedRealtime() * 1000L + this.elapsedRealtimeOffsetUs;
        }
        return System.currentTimeMillis() * 1000L;
    }
    
    private static MediaFormat getTrackFormat(final int n, final Format format, final String s, final long n2) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return MediaFormat.createVideoFormat(format.id, s, format.bitrate, -1, n2, format.width, format.height, null);
            }
            case 1: {
                return MediaFormat.createAudioFormat(format.id, s, format.bitrate, -1, n2, format.audioChannels, format.audioSamplingRate, null, format.language);
            }
            case 2: {
                return MediaFormat.createTextFormat(format.id, s, format.bitrate, n2, format.language);
            }
        }
    }
    
    static boolean mimeTypeIsRawText(final String s) {
        return "text/vtt".equals(s) || "application/ttml+xml".equals(s);
    }
    
    static boolean mimeTypeIsWebm(final String s) {
        return s.startsWith("video/webm") || s.startsWith("audio/webm") || s.startsWith("application/webm");
    }
    
    private Chunk newInitializationChunk(RangedUri rangedUri, RangedUri attemptMerge, final Representation representation, final ChunkExtractorWrapper chunkExtractorWrapper, final DataSource dataSource, final int n, final int n2) {
        if (rangedUri != null) {
            attemptMerge = rangedUri.attemptMerge(attemptMerge);
            if (attemptMerge != null) {
                rangedUri = attemptMerge;
            }
        }
        else {
            rangedUri = attemptMerge;
        }
        return new InitializationChunk(dataSource, new DataSpec(rangedUri.getUri(), rangedUri.start, rangedUri.length, representation.getCacheKey()), n2, representation.format, chunkExtractorWrapper, n);
    }
    
    private void notifyAvailableRangeChanged(final TimeRange timeRange) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new DashChunkSource$1(this, timeRange));
        }
    }
    
    private void processManifest(final MediaPresentationDescription currentManifest) {
        final Period period = currentManifest.getPeriod(0);
        while (this.periodHolders.size() > 0 && ((DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0)).startTimeUs < period.startMs * 1000L) {
            this.periodHolders.remove(((DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0)).localIndex);
        }
        if (this.periodHolders.size() > currentManifest.getPeriodCount()) {
            return;
        }
        try {
            final int size = this.periodHolders.size();
            if (size > 0) {
                ((DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0)).updatePeriod(currentManifest, 0, this.enabledTrack);
                if (size > 1) {
                    final int n = size - 1;
                    ((DashChunkSource$PeriodHolder)this.periodHolders.valueAt(n)).updatePeriod(currentManifest, n, this.enabledTrack);
                }
            }
            for (int i = this.periodHolders.size(); i < currentManifest.getPeriodCount(); ++i) {
                this.periodHolders.put(this.nextPeriodHolderIndex, (Object)new DashChunkSource$PeriodHolder(this.nextPeriodHolderIndex, currentManifest, i, this.enabledTrack));
                ++this.nextPeriodHolderIndex;
            }
        }
        catch (BehindLiveWindowException fatalError) {
            this.fatalError = fatalError;
            return;
        }
        final TimeRange availableRange = this.getAvailableRange(this.getNowUnixTimeUs());
        if (this.availableRange == null || !this.availableRange.equals(availableRange)) {
            this.notifyAvailableRangeChanged(this.availableRange = availableRange);
        }
        this.currentManifest = currentManifest;
    }
    
    @Override
    public void adaptiveTrack(final MediaPresentationDescription mediaPresentationDescription, int i, final int n, final int[] array) {
        if (this.adaptiveFormatEvaluator == null) {
            Log.w("DashChunkSource", "Skipping adaptive track (missing format evaluator)");
            return;
        }
        final AdaptationSet set = mediaPresentationDescription.getPeriod(i).adaptationSets.get(n);
        int max = 0;
        int max2 = 0;
        Format format = null;
        Format[] array2;
        Format format2;
        for (array2 = new Format[array.length], i = 0; i < array2.length; ++i) {
            format2 = set.representations.get(array[i]).format;
            if (format == null || format2.height > max2) {
                format = format2;
            }
            max = Math.max(max, format2.width);
            max2 = Math.max(max2, format2.height);
            array2[i] = format2;
        }
        Arrays.sort(array2, new Format$DecreasingBandwidthComparator());
        long n2;
        if (this.live) {
            n2 = -1L;
        }
        else {
            n2 = mediaPresentationDescription.duration * 1000L;
        }
        final String mediaMimeType = getMediaMimeType(format);
        if (mediaMimeType == null) {
            Log.w("DashChunkSource", "Skipped adaptive track (unknown media mime type)");
            return;
        }
        final MediaFormat trackFormat = getTrackFormat(set.type, format, mediaMimeType, n2);
        if (trackFormat == null) {
            Log.w("DashChunkSource", "Skipped adaptive track (unknown media format)");
            return;
        }
        this.tracks.add(new DashChunkSource$ExposedTrack(trackFormat.copyAsAdaptive(null), n, array2, max, max2));
    }
    
    @Override
    public void continueBuffering(long minUpdatePeriod) {
        if (this.manifestFetcher != null && this.currentManifest.dynamic && this.fatalError == null) {
            final MediaPresentationDescription processedManifest = this.manifestFetcher.getManifest();
            if (processedManifest != null && processedManifest != this.processedManifest) {
                this.processManifest(processedManifest);
                this.processedManifest = processedManifest;
            }
            if ((minUpdatePeriod = this.currentManifest.minUpdatePeriod) == 0L) {
                minUpdatePeriod = 5000L;
            }
            if (android.os.SystemClock.elapsedRealtime() > minUpdatePeriod + this.manifestFetcher.getManifestLoadStartTimestamp()) {
                this.manifestFetcher.requestRefresh();
            }
        }
    }
    
    @Override
    public void disable(final List<? extends MediaChunk> list) {
        if (this.enabledTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.disable();
        }
        if (this.manifestFetcher != null) {
            this.manifestFetcher.disable();
        }
        this.periodHolders.clear();
        this.evaluation.format = null;
        this.availableRange = null;
        this.fatalError = null;
        this.enabledTrack = null;
    }
    
    @Override
    public void enable(final int n) {
        this.enabledTrack = this.tracks.get(n);
        if (this.enabledTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.enable();
        }
        if (this.manifestFetcher != null) {
            this.manifestFetcher.enable();
            this.processManifest(this.manifestFetcher.getManifest());
            return;
        }
        this.processManifest(this.currentManifest);
    }
    
    @Override
    public void fixedTrack(final MediaPresentationDescription mediaPresentationDescription, int type, final int n, final int n2) {
        final AdaptationSet set = mediaPresentationDescription.getPeriod(type).adaptationSets.get(n);
        final Format format = set.representations.get(n2).format;
        final String mediaMimeType = getMediaMimeType(format);
        if (mediaMimeType == null) {
            Log.w("DashChunkSource", "Skipped track " + format.id + " (unknown media mime type)");
            return;
        }
        type = set.type;
        long n3;
        if (mediaPresentationDescription.dynamic) {
            n3 = -1L;
        }
        else {
            n3 = mediaPresentationDescription.duration * 1000L;
        }
        final MediaFormat trackFormat = getTrackFormat(type, format, mediaMimeType, n3);
        if (trackFormat == null) {
            Log.w("DashChunkSource", "Skipped track " + format.id + " (unknown media format)");
            return;
        }
        this.tracks.add(new DashChunkSource$ExposedTrack(trackFormat, n, format));
    }
    
    @Override
    public final void getChunkOperation(final List<? extends MediaChunk> list, long n, final ChunkOperationHolder chunkOperationHolder) {
        if (this.fatalError != null) {
            chunkOperationHolder.chunk = null;
        }
        else {
            this.evaluation.queueSize = list.size();
            if (this.evaluation.format == null || !this.lastChunkWasInitialization) {
                if (this.enabledTrack.isAdaptive()) {
                    this.adaptiveFormatEvaluator.evaluate(list, n, this.enabledTrack.adaptiveFormats, this.evaluation);
                }
                else {
                    this.evaluation.format = this.enabledTrack.fixedFormat;
                    this.evaluation.trigger = 2;
                }
            }
            final Format format = this.evaluation.format;
            chunkOperationHolder.queueSize = this.evaluation.queueSize;
            if (format == null) {
                chunkOperationHolder.chunk = null;
                return;
            }
            if (chunkOperationHolder.queueSize != list.size() || chunkOperationHolder.chunk == null || !chunkOperationHolder.chunk.format.equals(format)) {
                chunkOperationHolder.chunk = null;
                this.availableRange.getCurrentBoundsUs(this.availableRangeValues);
                DashChunkSource$PeriodHolder periodHolder;
                int n3;
                if (list.isEmpty()) {
                    long n2 = n;
                    if (this.live) {
                        if (n != 0L) {
                            this.startAtLiveEdge = false;
                        }
                        if (this.startAtLiveEdge) {
                            n2 = Math.max(this.availableRangeValues[0], this.availableRangeValues[1] - this.liveEdgeLatencyUs);
                        }
                        else {
                            n2 = Math.max(Math.min(n, this.availableRangeValues[1] - 1L), this.availableRangeValues[0]);
                        }
                    }
                    periodHolder = this.findPeriodHolder(n2);
                    n3 = 1;
                    n = n2;
                }
                else {
                    if (this.startAtLiveEdge) {
                        this.startAtLiveEdge = false;
                    }
                    final MediaChunk mediaChunk = (MediaChunk)list.get(chunkOperationHolder.queueSize - 1);
                    final long endTimeUs = mediaChunk.endTimeUs;
                    if (this.live && endTimeUs < this.availableRangeValues[0]) {
                        this.fatalError = new BehindLiveWindowException();
                        return;
                    }
                    if (this.currentManifest.dynamic && endTimeUs >= this.availableRangeValues[1]) {
                        return;
                    }
                    final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(this.periodHolders.size() - 1);
                    if (mediaChunk.parentId == dashChunkSource$PeriodHolder.localIndex && dashChunkSource$PeriodHolder.representationHolders.get(mediaChunk.format.id).isBeyondLastSegment(mediaChunk.getNextChunkIndex())) {
                        if (!this.currentManifest.dynamic) {
                            chunkOperationHolder.endOfStream = true;
                        }
                        return;
                    }
                    else {
                        periodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.get(mediaChunk.parentId);
                        if (periodHolder == null) {
                            periodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.valueAt(0);
                            n3 = 1;
                        }
                        else if (!periodHolder.isIndexUnbounded() && periodHolder.representationHolders.get(mediaChunk.format.id).isBeyondLastSegment(mediaChunk.getNextChunkIndex())) {
                            periodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.get(mediaChunk.parentId + 1);
                            n3 = 1;
                        }
                        else {
                            n3 = 0;
                        }
                    }
                }
                final DashChunkSource$RepresentationHolder dashChunkSource$RepresentationHolder = periodHolder.representationHolders.get(format.id);
                final Representation representation = dashChunkSource$RepresentationHolder.representation;
                RangedUri initializationUri = null;
                RangedUri indexUri = null;
                final MediaFormat mediaFormat = dashChunkSource$RepresentationHolder.mediaFormat;
                if (mediaFormat == null) {
                    initializationUri = representation.getInitializationUri();
                }
                if (dashChunkSource$RepresentationHolder.segmentIndex == null) {
                    indexUri = representation.getIndexUri();
                }
                if (initializationUri != null || indexUri != null) {
                    final Chunk initializationChunk = this.newInitializationChunk(initializationUri, indexUri, representation, dashChunkSource$RepresentationHolder.extractorWrapper, this.dataSource, periodHolder.localIndex, this.evaluation.trigger);
                    this.lastChunkWasInitialization = true;
                    chunkOperationHolder.chunk = initializationChunk;
                    return;
                }
                int n4;
                if (list.isEmpty()) {
                    n4 = dashChunkSource$RepresentationHolder.getSegmentNum(n);
                }
                else if (n3 != 0) {
                    n4 = dashChunkSource$RepresentationHolder.getFirstAvailableSegmentNum();
                }
                else {
                    n4 = ((MediaChunk)list.get(chunkOperationHolder.queueSize - 1)).getNextChunkIndex();
                }
                final Chunk mediaChunk2 = this.newMediaChunk(periodHolder, dashChunkSource$RepresentationHolder, this.dataSource, mediaFormat, this.enabledTrack, n4, this.evaluation.trigger, mediaFormat != null);
                this.lastChunkWasInitialization = false;
                chunkOperationHolder.chunk = mediaChunk2;
            }
        }
    }
    
    @Override
    public final MediaFormat getFormat(final int n) {
        return this.tracks.get(n).trackFormat;
    }
    
    public long getSeekToTime(final long n) {
        long n2 = n;
        if (this.mInitializationChunkIndex != null) {
            n2 = n;
            if (this.mInitializationChunkIndex.isSeekable()) {
                long n3 = 0L;
                for (int i = 0; i < this.mInitializationChunkIndex.getChunkIndex(n); ++i) {
                    n3 += this.mInitializationChunkIndex.durationsUs[i];
                }
                n2 = n3 / 1000L;
            }
        }
        return n2;
    }
    
    @Override
    public int getTrackCount() {
        return this.tracks.size();
    }
    
    @Override
    public void maybeThrowError() {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        if (this.manifestFetcher != null) {
            this.manifestFetcher.maybeThrowError();
        }
    }
    
    protected Chunk newMediaChunk(final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder, final DashChunkSource$RepresentationHolder dashChunkSource$RepresentationHolder, final DataSource dataSource, final MediaFormat mediaFormat, final DashChunkSource$ExposedTrack dashChunkSource$ExposedTrack, final int n, final int n2, final boolean b) {
        final Representation representation = dashChunkSource$RepresentationHolder.representation;
        final Format format = representation.format;
        final long segmentStartTimeUs = dashChunkSource$RepresentationHolder.getSegmentStartTimeUs(n);
        final long segmentEndTimeUs = dashChunkSource$RepresentationHolder.getSegmentEndTimeUs(n);
        final RangedUri segmentUrl = dashChunkSource$RepresentationHolder.getSegmentUrl(n);
        final DataSpec dataSpec = new DataSpec(segmentUrl.getUri(), segmentUrl.start, segmentUrl.length, representation.getCacheKey());
        final long startTimeUs = dashChunkSource$PeriodHolder.startTimeUs;
        final long presentationTimeOffsetUs = representation.presentationTimeOffsetUs;
        if (mimeTypeIsRawText(format.mimeType)) {
            return new SingleSampleMediaChunk(dataSource, dataSpec, 1, format, segmentStartTimeUs, segmentEndTimeUs, n, dashChunkSource$ExposedTrack.trackFormat, null, dashChunkSource$PeriodHolder.localIndex);
        }
        return new ContainerMediaChunk(dataSource, dataSpec, n2, format, segmentStartTimeUs, segmentEndTimeUs, n, startTimeUs - presentationTimeOffsetUs, dashChunkSource$RepresentationHolder.extractorWrapper, mediaFormat, dashChunkSource$ExposedTrack.adaptiveMaxWidth, dashChunkSource$ExposedTrack.adaptiveMaxHeight, dashChunkSource$PeriodHolder.drmInitData, b, dashChunkSource$PeriodHolder.localIndex);
    }
    
    @Override
    public void onChunkLoadCompleted(final Chunk chunk) {
        if (chunk instanceof InitializationChunk) {
            final InitializationChunk initializationChunk = (InitializationChunk)chunk;
            if (initializationChunk.getSeekMap() instanceof ChunkIndex) {
                this.mInitializationChunkIndex = (ChunkIndex)initializationChunk.getSeekMap();
            }
            final String id = initializationChunk.format.id;
            final DashChunkSource$PeriodHolder dashChunkSource$PeriodHolder = (DashChunkSource$PeriodHolder)this.periodHolders.get(initializationChunk.parentId);
            if (dashChunkSource$PeriodHolder != null) {
                final DashChunkSource$RepresentationHolder dashChunkSource$RepresentationHolder = dashChunkSource$PeriodHolder.representationHolders.get(id);
                if (initializationChunk.hasFormat()) {
                    dashChunkSource$RepresentationHolder.mediaFormat = initializationChunk.getFormat();
                }
                if (dashChunkSource$RepresentationHolder.segmentIndex == null && initializationChunk.hasSeekMap()) {
                    dashChunkSource$RepresentationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex)initializationChunk.getSeekMap(), initializationChunk.dataSpec.uri.toString());
                }
                if (dashChunkSource$PeriodHolder.drmInitData == null && initializationChunk.hasDrmInitData()) {
                    dashChunkSource$PeriodHolder.drmInitData = initializationChunk.getDrmInitData();
                }
            }
        }
    }
    
    @Override
    public void onChunkLoadError(final Chunk chunk, final Exception ex) {
    }
    
    @Override
    public boolean prepare() {
        while (true) {
            if (!this.prepareCalled) {
                this.prepareCalled = true;
                try {
                    this.trackSelector.selectTracks(this.currentManifest, 0, this);
                    if (this.fatalError == null) {
                        return true;
                    }
                }
                catch (IOException fatalError) {
                    this.fatalError = fatalError;
                    continue;
                }
                return false;
            }
            continue;
        }
    }
}
