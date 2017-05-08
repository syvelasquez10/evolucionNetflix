// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter$Callback;

class ActionMenuPresenter$PopupPresenterCallback implements MenuPresenter$Callback
{
    final /* synthetic */ ActionMenuPresenter this$0;
    
    ActionMenuPresenter$PopupPresenterCallback(final ActionMenuPresenter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (menuBuilder instanceof SubMenuBuilder) {
            menuBuilder.getRootMenu().close(false);
        }
        final MenuPresenter$Callback callback = this.this$0.getCallback();
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, b);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (menuBuilder == null) {
            return false;
        }
        this.this$0.mOpenSubMenuId = ((SubMenuBuilder)menuBuilder).getItem().getItemId();
        final MenuPresenter$Callback callback = this.this$0.getCallback();
        return callback != null && callback.onOpenSubMenu(menuBuilder);
    }
}
