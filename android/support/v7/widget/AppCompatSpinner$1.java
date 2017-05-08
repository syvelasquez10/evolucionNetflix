// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class AppCompatSpinner$1 extends ForwardingListener
{
    final /* synthetic */ AppCompatSpinner this$0;
    final /* synthetic */ AppCompatSpinner$DropdownPopup val$popup;
    
    AppCompatSpinner$1(final AppCompatSpinner this$0, final View view, final AppCompatSpinner$DropdownPopup val$popup) {
        this.this$0 = this$0;
        this.val$popup = val$popup;
        super(view);
    }
    
    @Override
    public ShowableListMenu getPopup() {
        return this.val$popup;
    }
    
    public boolean onForwardingStarted() {
        if (!this.this$0.mPopup.isShowing()) {
            this.this$0.mPopup.show();
        }
        return true;
    }
}
