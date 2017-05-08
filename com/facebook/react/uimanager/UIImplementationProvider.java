// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;

public class UIImplementationProvider
{
    public UIImplementation createUIImplementation(final ReactApplicationContext reactApplicationContext, final List<ViewManager> list, final EventDispatcher eventDispatcher) {
        return new UIImplementation(reactApplicationContext, list, eventDispatcher);
    }
}
