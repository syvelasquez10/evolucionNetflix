// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

class PopupWindowCompat$BasePopupWindowImpl implements PopupWindowCompat$PopupWindowImpl
{
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;
    
    @Override
    public boolean getOverlapAnchor(final PopupWindow popupWindow) {
        return false;
    }
    
    @Override
    public int getWindowLayoutType(final PopupWindow popupWindow) {
        Label_0031: {
            if (PopupWindowCompat$BasePopupWindowImpl.sGetWindowLayoutTypeMethodAttempted) {
                break Label_0031;
            }
            while (true) {
                try {
                    (PopupWindowCompat$BasePopupWindowImpl.sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", (Class<?>[])new Class[0])).setAccessible(true);
                    PopupWindowCompat$BasePopupWindowImpl.sGetWindowLayoutTypeMethodAttempted = true;
                    if (PopupWindowCompat$BasePopupWindowImpl.sGetWindowLayoutTypeMethod != null) {
                        try {
                            return (int)PopupWindowCompat$BasePopupWindowImpl.sGetWindowLayoutTypeMethod.invoke(popupWindow, new Object[0]);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (Exception ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
    }
    
    @Override
    public void setWindowLayoutType(final PopupWindow ex, final int n) {
        Label_0037: {
            if (PopupWindowCompat$BasePopupWindowImpl.sSetWindowLayoutTypeMethodAttempted) {
                break Label_0037;
            }
            while (true) {
                try {
                    (PopupWindowCompat$BasePopupWindowImpl.sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE)).setAccessible(true);
                    PopupWindowCompat$BasePopupWindowImpl.sSetWindowLayoutTypeMethodAttempted = true;
                    if (PopupWindowCompat$BasePopupWindowImpl.sSetWindowLayoutTypeMethod == null) {
                        return;
                    }
                    try {
                        PopupWindowCompat$BasePopupWindowImpl.sSetWindowLayoutTypeMethod.invoke(ex, n);
                    }
                    catch (Exception ex) {}
                }
                catch (Exception ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        int n4 = n;
        if ((GravityCompat.getAbsoluteGravity(n3, ViewCompat.getLayoutDirection(view)) & 0x7) == 0x5) {
            n4 = n - (popupWindow.getWidth() - view.getWidth());
        }
        popupWindow.showAsDropDown(view, n4, n2);
    }
}
