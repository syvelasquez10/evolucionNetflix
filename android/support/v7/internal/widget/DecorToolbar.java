// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.z;
import android.view.Menu;
import android.widget.SpinnerAdapter;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;

public interface DecorToolbar
{
    void animateToVisibility(final int p0);
    
    boolean canShowOverflowMenu();
    
    boolean canSplit();
    
    void collapseActionView();
    
    void dismissPopupMenus();
    
    Context getContext();
    
    View getCustomView();
    
    int getDisplayOptions();
    
    int getDropdownItemCount();
    
    int getDropdownSelectedPosition();
    
    int getNavigationMode();
    
    CharSequence getSubtitle();
    
    CharSequence getTitle();
    
    ViewGroup getViewGroup();
    
    boolean hasEmbeddedTabs();
    
    boolean hasExpandedActionView();
    
    boolean hasIcon();
    
    boolean hasLogo();
    
    boolean hideOverflowMenu();
    
    void initIndeterminateProgress();
    
    void initProgress();
    
    boolean isOverflowMenuShowPending();
    
    boolean isOverflowMenuShowing();
    
    boolean isSplit();
    
    boolean isTitleTruncated();
    
    void restoreHierarchyState(final SparseArray<Parcelable> p0);
    
    void saveHierarchyState(final SparseArray<Parcelable> p0);
    
    void setCollapsible(final boolean p0);
    
    void setCustomView(final View p0);
    
    void setDefaultNavigationContentDescription(final int p0);
    
    void setDefaultNavigationIcon(final Drawable p0);
    
    void setDisplayOptions(final int p0);
    
    void setDropdownParams(final SpinnerAdapter p0, final AdapterViewCompat$OnItemSelectedListener p1);
    
    void setDropdownSelectedPosition(final int p0);
    
    void setEmbeddedTabView(final ScrollingTabContainerView p0);
    
    void setHomeButtonEnabled(final boolean p0);
    
    void setIcon(final int p0);
    
    void setIcon(final Drawable p0);
    
    void setLogo(final int p0);
    
    void setLogo(final Drawable p0);
    
    void setMenu(final Menu p0, final z p1);
    
    void setMenuPrepared();
    
    void setNavigationContentDescription(final int p0);
    
    void setNavigationContentDescription(final CharSequence p0);
    
    void setNavigationIcon(final int p0);
    
    void setNavigationIcon(final Drawable p0);
    
    void setNavigationMode(final int p0);
    
    void setSplitToolbar(final boolean p0);
    
    void setSplitView(final ViewGroup p0);
    
    void setSplitWhenNarrow(final boolean p0);
    
    void setSubtitle(final CharSequence p0);
    
    void setTitle(final CharSequence p0);
    
    void setWindowCallback(final WindowCallback p0);
    
    void setWindowTitle(final CharSequence p0);
    
    boolean showOverflowMenu();
}
