// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.widget.ListAdapter;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.kids.lolomo.SkidmarkLoLoMoAdapter;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.ui.kids.lolomo.KidsLomoDetailAdapter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import java.util.Map;
import android.widget.AbsListView$RecyclerListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoLoMoFrag extends NetflixFrag implements ManagerStatusListener
{
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    private static final String EXTRA_IS_GENRE_LIST = "is_genre_list";
    private static final String TAG = "LoLoMoFrag";
    private ILoLoMoAdapter adapter;
    private LoLoMoFocusHandler focusHandler;
    private GenreList genre;
    private String genreId;
    private boolean isGenreList;
    private View kidsEntryHeader;
    private final ErrorWrapper.Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    protected StickyListHeadersListView listView;
    private ServiceManager manager;
    private final AbsListView$RecyclerListener recycleListener;
    private final Map<String, Object> stateMap;
    private ViewRecycler viewRecycler;
    
    public LoLoMoFrag() {
        this.stateMap = new HashMap<String, Object>();
        this.leCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                LoLoMoFrag.this.refresh();
            }
        };
        this.recycleListener = (AbsListView$RecyclerListener)new AbsListView$RecyclerListener() {
            public void onMovedToScrapHeap(final View view) {
                final BaseLoLoMoAdapter.RowHolder rowHolder = (BaseLoLoMoAdapter.RowHolder)view.getTag();
                if (rowHolder == null) {
                    return;
                }
                Log.v("LoLoMoFrag", "View moved to scrap heap - invalidating request");
                rowHolder.invalidateRequestId();
            }
        };
    }
    
    private void addKidsEntryHeaderIfNecessary(final StickyListHeadersListView stickyListHeadersListView) {
        if (this.getActivity() instanceof HomeActivity) {
            final HomeActivity homeActivity = (HomeActivity)this.getActivity();
            Log.v("LoLoMoFrag", String.format("isForKids: %s, isKidsGenre: %s, shouldShowKidsEntryInGenreLomo: %s", homeActivity.isForKids(), homeActivity.isKidsGenre(), KidsUtils.shouldShowKidsEntryInGenreLomo(homeActivity)));
            if (!homeActivity.isForKids() && homeActivity.isKidsGenre() && KidsUtils.shouldShowKidsEntryInGenreLomo(homeActivity)) {
                Log.v("LoLoMoFrag", "Adding Kids entry header to genre list view");
                (this.kidsEntryHeader = (View)new KidsGenreEntryHeader(this.getActivity())).setVisibility(4);
                stickyListHeadersListView.addHeaderView(this.kidsEntryHeader);
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
        this.listView.setAdapter(this.adapter);
        this.adapter.onManagerReady(this.manager, CommonStatus.OK);
    }
    
    protected ILoLoMoAdapter createAdapter() {
        if (this.getNetflixActivity() == null) {
            Log.w("LoLoMoFrag", "createAdapter(): activity is null - should not happen");
        }
        else if (this.getNetflixActivity().isForKids()) {
            if (this.isGenreList) {
                KidsUtils.addListViewSpacerIfNoHeaders(this.listView);
                return (ILoLoMoAdapter)new KidsLomoDetailAdapter(this, this.genre);
            }
            if (KidsUtils.isKidsWithUpDownScrolling(this.getNetflixActivity())) {
                return (ILoLoMoAdapter)new SkidmarkLoLoMoAdapter(this);
            }
        }
        if (this.isGenreList) {
            return (ILoLoMoAdapter)new GenreLoLoMoAdapter(this, this.genreId);
        }
        return (ILoLoMoAdapter)new LoLoMoAdapter(this);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.focusHandler != null && this.focusHandler.dispatchKeyEvent(keyEvent);
    }
    
    public Map<String, Object> getStateMap() {
        return this.stateMap;
    }
    
    public ViewRecycler getViewRecycler() {
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
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        Log.v("LoLoMoFrag", "Frag attached to activity: " + activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.genreId = this.getArguments().getString("genre_id");
        this.genre = (GenreList)this.getArguments().getParcelable("genre_parcel");
        this.isGenreList = this.getArguments().getBoolean("is_genre_list");
        this.viewRecycler = ((ViewRecycler.ViewRecyclerProvider)this.getActivity()).getViewRecycler();
        if (bundle != null) {
            Log.v("LoLoMoFrag", "Clearing all frag state");
            this.stateMap.clear();
        }
        this.handleInitIfReady();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("LoLoMoFrag", "Creating frag view");
        final View inflate = layoutInflater.inflate(2130903116, (ViewGroup)null);
        (this.listView = (StickyListHeadersListView)inflate.findViewById(16908298)).setDivider(null);
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
    
    public void onDetach() {
        super.onDetach();
        Log.v("LoLoMoFrag", "Frag detached from activity");
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
    public void setLoadingStatusCallback(final LoadingStatusCallback loadingStatusCallback) {
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
    
    public interface ILoLoMoAdapter extends ManagerStatusListener, LoadingStatus, ListAdapter, StickyListHeadersAdapter
    {
        void onDestroyView();
        
        void onPause();
        
        void onResume();
        
        void refreshData();
    }
}
