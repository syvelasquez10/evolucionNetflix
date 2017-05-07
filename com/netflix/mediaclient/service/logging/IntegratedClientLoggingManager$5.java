// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.SystemClock;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebClientFactory;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import java.util.Iterator;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import com.netflix.mediaclient.service.logging.client.model.SessionEvent;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebCallback;
import com.netflix.mediaclient.service.logging.client.model.LoggingRequest;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.NetflixService;
import java.util.concurrent.atomic.AtomicLong;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.app.UserInputManager;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Random;
import java.util.Map;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebClient;
import com.netflix.mediaclient.android.app.ApplicationStateListener;

class IntegratedClientLoggingManager$5 implements Runnable
{
    final /* synthetic */ IntegratedClientLoggingManager this$0;
    final /* synthetic */ boolean val$saveIfFailed;
    
    IntegratedClientLoggingManager$5(final IntegratedClientLoggingManager this$0, final boolean val$saveIfFailed) {
        this.this$0 = this$0;
        this.val$saveIfFailed = val$saveIfFailed;
    }
    
    @Override
    public void run() {
        this.this$0.mEventQueue.flushEvents(this.val$saveIfFailed);
    }
}
