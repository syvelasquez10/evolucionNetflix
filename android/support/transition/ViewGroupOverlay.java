// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(14)
class ViewGroupOverlay extends ViewOverlay
{
    ViewGroupOverlay(final Context context, final ViewGroup viewGroup, final View view) {
        super(context, viewGroup, view);
    }
    
    public static ViewGroupOverlay createFrom(final ViewGroup viewGroup) {
        return (ViewGroupOverlay)ViewOverlay.createFrom((View)viewGroup);
    }
    
    public void add(final View view) {
        this.mOverlayViewGroup.add(view);
    }
    
    public void remove(final View view) {
        this.mOverlayViewGroup.remove(view);
    }
}
