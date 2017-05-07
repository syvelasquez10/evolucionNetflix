// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Region;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.view.MenuItem;
import android.view.View;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;

public class ActionBarDrawerToggle implements DrawerListener
{
    private static final int ID_HOME = 16908332;
    private static final ActionBarDrawerToggleImpl IMPL;
    private final Activity mActivity;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private final int mOpenDrawerContentDescRes;
    private Object mSetIndicatorInfo;
    private final SlideDrawable mSlider;
    private Drawable mThemeImage;
    
    static {
        IMPL = (ActionBarDrawerToggleImpl)new ActionBarDrawerToggleImplHC();
    }
    
    public ActionBarDrawerToggle(final Activity mActivity, final DrawerLayout mDrawerLayout, final int mDrawerImageResource, final int mOpenDrawerContentDescRes, final int mCloseDrawerContentDescRes) {
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = mActivity;
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerImageResource = mDrawerImageResource;
        this.mOpenDrawerContentDescRes = mOpenDrawerContentDescRes;
        this.mCloseDrawerContentDescRes = mCloseDrawerContentDescRes;
        this.mThemeImage = ActionBarDrawerToggle.IMPL.getThemeUpIndicator(mActivity);
        this.mDrawerImage = mActivity.getResources().getDrawable(mDrawerImageResource);
        (this.mSlider = new SlideDrawable(this.mDrawerImage)).setOffsetBy(0.33333334f);
    }
    
    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.mThemeImage = ActionBarDrawerToggle.IMPL.getThemeUpIndicator(this.mActivity);
        this.mDrawerImage = this.mActivity.getResources().getDrawable(this.mDrawerImageResource);
        this.syncState();
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.mSlider.setOffset(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, this.mCloseDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.mSlider.setOffset(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, this.mOpenDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, float offset) {
        final float offset2 = this.mSlider.getOffset();
        if (offset > 0.5f) {
            offset = Math.max(offset2, Math.max(0.0f, offset - 0.5f) * 2.0f);
        }
        else {
            offset = Math.min(offset2, offset * 2.0f);
        }
        this.mSlider.setOffset(offset);
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
            if (this.mDrawerLayout.isDrawerVisible(8388611)) {
                this.mDrawerLayout.closeDrawer(8388611);
            }
            else {
                this.mDrawerLayout.openDrawer(8388611);
            }
        }
        return false;
    }
    
