// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.LinearLayout;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Bundle;
import java.util.List;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AddToListData;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class EpisodeListFrag extends NetflixDialogFrag implements ErrorWrapper.Callback, MdxMiniPlayerDialog, ManagerStatusListener
{
    private static final String EXTRA_EPISODE_ID = "extra_episode_id";
    private static final String EXTRA_EPISODE_INDEX = "extra_episode_index";
    private static final String EXTRA_SEASON_INDEX = "extra_season_index";
    private static final String EXTRA_SHOW_DETAILS = "extra_show_details";
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    private static final String TAG = "EpisodeListFrag";
    private EpisodeListAdapter adapter;
    private AddToListData.StateListener addToListWrapper;
    private int currEpisodeIndex;
    private int currSeasonIndex;
    private VideoDetailsViewGroup detailsViewGroup;
    private String episodeId;
    private final BroadcastReceiver episodeRefreshReceiver;
    private final ErrorWrapper.Callback errorCallback;
    private Handler handler;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private ListView listView;
    private ServiceManager manager;
    private long requestId;
    private ShowDetails showDetails;
    private boolean showDetailsOnLaunch;
    private String showId;
    private SeasonsSpinner spinner;
    private ViewGroup spinnerViewGroup;
    
    public EpisodeListFrag() {
        this.isLoading = true;
        this.currSeasonIndex = -1;
        this.currEpisodeIndex = -1;
        this.episodeRefreshReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (!EpisodeListFrag.this.isDestroyed() && "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH".equals(intent.getAction())) {
                    final int n = intent.getIntExtra("curSeasonNumber", 1) - 1;
                    final int selectedItemPosition = EpisodeListFrag.this.spinner.getSelectedItemPosition();
                    if (n == selectedItemPosition) {
                        final int n2 = intent.getIntExtra("curEpisodeNumber", 1) - 1;
                        final int n3 = n2 + EpisodeListFrag.this.listView.getHeaderViewsCount();
                        if (Log.isLoggable("EpisodeListFrag", 2)) {
                            Log.v("EpisodeListFrag", "Episode index: " + n2 + ", setting current episode to: " + n3);
                        }
                        EpisodeListFrag.this.listView.setItemChecked(n3, true);
                        EpisodeListFrag.this.adapter.updateSeasonDetails((SeasonDetails)EpisodeListFrag.this.spinner.getItemAtPosition(selectedItemPosition));
                        EpisodeListFrag.this.listView.smoothScrollToPosition(n3);
                        return;
                    }
                    if (Log.isLoggable("EpisodeListFrag", 2)) {
                        Log.v("EpisodeListFrag", "Notification is for season " + n + " but spinner set to season " + selectedItemPosition);
                    }
                }
            }
        };
        this.errorCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                final Activity activity = EpisodeListFrag.this.getActivity();
                if (activity instanceof ErrorWrapper.Callback) {
                    ((ErrorWrapper.Callback)activity).onRetryRequested();
                }
            }
        };
    }
    
    private static NetflixDialogFrag applyCreateArgs(final NetflixDialogFrag netflixDialogFrag, final String s, final String s2, final boolean b) {
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Creating frag for show ID: " + s);
        }
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", s);
        arguments.putString("extra_episode_id", s2);
        arguments.putBoolean("extra_show_details", b);
        netflixDialogFrag.setArguments(arguments);
        return netflixDialogFrag;
    }
    
    private void completeInitIfPossible() {
        if (this.getActivity() == null) {
            Log.v("EpisodeListFrag", "Can't complete init yet - activity is null");
            return;
        }
        if (this.manager == null) {
            Log.v("EpisodeListFrag", "Can't complete init yet - manager is null");
            return;
        }
        if (this.detailsViewGroup == null) {
            Log.v("EpisodeListFrag", "Can't complete init yet - details view is null");
            return;
        }
        if (this.showId == null) {
            Log.v("EpisodeListFrag", "Can't complete init yet - showId is null");
            return;
        }
        Log.v("EpisodeListFrag", "All clear - completing init process...");
        final TextView addToMyListButton = this.detailsViewGroup.getAddToMyListButton();
        if (this.getActivity() instanceof DetailsActivity && addToMyListButton != null) {
            this.addToListWrapper = this.manager.createAddToMyListWrapper((DetailsActivity)this.getActivity(), addToMyListButton, false);
            this.manager.registerAddToMyListListener(this.showId, this.addToListWrapper);
        }
        RecommendToFriendsFrag.addRecommendButtonHandler((NetflixActivity)this.getActivity(), this.manager, this.detailsViewGroup.getRecommendButton(), this.showId);
        this.fetchShowDetails();
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final EpisodeListFrag episodeListFrag = new EpisodeListFrag();
        episodeListFrag.setStyle(1, 2131558710);
        return applyCreateArgs(episodeListFrag, s, s2, b);
    }
    
    private ViewGroup createSeasonsSpinnerGroup() {
        (this.spinner = new SeasonsSpinner((Context)this.getActivity())).setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (Log.isLoggable("EpisodeListFrag", 2)) {
                    Log.v("EpisodeListFrag", "Season spinner selected position: " + n);
                }
                final SeasonDetails seasonDetails = (SeasonDetails)EpisodeListFrag.this.spinner.getItemAtPosition(n);
                if (seasonDetails == null && Log.isLoggable("EpisodeListFrag", 5)) {
                    Log.w("EpisodeListFrag", "null season details retrieved for position: " + n);
                }
                EpisodeListFrag.this.adapter.updateSeasonDetails(seasonDetails);
                EpisodeListFrag.this.currEpisodeIndex = -1;
                EpisodeListFrag.this.updateEpisodeSelection();
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
                Log.v("EpisodeListFrag", "Season spinner - Nothing selected");
            }
        });
        (this.spinnerViewGroup = (ViewGroup)new FrameLayout((Context)this.getActivity())).setBackgroundResource(2131296392);
        this.spinnerViewGroup.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.spinnerViewGroup.addView((View)this.spinner, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        return this.spinnerViewGroup;
    }
    
    private void fetchSeasonDetails() {
        if (this.manager == null) {
            Log.w("EpisodeListFrag", "Manager is null - can't get season details");
            return;
        }
        this.requestId = System.nanoTime();
        final int n = this.showDetails.getNumOfSeasons() - 1;
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Fetching seasons data from: " + 0 + " to " + n + ", id: " + this.requestId);
        }
        this.manager.getBrowse().fetchSeasons(this.showDetails.getId(), 0, n, new FetchSeasonsCallback(this.requestId));
    }
    
    private void fetchShowDetails() {
        if (this.manager == null) {
            Log.w("EpisodeListFrag", "Manager is null - can't get show details");
            return;
        }
        this.showLoadingView();
        this.isLoading = true;
        this.requestId = System.nanoTime();
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Fetching data for show ID: " + this.showId);
        }
        this.manager.getBrowse().fetchShowDetails(this.showId, this.episodeId, new FetchShowDetailsCallback(this.requestId));
    }
    
    private void postSetSpinnerSelectionRunnable() {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                int selection;
                if ((selection = EpisodeListFrag.this.currSeasonIndex) == -1) {
                    selection = EpisodeListFrag.this.spinner.getSeasonIndexBySeasonNumber(EpisodeListFrag.this.showDetails.getCurrentSeasonNumber());
                }
                if (selection < 0) {
                    Log.v("EpisodeListFrag", "No valid season index found");
                    return;
                }
                if (Log.isLoggable("EpisodeListFrag", 2)) {
                    Log.v("EpisodeListFrag", "Setting current season to: " + selection);
                }
                EpisodeListFrag.this.spinner.setSelection(selection);
            }
        });
    }
    
    private void registerEpisodeRefreshReceiver() {
        this.getActivity().registerReceiver(this.episodeRefreshReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH"));
    }
    
    private void showErrorView() {
        this.isLoading = false;
        this.leWrapper.showErrorView(false);
        AnimationUtils.hideView((View)this.listView, false);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(false);
        this.listView.setVisibility(4);
    }
    
    private void showStandardViews() {
        this.leWrapper.hide(false);
        if (!this.isListVisible()) {
            AnimationUtils.showView((View)this.listView, true);
        }
        this.postSetSpinnerSelectionRunnable();
        this.updateEpisodeSelection();
    }
    
    private void updateEpisodeSelection() {
        if (this.currEpisodeIndex == -1) {
            final SeasonDetails seasonDetails = (SeasonDetails)this.spinner.getItemAtPosition(this.spinner.getSelectedItemPosition());
            if (seasonDetails != null) {
                this.currEpisodeIndex = seasonDetails.getCurrentEpisodeNumber() - 1 + this.listView.getHeaderViewsCount();
            }
        }
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "selectedEpIndex: " + this.currEpisodeIndex);
        }
        this.listView.setItemChecked(this.currEpisodeIndex, true);
    }
    
    private void updateSeasonData(final List<SeasonDetails> list) {
        this.spinner.updateSeasonData(list);
        this.showStandardViews();
    }
    
    private void updateShowDetails(final ShowDetails showDetails) {
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Updating show details: " + showDetails.getTitle());
        }
        this.showDetails = showDetails;
        this.detailsViewGroup.updateDetails(showDetails, (VideoDetailsViewGroup.DetailsStringProvider)new ShowDetailsFrag.ShowDetailsStringProvider((Context)this.getActivity(), showDetails));
        this.fetchSeasonDetails();
    }
    
    ServiceManager getServiceManager() {
        return this.manager;
    }
    
    public void hideDetailViewHeader() {
        this.detailsViewGroup.setVisibility(8);
    }
    
    protected boolean isListVisible() {
        return this.listView.getVisibility() == 0;
    }
    
    @Override
    public boolean isLoadingData() {
        return this.isLoading || this.adapter.isLoadingData();
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        Log.v("EpisodeListFrag", "onActivityCreated");
        super.onActivityCreated(bundle);
        this.completeInitIfPossible();
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        final Activity activity = this.getActivity();
        if (activity instanceof DialogCanceledListenerProvider) {
            final DialogCanceledListener dialogCanceledListener = ((DialogCanceledListenerProvider)activity).getDialogCanceledListener();
            if (dialogCanceledListener != null) {
                dialogCanceledListener.onDialogCanceled(this);
            }
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.handler = new Handler();
        this.showId = this.getArguments().getString("extra_show_id");
        this.episodeId = this.getArguments().getString("extra_episode_id");
        this.showDetailsOnLaunch = this.getArguments().getBoolean("extra_show_details");
        if (bundle != null) {
            this.currSeasonIndex = bundle.getInt("extra_season_index");
            this.currEpisodeIndex = bundle.getInt("extra_episode_index");
            if (Log.isLoggable("EpisodeListFrag", 2)) {
                Log.v("EpisodeListFrag", "Restored season index as: " + this.currSeasonIndex + ", episode: " + this.currEpisodeIndex);
            }
        }
        super.onCreate(bundle);
        this.registerEpisodeRefreshReceiver();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("EpisodeListFrag", "onCreateView called");
        final View inflate = layoutInflater.inflate(2130903117, (ViewGroup)null, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        (this.listView = (ListView)inflate.findViewById(16908298)).setChoiceMode(1);
        this.listView.setDivider((Drawable)null);
        this.listView.setFocusable(false);
        this.listView.setVerticalScrollBarEnabled(false);
        ViewUtils.addActionBarPaddingView(this.listView);
        (this.detailsViewGroup = new VideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        final LinearLayout linearLayout = new LinearLayout((Context)this.getActivity());
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        linearLayout.addView((View)this.detailsViewGroup);
        if (!DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            linearLayout.setPadding(0, 0, 0, (int)this.getActivity().getResources().getDimension(2131361861));
        }
        this.listView.addHeaderView((View)linearLayout);
        if (this.showDetailsOnLaunch) {
            this.showDetailViewHeader();
        }
        else {
            this.hideDetailViewHeader();
        }
        this.spinnerViewGroup = this.createSeasonsSpinnerGroup();
        this.listView.addHeaderView((View)this.spinnerViewGroup);
        this.listView.setVisibility(4);
        this.adapter = new EpisodeListAdapter((NetflixActivity)this.getActivity(), this);
        this.listView.setAdapter((ListAdapter)this.adapter);
        this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)this.adapter);
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.manager != null && this.addToListWrapper != null) {
            this.manager.unregisterAddToMyListListener(this.showId, this.addToListWrapper);
        }
        this.getActivity().unregisterReceiver(this.episodeRefreshReceiver);
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        Log.v("EpisodeListFrag", "onManagerReady");
        super.onManagerReady(manager, status);
        this.manager = manager;
        this.completeInitIfPossible();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        super.onManagerUnavailable(serviceManager, status);
        this.manager = null;
    }
    
    public void onResume() {
        Log.v("EpisodeListFrag", "onResume");
        super.onResume();
    }
    
    @Override
    public void onRetryRequested() {
        Log.v("EpisodeListFrag", "Retry requested");
        this.fetchShowDetails();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final int selectedItemPosition = this.spinner.getSelectedItemPosition();
        final int checkedItemPosition = this.listView.getCheckedItemPosition();
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Saving season index as: " + selectedItemPosition + ", episode index: " + checkedItemPosition);
        }
        bundle.putInt("extra_season_index", selectedItemPosition);
        bundle.putInt("extra_episode_index", checkedItemPosition);
    }
    
    public void showDetailViewHeader() {
        this.detailsViewGroup.setVisibility(0);
    }
    
    private class FetchSeasonsCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchSeasonsCallback(final long requestId) {
            super("EpisodeListFrag");
            this.requestId = requestId;
        }
        
        @Override
        public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
            super.onSeasonsFetched(list, status);
            if (this.requestId != EpisodeListFrag.this.requestId) {
                Log.v("EpisodeListFrag", "Stale request - ignoring");
                return;
            }
            EpisodeListFrag.this.isLoading = false;
            if (status.isError()) {
                Log.w("EpisodeListFrag", "Invalid status code");
                EpisodeListFrag.this.showErrorView();
                return;
            }
            if (list == null) {
                Log.v("EpisodeListFrag", "No details in response");
                EpisodeListFrag.this.showErrorView();
                return;
            }
            EpisodeListFrag.this.updateSeasonData(list);
        }
    }
    
    private class FetchShowDetailsCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchShowDetailsCallback(final long requestId) {
            super("EpisodeListFrag");
            this.requestId = requestId;
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
            super.onShowDetailsFetched(showDetails, status);
            if (this.requestId != EpisodeListFrag.this.requestId) {
                Log.v("EpisodeListFrag", "Ignoring stale callback");
                return;
            }
            if (status.isError()) {
                Log.w("EpisodeListFrag", "Invalid status code");
                EpisodeListFrag.this.showErrorView();
                return;
            }
            if (showDetails == null) {
                Log.v("EpisodeListFrag", "No details in response");
                EpisodeListFrag.this.showErrorView();
                return;
            }
            EpisodeListFrag.this.updateShowDetails(showDetails);
        }
    }
}
