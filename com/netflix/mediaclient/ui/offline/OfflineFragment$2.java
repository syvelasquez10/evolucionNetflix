// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.Toast;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.res.Configuration;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$AdapterDataObserver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Context;
import com.netflix.mediaclient.Log;

class OfflineFragment$2 implements OfflineBaseAdapter$RowClickListener
{
    final /* synthetic */ OfflineFragment this$0;
    
    OfflineFragment$2(final OfflineFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRowClicked(final int n, final boolean b) {
        final String playableId = this.this$0.mOfflinePlayableAdapter.getPlayableId(n);
        if (this.this$0.mOfflinePlayableAdapter.isSelectionMode()) {
            this.this$0.mOfflinePlayableAdapter.toggleChecked(n, playableId);
        }
        else if (playableId != null) {
            switch (OfflineFragment$5.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$VideoType[this.this$0.mOfflinePlayableAdapter.getVideoType(n).ordinal()]) {
                default: {}
                case 1: {
                    if (b) {
                        this.this$0.startPlayerActivity(playableId, n);
                        return;
                    }
                    Log.i("OfflineFragment", "movie playIcon not visible, ignoring");
                }
                case 2: {
                    if (this.this$0.mOfflineAgentInterface == null) {
                        break;
                    }
                    final String firstEpisodeProfileId = OfflineFragment.getFirstEpisodeProfileId(this.this$0.mOfflineAgentInterface.getLatestOfflinePlayableList(), n);
                    if (this.this$0.getActivity() != null) {
                        this.this$0.startActivity(OfflineActivity.showAllDownloadsForTitle((Context)this.this$0.getActivity(), playableId, firstEpisodeProfileId, false));
                        return;
                    }
                    break;
                }
                case 3: {
                    if (b) {
                        this.this$0.startPlayerActivity(playableId, n);
                        return;
                    }
                    Log.i("OfflineFragment", "episode playIcon not visible, ignoring");
                }
            }
        }
    }
}
