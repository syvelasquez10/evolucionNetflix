// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(14)
class ViewOverlay
{
    protected ViewOverlay$OverlayViewGroup mOverlayViewGroup;
    
    ViewOverlay(final Context context, final ViewGroup viewGroup, final View view) {
        this.mOverlayViewGroup = new ViewOverlay$OverlayViewGroup(context, viewGroup, view, this);
    }
    
    public static ViewOverlay createFrom(final View view) {
        final ViewGroup contentView = getContentView(view);
        if (contentView != null) {
            for (int childCount = contentView.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = contentView.getChildAt(i);
                if (child instanceof ViewOverlay$OverlayViewGroup) {
                    return ((ViewOverlay$OverlayViewGroup)child).mViewOverlay;
                }
            }
            return new ViewGroupOverlay(contentView.getContext(), contentView, view);
        }
        return null;
    }
    
    static ViewGroup getContentView(View view) {
        while (view != null) {
            if (view.getId() == 16908290 && view instanceof ViewGroup) {
                return (ViewGroup)view;
            }
            if (!(view.getParent() instanceof ViewGroup)) {
                continue;
            }
            view = (View)view.getParent();
        }
        return null;
    }
    
    public void add(final Drawable drawable) {
        this.mOverlayViewGroup.add(drawable);
    }
    
    public void remove(final Drawable drawable) {
        this.mOverlayViewGroup.remove(drawable);
    }
}
