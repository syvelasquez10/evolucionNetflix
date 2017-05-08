// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.ViewParent;
import android.view.View;
import android.graphics.Rect;

public class ReactClippingViewGroupHelper
{
    private static final Rect sHelperRect;
    
    static {
        sHelperRect = new Rect();
    }
    
    public static void calculateClippingRect(final View view, final Rect rect) {
        final ViewParent parent = view.getParent();
        if (parent == null) {
            rect.setEmpty();
            return;
        }
        if (parent instanceof ReactClippingViewGroup) {
            final ReactClippingViewGroup reactClippingViewGroup = (ReactClippingViewGroup)parent;
            if (reactClippingViewGroup.getRemoveClippedSubviews()) {
                reactClippingViewGroup.getClippingRect(ReactClippingViewGroupHelper.sHelperRect);
                if (!ReactClippingViewGroupHelper.sHelperRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                    rect.setEmpty();
                    return;
                }
                ReactClippingViewGroupHelper.sHelperRect.offset(-view.getLeft(), -view.getTop());
                ReactClippingViewGroupHelper.sHelperRect.offset(view.getScrollX(), view.getScrollY());
                rect.set(ReactClippingViewGroupHelper.sHelperRect);
                return;
            }
        }
        view.getDrawingRect(rect);
    }
}
