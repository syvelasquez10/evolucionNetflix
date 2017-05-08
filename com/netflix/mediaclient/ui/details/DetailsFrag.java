// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;

public abstract class DetailsFrag<T extends VideoDetails> extends NetflixFrag implements ErrorWrapper$Callback, DetailsActivity$Reloader, VideoDetailsViewGroup$VideoDetailsViewGroupProvider
{
    public static final String EXTRA_IS_MOVIE = "extra_is_movie";
    private static final String TAG = "DetailsFrag";
    private AddToListData$StateListener addToListWrapper;
    public VideoDetailsViewGroup detailsViewGroup;
    protected final ErrorWrapper$Callback errorCallback;
    protected LoadingAndErrorWrapper leWrapper;
    private T mVideoDetails;
    protected View primaryView;
    
    public DetailsFrag() {
        this.errorCallback = new DetailsFrag$2(this);
    }
    
    protected static AddToListData$StateListener addToMyListWrapper(final VideoDetailsViewGroup videoDetailsViewGroup, final NetflixActivity netflixActivity, final ServiceManager serviceManager, final String s) {
        final TextView addToMyListButton = videoDetailsViewGroup.getAddToMyListButton();
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("Manager: ").append(serviceManager);
            String string;
            if (serviceManager == null) {
                string = "";
            }
            else {
                string = ", current profile: " + serviceManager.getCurrentProfile();
            }
            Log.v("DetailsFrag", append.append(string).toString());
            Log.v("DetailsFrag", "Activity: " + netflixActivity + ", btnMyList: " + addToMyListButton);
        }
        if (serviceManager == null || serviceManager.getCurrentProfile() == null || netflixActivity == null || addToMyListButton == null) {
            return null;
        }
        if (serviceManager.getCurrentProfile() == null) {
            ErrorLoggingManager.logHandledException("SPY-8691 - current profile is null");
            Log.w("DetailsFrag", "SPY-8691 - current profile is null");
            return null;
        }
        if ((serviceManager.getCurrentProfile().isKidsProfile() && !KidsUtils.isKidsParity((Context)netflixActivity)) || !serviceManager.isCurrentProfileInstantQueueEnabled()) {
            videoDetailsViewGroup.setMyListVisibility(8);
            return null;
        }
        final AddToListData$StateListener addToMyListWrapper = serviceManager.createAddToMyListWrapper((DetailsActivity)netflixActivity, addToMyListButton, videoDetailsViewGroup.getAddToMyListButtonLabel(), false);
        serviceManager.registerAddToMyListListener(s, addToMyListWrapper);
        return addToMyListWrapper;
    }
    
    protected abstract VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final T p0);
    
    protected int getLayoutId() {
        return 2130903336;
    }
    
    protected int getPrimaryViewId() {
        return 2131821550;
    }
    
    public String getTitle() {
        if (this.mVideoDetails == null) {
            return null;
        }
        return this.mVideoDetails.getTitle();
    }
    
    protected VideoDetails getVideoDetails() {
        return this.mVideoDetails;
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
        this.primaryView = inflate.findViewById(this.getPrimaryViewId());
        if (this.primaryView != null) {
            this.primaryView.setVerticalScrollBarEnabled(false);
            KidsUtils.setBackgroundIfApplicable(this.primaryView);
        }
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && this.addToListWrapper != null) {
            serviceManager.unregisterAddToMyListListener(this.getVideoId(), this.addToListWrapper);
        }
    }
    
    public void onResume() {
        super.onResume();
        this.updateMyListState();
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
        final ServiceManager serviceManager = this.getServiceManager();
        this.mVideoDetails = mVideoDetails;
        this.leWrapper.hide(true);
        if (this.primaryView != null) {
            AnimationUtils.showView(this.primaryView, true);
        }
        PlayContext playContext = null;
        if (this.getActivity() instanceof PlayContextProvider) {
            playContext = ((PlayContextProvider)this.getActivity()).getPlayContext();
        }
        ServiceManagerUtils.castPrefetchAndCacheManifestIfEnabled(serviceManager, this.mVideoDetails.getPlayable(), playContext);
        this.detailsViewGroup.updateDetails(mVideoDetails, this.getDetailsStringProvider(mVideoDetails));
        this.addToListWrapper = addToMyListWrapper(this.detailsViewGroup, (NetflixActivity)this.getActivity(), serviceManager, this.getVideoId());
        this.detailsViewGroup.setupDownloadButton(mVideoDetails);
    }
    
    protected void showErrorView() {
        this.leWrapper.showErrorView(true);
        if (this.getNetflixActivity() != null) {
            this.getNetflixActivity().runWhenManagerIsReady(new DetailsFrag$1(this));
        }
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
    
    protected void updateMyListState() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null) {
            if (this.detailsViewGroup != null) {
                this.detailsViewGroup.refreshImagesIfNecessary();
            }
            if (this.mVideoDetails instanceof VideoDetails) {
                serviceManager.updateMyListState(this.getVideoId(), this.mVideoDetails.isInQueue());
            }
            else if (Log.isLoggable()) {
                Log.d("DetailsFrag", "onResume() got weird videoDetails class: " + this.mVideoDetails);
            }
        }
    }
}
