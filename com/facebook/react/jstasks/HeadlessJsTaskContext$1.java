// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.jstasks;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.infer.annotation.Assertions;
import java.util.concurrent.CopyOnWriteArraySet;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Handler;
import java.util.Set;
import com.facebook.react.bridge.ReactContext;
import java.util.WeakHashMap;
import java.util.Iterator;

class HeadlessJsTaskContext$1 implements Runnable
{
    final /* synthetic */ HeadlessJsTaskContext this$0;
    final /* synthetic */ int val$taskId;
    
    HeadlessJsTaskContext$1(final HeadlessJsTaskContext this$0, final int val$taskId) {
        this.this$0 = this$0;
        this.val$taskId = val$taskId;
    }
    
    @Override
    public void run() {
        final Iterator<HeadlessJsTaskEventListener> iterator = this.this$0.mHeadlessJsTaskEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onHeadlessJsTaskFinish(this.val$taskId);
        }
    }
}
