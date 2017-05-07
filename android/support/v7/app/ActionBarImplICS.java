// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.app.ActionBar$TabListener;
import android.app.ActionBar$OnNavigationListener;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.app.ActionBar$Tab;
import android.content.Context;
import android.view.View;
import android.app.ActionBar$OnMenuVisibilityListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;

class ActionBarImplICS extends ActionBar
{
    final android.app.ActionBar mActionBar;
    FragmentTransaction mActiveTransaction;
    final Activity mActivity;
    private ArrayList<WeakReference<OnMenuVisibilityListenerWrapper>> mAddedMenuVisWrappers;
    final Callback mCallback;
    
    public ActionBarImplICS(final Activity activity, final Callback callback) {
        this(activity, callback, true);
    }
    
    ActionBarImplICS(final Activity mActivity, final Callback mCallback, final boolean b) {
        this.mAddedMenuVisWrappers = new ArrayList<WeakReference<OnMenuVisibilityListenerWrapper>>();
        this.mActivity = mActivity;
        this.mCallback = mCallback;
        this.mActionBar = mActivity.getActionBar();
        if (b && (this.getDisplayOptions() & 0x4) != 0x0) {
            this.setHomeButtonEnabled(true);
        }
    }
    
    private OnMenuVisibilityListenerWrapper findAndRemoveMenuVisWrapper(final OnMenuVisibilityListener onMenuVisibilityListener) {
        int n;
        for (int i = 0; i < this.mAddedMenuVisWrappers.size(); i = n + 1) {
            final OnMenuVisibilityListenerWrapper onMenuVisibilityListenerWrapper = this.mAddedMenuVisWrappers.get(i).get();
            if (onMenuVisibilityListenerWrapper == null) {
                this.mAddedMenuVisWrappers.remove(i);
                n = i - 1;
            }
            else {
                n = i;
                if (onMenuVisibilityListenerWrapper.mWrappedListener == onMenuVisibilityListener) {
                    this.mAddedMenuVisWrappers.remove(i);
                    return onMenuVisibilityListenerWrapper;
                }
            }
        }
        return null;
    }
    
