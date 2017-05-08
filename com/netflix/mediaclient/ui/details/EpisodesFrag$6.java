// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
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
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.AndroidUtils;
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
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.os.Build$VERSION;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class EpisodesFrag$6 extends BroadcastReceiver
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$6(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (!this.this$0.isDestroyed() && "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH".equals(intent.getAction())) {
            final int n = intent.getIntExtra("curSeasonNumber", 1) - 1;
            final int selectedItemPosition = this.this$0.spinner.getSelectedItemPosition();
            if (n == selectedItemPosition) {
                final int n2 = intent.getIntExtra("curEpisodeNumber", 1) - 1;
                if (Log.isLoggable()) {
                    Log.v("EpisodesFrag", "Setting current episode via episode refresh to: " + n2);
                }
                this.this$0.setItemChecked(n2);
                ((EpisodesAdapter)this.this$0.episodesAdapter).updateSeasonDetails((SeasonDetails)this.this$0.spinner.getItemAtPosition(selectedItemPosition));
                return;
            }
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "Notification is for season " + n + " but spinner set to season " + selectedItemPosition);
            }
        }
    }
}
