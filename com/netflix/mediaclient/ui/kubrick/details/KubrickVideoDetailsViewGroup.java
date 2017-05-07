// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.EvidenceDetails;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.app.FragmentTransaction;
import android.app.Fragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;

public class KubrickVideoDetailsViewGroup extends VideoDetailsViewGroup
{
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.9f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.1f;
    private TextView certification;
    private View credits;
    private RadioButton dataSelectorEpisodes;
    private View dataSelectorEpisodesTop;
    private RadioGroup dataSelectorGroup;
    private RadioButton dataSelectorRelated;
    private View dataSelectorRelatedTop;
    VideoDetails details;
    private TextView durationInfo;
    private IconFontTextView evidence;
    private ViewGroup evidenceGroup;
    private TextView evidenceText;
    private TextView genres;
    private IconFontTextView hdIcon;
    private View leftGroup;
    private TextView myList;
    private TextView myListLabel;
    private View rate;
    private View shadow;
    private TextView share;
    private TextView shareLabel;
    private AdvancedImageView titleImg;
    private TextView year;
    
    public KubrickVideoDetailsViewGroup(final Context context) {
        super(context);
    }
    
    private void alignHeroImage() {
        final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
        final float n = layoutParams.height;
        float n2;
        if (DeviceUtils.isLandscape(this.getContext())) {
            n2 = 0.9f;
        }
        else {
            n2 = 1.1f;
        }
        layoutParams.height = (int)(n2 * n);
    }
    
