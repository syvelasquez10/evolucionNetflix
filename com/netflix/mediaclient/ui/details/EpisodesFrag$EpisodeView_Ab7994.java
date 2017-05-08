// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.lomo.CwView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.view.View;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public class EpisodesFrag$EpisodeView_Ab7994 extends EpisodesFrag$EpisodeView
{
    private AdvancedImageView episodeBif;
    private TextView episodeDate;
    final /* synthetic */ EpisodesFrag this$0;
    
    public EpisodesFrag$EpisodeView_Ab7994(final EpisodesFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        if (episodeDetails.isAvailableToStream()) {
            if (episodeDetails.isNSRE()) {
                return episodeDetails.getTitle();
            }
            return this.getContext().getString(2131296646, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
        }
        else {
            if (StringUtils.isEmpty(episodeDetails.getAvailabilityDateMessage())) {
                return this.getContext().getString(2131296704);
            }
            return episodeDetails.getAvailabilityDateMessage();
        }
    }
    
    protected void dispatchSetPressed(final boolean b) {
    }
    
    @Override
    protected void findViews() {
        this.episodeDate = (TextView)this.findViewById(2131820733);
        this.episodeBif = (AdvancedImageView)this.findViewById(2131821022);
        final View viewById = this.findViewById(2131821021);
        final int width = this.getResources().getDisplayMetrics().widthPixels / 3;
        viewById.getLayoutParams().width = width;
        viewById.getLayoutParams().height = (int)(width * 0.5625f);
        viewById.requestLayout();
        super.findViews();
    }
    
    @Override
    public void setChecked(final boolean b) {
        super.setChecked(true);
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.playButton == null) {
            return;
        }
        final ImageView playButton = this.playButton;
        int visibility;
        if (episodeDetails.isAvailableToStream()) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        playButton.setVisibility(visibility);
        final EpisodesFrag$EpisodeView_Ab7994$1 onClickListener = new EpisodesFrag$EpisodeView_Ab7994$1(this, episodeDetails);
        if (this.episodeBif != null) {
            this.episodeBif.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.episodeBif.getPressedStateHandler(), (View$OnClickListener)onClickListener));
            return;
        }
        this.playButton.setOnClickListener((View$OnClickListener)onClickListener);
    }
    
    @Override
    public void update(final EpisodeDetails episodeDetails, final boolean b) {
        super.update(episodeDetails, b);
        if (this.episodeNumber != null) {
            this.episodeNumber.setVisibility(8);
        }
        if (this.title != null) {
            this.title.setText(this.createTitleText(episodeDetails));
        }
        if (this.episodeBif != null) {
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.episodeBif, BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), episodeDetails, CwView.class, 0), IClientLogging$AssetType.bif, AbsEpisodeView.createTitleText(episodeDetails, this.getContext()), BrowseExperience.getImageLoaderConfig(), true, 1);
        }
        if (this.episodeDate != null && episodeDetails.isNSRE()) {
            this.episodeDate.setText((CharSequence)episodeDetails.getAvailabilityDateMessage());
            this.episodeDate.setVisibility(0);
        }
        else if (episodeDetails.isAvailableToStream() && this.episodeTime != null) {
            this.episodeTime.setText((CharSequence)AbsEpisodeView.createEpisodeDuration(episodeDetails, this.getContext()));
        }
    }
}
