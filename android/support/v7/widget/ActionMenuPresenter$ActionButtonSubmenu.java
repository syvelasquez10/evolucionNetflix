// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.transition.ActionBarTransition;
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
import android.support.v7.internal.view.menu.aa;
import android.view.ViewGroup;
import android.support.v7.internal.view.menu.z;
import android.support.v7.appcompat.R$layout;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.y;
import android.support.v7.internal.view.menu.m;
import android.view.View;
import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$attr;
import android.content.Context;
import android.support.v7.internal.view.menu.ad;
import android.support.v7.internal.view.menu.v;

class ActionMenuPresenter$ActionButtonSubmenu extends v
{
    private ad mSubMenu;
    final /* synthetic */ ActionMenuPresenter this$0;
    
    public ActionMenuPresenter$ActionButtonSubmenu(final ActionMenuPresenter this$0, final Context context, final ad mSubMenu) {
        final boolean b = false;
        this.this$0 = this$0;
        super(context, mSubMenu, null, false, R$attr.actionOverflowMenuStyle);
        this.mSubMenu = mSubMenu;
        if (!((m)mSubMenu.getItem()).i()) {
            View access$500;
            if (this$0.mOverflowButton == null) {
                access$500 = (View)this$0.mMenuView;
            }
            else {
                access$500 = this$0.mOverflowButton;
            }
            this.setAnchorView(access$500);
        }
        this.setCallback(this$0.mPopupPresenterCallback);
        final int size = mSubMenu.size();
        int n = 0;
        boolean forceShowIcon;
        while (true) {
            forceShowIcon = b;
            if (n >= size) {
                break;
            }
            final MenuItem item = mSubMenu.getItem(n);
            if (item.isVisible() && item.getIcon() != null) {
                forceShowIcon = true;
                break;
            }
            ++n;
        }
        this.setForceShowIcon(forceShowIcon);
    }
    
    @Override
    public void onDismiss() {
        super.onDismiss();
        this.this$0.mActionButtonPopup = null;
        this.this$0.mOpenSubMenuId = 0;
    }
}
