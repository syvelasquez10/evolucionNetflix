// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$ItemAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewBackedScrollView extends RecyclerView
{
    private boolean mSendContentSizeChangeEvents;
    
    public RecyclerViewBackedScrollView(final Context context) {
        super(context);
        this.setHasFixedSize(true);
        this.setItemAnimator(new NotAnimatedItemAnimator());
        this.setLayoutManager(new LinearLayoutManager(context));
        this.setAdapter(new RecyclerViewBackedScrollView$ReactListAdapter(this));
    }
    
    private int calculateAbsoluteOffset() {
        int n = 0;
        if (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            n = ((RecyclerViewBackedScrollView$ReactListAdapter)this.getAdapter()).getTopOffsetForItem(this.getChildViewHolder(child).getLayoutPosition()) - child.getTop();
        }
        return n;
    }
    
    private void onTotalChildrenHeightChange(final int n) {
        if (this.mSendContentSizeChangeEvents) {
            ((ReactContext)this.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ContentSizeChangeEvent(this.getId(), this.getWidth(), n));
        }
    }
    
    void addViewToAdapter(final View view, final int n) {
        ((RecyclerViewBackedScrollView$ReactListAdapter)this.getAdapter()).addView(view, n);
    }
    
    View getChildAtFromAdapter(final int n) {
        return ((RecyclerViewBackedScrollView$ReactListAdapter)this.getAdapter()).getView(n);
    }
    
    int getChildCountFromAdapter() {
        return this.getAdapter().getItemCount();
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            return true;
        }
        return false;
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        ((ReactContext)this.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(this.getId(), ScrollEventType.SCROLL, 0, this.calculateAbsoluteOffset(), this.getWidth(), ((RecyclerViewBackedScrollView$ReactListAdapter)this.getAdapter()).getTotalChildrenHeight(), this.getWidth(), this.getHeight()));
    }
    
    void removeViewFromAdapter(final int n) {
        ((RecyclerViewBackedScrollView$ReactListAdapter)this.getAdapter()).removeViewAt(n);
    }
    
    void scrollTo(int n, final int n2, final boolean b) {
        n = n2 - this.calculateAbsoluteOffset();
        if (b) {
            this.smoothScrollBy(0, n);
            return;
        }
        this.scrollBy(0, n);
    }
    
    public void setSendContentSizeChangeEvents(final boolean mSendContentSizeChangeEvents) {
        this.mSendContentSizeChangeEvents = mSendContentSizeChangeEvents;
    }
}
