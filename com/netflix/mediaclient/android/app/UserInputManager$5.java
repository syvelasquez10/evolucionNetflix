// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.ui.launch.LaunchActivity;
import com.netflix.mediaclient.ui.launch.UIWebViewActivity;
import com.netflix.mediaclient.ui.launch.NetflixComLaunchActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.service.logging.IntegratedClientLoggingManager;
import com.netflix.mediaclient.Log;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.ArrayList;
import android.os.SystemClock;
import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import android.content.Intent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import android.app.Application$ActivityLifecycleCallbacks;
import java.util.Iterator;

class UserInputManager$5 implements Runnable
{
    final /* synthetic */ UserInputManager this$0;
    
    UserInputManager$5(final UserInputManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        final Iterator<ApplicationStateListener> iterator = this.this$0.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onBackground(this.this$0);
        }
    }
}
