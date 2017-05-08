// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.View;
import android.support.v7.widget.ForwardingListener;

class ActionMenuItemView$ActionMenuItemForwardingListener extends ForwardingListener
{
    final /* synthetic */ ActionMenuItemView this$0;
    
    public ActionMenuItemView$ActionMenuItemForwardingListener(final ActionMenuItemView this$0) {
        this.this$0 = this$0;
        super((View)this$0);
    }
    
    @Override
    public ShowableListMenu getPopup() {
        if (this.this$0.mPopupCallback != null) {
            return this.this$0.mPopupCallback.getPopup();
        }
        return null;
    }
    
    @Override
    protected boolean onForwardingStarted() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.this$0.mItemInvoker != null) {
            b2 = b;
            if (this.this$0.mItemInvoker.invokeItem(this.this$0.mItemData)) {
                final ShowableListMenu popup = this.getPopup();
                b2 = b;
                if (popup != null) {
                    b2 = b;
                    if (popup.isShowing()) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
}
