// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.details.AbsEpisodeView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.TimeUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import android.widget.Button;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.ProgressBar;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;

public class BarkerVideoDetailsViewGroup extends VideoDetailsViewGroup
{
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.9f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.1f;
    private View actions;
    private BarkerHelper$BarkerBars barker;
    protected TextView basicSupplementalInfo;
    private TextView bookmarkDuration;
    private ViewGroup bookmarkGroup;
    private ProgressBar bookmarkProgressBar;
    private TextView bookmarkValue;
    private TextView certification;
    private View credits;
    private RadioButton dataSelectorEpisodes;
    private View dataSelectorEpisodesTop;
    private RadioGroup dataSelectorGroup;
    private RadioButton dataSelectorRelated;
    private View dataSelectorRelatedTop;
    private LinearLayout detailFlipper;
    private View detailSpacer;
    private IconFontTextView hdIcon;
    protected AdvancedImageView horzDispImg2;
    private View leftGroup;
    private Button myList;
    private View rate;
    private View ratingContainer;
    private View shadow;
    protected AdvancedImageView titleImg;
    private TextView year;
    
    public BarkerVideoDetailsViewGroup(final Context context) {
        super(context);
        this.barker = new BarkerHelper$BarkerBars(context);
    }
    
    private void alignShadow(int height) {
        if (this.shadow != null) {
            height = (int)(this.horzDispImg.getLayoutParams().height / 2.0f);
            this.shadow.getLayoutParams().height = height;
        }
    }
    
    private void manageDetailsOrientation() {
        boolean b = true;
        if (this.detailFlipper != null && this.detailSpacer != null) {
            final LinearLayout detailFlipper = this.detailFlipper;
            int orientation;
            if (this.barker.isSynopsisAndCreditsInSameRow()) {
                orientation = 0;
            }
            else {
                orientation = 1;
            }
            detailFlipper.setOrientation(orientation);
            final View detailSpacer = this.detailSpacer;
            if (this.barker.isSynopsisAndCreditsInSameRow()) {
                b = false;
            }
            ViewUtils.setVisibleOrGone(detailSpacer, b);
        }
    }
    
