// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.ui.details.DummyEpisodeDetails;
import java.util.Collection;
import android.support.v7.widget.RecyclerView;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import com.netflix.mediaclient.ui.details.IEpisodeView;
import android.view.ViewGroup;
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
    private static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM_ONE = 1;
    public static final int TYPE_ITEM_TWO = 2;
    protected final List<Video> data;
    private final List<View> headerViews;
    private boolean isSingleChoiceMode;
    private int itemCheckedPosition;
    private RecyclerViewHeaderAdapter$OnItemClickListener itemClickListener;
    private int itemContentType;
    private Checkable lastCheckedView;
    private Trackable trackable;
    private RecyclerViewHeaderAdapter$IViewCreator viewCreator;
    
    public RecyclerViewHeaderAdapter(final RecyclerViewHeaderAdapter$IViewCreator viewCreator) {
        this.headerViews = new ArrayList<View>();
        this.data = new ArrayList<Video>();
        this.itemContentType = 2;
        this.lastCheckedView = null;
        this.isSingleChoiceMode = true;
        this.itemCheckedPosition = -1;
        this.viewCreator = viewCreator;
    }
    
    private boolean isPositionHeader(final int n) {
        return n < this.getHeaderViewsCount();
    }
    
    private void onBindHeaderView(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        this.removeViewHolderFromViewHierarchy(recyclerView$ViewHolder, n);
        ((ViewGroup)recyclerView$ViewHolder.itemView).addView((View)this.headerViews.get(n));
    }
    
    private void onBindItemView(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        final Video item = this.getItem(n);
        if (item != null) {
            if (this.getActiveLoadingView(n) != null) {
                ((RecyclerViewHeaderAdapter$VideoViewHolder)recyclerView$ViewHolder).setLoadingViewVisibility(0);
                return;
            }
            ((RecyclerViewHeaderAdapter$VideoViewHolder)recyclerView$ViewHolder).setLoadingViewVisibility(8);
            final View child = ((ViewGroup)recyclerView$ViewHolder.itemView).getChildAt(0);
            if (child instanceof IEpisodeView) {
                ((IEpisodeView<Video>)child).update(item);
                return;
            }
            if (child instanceof VideoViewGroup$IVideoView) {
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
            this.headerViews.add(view);
        }
    }
    
    protected View getActiveLoadingView(final int n) {
        return null;
    }
    
    public int getCheckedItemPosition() {
        return this.itemCheckedPosition;
    }
    
    public int getDataSetCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size() - this.getHeaderViewsCount();
    }
    
    public int getHeaderViewsCount() {
        return this.headerViews.size();
    }
    
    public Video getItem(final int n) {
        if (n < this.data.size()) {
            return this.data.get(n);
        }
        return null;
    }
    
    @Override
    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
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
        if (this.getItemViewType(n) == 0) {
            this.onBindHeaderView(recyclerView$ViewHolder, n);
        }
        else if (this.getItemViewType(n) == this.itemContentType) {
            this.onBindItemView(recyclerView$ViewHolder, n);
        }
        this.onPostItemViewBind(n);
    }
    
    @Override
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
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
    
    public boolean removeHeaderView(final View view) {
        return this.headerViews.remove(view);
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
        this.data.clear();
        for (int i = 0; i < this.getHeaderViewsCount(); ++i) {
            this.data.add(new DummyEpisodeDetails(i));
        }
        this.data.addAll(collection);
        this.notifyDataSetChanged();
    }
    
    public void setItems(final Collection<? extends Video> items, final int itemContentType, final RecyclerViewHeaderAdapter$IViewCreator viewCreator) {
        this.setViewCreator(viewCreator);
        this.setItemContentType(itemContentType);
        this.setItems(items);
    }
    
    public void setOnItemClickListener(final RecyclerViewHeaderAdapter$OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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
    
    public void updateItems(final Collection<? extends Video> collection, int i, final int n) {
        if (i == 0) {
            this.data.clear();
        }
        if (this.data.size() == 0) {
            for (i = 0; i < this.getHeaderViewsCount(); ++i) {
                this.data.add(new DummyEpisodeDetails(i));
            }
        }
        this.data.addAll(collection);
        this.notifyDataSetChanged();
    }
}
