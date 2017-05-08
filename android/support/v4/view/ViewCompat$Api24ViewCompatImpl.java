// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$Api24ViewCompatImpl extends ViewCompat$MarshmallowViewCompatImpl
{
    @Override
    public void setPointerIcon(final View view, final PointerIconCompat pointerIconCompat) {
        ViewCompatApi24.setPointerIcon(view, pointerIconCompat.getPointerIcon());
    }
}
