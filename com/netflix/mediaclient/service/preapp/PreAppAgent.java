// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import android.content.ComponentName;
import com.netflix.mediaclient.ui.homescreen.NetflixAppWidgetProvider;
import android.appwidget.AppWidgetManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.ServiceAgent;

public class PreAppAgent extends ServiceAgent implements PreAppAgentInterface
{
    public static final String PREAPP_AGENT_TO_ALL_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_UPDATED";
    public static final String PREAPP_AGENT_TO_CW_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED";
    public static final String PREAPP_AGENT_TO_IQ_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED";
    protected static final String TAG = "nf_preappagent";
    private final BroadcastReceiver mDataUpdateIntentReceiver;
    private PreAppAgentDataHandler mDiskDataHandler;
    public final BroadcastReceiver mUserAgentIntentReceiver;
    
    public PreAppAgent() {
        this.mDataUpdateIntentReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent != null) {
                    final String action = intent.getAction();
                    if (Log.isLoggable("nf_preappagent", 3)) {
                        Log.d("nf_preappagent", String.format("received intent action: %s", action));
                    }
                    if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_UPDATED".equals(action)) {
                        PreAppAgent.this.mDiskDataHandler.update(UpdateEventType.ALL_UPDATED);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED".equals(action)) {
                        PreAppAgent.this.mDiskDataHandler.update(UpdateEventType.CW_UPDATED);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED".equals(action)) {
                        PreAppAgent.this.mDiskDataHandler.update(UpdateEventType.IQ_UPDATED);
                    }
                }
            }
        };
        this.mUserAgentIntentReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent != null && "com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE".equals(intent.getAction())) {
                    Log.i("nf_preappagent", "UserAgentIntentReceiver inovoked and received Intent with Action NOTIFY_USER_PROFILE_DEACTIVE");
                    PreAppAgent.this.handleProfileDeactive();
                }
            }
        };
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
        this.mDiskDataHandler = new PreAppAgentDataHandler(this.getContext(), this);
        this.registerDataUpdateReceiver();
        this.registerUserAgentIntentReceiver();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public boolean isWidgetInstalled() {
        final int[] appWidgetIds = AppWidgetManager.getInstance(this.getContext()).getAppWidgetIds(new ComponentName(this.getContext(), (Class)NetflixAppWidgetProvider.class));
        if (Log.isLoggable("nf_preappagent", 3)) {
            Log.d("nf_preappagent", String.format("found widget: %b, num widgets installed: %d", appWidgetIds.length > 0, appWidgetIds.length));
        }
        return appWidgetIds.length > 0;
    }
}
