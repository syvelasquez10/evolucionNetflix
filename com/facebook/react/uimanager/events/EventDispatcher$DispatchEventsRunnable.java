// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import java.util.Iterator;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayList;
import java.util.Map;
import android.util.LongSparseArray;
import com.facebook.react.bridge.LifecycleEventListener;
import java.util.Comparator;
import java.util.Arrays;
import com.facebook.infer.annotation.Assertions;
import com.facebook.systrace.Systrace;

class EventDispatcher$DispatchEventsRunnable implements Runnable
{
    final /* synthetic */ EventDispatcher this$0;
    
    private EventDispatcher$DispatchEventsRunnable(final EventDispatcher this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        while (true) {
            final boolean b = false;
            Systrace.beginSection(0L, "DispatchEventsRunnable");
            while (true) {
                int n = 0;
                Label_0197: {
                    try {
                        Systrace.endAsyncFlow(0L, "ScheduleDispatchFrameCallback", this.this$0.mHasDispatchScheduledCount);
                        this.this$0.mHasDispatchScheduled = false;
                        this.this$0.mHasDispatchScheduledCount++;
                        Assertions.assertNotNull(this.this$0.mRCTEventEmitter);
                        final Object access$1000 = this.this$0.mEventsToDispatchLock;
                        // monitorenter(access$1000)
                        n = (b ? 1 : 0);
                        try {
                            if (this.this$0.mEventsToDispatchSize > 1) {
                                Arrays.sort(this.this$0.mEventsToDispatch, 0, this.this$0.mEventsToDispatchSize, EventDispatcher.EVENT_COMPARATOR);
                                n = (b ? 1 : 0);
                            }
                            if (n >= this.this$0.mEventsToDispatchSize) {
                                break;
                            }
                            final Event event = this.this$0.mEventsToDispatch[n];
                            if (event == null) {
                                break Label_0197;
                            }
                            Systrace.endAsyncFlow(0L, event.getEventName(), event.getUniqueID());
                            event.dispatch(this.this$0.mRCTEventEmitter);
                            event.dispose();
                            break Label_0197;
                        }
                        finally {
                        }
                        // monitorexit(access$1000)
                    }
                    finally {
                        Systrace.endSection(0L);
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
        this.this$0.clearEventsToDispatch();
        this.this$0.mEventCookieToLastEventIdx.clear();
        // monitorexit(access$1000)
        Systrace.endSection(0L);
    }
}
