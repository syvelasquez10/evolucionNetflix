// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.ServiceAgent$PreAppAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

public class PreAppAgent extends ServiceAgent implements ServiceAgent$PreAppAgentInterface
{
    public static final String PREAPP_AGENT_TO_ACCOUNT_DEACTIVE = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ACCOUNT_DEACTIVE";
    public static final String PREAPP_AGENT_TO_ALL_MEMBER_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_MEMBER_UPDATED";
    public static final String PREAPP_AGENT_TO_CW_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED";
    public static final String PREAPP_AGENT_TO_IQ_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED";
    public static final String PREAPP_AGENT_TO_NON_MEMBER_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_NON_MEMBER_UPDATED";
    private static final long PREAPP_PREFETCH_NOTIFY_DELAY_MS = 5000L;
    protected static final String TAG = "nf_preappagent";
    private final Runnable informPrefetchRunnable;
    private final BroadcastReceiver mDataUpdateIntentReceiver;
    private PreAppAgentDataHandler mPreAppAgentDataHandler;
    public final BroadcastReceiver mUserAgentIntentReceiver;
    
    public PreAppAgent() {
        this.mDataUpdateIntentReceiver = new PreAppAgent$1(this);
        this.mUserAgentIntentReceiver = new PreAppAgent$2(this);
        this.informPrefetchRunnable = new PreAppAgent$3(this);
    }
    
    private void handleAccountDeactive(final Context context) {
        this.mPreAppAgentDataHandler.clear(PreAppAgentEventType.ACCOUNT_DEACTIVATED);
    }
    
    public static void informCwUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED");
    }
    
    public static void informIqUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED");
    }
    
    public static void informMemberUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_MEMBER_UPDATED");
    }
    
    public static void informNonMemberVideosUpdated(final Context context) {
        sendLocalBroadcast(context, "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_NON_MEMBER_UPDATED");
    }
    
    private void registerDataUpdateReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_MEMBER_UPDATED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_NON_MEMBER_UPDATED");
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mDataUpdateIntentReceiver, intentFilter);
    }
    
    private void registerUserAgentIntentReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mUserAgentIntentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    private void removePrefetchRunnable() {
        try {
            this.getService().getHandler().removeCallbacks(this.informPrefetchRunnable);
        }
        catch (Exception ex) {
            Log.i("nf_preappagent", "fail removing informPrefetchRunnable " + ex);
        }
    }
    
    private static void sendLocalBroadcast(final Context context, final String s) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(s));
        if (Log.isLoggable()) {
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
        this.removePrefetchRunnable();
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
    
    public boolean handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_preappagent", "Intent is null");
            return false;
        }
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.d("nf_preappagent", "Received command " + action);
        }
        final ServiceAgent$BrowseAgentInterface browseAgent = this.getBrowseAgent();
        if (browseAgent == null) {
            Log.w("nf_preappagent", "browseAgent null?");
            return false;
        }
        if ("com.netflix.mediaclient.intent.action.REFRESH_NON_MEMBER_DATA".equals(action)) {
            browseAgent.fetchNonMemberVideos(12, false, null);
        }
        else {
            if (!"com.netflix.mediaclient.intent.action.REFRESH_DATA".equals(action)) {
                Log.e("nf_preappagent", "Unknown command!");
                return false;
            }
            if (this.getService().isUserLoggedIn()) {
                sendLocalBroadcast(this.getContext(), "com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_MEMBER_UPDATED");
            }
            else {
                browseAgent.fetchNonMemberVideos(12, false, null);
            }
        }
        return true;
    }
    
    @Override
    public void informPrefetched(final Context context) {
        this.getService().getHandler().removeCallbacks(this.informPrefetchRunnable);
        this.getService().getHandler().postDelayed(this.informPrefetchRunnable, 5000L);
    }
}
