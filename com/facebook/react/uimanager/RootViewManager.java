// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class RootViewManager extends ViewGroupManager<ViewGroup>
{
    public static final String REACT_CLASS = "RootView";
    
    @Override
    protected ViewGroup createViewInstance(final ThemedReactContext themedReactContext) {
        return (ViewGroup)new SizeMonitoringFrameLayout((Context)themedReactContext);
    }
    
    @Override
    public String getName() {
        return "RootView";
    }
}
