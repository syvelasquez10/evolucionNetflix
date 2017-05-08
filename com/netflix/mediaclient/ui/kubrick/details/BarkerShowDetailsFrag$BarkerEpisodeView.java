// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.ui.details.AbsEpisodeView;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View;
import com.netflix.mediaclient.ui.details.EpisodesFrag$EpisodeView;

public class BarkerShowDetailsFrag$BarkerEpisodeView extends EpisodesFrag$EpisodeView
{
    private View badgeContainer;
    private View episodePreviewContainer;
    protected AdvancedImageView image;
    protected View progressBarBackground;
    private TextView runtime;
    final /* synthetic */ BarkerShowDetailsFrag this$0;
    protected View unavailable;
    
    public BarkerShowDetailsFrag$BarkerEpisodeView(final BarkerShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
    }
    
    private void expandSynopsis() {
        this.synopsis.setMaxLines(10);
    }
    
    private void updateRuntime(final EpisodeDetails episodeDetails) {
        if (this.runtime != null) {
            if (!episodeDetails.isAvailableToStream() || episodeDetails.getPlayable().getRuntime() <= 0) {
                this.runtime.setVisibility(8);
                return;
            }
            this.runtime.setVisibility(0);
            this.runtime.setText((CharSequence)this.getResources().getString(2131231123, new Object[] { TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime()) }));
        }
    }
    
    private void updateSelection(final EpisodeDetails episodeDetails) {
        boolean b;
        if (episodeDetails.getPlayable().getPlayableBookmarkPosition() > 0) {
            b = true;
        }
        else {
            b = false;
        }
        final Resources resources = this.getResources();
        int n;
        if (this.isCurrentEpisode && b) {
            n = 2131624162;
        }
        else {
            n = 2131623955;
        }
        final int color = resources.getColor(n);
        if (this.synopsis != null) {
            this.synopsis.setTextColor(color);
            if (this.isCurrentEpisode) {
                this.expandSynopsis();
            }
        }
        if (this.runtime != null) {
            this.runtime.setTextColor(color);
        }
    }
    
    protected void adjustEpisodeImageHeight() {
        if (this.this$0.getActivity() == null) {
            return;
        }
        if (BrowseExperience.isDisplayPageRefresh()) {
            this.episodePreviewContainer.getLayoutParams().height = (int)(this.episodePreviewContainer.getLayoutParams().width * 0.5625f);
            return;
        }
        this.image.getLayoutParams().height = (int)((BarkerUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - (this.this$0.getNumColumns() + 1.0f) * this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362167)) / this.this$0.getNumColumns() * 0.5625f);
    }
    
    protected void adjustEpisodeImageWidth() {
        if (BarkerHelper.isInTest((Context)this.this$0.getActivity())) {
            this.episodePreviewContainer.getLayoutParams().width = this.this$0.barker.getEpisodeImageWidth();
        }
    }
    
    protected void disablePlay() {
        if (this.playButton != null) {
            this.playButton.setVisibility(8);
        }
        if (this.unavailable != null) {
            this.unavailable.setVisibility(0);
        }
        this.image.setOnClickListener((View$OnClickListener)null);
        this.image.setEnabled(false);
    }
    
    protected void enablePlay(final EpisodeDetails episodeDetails) {
        if (this.playButton != null) {
            this.playButton.setVisibility(0);
        }
        if (this.unavailable != null) {
            this.unavailable.setVisibility(8);
        }
        this.image.setEnabled(true);
        this.getPressableView().setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.getPressedStateHandler(), (View$OnClickListener)new BarkerShowDetailsFrag$BarkerEpisodeView$1(this, episodeDetails)));
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.image = (AdvancedImageView)this.findViewById(2131689620);
        this.runtime = (TextView)this.findViewById(2131689624);
        this.progressBarBackground = this.findViewById(2131689746);
        this.unavailable = this.findViewById(2131689622);
        this.episodePreviewContainer = this.findViewById(2131689619);
        this.badgeContainer = this.findViewById(2131689625);
    }
    
    @Override
    protected int getDefaultSynopsisVisibility() {
        return 0;
    }
    
    protected View getPressableView() {
        return (View)this.image;
    }
    
    protected PressedStateHandler getPressedStateHandler() {
        return this.image.getPressedStateHandler();
    }
    
    @Override
    public void setChecked(final boolean checked) {
        this.checked = checked;
        this.updateProgressBar();
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.image == null) {
            return;
        }
        if (episodeDetails.isAvailableToStream()) {
            this.enablePlay(episodeDetails);
            return;
        }
        this.disablePlay();
    }
    
    @Override
    public void update(final EpisodeDetails episodeDetails, final boolean b) {
        super.update(episodeDetails, b);
        this.updateEpisodeImage(episodeDetails);
        this.updateRuntime(episodeDetails);
        this.updateTitle(episodeDetails);
        this.updateProgressBar();
        this.setupPlayButton(episodeDetails);
        this.updateSelection(episodeDetails);
    }
    
    protected void updateEpisodeImage(final EpisodeDetails episodeDetails) {
        if (this.image != null) {
            final String interestingSmallUrl = episodeDetails.getInterestingSmallUrl();
            if (StringUtils.isNotEmpty(interestingSmallUrl)) {
                NetflixActivity.getImageLoader(this.getContext()).showImg(this.image, interestingSmallUrl, IClientLogging$AssetType.boxArt, episodeDetails.getTitle(), BrowseExperience.getImageLoaderConfig(), true, 1);
            }
            this.adjustEpisodeImageWidth();
            this.adjustEpisodeImageHeight();
            this.progressBar.getLayoutParams().width = this.this$0.barker.getEpisodeImageWidth();
        }
    }
    
    @Override
    protected void updateProgressBar() {
        super.updateProgressBar();
        if (this.progressBarBackground == null || this.progressBar == null || this.image == null) {
            return;
        }
        if (this.progressBar.getVisibility() == 0) {
            this.progressBarBackground.setVisibility(0);
            this.progressBarBackground.getLayoutParams().height = this.image.getLayoutParams().height / 2;
            this.episodePreviewContainer.getLayoutParams().width = this.this$0.barker.getEpisodeImageWidth();
            return;
        }
        this.progressBarBackground.setVisibility(8);
    }
    
    @Override
    protected void updateSynopsis(final EpisodeDetails episodeDetails) {
        if (this.synopsis == null || this.this$0.getActivity() == null) {
            return;
        }
        if (episodeDetails.isAvailableToStream()) {
            this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        }
        else {
            this.synopsis.setText((CharSequence)episodeDetails.getAvailabilityDateMessage());
        }
        this.synopsis.getLayoutParams().width = this.this$0.barker.getSynopsisWidth();
        final TextView synopsis = this.synopsis;
        int maxLines;
        if (DeviceUtils.isPortrait((Context)this.this$0.getActivity())) {
            maxLines = 3;
        }
        else {
            maxLines = 5;
        }
        synopsis.setMaxLines(maxLines);
        this.synopsis.setOnClickListener((View$OnClickListener)new BarkerShowDetailsFrag$BarkerEpisodeView$2(this));
    }
    
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title != null && this.this$0.getActivity() != null) {
            this.title.setTextColor(this.getResources().getColor(2131624099));
            this.title.setText(AbsEpisodeView.createTitleText(episodeDetails, (Context)this.this$0.getActivity()));
            if (this.badgeContainer != null) {
                this.badgeContainer.getLayoutParams().width = this.this$0.barker.getSynopsisWidth();
            }
        }
    }
}
