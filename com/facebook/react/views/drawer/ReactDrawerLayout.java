// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer;

import android.view.ViewGroup$LayoutParams;
import android.support.v4.widget.DrawerLayout$LayoutParams;
import android.view.View;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import android.support.v4.widget.DrawerLayout;

class ReactDrawerLayout extends DrawerLayout
{
    private int mDrawerPosition;
    private int mDrawerWidth;
    
    public ReactDrawerLayout(final ReactContext reactContext) {
        super((Context)reactContext);
        this.mDrawerPosition = 8388611;
        this.mDrawerWidth = -1;
    }
    
    void closeDrawer() {
        this.closeDrawer(this.mDrawerPosition);
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            return true;
        }
        return false;
    }
    
    void openDrawer() {
        this.openDrawer(this.mDrawerPosition);
    }
    
    void setDrawerPosition(final int mDrawerPosition) {
        this.mDrawerPosition = mDrawerPosition;
        this.setDrawerProperties();
    }
    
    void setDrawerProperties() {
        if (this.getChildCount() == 2) {
            final View child = this.getChildAt(1);
            final DrawerLayout$LayoutParams layoutParams = (DrawerLayout$LayoutParams)child.getLayoutParams();
            layoutParams.gravity = this.mDrawerPosition;
            layoutParams.width = this.mDrawerWidth;
            child.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            child.setClickable(true);
        }
    }
    
    void setDrawerWidth(final int mDrawerWidth) {
        this.mDrawerWidth = mDrawerWidth;
        this.setDrawerProperties();
    }
}
