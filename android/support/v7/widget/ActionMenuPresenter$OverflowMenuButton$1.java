// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.menu.ad;
import android.support.v7.appcompat.R$integer;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.internal.view.ActionBarPolicy;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View$MeasureSpec;
import android.support.v7.internal.view.menu.c;
import android.support.v7.internal.view.menu.k;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.m;
import android.support.v7.internal.view.menu.aa;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$layout;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;
import android.view.View;

class ActionMenuPresenter$OverflowMenuButton$1 extends ListPopupWindow$ForwardingListener
{
    final /* synthetic */ ActionMenuPresenter$OverflowMenuButton this$1;
    final /* synthetic */ ActionMenuPresenter val$this$0;
    
    ActionMenuPresenter$OverflowMenuButton$1(final ActionMenuPresenter$OverflowMenuButton this$1, final View view, final ActionMenuPresenter val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
        super(view);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        if (this.this$1.this$0.mOverflowPopup == null) {
            return null;
        }
        return this.this$1.this$0.mOverflowPopup.getPopup();
    }
    
    public boolean onForwardingStarted() {
        this.this$1.this$0.showOverflowMenu();
        return true;
    }
    
    public boolean onForwardingStopped() {
        if (this.this$1.this$0.mPostedOpenRunnable != null) {
            return false;
        }
        this.this$1.this$0.hideOverflowMenu();
        return true;
    }
}
