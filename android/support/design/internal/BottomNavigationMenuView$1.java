// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.view.View$MeasureSpec;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.design.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.util.Pools$SynchronizedPool;
import android.support.v7.view.menu.MenuBuilder;
import android.content.res.ColorStateList;
import android.support.v4.util.Pools$Pool;
import android.support.v7.view.menu.MenuView;
import android.view.ViewGroup;
import android.support.v7.view.menu.MenuPresenter;
import android.view.MenuItem;
import android.view.View;
import android.view.View$OnClickListener;

class BottomNavigationMenuView$1 implements View$OnClickListener
{
    final /* synthetic */ BottomNavigationMenuView this$0;
    
    BottomNavigationMenuView$1(final BottomNavigationMenuView this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView)view;
        final int itemPosition = bottomNavigationItemView.getItemPosition();
        if (!this.this$0.mMenu.performItemAction((MenuItem)bottomNavigationItemView.getItemData(), this.this$0.mPresenter, 0)) {
            this.this$0.activateNewButton(itemPosition);
        }
    }
}
