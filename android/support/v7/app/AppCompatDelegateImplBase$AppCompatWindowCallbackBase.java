// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.support.v7.internal.view.menu.i;
import android.view.Menu;
import android.view.KeyEvent;
import android.view.Window$Callback;
import android.support.v7.internal.view.WindowCallbackWrapper;

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
        return (n != 0 || menu instanceof i) && super.onCreatePanelMenu(n, menu);
    }
    
    @Override
    public boolean onMenuOpened(final int n, final Menu menu) {
        return super.onMenuOpened(n, menu) || this.this$0.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
        this.this$0.onPanelClosed(n, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        i i;
        if (menu instanceof i) {
            i = (i)menu;
        }
        else {
            i = null;
        }
        boolean onPreparePanel;
        if (n == 0 && i == null) {
            onPreparePanel = false;
        }
        else {
            if (i != null) {
                i.c(true);
            }
            onPreparePanel = super.onPreparePanel(n, view, menu);
            if (i != null) {
                i.c(false);
                return onPreparePanel;
            }
        }
        return onPreparePanel;
    }
}
