// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.ViewGroup;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import android.content.BroadcastReceiver;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;

public class LoMoViewPagerAdapter extends PagerAdapter
{
    private static final String TAG = "LoMoViewPagerAdapter";
    private final Activity activity;
    private final ProgressiveAdapterProvider billboard;
    private final BroadcastReceiver browseReceiver;
    private ProgressiveAdapterProvider currentAdapter;
    private final ProgressiveAdapterProvider cw;
    private final ProgressiveAdapterProvider error;
    private final ProgressiveAdapterProvider iq;
    private boolean isDestroyed;
    private int listViewPos;
    private BasicLoMo loMo;
    private final ProgressiveAdapterProvider loading;
    private final View$OnClickListener onReloadClickListener;
    private final LoMoViewPager pager;
    private final PagerAdapterCallbacks pagerAdapterCallbacks;
    private Type preErrorState;
    private final View reloadView;
    private final ProgressiveAdapterProvider standard;
    private Type state;
    private final ViewRecycler viewRecycler;
    
    public LoMoViewPagerAdapter(final LoMoViewPager pager, final ServiceManager serviceManager, final ViewRecycler viewRecycler, final View reloadView, final boolean b) {
        this.onReloadClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoMoViewPagerAdapter.this.reload();
            }
        };
        this.pagerAdapterCallbacks = new PagerAdapterCallbacks() {
            @Override
            public Activity getActivity() {
                return LoMoViewPagerAdapter.this.activity;
            }
            
            @Override
            public void notifyParentOfDataSetChange() {
                if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                    Log.v("LoMoViewPagerAdapter", "Notified parent of data set change");
                }
                LoMoViewPagerAdapter.this.notifyDataSetChanged();
                LoMoViewPagerAdapter.this.pager.notifyDataSetChanged();
                if (LoMoViewPagerAdapter.this.pager.getCurrentItem() == 0) {
                    if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                        Log.v("LoMoViewPagerAdapter", "Data loaded for page 0 - saving state");
                    }
                    LoMoViewPagerAdapter.this.pager.saveStateAndTrack(0);
                }
            }
            
            @Override
            public void notifyParentOfError() {
                if (LoMoViewPagerAdapter.this.state != Type.ERROR) {
                    LoMoViewPagerAdapter.this.preErrorState = LoMoViewPagerAdapter.this.state;
                }
                if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                    Log.v("LoMoViewPagerAdapter", "Pre-error state: " + LoMoViewPagerAdapter.this.preErrorState);
                }
                LoMoViewPagerAdapter.this.setState(Type.ERROR);
                LoMoViewPagerAdapter.this.notifyDataSetChanged();
                LoMoViewPagerAdapter.this.showReloadViews();
            }
        };
        this.browseReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    Log.w("LoMoViewPagerAdapter", "Received null intent");
                }
                else {
                    final String action = intent.getAction();
                    if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                        Log.v("LoMoViewPagerAdapter", "browseReceiver inovoked with Action: " + action);
                    }
                    if ("com.netflix.mediaclient.intent.action.BA_CW_REFRESH".equals(action)) {
                        if (Type.CW.equals(LoMoViewPagerAdapter.this.state)) {
                            Log.v("LoMoViewPagerAdapter", "Reloading cw row ");
                            LoMoViewPagerAdapter.this.refresh(Type.CW, LoMoViewPagerAdapter.this.loMo, LoMoViewPagerAdapter.this.listViewPos);
                        }
                        LoMoViewPagerAdapter.this.pager.invalidateCwCache();
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH".equals(action)) {
                        if (Type.IQ.equals(LoMoViewPagerAdapter.this.state)) {
                            Log.v("LoMoViewPagerAdapter", "Reloading iq row ");
                            LoMoViewPagerAdapter.this.refresh(Type.IQ, LoMoViewPagerAdapter.this.loMo, LoMoViewPagerAdapter.this.listViewPos);
                        }
                        LoMoViewPagerAdapter.this.pager.invalidateIqCache();
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.BA_CW_DATA_CHANGED".equals(action) && Type.CW.equals(LoMoViewPagerAdapter.this.state)) {
                        Log.v("LoMoViewPagerAdapter", "CW row data changed");
                        LoMoViewPagerAdapter.this.notifyDataSetChanged();
                    }
                }
            }
        };
        this.pager = pager;
        this.viewRecycler = viewRecycler;
        this.activity = (Activity)pager.getContext();
        this.reloadView = reloadView;
        this.registerBrowseNotificationReceiver();
        reloadView.setOnClickListener(this.onReloadClickListener);
        this.billboard = (ProgressiveAdapterProvider)new BillboardPagerAdapter(serviceManager, this.pagerAdapterCallbacks, viewRecycler);
        this.cw = (ProgressiveAdapterProvider)new CwPagerAdapter(serviceManager, this.pagerAdapterCallbacks, viewRecycler);
        this.iq = (ProgressiveAdapterProvider)new IqPagerAdapter(serviceManager, this.pagerAdapterCallbacks, viewRecycler);
        ProgressiveLoMoPagerAdapter standard;
        if (b) {
            standard = new GenreLoMoPagerAdapter(serviceManager, this.pagerAdapterCallbacks, viewRecycler);
        }
        else {
            standard = new StandardLoMoPagerAdapter(serviceManager, this.pagerAdapterCallbacks, viewRecycler);
        }
        this.standard = (ProgressiveAdapterProvider)standard;
        this.loading = (ProgressiveAdapterProvider)new LoadingFragmentPagerAdapter(this.pagerAdapterCallbacks, viewRecycler);
        this.error = (ProgressiveAdapterProvider)new ErrorFragmentPagerAdapter(this.pagerAdapterCallbacks);
        this.currentAdapter = this.loading;
        this.state = Type.LOADING;
    }
    
    private View getView(final int n) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "getView pos: " + n);
        }
        if (this.currentAdapter.hasMoreData() && this.isLastItem(n)) {
            return this.loading.getView(n);
        }
        return this.currentAdapter.getView(n);
    }
    
    private void hideReloadViews() {
        this.reloadView.setVisibility(8);
    }
    
    private boolean isLastItem(final int n) {
        return n == this.getCount() - 1;
    }
    
    private void registerBrowseNotificationReceiver() {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "  Registering browse notification receiver, " + this.browseReceiver.hashCode());
        }
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.BA_CW_REFRESH");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.BA_CW_DATA_CHANGED");
        this.activity.registerReceiver(this.browseReceiver, intentFilter);
    }
    
    private void setState(final Type state) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "new state: " + state);
        }
        this.state = state;
        switch (state) {
            default: {
                throw new IllegalStateException("Bad state");
            }
            case STANDARD: {
                this.currentAdapter = this.standard;
            }
            case LOADING: {
                this.currentAdapter = this.loading;
            }
            case ERROR: {
                this.currentAdapter = this.error;
            }
            case IQ: {
                this.currentAdapter = this.iq;
            }
            case CW: {
                this.currentAdapter = this.cw;
            }
            case BILLBOARD: {
                this.currentAdapter = this.billboard;
            }
        }
    }
    
    private void showLoading() {
        this.hideReloadViews();
        this.setState(Type.LOADING);
        this.notifyDataSetChanged();
    }
    
    private void showReloadViews() {
        this.reloadView.setVisibility(0);
    }
    
    private void unregisterBrowseNotificationReceiver() {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "Unregistering browse notification receiver, " + this.browseReceiver.hashCode());
        }
        this.activity.unregisterReceiver(this.browseReceiver);
    }
    
    public void destroy() {
        this.unregisterBrowseNotificationReceiver();
        this.isDestroyed = true;
        this.notifyDataSetChanged();
    }
    
    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "destroying item: " + o.getClass().getSimpleName() + ", pos: " + n);
        }
        viewGroup.removeView((View)o);
        this.viewRecycler.push((View)o);
    }
    
    @Override
    public int getCount() {
        int n = 0;
        if (this.isDestroyed) {
            return 0;
        }
        final int count = this.currentAdapter.getCount();
        if (this.currentAdapter.hasMoreData()) {
            n = 1;
        }
        return count + n;
    }
    
    @Override
    public int getItemPosition(final Object o) {
        return -2;
    }
    
    public boolean hasMoreData() {
        return this.currentAdapter.hasMoreData();
    }
    
    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "instantiateItem, pos: " + n);
        }
        final View view = this.getView(n);
        viewGroup.addView(view);
        return view;
    }
    
    public void invalidateRequestId() {
        this.currentAdapter.invalidateRequestId();
    }
    
    public boolean isLoading() {
        return this.state == Type.LOADING;
    }
    
    public boolean isShowingBillboard() {
        return this.state == Type.BILLBOARD;
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
    
    public void refresh(final Type state, final BasicLoMo loMo, final int listViewPos) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            final StringBuilder append = new StringBuilder().append("refreshing: ").append(listViewPos).append(", ");
            String title;
            if (loMo == null) {
                title = "n/a";
            }
            else {
                title = loMo.getTitle();
            }
            Log.v("LoMoViewPagerAdapter", append.append(title).append(", new state: ").append(state).toString());
        }
        this.listViewPos = listViewPos;
        this.hideReloadViews();
        this.loMo = loMo;
        this.showLoading();
        this.setState(state);
        this.currentAdapter.refreshData(loMo, listViewPos);
    }
    
    public void reload() {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            final StringBuilder append = new StringBuilder().append("Reloading data, ").append(this.listViewPos).append(", ");
            String title;
            if (this.loMo == null) {
                title = "n/a";
            }
            else {
                title = this.loMo.getTitle();
            }
            Log.v("LoMoViewPagerAdapter", append.append(title).append(", preErrorState state: ").append(this.preErrorState).toString());
        }
        this.showLoading();
        this.setState(this.preErrorState);
        this.currentAdapter.refreshData(this.loMo, this.listViewPos);
    }
    
    public void restoreFromMemento(final Memento memento) {
        this.loMo = memento.lomo;
        final Type state = memento.state;
        final View reloadView = this.reloadView;
        int visibility;
        if (state == Type.ERROR) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        reloadView.setVisibility(visibility);
        if (state == Type.ERROR || state == Type.LOADING) {
            if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                Log.v("LoMoViewPagerAdapter", "Page was in error or loading state - ignoring restoration");
            }
            return;
        }
        this.preErrorState = memento.preErrorState;
        this.setState(state);
        this.currentAdapter.restoreFromMemento(memento.adapterMemento);
    }
    
    public Memento saveToMemento() {
        return new Memento(this.state, this.preErrorState, this.loMo, this.currentAdapter);
    }
    
    public void trackPresentation(final int n) {
        this.currentAdapter.trackPresentation(n);
    }
    
    static class Memento
    {
        final BaseProgressivePagerAdapter.Memento adapterMemento;
        final BasicLoMo lomo;
        final Type preErrorState;
        final Type state;
        
        protected Memento(final Type state, final Type preErrorState, final BasicLoMo lomo, final ProgressiveAdapterProvider progressiveAdapterProvider) {
            this.state = state;
            this.preErrorState = preErrorState;
            this.lomo = lomo;
            this.adapterMemento = progressiveAdapterProvider.saveToMemento();
        }
        
        @Override
        public String toString() {
            final StringBuilder append = new StringBuilder().append("lomo: ");
            String title;
            if (this.lomo == null) {
                title = "n/a";
            }
            else {
                title = this.lomo.getTitle();
            }
            return append.append(title).append(", state: ").append(this.state).append(", adapter: ").append(this.adapterMemento).toString();
        }
    }
    
    interface ProgressiveAdapterProvider
    {
        int getCount();
        
        View getView(final int p0);
        
        boolean hasMoreData();
        
        void invalidateRequestId();
        
        void refreshData(final BasicLoMo p0, final int p1);
        
        void restoreFromMemento(final BaseProgressivePagerAdapter.Memento p0);
        
        BaseProgressivePagerAdapter.Memento saveToMemento();
        
        void trackPresentation(final int p0);
    }
    
    public enum Type
    {
        BILLBOARD, 
        CW, 
        ERROR, 
        IQ, 
        LOADING, 
        STANDARD;
    }
}
