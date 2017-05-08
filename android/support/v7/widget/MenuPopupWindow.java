// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.transition.Transition;
import android.os.Build$VERSION;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener
{
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;
    
    static {
        try {
            MenuPopupWindow.sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        }
        catch (NoSuchMethodException ex) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }
    
    public MenuPopupWindow(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    @Override
    DropDownListView createDropDownListView(final Context context, final boolean b) {
        final MenuPopupWindow$MenuDropDownListView menuPopupWindow$MenuDropDownListView = new MenuPopupWindow$MenuDropDownListView(context, b);
        menuPopupWindow$MenuDropDownListView.setHoverListener(this);
        return menuPopupWindow$MenuDropDownListView;
    }
    
    @Override
    public void onItemHoverEnter(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverEnter(menuBuilder, menuItem);
        }
    }
    
    @Override
    public void onItemHoverExit(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverExit(menuBuilder, menuItem);
        }
    }
    
    public void setEnterTransition(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition((Transition)o);
        }
    }
    
    public void setExitTransition(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition((Transition)o);
        }
    }
    
    public void setHoverListener(final MenuItemHoverListener mHoverListener) {
        this.mHoverListener = mHoverListener;
    }
    
    public void setTouchModal(final boolean b) {
        if (MenuPopupWindow.sSetTouchModalMethod == null) {
            return;
        }
        try {
            MenuPopupWindow.sSetTouchModalMethod.invoke(this.mPopup, b);
        }
        catch (Exception ex) {
            Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
        }
    }
}
