// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import android.content.Intent;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.UserInputManager;
import com.netflix.mediaclient.android.app.ApplicationStateListener;

class FalkorCacheMonitor$3 implements ApplicationStateListener
{
    final /* synthetic */ FalkorCacheMonitor this$0;
    
    FalkorCacheMonitor$3(final FalkorCacheMonitor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onBackground(final UserInputManager userInputManager) {
        this.this$0.logCacheAction("state", 0, null);
    }
    
    @Override
    public void onFocusGain(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onFocusLost(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onForeground(final UserInputManager userInputManager, final Intent intent) {
    }
    
    @Override
    public void onUiGone(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onUiStarted(final UserInputManager userInputManager) {
    }
}
