// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ViewUtils;
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
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.view.ContextThemeWrapper;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.DataUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.View$OnClickListener;

class EpisodesFrag$EpisodeView$1 implements View$OnClickListener
{
    final /* synthetic */ EpisodesFrag$EpisodeView this$1;
    final /* synthetic */ EpisodeDetails val$episode;
    
    EpisodesFrag$EpisodeView$1(final EpisodesFrag$EpisodeView this$1, final EpisodeDetails val$episode) {
        this.this$1 = this$1;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$1.playEpisode(this.val$episode);
        if (this.this$1.this$0.isCoppolaExperience()) {
            ((CoppolaDetailsActivity)this.this$1.this$0.getActivity()).setEpisodesLayoutCurrentEpisodeId(this.val$episode.getId(), this.this$1.this$0.currSeasonIndex);
            this.this$1.this$0.episodeId = this.val$episode.getId();
        }
    }
}