    public void setDrawerIndicatorEnabled(final boolean mDrawerIndicatorEnabled) {
        if (mDrawerIndicatorEnabled != this.mDrawerIndicatorEnabled) {
            if (mDrawerIndicatorEnabled) {
                final ActionBarDrawerToggleImpl impl = ActionBarDrawerToggle.IMPL;
                final Object mSetIndicatorInfo = this.mSetIndicatorInfo;
                final Activity mActivity = this.mActivity;
                final SlideDrawable mSlider = this.mSlider;
                int n;
                if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                    n = this.mOpenDrawerContentDescRes;
                }
                else {
                    n = this.mCloseDrawerContentDescRes;
                }
                this.mSetIndicatorInfo = impl.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, mSlider, n);
            }
            else {
                this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, this.mThemeImage, 0);
            }
            this.mDrawerIndicatorEnabled = mDrawerIndicatorEnabled;
        }
    }
    
    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mSlider.setOffset(1.0f);
        }
        else {
            this.mSlider.setOffset(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            final ActionBarDrawerToggleImpl impl = ActionBarDrawerToggle.IMPL;
            final Object mSetIndicatorInfo = this.mSetIndicatorInfo;
            final Activity mActivity = this.mActivity;
            final SlideDrawable mSlider = this.mSlider;
            int n;
            if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                n = this.mOpenDrawerContentDescRes;
            }
            else {
                n = this.mCloseDrawerContentDescRes;
            }
            this.mSetIndicatorInfo = impl.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, mSlider, n);
        }
    }
    
    private interface ActionBarDrawerToggleImpl
    {
        Drawable getThemeUpIndicator(final Activity p0);
        
        Object setActionBarDescription(final Object p0, final Activity p1, final int p2);
        
        Object setActionBarUpIndicator(final Object p0, final Activity p1, final Drawable p2, final int p3);
    }
    
    private static class ActionBarDrawerToggleImplHC implements ActionBarDrawerToggleImpl
    {
        @Override
        public Drawable getThemeUpIndicator(final Activity activity) {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(activity);
        }
        
        @Override
        public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
            return ActionBarDrawerToggleHoneycomb.setActionBarDescription(o, activity, n);
        }
        
        @Override
        public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
            return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(o, activity, drawable, n);
        }
    }
    
    private static class SlideDrawable extends Drawable implements Drawable$Callback
    {
        private float mOffset;
        private float mOffsetBy;
        private final Rect mTmpRect;
        private final Drawable mWrapped;
        
        public SlideDrawable(final Drawable mWrapped) {
            this.mTmpRect = new Rect();
            this.mWrapped = mWrapped;
        }
        
        public void clearColorFilter() {
            this.mWrapped.clearColorFilter();
        }
        
        public void draw(final Canvas canvas) {
            this.mWrapped.copyBounds(this.mTmpRect);
            canvas.save();
            canvas.translate(this.mOffsetBy * this.mTmpRect.width() * -this.mOffset, 0.0f);
            this.mWrapped.draw(canvas);
            canvas.restore();
        }
        
        public int getChangingConfigurations() {
            return this.mWrapped.getChangingConfigurations();
        }
        
        public Drawable$ConstantState getConstantState() {
            return super.getConstantState();
        }
        
        public Drawable getCurrent() {
            return this.mWrapped.getCurrent();
        }
        
        public int getIntrinsicHeight() {
            return this.mWrapped.getIntrinsicHeight();
        }
        
        public int getIntrinsicWidth() {
            return this.mWrapped.getIntrinsicWidth();
        }
        
        public int getMinimumHeight() {
            return this.mWrapped.getMinimumHeight();
        }
        
        public int getMinimumWidth() {
            return this.mWrapped.getMinimumWidth();
        }
        
        public float getOffset() {
            return this.mOffset;
        }
        
        public int getOpacity() {
            return this.mWrapped.getOpacity();
        }
        
        public boolean getPadding(final Rect rect) {
            return this.mWrapped.getPadding(rect);
        }
        
        public int[] getState() {
            return this.mWrapped.getState();
        }
        
        public Region getTransparentRegion() {
            return this.mWrapped.getTransparentRegion();
        }
        
        public void invalidateDrawable(final Drawable drawable) {
            if (drawable == this.mWrapped) {
                this.invalidateSelf();
            }
        }
        
        public boolean isStateful() {
            return this.mWrapped.isStateful();
        }
        
        protected void onBoundsChange(final Rect bounds) {
            super.onBoundsChange(bounds);
            this.mWrapped.setBounds(bounds);
        }
        
        protected boolean onStateChange(final int[] state) {
            this.mWrapped.setState(state);
            return super.onStateChange(state);
        }
        
        public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
            if (drawable == this.mWrapped) {
                this.scheduleSelf(runnable, n);
            }
        }
        
        public void setAlpha(final int alpha) {
            this.mWrapped.setAlpha(alpha);
        }
        
        public void setChangingConfigurations(final int changingConfigurations) {
            this.mWrapped.setChangingConfigurations(changingConfigurations);
        }
        
        public void setColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
            this.mWrapped.setColorFilter(n, porterDuff$Mode);
        }
        
        public void setColorFilter(final ColorFilter colorFilter) {
            this.mWrapped.setColorFilter(colorFilter);
        }
        
        public void setDither(final boolean dither) {
            this.mWrapped.setDither(dither);
        }
        
        public void setFilterBitmap(final boolean filterBitmap) {
            this.mWrapped.setFilterBitmap(filterBitmap);
        }
        
        public void setOffset(final float mOffset) {
            this.mOffset = mOffset;
            this.invalidateSelf();
        }
        
        public void setOffsetBy(final float mOffsetBy) {
            this.mOffsetBy = mOffsetBy;
            this.invalidateSelf();
        }
        
        public boolean setState(final int[] state) {
            return this.mWrapped.setState(state);
        }
        
        public boolean setVisible(final boolean b, final boolean b2) {
            return super.setVisible(b, b2);
        }
        
        public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
            if (drawable == this.mWrapped) {
                this.unscheduleSelf(runnable);
            }
        }
    }
}
