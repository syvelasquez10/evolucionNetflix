// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.app.ActionBarDrawerToggle$Delegate;

class ActionBarActivityDelegate$ActionBarDrawableToggleImpl implements ActionBarDrawerToggle$Delegate, ActionBarDrawerToggle$Delegate
{
    final /* synthetic */ ActionBarActivityDelegate this$0;
    
    private ActionBarActivityDelegate$ActionBarDrawableToggleImpl(final ActionBarActivityDelegate this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar supportActionBar = this.this$0.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
