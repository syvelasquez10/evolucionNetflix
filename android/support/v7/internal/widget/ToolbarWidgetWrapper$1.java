// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$id;
import android.support.v7.internal.view.menu.y;
import android.view.Menu;
import android.support.v7.widget.Toolbar$LayoutParams;
import android.util.Log;
import android.content.Context;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$drawable;
import android.support.v7.appcompat.R$string;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.widget.Toolbar;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.internal.view.menu.a;
import android.view.View$OnClickListener;

class ToolbarWidgetWrapper$1 implements View$OnClickListener
{
    final a mNavItem;
    final /* synthetic */ ToolbarWidgetWrapper this$0;
    
    ToolbarWidgetWrapper$1(final ToolbarWidgetWrapper this$0) {
        this.this$0 = this$0;
        this.mNavItem = new a(this.this$0.mToolbar.getContext(), 0, 16908332, 0, 0, this.this$0.mTitle);
    }
    
    public void onClick(final View view) {
        if (this.this$0.mWindowCallback != null && this.this$0.mMenuPrepared) {
            this.this$0.mWindowCallback.onMenuItemSelected(0, (MenuItem)this.mNavItem);
        }
    }
}
