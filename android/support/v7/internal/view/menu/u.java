// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.view.MenuItem;
import android.view.ActionProvider;
import android.content.Context;
import android.view.ActionProvider$VisibilityListener;

class u extends p implements ActionProvider$VisibilityListener
{
    android.support.v4.view.ActionProvider$VisibilityListener c;
    final /* synthetic */ t d;
    
    public u(final t d, final Context context, final android.view.ActionProvider actionProvider) {
        this.d = d;
        super(d, context, actionProvider);
    }
    
    public boolean isVisible() {
        return this.a.isVisible();
    }
    
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
    
    public void setVisibilityListener(final android.support.v4.view.ActionProvider$VisibilityListener c) {
        this.c = c;
        final android.view.ActionProvider a = this.a;
        Object visibilityListener;
        if (c != null) {
            visibilityListener = this;
        }
        else {
            visibilityListener = null;
        }
        a.setVisibilityListener((ActionProvider$VisibilityListener)visibilityListener);
    }
}
