// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;

class DeviceEventManagerModule$1 implements Runnable
{
    final /* synthetic */ DeviceEventManagerModule this$0;
    final /* synthetic */ DefaultHardwareBackBtnHandler val$backBtnHandler;
    
    DeviceEventManagerModule$1(final DeviceEventManagerModule this$0, final DefaultHardwareBackBtnHandler val$backBtnHandler) {
        this.this$0 = this$0;
        this.val$backBtnHandler = val$backBtnHandler;
    }
    
    @Override
    public void run() {
        UiThreadUtil.assertOnUiThread();
        this.val$backBtnHandler.invokeDefaultOnBackPressed();
    }
}
