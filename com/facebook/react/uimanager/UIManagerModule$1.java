// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.animation.Animation;
import com.facebook.systrace.SystraceMessage;
import android.content.ComponentCallbacks;
import java.util.HashMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.ReactMarker;
import android.content.Context;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Map;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

class UIManagerModule$1 implements SizeMonitoringFrameLayout$OnSizeChangedListener
{
    final /* synthetic */ UIManagerModule this$0;
    final /* synthetic */ int val$tag;
    
    UIManagerModule$1(final UIManagerModule this$0, final int val$tag) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
    }
    
    @Override
    public void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        this.this$0.getReactApplicationContext().runOnNativeModulesQueueThread(new UIManagerModule$1$1(this, n, n2));
    }
}
