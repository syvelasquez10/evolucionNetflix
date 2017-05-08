// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import android.text.TextUtils;
import android.graphics.SurfaceTexture;
import com.netflix.mediaclient.Log;
import android.view.Surface;
import android.media.MediaPlayer;
import android.view.TextureView;
import android.os.Handler;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;

class MediaPlayerWrapper$1 implements Runnable
{
    final /* synthetic */ MediaPlayerWrapper this$0;
    
    MediaPlayerWrapper$1(final MediaPlayerWrapper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.startPlayback();
    }
}
