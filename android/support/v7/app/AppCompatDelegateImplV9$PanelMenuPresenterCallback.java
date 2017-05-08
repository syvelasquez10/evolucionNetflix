// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Window$Callback;
import android.view.Menu;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter$Callback;

final class AppCompatDelegateImplV9$PanelMenuPresenterCallback implements MenuPresenter$Callback
{
    final /* synthetic */ AppCompatDelegateImplV9 this$0;
    
    AppCompatDelegateImplV9$PanelMenuPresenterCallback(final AppCompatDelegateImplV9 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, final boolean b) {
        final Object rootMenu = menuBuilder.getRootMenu();
        boolean b2;
        if (rootMenu != menuBuilder) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final AppCompatDelegateImplV9 this$0 = this.this$0;
        if (b2) {
            menuBuilder = (MenuBuilder)rootMenu;
        }
        final AppCompatDelegateImplV9$PanelFeatureState menuPanel = this$0.findMenuPanel((Menu)menuBuilder);
        if (menuPanel != null) {
            if (!b2) {
                this.this$0.closePanel(menuPanel, b);
                return;
            }
            this.this$0.callOnPanelClosed(menuPanel.featureId, menuPanel, (Menu)rootMenu);
            this.this$0.closePanel(menuPanel, true);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (menuBuilder == null && this.this$0.mHasActionBar) {
            final Window$Callback windowCallback = this.this$0.getWindowCallback();
            if (windowCallback != null && !this.this$0.isDestroyed()) {
                windowCallback.onMenuOpened(108, (Menu)menuBuilder);
            }
        }
        return true;
    }
}
