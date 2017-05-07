// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.Log;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AbsListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
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
        this.setTag(2131165256, (Object)true);
    }
    
    private void adjustHeight() {
        this.img.getLayoutParams().height = this.this$0.episodeImageHeight;
    }
    
    private void updateRuntime(final EpisodeDetails episodeDetails) {
        if (this.runtime != null && episodeDetails.getPlayable().getRuntime() > 0) {
            this.runtime.setText((CharSequence)this.getResources().getString(2131493159, new Object[] { TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime()) }));
        }
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails, final String s) {
        this.setTag(2131165257, (Object)episodeDetails.getSeasonNumber());
        return this.getResources().getString(2131493228, new Object[] { episodeDetails.getEpisodeNumber(), s });
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.img = (AdvancedImageView)this.findViewById(2131165427);
        this.runtime = (TextView)this.findViewById(2131165428);
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
    
    protected void updateEpisodeImage(final EpisodeDetails episodeDetails) {
        if (this.img != null) {
            final String horzDispUrl = episodeDetails.getHorzDispUrl();
            if (StringUtils.isNotEmpty(horzDispUrl)) {
                NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, horzDispUrl, IClientLogging$AssetType.boxArt, episodeDetails.getTitle(), true, true);
            }
            this.adjustHeight();
        }
    }
    
    @Override
    public void updateToEpisode(final EpisodeDetails episodeDetails, final boolean b) {
        super.updateToEpisode(episodeDetails, b);
        this.updateEpisodeImage(episodeDetails);
        this.updateRuntime(episodeDetails);
    }
}
