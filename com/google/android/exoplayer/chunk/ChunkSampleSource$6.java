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

class ChunkSampleSource$6 implements Runnable
{
    final /* synthetic */ ChunkSampleSource this$0;
    final /* synthetic */ Format val$format;
    final /* synthetic */ long val$positionUs;
    final /* synthetic */ int val$trigger;
    
    ChunkSampleSource$6(final ChunkSampleSource this$0, final Format val$format, final int val$trigger, final long val$positionUs) {
        this.this$0 = this$0;
        this.val$format = val$format;
        this.val$trigger = val$trigger;
        this.val$positionUs = val$positionUs;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onDownstreamFormatChanged(this.this$0.eventSourceId, this.val$format, this.val$trigger, this.this$0.usToMs(this.val$positionUs));
    }
}