    @Override
    public void addOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        if (onMenuVisibilityListener != null) {
            final OnMenuVisibilityListenerWrapper onMenuVisibilityListenerWrapper = new OnMenuVisibilityListenerWrapper(onMenuVisibilityListener);
            this.mAddedMenuVisWrappers.add(new WeakReference<OnMenuVisibilityListenerWrapper>(onMenuVisibilityListenerWrapper));
            this.mActionBar.addOnMenuVisibilityListener((ActionBar$OnMenuVisibilityListener)onMenuVisibilityListenerWrapper);
        }
    }
    
    @Override
    public void addTab(final Tab tab) {
        this.mActionBar.addTab(((TabWrapper)tab).mWrappedTab);
    }
    
    @Override
    public void addTab(final Tab tab, final int n) {
        this.mActionBar.addTab(((TabWrapper)tab).mWrappedTab, n);
    }
    
    @Override
    public void addTab(final Tab tab, final int n, final boolean b) {
        this.mActionBar.addTab(((TabWrapper)tab).mWrappedTab, n, b);
    }
    
    @Override
    public void addTab(final Tab tab, final boolean b) {
        this.mActionBar.addTab(((TabWrapper)tab).mWrappedTab, b);
    }
    
    void commitActiveTransaction() {
        if (this.mActiveTransaction != null && !this.mActiveTransaction.isEmpty()) {
            this.mActiveTransaction.commit();
        }
        this.mActiveTransaction = null;
    }
    
    FragmentTransaction getActiveTransaction() {
        if (this.mActiveTransaction == null) {
            this.mActiveTransaction = this.mCallback.getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        }
        return this.mActiveTransaction;
    }
    
    @Override
    public View getCustomView() {
        return this.mActionBar.getCustomView();
    }
    
    @Override
    public int getDisplayOptions() {
        return this.mActionBar.getDisplayOptions();
    }
    
    @Override
    public int getHeight() {
        return this.mActionBar.getHeight();
    }
    
    @Override
    public int getNavigationItemCount() {
        return this.mActionBar.getNavigationItemCount();
    }
    
    @Override
    public int getNavigationMode() {
        return this.mActionBar.getNavigationMode();
    }
    
    @Override
    public int getSelectedNavigationIndex() {
        return this.mActionBar.getSelectedNavigationIndex();
    }
    
    @Override
    public Tab getSelectedTab() {
        return (Tab)this.mActionBar.getSelectedTab().getTag();
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mActionBar.getSubtitle();
    }
    
    @Override
    public Tab getTabAt(final int n) {
        return (Tab)this.mActionBar.getTabAt(n).getTag();
    }
    
    @Override
    public int getTabCount() {
        return this.mActionBar.getTabCount();
    }
    
    @Override
    public Context getThemedContext() {
        return this.mActionBar.getThemedContext();
    }
    
    @Override
    public CharSequence getTitle() {
        return this.mActionBar.getTitle();
    }
    
    @Override
    public void hide() {
        this.mActionBar.hide();
    }
    
    @Override
    public boolean isShowing() {
        return this.mActionBar.isShowing();
    }
    
    @Override
    public Tab newTab() {
        final ActionBar$Tab tab = this.mActionBar.newTab();
        final TabWrapper tag = new TabWrapper(tab);
        tab.setTag((Object)tag);
        return tag;
    }
    
    @Override
    public void removeAllTabs() {
        this.mActionBar.removeAllTabs();
    }
    
    @Override
    public void removeOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mActionBar.removeOnMenuVisibilityListener((ActionBar$OnMenuVisibilityListener)this.findAndRemoveMenuVisWrapper(onMenuVisibilityListener));
    }
    
    @Override
    public void removeTab(final Tab tab) {
        this.mActionBar.removeTab(((TabWrapper)tab).mWrappedTab);
    }
    
    @Override
    public void removeTabAt(final int n) {
        this.mActionBar.removeTabAt(n);
    }
    
    @Override
    public void selectTab(final Tab tab) {
        this.mActionBar.selectTab(((TabWrapper)tab).mWrappedTab);
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        this.mActionBar.setBackgroundDrawable(backgroundDrawable);
    }
    
    @Override
    public void setCustomView(final int customView) {
        this.mActionBar.setCustomView(customView);
    }
    
    @Override
    public void setCustomView(final View customView) {
        this.mActionBar.setCustomView(customView);
    }
    
    @Override
    public void setCustomView(final View view, final LayoutParams layoutParams) {
        final ActionBar$LayoutParams actionBar$LayoutParams = new ActionBar$LayoutParams((ViewGroup$LayoutParams)layoutParams);
        actionBar$LayoutParams.gravity = layoutParams.gravity;
        this.mActionBar.setCustomView(view, actionBar$LayoutParams);
    }
    
    @Override
    public void setDisplayHomeAsUpEnabled(final boolean displayHomeAsUpEnabled) {
        this.mActionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }
    
    @Override
    public void setDisplayOptions(final int displayOptions) {
        this.mActionBar.setDisplayOptions(displayOptions);
    }
    
    @Override
    public void setDisplayOptions(final int n, final int n2) {
        this.mActionBar.setDisplayOptions(n, n2);
    }
    
    @Override
    public void setDisplayShowCustomEnabled(final boolean displayShowCustomEnabled) {
        this.mActionBar.setDisplayShowCustomEnabled(displayShowCustomEnabled);
    }
    
    @Override
    public void setDisplayShowHomeEnabled(final boolean displayShowHomeEnabled) {
        this.mActionBar.setDisplayShowHomeEnabled(displayShowHomeEnabled);
    }
    
    @Override
    public void setDisplayShowTitleEnabled(final boolean displayShowTitleEnabled) {
        this.mActionBar.setDisplayShowTitleEnabled(displayShowTitleEnabled);
    }
    
    @Override
    public void setDisplayUseLogoEnabled(final boolean displayUseLogoEnabled) {
        this.mActionBar.setDisplayUseLogoEnabled(displayUseLogoEnabled);
    }
    
    @Override
    public void setHomeButtonEnabled(final boolean homeButtonEnabled) {
        this.mActionBar.setHomeButtonEnabled(homeButtonEnabled);
    }
    
    @Override
    public void setIcon(final int icon) {
        this.mActionBar.setIcon(icon);
    }
    
    @Override
    public void setIcon(final Drawable icon) {
        this.mActionBar.setIcon(icon);
    }
    
    @Override
    public void setListNavigationCallbacks(final SpinnerAdapter spinnerAdapter, final OnNavigationListener onNavigationListener) {
        final android.app.ActionBar mActionBar = this.mActionBar;
        Object o;
        if (onNavigationListener != null) {
            o = new OnNavigationListenerWrapper(onNavigationListener);
        }
        else {
            o = null;
        }
        mActionBar.setListNavigationCallbacks(spinnerAdapter, (ActionBar$OnNavigationListener)o);
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mActionBar.setLogo(logo);
    }
    
    @Override
    public void setLogo(final Drawable logo) {
        this.mActionBar.setLogo(logo);
    }
    
    @Override
    public void setNavigationMode(final int navigationMode) {
        this.mActionBar.setNavigationMode(navigationMode);
    }
    
    @Override
    public void setSelectedNavigationItem(final int selectedNavigationItem) {
        this.mActionBar.setSelectedNavigationItem(selectedNavigationItem);
    }
    
    @Override
    public void setSubtitle(final int subtitle) {
        this.mActionBar.setSubtitle(subtitle);
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mActionBar.setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int title) {
        this.mActionBar.setTitle(title);
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mActionBar.setTitle(title);
    }
    
    @Override
    public void show() {
        this.mActionBar.show();
    }
    
    static class OnMenuVisibilityListenerWrapper implements ActionBar$OnMenuVisibilityListener
    {
        final OnMenuVisibilityListener mWrappedListener;
        
        public OnMenuVisibilityListenerWrapper(final OnMenuVisibilityListener mWrappedListener) {
            this.mWrappedListener = mWrappedListener;
        }
        
        public void onMenuVisibilityChanged(final boolean b) {
            this.mWrappedListener.onMenuVisibilityChanged(b);
        }
    }
    
    static class OnNavigationListenerWrapper implements ActionBar$OnNavigationListener
    {
        private final OnNavigationListener mWrappedListener;
        
        public OnNavigationListenerWrapper(final OnNavigationListener mWrappedListener) {
            this.mWrappedListener = mWrappedListener;
        }
        
        public boolean onNavigationItemSelected(final int n, final long n2) {
            return this.mWrappedListener.onNavigationItemSelected(n, n2);
        }
    }
    
    class TabWrapper extends Tab implements ActionBar$TabListener
    {
        private CharSequence mContentDescription;
        private TabListener mTabListener;
        private Object mTag;
        final ActionBar$Tab mWrappedTab;
        
        public TabWrapper(final ActionBar$Tab mWrappedTab) {
            this.mWrappedTab = mWrappedTab;
        }
        
        @Override
        public CharSequence getContentDescription() {
            return this.mContentDescription;
        }
        
        @Override
        public View getCustomView() {
            return this.mWrappedTab.getCustomView();
        }
        
        @Override
        public Drawable getIcon() {
            return this.mWrappedTab.getIcon();
        }
        
        @Override
        public int getPosition() {
            return this.mWrappedTab.getPosition();
        }
        
        @Override
        public Object getTag() {
            return this.mTag;
        }
        
        @Override
        public CharSequence getText() {
            return this.mWrappedTab.getText();
        }
        
        public void onTabReselected(final ActionBar$Tab actionBar$Tab, final android.app.FragmentTransaction fragmentTransaction) {
            final TabListener mTabListener = this.mTabListener;
            FragmentTransaction activeTransaction;
            if (fragmentTransaction != null) {
                activeTransaction = ActionBarImplICS.this.getActiveTransaction();
            }
            else {
                activeTransaction = null;
            }
            mTabListener.onTabReselected(this, activeTransaction);
            ActionBarImplICS.this.commitActiveTransaction();
        }
        
        public void onTabSelected(final ActionBar$Tab actionBar$Tab, final android.app.FragmentTransaction fragmentTransaction) {
            final TabListener mTabListener = this.mTabListener;
            FragmentTransaction activeTransaction;
            if (fragmentTransaction != null) {
                activeTransaction = ActionBarImplICS.this.getActiveTransaction();
            }
            else {
                activeTransaction = null;
            }
            mTabListener.onTabSelected(this, activeTransaction);
            ActionBarImplICS.this.commitActiveTransaction();
        }
        
        public void onTabUnselected(final ActionBar$Tab actionBar$Tab, final android.app.FragmentTransaction fragmentTransaction) {
            final TabListener mTabListener = this.mTabListener;
            FragmentTransaction activeTransaction;
            if (fragmentTransaction != null) {
                activeTransaction = ActionBarImplICS.this.getActiveTransaction();
            }
            else {
                activeTransaction = null;
            }
            mTabListener.onTabUnselected(this, activeTransaction);
        }
        
        @Override
        public void select() {
            this.mWrappedTab.select();
        }
        
        @Override
        public Tab setContentDescription(final int n) {
            this.mContentDescription = ActionBarImplICS.this.mActivity.getText(n);
            return this;
        }
        
        @Override
        public Tab setContentDescription(final CharSequence mContentDescription) {
            this.mContentDescription = mContentDescription;
            return this;
        }
        
        @Override
        public Tab setCustomView(final int customView) {
            this.mWrappedTab.setCustomView(customView);
            return this;
        }
        
        @Override
        public Tab setCustomView(final View customView) {
            this.mWrappedTab.setCustomView(customView);
            return this;
        }
        
        @Override
        public Tab setIcon(final int icon) {
            this.mWrappedTab.setIcon(icon);
            return this;
        }
        
        @Override
        public Tab setIcon(final Drawable icon) {
            this.mWrappedTab.setIcon(icon);
            return this;
        }
        
        @Override
        public Tab setTabListener(final TabListener mTabListener) {
            this.mTabListener = mTabListener;
            final ActionBar$Tab mWrappedTab = this.mWrappedTab;
            Object tabListener;
            if (mTabListener != null) {
                tabListener = this;
            }
            else {
                tabListener = null;
            }
            mWrappedTab.setTabListener((ActionBar$TabListener)tabListener);
            return this;
        }
        
        @Override
        public Tab setTag(final Object mTag) {
            this.mTag = mTag;
            return this;
        }
        
        @Override
        public Tab setText(final int text) {
            this.mWrappedTab.setText(text);
            return this;
        }
        
        @Override
        public Tab setText(final CharSequence text) {
            this.mWrappedTab.setText(text);
            return this;
        }
    }
}
