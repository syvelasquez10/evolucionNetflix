// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.servicemgr.AddToListData;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;

public abstract class DetailsFrag<T extends VideoDetails> extends NetflixFrag implements Callback
{
    private static final String TAG = "DetailsFrag";
    private AddToListData.StateListener addToListWrapper;
    private VideoDetailsViewGroup detailsViewGroup;
    private final Callback errorCallback;
    private LoadingAndErrorWrapper leWrapper;
    private T mVideoDetails;
    private ServiceManager manager;
    protected View primaryView;
    
    public DetailsFrag() {
        this.errorCallback = new Callback() {
            @Override
            public void onRetryRequested() {
                ((Callback)DetailsFrag.this.getActivity()).onRetryRequested();
            }
        };
    }
    
    protected abstract VideoDetailsViewGroup.DetailsStringProvider getDetailsStringProvider(final T p0);
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    protected abstract String getVideoId();
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("DetailsFrag", "Creating new frag view...");
        final View inflate = layoutInflater.inflate(2130903194, (ViewGroup)null, false);
        this.detailsViewGroup = (VideoDetailsViewGroup)inflate.findViewById(2131165675);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        (this.primaryView = inflate.findViewById(2131165674)).setVerticalScrollBarEnabled(false);
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.manager != null && this.addToListWrapper != null) {
            this.manager.unregisterAddToMyListListener(this.getVideoId(), this.addToListWrapper);
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        super.onManagerReady(manager, status);
        this.manager = manager;
        final TextView addToMyListButton = this.detailsViewGroup.getAddToMyListButton();
        if (manager != null && this.getActivity() != null && addToMyListButton != null) {
            this.addToListWrapper = manager.createAddToMyListWrapper((DetailsActivity)this.getActivity(), addToMyListButton, false);
            manager.registerAddToMyListListener(this.getVideoId(), this.addToListWrapper);
        }
        RecommendToFriendsFrag.addRecommendButtonHandler((NetflixActivity)this.getActivity(), manager, this.detailsViewGroup.getRecommendButton(), this.getVideoId());
    }
    
    public void onResume() {
        super.onResume();
        this.detailsViewGroup.refreshImagesIfNecessary();
        if (this.manager != null) {
            if (this.mVideoDetails instanceof VideoDetails) {
                this.manager.updateMyListState(this.getVideoId(), this.mVideoDetails.isInQueue());
            }
            else if (Log.isLoggable("DetailsFrag", 6)) {
                Log.e("DetailsFrag", "onResume() got weird videoDetails class: " + this.mVideoDetails);
            }
        }
    }
    
    @Override
    public void onRetryRequested() {
        this.showLoadingView();
        this.reloadData();
    }
    
    protected abstract void reloadData();
    
    protected void showDetailsView(final T mVideoDetails) {
        this.mVideoDetails = mVideoDetails;
        this.leWrapper.hide(true);
        AnimationUtils.showView(this.primaryView, true);
        ((DetailsActivity)this.getActivity()).updateMenus(mVideoDetails);
        this.detailsViewGroup.updateDetails(mVideoDetails, this.getDetailsStringProvider(mVideoDetails));
    }
    
    protected void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView(this.primaryView, true);
    }
    
    protected void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView(this.primaryView, true);
    }
}
