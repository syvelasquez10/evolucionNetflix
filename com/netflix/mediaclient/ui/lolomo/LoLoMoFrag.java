// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.widget.ListAdapter;
import com.netflix.mediaclient.android.app.LoadingStatus;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.database.DataSetObserver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.kids.lolomo.SkidmarkLoLoMoAdapter;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.ui.kids.lolomo.KidsLomoDetailAdapter;
import com.netflix.mediaclient.ui.kids.lolomo.KidsGenreWrapper;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import java.util.Map;
import android.widget.AbsListView$RecyclerListener;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoLoMoFrag extends NetflixFrag implements ManagerStatusListener
{
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_IS_GENRE_LIST = "is_genre_list";
    private static final String TAG = "LoLoMoFrag";
    private ILoLoMoAdapter adapter;
    private LoLoMoFocusHandler focusHandler;
    private String genreId;
    private boolean isGenreList;
    private View kidsEntryHeader;
    private final ErrorWrapper.Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    private StickyListHeadersListView listView;
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
            if (!homeActivity.isForKids() && homeActivity.isKidsGenre() && KidsUtils.shouldShowKidsEntryInGenreLomo(homeActivity)) {
                (this.kidsEntryHeader = this.createKidsEntryHeader()).setVisibility(4);
                stickyListHeadersListView.addHeaderView(this.kidsEntryHeader);
            }
        }
    }
    
    public static LoLoMoFrag create(final String s) {
        final LoLoMoFrag loLoMoFrag = new LoLoMoFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("genre_id", s);
        arguments.putBoolean("is_genre_list", !"lolomo".equals(s));
        loLoMoFrag.setArguments(arguments);
        return loLoMoFrag;
    }
    
    private View createKidsEntryHeader() {
        final Activity activity = this.getActivity();
        final TextView textView = new TextView((Context)activity);
        textView.setBackgroundResource(2130837827);
        textView.setGravity(17);
        textView.setOnClickListener((View$OnClickListener)new KidsUtils.OnSwitchToKidsClickListener(activity));
        textView.setText(2131492948);
        textView.setTextColor(-1);
        textView.setTextSize(48.0f);
        textView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, (int)(DeviceUtils.getScreenWidthInPixels((Context)activity) * 0.5625f)));
        return (View)textView;
    }
    
    protected ILoLoMoAdapter createAdapter() {
        if (this.getNetflixActivity().isForKids()) {
            if (this.isGenreList) {
                return (ILoLoMoAdapter)new KidsLomoDetailAdapter(this, new KidsGenreWrapper(this.genreId));
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
        this.leWrapper.hide(false);
        AnimationUtils.showViewIfHidden((View)this.listView, true);
        AnimationUtils.showViewIfHidden(this.kidsEntryHeader, true);
    }
    
    public boolean isLoadingData() {
        final boolean loadingData = this.adapter.isLoadingData();
        Log.v("NflxLoading", "Class: " + this.getClass().getSimpleName() + ", loading: " + loadingData);
        return loadingData;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.genreId = this.getArguments().getString("genre_id");
        this.isGenreList = this.getArguments().getBoolean("is_genre_list");
        this.viewRecycler = ((ViewRecycler.ViewRecyclerProvider)this.getActivity()).getViewRecycler();
        if (bundle != null) {
            Log.v("LoLoMoFrag", "Clearing all frag state");
            this.stateMap.clear();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("LoLoMoFrag", "Creating frag view");
        final View inflate = layoutInflater.inflate(2130903106, (ViewGroup)null);
        (this.listView = (StickyListHeadersListView)inflate.findViewById(16908298)).setRecyclerListener(this.recycleListener);
        this.listView.setDivider(null);
        this.listView.setFocusable(false);
        ViewUtils.addActionBarPaddingView(this.listView);
        this.addKidsEntryHeaderIfNecessary(this.listView);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.leCallback);
        return inflate;
    }
    
    public void onDestroyView() {
        Log.v("LoLoMoFrag", "onDestroyView");
        if (this.adapter != null) {
            this.adapter.onDestroyView();
        }
        super.onDestroyView();
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
        Log.v("LoLoMoFrag", "onManagerReady");
        this.adapter = this.createAdapter();
        if (this.focusHandler != null) {
            this.adapter.registerDataSetObserver((DataSetObserver)this.focusHandler);
        }
        this.listView.setAdapter(this.adapter);
        this.adapter.onManagerReady(serviceManager, n);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        (this.adapter = this.createAdapter()).onManagerUnavailable(serviceManager, n);
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
