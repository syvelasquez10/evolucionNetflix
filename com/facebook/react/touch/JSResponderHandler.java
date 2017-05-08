// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.touch;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;

public class JSResponderHandler implements OnInterceptTouchEventListener
{
    private volatile int mCurrentJSResponder;
    private ViewParent mViewParentBlockingNativeResponder;
    
    public JSResponderHandler() {
        this.mCurrentJSResponder = -1;
    }
    
    private void maybeUnblockNativeResponder() {
        if (this.mViewParentBlockingNativeResponder != null) {
            this.mViewParentBlockingNativeResponder.requestDisallowInterceptTouchEvent(false);
            this.mViewParentBlockingNativeResponder = null;
        }
    }
    
    public void clearJSResponder() {
        this.mCurrentJSResponder = -1;
        this.maybeUnblockNativeResponder();
    }
    
    @Override
    public boolean onInterceptTouchEvent(final ViewGroup viewGroup, final MotionEvent motionEvent) {
        final int mCurrentJSResponder = this.mCurrentJSResponder;
        return mCurrentJSResponder != -1 && motionEvent.getAction() != 1 && viewGroup.getId() == mCurrentJSResponder;
    }
    
    public void setJSResponder(final int mCurrentJSResponder, final ViewParent mViewParentBlockingNativeResponder) {
        this.mCurrentJSResponder = mCurrentJSResponder;
        this.maybeUnblockNativeResponder();
        if (mViewParentBlockingNativeResponder != null) {
            mViewParentBlockingNativeResponder.requestDisallowInterceptTouchEvent(true);
            this.mViewParentBlockingNativeResponder = mViewParentBlockingNativeResponder;
        }
    }
}
