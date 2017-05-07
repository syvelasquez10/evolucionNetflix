// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.servicemgr.IVoip$CallState;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.vailsys.whistleengine.WhistleEngineDelegate$ConnectivityState;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import android.media.AudioManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import com.netflix.mediaclient.util.FileUtils;
import com.vailsys.whistleengine.WhistleEngineConfig$TransportMode;
import com.vailsys.whistleengine.WhistleEngineConfig;
import android.app.Service;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.NetflixService;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import java.util.List;
import com.vailsys.whistleengine.WhistleEngine;
import com.netflix.mediaclient.servicemgr.IVoip$ConnectivityState;
import android.content.ServiceConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import com.vailsys.whistleengine.WhistleEngineDelegate;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class WhistleVoipAgent$5 extends BroadcastReceiver
{
    final /* synthetic */ WhistleVoipAgent this$0;
    
    WhistleVoipAgent$5(final WhistleVoipAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if (!intent.hasCategory("com.netflix.mediaclient.intent.category.VOIP")) {
            Log.d("nf_voip", "Received intent with uknown category!", intent);
            return;
        }
        if (CallNotificationManager.isDelete(action)) {
            Log.d("nf_voip", "Intent to cancel call received");
            this.this$0.onCallDisconnected(this.this$0.mCurrentCall.line);
            this.this$0.terminate();
            return;
        }
        Log.d("nf_voip", "Uknown VOIP action!", intent);
    }
}
