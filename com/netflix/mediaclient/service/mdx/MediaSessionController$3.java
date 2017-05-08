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
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.media.VolumeProvider;

class MediaSessionController$3 extends VolumeProvider
{
    final /* synthetic */ MediaSessionController this$0;
    
    MediaSessionController$3(final MediaSessionController this$0, final int n, final int n2, final int n3) {
        this.this$0 = this$0;
        super(n, n2, n3);
    }
    
    public void onAdjustVolume(final int n) {
        if (Log.isLoggable()) {
            Log.i("nf_media_session_controller", "onAdjustVolume: " + n);
        }
        if (n == 1) {
            this.this$0.mVolume += 10;
        }
        else if (n == -1) {
            this.this$0.mVolume -= 10;
        }
        else {
            if (Log.isLoggable()) {
                Log.i("nf_media_session_controller", "onAdjustVolume strange direction, skipping: " + n);
            }
            return;
        }
        this.this$0.updateCurrentVolume(this.this$0.mVolume, true);
    }
    
    public void onSetVolumeTo(final int n) {
        if (Log.isLoggable()) {
            Log.i("nf_media_session_controller", "onSetVolumeTo: " + n);
        }
        this.this$0.updateCurrentVolume(n * 10, true);
    }
}
