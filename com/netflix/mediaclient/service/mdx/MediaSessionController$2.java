// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import android.media.MediaMetadata;
import android.os.Handler;
import android.media.MediaMetadata$Builder;
import android.graphics.Bitmap;
import android.media.session.MediaSession$Token;
import android.media.session.PlaybackState$Builder;
import android.content.IntentFilter;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.app.PendingIntent$CanceledException;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.media.session.MediaSession$Callback;

class MediaSessionController$2 extends MediaSession$Callback
{
    final /* synthetic */ MediaSessionController this$0;
    
    MediaSessionController$2(final MediaSessionController this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMediaButtonEvent(final Intent intent) {
        Log.d("nf_media_session_controller", intent);
        final KeyEvent keyEvent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
    Label_0056:
        while (true) {
            if (keyEvent.getAction() != 1) {
                break Label_0056;
            }
            if (keyEvent.getKeyCode() != 126) {
                if (keyEvent.getKeyCode() == 127) {
                    Label_0183: {
                        try {
                            if (this.this$0.mIsPostPlay) {
                                this.this$0.mAgent.getPlayNextIntent().send();
                                break Label_0056;
                            }
                            break Label_0183;
                        }
                        catch (PendingIntent$CanceledException ex) {
                            if (Log.isLoggable()) {
                                Log.e("nf_media_session_controller", "Got exception: " + ex);
                                break Label_0056;
                            }
                            break Label_0056;
                        }
                        break Label_0056;
                    }
                    this.this$0.mAgent.getPauseIntent().send();
                    break Label_0056;
                }
                ErrorLoggingManager.logHandledException(new Throwable("SPY-7597 - MediaSession::onMediaButtonEvent() got weird code: " + keyEvent.getKeyCode()));
                break Label_0056;
            }
            while (true) {
                try {
                    if (this.this$0.mIsPostPlay) {
                        this.this$0.mAgent.getPlayNextIntent().send();
                    }
                    else {
                        this.this$0.mAgent.getResumeIntent().send();
                    }
                    return super.onMediaButtonEvent(intent);
                }
                catch (PendingIntent$CanceledException ex2) {
                    if (Log.isLoggable()) {
                        Log.e("nf_media_session_controller", "Got exception: " + ex2);
                        return super.onMediaButtonEvent(intent);
                    }
                    return super.onMediaButtonEvent(intent);
                }
                continue Label_0056;
            }
            break;
        }
    }
}
