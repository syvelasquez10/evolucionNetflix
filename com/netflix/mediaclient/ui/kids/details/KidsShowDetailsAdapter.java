// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import java.util.Iterator;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.details.DummyEpisodeDetails;
import android.widget.AbsListView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import android.widget.TextView;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.widget.AbsListView$OnScrollListener;
import android.widget.BaseAdapter;

public class KidsShowDetailsAdapter extends BaseAdapter implements AbsListView$OnScrollListener, StickyListHeadersAdapter
{
    private static final int NUM_EPISODES_TO_FETCH_PER_BATCH = 60;
    private static final String TAG = "KidsShowDetailsAdapter";
    private static final int TYPE_EPISODE = 2;
    private static final int TYPE_ERROR = 1;
    private static final int TYPE_LOADING = 0;
    private final NetflixActivity activity;
    private int currFocusIndex;
    private int currSeasonNumber;
    private final List<EpisodeDetails> episodes;
    private final EpisodeDetails errorEpisode;
    private int firstItemHeight;
    private final KidsShowDetailsFrag frag;
    private boolean isLoading;
    private int itemHeight;
    private final AbsListView$LayoutParams itemParams;
    private final StickyListHeadersListView listView;
    private final EpisodeDetails loadingEpisode;
    private int lr;
    private final ServiceManager manager;
    private long requestId;
    private final List<SeasonDetails> seasons;
    private final ShowDetails showDetails;
    private int tb;
    
    public KidsShowDetailsAdapter(final KidsShowDetailsFrag frag, final ShowDetails showDetails, final List<SeasonDetails> seasons) {
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.listView = frag.getListView();
        this.manager = this.activity.getServiceManager();
        this.showDetails = showDetails;
        this.seasons = seasons;
        this.currSeasonNumber = seasons.get(0).getSeasonNumber();
        this.errorEpisode = new ErrorEpisodeDetails(this.activity);
        this.loadingEpisode = new LoadingEpisodeDetails(this.activity);
        this.episodes = new ArrayList<EpisodeDetails>(showDetails.getNumOfEpisodes());
        for (int i = 0; i < this.getCount(); ++i) {
            this.episodes.add(this.loadingEpisode);
        }
        this.lr = this.activity.getResources().getDimensionPixelSize(2131361971);
        this.tb = this.activity.getResources().getDimensionPixelSize(2131361972);
        this.itemHeight = (int)((DeviceUtils.getScreenWidthInPixels((Context)this.activity) - this.lr - this.lr) * 0.75f) + this.tb;
        this.firstItemHeight = this.itemHeight + this.tb;
        this.itemParams = new AbsListView$LayoutParams(-1, this.itemHeight);
    }
    
    private TextView createStatusTextView() {
        final Resources resources = this.activity.getResources();
        final TextView textView = new TextView((Context)this.activity);
        textView.setTextColor(resources.getColor(2131296410));
        textView.setTextSize(0, (float)resources.getDimensionPixelSize(2131361866));
        textView.setGravity(17);
        textView.setLayoutParams((ViewGroup$LayoutParams)this.itemParams);
        return textView;
    }
    
