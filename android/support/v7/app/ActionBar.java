// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.app.FragmentManager;
import android.widget.SpinnerAdapter;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.view.View;

public abstract class ActionBar
{
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_TABS = 2;
    
    public abstract void addOnMenuVisibilityListener(final OnMenuVisibilityListener p0);
    
    public abstract void addTab(final Tab p0);
    
    public abstract void addTab(final Tab p0, final int p1);
    
    public abstract void addTab(final Tab p0, final int p1, final boolean p2);
    
    public abstract void addTab(final Tab p0, final boolean p1);
    
    public abstract View getCustomView();
    
    public abstract int getDisplayOptions();
    
    public abstract int getHeight();
    
    public abstract int getNavigationItemCount();
    
    public abstract int getNavigationMode();
    
    public abstract int getSelectedNavigationIndex();
    
    public abstract Tab getSelectedTab();
    
    public abstract CharSequence getSubtitle();
    
    public abstract Tab getTabAt(final int p0);
    
    public abstract int getTabCount();
    
    public Context getThemedContext() {
        return null;
    }
    
    public abstract CharSequence getTitle();
    
    public abstract void hide();
    
    public abstract boolean isShowing();
    
    public abstract Tab newTab();
    
    public abstract void removeAllTabs();
    
    public abstract void removeOnMenuVisibilityListener(final OnMenuVisibilityListener p0);
    
    public abstract void removeTab(final Tab p0);
    
    public abstract void removeTabAt(final int p0);
    
    public abstract void selectTab(final Tab p0);
    
    public abstract void setBackgroundDrawable(final Drawable p0);
    
    public abstract void setCustomView(final int p0);
    
    public abstract void setCustomView(final View p0);
    
    public abstract void setCustomView(final View p0, final LayoutParams p1);
    
    public abstract void setDisplayHomeAsUpEnabled(final boolean p0);
    
    public abstract void setDisplayOptions(final int p0);
    
    public abstract void setDisplayOptions(final int p0, final int p1);
    
    public abstract void setDisplayShowCustomEnabled(final boolean p0);
    
    public abstract void setDisplayShowHomeEnabled(final boolean p0);
    
    public abstract void setDisplayShowTitleEnabled(final boolean p0);
    
    public abstract void setDisplayUseLogoEnabled(final boolean p0);
    
    public void setHomeButtonEnabled(final boolean b) {
    }
    
    public abstract void setIcon(final int p0);
    
    public abstract void setIcon(final Drawable p0);
    
    public abstract void setListNavigationCallbacks(final SpinnerAdapter p0, final OnNavigationListener p1);
    
    public abstract void setLogo(final int p0);
    
    public abstract void setLogo(final Drawable p0);
    
    public abstract void setNavigationMode(final int p0);
    
    public abstract void setSelectedNavigationItem(final int p0);
    
    public void setSplitBackgroundDrawable(final Drawable drawable) {
    }
    
    public void setStackedBackgroundDrawable(final Drawable drawable) {
    }
    
    public abstract void setSubtitle(final int p0);
    
    public abstract void setSubtitle(final CharSequence p0);
    
    public abstract void setTitle(final int p0);
    
    public abstract void setTitle(final CharSequence p0);
    
    public abstract void show();
    
    interface Callback
    {
        FragmentManager getSupportFragmentManager();
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        public int gravity;
        
        public LayoutParams(final int n) {
            this(-2, -1, n);
        }
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.gravity = -1;
            this.gravity = 19;
        }
        
        public LayoutParams(final int n, final int n2, final int gravity) {
            super(n, n2);
            this.gravity = -1;
            this.gravity = gravity;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.gravity = -1;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.ActionBarLayout);
            this.gravity = obtainStyledAttributes.getInt(0, -1);
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$MarginLayoutParams)layoutParams);
            this.gravity = -1;
            this.gravity = layoutParams.gravity;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.gravity = -1;
        }
    }
    
    public interface OnMenuVisibilityListener
    {
        void onMenuVisibilityChanged(final boolean p0);
    }
    
    public interface OnNavigationListener
    {
        boolean onNavigationItemSelected(final int p0, final long p1);
    }
    
    public abstract static class Tab
    {
        public static final int INVALID_POSITION = -1;
        
        public abstract CharSequence getContentDescription();
        
        public abstract View getCustomView();
        
        public abstract Drawable getIcon();
        
        public abstract int getPosition();
        
        public abstract Object getTag();
        
        public abstract CharSequence getText();
        
        public abstract void select();
        
        public abstract Tab setContentDescription(final int p0);
        
        public abstract Tab setContentDescription(final CharSequence p0);
        
        public abstract Tab setCustomView(final int p0);
        
        public abstract Tab setCustomView(final View p0);
        
        public abstract Tab setIcon(final int p0);
        
        public abstract Tab setIcon(final Drawable p0);
        
        public abstract Tab setTabListener(final TabListener p0);
        
        public abstract Tab setTag(final Object p0);
        
        public abstract Tab setText(final int p0);
        
        public abstract Tab setText(final CharSequence p0);
    }
    
    public interface TabListener
    {
        void onTabReselected(final Tab p0, final FragmentTransaction p1);
        
        void onTabSelected(final Tab p0, final FragmentTransaction p1);
        
        void onTabUnselected(final Tab p0, final FragmentTransaction p1);
    }
}
