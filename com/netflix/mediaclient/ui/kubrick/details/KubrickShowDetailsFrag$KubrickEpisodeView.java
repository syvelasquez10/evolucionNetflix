// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.MdxUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver$Callback;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.EpisodesFrag$EpisodeView;

public class KubrickShowDetailsFrag$KubrickEpisodeView extends EpisodesFrag$EpisodeView
{
    private AdvancedImageView img;
    private TextView runtime;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$KubrickEpisodeView(final KubrickShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
    }
    
    private void adjustHeight() {
        this.img.getLayoutParams().height = (int)((DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131296461) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    private void updateRuntime(final EpisodeDetails episodeDetails) {
        if (this.runtime != null && episodeDetails.getPlayable().getRuntime() > 0) {
            this.runtime.setText((CharSequence)this.getResources().getString(2131493175, new Object[] { TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime()) }));
        }
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        return this.getResources().getString(2131493245, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.img = (AdvancedImageView)this.findViewById(2131427582);
        this.runtime = (TextView)this.findViewById(2131427583);
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
        if (this.rowHeader == null) {
            return;
        }
        this.rowHeader.setOnClickListener((View$OnClickListener)new KubrickShowDetailsFrag$KubrickEpisodeView$1(this, episodeDetails));
    }
    
    @Override
    public void update(final EpisodeDetails episodeDetails, final boolean b) {
        super.update(episodeDetails, b);
        this.updateEpisodeImage(episodeDetails);
        this.updateRuntime(episodeDetails);
        this.updateTitle(episodeDetails);
        this.setupPlayButton(episodeDetails);
        this.updateProgressBar();
    }
    
    protected void updateEpisodeImage(final EpisodeDetails episodeDetails) {
        if (this.img != null) {
            final String horzDispUrl = episodeDetails.getHorzDispUrl();
            if (StringUtils.isNotEmpty(horzDispUrl)) {
                NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, horzDispUrl, IClientLogging$AssetType.boxArt, episodeDetails.getTitle(), true, true);
            }
            this.adjustHeight();
        }
    }
    
    protected void updateSynopsis(final EpisodeDetails episodeDetails) {
        if (this.synopsis == null) {
            return;
        }
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
    }
    
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title == null) {
            return;
        }
        this.title.setText(this.createTitleText(episodeDetails));
    }
}
