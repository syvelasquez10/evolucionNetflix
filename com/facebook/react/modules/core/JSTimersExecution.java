// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.JavaScriptModule;

public interface JSTimersExecution extends JavaScriptModule
{
    void callIdleCallbacks(final double p0);
    
    void callTimers(final WritableArray p0);
    
    void emitTimeDriftWarning(final String p0);
}
