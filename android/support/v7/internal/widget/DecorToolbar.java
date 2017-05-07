// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.y;
import android.view.Menu;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

public interface DecorToolbar
{
    void animateToVisibility(final int p0);
    
    boolean canShowOverflowMenu();
    
    void collapseActionView();
    
    void dismissPopupMenus();
    
    Context getContext();
    
    int getDisplayOptions();
    
    int getNavigationMode();
    
    CharSequence getTitle();
    
    ViewGroup getViewGroup();
    
    boolean hasExpandedActionView();
    
    boolean hideOverflowMenu();
    
    void initIndeterminateProgress();
    
    void initProgress();
    
    boolean isOverflowMenuShowPending();
    
    boolean isOverflowMenuShowing();
    
    boolean isSplit();
    
    void setCollapsible(final boolean p0);
    
    void setCustomView(final View p0);
    
    void setDisplayOptions(final int p0);
    
    void setEmbeddedTabView(final ScrollingTabContainerView p0);
    
    void setHomeButtonEnabled(final boolean p0);
    
    void setIcon(final int p0);
    
    void setIcon(final Drawable p0);
    
    void setLogo(final int p0);
    
    void setMenu(final Menu p0, final y p1);
    
    void setMenuPrepared();
    
    void setNavigationContentDescription(final int p0);
    
    void setNavigationIcon(final Drawable p0);
    
    void setTitle(final CharSequence p0);
    
    void setWindowCallback(final WindowCallback p0);
    
    void setWindowTitle(final CharSequence p0);
    
    boolean showOverflowMenu();
}
