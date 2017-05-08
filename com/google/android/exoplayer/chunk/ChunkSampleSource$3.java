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

class ChunkSampleSource$3 implements Runnable
{
    final /* synthetic */ ChunkSampleSource this$0;
    final /* synthetic */ long val$bytesLoaded;
    
    ChunkSampleSource$3(final ChunkSampleSource this$0, final long val$bytesLoaded) {
        this.this$0 = this$0;
        this.val$bytesLoaded = val$bytesLoaded;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onLoadCanceled(this.this$0.eventSourceId, this.val$bytesLoaded);
    }
}
