// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.Arrays;
import java.nio.ByteBuffer;
import android.media.MediaFormat;
import android.view.SurfaceHolder;
import android.view.Surface;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.util.Log;

public class JPlayer$VideoEventListener implements MediaDecoderPipe$EventListener
{
    final /* synthetic */ JPlayer this$0;
    
    public JPlayer$VideoEventListener(final JPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDecoderStarted() {
        if (this.this$0.mAudioPipe != null && this.this$0.mAudioPipe.isPauseded()) {
            this.this$0.mAudioPipe.unpause();
        }
    }
    
    @Override
    public void onStartRender() {
        if (this.this$0.mVideoPipe != this.this$0.mVideoPipe1) {
            Log.d("NF_JPlayer", "mVideoPipe2 is current");
            if (this.this$0.mJplayerListener != null) {
                this.this$0.mJplayerListener.onSurface2Visibility(true);
            }
            if (this.this$0.mVideoPipe1 != null && !this.this$0.mVideoPipe1.isStopped()) {
                this.this$0.mVideoPipe1.stop();
            }
            this.this$0.mVideoPipe1 = null;
            return;
        }
        Log.d("NF_JPlayer", "mVideoPipe1 is current");
        if (this.this$0.mJplayerListener != null) {
            this.this$0.mJplayerListener.onSurface2Visibility(false);
        }
        if (this.this$0.mVideoPipe2 != null && !this.this$0.mVideoPipe2.isStopped()) {
            this.this$0.mVideoPipe2.stop();
        }
        this.this$0.mVideoPipe2 = null;
    }
    
    @Override
    public void onStop() {
    }
}
