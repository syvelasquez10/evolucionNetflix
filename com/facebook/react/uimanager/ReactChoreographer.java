// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import android.view.Choreographer;
import android.view.Choreographer$FrameCallback;
import java.util.ArrayDeque;

public class ReactChoreographer
{
    private static ReactChoreographer sInstance;
    private final ArrayDeque<Choreographer$FrameCallback>[] mCallbackQueues;
    private final Choreographer mChoreographer;
    private boolean mHasPostedCallback;
    private final ReactChoreographer$ReactChoreographerDispatcher mReactChoreographerDispatcher;
    private int mTotalCallbacks;
    
    private ReactChoreographer() {
        int i = 0;
        this.mTotalCallbacks = 0;
        this.mHasPostedCallback = false;
        this.mChoreographer = Choreographer.getInstance();
        this.mReactChoreographerDispatcher = new ReactChoreographer$ReactChoreographerDispatcher(this, null);
        this.mCallbackQueues = (ArrayDeque<Choreographer$FrameCallback>[])new ArrayDeque[ReactChoreographer$CallbackType.values().length];
        while (i < this.mCallbackQueues.length) {
            this.mCallbackQueues[i] = new ArrayDeque<Choreographer$FrameCallback>();
            ++i;
        }
    }
    
    public static ReactChoreographer getInstance() {
        UiThreadUtil.assertOnUiThread();
        if (ReactChoreographer.sInstance == null) {
            ReactChoreographer.sInstance = new ReactChoreographer();
        }
        return ReactChoreographer.sInstance;
    }
    
    private void maybeRemoveFrameCallback() {
        Assertions.assertCondition(this.mTotalCallbacks >= 0);
        if (this.mTotalCallbacks == 0 && this.mHasPostedCallback) {
            this.mChoreographer.removeFrameCallback((Choreographer$FrameCallback)this.mReactChoreographerDispatcher);
            this.mHasPostedCallback = false;
        }
    }
    
    public void postFrameCallback(final ReactChoreographer$CallbackType reactChoreographer$CallbackType, final Choreographer$FrameCallback choreographer$FrameCallback) {
        UiThreadUtil.assertOnUiThread();
        this.mCallbackQueues[reactChoreographer$CallbackType.getOrder()].addLast(choreographer$FrameCallback);
        ++this.mTotalCallbacks;
        Assertions.assertCondition(this.mTotalCallbacks > 0);
        if (!this.mHasPostedCallback) {
            this.mChoreographer.postFrameCallback((Choreographer$FrameCallback)this.mReactChoreographerDispatcher);
            this.mHasPostedCallback = true;
        }
    }
    
    public void removeFrameCallback(final ReactChoreographer$CallbackType reactChoreographer$CallbackType, final Choreographer$FrameCallback choreographer$FrameCallback) {
        UiThreadUtil.assertOnUiThread();
        if (this.mCallbackQueues[reactChoreographer$CallbackType.getOrder()].removeFirstOccurrence(choreographer$FrameCallback)) {
            --this.mTotalCallbacks;
            this.maybeRemoveFrameCallback();
            return;
        }
        FLog.e("React", "Tried to remove non-existent frame callback");
    }
}
