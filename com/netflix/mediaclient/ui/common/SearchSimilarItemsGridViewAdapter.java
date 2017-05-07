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
import com.netflix.mediaclient.servicemgr.model.Video;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
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
        gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                final GridView access$000 = SearchSimilarItemsGridViewAdapter.this.gridView;
                final int n = access$000.getWidth() - access$000.getPaddingLeft() - access$000.getPaddingRight();
                Log.v("SearchSimilarItemsGridViewAdapter", "View dimens: " + n + ", " + access$000.getHeight());
                SearchSimilarItemsGridViewAdapter.this.imgHeight = (int)(n / SearchSimilarItemsGridViewAdapter.this.numGridCols * SearchUtils.getVideoImageAspectRatio() + 0.5);
                Log.v("SearchSimilarItemsGridViewAdapter", "imgHeight: " + SearchSimilarItemsGridViewAdapter.this.imgHeight);
                ViewUtils.removeGlobalLayoutListener((View)access$000, (ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        });
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
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        boolean isHorizontal = true;
        Object o = view;
        if (view == null) {
            o = new VideoView((Context)this.activity);
            final int dimensionPixelOffset = this.activity.getResources().getDimensionPixelOffset(2131361897);
            ((VideoView)o).setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            ((VideoView)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.imgHeight));
            ((VideoView)o).setAdjustViewBounds(true);
            ((RoundedImageView)o).setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        final VideoView videoView = (VideoView)o;
        if (SearchUtils.shouldShowVerticalBoxArt()) {
            isHorizontal = false;
        }
        videoView.setIsHorizontal(isHorizontal);
        videoView.update(this.getItem(n), this.trackable, n, false);
        return (View)o;
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
