// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.content.Context;

public abstract class ActionBar
{
    public boolean collapseActionView() {
        return false;
    }
    
    public void dispatchMenuVisibilityChanged(final boolean b) {
    }
    
    public abstract int getDisplayOptions();
    
    public int getHideOffset() {
        return 0;
    }
    
    public Context getThemedContext() {
        return null;
    }
    
    public abstract void hide();
    
    public boolean invalidateOptionsMenu() {
        return false;
    }
    
    public abstract boolean isShowing();
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    void onDestroy() {
    }
    
    public boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        return false;
    }
    
    boolean requestFocus() {
        return false;
    }
    
    public abstract void setBackgroundDrawable(final Drawable p0);
    
    public abstract void setCustomView(final View p0, final ActionBar$LayoutParams p1);
    
    public void setDefaultDisplayHomeAsUpEnabled(final boolean b) {
    }
    
    public abstract void setDisplayHomeAsUpEnabled(final boolean p0);
    
    public abstract void setDisplayShowCustomEnabled(final boolean p0);
    
    public abstract void setDisplayShowHomeEnabled(final boolean p0);
    
    public abstract void setDisplayShowTitleEnabled(final boolean p0);
    
    public abstract void setDisplayUseLogoEnabled(final boolean p0);
    
    public void setElevation(final float n) {
        if (n != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }
    
    public void setHideOnContentScrollEnabled(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }
    
    public void setHomeActionContentDescription(final int n) {
    }
    
    public void setHomeButtonEnabled(final boolean b) {
    }
    
    public abstract void setLogo(final int p0);
    
    public abstract void setLogo(final Drawable p0);
    
    public void setShowHideAnimationEnabled(final boolean b) {
    }
    
    public abstract void setSubtitle(final CharSequence p0);
    
    public abstract void setTitle(final CharSequence p0);
    
    public void setWindowTitle(final CharSequence charSequence) {
    }
    
    public abstract void show();
    
    public ActionMode startActionMode(final ActionMode$Callback actionMode$Callback) {
        return null;
    }
}
