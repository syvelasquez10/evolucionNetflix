// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.view.ViewGroup$LayoutParams;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import java.util.HashMap;
import com.netflix.model.branches.FalkorActorStill;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import java.util.Map;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.graphics.Color;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeUtils;
import android.view.View$OnClickListener;
import com.netflix.model.branches.FalkorVideo;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.content.BroadcastReceiver;
import android.widget.ViewFlipper;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.Checkable;
import android.widget.RelativeLayout;

class RoleDetailsFrag$ActorRelatedView extends RelativeLayout implements Checkable, VideoViewGroup$IVideoView
{
    final int EXPANDED;
    final int UNEXPANDED;
    private AddToListData$StateListener addToListWrapper;
    TextView addToQueue;
    int currentPosition;
    TextView duration;
    View expand;
    ViewGroup expandedGroup;
    ViewFlipper flipper;
    TextView genre;
    boolean isChecked;
    private final BroadcastReceiver rdpClose;
    View showDP;
    AdvancedImageView storyArtImage;
    AdvancedImageView storyArtImageExpanded;
    TextView synopsis;
    final /* synthetic */ RoleDetailsFrag this$0;
    TextView title;
    TextView titleExpanded;
    AdvancedImageView titleImage;
    ViewGroup unExpandedGroup;
    
    public RoleDetailsFrag$ActorRelatedView(final RoleDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.UNEXPANDED = 0;
        this.EXPANDED = 1;
        this.rdpClose = new RoleDetailsFrag$ActorRelatedView$1(this);
        this.init();
    }
    
    public RoleDetailsFrag$ActorRelatedView(final RoleDetailsFrag this$0, final Context context, final AttributeSet set) {
        this.this$0 = this$0;
        super(context, set);
        this.UNEXPANDED = 0;
        this.EXPANDED = 1;
        this.rdpClose = new RoleDetailsFrag$ActorRelatedView$1(this);
        this.init();
    }
    
    public RoleDetailsFrag$ActorRelatedView(final RoleDetailsFrag this$0, final Context context, final AttributeSet set, final int n) {
        this.this$0 = this$0;
        super(context, set, n);
        this.UNEXPANDED = 0;
        this.EXPANDED = 1;
        this.rdpClose = new RoleDetailsFrag$ActorRelatedView$1(this);
        this.init();
    }
    
