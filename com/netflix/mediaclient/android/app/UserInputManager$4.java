// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.ui.launch.LaunchActivity;
import com.netflix.mediaclient.ui.launch.UIWebViewActivity;
import com.netflix.mediaclient.ui.launch.NetflixComLaunchActivity;
import android.os.Bundle;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import android.app.Application$ActivityLifecycleCallbacks;
import java.util.Iterator;
import android.content.Intent;

class UserInputManager$4 implements Runnable
{
    final /* synthetic */ UserInputManager this$0;
    final /* synthetic */ Intent val$intent;
    
    UserInputManager$4(final UserInputManager this$0, final Intent val$intent) {
        this.this$0 = this$0;
        this.val$intent = val$intent;
    }
    
    @Override
    public void run() {
        final Iterator<ApplicationStateListener> iterator = this.this$0.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onForeground(this.this$0, this.val$intent);
        }
    }
}
