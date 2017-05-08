// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v4.widget.DrawerLayout$DrawerListener;

public class ActionBarDrawerToggle implements DrawerLayout$DrawerListener
{
    private final ActionBarDrawerToggle$Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    boolean mDrawerIndicatorEnabled;
    private final int mOpenDrawerContentDescRes;
    private DrawerArrowDrawable mSlider;
    
    private void setPosition(final float progress) {
        if (progress == 1.0f) {
            this.mSlider.setVerticalMirror(true);
        }
        else if (progress == 0.0f) {
            this.mSlider.setVerticalMirror(false);
        }
        this.mSlider.setProgress(progress);
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
        this.setPosition(Math.min(1.0f, Math.max(0.0f, n)));
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
    
    void setActionBarDescription(final int actionBarDescription) {
        this.mActivityImpl.setActionBarDescription(actionBarDescription);
    }
}
