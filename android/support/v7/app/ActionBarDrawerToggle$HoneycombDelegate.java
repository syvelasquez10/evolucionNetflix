// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.graphics.drawable.Drawable;
import android.app.ActionBar;
import android.content.Context;
import android.app.Activity;

class ActionBarDrawerToggle$HoneycombDelegate implements ActionBarDrawerToggle$Delegate
{
    final Activity mActivity;
    ActionBarDrawerToggleHoneycomb$SetIndicatorInfo mSetIndicatorInfo;
    
    private ActionBarDrawerToggle$HoneycombDelegate(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            return actionBar.getThemedContext();
        }
        return (Context)this.mActivity;
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(this.mActivity);
    }
    
    @Override
    public void setActionBarDescription(final int n) {
        this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, n);
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable drawable, final int n) {
        this.mActivity.getActionBar().setDisplayShowHomeEnabled(true);
        this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, drawable, n);
        this.mActivity.getActionBar().setDisplayShowHomeEnabled(false);
    }
}
