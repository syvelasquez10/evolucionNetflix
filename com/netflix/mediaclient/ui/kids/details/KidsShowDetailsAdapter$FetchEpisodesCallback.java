// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import android.widget.AbsListView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import android.widget.TextView;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.AbsListView$OnScrollListener;
import android.widget.BaseAdapter;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KidsShowDetailsAdapter$FetchEpisodesCallback extends LoggingManagerCallback
{
    private final int from;
    private final long requestId;
    final /* synthetic */ KidsShowDetailsAdapter this$0;
    private final int to;
    
    public KidsShowDetailsAdapter$FetchEpisodesCallback(final KidsShowDetailsAdapter this$0, final long requestId, final int from, final int to) {
        this.this$0 = this$0;
        super("KidsShowDetailsAdapter");
        this.requestId = requestId;
        this.from = from;
        this.to = to;
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        final boolean b = false;
        super.onEpisodesFetched(list, status);
        if (this.this$0.activity.destroyed()) {
            return;
        }
        if (this.requestId != this.this$0.requestId) {
            Log.v("KidsShowDetailsAdapter", "Ignoring stale request");
            return;
        }
        this.this$0.isLoading = false;
        List<EpisodeDetails> list2 = null;
        Label_0136: {
            if (!status.isError() && list != null) {
                list2 = list;
                if (list.size() != 0) {
                    break Label_0136;
                }
            }
            Log.w("KidsShowDetailsAdapter", "Error occurred");
            final ArrayList<EpisodeDetails> list3 = new ArrayList<EpisodeDetails>();
            int n = 0;
            while (true) {
                list2 = list3;
                if (n >= this.to - this.from + 1) {
                    break;
                }
                list3.add(this.this$0.errorEpisode);
                ++n;
            }
        }
        int i = b ? 1 : 0;
        if (Log.isLoggable("KidsShowDetailsAdapter", 2)) {
            Log.v("KidsShowDetailsAdapter", "Received episodes: " + list2.size() + ", from: " + this.from + ", to: " + this.to);
            final Iterator<EpisodeDetails> iterator = list2.iterator();
            while (true) {
                i = (b ? 1 : 0);
                if (!iterator.hasNext()) {
                    break;
                }
                final EpisodeDetails episodeDetails = iterator.next();
                Log.v("KidsShowDetailsAdapter", String.format("  S%02d:E%02d - %s", episodeDetails.getSeasonNumber(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle()));
            }
        }
        while (i < list2.size()) {
            this.this$0.episodes.set(this.from + i, list2.get(i));
            ++i;
        }
        this.this$0.notifyDataSetChanged();
    }
}
