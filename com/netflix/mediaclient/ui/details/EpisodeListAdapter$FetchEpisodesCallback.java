// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Collection;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

public class EpisodeListAdapter$FetchEpisodesCallback extends LoggingManagerCallback
{
    private final int numItems;
    private final long requestId;
    final /* synthetic */ EpisodeListAdapter this$0;
    
    public EpisodeListAdapter$FetchEpisodesCallback(final EpisodeListAdapter this$0, final long requestId, final int numItems) {
        this.this$0 = this$0;
        super("EpisodeListAdapter");
        this.requestId = requestId;
        this.numItems = numItems;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        super.onEpisodesFetched(list, status);
        if (this.this$0.activity.destroyed()) {
            return;
        }
        if (this.requestId != this.this$0.requestId) {
            Log.v("EpisodeListAdapter", "Ignoring stale request");
            return;
        }
        this.this$0.hasMoreData = true;
        this.this$0.isLoading = false;
        this.this$0.onLoaded(status);
        if (status.isError()) {
            Log.w("EpisodeListAdapter", "Invalid status code");
            this.this$0.hasMoreData = false;
            this.this$0.onFetchError();
            return;
        }
        if (list == null || list.size() == 0) {
            Log.v("EpisodeListAdapter", "No details in response");
            this.this$0.hasMoreData = false;
            this.this$0.notifyDataSetChanged();
            return;
        }
        if (Log.isLoggable("EpisodeListAdapter", 2)) {
            Log.v("EpisodeListAdapter", "Got " + list.size() + " items, expected " + this.numItems);
        }
        if (list.size() < this.numItems) {
            this.this$0.hasMoreData = false;
        }
        this.this$0.updateEpisodesData(list);
    }
}
