// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build$VERSION;

class ViewGroupUtils
{
    private static final ViewGroupUtils$ViewGroupUtilsImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = new ViewGroupUtils$ViewGroupUtilsImplHoneycomb(null);
            return;
        }
        IMPL = new ViewGroupUtils$ViewGroupUtilsImplBase(null);
    }
    
    static void getDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        offsetDescendantRect(viewGroup, view, rect);
    }
    
    static void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        ViewGroupUtils.IMPL.offsetDescendantRect(viewGroup, view, rect);
    }
}
