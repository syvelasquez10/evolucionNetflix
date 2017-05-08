// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.util.IntentUtils;
import android.content.Intent;
import android.os.Looper;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;

class BroadcastReceiverHelper$2 implements Runnable
{
    final /* synthetic */ BroadcastReceiverHelper this$0;
    final /* synthetic */ String val$intentAction;
    final /* synthetic */ IPlayer$PlaybackType val$playbackType;
    
    BroadcastReceiverHelper$2(final BroadcastReceiverHelper this$0, final String val$intentAction, final IPlayer$PlaybackType val$playbackType) {
        this.this$0 = this$0;
        this.val$intentAction = val$intentAction;
        this.val$playbackType = val$playbackType;
    }
    
    @Override
    public void run() {
        final String val$intentAction = this.val$intentAction;
        switch (val$intentAction) {
            default: {}
            case "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START": {
                switch (BroadcastReceiverHelper$5.$SwitchMap$com$netflix$mediaclient$servicemgr$IPlayer$PlaybackType[this.val$playbackType.ordinal()]) {
                    default: {
                        return;
                    }
                    case 1: {
                        Log.i("nf_offlineBroadcast", "onStreamingPlayStartReceived");
                        this.this$0.mCommonBroadcastListener.onStreamingPlayStartReceived();
                        return;
                    }
                    case 2: {
                        Log.i("nf_offlineBroadcast", "onOfflinePlayStartReceived");
                        return;
                    }
                }
                break;
            }
            case "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP": {
                switch (BroadcastReceiverHelper$5.$SwitchMap$com$netflix$mediaclient$servicemgr$IPlayer$PlaybackType[this.val$playbackType.ordinal()]) {
                    default: {
                        return;
                    }
                    case 1: {
                        Log.i("nf_offlineBroadcast", "onStreamingPlayStopReceived");
                        this.this$0.mCommonBroadcastListener.onStreamingPlayStopReceived();
                        return;
                    }
                    case 2: {
                        Log.i("nf_offlineBroadcast", "onOfflinePlayStopReceived");
                        return;
                    }
                }
                break;
            }
        }
    }
}