    private void fetchEpisodes(int max) {
        if (this.manager == null) {
            Log.w("KidsShowDetailsAdapter", "Manager is null - can't get show details");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        final String id = this.showDetails.getId();
        max = Math.max(max - 30, 0);
        final int min = Math.min(max + 60 - 1, this.getCount());
        if (Log.isLoggable("KidsShowDetailsAdapter", 2)) {
            Log.v("KidsShowDetailsAdapter", String.format("Fetching episodes, id: %s, from: %d, to: %d", id, max, min));
        }
        this.manager.getBrowse().fetchEpisodes(id, max, min, new FetchEpisodesCallback(this.requestId, max, min));
    }
    
    private void updateSeasonHeaderView(final boolean b) {
        if (this.listView.getHeader() != null) {
            Log.v("KidsShowDetailsAdapter", "Updating season header view, useSmoothScroll: " + b);
            ((KidsSeasonSpinner)this.listView.getHeader()).setSeasonNumber(this.currSeasonNumber);
        }
    }
    
    private boolean updateSeasonNumber() {
        if (this.currFocusIndex >= 0) {
            final EpisodeDetails item = this.getItem(this.currFocusIndex);
            if (item != null && item != this.loadingEpisode && item != this.errorEpisode) {
                final int seasonNumber = item.getSeasonNumber();
                if (this.currSeasonNumber != seasonNumber) {
                    this.currSeasonNumber = seasonNumber;
                    if (Log.isLoggable("KidsShowDetailsAdapter", 2)) {
                        Log.v("KidsShowDetailsAdapter", "Season number has changed to: " + this.currSeasonNumber);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getCount() {
        return this.showDetails.getNumOfEpisodes();
    }
    
    public long getHeaderId(final int n) {
        return 0L;
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        Log.v("KidsShowDetailsAdapter", "Getting header view, convertView: " + view);
        Object o = view;
        if (view == null) {
            o = new KidsSeasonSpinner(this.frag, this);
        }
        ((KidsSeasonSpinner)o).setSeasonNumber(this.currSeasonNumber);
        return (View)o;
    }
    
    public EpisodeDetails getItem(final int n) {
        return this.episodes.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        final EpisodeDetails item = this.getItem(n);
        if (item == this.loadingEpisode) {
            return 0;
        }
        if (item == this.errorEpisode) {
            return 1;
        }
        return 2;
    }
    
    public List<SeasonDetails> getSeasons() {
        return this.seasons;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final int itemViewType = this.getItemViewType(n);
        if (!this.isLoading && (itemViewType == 0 || itemViewType == 1)) {
            this.fetchEpisodes(n);
        }
        switch (itemViewType) {
            default: {
                Object statusTextView = view;
                if (view == null) {
                    statusTextView = this.createStatusTextView();
                }
                ((TextView)statusTextView).setText(2131493184);
                return (View)statusTextView;
            }
            case 2: {
                Object o = view;
                if (view == null) {
                    o = new KidsEpisodeViewGroup((Context)this.activity);
                    ((View)o).setLayoutParams((ViewGroup$LayoutParams)this.itemParams);
                }
                final ViewGroup$LayoutParams layoutParams = ((View)o).getLayoutParams();
                int height;
                if (n == 0) {
                    height = this.firstItemHeight;
                }
                else {
                    height = this.itemHeight;
                }
                layoutParams.height = height;
                final int lr = this.lr;
                int tb;
                if (n == 0) {
                    tb = this.tb;
                }
                else {
                    tb = 0;
                }
                ((View)o).setPadding(lr, tb, this.lr, this.tb);
                ((KidsEpisodeViewGroup)o).update(this.getItem(n));
                return (View)o;
            }
            case 1: {
                Object statusTextView2 = view;
                if (view == null) {
                    statusTextView2 = this.createStatusTextView();
                }
                ((TextView)statusTextView2).setText(2131492984);
                return (View)statusTextView2;
            }
        }
    }
    
    public int getViewTypeCount() {
        return 3;
    }
    
    public boolean isLoading() {
        return this.isLoading;
    }
    
    public void notifyDataSetChanged() {
        Log.v("KidsShowDetailsAdapter", "notifyDataSetChanged()");
        super.notifyDataSetChanged();
        this.updateSeasonNumber();
        this.updateSeasonHeaderView(false);
    }
    
    public void onScroll(final AbsListView absListView, int currFocusIndex, final int n, final int n2) {
        currFocusIndex = Math.min(currFocusIndex + 1, this.getCount()) - this.listView.getHeaderViewsCount();
        if (this.currFocusIndex != currFocusIndex) {
            this.currFocusIndex = currFocusIndex;
            if (Log.isLoggable("KidsShowDetailsAdapter", 2)) {
                Log.v("KidsShowDetailsAdapter", "New item focused in list view, index: " + currFocusIndex);
            }
            if (this.updateSeasonNumber()) {
                this.updateSeasonHeaderView(true);
            }
        }
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
    }
    
    public void selectSeasonByNumber(final int currSeasonNumber) {
        int n = 0;
        for (int i = 0; i < this.seasons.size(); ++i) {
            final SeasonDetails seasonDetails = this.seasons.get(i);
            if (seasonDetails.getSeasonNumber() == currSeasonNumber) {
                Log.v("KidsShowDetailsAdapter", "Scrolling to episode, position: " + n);
                this.currSeasonNumber = currSeasonNumber;
                this.updateSeasonHeaderView(true);
                this.listView.setSelection(this.listView.getHeaderViewsCount() + n);
                break;
            }
            n += seasonDetails.getNumOfEpisodes();
        }
    }
    
    private static class ErrorEpisodeDetails extends DummyEpisodeDetails
    {
        private NetflixActivity activity;
        
        public ErrorEpisodeDetails(final NetflixActivity activity) {
            super(-1);
            this.activity = activity;
        }
        
        @Override
        public String getTitle() {
            return this.activity.getString(2131492984);
        }
    }
    
    private class FetchEpisodesCallback extends LoggingManagerCallback
    {
        private int from;
        private final long requestId;
        private int to;
        
        public FetchEpisodesCallback(final long requestId, final int from, final int to) {
            super("KidsShowDetailsAdapter");
            this.requestId = requestId;
            this.from = from;
            this.to = to;
        }
        
        @Override
        public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
            super.onEpisodesFetched(list, status);
            if (KidsShowDetailsAdapter.this.activity.destroyed()) {
                return;
            }
            if (this.requestId != KidsShowDetailsAdapter.this.requestId) {
                Log.v("KidsShowDetailsAdapter", "Ignoring stale request");
                return;
            }
            KidsShowDetailsAdapter.this.isLoading = false;
            List<EpisodeDetails> list2 = null;
            Label_0133: {
                if (!status.isError() && list != null) {
                    list2 = list;
                    if (list.size() != 0) {
                        break Label_0133;
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
                    list3.add(KidsShowDetailsAdapter.this.errorEpisode);
                    ++n;
                }
            }
            if (Log.isLoggable("KidsShowDetailsAdapter", 2)) {
                Log.v("KidsShowDetailsAdapter", "Received episodes: " + list2.size() + ", from: " + this.from + ", to: " + this.to);
                for (final EpisodeDetails episodeDetails : list2) {
                    Log.v("KidsShowDetailsAdapter", String.format("  S%02d:E%02d - %s", episodeDetails.getSeasonNumber(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle()));
                }
            }
            for (int i = 0; i < list2.size(); ++i) {
                KidsShowDetailsAdapter.this.episodes.set(this.from + i, list2.get(i));
            }
            KidsShowDetailsAdapter.this.notifyDataSetChanged();
        }
    }
    
    private static class LoadingEpisodeDetails extends DummyEpisodeDetails
    {
        private NetflixActivity activity;
        
        public LoadingEpisodeDetails(final NetflixActivity activity) {
            super(-1);
            this.activity = activity;
        }
        
        @Override
        public String getTitle() {
            return this.activity.getString(2131493184);
        }
    }
}
