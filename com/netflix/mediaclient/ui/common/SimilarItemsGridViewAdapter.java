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
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;
import android.util.SparseIntArray;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import android.widget.GridView;
import android.app.Activity;
import android.widget.BaseAdapter;

public class SimilarItemsGridViewAdapter extends BaseAdapter
{
    private static final int MIN_NUMBER_SIMS_TO_FETCH = 10;
    private static final String TAG = "SimilarItemsGridViewAdapter";
    private final Activity activity;
    private final boolean clipToCompleteRows;
    private final GridView gridView;
    private int imgHeight;
    private final int numGridCols;
    private List<Video> similarMovies;
    private Trackable trackable;
    
    public SimilarItemsGridViewAdapter(final Activity activity, final GridView gridView, final boolean clipToCompleteRows) {
        this.similarMovies = new ArrayList<Video>();
        this.activity = activity;
        this.gridView = gridView;
        this.clipToCompleteRows = clipToCompleteRows;
        gridView.setNumColumns(this.numGridCols = this.getNumGridCols());
        gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                final GridView access$000 = SimilarItemsGridViewAdapter.this.gridView;
                final int n = access$000.getWidth() - access$000.getPaddingLeft() - access$000.getPaddingRight();
                Log.v("SimilarItemsGridViewAdapter", "View dimens: " + n + ", " + access$000.getHeight());
                SimilarItemsGridViewAdapter.this.imgHeight = (int)(n / SimilarItemsGridViewAdapter.this.numGridCols * 1.43f + 0.5f);
                Log.v("SimilarItemsGridViewAdapter", "imgHeight: " + SimilarItemsGridViewAdapter.this.imgHeight);
                ViewUtils.removeGlobalLayoutListener((View)access$000, (ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        });
    }
    
    private int clipCountToCompleteRows(final int n) {
        return n / this.numGridCols * this.numGridCols;
    }
    
    private int getNumGridCols() {
        return ((SparseIntArray)PaginatedLoMoAdapter.numVideosPerPageTable.get(DeviceUtils.getBasicScreenOrientation((Context)this.activity))).get(DeviceUtils.getScreenSizeCategory((Context)this.activity));
    }
    
    public int computeNumSimsToFetch() {
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)this.activity);
        int i;
        for (i = ((SparseIntArray)PaginatedLoMoAdapter.numVideosPerPageTable.get(1)).get(screenSizeCategory) * ((SparseIntArray)PaginatedLoMoAdapter.numVideosPerPageTable.get(2)).get(screenSizeCategory); i < 10; i *= 2) {}
        final int n = i - 1;
        Log.v("SimilarItemsGridViewAdapter", "Computed num sims to fetch as: " + n);
        return n;
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
        Object o = view;
        if (view == null) {
            o = new VideoView((Context)this.activity);
            final int dimensionPixelOffset = this.activity.getResources().getDimensionPixelOffset(2131361870);
            ((VideoView)o).setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            ((VideoView)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.imgHeight));
            ((VideoView)o).setAdjustViewBounds(true);
            ((RoundedImageView)o).setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        ((VideoView)o).update(this.getItem(n), this.trackable, n, false);
        return (View)o;
    }
    
    public void refreshImagesIfNecessary() {
        for (int i = 0; i < this.gridView.getChildCount(); ++i) {
            ((VideoView)this.gridView.getChildAt(i)).refreshImageIfNecessary();
        }
    }
    
    public void setData(final List<Video> similarMovies, final Trackable trackable) {
        this.similarMovies = similarMovies;
        this.trackable = trackable;
        this.notifyDataSetChanged();
    }
}
