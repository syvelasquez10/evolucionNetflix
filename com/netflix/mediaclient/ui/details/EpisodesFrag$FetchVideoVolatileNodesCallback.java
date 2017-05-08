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
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
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
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import android.view.View;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
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
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class EpisodesFrag$FetchVideoVolatileNodesCallback extends LoggingManagerCallback
{
    private final boolean inQueue;
    private final int matchPercentage;
    final /* synthetic */ EpisodesFrag this$0;
    private final int userRating;
    private final String videoId;
    
    private EpisodesFrag$FetchVideoVolatileNodesCallback(final EpisodesFrag this$0, final String videoId, final int userRating, final int matchPercentage, final boolean inQueue) {
        this.this$0 = this$0;
        super("EpisodesFrag");
        this.videoId = videoId;
        this.userRating = userRating;
        this.matchPercentage = matchPercentage;
        this.inQueue = inQueue;
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            Log.v("EpisodesFrag", "Activity state is invalid");
        }
        else {
            if (status.isError()) {
                Log.w("EpisodesFrag", "Volatile data has invalid status code");
                return;
            }
            if (movieDetails == null) {
                Log.v("EpisodesFrag", "Volatile data no details in response");
                return;
            }
            if (!movieDetails.getId().equals(this.videoId)) {
                Log.v("EpisodesFrag", "Ignoring stale volatile data callback");
                return;
            }
            if (!TextUtils.equals((CharSequence)movieDetails.getId(), (CharSequence)this.videoId) || this.matchPercentage != movieDetails.getMatchPercentage() || this.inQueue != movieDetails.isInQueue()) {
                this.this$0.updateVolatileDataInView((ShowDetails)movieDetails);
            }
        }
    }
}
