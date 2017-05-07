// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle$Delegate;

class ActionBarActivityDelegate$ActionBarDrawableToggleImpl implements ActionBarDrawerToggle$Delegate, ActionBarDrawerToggle$Delegate
{
    final /* synthetic */ ActionBarActivityDelegate this$0;
    
    private ActionBarActivityDelegate$ActionBarDrawableToggleImpl(final ActionBarActivityDelegate this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        return this.this$0.getActionBarThemedContext();
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        final TypedArray obtainStyledAttributes = this.this$0.getActionBarThemedContext().obtainStyledAttributes(new int[] { this.this$0.getHomeAsUpIndicatorAttrId() });
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar supportActionBar = this.this$0.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable homeAsUpIndicator, final int homeActionContentDescription) {
        final ActionBar supportActionBar = this.this$0.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
