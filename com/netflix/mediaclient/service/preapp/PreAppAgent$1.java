// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.ServiceAgent$PreAppAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.pservice.PService;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PreAppAgent$1 extends BroadcastReceiver
{
    final /* synthetic */ PreAppAgent this$0;
    
    PreAppAgent$1(final PreAppAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Log.isLoggable()) {
                Log.d("nf_preappagent", String.format("received intent action: %s", action));
            }
            if (!AndroidUtils.isWidgetInstalled(context) && !PService.isRemoteUiDevice()) {
                Log.d("nf_preappagent", "widget not installed - skip fetching data");
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_ALL_MEMBER_UPDATED".equals(action)) {
                this.this$0.mPreAppAgentDataHandler.update(PreAppAgentEventType.ALL_MEMBER_UPDATED);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_CW_UPDATED".equals(action)) {
                this.this$0.mPreAppAgentDataHandler.update(PreAppAgentEventType.CW_UPDATED);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_IQ_UPDATED".equals(action)) {
                this.this$0.mPreAppAgentDataHandler.update(PreAppAgentEventType.IQ_UPDATED);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_TO_NON_MEMBER_UPDATED".equals(action)) {
                this.this$0.mPreAppAgentDataHandler.update(PreAppAgentEventType.NON_MEMBER_UPDATED);
            }
        }
    }
}
