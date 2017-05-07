// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import java.io.Serializable;
import java.util.Collection;
import android.support.v7.widget.RecyclerView;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import com.netflix.mediaclient.ui.details.IEpisodeView;
import android.view.ViewGroup;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.widget.Checkable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;

public abstract class RecyclerViewHeaderAdapter extends RecyclerView$Adapter<RecyclerView$ViewHolder>
{
    private static final String TAG = "RecyclerViewHeaderAdapter";
    private static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM_ONE = 1;
    public static final int TYPE_ITEM_TWO = 2;
    protected final List<Video> data;
    private final List<View> headerViews;
    private boolean isSingleChoiceMode;
    private int itemCheckedPosition;
    private int itemContentType;
    private Checkable lastCheckedView;
    private int prevCount;
    private int prevHeaderCount;
    private Trackable trackable;
    private RecyclerViewHeaderAdapter$IViewCreator viewCreator;
    
    public RecyclerViewHeaderAdapter(final RecyclerViewHeaderAdapter$IViewCreator viewCreator) {
        this.headerViews = new ArrayList<View>();
        this.data = new ArrayList<Video>();
        this.itemContentType = 2;
        this.lastCheckedView = null;
        this.isSingleChoiceMode = true;
        this.itemCheckedPosition = -1;
        this.prevCount = -1;
        this.prevHeaderCount = -1;
        this.viewCreator = viewCreator;
    }
    
    private static View createFrameView(final Context context) {
        final FrameLayout frameLayout = new FrameLayout(context);
        ((View)frameLayout).setLayoutParams(new ViewGroup$LayoutParams(-1, -2));
        return (View)frameLayout;
    }
    
    private boolean isPositionHeader(final int n) {
        return n < this.getHeaderViewsCount();
    }
    
