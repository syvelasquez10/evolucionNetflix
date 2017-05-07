// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import android.os.Bundle;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import android.content.Context;
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

class UserInputManager$2 implements Runnable
{
    final /* synthetic */ UserInputManager this$0;
    
    UserInputManager$2(final UserInputManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        final Iterator<ApplicationStateListener> iterator = this.this$0.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onUiStarted(this.this$0);
        }
    }
}
