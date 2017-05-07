// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MenuItem;
import android.support.v7.internal.view.menu.ae;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.view.Menu;
import android.support.v7.internal.view.menu.w;
import android.support.v7.internal.view.menu.i;
import android.view.View$OnTouchListener;
import android.content.Context;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.j;
import android.view.View;

class PopupMenu$1 extends ListPopupWindow$ForwardingListener
{
    final /* synthetic */ PopupMenu this$0;
    
    PopupMenu$1(final PopupMenu this$0, final View view) {
        this.this$0 = this$0;
        super(view);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        return this.this$0.mPopup.getPopup();
    }
    
    @Override
    protected boolean onForwardingStarted() {
        this.this$0.show();
        return true;
    }
    
    @Override
    protected boolean onForwardingStopped() {
        this.this$0.dismiss();
        return true;
    }
}
