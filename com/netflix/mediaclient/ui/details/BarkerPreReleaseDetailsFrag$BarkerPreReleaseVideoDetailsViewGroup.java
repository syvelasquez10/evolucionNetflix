// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.text.TextUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper$BarkerBars;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.widget.TextView;
import android.widget.Button;

class BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup extends VideoDetailsViewGroup
{
    private static final String TAG = "BarkerPreReleaseVideoDetailsViewGroup";
    private Button playButton;
    private TextView supplementalMessage;
    final /* synthetic */ BarkerPreReleaseDetailsFrag this$0;
    
    public BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup(final BarkerPreReleaseDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    private void fetchSupplementalVideos(final String s) {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("BarkerPreReleaseVideoDetailsViewGroup", "Manager is null/notReady - can't reload data");
            return;
        }
        this.this$0.requestId = System.nanoTime();
        serviceManager.getBrowse().fetchMovieDetails(s, null, new BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback(this, this.this$0.requestId));
    }
    
    private void updateSupplementalMessage(final VideoDetails videoDetails) {
        if (this.supplementalMessage != null && videoDetails != null) {
            if (this.this$0.isSupplementalMessageAvailable()) {
                this.supplementalMessage.setText((CharSequence)videoDetails.getSupplementalMessage());
                return;
            }
            this.supplementalMessage.getLayoutParams().height = 0;
            if (this.relatedTitle != null) {
                this.relatedTitle.setVisibility(4);
            }
        }
    }
    
    @Override
    protected void alignViews() {
        this.imgGroup.getLayoutParams().width = new BarkerHelper$BarkerBars((Context)this.this$0.getActivity()).getPreReleaseContentWidth();
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.playButton = (Button)this.findViewById(2131821565);
        this.supplementalMessage = (TextView)this.findViewById(2131821558);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903347;
    }
    
    @Override
    protected void setupImageClicks(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        if (videoDetails.hasTrailers()) {
            final String defaultTrailer = videoDetails.getDefaultTrailer();
            if (!TextUtils.isEmpty((CharSequence)defaultTrailer)) {
                this.fetchSupplementalVideos(defaultTrailer);
            }
            return;
        }
        this.playButton.setVisibility(4);
    }
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateDetails(videoDetails, videoDetailsViewGroup$DetailsStringProvider);
        this.updateSupplementalMessage(videoDetails);
    }
    
    @Override
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.getBackgroundImage();
        String s2;
        if (videoDetails.getTitleCroppedImgUrl() == null) {
            s2 = videoDetails.getTvCardUrl();
        }
        else {
            s2 = videoDetails.getTitleCroppedImgUrl();
        }
        final String storyUrl = videoDetails.getStoryUrl();
        if (Log.isLoggable()) {
            Log.d("BarkerPreReleaseVideoDetailsViewGroup", "logoImg -> " + s2);
            Log.d("BarkerPreReleaseVideoDetailsViewGroup", "bgImg -> " + storyUrl);
        }
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg((AdvancedImageView)this.horzDispImg, s2, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true);
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(advancedImageView, storyUrl, IClientLogging$AssetType.heroImage, s, BrowseExperience.getImageLoaderConfig(), true);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
}
