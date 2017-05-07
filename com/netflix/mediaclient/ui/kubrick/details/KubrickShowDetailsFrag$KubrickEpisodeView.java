// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.view.View;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.EpisodeRowView;

class KubrickShowDetailsFrag$KubrickEpisodeView extends EpisodeRowView
{
    private AdvancedImageView img;
    private TextView runtime;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$KubrickEpisodeView(final KubrickShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(context, n);
        this.setTag(2131165257, (Object)true);
    }
    
    private void adjustHeight() {
        this.img.getLayoutParams().height = (int)(DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) / this.this$0.numColumns * 0.5625f);
    }
    
    private void updateRuntime(final EpisodeDetails episodeDetails) {
        if (this.runtime != null && episodeDetails.getPlayable().getRuntime() > 0) {
            this.runtime.setText((CharSequence)this.getResources().getString(2131493159, new Object[] { TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime()) }));
        }
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails, final String s) {
        this.setTag(2131165258, (Object)episodeDetails.getSeasonNumber());
        return this.getResources().getString(2131493228, new Object[] { episodeDetails.getEpisodeNumber(), s });
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.img = (AdvancedImageView)this.findViewById(2131165429);
        this.runtime = (TextView)this.findViewById(2131165430);
    }
    
    @Override
    protected int getDefaultSynopsisVisibility() {
        return 0;
    }
    
    @Override
    public void setChecked(final boolean checked) {
        this.checked = checked;
        this.setCheckedProgressBar();
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.rowHeader == null) {
            return;
        }
        this.rowHeader.setOnClickListener((View$OnClickListener)new KubrickShowDetailsFrag$KubrickEpisodeView$1(this, episodeDetails));
    }
    
    @Override
    public void update(final Video video, final Trackable trackable, final int n, final boolean b, final boolean b2) {
        this.updateEpisodeImage((EpisodeDetails)video);
        this.updateRuntime((EpisodeDetails)video);
        this.updateTitle((EpisodeDetails)video);
        this.updateSynopsis((EpisodeDetails)video);
        this.setupPlayButton((EpisodeDetails)video);
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
        this.title.setText(this.createTitleText(episodeDetails, episodeDetails.getTitle()));
    }
}
