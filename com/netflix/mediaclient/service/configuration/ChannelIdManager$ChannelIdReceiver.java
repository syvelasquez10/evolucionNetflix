// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import android.content.IntentFilter;
import android.os.Handler;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class ChannelIdManager$ChannelIdReceiver extends BroadcastReceiver
{
    final /* synthetic */ ChannelIdManager this$0;
    
    public ChannelIdManager$ChannelIdReceiver(final ChannelIdManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.d(ChannelIdManager.TAG, "dropping null intent");
        }
        else {
            final String action = intent.getAction();
            Log.i(ChannelIdManager.TAG, "ChannelId receiver invoked and received Intent with Action %s", action);
            if (!"com.netflix.partner.activation.intent.action.CHANNEL_ID_RESPONSE".equals(action)) {
                Log.w(ChannelIdManager.TAG, "dropping intent! wrong action");
                return;
            }
            final String stringExtra = intent.getStringExtra("channelId");
            if (StringUtils.isNotEmpty(stringExtra)) {
                if (StringUtils.isNotEmpty(this.this$0.mChannelId)) {
                    Log.w(ChannelIdManager.TAG, "Ignoring channelId intent - already got");
                    return;
                }
                PreferenceUtils.putStringPref(this.this$0.mContext, "channelIdValue", stringExtra);
                this.this$0.mChannelId = stringExtra;
                Log.d(ChannelIdManager.TAG, "Got channelId : %s", this.this$0.mChannelId);
            }
        }
    }
}
