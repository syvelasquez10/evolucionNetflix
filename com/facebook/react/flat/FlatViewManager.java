// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

abstract class FlatViewManager extends ViewGroupManager<FlatViewGroup>
{
    @Override
    protected FlatViewGroup createViewInstance(final ThemedReactContext themedReactContext) {
        return new FlatViewGroup((Context)themedReactContext);
    }
    
    @Override
    public void removeAllViews(final FlatViewGroup flatViewGroup) {
        flatViewGroup.removeAllViewsInLayout();
    }
    
    public void setBackgroundColor(final FlatViewGroup flatViewGroup, final int n) {
    }
}
