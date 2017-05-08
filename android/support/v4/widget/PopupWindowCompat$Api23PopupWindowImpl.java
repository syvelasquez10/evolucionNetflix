// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.PopupWindow;

class PopupWindowCompat$Api23PopupWindowImpl extends PopupWindowCompat$Api21PopupWindowImpl
{
    @Override
    public boolean getOverlapAnchor(final PopupWindow popupWindow) {
        return PopupWindowCompatApi23.getOverlapAnchor(popupWindow);
    }
    
    @Override
    public int getWindowLayoutType(final PopupWindow popupWindow) {
        return PopupWindowCompatApi23.getWindowLayoutType(popupWindow);
    }
    
    @Override
    public void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
        PopupWindowCompatApi23.setOverlapAnchor(popupWindow, b);
    }
    
    @Override
    public void setWindowLayoutType(final PopupWindow popupWindow, final int n) {
        PopupWindowCompatApi23.setWindowLayoutType(popupWindow, n);
    }
}
