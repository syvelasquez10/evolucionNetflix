// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

public class EpisodesAdapter$FetchEpisodesCallback extends LoggingManagerCallback
{
    private final int numItems;
    private final long requestId;
    private final int startIndex;
    final /* synthetic */ EpisodesAdapter this$0;
    
    public EpisodesAdapter$FetchEpisodesCallback(final EpisodesAdapter this$0, final long requestId, final int startIndex, final int n) {
        this.this$0 = this$0;
        super("EpisodesAdapter");
        this.requestId = requestId;
        this.numItems = n - startIndex + 1;
        this.startIndex = startIndex;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        super.onEpisodesFetched(list, status);
        if (this.this$0.activity.destroyed()) {
            return;
        }
        if (this.requestId != this.this$0.requestId) {
            Log.v("EpisodesAdapter", "Ignoring stale request");
            return;
        }
        this.this$0.hasMoreData = true;
        this.this$0.isLoading = false;
        this.this$0.onLoaded(status);
        if (status.isError()) {
            Log.w("EpisodesAdapter", "Invalid status code");
            this.this$0.hasMoreData = false;
            this.this$0.onFetchError();
            return;
        }
        if (list == null || list.size() == 0) {
            Log.v("EpisodesAdapter", "No details in response");
            this.this$0.hasMoreData = false;
            this.this$0.notifyDataSetChanged();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("EpisodesAdapter", "Got " + list.size() + " items, expected " + this.numItems);
        }
        if (list.size() < this.numItems) {
            this.this$0.hasMoreData = false;
        }
        this.this$0.updateEpisodesData(list, this.startIndex);
        this.this$0.episodeListFrag.updateEpisodeSelection();
    }
}
