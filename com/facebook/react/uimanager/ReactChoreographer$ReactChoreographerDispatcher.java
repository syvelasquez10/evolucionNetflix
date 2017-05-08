// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import android.view.Choreographer;
import java.util.ArrayDeque;
import android.view.Choreographer$FrameCallback;

class ReactChoreographer$ReactChoreographerDispatcher implements Choreographer$FrameCallback
{
    final /* synthetic */ ReactChoreographer this$0;
    
    private ReactChoreographer$ReactChoreographerDispatcher(final ReactChoreographer this$0) {
        this.this$0 = this$0;
    }
    
    public void doFrame(final long n) {
        this.this$0.mHasPostedCallback = false;
        for (int i = 0; i < this.this$0.mCallbackQueues.length; ++i) {
            for (int size = this.this$0.mCallbackQueues[i].size(), j = 0; j < size; ++j) {
                ((Choreographer$FrameCallback)this.this$0.mCallbackQueues[i].removeFirst()).doFrame(n);
                this.this$0.mTotalCallbacks--;
            }
        }
        this.this$0.maybeRemoveFrameCallback();
    }
}
