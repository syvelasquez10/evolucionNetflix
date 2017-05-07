// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.view.ActionProvider$VisibilityListener;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;

class MenuItemWrapperJB extends MenuItemWrapperICS
{
    MenuItemWrapperJB(final MenuItem menuItem) {
        super(menuItem, false);
    }
    
    @Override
    ActionProviderWrapper createActionProviderWrapper(final ActionProvider actionProvider) {
        return new ActionProviderWrapperJB(actionProvider);
    }
    
    class ActionProviderWrapperJB extends ActionProviderWrapper implements VisibilityListener
    {
        ActionProvider$VisibilityListener mListener;
        
        public ActionProviderWrapperJB(final ActionProvider actionProvider) {
            super(actionProvider);
        }
        
        public boolean isVisible() {
            return this.mInner.isVisible();
        }
        
        @Override
        public void onActionProviderVisibilityChanged(final boolean b) {
            if (this.mListener != null) {
                this.mListener.onActionProviderVisibilityChanged(b);
            }
        }
        
        public View onCreateActionView(final MenuItem menuItem) {
            return this.mInner.onCreateActionView(menuItem);
        }
        
        public boolean overridesItemVisibility() {
            return this.mInner.overridesItemVisibility();
        }
        
        public void refreshVisibility() {
            this.mInner.refreshVisibility();
        }
        
        public void setVisibilityListener(final ActionProvider$VisibilityListener mListener) {
            this.mListener = mListener;
            final ActionProvider mInner = this.mInner;
            Object visibilityListener;
            if (mListener != null) {
                visibilityListener = this;
            }
            else {
                visibilityListener = null;
            }
            mInner.setVisibilityListener((ActionProvider.VisibilityListener)visibilityListener);
        }
    }
}
