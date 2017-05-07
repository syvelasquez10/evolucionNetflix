// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.kids.lolomo.SkidmarkLoLoMoAdapter;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.ui.kids.lolomo.KidsLomoDetailAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickGenreLoLoMoAdapter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListAdapter;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import java.util.HashMap;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import java.util.Map;
import android.widget.AbsListView$RecyclerListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoLoMoFrag extends NetflixFrag implements ManagerStatusListener
{
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    private static final String EXTRA_IS_GENRE_LIST = "is_genre_list";
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "LoLoMoFrag";
    private LoLoMoFrag$ILoLoMoAdapter adapter;
    private LoLoMoFocusHandler focusHandler;
    private GenreList genre;
    private String genreId;
    private boolean isGenreList;
    private View kidsEntryHeader;
    private final ErrorWrapper$Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    protected ListView listView;
    private ServiceManager manager;
    private final AbsListView$RecyclerListener recycleListener;
    private final Map<String, Object> stateMap;
    private ObjectRecycler$ViewRecycler viewRecycler;
    
    public LoLoMoFrag() {
        this.stateMap = new HashMap<String, Object>();
        this.leCallback = new LoLoMoFrag$1(this);
        this.recycleListener = (AbsListView$RecyclerListener)new LoLoMoFrag$2(this);
    }
    
    private void addKidsEntryHeaderIfNecessary(final ListView listView) {
        if (this.getActivity() instanceof HomeActivity) {
            final HomeActivity homeActivity = (HomeActivity)this.getActivity();
            Log.v("LoLoMoFrag", String.format("isForKids: %s, isKidsGenre: %s, shouldShowKidsEntryInGenreLomo: %s", homeActivity.isForKids(), homeActivity.isKidsGenre(), KidsUtils.shouldShowKidsEntryInGenreLomo(homeActivity)));
            if (!homeActivity.isForKids() && homeActivity.isKidsGenre() && KidsUtils.shouldShowKidsEntryInGenreLomo(homeActivity)) {
                Log.v("LoLoMoFrag", "Adding Kids entry header to genre list view");
                (this.kidsEntryHeader = (View)new KidsGenreEntryHeader(this.getNetflixActivity())).setVisibility(4);
                listView.addHeaderView(this.kidsEntryHeader);
            }
        }
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
        if (this.getActivity() == null) {
            Log.d("LoLoMoFrag", "Activity is null - can't continue init");
            return;
        }
        if (this.manager == null || !this.manager.isReady()) {
            Log.d("LoLoMoFrag", "Manager not available - can't continue init");
            return;
        }
        this.addKidsEntryHeaderIfNecessary(this.listView);
        if (this.adapter != null && this.focusHandler != null) {
            this.adapter.unregisterDataSetObserver((DataSetObserver)this.focusHandler);
        }
        this.adapter = this.createAdapter();
        if (this.focusHandler != null) {
            this.adapter.registerDataSetObserver((DataSetObserver)this.focusHandler);
        }
        this.listView.setAdapter((ListAdapter)this.adapter);
        this.listView.setOnScrollListener((AbsListView$OnScrollListener)this.adapter);
        this.adapter.onManagerReady(this.manager, CommonStatus.OK);
    }
    
    protected LoLoMoFrag$ILoLoMoAdapter createAdapter() {
        final NetflixActivity netflixActivity = this.getNetflixActivity();
        if (netflixActivity == null) {
            Log.w("LoLoMoFrag", "createAdapter(): activity is null - should not happen");
        }
        else if (netflixActivity.isKubrick()) {
            if (netflixActivity.isForKids()) {
                if (this.isGenreList) {
                    return new KubrickGenreLoLoMoAdapter(this, this.genreId, false);
                }
                return new LoLoMoAdapter(this);
            }
            else {
                if (this.isGenreList) {
                    return new KubrickGenreLoLoMoAdapter(this, this.genreId, true);
                }
                return new KubrickLoLoMoAdapter(this);
            }
        }
        else if (netflixActivity.isForKids() && !netflixActivity.isKubrick()) {
            if (this.isGenreList) {
                KidsUtils.addListViewSpacerIfNoHeaders(this.listView);
                return new KidsLomoDetailAdapter(this, this.genre);
            }
            if (KidsUtils.isKidsWithUpDownScrolling(netflixActivity)) {
                return new SkidmarkLoLoMoAdapter(this);
            }
        }
        if (this.isGenreList) {
            return new GenreLoLoMoAdapter(this, this.genreId);
        }
        return new LoLoMoAdapter(this);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.focusHandler != null && this.focusHandler.dispatchKeyEvent(keyEvent);
    }
    
    ListView getListView() {
        return this.listView;
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
        AnimationUtils.showViewIfHidden(this.kidsEntryHeader, true);
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
        this.genre = (GenreList)this.getArguments().getParcelable("genre_parcel");
        this.isGenreList = this.getArguments().getBoolean("is_genre_list");
        this.viewRecycler = ((ObjectRecycler$ViewRecyclerProvider)this.getActivity()).getViewRecycler();
        if (bundle != null) {
            Log.v("LoLoMoFrag", "Clearing all frag state");
            this.stateMap.clear();
        }
        this.handleInitIfReady();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("LoLoMoFrag", "Creating frag view");
        final View inflate = layoutInflater.inflate(2130903130, viewGroup, false);
        (this.listView = (ListView)inflate.findViewById(16908298)).setDivider((Drawable)null);
        this.listView.setFocusable(false);
        this.listView.setRecyclerListener(this.recycleListener);
        KidsUtils.configureListViewForKids(this.getNetflixActivity(), this.listView);
        this.focusHandler = new LoLoMoFocusHandler(this.getNetflixActivity(), this.listView);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.leCallback);
        return inflate;
    }
    
    public void onDataLoadSuccess() {
    }
    
    public void onDestroyView() {
        Log.v("LoLoMoFrag", "onDestroyView");
        if (this.adapter != null) {
            this.adapter.onDestroyView();
        }
        super.onDestroyView();
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        Log.v("LoLoMoFrag", "onManagerReady");
        this.manager = manager;
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
        this.adapter.refreshData();
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatusCallback) {
        if (this.adapter != null) {
            this.adapter.setLoadingStatusCallback(loadingStatusCallback);
        }
    }
    
    public void showErrorView() {
        Log.v("LoLoMoFrag", "Showing error view");
        AnimationUtils.hideView((View)this.listView, true);
        if (this.kidsEntryHeader != null) {
            this.kidsEntryHeader.setVisibility(4);
        }
        this.leWrapper.showErrorView(true);
    }
    
    public void showLoadingView() {
        Log.v("LoLoMoFrag", "Showing loading view");
        AnimationUtils.hideView((View)this.listView, true);
        if (this.kidsEntryHeader != null) {
            this.kidsEntryHeader.setVisibility(4);
        }
        this.leWrapper.showLoadingView(true);
    }
}
