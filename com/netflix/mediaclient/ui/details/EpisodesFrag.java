// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.view.ContextThemeWrapper;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.util.DataUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class EpisodesFrag extends NetflixDialogFrag implements ErrorWrapper$Callback, ManagerStatusListener, DetailsActivity$Reloader, ServiceManagerProvider, VideoDetailsViewGroup$VideoDetailsViewGroupProvider, CastPlayerHelper$CastPlayerDialog, TutorialHelper$Tutorialable
{
    protected static final String EXTRA_EPISODE_ID = "extra_episode_id";
    private static final String EXTRA_EPISODE_INDEX = "extra_episode_index";
    private static final String EXTRA_FORCE_SHOW_SEASONS_SELECTOR = "extra_show_episodes_selector";
    private static final String EXTRA_SEASON_INDEX = "extra_season_index";
    protected static final String EXTRA_SHOW_DETAILS = "extra_show_details";
    protected static final String EXTRA_SHOW_ID = "extra_show_id";
    private static final String TAG = "EpisodesFrag";
    private AddToListData$StateListener addToListWrapper;
    private ActivityPageOfflineAgentListener agentListener;
    private SeasonDetails currSeasonDetails;
    protected int currSeasonIndex;
    protected VideoDetailsViewGroup detailsViewGroup;
    protected String episodeId;
    private final BroadcastReceiver episodeRefreshReceiver;
    protected RecyclerViewHeaderAdapter episodesAdapter;
    private final ErrorWrapper$Callback errorCallback;
    protected Handler handler;
    protected boolean isLoading;
    private boolean isShowDAB;
    protected LoadingAndErrorWrapper leWrapper;
    protected RecyclerView recyclerView;
    protected long requestId;
    private int restoredEpisodeIndex;
    public int selectedEpisodeIndex;
    protected ShowDetails showDetails;
    protected boolean showDetailsOnLaunch;
    protected String showId;
    protected SeasonsSpinner spinner;
    protected ViewGroup spinnerViewGroup;
    protected final RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    
    public EpisodesFrag() {
        this.selectedEpisodeIndex = -1;
        this.restoredEpisodeIndex = -1;
        this.currSeasonIndex = -1;
        this.isLoading = true;
        this.isShowDAB = false;
        this.viewCreatorEpisodes = new EpisodesFrag$1(this);
        this.episodeRefreshReceiver = new EpisodesFrag$6(this);
        this.errorCallback = new EpisodesFrag$7(this);
    }
    
    private void addSpinnerAsHeader() {
        if (this.recyclerView != null) {
            ((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).addHeaderView((View)this.spinnerViewGroup);
        }
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
        if (this.getServiceManager() == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - manager is null");
            return;
        }
        if (this.detailsViewGroup == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - details view is null");
            return;
        }
        if (this.getShowId() == null) {
            Log.v("EpisodesFrag", "Can't complete init yet - showId is null");
            return;
        }
        Log.v("EpisodesFrag", "All clear - completing init process...");
        this.showLoadingView();
        this.fetchShowDetailsAndSeasons();
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final EpisodesFrag episodesFrag = new EpisodesFrag();
        episodesFrag.setStyle(1, 2131493008);
        return applyCreateArgs(episodesFrag, s, s2, b, false);
    }
    
    public static NetflixDialogFrag createEpisodes(final String s, final String s2, final boolean b) {
        final EpisodesFrag episodesFrag = new EpisodesFrag();
        episodesFrag.setStyle(1, 2131493151);
        return applyCreateArgs(episodesFrag, s, s2, b, true);
    }
    
    private int getCheckedItemPosition() {
        if (this.episodesAdapter == null) {
            return -1;
        }
        return this.episodesAdapter.getCheckedItemPosition();
    }
    
    @SuppressLint({ "PrivateResource" })
    private int getSeasonsSpinnerStyle() {
        if (BrowseExperience.isLightTheme() && !KidsUtils.shouldTextBeForcedLight((Context)this.getActivity(), VideoDetailsViewGroup$Section.SPINNER)) {
            return 2131493305;
        }
        return 2131493299;
    }
    
    private boolean hasSeasons(final List<SeasonDetails> list) {
        return list != null && list.size() > 0;
    }
    
    private void invalidateCachedEpisodesIfDAB() {
        if (this.isShowDAB) {
            Log.v("EpisodesFrag", "Show is 'Day After Broadcast' (DAB), invalidating episode cache");
            DataUtil.invalidateCachedEpisodes(this.getServiceManager(), this.getShowId(), this.currSeasonDetails);
        }
    }
    
    private boolean isCoppolaExperience() {
        return this.getActivity() instanceof CoppolaDetailsActivity;
    }
    
    private void notifyDataLoadingComplete() {
        this.onLoaded(CommonStatus.OK);
    }
    
    private void registerEpisodeRefreshReceiver() {
        this.getActivity().registerReceiver(this.episodeRefreshReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH"));
    }
    
    private void removeOfflineAgentListener() {
        final OfflineAgentInterface offlineAgentOrNull = NetflixActivity.getOfflineAgentOrNull(this.getNetflixActivity());
        if (offlineAgentOrNull != null && this.agentListener != null) {
            offlineAgentOrNull.removeOfflineAgentListener((OfflineAgentListener)this.agentListener);
            this.agentListener = null;
        }
    }
    
    private void setItemChecked(final int itemChecked) {
        if (this.recyclerView != null && this.episodesAdapter != null) {
            this.episodesAdapter.setItemChecked(itemChecked);
        }
    }
    
    private void showLoadingView() {
        Log.v("EpisodesFrag", "Showing loading view");
        this.leWrapper.showLoadingView(false);
        this.recyclerView.setVisibility(4);
        if (this.spinnerViewGroup != null) {
            this.spinnerViewGroup.setVisibility(8);
        }
    }
    
    protected void addOfflineAgentListener(final ViewGroup viewGroup) {
        this.removeOfflineAgentListener();
        final OfflineAgentInterface offlineAgentOrNull = NetflixActivity.getOfflineAgentOrNull(this.getNetflixActivity());
        if (offlineAgentOrNull != null) {
            offlineAgentOrNull.addOfflineAgentListener((OfflineAgentListener)(this.agentListener = new ActivityPageOfflineAgentListener(viewGroup, false)));
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
    
    protected ViewGroup createSeasonsSelectorGroup() {
        if (this.getActivity() == null) {
            return null;
        }
        this.spinner = new SeasonsSpinner((Context)new ContextThemeWrapper((Context)this.getActivity(), this.getSeasonsSpinnerStyle()));
        this.setupSeasonsSpinnerAdapter();
        this.setupSeasonsSpinnerListener();
        (this.spinnerViewGroup = (ViewGroup)new FrameLayout((Context)this.getActivity())).setBackgroundResource(2131755279);
        this.spinnerViewGroup.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, (int)this.getResources().getDimension(2131427904)));
        this.spinnerViewGroup.addView((View)this.spinner, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 8388627));
        return this.spinnerViewGroup;
    }
    
    protected void fetchShowDetailsAndSeasons() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("EpisodesFrag", "Manager is null - can't get show details");
            return;
        }
        if (this.getNetflixActivity() == null) {
            Log.w("EpisodesFrag", "Activity is null - can't get show details");
            return;
        }
        if (this.getShowId() == null) {
            Log.w("EpisodesFrag", "show ID is null - can't get show details");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Fetching data for show ID: " + this.getShowId());
        }
        serviceManager.getBrowse().fetchShowDetailsAndSeasons(this.showId, this.episodeId, BrowseExperience.shouldLoadKubrickLeavesInDetails(), DPPrefetchABTestUtils.isInTest((Context)this.getActivity()), new EpisodesFrag$FetchDataCallback(this, this.requestId));
    }
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
    }
    
    protected RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
    
    protected SeasonsSpinner getSeasonSpinner() {
        return this.spinner;
    }
    
    public String getShowId() {
        return this.showId;
    }
    
    @Override
    public VideoDetailsViewGroup getVideoDetailsViewGroup() {
        return this.detailsViewGroup;
    }
    
    protected int getlayoutId() {
        return 2130903285;
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
            this.restoredEpisodeIndex = bundle.getInt("extra_episode_index");
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "Restored season index as: " + this.currSeasonIndex + ", episode: " + this.restoredEpisodeIndex);
            }
        }
        super.onCreate(bundle);
        this.registerEpisodeRefreshReceiver();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("EpisodesFrag", "onCreateView called");
        final View inflate = layoutInflater.inflate(this.getlayoutId(), (ViewGroup)null, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        this.findViews(inflate);
        this.setupRecyclerView();
        this.initDetailsViewGroupAsHeader();
        this.setupSeasonsSpinnerGroup();
        this.detailsViewGroup.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new EpisodesFrag$2(this));
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && this.addToListWrapper != null) {
            serviceManager.unregisterAddToMyListListener(this.getShowId(), this.addToListWrapper);
        }
        this.invalidateCachedEpisodesIfDAB();
        this.getActivity().unregisterReceiver(this.episodeRefreshReceiver);
    }
    
    public void onDestroyView() {
        super.onDestroyView();
        this.removeOfflineAgentListener();
    }
    
    public void onEpisodesUpdated(final List<EpisodeDetails> list) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.getNetflixActivity()) && list != null) {
            final Iterator<EpisodeDetails> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getPlayable().isAvailableOffline()) {
                    this.getNetflixActivity().getTutorialHelper().showTutorialForVideoWithScroll((TutorialHelper$Tutorialable)this, (VideoDetails)list.get(0), this.recyclerView, this.getServiceManager());
                }
            }
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        this.completeInitIfPossible();
    }
    
    public void onResume() {
        Log.v("EpisodesFrag", "onResume");
        super.onResume();
        if (this.episodesAdapter != null) {
            this.episodesAdapter.notifyDataSetChanged();
        }
    }
    
    @Override
    public void onRetryRequested() {
        Log.v("EpisodesFrag", "Retry requested");
        if (this.getServiceManager() != null && ConnectivityUtils.isConnected((Context)this.getActivity())) {
            this.showLoadingView();
            this.fetchShowDetailsAndSeasons();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.spinner != null) {
            final int selectedItemPosition = this.spinner.getSelectedItemPosition();
            final int checkedItemPosition = this.getCheckedItemPosition();
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "Saving season index as: " + selectedItemPosition + ", episode index: " + checkedItemPosition);
            }
            bundle.putInt("extra_season_index", selectedItemPosition);
            bundle.putInt("extra_episode_index", checkedItemPosition);
        }
    }
    
    protected void postSetSpinnerSelectionRunnable() {
        this.handler.post((Runnable)new EpisodesFrag$5(this));
    }
    
    @Override
    public void reload() {
        Log.v("EpisodesFrag", "reload()");
        this.fetchShowDetailsAndSeasons();
    }
    
    public void setAsDAB(final boolean isShowDAB) {
        this.isShowDAB = isShowDAB;
    }
    
    public void setDetailViewGroupVisibility(final int visibility) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "setDetailViewGroupVisibility: " + ViewUtils.getVisibilityAsString(visibility));
        }
        this.detailsViewGroup.setVisibility(visibility);
    }
    
    protected void setSeasonIndex() {
        if (this.currSeasonIndex == -1 && this.showDetails != null) {
            this.currSeasonIndex = this.spinner.tryGetSeasonIndexBySeasonNumber(this.showDetails.getCurrentSeasonNumber());
        }
    }
    
    protected void setSpinnerSelection() {
        this.setSeasonIndex();
        if (this.currSeasonIndex < 0) {
            Log.v("EpisodesFrag", "No valid season index found");
            return;
        }
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Setting current season to: " + this.currSeasonIndex);
        }
        this.spinner.setNonTouchSelection(this.currSeasonIndex);
    }
    
    public void setVideoId(final String showId) {
        this.showId = showId;
    }
    
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null && this.recyclerView.getContext() instanceof NetflixActivity) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                if (DeviceUtils.isNotTabletByContext((Context)this.getActivity()) || DeviceUtils.isPortrait((Context)this.getActivity())) {
                    final DetailsPageParallaxScrollListener default1 = DetailsPageParallaxScrollListener.createDefault(this.spinner, this.recyclerView, new View[] { this.detailsViewGroup.getHeroImage() }, null, null);
                    this.recyclerView.setOnScrollListener(default1);
                    return default1;
                }
            }
        }
        return null;
    }
    
    protected void setupRecyclerView() {
        if (this.recyclerView == null) {
            return;
        }
        this.recyclerView.setFocusable(false);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        Log.v("EpisodesFrag", "Setting recyclerView to invisible");
        this.recyclerView.setVisibility(4);
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
        this.setupRecyclerViewItemDecoration();
        KidsUtils.setBackgroundIfApplicable((View)this.recyclerView);
    }
    
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new EpisodesAdapter((NetflixActivity)this.getActivity(), this, this.viewCreatorEpisodes);
        if (DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity())) {
            this.episodesAdapter.addHeaderView(ViewUtils.createActionBarDummyView(this.getNetflixActivity()));
        }
        this.episodesAdapter.addFooterView(ViewUtils.createActionBarDummyView(this.getNetflixActivity(), this.getResources().getDimensionPixelOffset(2131427907)));
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setSingleChoiceMode(!Config_Ab7994.shouldRenderDPWithBifs((Context)this.getActivity()));
        this.addOfflineAgentListener(this.recyclerView);
    }
    
    protected void setupRecyclerViewItemDecoration() {
    }
    
    protected void setupRecyclerViewLayoutManager() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity()));
    }
    
    protected void setupSeasonsSpinnerAdapter() {
        final SeasonsSpinnerAdapter adapter = new SeasonsSpinnerAdapter(this.getNetflixActivity(), new EpisodesFrag$4(this));
        adapter.setItemBackgroundColor(2130838160);
        this.spinner.setAdapter((SpinnerAdapter)adapter);
    }
    
    protected void setupSeasonsSpinnerGroup() {
        this.spinnerViewGroup = this.createSeasonsSelectorGroup();
        if (this.getArguments().getBoolean("extra_show_episodes_selector") || (DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity()))) {
            this.addSpinnerAsHeader();
            return;
        }
        this.addSpinnerToDetailsGroup();
    }
    
    protected void setupSeasonsSpinnerListener() {
        this.spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new EpisodesFrag$3(this));
    }
    
    public Tooltip setupTutorial(final UserProfile userProfile) {
        final View viewById = this.recyclerView.findViewById(2131820723);
        if (viewById == null) {
            return null;
        }
        return TutorialHelper.buildDownloadButtonTutorial(viewById, this.getActivity(), userProfile);
    }
    
    protected void showErrorView() {
        Log.v("EpisodesFrag", "Showing error view");
        this.isLoading = false;
        this.leWrapper.showErrorView(false);
        this.recyclerView.setVisibility(4);
        if (this.spinnerViewGroup != null) {
            this.spinnerViewGroup.setVisibility(8);
        }
    }
    
    protected void showStandardViews() {
        Log.v("EpisodesFrag", "Showing standard views");
        if (!this.isListVisible()) {
            Log.v("EpisodesFrag", "Showing recycler view");
            AnimationUtils.showView((View)this.recyclerView, true);
        }
        if (this.spinnerViewGroup != null) {
            this.spinnerViewGroup.setVisibility(0);
        }
        this.postSetSpinnerSelectionRunnable();
    }
    
    public void switchSeason(final int currSeasonIndex, final boolean b) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Season spinner selected position: " + currSeasonIndex);
        }
        this.invalidateCachedEpisodesIfDAB();
        this.currSeasonDetails = (SeasonDetails)this.spinner.getItemAtPosition(currSeasonIndex);
        this.currSeasonIndex = currSeasonIndex;
        if (this.currSeasonDetails == null && Log.isLoggable()) {
            Log.w("EpisodesFrag", "null season details retrieved for position: " + currSeasonIndex);
        }
        this.leWrapper.showLoadingView(true);
        ((EpisodesAdapter)this.episodesAdapter).updateSeasonDetails(this.currSeasonDetails);
        this.selectedEpisodeIndex = -1;
    }
    
    public void updateEpisodeSelection() {
        if (this.restoredEpisodeIndex != -1) {
            this.selectedEpisodeIndex = this.restoredEpisodeIndex;
            this.restoredEpisodeIndex = -1;
        }
        if (this.selectedEpisodeIndex == -1 && this.episodesAdapter != null && this.showDetails != null) {
            String s;
            if (this.isCoppolaExperience()) {
                s = this.episodeId;
            }
            else {
                s = this.showDetails.getCurrentEpisodeId();
            }
            if (Log.isLoggable()) {
                Log.v("EpisodesFrag", "currEpisodeId: " + s + ", adapter count: " + this.episodesAdapter.getItemCount());
            }
            for (int i = 0; i < this.episodesAdapter.getItemCount(); ++i) {
                final Video item = this.episodesAdapter.getItem(i);
                if (item != null && StringUtils.safeEquals(item.getId(), s)) {
                    if (Log.isLoggable()) {
                        Log.v("EpisodesFrag", "found selected episode, index: " + i);
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
        this.notifyDataLoadingComplete();
        this.setItemChecked(this.selectedEpisodeIndex + this.episodesAdapter.getHeaderViewsCount());
    }
    
    protected void updateSeasonData(final List<SeasonDetails> list) {
        this.spinner.updateSeasonData(list);
        this.showStandardViews();
    }
    
    protected void updateShowDetails(final ShowDetails showDetails) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Updating show details: " + showDetails.getTitle());
            Log.v("EpisodesFrag", "evidence glyph: " + showDetails.getEvidenceGlyph() + ", evidence text: " + showDetails.getEvidenceText());
        }
        final ServiceManager serviceManager = this.getServiceManager();
        this.showDetails = showDetails;
        this.detailsViewGroup.updateDetails(showDetails, new ShowDetailsFrag$ShowDetailsStringProvider((Context)this.getActivity(), showDetails));
        this.detailsViewGroup.setCopyright(showDetails);
        if (StringUtils.isEmpty(this.episodeId)) {
            this.episodeId = this.showDetails.getCurrentEpisodeId();
        }
        if (this.getActivity() instanceof DetailsActivity && this.detailsViewGroup != null) {
            this.addToListWrapper = DetailsFrag.addToMyListWrapper(this.detailsViewGroup, (NetflixActivity)this.getActivity(), serviceManager, this.getShowId());
            this.detailsViewGroup.setupDownloadButton(this.showDetails);
            if (this.getActivity() instanceof ShowDetailsActivity) {
                Log.i("EpisodesFrag", "EpisodeFag updateShowDetails");
                ((ShowDetailsActivity)this.getActivity()).dataReady(showDetails);
            }
        }
    }
    
    protected void updateVolatileDataInView(final ShowDetails showDetails) {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            return;
        }
        Log.v("EpisodesFrag", "Volatile data update rating: %d, inQ: %b", showDetails.getUserThumbRating(), showDetails.isInQueue());
        this.detailsViewGroup.updateRating(showDetails);
        serviceManager.updateMyListState(showDetails.getId(), showDetails.isInQueue());
    }
}
