// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import java.util.Collection;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.view.Choreographer$FrameCallback;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactChoreographer;
import java.util.ArrayList;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;

class NativeAnimatedModule$1 extends GuardedChoreographerFrameCallback
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ NativeAnimatedNodesManager val$nodesManager;
    
    NativeAnimatedModule$1(final NativeAnimatedModule this$0, final ReactContext reactContext, final NativeAnimatedNodesManager val$nodesManager) {
        this.this$0 = this$0;
        this.val$nodesManager = val$nodesManager;
        super(reactContext);
    }
    
    @Override
    protected void doFrameGuarded(final long n) {
        synchronized (this.this$0.mOperationsCopyLock) {
            final ArrayList access$100 = this.this$0.mReadyOperations;
            this.this$0.mReadyOperations = null;
            // monitorexit(NativeAnimatedModule.access$000(this.this$0))
            if (access$100 != null) {
                for (int size = access$100.size(), i = 0; i < size; ++i) {
                    access$100.get(i).execute(this.val$nodesManager);
                }
            }
        }
        if (this.val$nodesManager.hasActiveAnimations()) {
            this.val$nodesManager.runUpdates(n);
        }
        Assertions.assertNotNull(this.this$0.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.NATIVE_ANIMATED_MODULE, (Choreographer$FrameCallback)this.this$0.mAnimatedFrameCallback);
    }
}
