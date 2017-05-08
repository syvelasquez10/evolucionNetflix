// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.SubMenuBuilder;
import android.os.Parcelable;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;

class Toolbar$ExpandedActionViewMenuPresenter implements MenuPresenter
{
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;
    final /* synthetic */ Toolbar this$0;
    
    Toolbar$ExpandedActionViewMenuPresenter(final Toolbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        if (this.this$0.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)this.this$0.mExpandedActionView).onActionViewCollapsed();
        }
        this.this$0.removeView(this.this$0.mExpandedActionView);
        this.this$0.removeView((View)this.this$0.mCollapseButtonView);
        this.this$0.mExpandedActionView = null;
        this.this$0.addChildrenForExpandedActionView();
        this.mCurrentExpandedItem = null;
        this.this$0.requestLayout();
        menuItemImpl.setActionViewExpanded(false);
        return true;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl mCurrentExpandedItem) {
        this.this$0.ensureCollapseButtonView();
        if (this.this$0.mCollapseButtonView.getParent() != this.this$0) {
            this.this$0.addView((View)this.this$0.mCollapseButtonView);
        }
        this.this$0.mExpandedActionView = mCurrentExpandedItem.getActionView();
        this.mCurrentExpandedItem = mCurrentExpandedItem;
        if (this.this$0.mExpandedActionView.getParent() != this.this$0) {
            final Toolbar$LayoutParams generateDefaultLayoutParams = this.this$0.generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (0x800003 | (this.this$0.mButtonGravity & 0x70));
            generateDefaultLayoutParams.mViewType = 2;
            this.this$0.mExpandedActionView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
            this.this$0.addView(this.this$0.mExpandedActionView);
        }
        this.this$0.removeChildrenForExpandedActionView();
        this.this$0.requestLayout();
        mCurrentExpandedItem.setActionViewExpanded(true);
        if (this.this$0.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)this.this$0.mExpandedActionView).onActionViewExpanded();
        }
        return true;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    @Override
    public int getId() {
        return 0;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder mMenu) {
        if (this.mMenu != null && this.mCurrentExpandedItem != null) {
            this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
        }
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return false;
    }
    
    @Override
    public void setCallback(final MenuPresenter$Callback menuPresenter$Callback) {
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        final boolean b2 = false;
        if (this.mCurrentExpandedItem != null) {
            boolean b3 = b2;
            if (this.mMenu != null) {
                final int size = this.mMenu.size();
                int n = 0;
                while (true) {
                    b3 = b2;
                    if (n >= size) {
                        break;
                    }
                    if (this.mMenu.getItem(n) == this.mCurrentExpandedItem) {
                        b3 = true;
                        break;
                    }
                    ++n;
                }
            }
            if (!b3) {
                this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }
    }
}
