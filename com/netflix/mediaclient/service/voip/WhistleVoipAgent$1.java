// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

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
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.NetflixService;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IVoip$ConnectivityState;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import com.vailsys.whistleengine.WhistleEngineDelegate;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.ServiceAgent;
import com.vailsys.whistleengine.WhistleEngine;
import com.vailsys.whistleengine.WhistleEngine$WhistleEngineBinder;
import com.netflix.mediaclient.Log;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

class WhistleVoipAgent$1 implements ServiceConnection
{
    final /* synthetic */ WhistleVoipAgent this$0;
    
    WhistleVoipAgent$1(final WhistleVoipAgent this$0) {
        this.this$0 = this$0;
    }
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "ServiceConnected with IBinder: " + binder);
        }
        try {
            this.this$0.mEngine = ((WhistleEngine$WhistleEngineBinder)binder).getService();
            this.this$0.mEngine.applicationInForeground();
            this.this$0.mReady.set(true);
            this.this$0.mServiceState = WhistleVoipAgent$ServiceState.STARTED;
            this.this$0.doDialWithEngineCheck();
        }
        catch (Exception ex) {
            Log.e("nf_voip", "Failed to start VOIP service", ex);
            this.this$0.mServiceState = WhistleVoipAgent$ServiceState.NOT_STARTED;
        }
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        Log.d("nf_voip", "onServiceDisconnected");
        this.this$0.mEngine = null;
        this.this$0.mReady.set(false);
        this.this$0.mServiceState = WhistleVoipAgent$ServiceState.STOPPED;
    }
}
