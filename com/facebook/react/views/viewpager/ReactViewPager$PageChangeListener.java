// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import java.util.List;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.PagerAdapter;
import com.facebook.react.uimanager.UIManagerModule;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.support.v4.view.ViewPager;
import com.facebook.react.uimanager.events.Event;
import android.support.v4.view.ViewPager$OnPageChangeListener;

class ReactViewPager$PageChangeListener implements ViewPager$OnPageChangeListener
{
    final /* synthetic */ ReactViewPager this$0;
    
    private ReactViewPager$PageChangeListener(final ReactViewPager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPageScrollStateChanged(final int n) {
        String s = null;
        switch (n) {
            default: {
                throw new IllegalStateException("Unsupported pageScrollState");
            }
            case 0: {
                s = "idle";
                break;
            }
            case 1: {
                s = "dragging";
                break;
            }
            case 2: {
                s = "settling";
                break;
            }
        }
        this.this$0.mEventDispatcher.dispatchEvent(new PageScrollStateChangedEvent(this.this$0.getId(), s));
    }
    
    @Override
    public void onPageScrolled(final int n, final float n2, final int n3) {
        this.this$0.mEventDispatcher.dispatchEvent(new PageScrollEvent(this.this$0.getId(), n, n2));
    }
    
    @Override
    public void onPageSelected(final int n) {
        if (!this.this$0.mIsCurrentItemFromJs) {
            this.this$0.mEventDispatcher.dispatchEvent(new PageSelectedEvent(this.this$0.getId(), n));
        }
    }
}
