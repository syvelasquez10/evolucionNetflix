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

class BroadcastReceiverHelper
{
    private static final String TAG = "nf_offlineBroadcast";
    private final BroadcastReceiverHelper$BroadcastHelperListener mCommonBroadcastListener;
    private final Context mContext;
    private boolean mIsRegistered;
    private final BroadcastReceiver mPlayerBroadcastReceiver;
    private final BroadcastReceiver mUserAgentBroadcastReceiver;
    private final Handler mWorkHandler;
    
    public BroadcastReceiverHelper(final Context mContext, final BroadcastReceiverHelper$BroadcastHelperListener mCommonBroadcastListener, final Looper looper) {
        this.mPlayerBroadcastReceiver = new BroadcastReceiverHelper$1(this);
        this.mUserAgentBroadcastReceiver = new BroadcastReceiverHelper$3(this);
        this.mContext = mContext;
        this.mWorkHandler = new Handler(looper);
        this.mCommonBroadcastListener = mCommonBroadcastListener;
    }
    
    private void onPlayerIntentReceived(final Intent intent) {
        final String intentActionOrNull = IntentUtils.getIntentActionOrNull(intent);
        if (intentActionOrNull != null) {
            final IPlayer$PlaybackType fromValue = IPlayer$PlaybackType.fromValue(intent.getStringExtra("playbackType"));
            if (Log.isLoggable()) {
                Log.i("nf_offlineBroadcast", "onPlayerIntentReceived playbackType=" + fromValue + " intentAction=" + intentActionOrNull);
            }
            if (fromValue != null) {
                this.mWorkHandler.post((Runnable)new BroadcastReceiverHelper$2(this, intentActionOrNull, fromValue));
            }
        }
    }
    
    private void onUserAgentIntentReceived(final Intent intent) {
        final String intentActionOrNull = IntentUtils.getIntentActionOrNull(intent);
        if (intentActionOrNull == null) {
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_offlineBroadcast", "onUserAgentIntentReceived intentAction=" + intentActionOrNull);
        }
        this.mWorkHandler.post((Runnable)new BroadcastReceiverHelper$4(this, intentActionOrNull));
    }
    
    public void destroy() {
        if (this.mIsRegistered) {
            IntentUtils.unregisterSafelyBroadcastReceiver(this.mContext, this.mPlayerBroadcastReceiver);
            IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.mContext, this.mUserAgentBroadcastReceiver);
        }
        this.mIsRegistered = false;
        this.mWorkHandler.removeCallbacksAndMessages((Object)null);
    }
    
    public void init() {
        this.mIsRegistered = true;
        IntentUtils.registerSafelyBroadcastReceiver(this.mContext, this.mPlayerBroadcastReceiver, null, "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START", "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        IntentUtils.registerSafelyLocalBroadcastReceiver(this.mContext, this.mUserAgentBroadcastReceiver, null, "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE", "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE", "com.netflix.mediaclient.intent.action.ACCOUNT_DATA_FETCHED");
    }
}
