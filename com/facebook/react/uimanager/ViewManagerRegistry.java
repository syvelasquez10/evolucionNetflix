// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewManagerRegistry
{
    private final Map<String, ViewManager> mViewManagers;
    
    public ViewManagerRegistry(final List<ViewManager> list) {
        this.mViewManagers = new HashMap<String, ViewManager>();
        for (final ViewManager viewManager : list) {
            this.mViewManagers.put(viewManager.getName(), viewManager);
        }
    }
    
    public ViewManager get(final String s) {
        final ViewManager viewManager = this.mViewManagers.get(s);
        if (viewManager != null) {
            return viewManager;
        }
        throw new IllegalViewOperationException("No ViewManager defined for class " + s);
    }
}
