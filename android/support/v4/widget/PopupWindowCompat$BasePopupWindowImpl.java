// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;

class PopupWindowCompat$BasePopupWindowImpl implements PopupWindowCompat$PopupWindowImpl
{
    @Override
    public void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
    }
    
    @Override
    public void setWindowLayoutType(final PopupWindow popupWindow, final int n) {
    }
    
    @Override
    public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        popupWindow.showAsDropDown(view, n, n2);
    }
}
