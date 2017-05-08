// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

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
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.chunk.ChunkSource;
import com.google.android.exoplayer.TimeRange;

class DashChunkSource$1 implements Runnable
{
    final /* synthetic */ DashChunkSource this$0;
    final /* synthetic */ TimeRange val$seekRange;
    
    DashChunkSource$1(final DashChunkSource this$0, final TimeRange val$seekRange) {
        this.this$0 = this$0;
        this.val$seekRange = val$seekRange;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onAvailableRangeChanged(this.this$0.eventSourceId, this.val$seekRange);
    }
}
