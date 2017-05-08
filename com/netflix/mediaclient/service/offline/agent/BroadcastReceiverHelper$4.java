// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;
import com.netflix.mediaclient.util.IntentUtils;
import android.content.Intent;
import android.os.Looper;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;

class BroadcastReceiverHelper$4 implements Runnable
{
    final /* synthetic */ BroadcastReceiverHelper this$0;
    final /* synthetic */ String val$intentAction;
    
    BroadcastReceiverHelper$4(final BroadcastReceiverHelper this$0, final String val$intentAction) {
        this.this$0 = this$0;
        this.val$intentAction = val$intentAction;
    }
    
    @Override
    public void run() {
        final String val$intentAction = this.val$intentAction;
        switch (val$intentAction) {
            default: {}
            case "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE": {
                this.this$0.mCommonBroadcastListener.onUserAccountActive();
            }
            case "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE": {
                this.this$0.mCommonBroadcastListener.onUserAccountInActive();
            }
            case "com.netflix.mediaclient.intent.action.ACCOUNT_DATA_FETCHED": {
                this.this$0.mCommonBroadcastListener.onAccountDataFetched();
            }
        }
    }
}
