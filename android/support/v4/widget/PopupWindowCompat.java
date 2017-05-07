// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;
import android.os.Build$VERSION;

public class PopupWindowCompat
{
    static final PopupWindowCompat$PopupWindowImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = new PopupWindowCompat$KitKatPopupWindowImpl();
            return;
        }
        IMPL = new PopupWindowCompat$BasePopupWindowImpl();
    }
    
    public static void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        PopupWindowCompat.IMPL.showAsDropDown(popupWindow, view, n, n2, n3);
    }
}
