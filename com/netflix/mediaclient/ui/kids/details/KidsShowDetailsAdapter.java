// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import android.widget.AbsListView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
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
    private final int firstItemHeight;
    private final KidsShowDetailsFrag frag;
    private boolean isLoading;
    private final int itemHeight;
    private final AbsListView$LayoutParams itemParams;
    private final StickyListHeadersListView listView;
    private final EpisodeDetails loadingEpisode;
    private final int lr;
    private final ServiceManager manager;
    private long requestId;
    private final List<SeasonDetails> seasons;
    private final ShowDetails showDetails;
    private final int tb;
    
    public KidsShowDetailsAdapter(final KidsShowDetailsFrag frag, final ShowDetails showDetails, final List<SeasonDetails> seasons) {
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.listView = frag.getListView();
        this.manager = this.activity.getServiceManager();
        this.showDetails = showDetails;
        this.seasons = seasons;
        this.currSeasonNumber = seasons.get(0).getSeasonNumber();
        this.errorEpisode = new KidsShowDetailsAdapter$ErrorEpisodeDetails(this.activity);
        this.loadingEpisode = new KidsShowDetailsAdapter$LoadingEpisodeDetails(this.activity);
        this.episodes = new ArrayList<EpisodeDetails>(showDetails.getNumOfEpisodes());
        for (int i = 0; i < this.getCount(); ++i) {
            this.episodes.add(this.loadingEpisode);
        }
        this.lr = this.activity.getResources().getDimensionPixelSize(2131361974);
        this.tb = this.activity.getResources().getDimensionPixelSize(2131361975);
        this.itemHeight = (int)((DeviceUtils.getScreenWidthInPixels((Context)this.activity) - this.lr - this.lr) * 0.75f) + this.tb;
        this.firstItemHeight = this.itemHeight + this.tb;
        this.itemParams = new AbsListView$LayoutParams(-1, this.itemHeight);
    }
    
    private TextView createStatusTextView() {
        final Resources resources = this.activity.getResources();
        final TextView textView = new TextView((Context)this.activity);
        textView.setTextColor(resources.getColor(2131296397));
        textView.setTextSize(0, (float)resources.getDimensionPixelSize(2131361884));
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
        this.manager.getBrowse().fetchEpisodes(id, VideoType.SHOW, max, min, new KidsShowDetailsAdapter$FetchEpisodesCallback(this, this.requestId, max, min));
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
    
    public View getHeaderView(final int n, View view, final ViewGroup viewGroup) {
        Log.v("KidsShowDetailsAdapter", "Getting header view, convertView: " + view);
        if (view == null) {
            view = (View)new KidsSeasonSpinner(this.frag, this);
        }
        ((KidsSeasonSpinner)view).setSeasonNumber(this.currSeasonNumber);
        return view;
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
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        final int itemViewType = this.getItemViewType(n);
        if (!this.isLoading && (itemViewType == 0 || itemViewType == 1)) {
            this.fetchEpisodes(n);
        }
        switch (itemViewType) {
            default: {
                if (view == null) {
                    view = (View)this.createStatusTextView();
                }
                ((TextView)view).setText(2131493158);
                return view;
            }
            case 2: {
                if (view == null) {
                    view = (View)new KidsEpisodeViewGroup((Context)this.activity);
                    view.setLayoutParams((ViewGroup$LayoutParams)this.itemParams);
                }
                final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
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
                view.setPadding(lr, tb, this.lr, this.tb);
                ((KidsEpisodeViewGroup)view).update(this.getItem(n));
                return view;
            }
            case 1: {
                if (view == null) {
                    view = (View)this.createStatusTextView();
                }
                ((TextView)view).setText(2131492989);
                return view;
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
        int i = 0;
        int n = 0;
        while (i < this.seasons.size()) {
            final SeasonDetails seasonDetails = this.seasons.get(i);
            if (seasonDetails.getSeasonNumber() == currSeasonNumber) {
                Log.v("KidsShowDetailsAdapter", "Scrolling to episode, position: " + n);
                this.currSeasonNumber = currSeasonNumber;
                this.updateSeasonHeaderView(true);
                this.listView.setSelection(this.listView.getHeaderViewsCount() + n);
                break;
            }
            n += seasonDetails.getNumOfEpisodes();
            ++i;
        }
    }
}
