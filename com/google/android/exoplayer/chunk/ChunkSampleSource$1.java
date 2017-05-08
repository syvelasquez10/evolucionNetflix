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

class ChunkSampleSource$1 implements Runnable
{
    final /* synthetic */ ChunkSampleSource this$0;
    final /* synthetic */ Format val$format;
    final /* synthetic */ long val$length;
    final /* synthetic */ long val$mediaEndTimeUs;
    final /* synthetic */ long val$mediaStartTimeUs;
    final /* synthetic */ int val$trigger;
    final /* synthetic */ int val$type;
    
    ChunkSampleSource$1(final ChunkSampleSource this$0, final long val$length, final int val$type, final int val$trigger, final Format val$format, final long val$mediaStartTimeUs, final long val$mediaEndTimeUs) {
        this.this$0 = this$0;
        this.val$length = val$length;
        this.val$type = val$type;
        this.val$trigger = val$trigger;
        this.val$format = val$format;
        this.val$mediaStartTimeUs = val$mediaStartTimeUs;
        this.val$mediaEndTimeUs = val$mediaEndTimeUs;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onLoadStarted(this.this$0.eventSourceId, this.val$length, this.val$type, this.val$trigger, this.val$format, this.this$0.usToMs(this.val$mediaStartTimeUs), this.this$0.usToMs(this.val$mediaEndTimeUs));
    }
}
