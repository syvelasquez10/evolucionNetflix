// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import java.util.Collection;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import android.view.Choreographer$FrameCallback;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ReactChoreographer;
import java.util.ArrayList;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.Arguments;

class NativeAnimatedModule$3 implements AnimatedNodeValueListener
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;
    
    NativeAnimatedModule$3(final NativeAnimatedModule this$0, final int val$tag) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
    }
    
    @Override
    public void onValueUpdate(final double n) {
        final WritableMap map = Arguments.createMap();
        map.putInt("tag", this.val$tag);
        map.putDouble("value", n);
        this.this$0.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("onAnimatedValueUpdate", map);
    }
}
