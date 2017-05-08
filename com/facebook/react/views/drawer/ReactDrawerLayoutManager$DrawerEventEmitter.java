// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer;

import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import android.view.View;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout$DrawerListener;

public class ReactDrawerLayoutManager$DrawerEventEmitter implements DrawerLayout$DrawerListener
{
    private final DrawerLayout mDrawerLayout;
    private final EventDispatcher mEventDispatcher;
    
    public ReactDrawerLayoutManager$DrawerEventEmitter(final DrawerLayout mDrawerLayout, final EventDispatcher mEventDispatcher) {
        this.mDrawerLayout = mDrawerLayout;
        this.mEventDispatcher = mEventDispatcher;
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.mEventDispatcher.dispatchEvent(new DrawerClosedEvent(this.mDrawerLayout.getId()));
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.mEventDispatcher.dispatchEvent(new DrawerOpenedEvent(this.mDrawerLayout.getId()));
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
        this.mEventDispatcher.dispatchEvent(new DrawerSlideEvent(this.mDrawerLayout.getId(), n));
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
        this.mEventDispatcher.dispatchEvent(new DrawerStateChangedEvent(this.mDrawerLayout.getId(), n));
    }
}
