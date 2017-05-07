// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
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
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;

public abstract class DetailsFrag<T extends VideoDetails> extends NetflixFrag implements ErrorWrapper$Callback, DetailsActivity$Reloader, VideoDetailsViewGroup$VideoDetailsViewGroupProvider
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
        return 2130903206;
    }
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    public String getTitle() {
        if (this.mVideoDetails == null) {
            return null;
        }
        return this.mVideoDetails.getTitle();
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
        this.primaryView = inflate.findViewById(2131427842);
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
            else if (Log.isLoggable()) {
                Log.e("DetailsFrag", "onResume() got weird videoDetails class: " + this.mVideoDetails);
            }
        }
    }
    
    @Override
    public void onRetryRequested() {
        this.showLoadingView();
        this.reloadData();
    }
    
    @Override
    public void reload() {
        Log.v("DetailsFrag", "reload()");
        this.reloadData();
    }
    
    protected abstract void reloadData();
    
    protected void showDetailsView(final T mVideoDetails) {
        this.mVideoDetails = mVideoDetails;
        this.leWrapper.hide(true);
        if (this.primaryView != null) {
            AnimationUtils.showView(this.primaryView, true);
        }
        PlayContext playContext = null;
        if (this.getActivity() instanceof PlayContextProvider) {
            playContext = ((PlayContextProvider)this.getActivity()).getPlayContext();
        }
        ServiceManagerUtils.cacheManifestIfEnabled(this.getServiceManager(), this.mVideoDetails.getPlayable(), playContext);
        this.detailsViewGroup.updateDetails(mVideoDetails, this.getDetailsStringProvider(mVideoDetails));
        this.addToListWrapper = SocialUtils.setupVideoDetailsButtons(this.detailsViewGroup, (NetflixActivity)this.getActivity(), this.manager, this.getVideoId(), this.mVideoDetails.getTitle(), this.mVideoDetails.getType());
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
