// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.TextUtils$TruncateAt;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.widget.ViewUtils;
import android.text.Layout;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.support.v7.app.ActionBar$LayoutParams;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$OnClickListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.text.TextUtils;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.internal.widget.TintManager;
import java.util.ArrayList;
import android.widget.TextView;
import android.support.v7.internal.view.menu.j;
import android.widget.ImageView;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.ae;
import android.os.Parcelable;
import android.content.Context;
import android.support.v7.internal.view.menu.aa;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.m;
import android.support.v7.internal.view.menu.y;

class Toolbar$ExpandedActionViewMenuPresenter implements y
{
    m mCurrentExpandedItem;
    i mMenu;
    final /* synthetic */ Toolbar this$0;
    
    private Toolbar$ExpandedActionViewMenuPresenter(final Toolbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean collapseItemActionView(final i i, final m m) {
        if (this.this$0.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)this.this$0.mExpandedActionView).onActionViewCollapsed();
        }
        this.this$0.removeView(this.this$0.mExpandedActionView);
        this.this$0.removeView((View)this.this$0.mCollapseButtonView);
        this.this$0.mExpandedActionView = null;
        this.this$0.setChildVisibilityForExpandedActionView(false);
        this.mCurrentExpandedItem = null;
        this.this$0.requestLayout();
        m.e(false);
        return true;
    }
    
    @Override
    public boolean expandItemActionView(final i i, final m mCurrentExpandedItem) {
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
        this.this$0.setChildVisibilityForExpandedActionView(true);
        this.this$0.requestLayout();
        mCurrentExpandedItem.e(true);
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
    public aa getMenuView(final ViewGroup viewGroup) {
        return null;
    }
    
    @Override
    public void initForMenu(final Context context, final i mMenu) {
        if (this.mMenu != null && this.mCurrentExpandedItem != null) {
            this.mMenu.d(this.mCurrentExpandedItem);
        }
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final i i, final boolean b) {
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final ae ae) {
        return false;
    }
    
    @Override
    public void setCallback(final z z) {
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
