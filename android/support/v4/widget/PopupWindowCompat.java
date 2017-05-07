// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;
import android.os.Build$VERSION;

public class PopupWindowCompat
{
    static final PopupWindowImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (PopupWindowImpl)new KitKatPopupWindowImpl();
            return;
        }
        IMPL = (PopupWindowImpl)new BasePopupWindowImpl();
    }
    
    public static void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        PopupWindowCompat.IMPL.showAsDropDown(popupWindow, view, n, n2, n3);
    }
    
    static class BasePopupWindowImpl implements PopupWindowImpl
    {
        @Override
        public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
            popupWindow.showAsDropDown(view, n, n2);
        }
    }
    
    static class KitKatPopupWindowImpl extends BasePopupWindowImpl
    {
        @Override
        public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
            PopupWindowCompatKitKat.showAsDropDown(popupWindow, view, n, n2, n3);
        }
    }
    
    interface PopupWindowImpl
    {
        void showAsDropDown(final PopupWindow p0, final View p1, final int p2, final int p3, final int p4);
    }
}
