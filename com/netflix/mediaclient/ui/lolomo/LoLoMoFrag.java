// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.Collections;
import com.netflix.mediaclient.service.logging.perf.Events;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListAdapter;
import android.database.DataSetObserver;
import com.netflix.mediaclient.Log;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.HashMap;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import java.util.Map;
import android.widget.AbsListView$RecyclerListener;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.FrameLayout;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoLoMoFrag extends NetflixFrag implements ManagerStatusListener
{
    protected static final String EXTRA_GENRE_ID = "genre_id";
    protected static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    protected static final String EXTRA_IS_GENRE_LIST = "is_genre_list";
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "LoLoMoFrag";
    private LoLoMoFrag$ILoLoMoAdapter adapter;
    private FrameLayout content;
    protected LoLoMoFocusHandler focusHandler;
    protected String genreId;
    private boolean isFirstLaunch;
    private boolean isGenreList;
    protected final ErrorWrapper$Callback leCallback;
    protected LoadingAndErrorWrapper leWrapper;
    protected ListView listView;
    private final AbsListView$RecyclerListener recycleListener;
    private final Map<String, Object> stateMap;
    private ObjectRecycler$ViewRecycler viewRecycler;
    private boolean wasInitPerformed;
    
    public LoLoMoFrag() {
        this.stateMap = new HashMap<String, Object>();
        this.leCallback = new LoLoMoFrag$2(this);
        this.recycleListener = (AbsListView$RecyclerListener)new LoLoMoFrag$3(this);
    }
    
    public static LoLoMoFrag create(final String s, final GenreList list) {
        final LoLoMoFrag loLoMoFrag = new LoLoMoFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("genre_id", s);
        arguments.putBoolean("is_genre_list", !"lolomo".equals(s));
        if (list != null) {
            arguments.putParcelable("genre_parcel", (Parcelable)list);
        }
        loLoMoFrag.setArguments(arguments);
        return loLoMoFrag;
    }
    
    private void handleInitIfReady() {
        if (this.wasInitPerformed) {
            return;
        }
        if (this.getActivity() == null) {
            Log.d("LoLoMoFrag", "Activity is null - can't continue init");
            return;
        }
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.d("LoLoMoFrag", "Manager not available - can't continue init");
            return;
        }
        if (this.listView == null) {
            Log.d("LoLoMoFrag", "Views are not initialized - can't continue init");
            return;
        }
        if (this.adapter != null && this.focusHandler != null) {
            this.adapter.unregisterDataSetObserver((DataSetObserver)this.focusHandler);
        }
        this.adapter = this.createAdapter();
        if (this.focusHandler != null) {
            this.adapter.registerDataSetObserver((DataSetObserver)this.focusHandler);
        }
        this.listView.setAdapter((ListAdapter)this.adapter);
        this.listView.setOnScrollListener((AbsListView$OnScrollListener)this.adapter);
        this.adapter.onManagerReady(serviceManager, CommonStatus.OK);
        this.wasInitPerformed = true;
    }
    
    private void setupErrorWrapper(final View view) {
        this.leWrapper = new LoadingAndErrorWrapper(view, this.leCallback);
    }
    
    protected LoLoMoFrag$ILoLoMoAdapter createAdapter() {
        return BrowseExperience.get().createLolomoAdapter(this, this.isGenreList, this.genreId);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.focusHandler != null && this.focusHandler.dispatchKeyEvent(keyEvent);
    }
    
    public FrameLayout getContentView() {
        return this.content;
    }
    
    protected int getLayoutId() {
        return 2130903177;
    }
    
    ListView getListView() {
        return this.listView;
    }
    
    protected View getMainView() {
        return (View)this.listView;
    }
    
    public Map<String, Object> getStateMap() {
        return this.stateMap;
    }
    
    public ObjectRecycler$ViewRecycler getViewRecycler() {
        return this.viewRecycler;
    }
    
    public void hideLoadingAndErrorViews() {
        Log.v("LoLoMoFrag", "Hiding loading and error views");
        this.leWrapper.hide(false);
        AnimationUtils.showViewIfHidden((View)this.listView, true);
    }
    
    public boolean isLoadingData() {
        if (this.adapter == null) {
            Log.v("NflxLoading", "No adapter yet - not loading data");
            return false;
        }
        final boolean loadingData = this.adapter.isLoadingData();
        Log.v("NflxLoading", "Class: " + this.getClass().getSimpleName() + ", loading: " + loadingData);
        return loadingData;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.genreId = this.getArguments().getString("genre_id");
        this.isGenreList = this.getArguments().getBoolean("is_genre_list");
        this.viewRecycler = ((ObjectRecycler$ViewRecyclerProvider)this.getActivity()).getViewRecycler();
        if (bundle != null) {
            Log.v("LoLoMoFrag", "Clearing all frag state");
            this.stateMap.clear();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("LoLoMoFrag", "Creating frag view");
        this.content = (FrameLayout)layoutInflater.inflate(this.getLayoutId(), viewGroup, false);
        this.isFirstLaunch = (bundle == null);
        if (Log.isLoggable()) {
            Log.v("LoLoMoFrag", "onCreateView: isFirstLaunch = " + this.isFirstLaunch);
        }
        this.setupMainView((View)this.content);
        this.setupFocushandler();
        this.setupErrorWrapper((View)this.content);
        this.handleInitIfReady();
        return (View)this.content;
    }
    
    public void onDestroyView() {
        Log.v("LoLoMoFrag", "onDestroyView");
        if (this.adapter != null) {
            this.adapter.onDestroyView();
        }
        super.onDestroyView();
    }
    
    public void onLolomoPrefetchComplete(final boolean b) {
        if (!this.isFirstLaunch) {
            Log.d("LoLoMoFrag", "onLolomoPrefetchComplete: is not from cache or is not first launch to refresh CW");
        }
        else if (PrefetchLolomoABTestUtils.hasJobScheduler((Context)this.getNetflixActivity())) {
            PerformanceProfiler.getInstance().logEvent(Events.LOLOMO_METADATA_FETCHED_EVENT, Collections.singletonMap("isFromCache", String.valueOf(b)));
            if (b && this.getServiceManager() != null) {
                if (Log.isLoggable()) {
                    Log.d("LoLoMoFrag", "onLolomoPrefetchComplete: lolomo data is from cache on a new app launch - refresh CW data from server");
                }
                this.getServiceManager().getBrowse().refreshCw(true);
            }
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.v("LoLoMoFrag", "onManagerReady");
        if (status.isError()) {
            Log.w("LoLoMoFrag", "Manager status code not okay");
            return;
        }
        this.handleInitIfReady();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
    
    public void onPause() {
        super.onPause();
        if (this.adapter != null) {
            this.adapter.onPause();
        }
    }
    
    public void onResume() {
        super.onResume();
        if (this.adapter != null) {
            this.adapter.onResume();
        }
    }
    
    public void refresh() {
        this.showLoadingView();
        this.stateMap.clear();
        this.adapter.refreshData();
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatusCallback) {
        if (this.adapter != null) {
            this.adapter.setLoadingStatusCallback(loadingStatusCallback);
        }
    }
    
    protected void setupFocushandler() {
        this.focusHandler = new LoLoMoFocusHandler(this.getNetflixActivity(), this.listView);
    }
    
    protected void setupMainView(final View view) {
        (this.listView = (ListView)view.findViewById(16908298)).setDivider((Drawable)null);
        this.listView.setFocusable(false);
        this.listView.setRecyclerListener(this.recycleListener);
        if (BrowseExperience.showKidsExperience()) {
            KidsUtils.configureListViewForKids(this.listView);
        }
    }
    
    public void showErrorView() {
        Log.v("LoLoMoFrag", "Showing error view");
        AnimationUtils.hideView((View)this.listView, true);
        final NetflixActivity netflixActivity = this.getNetflixActivity();
        if (netflixActivity != null) {
            netflixActivity.removeNoNetworkOverlay();
            this.getNetflixActivity().runWhenManagerIsReady(new LoLoMoFrag$1(this));
        }
        this.leWrapper.showErrorView(true);
    }
    
    public void showLoadingView() {
        Log.v("LoLoMoFrag", "Showing loading view");
        AnimationUtils.hideView((View)this.listView, true);
        this.leWrapper.showLoadingView(true);
    }
}
