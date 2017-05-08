// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ThemedReactContext;
import android.view.View;
import com.facebook.react.uimanager.ViewManager;

abstract class VirtualViewManager<C extends FlatShadowNode> extends ViewManager<View, C>
{
    @Override
    protected View createViewInstance(final ThemedReactContext themedReactContext) {
        throw new RuntimeException(this.getName() + " doesn't map to a View");
    }
    
    @Override
    public void updateExtraData(final View view, final Object o) {
    }
}
