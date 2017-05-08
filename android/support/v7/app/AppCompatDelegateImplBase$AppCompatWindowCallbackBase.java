// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.KeyEvent;
import android.view.Window$Callback;
import android.support.v7.view.WindowCallbackWrapper;

class AppCompatDelegateImplBase$AppCompatWindowCallbackBase extends WindowCallbackWrapper
{
    final /* synthetic */ AppCompatDelegateImplBase this$0;
    
    AppCompatDelegateImplBase$AppCompatWindowCallbackBase(final AppCompatDelegateImplBase this$0, final Window$Callback window$Callback) {
        this.this$0 = this$0;
        super(window$Callback);
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.this$0.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    @Override
    public boolean dispatchKeyShortcutEvent(final KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent) || this.this$0.onKeyShortcut(keyEvent.getKeyCode(), keyEvent);
    }
    
    @Override
    public void onContentChanged() {
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return (n != 0 || menu instanceof MenuBuilder) && super.onCreatePanelMenu(n, menu);
    }
    
    @Override
    public boolean onMenuOpened(final int n, final Menu menu) {
        super.onMenuOpened(n, menu);
        this.this$0.onMenuOpened(n, menu);
        return true;
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
        this.this$0.onPanelClosed(n, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        MenuBuilder menuBuilder;
        if (menu instanceof MenuBuilder) {
            menuBuilder = (MenuBuilder)menu;
        }
        else {
            menuBuilder = null;
        }
        boolean onPreparePanel;
        if (n == 0 && menuBuilder == null) {
            onPreparePanel = false;
        }
        else {
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            onPreparePanel = super.onPreparePanel(n, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
                return onPreparePanel;
            }
        }
        return onPreparePanel;
    }
}
