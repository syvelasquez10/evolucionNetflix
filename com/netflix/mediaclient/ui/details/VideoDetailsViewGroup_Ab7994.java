// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import android.widget.LinearLayout;
import android.view.ViewStub;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.android.widgetry.widget.UserRatingButton;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.TrailerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View$OnClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class VideoDetailsViewGroup_Ab7994 extends VideoDetailsViewGroup
{
    private TextView basicInfoMaturity;
    private TextView basicInfoNumSeasons;
    private TextView basicInfoYear;
    private RadioGroup dataSelectorGroup;
    private RadioButton firstTabContainer;
    private View firstTabSelector;
    private RadioButton secondTabContainer;
    private View secondTabSelector;
    private View shareButton;
    
    public VideoDetailsViewGroup_Ab7994(final Context context) {
        super(context);
    }
    
    public VideoDetailsViewGroup_Ab7994(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public VideoDetailsViewGroup_Ab7994(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    private void setupRadioButtons() {
        final VideoDetailsViewGroup_Ab7994$2 videoDetailsViewGroup_Ab7994$2 = new VideoDetailsViewGroup_Ab7994$2(this);
        if (this.firstTabContainer != null) {
            this.firstTabContainer.setOnClickListener((View$OnClickListener)videoDetailsViewGroup_Ab7994$2);
        }
        if (this.secondTabContainer != null) {
            this.secondTabContainer.setOnClickListener((View$OnClickListener)videoDetailsViewGroup_Ab7994$2);
        }
    }
    
    private void setupTabViews() {
        if (this.details == null) {
            return;
        }
        this.dataSelectorGroup = (RadioGroup)this.findViewById(2131820738);
        if (this.details.getType() == VideoType.MOVIE) {
            if (TrailerUtils.shouldShowTrailers(this.details)) {
                this.firstTabContainer = (RadioButton)this.findViewById(2131820974);
                this.firstTabSelector = this.findViewById(2131820973);
            }
            else {
                this.firstTabContainer = (RadioButton)this.findViewById(2131820741);
                this.firstTabSelector = this.findViewById(2131820742);
            }
            ViewUtils.setVisibleOrGone((View)this.firstTabContainer.getParent(), true);
        }
        else {
            this.firstTabContainer = (RadioButton)this.findViewById(2131820739);
            this.firstTabSelector = this.findViewById(2131820740);
            if (TrailerUtils.shouldShowTrailers(this.details)) {
                this.secondTabSelector = this.findViewById(2131820973);
                this.secondTabContainer = (RadioButton)this.findViewById(2131820974);
            }
            else {
                this.secondTabSelector = this.findViewById(2131820742);
                this.secondTabContainer = (RadioButton)this.findViewById(2131820741);
            }
            ViewUtils.setVisibleOrGone((View)this.firstTabContainer.getParent(), true);
            ViewUtils.setVisibleOrGone((View)this.secondTabContainer.getParent(), true);
            this.setupRadioButtons();
        }
        ViewUtils.setVisibleOrGone(this.firstTabSelector, true);
    }
    
    private static void updateShareButtonLayout(final View view, final int n) {
        if (view != null) {
            view.setVisibility(0);
            view.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, -1));
        }
    }
    
    @Override
    protected void createActionButtons(final boolean b) {
        if (!this.actionButtonsCreated) {
            Object inflate;
            if (this.myListMdp == null || this.myListSdp == null) {
                inflate = this;
            }
            else {
                ViewStub viewStub;
                if (b) {
                    viewStub = this.myListMdp;
                }
                else {
                    viewStub = this.myListSdp;
                }
                inflate = viewStub.inflate();
            }
            this.addToMyListGroup = ((View)inflate).findViewById(2131821170);
            this.addToMyList = (TextView)((View)inflate).findViewById(2131820926);
            this.addToMyListLabel = (TextView)((View)inflate).findViewById(2131821171);
            this.mMovieDownloadButton = (DownloadButton)((View)inflate).findViewById(2131821086);
            this.userRatingButton = (UserRatingButton)((View)inflate).findViewById(2131821545);
            this.shareButton = ((View)inflate).findViewById(2131821546);
            LocalizationUtils.setRtlLayoutDirectionIfApplicable((View)this.userRatingButton);
            this.actionButtonsCreated = true;
            this.setupVideoActionsBar();
        }
    }
    
    @Override
    protected void findViews() {
        this.myListMdp = (ViewStub)this.findViewById(2131821564);
        this.myListSdp = (ViewStub)this.findViewById(2131821561);
        this.videoActionsContainer = (LinearLayout)this.findViewById(2131821544);
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131820712);
        this.matchPercentage = (TextView)this.findViewById(2131820710);
        this.basicInfoYear = (TextView)this.findViewById(2131821555);
        this.basicInfoMaturity = (TextView)this.findViewById(2131821556);
        this.basicInfoNumSeasons = (TextView)this.findViewById(2131821557);
        this.episodeBadge = (TextView)this.findViewById(2131820736);
        this.episodeTitle = (TextView)this.findViewById(2131820737);
        this.supplemental = (TextView)this.findViewById(2131820750);
        this.synopsis = (TextView)this.findViewById(2131820752);
        this.starring = (TextView)this.findViewById(2131821107);
        this.creators = (TextView)this.findViewById(2131821108);
        this.horzDispImg = (TopCropImageView)this.findViewById(2131820743);
        this.title = (TextView)this.findViewById(2131820757);
        this.imgGroup = (ViewGroup)this.findViewById(2131820927);
        this.backgroundImg = (ImageView)this.findViewById(2131821044);
        this.relatedTitle = (TextView)this.findViewById(2131820760);
        this.basicInfoBadges = (TextView)this.findViewById(2131820925);
        this.footerViewGroup = (ViewGroup)this.findViewById(2131820759);
        this.copyright = (ViewGroup)this.findViewById(2131821547);
        this.play = this.findViewById(2131820758);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903346;
    }
    
    protected void setFirstTabAsSelected() {
        if (this.firstTabContainer != null && this.firstTabSelector != null) {
            this.firstTabContainer.setTextColor(ContextCompat.getColor(this.getContext(), 2131755247));
            this.firstTabSelector.setVisibility(0);
            this.firstTabContainer.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.secondTabContainer != null && this.secondTabSelector != null) {
            this.secondTabContainer.setTextColor(ContextCompat.getColor(this.getContext(), 2131755248));
            this.secondTabSelector.setVisibility(4);
            this.secondTabContainer.setTypeface(Typeface.DEFAULT);
        }
    }
    
    protected void setSecondTabAsSelected() {
        if (this.secondTabContainer != null && this.secondTabSelector != null) {
            this.secondTabContainer.setTextColor(ContextCompat.getColor(this.getContext(), 2131755247));
            this.secondTabSelector.setVisibility(0);
            this.secondTabContainer.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.firstTabContainer != null && this.firstTabSelector != null) {
            this.firstTabContainer.setTextColor(ContextCompat.getColor(this.getContext(), 2131755248));
            this.firstTabSelector.setVisibility(4);
            this.firstTabContainer.setTypeface(Typeface.DEFAULT);
        }
    }
    
    @Override
    protected void updateBasicInfo(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (videoDetails != null && videoDetails.isAvailableToStream()) {
            if (this.basicInfoYear != null) {
                this.basicInfoYear.setText((CharSequence)String.format(Locale.getDefault(), "%d", videoDetails.getYear()));
            }
            if (this.basicInfoMaturity != null) {
                this.basicInfoMaturity.setText((CharSequence)videoDetails.getCertification());
            }
            if (this.basicInfoNumSeasons != null && videoDetails instanceof ShowDetails) {
                this.basicInfoNumSeasons.setText((CharSequence)((ShowDetails)videoDetails).getNumSeasonsLabel());
            }
            if (KidsUtils.isKidsParity(this.getContext())) {
                this.basicInfo.setTextColor(KidsUtils.getTheme().getTextColor());
            }
        }
    }
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        super.updateDetails(videoDetails, videoDetailsViewGroup$DetailsStringProvider);
        this.setupTabViews();
        if (this.shareButton != null) {
            this.shareButton.setOnClickListener((View$OnClickListener)new VideoDetailsViewGroup_Ab7994$1(this, videoDetails));
        }
    }
    
    @Override
    protected void updateVideoActionsLayout() {
        if (this.videoActionsContainer != null) {
            if (this.videoActionsContainer.getChildCount() != 4) {
                throw new IllegalStateException("Only 4 buttons expected in the video actions");
            }
            int n;
            if (this.shouldRenderDownloadButton(this.details)) {
                n = 4;
            }
            else {
                n = 3;
            }
            int n2;
            if (!DeviceUtils.isLandscape(this.getContext())) {
                n2 = Math.max(this.getMeasuredWidth() / 4, this.getResources().getDimensionPixelSize(2131427943));
            }
            else {
                n2 = Math.max(Math.min(this.getMeasuredWidth() / 4, this.getResources().getDimensionPixelSize(2131427942)), this.getResources().getDimensionPixelSize(2131427943));
            }
            for (int i = 0; i < this.videoActionsContainer.getChildCount(); ++i) {
                this.videoActionsContainer.getChildAt(i).setVisibility(8);
            }
            if (n == 4) {
                VideoDetailsViewGroup.updateButtonLayout((View)this.mMovieDownloadButton, n2);
            }
            VideoDetailsViewGroup.updateButtonLayout((View)this.userRatingButton, n2);
            VideoDetailsViewGroup.updateButtonLayout(this.addToMyListGroup, n2);
            updateShareButtonLayout(this.shareButton, n2);
        }
    }
}
