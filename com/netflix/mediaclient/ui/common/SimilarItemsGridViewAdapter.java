// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import android.widget.GridView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;

public class SimilarItemsGridViewAdapter extends BaseAdapter
{
    private static final String TAG = "SimilarItemsGridViewAdapter";
    private final NetflixActivity activity;
    private final boolean clipToCompleteRows;
    protected final GridView gridView;
    private int imgHeight;
    private final int numGridCols;
    private List<Video> similarMovies;
    private Trackable trackable;
    
    public SimilarItemsGridViewAdapter(final NetflixActivity activity, final GridView gridView, final boolean clipToCompleteRows) {
        this.similarMovies = new ArrayList<Video>();
        this.activity = activity;
        this.gridView = gridView;
        this.clipToCompleteRows = clipToCompleteRows;
        gridView.setNumColumns(this.numGridCols = this.getNumGridCols());
        gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SimilarItemsGridViewAdapter$1(this));
    }
    
    private int calculateImageHeight(final int n) {
        return (int)(n / this.numGridCols * 1.43f + 0.5f);
    }
    
    private int clipCountToCompleteRows(final int n) {
        return n / this.numGridCols * this.numGridCols;
    }
    
    protected VideoView createView(final int n) {
        final VideoView videoView = new VideoView((Context)this.activity);
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        videoView.setTag(2131165256, (Object)true);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        return videoView;
    }
    
    public int getCount() {
        if (this.clipToCompleteRows) {
            return this.clipCountToCompleteRows(this.similarMovies.size());
        }
        return this.similarMovies.size();
    }
    
    protected int getImageHeight() {
        return this.imgHeight;
    }
    
    public Video getItem(final int n) {
        return this.similarMovies.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    protected int getNumGridCols() {
        return LomoConfig.computeStandardNumVideosPerPage(this.activity, false);
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        if (view == null) {
            view = (View)this.createView(n);
        }
        ((VideoView)view).update(this.getItem(n), this.trackable, n, false);
        this.setPadding(view, n);
        return view;
    }
    
    public void refreshImagesIfNecessary() {
        for (int i = 0; i < this.gridView.getChildCount(); ++i) {
            final View child = this.gridView.getChildAt(i);
            if (child instanceof VideoView) {
                ((VideoView)child).refreshImageIfNecessary();
            }
        }
    }
    
    public void setData(final List<Video> similarMovies, final Trackable trackable) {
        this.similarMovies = similarMovies;
        this.trackable = trackable;
        this.notifyDataSetChanged();
    }
    
    protected void setPadding(final View view, final int n) {
        ViewUtils.applyUniformPaddingToGridItem(view, this.activity.getResources().getDimensionPixelOffset(2131361878), this.numGridCols, n);
    }
}
