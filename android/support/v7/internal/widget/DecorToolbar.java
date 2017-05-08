// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.Window$Callback;
import android.support.v7.internal.view.menu.j;
import android.support.v7.internal.view.menu.y;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.Menu;
import android.content.Context;

public interface DecorToolbar
{
    boolean canShowOverflowMenu();
    
    void collapseActionView();
    
    void dismissPopupMenus();
    
    Context getContext();
    
    int getDisplayOptions();
    
    Menu getMenu();
    
    int getNavigationMode();
    
    CharSequence getTitle();
    
    ViewGroup getViewGroup();
    
    int getVisibility();
    
    boolean hasExpandedActionView();
    
    boolean hideOverflowMenu();
    
    void initIndeterminateProgress();
    
    void initProgress();
    
    boolean isOverflowMenuShowPending();
    
    boolean isOverflowMenuShowing();
    
    void setBackgroundDrawable(final Drawable p0);
    
    void setCollapsible(final boolean p0);
    
    void setCustomView(final View p0);
    
    void setDisplayOptions(final int p0);
    
    void setEmbeddedTabView(final ScrollingTabContainerView p0);
    
    void setHomeButtonEnabled(final boolean p0);
    
    void setIcon(final int p0);
    
    void setIcon(final Drawable p0);
    
    void setLogo(final int p0);
    
    void setLogo(final Drawable p0);
    
    void setMenu(final Menu p0, final y p1);
    
    void setMenuCallbacks(final y p0, final j p1);
    
    void setMenuPrepared();
    
    void setNavigationContentDescription(final int p0);
    
    void setTitle(final CharSequence p0);
    
    void setVisibility(final int p0);
    
    void setWindowCallback(final Window$Callback p0);
    
    void setWindowTitle(final CharSequence p0);
    
    ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int p0, final long p1);
    
    boolean showOverflowMenu();
}
