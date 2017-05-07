// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.EvidenceDetails;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
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
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ProgressBar;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;

public class KubrickVideoDetailsViewGroup extends VideoDetailsViewGroup
{
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.75f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.1f;
    private TextView bookMarkTitle;
    private TextView bookmarkDuration;
    private ViewGroup bookmarkGroup;
    private ProgressBar bookmarkProgressBar;
    private TextView bookmarkValue;
    private TextView certification;
    private RadioButton dataSelectorEpisodes;
    private View dataSelectorEpisodesTop;
    private RadioGroup dataSelectorGroup;
    private RadioButton dataSelectorRelated;
    private View dataSelectorRelatedTop;
    private TextView durationInfo;
    private IconFontTextView evidence;
    private ViewGroup evidenceGroup;
    private TextView evidenceText;
    private TextView genres;
    private IconFontTextView hdIcon;
    protected AdvancedImageView horzDispImg2;
    private View leftGroup;
    private TextView myList;
    private TextView myListLabel;
    private View rate;
    private View shadow;
    private TextView share;
    private TextView shareLabel;
    protected AdvancedImageView titleImg;
    private TextView year;
    
    public KubrickVideoDetailsViewGroup(final Context context) {
        super(context);
    }
    
    private void alignShadow(int height) {
        if (this.shadow != null) {
            height = (int)(this.horzDispImg.getLayoutParams().height / 2.0f);
            this.shadow.getLayoutParams().height = height;
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
        final KubrickVideoDetailsViewGroup$DataSelectorButtonListener kubrickVideoDetailsViewGroup$DataSelectorButtonListener = new KubrickVideoDetailsViewGroup$DataSelectorButtonListener(this);
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setOnClickListener((View$OnClickListener)kubrickVideoDetailsViewGroup$DataSelectorButtonListener);
        }
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setOnClickListener((View$OnClickListener)kubrickVideoDetailsViewGroup$DataSelectorButtonListener);
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
        final RatingDialogFrag create = RatingDialogFrag.create(parentId, videoType, "", this.rate, 2130903199, false);
        final FragmentTransaction beginTransaction = ((NetflixActivity)this.getContext()).getFragmentManager().beginTransaction();
        beginTransaction.add(0, (Fragment)create, "frag_dialog");
        beginTransaction.commit();
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
    
    private void updateDuration(final KubrickVideo kubrickVideo) {
        if (this.durationInfo == null) {
            return;
        }
        final Resources resources = this.getResources();
        if (kubrickVideo.getType() == VideoType.SHOW) {
            final int seasonCount = kubrickVideo.getSeasonCount();
            if (seasonCount > 0) {
                this.durationInfo.setText((CharSequence)resources.getQuantityString(2131230721, seasonCount, new Object[] { seasonCount }));
                this.durationInfo.setVisibility(0);
                return;
            }
            this.durationInfo.setVisibility(8);
        }
        else {
            final int runtime = kubrickVideo.getRuntime();
            if (runtime > 0) {
                this.durationInfo.setText((CharSequence)resources.getString(2131165566, new Object[] { TimeUtils.convertSecondsToMinutes(runtime) }));
                this.durationInfo.setVisibility(0);
                return;
            }
            this.durationInfo.setVisibility(8);
        }
    }
    
    private void updateEvidence(final EvidenceDetails evidenceDetails) {
        if (evidenceDetails == null || this.evidenceGroup == null) {
            return;
        }
        if (this.evidence != null && this.evidenceText != null && StringUtils.isNotEmpty(evidenceDetails.getEvidenceText())) {
            this.evidence.setToIcon(evidenceDetails.getEvidenceGlyph(), 2131296515);
            this.evidenceText.setText((CharSequence)evidenceDetails.getEvidenceText());
            this.evidenceGroup.setVisibility(0);
            return;
        }
        this.evidenceGroup.setVisibility(8);
    }
    
    private void updateGenres(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (this.genres == null) {
            return;
        }
        if (videoDetailsViewGroup$DetailsStringProvider.getGenresText() != null) {
            this.genres.setText(videoDetailsViewGroup$DetailsStringProvider.getGenresText());
            this.genres.setVisibility(0);
            return;
        }
        this.genres.setVisibility(8);
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
    
    protected void alignHeroImage() {
        float n = 1.1f;
        if (DeviceUtils.getScreenAspectRatio(this.getContext()) > 1.6f) {
            final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
            final float n2 = layoutParams.height;
            if (DeviceUtils.isLandscape(this.getContext())) {
                n = 0.75f;
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
            this.leftGroup.getLayoutParams().width = (int)(KubrickUtils.getDetailsPageContentWidth(this.getContext()) * 0.667f);
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
            n = this.leftGroup.getMeasuredHeight() + this.getContext().getResources().getDimension(2131296515);
        }
        final int n2 = (int)n;
        this.alignHeroImage();
        this.alignShadow(n2);
        this.alignLeftGroup();
    }
    
    @Override
    protected int calculateImageHeight() {
        return (int)(KubrickUtils.getDetailsPageContentWidth(this.getContext()) * 0.5625f);
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
        this.dataSelectorEpisodes = (RadioButton)this.findViewById(2131624255);
        this.dataSelectorRelated = (RadioButton)this.findViewById(2131624257);
        this.dataSelectorEpisodesTop = this.findViewById(2131624256);
        this.titleImg = (AdvancedImageView)this.findViewById(2131624186);
        this.dataSelectorRelatedTop = this.findViewById(2131624258);
        this.certification = (TextView)this.findViewById(2131624195);
        this.evidenceGroup = (ViewGroup)this.findViewById(2131624259);
        this.evidenceText = (TextView)this.findViewById(2131624261);
        this.hdIcon = (IconFontTextView)this.findViewById(2131624197);
        this.durationInfo = (TextView)this.findViewById(2131624196);
        this.evidence = (IconFontTextView)this.findViewById(2131624260);
        this.dataSelectorGroup = (RadioGroup)this.findViewById(2131624209);
        this.myListLabel = (TextView)this.findViewById(2131624245);
        this.shareLabel = (TextView)this.findViewById(2131624242);
        this.horzDispImg2 = (AdvancedImageView)this.findViewById(2131624184);
        this.myList = (TextView)this.findViewById(2131624244);
        this.genres = (TextView)this.findViewById(2131624254);
        this.year = (TextView)this.findViewById(2131624194);
        this.share = (TextView)this.findViewById(2131624243);
        this.leftGroup = this.findViewById(2131624265);
        this.shadow = this.findViewById(2131624263);
        this.rate = this.findViewById(2131624246);
        this.bookMarkTitle = (TextView)this.findViewById(2131624248);
        this.bookmarkGroup = (ViewGroup)this.findViewById(2131624266);
        this.bookmarkDuration = (TextView)this.findViewById(2131624250);
        this.bookmarkValue = (TextView)this.findViewById(2131624249);
        this.bookmarkProgressBar = (ProgressBar)this.findViewById(2131624251);
    }
    
    @Override
    public TextView getAddToMyListButton() {
        return this.myList;
    }
    
    @Override
    public TextView getAddToMyListButtonLabel() {
        return this.myListLabel;
    }
    
    public AdvancedImageView getHeroImage2() {
        return this.horzDispImg2;
    }
    
    public RadioButton getRelatedTitleButton() {
        return this.dataSelectorRelated;
    }
    
    @Override
    protected int getlayoutId() {
        if (DeviceUtils.getScreenWidthInDPs(this.getContext()) <= 600) {
            return 2130903130;
        }
        return 2130903129;
    }
    
    public void hideDataSelector() {
        if (this.dataSelectorGroup != null) {
            this.dataSelectorGroup.setVisibility(8);
        }
    }
    
    public void hideEvidence() {
        if (this.evidence != null) {
            this.evidence.setVisibility(8);
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
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131558520));
            this.dataSelectorEpisodesTop.setVisibility(0);
            this.dataSelectorEpisodes.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131558499));
            this.dataSelectorRelatedTop.setVisibility(4);
            this.dataSelectorRelated.setTypeface(Typeface.DEFAULT);
        }
    }
    
    public void setEvidenceVisibility(final int visibility) {
        if (this.evidenceGroup != null) {
            this.evidenceGroup.setVisibility(visibility);
        }
    }
    
    protected void setRelatedTextAsSelected() {
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131558520));
            this.dataSelectorRelatedTop.setVisibility(0);
            this.dataSelectorRelated.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131558499));
            this.dataSelectorEpisodesTop.setVisibility(4);
            this.dataSelectorEpisodes.setTypeface(Typeface.DEFAULT);
        }
    }
    
    protected void setupRateButton() {
        if (this.rate != null) {
            this.rate.setOnClickListener((View$OnClickListener)new KubrickVideoDetailsViewGroup$1(this));
        }
    }
    
    protected void updateBasicInfo(final KubrickVideo year) {
        if (year == null) {
            return;
        }
        this.updateHD(year);
        this.setYear(year);
        this.updateCertification(year);
        this.updateDuration(year);
    }
    
    @Override
    protected void updateBasicInfo(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
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
            this.bookmarkDuration.setText((CharSequence)this.getResources().getString(2131165566, new Object[] { TimeUtils.convertSecondsToMinutes(playable.getRuntime()) }));
        }
        if (this.bookmarkValue != null) {
            this.bookmarkValue.setText((CharSequence)this.getResources().getString(2131165566, new Object[] { TimeUtils.convertSecondsToMinutes(playable.getPlayableBookmarkPosition()) }));
        }
    }
    
    public void updateBookmarkTitle(final EpisodeDetails episodeDetails) {
        if (this.bookMarkTitle != null) {
            this.bookMarkTitle.setText((CharSequence)this.getResources().getString(2131165511, new Object[] { episodeDetails.getSeasonNumber(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() }));
            this.bookMarkTitle.setVisibility(0);
        }
    }
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateDetails(videoDetails, videoDetailsViewGroup$DetailsStringProvider);
        this.updateGenres(videoDetailsViewGroup$DetailsStringProvider);
        this.updateEvidence((EvidenceDetails)videoDetails);
        this.updateTitle(videoDetails);
        this.updateBasicInfo((KubrickVideo)videoDetails);
        this.setupActionButtons();
        this.setupRadioButtons();
        this.setEpisodesTextAsSelected();
    }
    
    @Override
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
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
        if (StringUtils.isEmpty(tag)) {
            return;
        }
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, tag, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true, 1, bitmap$Config);
        this.horzDispImg.setTag((Object)tag);
        this.setupImageClicks(this.details, netflixActivity);
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
