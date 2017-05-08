// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.ViewParent;
import com.facebook.infer.annotation.Assertions;
import android.view.View;

public class RootViewUtil
{
    public static RootView getRootView(View view) {
        while (!(view instanceof RootView)) {
            final ViewParent parent = view.getParent();
            if (parent == null) {
                return null;
            }
            Assertions.assertCondition(parent instanceof View);
            view = (View)parent;
        }
        return (RootView)view;
    }
}
