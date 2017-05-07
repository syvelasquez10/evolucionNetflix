// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.util.StringUtils;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class EpisodesFrag$2 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$2(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Season spinner selected position: " + n);
        }
        final SeasonDetails seasonDetails = (SeasonDetails)this.this$0.spinner.getItemAtPosition(n);
        if (seasonDetails == null && Log.isLoggable("EpisodeListFrag", 5)) {
            Log.w("EpisodeListFrag", "null season details retrieved for position: " + n);
        }
        this.this$0.leWrapper.showLoadingView(true);
        ((EpisodesAdapter)this.this$0.episodesAdapter).updateShowAndSeasonDetails(this.this$0.showDetails, seasonDetails);
        this.this$0.selectedEpisodeIndex = -1;
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Log.v("EpisodeListFrag", "Season spinner - Nothing selected");
    }
}
