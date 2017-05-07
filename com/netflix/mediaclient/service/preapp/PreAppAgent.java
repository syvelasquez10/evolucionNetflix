// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.ServiceAgent$PreAppAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

public class PreAppAgent extends ServiceAgent implements ServiceAgent$PreAppAgentInterface
{
    public static final String PREAPP_AGENT_TO_ALL_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_UPDATED";
    public static final String PREAPP_AGENT_TO_CW_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED";
    public static final String PREAPP_AGENT_TO_IQ_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED";
    protected static final String TAG = "nf_preappagent";
    private final BroadcastReceiver mDataUpdateIntentReceiver;
    private PreAppAgentDataHandler mPreAppAgentDataHandler;
    public final BroadcastReceiver mUserAgentIntentReceiver;
    
    public PreAppAgent() {
        this.mDataUpdateIntentReceiver = new PreAppAgent$1(this);
        this.mUserAgentIntentReceiver = new PreAppAgent$2(this);
    }
    
    private void handleProfileDeactive() {
    }
    
    public static void informCwUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED");
    }
    
    public static void informIqUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED");
    }
    
    public static void informPrefetched(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_UPDATED");
    }
    
    private void registerDataUpdateReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_UPDATED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED");
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mDataUpdateIntentReceiver, intentFilter);
    }
    
    private void registerUserAgentIntentReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mUserAgentIntentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    private static void sendLocalBroadcast(final Context context, final String s) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(s));
        if (Log.isLoggable("nf_preappagent", 3)) {
            Log.v("nf_preappagent", String.format("Intent %s sent", s));
        }
    }
    
    private void unregisterDataUpdateReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mDataUpdateIntentReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_preappagent", "unregisterDataUpdateReceiver " + ex);
        }
    }
    
    private void unregisterUserAgentIntentReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mUserAgentIntentReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_preappagent", "unregisterUserAgentIntentReceiver " + ex);
        }
    }
    
    @Override
    public void destroy() {
        this.unregisterDataUpdateReceiver();
        this.unregisterUserAgentIntentReceiver();
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        this.mPreAppAgentDataHandler = new PreAppAgentDataHandler(this.getContext(), this);
        this.registerDataUpdateReceiver();
        this.registerUserAgentIntentReceiver();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public boolean isWidgetInstalled() {
        return AndroidUtils.isWidgetInstalled(this.getContext());
    }
}
