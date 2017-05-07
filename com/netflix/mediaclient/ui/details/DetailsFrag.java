// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
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
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;

public abstract class DetailsFrag<T extends VideoDetails> extends NetflixFrag implements ErrorWrapper$Callback, VideoDetailsViewGroup$VideoDetailsViewGroupProvider
{
    private static final String TAG = "DetailsFrag";
    private AddToListData$StateListener addToListWrapper;
    public VideoDetailsViewGroup detailsViewGroup;
    protected final ErrorWrapper$Callback errorCallback;
    protected LoadingAndErrorWrapper leWrapper;
    private T mVideoDetails;
    private ServiceManager manager;
    protected View primaryView;
    
    public DetailsFrag() {
        this.errorCallback = new DetailsFrag$1(this);
    }
    
    protected abstract VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final T p0);
    
    protected int getLayoutId() {
        return 2130903204;
    }
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    @Override
    public VideoDetailsViewGroup getVideoDetailsViewGroup() {
        return this.detailsViewGroup;
    }
    
    protected abstract String getVideoId();
    
    protected abstract void initDetailsViewGroup(final View p0);
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("DetailsFrag", "Creating new frag view...");
        final View inflate = layoutInflater.inflate(this.getLayoutId(), (ViewGroup)null, false);
        this.initDetailsViewGroup(inflate);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        this.primaryView = inflate.findViewById(2131165686);
        if (this.primaryView != null) {
            this.primaryView.setVerticalScrollBarEnabled(false);
        }
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
        final TextView addToMyListButtonLabel = this.detailsViewGroup.getAddToMyListButtonLabel();
        if (manager != null && this.getActivity() != null && addToMyListButton != null) {
            this.addToListWrapper = manager.createAddToMyListWrapper((DetailsActivity)this.getActivity(), addToMyListButton, addToMyListButtonLabel, false);
            manager.registerAddToMyListListener(this.getVideoId(), this.addToListWrapper);
        }
        RecommendToFriendsFrag.addRecommendButtonHandler((NetflixActivity)this.getActivity(), manager, this.detailsViewGroup.getRecommendButton(), this.getVideoId(), this.detailsViewGroup.getRecommendButtonLabel());
    }
    
    public void onResume() {
        super.onResume();
        if (this.detailsViewGroup != null) {
            this.detailsViewGroup.refreshImagesIfNecessary();
        }
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
        if (this.primaryView != null) {
            AnimationUtils.showView(this.primaryView, true);
        }
        ((DetailsActivity)this.getActivity()).updateMenus(mVideoDetails);
        ServiceManagerUtils.cacheManifestIfEnabled(this.getServiceManager(), this.mVideoDetails.getPlayable());
        this.detailsViewGroup.updateDetails(mVideoDetails, this.getDetailsStringProvider(mVideoDetails));
    }
    
    protected void showErrorView() {
        this.leWrapper.showErrorView(true);
        if (this.primaryView != null) {
            AnimationUtils.hideView(this.primaryView, true);
        }
    }
    
    protected void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        if (this.primaryView != null) {
            AnimationUtils.hideView(this.primaryView, true);
        }
    }
}
