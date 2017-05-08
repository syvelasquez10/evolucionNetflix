// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.os.Handler;
import android.content.Context;

public class ChannelIdManager
{
    public static final String INTENT_CHANNELID_INQUIRY = "com.netflix.partner.activation.intent.action.CHANNEL_ID_REQUEST";
    public static final String INTENT_CHANNELID_RESPONSE = "com.netflix.partner.activation.intent.action.CHANNEL_ID_RESPONSE";
    public static final String INTENT_CHANNEL_ID_EXTRA_VALUE = "channelId";
    private static int MAX_APP_LAUNCH_TRIES = 0;
    private static int MAX_PER_APP_LAUNCH_TRIES = 0;
    private static final String PERMISSION_CHANNEL_INQUIRY = "com.netflix.partner.activation.permission.CHANNEL_ID";
    private static final String TAG;
    private int mAppLaunchCheckCount;
    private String mChannelId;
    private ChannelIdManager$ChannelIdReceiver mChannelIdReceiver;
    private Context mContext;
    private int mCurrentCheckCount;
    private Handler mHandler;
    
    static {
        TAG = ChannelIdManager.class.getSimpleName();
        ChannelIdManager.MAX_PER_APP_LAUNCH_TRIES = 2;
        ChannelIdManager.MAX_APP_LAUNCH_TRIES = 2;
    }
    
    public ChannelIdManager(final Context mContext, final Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.mChannelId = PreferenceUtils.getStringPref(this.mContext, "channelIdValue", null);
        this.mAppLaunchCheckCount = PreferenceUtils.getIntPref(this.mContext, "channelIdAppLaunches", 0);
        ++this.mAppLaunchCheckCount;
        PreferenceUtils.putIntPref(this.mContext, "channelIdAppLaunches", this.mAppLaunchCheckCount);
        if (this.shouldRequestChannelId()) {
            Log.d(ChannelIdManager.TAG, "need to request channelId");
            this.registerChannelIdReceiver();
            ++this.mCurrentCheckCount;
            this.requestActivationApkForChannelId();
        }
    }
    
    private boolean haveTriedForManyAppLaunches() {
        return this.mAppLaunchCheckCount > ChannelIdManager.MAX_APP_LAUNCH_TRIES;
    }
    
    private boolean haveTriedForManyTimes() {
        return this.mCurrentCheckCount > ChannelIdManager.MAX_PER_APP_LAUNCH_TRIES;
    }
    
    private void registerChannelIdReceiver() {
        this.mChannelIdReceiver = new ChannelIdManager$ChannelIdReceiver(this);
        this.mContext.registerReceiver((BroadcastReceiver)this.mChannelIdReceiver, new IntentFilter("com.netflix.partner.activation.intent.action.CHANNEL_ID_RESPONSE"), "com.netflix.partner.activation.permission.CHANNEL_ID", this.mHandler);
    }
    
    private void requestActivationApkForChannelId() {
        Log.d(ChannelIdManager.TAG, "inquiring for channelId appLaunchCount: %d(%d), currentCheckCount: %d(%d)", this.mAppLaunchCheckCount, ChannelIdManager.MAX_APP_LAUNCH_TRIES, this.mCurrentCheckCount, ChannelIdManager.MAX_PER_APP_LAUNCH_TRIES);
        final Intent intent = new Intent("com.netflix.partner.activation.intent.action.CHANNEL_ID_REQUEST");
        intent.addFlags(32);
        this.mContext.sendBroadcast(intent, "com.netflix.partner.activation.permission.CHANNEL_ID");
    }
    
    private void requestChannelIdIfNeeded() {
        if (this.shouldRequestChannelId()) {
            ++this.mCurrentCheckCount;
            this.requestActivationApkForChannelId();
        }
    }
    
    private boolean shouldRequestChannelId() {
        return !StringUtils.isNotEmpty(this.mChannelId) && !this.haveTriedForManyAppLaunches() && !this.haveTriedForManyTimes();
    }
    
    private void unregisterChannelIdReceiver() {
        if (this.mChannelIdReceiver != null) {
            this.mContext.unregisterReceiver((BroadcastReceiver)this.mChannelIdReceiver);
        }
    }
    
    public void destroy() {
        this.unregisterChannelIdReceiver();
    }
    
    public String requestChannelId() {
        Log.d(ChannelIdManager.TAG, "requestChannelId %s", this.mChannelId);
        this.requestChannelIdIfNeeded();
        return this.mChannelId;
    }
}
