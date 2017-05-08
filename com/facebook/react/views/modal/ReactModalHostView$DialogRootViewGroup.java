// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import android.view.MotionEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.view.ViewGroup;
import android.content.Context;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.views.view.ReactViewGroup;

class ReactModalHostView$DialogRootViewGroup extends ReactViewGroup implements RootView
{
    private final JSTouchDispatcher mJSTouchDispatcher;
    
    public ReactModalHostView$DialogRootViewGroup(final Context context) {
        super(context);
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
    }
    
    private EventDispatcher getEventDispatcher() {
        return ((ReactContext)this.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher();
    }
    
    @Override
    public void onChildStartedNativeGesture(final MotionEvent motionEvent) {
        this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, this.getEventDispatcher());
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.getEventDispatcher());
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    @Override
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.getChildCount() > 0) {
            ((ReactContext)this.getContext()).runOnNativeModulesQueueThread(new ReactModalHostView$DialogRootViewGroup$1(this, n, n2));
        }
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.getEventDispatcher());
        super.onTouchEvent(motionEvent);
        return true;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
    }
}
