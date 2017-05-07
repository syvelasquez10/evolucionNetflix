// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;
import android.view.View;

class ViewCompat$EclairMr1ViewCompatImpl extends ViewCompat$BaseViewCompatImpl
{
    @Override
    public boolean isOpaque(final View view) {
        return ViewCompatEclairMr1.isOpaque(view);
    }
    
    @Override
    public void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewCompatEclairMr1.setChildrenDrawingOrderEnabled(viewGroup, b);
    }
}
