// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.UiThreadUtil;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.common.logging.FLog;
import android.view.MotionEvent;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.facebook.infer.annotation.Assertions;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Bundle;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;

class ReactRootView$1 implements Runnable
{
    final /* synthetic */ ReactRootView this$0;
    
    ReactRootView$1(final ReactRootView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.attachToReactInstanceManager();
    }
}