    private void findViews() {
        this.synopsis = (TextView)this.findViewById(2131690249);
        this.duration = (TextView)this.findViewById(2131690250);
        this.titleExpanded = (TextView)this.findViewById(2131690246);
        this.title = (TextView)this.findViewById(2131690242);
        this.genre = (TextView)this.findViewById(2131690251);
        this.storyArtImage = (AdvancedImageView)this.findViewById(2131690228);
        this.storyArtImageExpanded = (AdvancedImageView)this.findViewById(2131690245);
        this.titleImage = (AdvancedImageView)this.findViewById(2131690241);
        this.addToQueue = (TextView)this.findViewById(2131690248);
        this.expand = this.findViewById(2131690243);
        this.showDP = this.findViewById(2131690247);
        this.expandedGroup = (ViewGroup)this.findViewById(2131690244);
        this.unExpandedGroup = (ViewGroup)this.findViewById(2131690240);
        this.flipper = (ViewFlipper)this.findViewById(2131690239);
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this, true);
        this.findViews();
        this.setupExpand();
        this.initReceivers();
    }
    
    private void initReceivers() {
        this.this$0.getNetflixActivity().registerReceiverLocallyWithAutoUnregister(this.rdpClose, "com.netflix.mediaclient.intent.action.RDP_CLOSE");
    }
    
    private void setupButtons(final FalkorVideo falkorVideo) {
        if (this.showDP != null) {
            this.showDP.setOnClickListener((View$OnClickListener)new RoleDetailsFrag$ActorRelatedView$2(this, falkorVideo));
        }
    }
    
    private void setupExpand() {
        if (this.expandedGroup != null) {
            this.expandedGroup.setOnClickListener((View$OnClickListener)new RoleDetailsFrag$ActorRelatedView$3(this));
        }
        if (this.unExpandedGroup != null) {
            this.unExpandedGroup.setOnClickListener((View$OnClickListener)new RoleDetailsFrag$ActorRelatedView$4(this));
        }
    }
    
    private void updateDuration(final FalkorVideo falkorVideo) {
        if (this.duration != null) {
            this.duration.setText((CharSequence)TimeUtils.getFormattedTime(falkorVideo.getRuntime(), this.getContext()));
        }
    }
    
    private void updateGenre(final FalkorVideo falkorVideo) {
        if (this.genre != null) {
            this.genre.setText((CharSequence)falkorVideo.getGenres());
        }
    }
    
    private void updateImage(final FalkorVideo falkorVideo) {
        final String imageUrl = this.getImageUrl(falkorVideo, false);
        if (this.storyArtImage != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.storyArtImage, imageUrl, IClientLogging$AssetType.boxArt, "RoleDetailsFragActorRelatedView", BrowseExperience.getImageLoaderConfig(), true);
            this.adjustHeight(this.storyArtImage);
        }
        if (this.storyArtImageExpanded != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.storyArtImageExpanded, imageUrl, IClientLogging$AssetType.boxArt, "RoleDetailsFragActorRelatedView", BrowseExperience.getImageLoaderConfig(), true);
            this.adjustHeight(this.storyArtImageExpanded);
            this.storyArtImageExpanded.setColorFilter(Color.argb(220, 0, 0, 0));
        }
    }
    
    private void updateMyListState(final FalkorVideo falkorVideo) {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager != null) {
            serviceManager.updateMyListState(falkorVideo.getId(), falkorVideo.isInQueue());
        }
    }
    
    private void updateSynopsis(final FalkorVideo falkorVideo) {
        if (this.synopsis != null) {
            this.synopsis.setText((CharSequence)falkorVideo.getSynopsis());
        }
    }
    
    private void updateTitle(final FalkorVideo falkorVideo) {
        if (this.titleExpanded != null) {
            this.titleExpanded.setText((CharSequence)falkorVideo.getTitle());
        }
        if (this.title != null) {
            if (!StringUtils.isNotEmpty(falkorVideo.getTitleImgUrl())) {
                this.title.setVisibility(0);
                this.title.setText((CharSequence)falkorVideo.getTitle());
            }
            else {
                this.title.setVisibility(4);
            }
        }
        if (this.titleImage != null) {
            if (!StringUtils.isNotEmpty(falkorVideo.getTitleImgUrl())) {
                this.titleImage.setVisibility(4);
                return;
            }
            this.titleImage.setVisibility(0);
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.titleImage, falkorVideo.getTitleImgUrl(), IClientLogging$AssetType.boxArt, "RoleDetailsFragActorRelatedView", BrowseExperience.getImageLoaderConfig(), true);
            this.titleImage.getLayoutParams().height = this.storyArtImage.getLayoutParams().height / 3;
        }
    }
    
    AddToListData$StateListener addToMyListWrapper(final Video video) {
        final ServiceManager serviceManager = this.this$0.getNetflixActivity().getServiceManager();
        if (serviceManager != null && serviceManager.getCurrentProfile() != null && this.this$0.getActivity() != null && this.addToQueue != null) {
            if (serviceManager.getCurrentProfile() == null) {
                ErrorLoggingManager.logHandledException("SPY-8691 - current profile is null");
            }
            else {
                this.addToListWrapper = serviceManager.createAddToMyListWrapper(this.this$0.getNetflixActivity(), this.addToQueue, video.getId(), video.getType(), PlayContext.NFLX_MDX_CONTEXT.getTrackId());
                serviceManager.registerAddToMyListListener(video.getId(), this.addToListWrapper);
            }
        }
        return this.addToListWrapper;
    }
    
    protected void adjustHeight(final ImageView imageView) {
        imageView.getLayoutParams().height = (int)((BarkerUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131361867) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    public String getImageUrl(final Object o, final boolean b) {
        return ((FalkorVideo)o).getStoryUrl();
    }
    
    public PlayContext getPlayContext() {
        return PlayContext.NFLX_MDX_CONTEXT;
    }
    
    protected int getlayoutId() {
        return 2130903257;
    }
    
    public void hide() {
    }
    
    public boolean isChecked() {
        return this.isChecked;
    }
    
    public void setChecked(final boolean isChecked) {
        this.isChecked = isChecked;
        if (this.flipper != null) {
            final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            ((Animation)alphaAnimation).setDuration(500L);
            final AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.1f);
            ((Animation)alphaAnimation2).setDuration(500L);
            if (isChecked && this.flipper.getDisplayedChild() != 1) {
                this.flipper.setInAnimation((Animation)alphaAnimation);
                this.flipper.setOutAnimation((Animation)alphaAnimation2);
                this.flipper.setDisplayedChild(1);
                return;
            }
            if (!isChecked && this.flipper.getDisplayedChild() != 0) {
                this.flipper.setInAnimation((Animation)alphaAnimation);
                this.flipper.setOutAnimation((Animation)alphaAnimation2);
                this.flipper.setDisplayedChild(0);
            }
        }
    }
    
    public void toggle() {
    }
    
    public void update(final Object o, final Trackable trackable, final int n, final boolean b, final boolean b2) {
        final FalkorVideo falkorVideo = (FalkorVideo)o;
        this.currentPosition = n + 1;
        if (this.addToListWrapper == null) {
            this.addToMyListWrapper(falkorVideo);
            this.this$0.addMyListener(falkorVideo, this.addToListWrapper);
        }
        this.updateMyListState(falkorVideo);
        this.updateDetails(falkorVideo);
        this.setupButtons(falkorVideo);
    }
    
    void updateDetails(final FalkorVideo falkorVideo) {
        if (falkorVideo == null) {
            return;
        }
        this.updateGenre(falkorVideo);
        this.updateSynopsis(falkorVideo);
        this.updateDuration(falkorVideo);
        this.updateImage(falkorVideo);
        this.updateTitle(falkorVideo);
    }
}
