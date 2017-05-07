// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ContextThemeWrapper;
import android.support.v7.internal.widget.ViewUtils;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.support.v7.internal.view.menu.m;
import android.support.v7.internal.view.menu.x;
import android.view.Menu;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.internal.view.menu.y;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.k;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.j;

class ActionMenuView$MenuBuilderCallback implements j
{
    final /* synthetic */ ActionMenuView this$0;
    
    private ActionMenuView$MenuBuilderCallback(final ActionMenuView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemSelected(final i i, final MenuItem menuItem) {
        return this.this$0.mOnMenuItemClickListener != null && this.this$0.mOnMenuItemClickListener.onMenuItemClick(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final i i) {
        if (this.this$0.mMenuBuilderCallback != null) {
            this.this$0.mMenuBuilderCallback.onMenuModeChange(i);
        }
    }
}
