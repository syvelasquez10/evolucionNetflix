// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import java.util.UUID;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.media.MediaDrm$CryptoSession;
import com.netflix.mediaclient.util.CryptoUtils;
import android.annotation.TargetApi;
import android.media.MediaDrmResetException;
import java.util.Arrays;
import com.netflix.msl.keyx.WidevineKeyRequestData;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.error.crypto.ErrorSource;
import com.netflix.mediaclient.service.error.crypto.CryptoErrorManager;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.msl.client.AndroidWidevineKeyRequestData;
import android.media.MediaDrm;
import java.util.concurrent.atomic.AtomicInteger;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class BaseCryptoManager$PlaybackWatcherReceiver extends BroadcastReceiver
{
    final /* synthetic */ BaseCryptoManager this$0;
    
    private BaseCryptoManager$PlaybackWatcherReceiver(final BaseCryptoManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.d(BaseCryptoManager.TAG, "received action request: " + intent.getAction());
        }
        if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED".equals(intent.getAction())) {
            this.this$0.mPlaybackInProgress.set(true);
        }
        else if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED".equals(intent.getAction())) {
            this.this$0.mPlaybackInProgress.set(false);
        }
    }
}
