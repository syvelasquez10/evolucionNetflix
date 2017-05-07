// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.widget.GridView;
import android.view.View;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class KubrickMovieDetailsFrag extends MovieDetailsFrag
{
    private static final String POS = "EXTRA_SHOW_GRID_POS";
    private int gridPosition;
    private View gridViewGroup;
    private View rootContainer;
    
    public static KubrickMovieDetailsFrag create(final String s) {
        final KubrickMovieDetailsFrag kubrickMovieDetailsFrag = new KubrickMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickMovieDetailsFrag.setArguments(arguments);
        return kubrickMovieDetailsFrag;
    }
    
    private void restoreScrollPosition() {
        if (this.gridView != null) {
            this.gridView.post((Runnable)new KubrickMovieDetailsFrag$1(this));
        }
    }
    
    private void setupClicks() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickMovieDetailsFrag$2(this));
        }
    }
    
    private void setupGridViewlayout(final View view) {
        if (this.gridViewGroup == null) {
            return;
        }
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(KubrickUtils.getDetailsPageContentWidth((Context)this.getNetflixActivity()), -1);
        layoutParams.gravity = 1;
        this.gridViewGroup.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131165480);
        this.gridViewGroup = view.findViewById(2131165683);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903200;
    }
    
    protected void getScrollPosition(final Bundle bundle) {
        if (bundle != null) {
            this.gridPosition = bundle.getInt("EXTRA_SHOW_GRID_POS", 0);
        }
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(this.getLayoutId(), (ViewGroup)null, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        this.findViews(inflate);
        this.setupGridViewlayout(inflate);
        this.setupGridView();
        this.setupClicks();
        this.initDetailsViewGroup(inflate);
        this.adapter = new KubrickSimilarsGridAdapter(this.getNetflixActivity(), this.gridView);
        ((KubrickSimilarsGridAdapter)this.adapter).setDetailsHeaderView((ViewGroup)this.detailsViewGroup);
        this.gridView.setAdapter((ListAdapter)this.adapter);
        this.getScrollPosition(bundle);
        this.setupDetailsPageParallaxScrollListener();
        return inflate;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("EXTRA_SHOW_GRID_POS", this.gridView.getFirstVisiblePosition());
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.restoreScrollPosition();
    }
}
