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
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$AdapterDataObserver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View;
import android.view.View$OnClickListener;

class OfflineFragment$4 implements View$OnClickListener
{
    final /* synthetic */ OfflineFragment this$0;
    final /* synthetic */ boolean val$isEpisodeFrag;
    
    OfflineFragment$4(final OfflineFragment this$0, final boolean val$isEpisodeFrag) {
        this.this$0 = this$0;
        this.val$isEpisodeFrag = val$isEpisodeFrag;
    }
    
    public void onClick(final View view) {
        if (this.this$0.isActivityValid()) {
            if (!this.val$isEpisodeFrag) {
                OfflineUiHelper.showAvailableDownloadsGenreList(this.this$0.getNetflixActivity());
                return;
            }
            DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), VideoType.SHOW, this.this$0.selectedTitleId, "", PlayContext.OFFLINE_MY_DOWNLOADS_CONTEXT, "");
        }
    }
}
