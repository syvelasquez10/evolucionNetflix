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
import com.google.android.exoplayer.upstream.Loader$Callback;
import com.google.android.exoplayer.SampleSource$SampleSourceReader;
import com.google.android.exoplayer.SampleSource;
import java.io.IOException;

class ChunkSampleSource$4 implements Runnable
{
    final /* synthetic */ ChunkSampleSource this$0;
    final /* synthetic */ IOException val$e;
    
    ChunkSampleSource$4(final ChunkSampleSource this$0, final IOException val$e) {
        this.this$0 = this$0;
        this.val$e = val$e;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onLoadError(this.this$0.eventSourceId, this.val$e);
    }
}