    private void onBindHeaderView(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "onBindHeaderView - re-adding header view");
        }
        this.removeViewHolderFromViewHierarchy(recyclerView$ViewHolder, n);
        ((ViewGroup)recyclerView$ViewHolder.itemView).addView((View)this.headerViews.get(n));
    }
    
    private void onBindItemView(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "onBindItemView - position: " + n);
        }
        final Video item = this.getItem(n);
        if (item != null) {
            final View child = ((ViewGroup)recyclerView$ViewHolder.itemView).getChildAt(0);
            if (child instanceof IEpisodeView) {
                if (Log.isLoggable()) {
                    Log.v("RecyclerViewHeaderAdapter", "onBindItemView - updating for episode, position: " + n);
                }
                ((IEpisodeView<Video>)child).update(item);
                return;
            }
            if (child instanceof VideoViewGroup$IVideoView) {
                if (Log.isLoggable()) {
                    Log.v("RecyclerViewHeaderAdapter", "onBindItemView - updating for video view, position: " + n);
                }
                ((VideoViewGroup$IVideoView<Video>)child).update(item, this.getTrackable(), n, false, false);
            }
        }
    }
    
    private void removeViewHolderFromViewHierarchy(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        ((ViewGroup)recyclerView$ViewHolder.itemView).removeAllViews();
        final ViewParent parent = this.headerViews.get(n).getParent();
        if (parent != null) {
            ((ViewGroup)parent).removeView((View)this.headerViews.get(n));
        }
    }
    
    public void addHeaderView(final View view) {
        if (!this.headerViews.contains(view)) {
            if (Log.isLoggable()) {
                Log.v("RecyclerViewHeaderAdapter", "Adding header view: " + view);
            }
            this.headerViews.add(view);
        }
    }
    
    public void clearData() {
        this.data.clear();
        this.notifyDataSetChanged();
    }
    
    public int getCheckedItemPosition() {
        return this.itemCheckedPosition;
    }
    
    public int getHeaderViewsCount() {
        final int size = this.headerViews.size();
        if (Log.isLoggable() && this.prevHeaderCount != size) {
            this.prevHeaderCount = size;
            Log.v("RecyclerViewHeaderAdapter", "getHeaderViewsCount changed to: " + size);
        }
        return size;
    }
    
    public Video getItem(final int n) {
        if (n < this.data.size()) {
            return this.data.get(n);
        }
        return null;
    }
    
    @Override
    public int getItemCount() {
        final int prevCount = this.data.size() + this.getHeaderViewsCount();
        if (Log.isLoggable() && this.prevCount != prevCount) {
            this.prevCount = prevCount;
            Log.v("RecyclerViewHeaderAdapter", "getItemCount() result changed to: " + prevCount);
        }
        return prevCount;
    }
    
    protected int getItemCountExcludingHeaders() {
        return this.data.size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        if (this.isPositionHeader(n)) {
            return 0;
        }
        return this.itemContentType;
    }
    
    protected Trackable getTrackable() {
        return this.trackable;
    }
    
    public boolean isViewHeader(final View view, final RecyclerView recyclerView) {
        return view != null && recyclerView != null && recyclerView.getChildPosition(view) < this.getHeaderViewsCount();
    }
    
    @Override
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        final int itemViewType = this.getItemViewType(n);
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "onBindViewHolder, viewType: " + itemViewType);
        }
        if (itemViewType == 0) {
            this.onBindHeaderView(recyclerView$ViewHolder, n);
        }
        else if (itemViewType == this.itemContentType) {
            this.onBindItemView(recyclerView$ViewHolder, n - this.getHeaderViewsCount());
        }
        this.onPostItemViewBind(n);
    }
    
    @Override
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "onCreateViewHolder, viewType: " + n);
        }
        if (n == this.itemContentType) {
            return new RecyclerViewHeaderAdapter$VideoViewHolder(this, this.viewCreator.createItemView(), viewGroup.getContext());
        }
        if (n == 0) {
            return new RecyclerViewHeaderAdapter$VideoViewHolder(this, null, viewGroup.getContext());
        }
        throw new IllegalArgumentException("No matching type " + n);
    }
    
    protected void onPostItemViewBind(final int n) {
    }
    
    public void setItemChecked(final int n, final RecyclerView recyclerView) {
        if (n >= 0 && this.isSingleChoiceMode && recyclerView != null && recyclerView.getChildCount() > n) {
            final ViewGroup viewGroup = (ViewGroup)recyclerView.getChildAt(n);
            if (viewGroup != null && viewGroup.getChildCount() > 0) {
                final View child = viewGroup.getChildAt(0);
                if (child instanceof Checkable) {
                    this.setSingleChoiceModeState(child, n);
                }
            }
        }
    }
    
    public void setItemContentType(final int itemContentType) {
        this.itemContentType = itemContentType;
    }
    
    public void setItems(final Collection<? extends Video> collection) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("setItems, newItems: ");
            Serializable value;
            if (collection == null) {
                value = "null";
            }
            else {
                value = collection.size();
            }
            Log.v("RecyclerViewHeaderAdapter", append.append(value).toString());
        }
        this.data.clear();
        this.data.addAll(collection);
        this.notifyDataSetChanged();
    }
    
    public void setItems(final Collection<? extends Video> items, final int itemContentType, final RecyclerViewHeaderAdapter$IViewCreator viewCreator) {
        this.setViewCreator(viewCreator);
        this.setItemContentType(itemContentType);
        this.setItems(items);
    }
    
    public void setSingleChoiceMode(final boolean isSingleChoiceMode) {
        this.isSingleChoiceMode = isSingleChoiceMode;
    }
    
    void setSingleChoiceModeState(final View view, final int itemCheckedPosition) {
        if (this.isSingleChoiceMode && view instanceof Checkable) {
            final Checkable lastCheckedView = (Checkable)view;
            if (lastCheckedView.isChecked()) {
                lastCheckedView.setChecked(false);
            }
            else {
                lastCheckedView.setChecked(true);
                if (this.lastCheckedView != null && lastCheckedView != this.lastCheckedView) {
                    this.lastCheckedView.setChecked(false);
                }
                this.lastCheckedView = lastCheckedView;
            }
            if (lastCheckedView.isChecked()) {
                this.itemCheckedPosition = itemCheckedPosition;
            }
        }
    }
    
    public void setTrackable(final Trackable trackable) {
        this.trackable = trackable;
    }
    
    public void setViewCreator(final RecyclerViewHeaderAdapter$IViewCreator viewCreator) {
        this.viewCreator = viewCreator;
    }
    
    protected void updateItems(final Collection<? extends Video> collection, final int n) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("updateItems, newItems: ");
            Serializable value;
            if (collection == null) {
                value = "null";
            }
            else {
                value = collection.size();
            }
            Log.v("RecyclerViewHeaderAdapter", append.append(value).append(", start index: ").append(n).toString());
        }
        if (n == 0) {
            this.data.clear();
        }
        this.data.addAll(collection);
        this.notifyDataSetChanged();
    }
}
