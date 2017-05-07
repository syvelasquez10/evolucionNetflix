// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.internal.view.menu.ad;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.y;

class ActionMenuPresenter$PopupPresenterCallback implements y
{
    final /* synthetic */ ActionMenuPresenter this$0;
    
    private ActionMenuPresenter$PopupPresenterCallback(final ActionMenuPresenter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
        if (i instanceof ad) {
            ((ad)i).p().a(false);
        }
        final y callback = this.this$0.getCallback();
        if (callback != null) {
            callback.onCloseMenu(i, b);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final i i) {
        if (i == null) {
            return false;
        }
        this.this$0.mOpenSubMenuId = ((ad)i).getItem().getItemId();
        final y callback = this.this$0.getCallback();
        return callback != null && callback.onOpenSubMenu(i);
    }
}
