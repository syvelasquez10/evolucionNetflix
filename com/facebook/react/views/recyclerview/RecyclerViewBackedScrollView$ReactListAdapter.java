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
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$ItemAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.view.View$OnLayoutChangeListener;
import android.support.v7.widget.RecyclerView$Adapter;

class RecyclerViewBackedScrollView$ReactListAdapter extends RecyclerView$Adapter<RecyclerViewBackedScrollView$ConcreteViewHolder>
{
    private final View$OnLayoutChangeListener mChildLayoutChangeListener;
    private final RecyclerViewBackedScrollView$ScrollOffsetTracker mScrollOffsetTracker;
    private final RecyclerViewBackedScrollView mScrollView;
    private int mTotalChildrenHeight;
    private final List<View> mViews;
    
    public RecyclerViewBackedScrollView$ReactListAdapter(final RecyclerViewBackedScrollView mScrollView) {
        this.mViews = new ArrayList<View>();
        this.mTotalChildrenHeight = 0;
        this.mChildLayoutChangeListener = (View$OnLayoutChangeListener)new RecyclerViewBackedScrollView$ReactListAdapter$1(this);
        this.mScrollView = mScrollView;
        this.mScrollOffsetTracker = new RecyclerViewBackedScrollView$ScrollOffsetTracker(this, null);
        this.setHasStableIds(true);
    }
    
    private void updateTotalChildrenHeight(final int n) {
        if (n != 0) {
            this.mTotalChildrenHeight += n;
            this.mScrollView.onTotalChildrenHeightChange(this.mTotalChildrenHeight);
        }
    }
    
    public void addView(final View view, final int n) {
        this.mViews.add(n, view);
        this.updateTotalChildrenHeight(view.getMeasuredHeight());
        view.addOnLayoutChangeListener(this.mChildLayoutChangeListener);
        this.notifyItemInserted(n);
    }
    
    @Override
    public int getItemCount() {
        return this.mViews.size();
    }
    
    @Override
    public long getItemId(final int n) {
        return this.mViews.get(n).getId();
    }
    
    public int getTopOffsetForItem(final int n) {
        return this.mScrollOffsetTracker.getTopOffsetForItem(n);
    }
    
    public int getTotalChildrenHeight() {
        return this.mTotalChildrenHeight;
    }
    
    public View getView(final int n) {
        return this.mViews.get(n);
    }
    
    @Override
    public void onBindViewHolder(final RecyclerViewBackedScrollView$ConcreteViewHolder recyclerViewBackedScrollView$ConcreteViewHolder, final int n) {
        final RecyclerViewBackedScrollView$RecyclableWrapperViewGroup recyclerViewBackedScrollView$RecyclableWrapperViewGroup = (RecyclerViewBackedScrollView$RecyclableWrapperViewGroup)recyclerViewBackedScrollView$ConcreteViewHolder.itemView;
        final View view = this.mViews.get(n);
        if (view.getParent() != recyclerViewBackedScrollView$RecyclableWrapperViewGroup) {
            recyclerViewBackedScrollView$RecyclableWrapperViewGroup.addView(view, 0);
        }
    }
    
    @Override
    public RecyclerViewBackedScrollView$ConcreteViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new RecyclerViewBackedScrollView$ConcreteViewHolder((View)new RecyclerViewBackedScrollView$RecyclableWrapperViewGroup(viewGroup.getContext()));
    }
    
    @Override
    public void onViewRecycled(final RecyclerViewBackedScrollView$ConcreteViewHolder recyclerViewBackedScrollView$ConcreteViewHolder) {
        super.onViewRecycled(recyclerViewBackedScrollView$ConcreteViewHolder);
        ((RecyclerViewBackedScrollView$RecyclableWrapperViewGroup)recyclerViewBackedScrollView$ConcreteViewHolder.itemView).removeAllViews();
    }
    
    public void removeViewAt(final int n) {
        final View view = this.mViews.get(n);
        if (view != null) {
            this.mViews.remove(n);
            view.removeOnLayoutChangeListener(this.mChildLayoutChangeListener);
            this.updateTotalChildrenHeight(-view.getMeasuredHeight());
            this.notifyItemRemoved(n);
        }
    }
}
