// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.graphics.drawable.Drawable;
import android.content.Context;
import android.app.Activity;

class ActionBarDrawerToggle$DummyDelegate implements ActionBarDrawerToggle$Delegate
{
    final Activity mActivity;
    
    ActionBarDrawerToggle$DummyDelegate(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        return (Context)this.mActivity;
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        return null;
    }
    
    @Override
    public void setActionBarDescription(final int n) {
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable drawable, final int n) {
    }
}
