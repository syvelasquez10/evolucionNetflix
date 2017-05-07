// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListAdapter;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.KeyEvent;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.HashMap;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import java.util.Map;
import android.widget.AbsListView$RecyclerListener;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoLoMoFrag extends NetflixFrag implements ManagerStatusListener
{
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_IS_GENRE_LIST = "is_genre_list";
    private static final String TAG = "LoLoMoFrag";
    private BaseLoLoMoAdapter<?> adapter;
    private LoLoMoFocusHandler focusHandler;
    private String genreId;
    private boolean isGenreList;
    private final ErrorWrapper.Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    private ListView listView;
    private final AbsListView$RecyclerListener recycleListener;
    private final Map<String, LoMoViewPager.State> stateMap;
    private ViewRecycler viewRecycler;
    
    public LoLoMoFrag() {
        this.stateMap = new HashMap<String, LoMoViewPager.State>();
        this.leCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                LoLoMoFrag.this.refresh();
            }
        };
        this.recycleListener = (AbsListView$RecyclerListener)new AbsListView$RecyclerListener() {
            public void onMovedToScrapHeap(final View view) {
                Log.v("LoLoMoFrag", "View moved to scrap heap - invalidating request");
                final BaseLoLoMoAdapter.RowHolder rowHolder = (BaseLoLoMoAdapter.RowHolder)view.getTag();
                if (rowHolder == null || rowHolder.pager == null || rowHolder.pager.getLoMoViewPagerAdapter() == null) {
                    return;
                }
                rowHolder.pager.getLoMoViewPagerAdapter().invalidateRequestId();
            }
        };
    }
    
    public static LoLoMoFrag create(final String s) {
        final LoLoMoFrag loLoMoFrag = new LoLoMoFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("genre_id", s);
        arguments.putBoolean("is_genre_list", !"lolomo".equals(s));
        loLoMoFrag.setArguments(arguments);
        return loLoMoFrag;
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.focusHandler.dispatchKeyEvent(keyEvent);
    }
    
    public BaseLoLoMoAdapter<?> getLoLoMoAdapter() {
        return this.adapter;
    }
    
    public Map<String, LoMoViewPager.State> getStateMap() {
        return this.stateMap;
    }
    
    public ViewRecycler getViewRecycler() {
        return this.viewRecycler;
    }
    
    public void hideLoadingAndErrorViews() {
        this.leWrapper.hide(false);
        AnimationUtils.showView((View)this.listView, true);
    }
    
    public boolean isLoadingData() {
        final boolean loadingData = this.adapter.isLoadingData();
        Log.v("NflxLoading", "Class: " + this.getClass().getSimpleName() + ", loading: " + loadingData);
        return loadingData;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.genreId = this.getArguments().getString("genre_id");
        this.isGenreList = this.getArguments().getBoolean("is_genre_list");
        this.viewRecycler = ((HomeActivity)this.getActivity()).getViewRecycler();
        if (bundle != null) {
            Log.v("LoLoMoFrag", "Clearing all frag state");
            this.stateMap.clear();
        }
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("LoLoMoFrag", "Creating frag view");
        final View inflate = layoutInflater.inflate(2130903096, (ViewGroup)null);
        inflate.findViewById(2131230901).setVisibility(0);
        (this.listView = (ListView)inflate.findViewById(16908298)).setRecyclerListener(this.recycleListener);
        this.listView.setDivider((Drawable)null);
        this.listView.setFocusable(false);
        this.focusHandler = new LoLoMoFocusHandler(this.listView);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.leCallback);
        ManagerStatusListener adapter;
        if (this.isGenreList) {
            adapter = new GenreLoLoMoAdapter(this, this.genreId);
        }
        else {
            adapter = new LoLoMoAdapter(this);
        }
        (this.adapter = (BaseLoLoMoAdapter<?>)adapter).registerDataSetObserver((DataSetObserver)this.focusHandler);
        this.listView.setAdapter((ListAdapter)this.adapter);
        return inflate;
    }
    
    public void onDestroyView() {
        Log.v("LoLoMoFrag", "onDestroyView");
        this.adapter.destroy();
        super.onDestroyView();
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
        this.adapter.onManagerReady(serviceManager, n);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        this.adapter.onManagerUnavailable(serviceManager, n);
    }
    
    public void onPause() {
        super.onPause();
        this.adapter.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.adapter.onResume();
    }
    
    public void refresh() {
        this.showLoadingView();
        this.adapter.refresh();
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
        this.leWrapper.showErrorView(true);
    }
    
    public void showLoadingView() {
        Log.v("LoLoMoFrag", "Showing loading view");
        AnimationUtils.hideView((View)this.listView, true);
        this.leWrapper.showLoadingView(true);
    }
}
