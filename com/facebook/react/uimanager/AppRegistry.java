// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.JavaScriptModule;

public interface AppRegistry extends JavaScriptModule
{
    void runApplication(final String p0, final WritableMap p1);
    
    void startHeadlessTask(final int p0, final String p1, final WritableMap p2);
    
    void unmountApplicationComponentAtRootTag(final int p0);
}
