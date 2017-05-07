// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class EpisodesFrag extends NetflixDialogFrag implements ErrorWrapper$Callback, ManagerStatusListener, DetailsActivity$Reloader, ServiceManagerProvider, VideoDetailsViewGroup$VideoDetailsViewGroupProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final String EXTRA_EPISODE_ID = "extra_episode_id";
    private static final String EXTRA_EPISODE_INDEX = "extra_episode_index";
    private static final String EXTRA_FORCE_SHOW_SEASONS_SELECTOR = "extra_show_episodes_selector";
    private static final String EXTRA_SEASON_INDEX = "extra_season_index";
    protected static final String EXTRA_SHOW_DETAILS = "extra_show_details";
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    private static final String TAG = "EpisodesFrag";
    private AddToListData$StateListener addToListWrapper;
    private int currSeasonIndex;
    protected VideoDetailsViewGroup detailsViewGroup;
    private String episodeId;
    protected final BroadcastReceiver episodeRefreshReceiver;
    protected RecyclerViewHeaderAdapter episodesAdapter;
    protected final ErrorWrapper$Callback errorCallback;
    protected Handler handler;
    private int initialEpisodeIndex;
    private boolean isLoading;
    protected LoadingAndErrorWrapper leWrapper;
    private ServiceManager manager;
    protected int numColumns;
    protected RecyclerView recyclerView;
    private long requestId;
    private int selectedEpisodeIndex;
    protected ShowDetails showDetails;
    protected boolean showDetailsOnLaunch;
    private String showId;
    protected SeasonsSpinner spinner;
    protected ViewGroup spinnerViewGroup;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    
    public EpisodesFrag() {
        this.selectedEpisodeIndex = -1;
        this.initialEpisodeIndex = -1;
        this.currSeasonIndex = -1;
        this.isLoading = true;
        this.numColumns = 1;
        this.viewCreatorEpisodes = new EpisodesFrag$1(this);
        this.episodeRefreshReceiver = new EpisodesFrag$7(this);
        this.errorCallback = new EpisodesFrag$8(this);
    }
    
    protected static NetflixDialogFrag applyCreateArgs(final NetflixDialogFrag netflixDialogFrag, final String s, final String s2, final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Creating frag for show ID: " + s);
        }
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", s);
        arguments.putString("extra_episode_id", s2);
        arguments.putBoolean("extra_show_episodes_selector", b2);
        arguments.putBoolean("extra_show_details", b);
        netflixDialogFrag.setArguments(arguments);
        return netflixDialogFrag;
    }
    
    private void completeInitIfPossible() {
        if (this.getActivity() == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - activity is null");
            return;
        }
        if (this.manager == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - manager is null");
            return;
        }
        if (this.detailsViewGroup == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - details view is null");
            return;
        }
        if (this.showId == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - showId is null");
            return;
        }
        Log.v("EpisodesFrag", "All clear - completing init process...");
        this.showLoadingView();
        this.fetchShowDetails();
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final EpisodesFrag episodesFrag = new EpisodesFrag();
        episodesFrag.setStyle(1, 2131558712);
        return applyCreateArgs(episodesFrag, s, s2, b, false);
    }
    
    public static NetflixDialogFrag createEpisodes(final String s, final String s2, final boolean b) {
        final EpisodesFrag episodesFrag = new EpisodesFrag();
        episodesFrag.setStyle(1, 2131558712);
        return applyCreateArgs(episodesFrag, s, s2, b, true);
    }
    
    private void fetchSeasonDetails() {
        if (this.manager == null) {
            Log.w("EpisodesFrag", "Manager is null - can't get season details");
            return;
        }
        this.requestId = System.nanoTime();
        final int n = this.showDetails.getNumOfSeasons() - 1;
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Fetching seasons data from: " + 0 + " to " + n + ", id: " + this.requestId);
        }
        this.manager.getBrowse().fetchSeasons(this.showDetails.getId(), 0, n, new EpisodesFrag$FetchSeasonsCallback(this, this.requestId));
    }
    
    private void fetchShowDetails() {
        if (this.manager == null) {
            Log.w("EpisodesFrag", "Manager is null - can't get show details");
            return;
        }
        if (this.getNetflixActivity() == null) {
            Log.w("EpisodesFrag", "Activity is null - can't get show details");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Fetching data for show ID: " + this.showId);
        }
        this.makeFetchShowDetailsRequest(this.manager, this.showId, this.episodeId, BrowseExperience.shouldLoadKubrickLeaves(), this.requestId);
    }
    
    private void registerEpisodeRefreshReceiver() {
        this.getActivity().registerReceiver(this.episodeRefreshReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH"));
    }
    
    private void setItemChecked(final int n) {
        if (this.recyclerView != null && this.episodesAdapter != null) {
            this.recyclerView.post((Runnable)new EpisodesFrag$5(this, n));
        }
    }
    
    private void showLoadingView() {
        Log.v("EpisodesFrag", "Showing loading view");
        this.leWrapper.showLoadingView(false);
        this.getRecyclerView().setVisibility(4);
    }
    
    private void showStandardViews() {
        if (!this.isListVisible()) {
            AnimationUtils.showView((View)this.getRecyclerView(), true);
        }
        this.postSetSpinnerSelectionRunnable();
    }
    
    protected void addSpinnerAsHeader() {
        if (this.recyclerView != null) {
            ((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).addHeaderView((View)this.spinnerViewGroup);
        }
    }
    
    protected void addSpinnerToDetailsGroup() {
        if (this.detailsViewGroup != null && this.spinnerViewGroup != null) {
            final ViewGroup footerViewGroup = this.detailsViewGroup.getFooterViewGroup();
            if (footerViewGroup != null) {
                footerViewGroup.addView((View)this.spinnerViewGroup);
            }
        }
    }
    
    protected ViewGroup createSeasonsSpinnerGroup() {
        SeasonsSpinner spinner;
        if (Build$VERSION.SDK_INT >= 21) {
            spinner = new SeasonsSpinnerLollipop((Context)this.getActivity());
        }
        else {
            spinner = new SeasonsSpinner((Context)this.getActivity());
        }
        this.spinner = spinner;
        this.setupSeasonsSpinnerAdapter();
        this.setupSeasonsSpinnerListener();
        (this.spinnerViewGroup = (ViewGroup)new FrameLayout((Context)this.getActivity())).setBackgroundResource(2131230820);
        this.spinnerViewGroup.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, (int)this.getResources().getDimension(2131296410)));
        this.spinnerViewGroup.addView((View)this.spinner, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        return this.spinnerViewGroup;
    }
    
    protected int getCheckedItemPosition() {
        if (this.episodesAdapter == null) {
            return -1;
        }
        return this.episodesAdapter.getCheckedItemPosition();
    }
    
    protected long getCurrRequestId() {
        return this.requestId;
    }
    
    protected RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
    
    @Override
    public ServiceManager getServiceManager() {
        return this.manager;
    }
    
    String getShowId() {
        return this.showId;
    }
    
    @Override
    public VideoDetailsViewGroup getVideoDetailsViewGroup() {
        return this.detailsViewGroup;
    }
    
    protected int getlayoutId() {
        return 2130903184;
    }
    
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new VideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    protected void initDetailsViewGroupAsHeader() {
        this.initDetailsViewGroup();
        ((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).addHeaderView((View)this.detailsViewGroup);
        int detailViewGroupVisibility;
        if (this.showDetailsOnLaunch) {
            detailViewGroupVisibility = 0;
        }
        else {
            detailViewGroupVisibility = 8;
        }
        this.setDetailViewGroupVisibility(detailViewGroupVisibility);
    }
    
    protected boolean isListVisible() {
        return this.recyclerView.getVisibility() == 0;
    }
    
    @Override
    public boolean isLoadingData() {
        return this.isLoading || ((LoadingStatus)this.episodesAdapter).isLoadingData();
    }
    
    protected void makeFetchShowDetailsRequest(final ServiceManager serviceManager, final String s, final String s2, final boolean b, final long n) {
        serviceManager.getBrowse().fetchShowDetails(s, s2, b, new EpisodesFrag$FetchShowDetailsCallback(this, n));
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        Log.v("EpisodesFrag", "onActivityCreated");
        super.onActivityCreated(bundle);
        this.completeInitIfPossible();
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        final Activity activity = this.getActivity();
        if (activity instanceof NetflixDialogFrag$DialogCanceledListenerProvider) {
            final NetflixDialogFrag$DialogCanceledListener dialogCanceledListener = ((NetflixDialogFrag$DialogCanceledListenerProvider)activity).getDialogCanceledListener();
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
            this.initialEpisodeIndex = bundle.getInt("extra_episode_index");
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "Restored season index as: " + this.currSeasonIndex + ", episode: " + this.initialEpisodeIndex);
            }
        }
        super.onCreate(bundle);
        this.registerEpisodeRefreshReceiver();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("EpisodesFrag", "onCreateView called");
        final View inflate = layoutInflater.inflate(this.getlayoutId(), (ViewGroup)null, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        this.setupRecyclerView(inflate);
        this.initDetailsViewGroupAsHeader();
        this.setupSeasonsSpinnerGroup();
        this.detailsViewGroup.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new EpisodesFrag$2(this));
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
        Log.v("EpisodesFrag", "onManagerReady");
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
        Log.v("EpisodesFrag", "onResume");
        super.onResume();
    }
    
    @Override
    public void onRetryRequested() {
        Log.v("EpisodesFrag", "Retry requested");
        if (this.manager != null) {
            this.showLoadingView();
            this.fetchShowDetails();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final int selectedItemPosition = this.spinner.getSelectedItemPosition();
        final int checkedItemPosition = this.getCheckedItemPosition();
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Saving season index as: " + selectedItemPosition + ", episode index: " + checkedItemPosition);
        }
        bundle.putInt("extra_season_index", selectedItemPosition);
        bundle.putInt("extra_episode_index", checkedItemPosition);
    }
    
    protected void postSetSpinnerSelectionRunnable() {
        this.handler.post((Runnable)new EpisodesFrag$6(this));
    }
    
    @Override
    public void reload() {
        Log.v("EpisodesFrag", "reload()");
        this.fetchShowDetails();
    }
    
    public void setDetailViewGroupVisibility(final int visibility) {
        this.detailsViewGroup.setVisibility(visibility);
    }
    
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && (DeviceUtils.isNotTabletByContext((Context)this.getActivity()) || DeviceUtils.isPortrait((Context)this.getActivity()))) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), (View)this.detailsViewGroup.getHeroImage(), null, this.recyclerView.getResources().getColor(2131230827), 0, null);
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                return onScrollListener;
            }
        }
        return null;
    }
    
    protected void setupRecyclerView(final View view) {
        (this.recyclerView = (RecyclerView)view.findViewById(16908298)).setFocusable(false);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.recyclerView.setVisibility(4);
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
        this.setupRecyclerViewItemDecoration();
    }
    
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new EpisodesAdapter((NetflixActivity)this.getActivity(), this, this.viewCreatorEpisodes);
        if (DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity())) {
            this.episodesAdapter.addHeaderView(ViewUtils.createActionBarDummyView(this.getNetflixActivity()));
        }
        this.recyclerView.setAdapter(this.episodesAdapter);
        ((EpisodesAdapter)this.episodesAdapter).setLoadingAndError(this.leWrapper);
        this.episodesAdapter.setSingleChoiceMode(true);
    }
    
    protected void setupRecyclerViewItemDecoration() {
    }
    
    protected void setupRecyclerViewLayoutManager() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity()));
    }
    
    protected void setupSeasonsSpinnerAdapter() {
        final SeasonsSpinnerAdapter adapter = new SeasonsSpinnerAdapter(this.getNetflixActivity(), new EpisodesFrag$4(this));
        adapter.setItemBackgroundColor(2130837879);
        this.spinner.setAdapter((SpinnerAdapter)adapter);
    }
    
    protected void setupSeasonsSpinnerGroup() {
        this.spinnerViewGroup = this.createSeasonsSpinnerGroup();
        if (this.getArguments().getBoolean("extra_show_episodes_selector") || (DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity()))) {
            this.addSpinnerAsHeader();
            return;
        }
        this.addSpinnerToDetailsGroup();
    }
    
    protected void setupSeasonsSpinnerListener() {
        this.spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new EpisodesFrag$3(this));
    }
    
    protected void showErrorView() {
        this.isLoading = false;
        this.leWrapper.showErrorView(false);
        AnimationUtils.hideView((View)this.getRecyclerView(), false);
    }
    
    public void updateEpisodeSelection() {
        if (this.initialEpisodeIndex != -1) {
            this.selectedEpisodeIndex = this.initialEpisodeIndex;
            this.initialEpisodeIndex = -1;
        }
        if (this.selectedEpisodeIndex == -1 && this.episodesAdapter != null && this.showDetails != null) {
            final String currentEpisodeId = this.showDetails.getCurrentEpisodeId();
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "currEpisodeId: " + currentEpisodeId + ", adapter count: " + this.episodesAdapter.getItemCount());
            }
            for (int i = 0; i < this.episodesAdapter.getItemCount(); ++i) {
                final Video item = this.episodesAdapter.getItem(i);
                if (item != null && StringUtils.safeEquals(item.getId(), currentEpisodeId)) {
                    if (Log.isLoggable()) {
                        Log.v("EpisodesFrag", "found selected episode, i: " + i);
                    }
                    this.selectedEpisodeIndex = i;
                }
            }
        }
        if (this.selectedEpisodeIndex == -1) {
            this.selectedEpisodeIndex = 0;
        }
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "selectedEpIndex: " + this.selectedEpisodeIndex);
        }
        this.leWrapper.hide(false);
        this.setItemChecked(this.selectedEpisodeIndex);
    }
    
    protected void updateSeasonData(final List<SeasonDetails> list) {
        this.spinner.updateSeasonData(list);
        this.showStandardViews();
    }
    
    protected void updateShowDetails(final ShowDetails showDetails, final boolean b) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Updating show details: " + showDetails.getTitle());
            Log.v("EpisodesFrag", "evidence glyph: " + showDetails.getEvidenceGlyph() + ", evidence text: " + showDetails.getEvidenceText());
        }
        this.showDetails = showDetails;
        this.detailsViewGroup.updateDetails(showDetails, new ShowDetailsFrag$ShowDetailsStringProvider((Context)this.getActivity(), showDetails));
        if (b) {
            this.fetchSeasonDetails();
        }
        if (this.getActivity() instanceof DetailsActivity && this.detailsViewGroup != null) {
            this.addToListWrapper = SocialUtils.setupVideoDetailsButtons(this.detailsViewGroup, (NetflixActivity)this.getActivity(), this.manager, this.showId, this.showDetails.getTitle(), this.showDetails.getType());
        }
    }
}
