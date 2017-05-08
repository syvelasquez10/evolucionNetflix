// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.widget.Toast;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.res.Configuration;
import com.netflix.mediaclient.android.app.Status;
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
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;

class OfflineFragment$1 extends NetflixActivity$ServiceManagerRunnable
{
    final /* synthetic */ OfflineFragment this$0;
    final /* synthetic */ NetflixActivity val$netflixActivity;
    
    OfflineFragment$1(final OfflineFragment this$0, final NetflixActivity val$netflixActivity) {
        this.this$0 = this$0;
        this.val$netflixActivity = val$netflixActivity;
    }
    
    @Override
    public void run(final ServiceManager serviceManager) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.val$netflixActivity)) {
            this.this$0.updateCurrentShowIdIfFound();
            this.this$0.initAdapter();
        }
    }
}
