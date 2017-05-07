// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.app.ActionBar;
import android.content.Context;
import android.app.Activity;

class ActionBarDrawerToggle$JellybeanMr2Delegate implements ActionBarDrawerToggle$Delegate
{
    final Activity mActivity;
    
    private ActionBarDrawerToggle$JellybeanMr2Delegate(final Activity mActivity) {
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
        final TypedArray obtainStyledAttributes = this.getActionBarThemedContext().obtainStyledAttributes((AttributeSet)null, new int[] { 16843531 }, 16843470, 0);
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable homeAsUpIndicator, final int homeActionContentDescription) {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            actionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
