// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import android.media.MediaMetadata;
import android.os.Handler;
import android.media.MediaMetadata$Builder;
import android.graphics.Bitmap;
import android.media.session.MediaSession$Callback;
import android.media.session.MediaSession$Token;
import android.media.session.PlaybackState$Builder;
import android.content.Intent;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;

class MediaSessionController$1 implements Runnable
{
    final /* synthetic */ MediaSessionController this$0;
    
    MediaSessionController$1(final MediaSessionController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.setState(this.this$0.mPostponedState);
    }
}
