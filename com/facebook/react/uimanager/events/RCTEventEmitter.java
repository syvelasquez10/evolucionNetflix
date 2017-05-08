// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.JavaScriptModule;

public interface RCTEventEmitter extends JavaScriptModule
{
    void receiveEvent(final int p0, final String p1, final WritableMap p2);
    
    void receiveTouches(final String p0, final WritableArray p1, final WritableArray p2);
}
