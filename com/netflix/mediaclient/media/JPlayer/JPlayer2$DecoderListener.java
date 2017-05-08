// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaFormat;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.nio.ByteBuffer;
import android.view.Surface;
import com.netflix.mediaclient.media.VideoResolutionRange;
import android.media.MediaCrypto;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;

public class JPlayer2$DecoderListener implements MediaDecoderBase$EventListener
{
    final /* synthetic */ JPlayer2 this$0;
    
    public JPlayer2$DecoderListener(final JPlayer2 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDecoderReady(final boolean b) {
        // monitorenter(this)
        Label_0017: {
            if (!b) {
                break Label_0017;
            }
            try {
                Log.d("NF_JPlayer2", "AUDIO init'd");
                return;
                Log.d("NF_JPlayer2", "VIDEO init'd");
                this.this$0.notifyReady();
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    @Override
    public void onDecoderStarted(final boolean b) {
        // monitorenter(this)
        Label_0017: {
            if (!b) {
                break Label_0017;
            }
            try {
                Log.d("NF_JPlayer2", "AUDIO ready");
                return;
                Log.d("NF_JPlayer2", "VIDEO ready");
                this.this$0.mVideoPipe.unpause();
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    @Override
    public void onEndOfStream(final boolean b) {
        this.this$0.notifyEndOfStream(b);
        if (b) {
            Log.d("NF_JPlayer2", "AUDIO END_OF_STREAM");
            return;
        }
        Log.d("NF_JPlayer2", "VIDEO END_OF_STREAM");
    }
    
    @Override
    public void onError(final boolean b, final int n, final String s) {
        synchronized (this) {
            if (!this.this$0.mErrorReported) {
                this.this$0.nativeNotifyError(n, s);
                this.this$0.mErrorReported = true;
            }
        }
    }
    
    @Override
    public void onFlushed(final boolean b) {
        // monitorenter(this)
        Label_0024: {
            if (!b) {
                break Label_0024;
            }
            try {
                Log.d("NF_JPlayer2", "AUDIO flushed");
                this.this$0.notifyReady();
                return;
                Log.d("NF_JPlayer2", "VIDEO flushed");
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    @Override
    public void onPasued(final boolean b) {
        // monitorenter(this)
        Label_0024: {
            if (!b) {
                break Label_0024;
            }
            try {
                Log.d("NF_JPlayer2", "AUDIO paused");
                this.this$0.notifyReady();
                return;
                Log.d("NF_JPlayer2", "VIDEO paused");
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    @Override
    public void onSampleRendered(final boolean b, final long n, final long n2) {
        synchronized (this) {
            this.this$0.updatePosition(b, n2);
            if (b) {}
        }
    }
}
