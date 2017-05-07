// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.AdapterView$OnItemSelectedListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.EpisodesFrag$EpisodeView;

public class KubrickShowDetailsFrag$KubrickEpisodeView extends EpisodesFrag$EpisodeView
{
    protected AdvancedImageView image;
    protected View progressBarBackground;
    private TextView runtime;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    private View unavailable;
    
    public KubrickShowDetailsFrag$KubrickEpisodeView(final KubrickShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
    }
    
    private void updateRuntime(final EpisodeDetails episodeDetails) {
        if (this.runtime != null) {
            if (!episodeDetails.isAvailableToStream() || episodeDetails.getPlayable().getRuntime() <= 0) {
                this.runtime.setVisibility(8);
                return;
            }
            this.runtime.setVisibility(0);
            this.runtime.setText((CharSequence)this.getResources().getString(2131165481, new Object[] { TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime()) }));
        }
    }
    
    protected void adjustHeight() {
        this.image.getLayoutParams().height = (int)((KubrickUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131296507) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        return this.getResources().getString(2131165384, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
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
        this.image.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.image.getPressedStateHandler(), (View$OnClickListener)new KubrickShowDetailsFrag$KubrickEpisodeView$1(this, episodeDetails)));
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.image = (AdvancedImageView)this.findViewById(2131624225);
        this.runtime = (TextView)this.findViewById(2131624228);
        this.progressBarBackground = this.findViewById(2131624226);
        this.unavailable = this.findViewById(2131624227);
    }
    
    @Override
    protected int getDefaultSynopsisVisibility() {
        return 0;
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
    }
    
    protected void updateEpisodeImage(final EpisodeDetails episodeDetails) {
        if (this.image != null) {
            final String interestingUrl = episodeDetails.getInterestingUrl();
            if (StringUtils.isNotEmpty(interestingUrl)) {
                NetflixActivity.getImageLoader(this.getContext()).showImg(this.image, interestingUrl, IClientLogging$AssetType.boxArt, episodeDetails.getTitle(), BrowseExperience.getImageLoaderConfig(), true, 1);
            }
            this.adjustHeight();
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
            return;
        }
        this.progressBarBackground.setVisibility(8);
    }
    
    @Override
    protected void updateSynopsis(final EpisodeDetails episodeDetails) {
        if (this.synopsis == null) {
            return;
        }
        if (episodeDetails.isAvailableToStream()) {
            this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
            return;
        }
        this.synopsis.setText((CharSequence)episodeDetails.getAvailabilityDateMessage());
    }
    
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title == null) {
            return;
        }
        this.title.setTextColor(this.getResources().getColor(2131558545));
        this.title.setText(this.createTitleText(episodeDetails));
    }
}
