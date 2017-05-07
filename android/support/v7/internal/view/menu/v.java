// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.view.MenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider$VisibilityListener;

class v extends p implements ActionProvider$VisibilityListener
{
    android.view.ActionProvider$VisibilityListener c;
    final /* synthetic */ u d;
    
    public v(final u d, final ActionProvider actionProvider) {
        this.d = d;
        super(d, actionProvider);
    }
    
    public boolean isVisible() {
        return this.a.isVisible();
    }
    
    @Override
    public void onActionProviderVisibilityChanged(final boolean b) {
        if (this.c != null) {
            this.c.onActionProviderVisibilityChanged(b);
        }
    }
    
    public View onCreateActionView(final MenuItem menuItem) {
        return this.a.onCreateActionView(menuItem);
    }
    
    public boolean overridesItemVisibility() {
        return this.a.overridesItemVisibility();
    }
    
    public void refreshVisibility() {
        this.a.refreshVisibility();
    }
    
    public void setVisibilityListener(final android.view.ActionProvider$VisibilityListener c) {
        this.c = c;
        final ActionProvider a = this.a;
        v visibilityListener;
        if (c != null) {
            visibilityListener = this;
        }
        else {
            visibilityListener = null;
        }
        a.setVisibilityListener(visibilityListener);
    }
}
