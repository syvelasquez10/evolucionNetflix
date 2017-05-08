// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

class ViewGroupUtils$ViewGroupUtilsImplBase implements ViewGroupUtils$ViewGroupUtilsImpl
{
    @Override
    public void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        viewGroup.offsetDescendantRectToMyCoords(view, rect);
        rect.offset(view.getScrollX(), view.getScrollY());
    }
}
