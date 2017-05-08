// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.support.v7.widget.RecyclerView$AdapterDataObserver;

class OfflineFragment$3 extends RecyclerView$AdapterDataObserver
{
    final /* synthetic */ OfflineFragment this$0;
    
    OfflineFragment$3(final OfflineFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onChanged() {
        this.this$0.updateEmptyState();
    }
}
