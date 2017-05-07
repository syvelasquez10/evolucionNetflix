// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.makeramen.RoundedImageView;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import java.util.List;
import android.widget.GridView;
import android.app.Activity;
import android.widget.BaseAdapter;

public class SearchSimilarItemsGridViewAdapter extends BaseAdapter
{
    private static final String TAG = "SearchSimilarItemsGridViewAdapter";
    private final Activity activity;
    private final boolean clipToCompleteRows;
    private final GridView gridView;
    private int imgHeight;
    private final int numGridCols;
    private List<SearchVideo> similarMovies;
    private Trackable trackable;
    
    public SearchSimilarItemsGridViewAdapter(final Activity activity, final GridView gridView, final boolean clipToCompleteRows) {
        this.similarMovies = new ArrayList<SearchVideo>();
        this.activity = activity;
        this.gridView = gridView;
        this.clipToCompleteRows = clipToCompleteRows;
        gridView.setNumColumns(this.numGridCols = SearchUtils.getNumVideoGridCols((Context)activity));
        gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SearchSimilarItemsGridViewAdapter$1(this));
    }
    
    private int clipCountToCompleteRows(final int n) {
        return n / this.numGridCols * this.numGridCols;
    }
    
    public int getCount() {
        if (this.clipToCompleteRows) {
            return this.clipCountToCompleteRows(this.similarMovies.size());
        }
        return this.similarMovies.size();
    }
    
    public Video getItem(final int n) {
        return this.similarMovies.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        boolean isHorizontal = true;
        if (view == null) {
            view = (View)new VideoView((Context)this.activity);
            final int dimensionPixelOffset = this.activity.getResources().getDimensionPixelOffset(2131296381);
            ((VideoView)view).setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            ((VideoView)view).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.imgHeight));
            ((VideoView)view).setAdjustViewBounds(true);
            ((RoundedImageView)view).setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        final VideoView videoView = (VideoView)view;
        if (SearchUtils.shouldShowVerticalBoxArt()) {
            isHorizontal = false;
        }
        videoView.setIsHorizontal(isHorizontal);
        videoView.update(this.getItem(n), this.trackable, n, false, false);
        return view;
    }
    
    public void refreshImagesIfNecessary() {
        for (int i = 0; i < this.gridView.getChildCount(); ++i) {
            ((VideoView)this.gridView.getChildAt(i)).refreshImageIfNecessary();
        }
    }
    
    public void setData(final List<SearchVideo> similarMovies, final Trackable trackable) {
        this.similarMovies = similarMovies;
        this.trackable = trackable;
        this.notifyDataSetChanged();
    }
}
