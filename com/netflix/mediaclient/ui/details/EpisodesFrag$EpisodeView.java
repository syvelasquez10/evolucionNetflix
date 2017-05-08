// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.view.ContextThemeWrapper;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.offline.OfflineUiHelper;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.ImageView;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Playable;

public class EpisodesFrag$EpisodeView extends AbsEpisodeView
{
    Playable playable;
    final /* synthetic */ EpisodesFrag this$0;
    
    public EpisodesFrag$EpisodeView(final EpisodesFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(context, n);
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.playButton != null) {
            final ImageView playButton = this.playButton;
            int visibility;
            if (episodeDetails.isAvailableToStream()) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            playButton.setVisibility(visibility);
            this.playButton.setOnClickListener((View$OnClickListener)new EpisodesFrag$EpisodeView$1(this, episodeDetails));
            if (KidsUtils.isKidsParity(this.getContext())) {
                DrawableCompat.setTint(this.playButton.getDrawable(), KidsUtils.getTheme().getTextColor());
            }
        }
    }
    
    public void update(final EpisodeDetails episodeDetails) {
        this.playable = episodeDetails.getPlayable();
        this.update(episodeDetails, StringUtils.safeEquals(this.this$0.showDetails.getCurrentEpisodeId(), episodeDetails.getId()));
        this.setTag(2131820579, (Object)episodeDetails.getId());
        this.setTag(2131820580, (Object)episodeDetails.getType().getValue());
    }
    
    @Override
    protected void updateDownloadButton(final Playable playable) {
        if (this.episodeDownloadButton != null) {
            final ServiceManager serviceManager = this.this$0.getServiceManager();
            if (serviceManager != null && serviceManager.isReady()) {
                final OfflineAgentInterface offlineAgent = serviceManager.getOfflineAgent();
                if (offlineAgent == null) {
                    ViewUtils.setVisibleOrGone((View)this.episodeDownloadButton, false);
                    return;
                }
                this.episodeDownloadButton.setStateFromPlayable(playable, this.this$0.getNetflixActivity());
                final OfflinePlayableViewData offlinePlayableViewData = offlineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(playable.getPlayableId());
                if (offlinePlayableViewData != null && OfflineUiHelper.isFullyDownloadedAndNotWatchable(offlinePlayableViewData)) {
                    ViewUtils.setVisibleOrGone((View)this.playButton, false);
                }
            }
        }
    }
}
