// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.vailsys.whistleengine.WhistleEngineDelegate$ConnectivityState;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import android.os.Process;
import android.media.AudioManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import com.vailsys.whistleengine.WhistleEngineThresholds;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import com.netflix.mediaclient.util.FileUtils;
import com.vailsys.whistleengine.WhistleEngineConfig$TransportMode;
import com.vailsys.whistleengine.WhistleEngineConfig;
import com.netflix.mediaclient.service.user.UserLocaleRepository;
import android.app.Service;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.service.NetflixService;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
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

class WhistleVoipAgent$4 implements Runnable
{
    final /* synthetic */ WhistleVoipAgent this$0;
    final /* synthetic */ int val$line;
    
    WhistleVoipAgent$4(final WhistleVoipAgent this$0, final int val$line) {
        this.this$0 = this$0;
        this.val$line = val$line;
    }
    
    @Override
    public void run() {
        setUrgentAudioThreadPriority();
        if (this.this$0.mEngine == null) {
            Log.w("nf_voip", "Engine is null, what happend");
        }
        else {
            this.this$0.mEngine.hangup(this.val$line);
        }
        this.this$0.stopEngine();
    }
}
