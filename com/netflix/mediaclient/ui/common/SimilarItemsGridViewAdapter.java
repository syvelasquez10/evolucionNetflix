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
import com.netflix.mediaclient.util.UiUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import android.widget.GridView;
import android.app.Activity;
import android.widget.BaseAdapter;

public class SimilarItemsGridViewAdapter extends BaseAdapter
{
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
        gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SimilarItemsGridViewAdapter$1(this));
    }
    
    private int clipCountToCompleteRows(final int n) {
        return n / this.numGridCols * this.numGridCols;
    }
    
    private int getNumGridCols() {
        return UiUtils.computeNumItemsPerPage(DeviceUtils.getBasicScreenOrientation((Context)this.activity), DeviceUtils.getScreenSizeCategory((Context)this.activity));
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
        if (view == null) {
            view = (View)new VideoView((Context)this.activity);
            final int dimensionPixelOffset = this.activity.getResources().getDimensionPixelOffset(2131361897);
            if (n % this.numGridCols == 0) {
                ((VideoView)view).setPadding(0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            }
            else if ((n + 1) % this.numGridCols == 0) {
                ((VideoView)view).setPadding(dimensionPixelOffset, dimensionPixelOffset, 0, dimensionPixelOffset);
            }
            else {
                ((VideoView)view).setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            }
            ((VideoView)view).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.imgHeight));
            ((VideoView)view).setAdjustViewBounds(true);
            ((RoundedImageView)view).setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        ((VideoView)view).update(this.getItem(n), this.trackable, n, false);
        return view;
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