    private void setYear(final KubrickVideo kubrickVideo) {
        if (this.year == null) {
            return;
        }
        final String value = String.valueOf(kubrickVideo.getYear());
        this.year.setText((CharSequence)value);
        final TextView year = this.year;
        int visibility;
        if (StringUtils.isEmpty(value)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        year.setVisibility(visibility);
    }
    
    private void setupActionButtons() {
        this.setupRateButton();
    }
    
    private void setupRadioButtons() {
        final BarkerVideoDetailsViewGroup$DataSelectorButtonListener barkerVideoDetailsViewGroup$DataSelectorButtonListener = new BarkerVideoDetailsViewGroup$DataSelectorButtonListener(this);
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setOnClickListener((View$OnClickListener)barkerVideoDetailsViewGroup$DataSelectorButtonListener);
        }
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setOnClickListener((View$OnClickListener)barkerVideoDetailsViewGroup$DataSelectorButtonListener);
        }
    }
    
    private void showRatingDialog() {
        final String parentId = this.details.getPlayable().getParentId();
        VideoType videoType;
        if (this.details.getType() == VideoType.EPISODE) {
            videoType = VideoType.SHOW;
        }
        else {
            videoType = this.details.getType();
        }
        final RatingDialogFrag create = RatingDialogFrag.create(parentId, videoType, "", this.rate, 2130903255, false);
        final FragmentTransaction beginTransaction = ((NetflixActivity)this.getContext()).getFragmentManager().beginTransaction();
        beginTransaction.add(0, (Fragment)create, "frag_dialog");
        beginTransaction.commitAllowingStateLoss();
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        if (netflixActivity != null && netflixActivity.getServiceManager() != null) {
            create.onManagerReady(netflixActivity.getServiceManager(), CommonStatus.OK);
        }
    }
    
    private void updateCertification(final KubrickVideo kubrickVideo) {
        if (this.certification == null) {
            return;
        }
        final String certification = kubrickVideo.getCertification();
        this.certification.setText((CharSequence)certification);
        final TextView certification2 = this.certification;
        int visibility;
        if (StringUtils.isEmpty(certification)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        certification2.setVisibility(visibility);
    }
    
    private void updateHD(final KubrickVideo kubrickVideo) {
        if (this.hdIcon == null) {
            return;
        }
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        final IconFontTextView hdIcon = this.hdIcon;
        int visibility;
        if (DeviceUtils.shouldShowHdIcon(netflixActivity, kubrickVideo)) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        hdIcon.setVisibility(visibility);
    }
    
    private void updateMessage(final VideoDetails videoDetails) {
        if (this.supplemental != null && !TextUtils.isEmpty((CharSequence)videoDetails.getSupplementalMessage())) {
            this.supplemental.setText((CharSequence)videoDetails.getSupplementalMessage());
            this.supplemental.setVisibility(0);
        }
    }
    
    private void updateSupplementalInfo(final KubrickVideo kubrickVideo) {
        if (this.basicSupplementalInfo == null) {
            return;
        }
        if (kubrickVideo.getType() == VideoType.SHOW) {
            if (kubrickVideo.getSeasonCount() > 0) {
                this.basicSupplementalInfo.setText((CharSequence)kubrickVideo.getNumSeasonsLabel());
                this.basicSupplementalInfo.setVisibility(0);
                return;
            }
            this.basicSupplementalInfo.setVisibility(8);
        }
        else {
            if (kubrickVideo.getRuntime() > 0) {
                this.basicSupplementalInfo.setText((CharSequence)TimeUtils.getFormattedTime(kubrickVideo.getRuntime(), this.getContext()));
                this.basicSupplementalInfo.setVisibility(0);
                return;
            }
            this.basicSupplementalInfo.setVisibility(8);
        }
    }
    
    protected void alignHeroImage() {
        float n = 1.1f;
        if (DeviceUtils.getScreenAspectRatio(this.getContext()) > 1.6f) {
            final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
            final float n2 = layoutParams.height;
            if (DeviceUtils.isLandscape(this.getContext())) {
                n = 0.9f;
            }
            layoutParams.height = (int)(n * n2);
            this.horzDispImg.requestLayout();
            return;
        }
        final ViewGroup$LayoutParams layoutParams2 = this.horzDispImg.getLayoutParams();
        final float n3 = layoutParams2.height;
        if (DeviceUtils.isLandscape(this.getContext())) {
            n = 1.0f;
        }
        layoutParams2.height = (int)(n * n3);
        this.horzDispImg.requestLayout();
    }
    
    protected void alignLeftGroup() {
        if (this.leftGroup != null) {
            this.leftGroup.getLayoutParams().width = this.barker.getBookmarkWidth();
        }
    }
    
    @Override
    protected void alignViews() {
        super.alignViews();
        float n;
        if (this.leftGroup == null) {
            n = 0.0f;
        }
        else {
            n = this.leftGroup.getMeasuredHeight() + this.getContext().getResources().getDimension(2131362183);
        }
        final int n2 = (int)n;
        this.alignHeroImage();
        this.alignShadow(n2);
        this.alignLeftGroup();
    }
    
    @Override
    protected int calculateImageHeight() {
        return (int)(BarkerUtils.getDetailsPageContentWidth(this.getContext()) * 0.5625f);
    }
    
    public void clearHeroImages() {
        if (this.horzDispImg != null) {
            this.horzDispImg.setImageDrawable(null);
        }
        if (this.horzDispImg2 != null) {
            this.horzDispImg2.setImageDrawable(null);
        }
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.dataSelectorEpisodes = (RadioButton)this.findViewById(2131689633);
        this.dataSelectorRelated = (RadioButton)this.findViewById(2131689635);
        this.dataSelectorEpisodesTop = this.findViewById(2131689634);
        this.titleImg = (AdvancedImageView)this.findViewById(2131689650);
        this.dataSelectorRelatedTop = this.findViewById(2131689636);
        this.certification = (TextView)this.findViewById(2131689615);
        this.hdIcon = (IconFontTextView)this.findViewById(2131689617);
        this.basicSupplementalInfo = (TextView)this.findViewById(2131689616);
        this.dataSelectorGroup = (RadioGroup)this.findViewById(2131689632);
        this.horzDispImg2 = (AdvancedImageView)this.findViewById(2131689638);
        this.myList = (Button)this.findViewById(2131689631);
        this.year = (TextView)this.findViewById(2131689614);
        this.leftGroup = this.findViewById(2131689643);
        this.shadow = this.findViewById(2131689639);
        this.rate = this.findViewById(2131689613);
        this.bookmarkGroup = (ViewGroup)this.findViewById(2131689645);
        this.bookmarkDuration = (TextView)this.findViewById(2131689903);
        this.bookmarkValue = (TextView)this.findViewById(2131689902);
        this.bookmarkProgressBar = (ProgressBar)this.findViewById(2131689904);
        this.credits = this.findViewById(2131689648);
        this.actions = this.findViewById(2131689618);
        this.detailFlipper = (LinearLayout)this.findViewById(2131689642);
        this.detailSpacer = this.findViewById(2131689647);
        this.ratingContainer = this.findViewById(2131689612);
    }
    
    @Override
    public TextView getAddToMyListButton() {
        return (TextView)this.myList;
    }
    
    public AdvancedImageView getHeroImage2() {
        return this.horzDispImg2;
    }
    
    public RadioButton getRelatedTitleButton() {
        return this.dataSelectorRelated;
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903076;
    }
    
    public void hideDataSelector() {
        if (this.dataSelectorGroup != null) {
            this.dataSelectorGroup.setVisibility(8);
        }
    }
    
    public void performClickOnRelatedTitles() {
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.performClick();
        }
    }
    
    public void setBookmarkVisibility(final int visibility) {
        if (this.bookmarkGroup != null) {
            this.bookmarkGroup.setVisibility(visibility);
        }
    }
    
    protected void setEpisodesTextAsSelected() {
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131624167));
            this.dataSelectorEpisodesTop.setVisibility(0);
            this.dataSelectorEpisodes.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131624060));
            this.dataSelectorRelatedTop.setVisibility(4);
            this.dataSelectorRelated.setTypeface(Typeface.DEFAULT);
        }
    }
    
    protected void setRelatedTextAsSelected() {
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131623956));
            this.dataSelectorRelatedTop.setVisibility(0);
            this.dataSelectorRelated.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131623957));
            this.dataSelectorEpisodesTop.setVisibility(4);
            this.dataSelectorEpisodes.setTypeface(Typeface.DEFAULT);
        }
    }
    
    protected void setupRateButton() {
        if (this.ratingContainer != null) {
            this.ratingContainer.setOnClickListener((View$OnClickListener)new BarkerVideoDetailsViewGroup$1(this));
        }
    }
    
    protected void updateActions() {
        if (this.actions != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.actions.getLayoutParams();
            if (!this.barker.isSynopsisAndCreditsInSameRow()) {
                relativeLayout$LayoutParams.addRule(21);
                return;
            }
            relativeLayout$LayoutParams.setMarginStart(this.barker.getBookmarkWidth() + this.barker.getGutterWidth());
        }
    }
    
    protected void updateBasicInfo(final KubrickVideo year) {
        if (year == null) {
            return;
        }
        this.updateHD(year);
        this.setYear(year);
        this.updateCertification(year);
        this.updateSupplementalInfo(year);
    }
    
    @Override
    protected void updateBasicInfo(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
    }
    
    public void updateBookmark(final Playable playable) {
        if (this.bookmarkProgressBar != null) {
            int progress;
            if (playable.getRuntime() > 0) {
                progress = playable.getPlayableBookmarkPosition() * 100 / playable.getRuntime();
            }
            else {
                progress = 0;
            }
            this.bookmarkProgressBar.setProgress(progress);
        }
        if (this.bookmarkDuration != null) {
            this.bookmarkDuration.setText((CharSequence)TimeUtils.getFormattedTime(playable.getRuntime(), this.getContext()));
        }
        if (this.bookmarkValue != null) {
            this.bookmarkValue.setText((CharSequence)TimeUtils.getFormattedTime(playable.getPlayableBookmarkPosition(), this.getContext()));
        }
    }
    
    public void updateBookmarkTitle(final EpisodeDetails episodeDetails) {
        if (this.episodeTitle != null) {
            if (episodeDetails.isNSRE()) {
                this.episodeTitle.setText((CharSequence)AbsEpisodeView.createTitleText(episodeDetails, this.getContext()));
            }
            else {
                this.episodeTitle.setText((CharSequence)this.getResources().getString(2131231067, new Object[] { episodeDetails.getSeasonAbbrSeqLabel(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() }));
            }
            this.episodeTitle.setVisibility(0);
        }
    }
    
    @Override
    protected void updateCredits(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateCredits(videoDetailsViewGroup$DetailsStringProvider);
        if (this.credits != null) {
            this.credits.getLayoutParams().width = this.barker.getCreditsWidth();
        }
    }
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateDetails(videoDetails, videoDetailsViewGroup$DetailsStringProvider);
        this.updateTitle(videoDetails);
        this.updateBasicInfo((KubrickVideo)videoDetails);
        this.setupActionButtons();
        this.setupRadioButtons();
        this.setEpisodesTextAsSelected();
        this.updateActions();
        this.manageDetailsOrientation();
        this.updateMessage(videoDetails);
    }
    
    @Override
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
        if (netflixActivity == null || netflixActivity.getServiceManager() == null) {
            return;
        }
        String tag = null;
        if (videoDetails instanceof KubrickVideo) {
            tag = ((KubrickVideo)videoDetails).getKubrickStoryImgUrl();
        }
        if (StringUtils.isEmpty(tag)) {
            tag = videoDetails.getStoryUrl();
        }
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, tag, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true, 1, Bitmap$Config.ARGB_8888);
        this.horzDispImg.setTag((Object)tag);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    protected void updateImage(final String tag, final NetflixActivity netflixActivity, final String s, final Bitmap$Config bitmap$Config) {
        if (netflixActivity != null && netflixActivity.getServiceManager() != null && !StringUtils.isEmpty(tag)) {
            NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, tag, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true, 1, bitmap$Config);
            this.horzDispImg.setTag((Object)tag);
            this.setupImageClicks(this.details, netflixActivity);
        }
    }
    
    @Override
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
    }
    
    @Override
    protected void updateTitle(final VideoDetails videoDetails) {
        if (videoDetails == null || this.title == null) {
            return;
        }
        final String titleImgUrl = videoDetails.getTitleImgUrl();
        if (StringUtils.isEmpty(titleImgUrl) || !AndroidUtils.canDisplayTransparentWebpImages()) {
            this.title.setText((CharSequence)videoDetails.getTitle());
            this.title.setVisibility(0);
            this.titleImg.setVisibility(4);
            return;
        }
        this.title.setVisibility(8);
        this.titleImg.setVisibility(0);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.titleImg, titleImgUrl, IClientLogging$AssetType.heroImage, videoDetails.getTitle(), ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true, 1, Bitmap$Config.RGB_565);
    }
}
