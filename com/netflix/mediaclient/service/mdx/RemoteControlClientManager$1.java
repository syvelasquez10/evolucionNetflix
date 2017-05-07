// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.RemoteControlClient$MetadataEditor;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.media.RemoteControlClient;
import android.content.ComponentName;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class RemoteControlClientManager$1 extends BroadcastReceiver
{
    private final PostPlayMediaButtonHandler postPlayMediaButtonHandler;
    private final StandardMediaButtonHandler standardMediaButtonHandler;
    final /* synthetic */ RemoteControlClientManager this$0;
    
    RemoteControlClientManager$1(final RemoteControlClientManager this$0) {
        this.this$0 = this$0;
        this.standardMediaButtonHandler = new StandardMediaButtonHandler();
        this.postPlayMediaButtonHandler = new PostPlayMediaButtonHandler();
    }
    
    public void onReceive(final Context context, final Intent intent) {
        int int1 = -1;
        if (!intent.getAction().equals("com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy")) {
            Log.w("RemoteControlClientManager", "Received broadcast event but not for Media Button proxy action!");
        }
        else {
            final KeyEvent keyEvent = (KeyEvent)intent.getExtras().get("android.intent.extra.KEY_EVENT");
            if (keyEvent.getAction() == 0) {
                if (Log.isLoggable("RemoteControlClientManager", 2)) {
                    Log.v("RemoteControlClientManager", "received ACTION_MEDIA_BUTTON, key down event, keyCode: " + keyEvent.getKeyCode());
                }
                if (this.this$0.mIsPostPlay) {
                    while (true) {
                        Label_0184: {
                            if (!(this.this$0.mEpisodeDetails instanceof EpisodeDetails)) {
                                break Label_0184;
                            }
                            final EpisodeDetails episodeDetails = (EpisodeDetails)this.this$0.mEpisodeDetails;
                            if (episodeDetails.getPlayable() == null) {
                                break Label_0184;
                            }
                            final int int2 = Integer.parseInt(episodeDetails.getPlayable().getParentId());
                            int1 = Integer.parseInt(episodeDetails.getId());
                            this.postPlayMediaButtonHandler.handleButtonDown(context, keyEvent, int2, int1, this.this$0.mTargetUUID);
                            return;
                        }
                        final int int2 = -1;
                        continue;
                    }
                }
                this.standardMediaButtonHandler.handleButtonDown(context, keyEvent);
            }
        }
    }
}
