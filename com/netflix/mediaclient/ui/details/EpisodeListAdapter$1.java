// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class EpisodeListAdapter$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ EpisodeListAdapter this$0;
    
    EpisodeListAdapter$1(final EpisodeListAdapter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.initToLoadingState();
    }
}
