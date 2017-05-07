// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.TextUtils$TruncateAt;
import android.view.ContextThemeWrapper;
import android.os.Build$VERSION;
import android.support.v4.view.MenuItemCompat;
import android.os.Parcelable;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v7.internal.widget.DecorToolbar;
import android.view.Menu;
import android.support.v7.app.ActionBar$LayoutParams;
import android.support.v7.internal.view.menu.m;
import android.view.View$MeasureSpec;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v7.internal.view.menu.x;
import android.support.v7.internal.view.menu.i;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
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
import android.widget.TextView;
import android.content.Context;
import android.support.v7.internal.view.menu.j;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.View;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.support.v7.internal.view.menu.y;
import android.view.ViewGroup;
import android.view.MenuItem;

class Toolbar$1 implements ActionMenuView$OnMenuItemClickListener
{
    final /* synthetic */ Toolbar this$0;
    
    Toolbar$1(final Toolbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return this.this$0.mOnMenuItemClickListener != null && this.this$0.mOnMenuItemClickListener.onMenuItemClick(menuItem);
    }
}
