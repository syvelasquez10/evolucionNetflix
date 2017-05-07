// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

class ListPopupWindow$1 extends ListPopupWindow$ForwardingListener
{
    final /* synthetic */ ListPopupWindow this$0;
    
    ListPopupWindow$1(final ListPopupWindow this$0, final View view) {
        this.this$0 = this$0;
        super(view);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        return this.this$0;
    }
}
