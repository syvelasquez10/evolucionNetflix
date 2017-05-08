// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.Menu;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.appcompat.R$attr;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.support.design.R$dimen;
import android.support.v4.content.ContextCompat;
import android.support.design.R$color;
import android.os.Build$VERSION;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.support.v7.view.menu.MenuPresenter;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.support.design.internal.BottomNavigationMenu;
import android.util.AttributeSet;
import android.content.Context;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.internal.BottomNavigationMenuView;
import android.view.MenuInflater;
import android.widget.FrameLayout;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder$Callback;

class BottomNavigationView$1 implements MenuBuilder$Callback
{
    final /* synthetic */ BottomNavigationView this$0;
    
    BottomNavigationView$1(final BottomNavigationView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return this.this$0.mListener != null && !this.this$0.mListener.onNavigationItemSelected(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
    }
}
