// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout$DrawerListener;

@Deprecated
public class ActionBarDrawerToggle implements DrawerLayout$DrawerListener
{
    private static final ActionBarDrawerToggle$ActionBarDrawerToggleImpl IMPL;
    private final Activity mActivity;
    private final ActionBarDrawerToggle$Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private boolean mDrawerIndicatorEnabled;
    private final int mOpenDrawerContentDescRes;
    private Object mSetIndicatorInfo;
    private ActionBarDrawerToggle$SlideDrawable mSlider;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 18) {
            IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplJellybeanMR2(null);
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplHC(null);
            return;
        }
        IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplBase(null);
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, float position) {
        final float position2 = this.mSlider.getPosition();
        if (position > 0.5f) {
            position = Math.max(position2, Math.max(0.0f, position - 0.5f) * 2.0f);
        }
        else {
            position = Math.min(position2, position * 2.0f);
        }
        this.mSlider.setPosition(position);
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
    
    void setActionBarDescription(final int actionBarDescription) {
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarDescription(actionBarDescription);
            return;
        }
        this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, actionBarDescription);
    }
}
