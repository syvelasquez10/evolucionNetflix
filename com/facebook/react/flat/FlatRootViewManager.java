// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.ViewGroup;
import com.facebook.react.uimanager.RootViewManager;

class FlatRootViewManager extends RootViewManager
{
    @Override
    public void removeAllViews(final ViewGroup viewGroup) {
        viewGroup.removeAllViewsInLayout();
    }
}
