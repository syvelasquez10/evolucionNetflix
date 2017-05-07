// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.graphics.SurfaceTexture;
import java.io.IOException;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.TextureView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.Surface;
import android.media.MediaPlayer;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class MediaPlayerWrapper$1 extends SimpleManagerCallback
{
    final /* synthetic */ MediaPlayerWrapper this$0;
    
    MediaPlayerWrapper$1(final MediaPlayerWrapper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        super.onResourceFetched(s, s2, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("MediaPlayerWrapper", "Failed to download video: " + s);
            }
            return;
        }
        this.this$0.localUrl = s2;
        this.this$0.prepareMediaPlayer();
    }
}