    private void alignShadow(int height, final int n) {
        if (this.shadow != null) {
            height = (int)(this.durationInfo.getMeasuredHeight() + Math.max(n, height) / 2.0f);
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
        final RatingDialogFrag instance = RatingDialogFrag.newInstance(MdxUtils.getRating(this.details, this.details.getUserRating()), this.details.getPlayable().getParentId(), "", this.rate, 2130903123);
        instance.setAutoDismiss(false);
        final FragmentTransaction beginTransaction = ((NetflixActivity)this.getContext()).getFragmentManager().beginTransaction();
        beginTransaction.add(0, (Fragment)instance, (String)null);
        beginTransaction.commit();
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
                this.durationInfo.setText((CharSequence)resources.getQuantityString(2131623937, seasonCount, new Object[] { seasonCount }));
                this.durationInfo.setVisibility(0);
                return;
            }
            this.durationInfo.setVisibility(8);
        }
        else {
            final int runtime = kubrickVideo.getRuntime();
            if (runtime > 0) {
                this.durationInfo.setText((CharSequence)resources.getString(2131493166, new Object[] { TimeUtils.convertSecondsToMinutes(runtime) }));
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
            this.evidence.setToIcon(evidenceDetails.getEvidenceGlyph(), 2131362001);
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
    
    protected void alignLeftGroup(int width, final int n) {
        if (this.leftGroup != null && DeviceUtils.isLandscape(this.getContext())) {
            width = (int)(KubrickUtils.getDetailsPageContentWidth(this.getContext()) * 0.667f);
            this.leftGroup.getLayoutParams().width = width;
        }
    }
    
    @Override
    protected void alignViews() {
        super.alignViews();
        int measuredHeight;
        if (this.credits == null) {
            measuredHeight = 0;
        }
        else {
            measuredHeight = this.credits.getMeasuredHeight();
        }
        float n;
        if (this.leftGroup == null) {
            n = 0.0f;
        }
        else {
            n = this.leftGroup.getMeasuredHeight() + this.getContext().getResources().getDimension(2131362001);
        }
        final int n2 = (int)n;
        this.alignHeroImage();
        this.alignShadow(measuredHeight, n2);
        this.alignLeftGroup(measuredHeight, n2);
    }
    
    @Override
    protected int calculateImageHeight() {
        return (int)(KubrickUtils.getDetailsPageContentWidth(this.getContext()) * 0.5625f);
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.dataSelectorEpisodes = (RadioButton)this.findViewById(2131165464);
        this.dataSelectorRelated = (RadioButton)this.findViewById(2131165466);
        this.dataSelectorEpisodesTop = this.findViewById(2131165463);
        this.titleImg = (AdvancedImageView)this.findViewById(2131165388);
        this.dataSelectorRelatedTop = this.findViewById(2131165465);
        this.certification = (TextView)this.findViewById(2131165426);
        this.evidenceGroup = (ViewGroup)this.findViewById(2131165467);
        this.evidenceText = (TextView)this.findViewById(2131165469);
        this.hdIcon = (IconFontTextView)this.findViewById(2131165428);
        this.durationInfo = (TextView)this.findViewById(2131165427);
        this.evidence = (IconFontTextView)this.findViewById(2131165468);
        this.dataSelectorGroup = (RadioGroup)this.findViewById(2131165462);
        this.myListLabel = (TextView)this.findViewById(2131165458);
        this.shareLabel = (TextView)this.findViewById(2131165456);
        this.myList = (TextView)this.findViewById(2131165453);
        this.genres = (TextView)this.findViewById(2131165461);
        this.year = (TextView)this.findViewById(2131165425);
        this.share = (TextView)this.findViewById(2131165455);
        this.leftGroup = this.findViewById(2131165473);
        this.shadow = this.findViewById(2131165471);
        this.credits = this.findViewById(2131165475);
        this.rate = this.findViewById(2131165454);
    }
    
    @Override
    public TextView getAddToMyListButton() {
        return this.myList;
    }
    
    @Override
    public TextView getAddToMyListButtonLabel() {
        return this.myListLabel;
    }
    
    @Override
    public TextView getRecommendButton() {
        return this.share;
    }
    
    @Override
    public TextView getRecommendButtonLabel() {
        return this.shareLabel;
    }
    
    public RadioButton getRelatedTitleButton() {
        return this.dataSelectorRelated;
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903129;
    }
    
    public void hideCredits() {
        if (this.credits != null) {
            this.credits.setVisibility(8);
        }
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
    
    protected void setEpisodesTextAsSelected() {
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131296361));
            this.dataSelectorEpisodesTop.setVisibility(0);
        }
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131296431));
            this.dataSelectorRelatedTop.setVisibility(4);
        }
    }
    
    protected void setRelatedTextAsSelected() {
        if (this.dataSelectorRelated != null) {
            this.dataSelectorRelated.setTextColor(this.getResources().getColor(2131296361));
            this.dataSelectorRelatedTop.setVisibility(0);
        }
        if (this.dataSelectorEpisodes != null) {
            this.dataSelectorEpisodes.setTextColor(this.getResources().getColor(2131296431));
            this.dataSelectorEpisodesTop.setVisibility(4);
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
    
    @Override
    public void updateDetails(final VideoDetails details, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateDetails(details, videoDetailsViewGroup$DetailsStringProvider);
        this.details = details;
        this.updateGenres(videoDetailsViewGroup$DetailsStringProvider);
        this.updateEvidence((EvidenceDetails)details);
        this.updateTitle(details);
        this.updateBasicInfo((KubrickVideo)details);
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
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, tag, IClientLogging$AssetType.boxArt, s, true, true);
        this.horzDispImg.setTag((Object)tag);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    @Override
    protected void updateTitle(final VideoDetails videoDetails) {
        if (videoDetails == null || this.title == null) {
            return;
        }
        final String titleImgUrl = videoDetails.getTitleImgUrl();
        if (StringUtils.isEmpty(titleImgUrl)) {
            this.title.setText((CharSequence)videoDetails.getTitle());
            this.title.setVisibility(0);
            this.titleImg.setVisibility(8);
            return;
        }
        this.title.setVisibility(8);
        this.titleImg.setVisibility(0);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.titleImg, titleImgUrl, IClientLogging$AssetType.heroImage, videoDetails.getTitle(), false, true, 1);
    }
}
