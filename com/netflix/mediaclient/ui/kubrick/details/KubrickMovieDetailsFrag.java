// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.MdxUtils;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver$Callback;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class KubrickMovieDetailsFrag extends MovieDetailsFrag implements DialogMessageReceiver$Callback
{
    private static final String TAG = "KubrickMovieDetailsFrag";
    private final DialogMessageReceiver dialogMessageReceiver;
    
    public KubrickMovieDetailsFrag() {
        this.dialogMessageReceiver = new DialogMessageReceiver(this);
    }
    
    public static KubrickMovieDetailsFrag create(final String s) {
        final KubrickMovieDetailsFrag kubrickMovieDetailsFrag = new KubrickMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickMovieDetailsFrag.setArguments(arguments);
        return kubrickMovieDetailsFrag;
    }
    
    @Override
    public void handleUserRatingChange(final String s, final float n) {
        if (Log.isLoggable()) {
            Log.v("KubrickMovieDetailsFrag", "Change user settings for received video id: " + s + " to rating: " + n);
        }
        if (s == null) {
            if (Log.isLoggable()) {
                Log.v("KubrickMovieDetailsFrag", "Can't set rating receivedVideoId is null");
            }
        }
        else {
            if (this.getServiceManager() != null) {
                this.getServiceManager().getBrowse().setVideoRating(s, VideoType.MOVIE, (int)n, PlayContext.EMPTY_CONTEXT.getTrackId(), new MdxUtils$SetVideoRatingCallback((NetflixActivity)this.getActivity(), n));
                return;
            }
            if (Log.isLoggable()) {
                Log.v("KubrickMovieDetailsFrag", "Can't set rating because service man is null");
            }
        }
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    public void onStart() {
        super.onStart();
        MdxUtils.registerReceiver(this.getActivity(), this.dialogMessageReceiver);
    }
    
    public void onStop() {
        super.onStop();
        MdxUtils.unregisterReceiver(this.getActivity(), this.dialogMessageReceiver);
    }
    
    @Override
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131361802);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(this, this.recyclerView, true, this.numColumns)).addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296461), this.numColumns));
    }
}
