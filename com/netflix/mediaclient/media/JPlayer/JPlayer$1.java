// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.Arrays;
import java.nio.ByteBuffer;
import android.view.SurfaceHolder;
import android.view.Surface;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.util.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaFormat;

class JPlayer$1 implements Runnable
{
    final /* synthetic */ JPlayer this$0;
    
    JPlayer$1(final JPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "video/avc");
    Label_0194_Outer:
        while (true) {
            while (true) {
                Label_0240: {
                    if (AndroidUtils.getAndroidVersion() <= 18) {
                        break Label_0240;
                    }
                    mediaFormat.setInteger("max-width", 720);
                    mediaFormat.setInteger("max-height", 480);
                    mediaFormat.setInteger("width", 720);
                    mediaFormat.setInteger("height", 480);
                    if (this.this$0.mVideoPipe1 != null && (this.this$0.mVideoPipe == this.this$0.mVideoPipe1 || !this.this$0.mVideoPipe1.isStopped())) {
                        break Label_0240;
                    }
                    Log.d("NF_JPlayer", "mVideoPipe1 is idle");
                    try {
                        this.this$0.mVideoPipe1 = new VideoDecoderPipe(new JPlayer$VideoDataSource(this.this$0), "video/avc", mediaFormat, this.this$0.mSurface1, "1", this.this$0.mJPlayerConfig);
                        this.this$0.mVideoPipe = this.this$0.mVideoPipe1;
                        this.this$0.mInitialVideoInit = true;
                        this.this$0.mVideoPipe1.setEventListener(this.this$0.mListener);
                        if (this.this$0.mVideoPipe.isDecoderCreated()) {
                            final MediaDecoderPipe$Clock clock = this.this$0.mAudioPipe.getClock();
                            this.this$0.mVideoPipe.start();
                            this.this$0.mVideoPipe.setReferenceClock(clock);
                            return;
                        }
                        break Label_0194_Outer;
                        mediaFormat.setInteger("max-input-size", 163840);
                        mediaFormat.setInteger("width", 720);
                        mediaFormat.setInteger("height", 480);
                        continue Label_0194_Outer;
                    }
                    catch (Exception ex) {
                        Log.e("NF_JPlayer", Log.getStackTraceString((Throwable)ex));
                        if (!this.this$0.mInitialVideoInit) {
                            this.this$0.nativeNotifyError(-2, Log.getStackTraceString((Throwable)ex));
                        }
                        this.this$0.mVideoPipe = null;
                        return;
                    }
                }
                if (this.this$0.mVideoPipe2 == null || (this.this$0.mVideoPipe != this.this$0.mVideoPipe2 && this.this$0.mVideoPipe2.isStopped())) {
                    Log.d("NF_JPlayer", "mVideoPipe2 is idle");
                    if (this.this$0.mSurface2 == null) {
                        if (this.this$0.mJplayerListener != null) {
                            this.this$0.mSurface2 = this.this$0.mJplayerListener.onGetTextureSurface();
                        }
                        if (this.this$0.mSurface2 == null) {
                            Log.d("NF_JPlayer", "TextureSurface is not ready, wait...");
                            try {
                                Thread.sleep(10L);
                                continue Label_0194_Outer;
                            }
                            catch (InterruptedException ex3) {
                                Log.d("NF_JPlayer", "configureVideoPipe interrupted");
                                continue;
                            }
                        }
                    }
                    try {
                        this.this$0.mVideoPipe2 = new VideoDecoderPipe(new JPlayer$VideoDataSource(this.this$0), "video/avc", mediaFormat, this.this$0.mSurface2, "2", this.this$0.mJPlayerConfig);
                        this.this$0.mVideoPipe = this.this$0.mVideoPipe2;
                        this.this$0.mVideoPipe2.setEventListener(this.this$0.mListener);
                        continue;
                    }
                    catch (Exception ex2) {
                        this.this$0.mVideoPipe = null;
                        Log.e("NF_JPlayer", Log.getStackTraceString((Throwable)ex2));
                        return;
                    }
                }
                try {
                    Thread.sleep(50L);
                    Log.d("NF_JPlayer", "video pipe is not ready, wait...");
                    continue Label_0194_Outer;
                }
                catch (InterruptedException ex4) {
                    Log.d("NF_JPlayer", "configureVideoPipe interrupted");
                    continue;
                }
                break;
            }
            break;
        }
        Log.e("NF_JPlayer", "VideoDecoder initialization failed, exiting...");
        this.this$0.mVideoPipe = null;
    }
}
