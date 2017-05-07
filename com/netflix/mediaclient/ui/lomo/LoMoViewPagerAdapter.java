// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import java.util.EnumMap;
import android.support.v4.view.PagerAdapter;

public class LoMoViewPagerAdapter extends PagerAdapter
{
    private static final EnumMap<LoMoType, LoMoViewPagerAdapter$Type> LOMO_TYPE_TABLE;
    private static final String TAG = "LoMoViewPagerAdapter";
    private final NetflixActivity activity;
    private final LoMoViewPagerAdapter$RowAdapterSet adapters;
    private final BroadcastReceiver browseReceiver;
    private RowAdapter currentAdapter;
    private boolean isDestroyed;
    private int listViewPos;
    private BasicLoMo loMo;
    private final View$OnClickListener onReloadClickListener;
    private final LoMoViewPager pager;
    private final RowAdapterCallbacks pagerAdapterCallbacks;
    private LoMoViewPagerAdapter$Type preErrorState;
    private final View reloadView;
    private LoMoViewPagerAdapter$Type state;
    private final ObjectRecycler$ViewRecycler viewRecycler;
    
    static {
        LOMO_TYPE_TABLE = new LoMoViewPagerAdapter$1(LoMoType.class);
    }
    
    public LoMoViewPagerAdapter(final LoMoViewPager pager, final ServiceManager serviceManager, final ObjectRecycler$ViewRecycler viewRecycler, final View reloadView, final boolean b) {
        this.state = LoMoViewPagerAdapter$Type.LOADING;
        this.preErrorState = LoMoViewPagerAdapter$Type.LOADING;
        this.onReloadClickListener = (View$OnClickListener)new LoMoViewPagerAdapter$2(this);
        this.pagerAdapterCallbacks = new LoMoViewPagerAdapter$3(this);
        this.browseReceiver = new LoMoViewPagerAdapter$4(this);
        this.pager = pager;
        this.viewRecycler = viewRecycler;
        this.activity = (NetflixActivity)pager.getContext();
        this.reloadView = reloadView;
        this.adapters = new LoMoViewPagerAdapter$RowAdapterSet(serviceManager, this.pagerAdapterCallbacks, viewRecycler, b);
        this.registerBrowseNotificationReceiver();
        reloadView.setOnClickListener(this.onReloadClickListener);
        this.currentAdapter = this.adapters.loading;
    }
    
    private View getView(final int n) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "getView pos: " + n);
        }
        if (this.currentAdapter.hasMoreData() && this.isLastItem(n)) {
            return this.adapters.loading.getView(n);
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
            Log.v("LoMoViewPagerAdapter", "Registering browse notification receiver, " + this.browseReceiver.hashCode());
        }
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.BA_CW_REFRESH");
        this.activity.registerReceiver(this.browseReceiver, intentFilter);
    }
    
    private void setState(final LoMoViewPagerAdapter$Type state) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "new state: " + state);
        }
        this.state = state;
        switch (LoMoViewPagerAdapter$5.$SwitchMap$com$netflix$mediaclient$ui$lomo$LoMoViewPagerAdapter$Type[state.ordinal()]) {
            default: {
                throw new IllegalStateException("Bad state");
            }
            case 1: {
                this.currentAdapter = this.adapters.standard;
            }
            case 2: {
                this.currentAdapter = this.adapters.loading;
            }
            case 3: {
                this.currentAdapter = this.adapters.error;
            }
            case 4: {
                this.currentAdapter = this.adapters.iq;
            }
            case 5: {
                this.currentAdapter = this.adapters.cw;
            }
            case 6: {
                this.currentAdapter = this.adapters.billboard;
            }
            case 7: {
                this.currentAdapter = this.adapters.character;
            }
        }
    }
    
    private void showLoading() {
        this.hideReloadViews();
        this.setState(LoMoViewPagerAdapter$Type.LOADING);
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
        Log.v("LoMoViewPagerAdapter", "Destroying adapter");
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
        return n + count;
    }
    
    @Override
    public int getItemPosition(final Object o) {
        return -2;
    }
    
    public LinearLayout$LayoutParams getLayoutParams(final LoMoType loMoType) {
        int n = 0;
        switch (LoMoViewPagerAdapter$5.$SwitchMap$com$netflix$mediaclient$servicemgr$model$LoMoType[loMoType.ordinal()]) {
            default: {
                n = this.adapters.standard.getRowHeightInPx();
                break;
            }
            case 1: {
                n = this.adapters.billboard.getRowHeightInPx();
                break;
            }
            case 2: {
                n = this.adapters.cw.getRowHeightInPx();
                break;
            }
            case 3: {
                n = this.adapters.character.getRowHeightInPx();
                break;
            }
        }
        Log.v("LoMoViewPagerAdapter", "Creating layout params with height: " + n);
        return new LinearLayout$LayoutParams(-1, n);
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
        return this.state == LoMoViewPagerAdapter$Type.LOADING;
    }
    
    public boolean isShowingBillboard() {
        return this.state == LoMoViewPagerAdapter$Type.BILLBOARD;
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
    
    public void refresh(final BasicLoMo loMo, final int listViewPos) {
        final LoMoViewPagerAdapter$Type state = LoMoViewPagerAdapter.LOMO_TYPE_TABLE.get(loMo.getType());
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
    
    public void restoreFromMemento(final LoMoViewPagerAdapter$Memento loMoViewPagerAdapter$Memento) {
        this.loMo = loMoViewPagerAdapter$Memento.lomo;
        final LoMoViewPagerAdapter$Type state = loMoViewPagerAdapter$Memento.state;
        final View reloadView = this.reloadView;
        int visibility;
        if (state == LoMoViewPagerAdapter$Type.ERROR) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        reloadView.setVisibility(visibility);
        if (state == LoMoViewPagerAdapter$Type.ERROR || state == LoMoViewPagerAdapter$Type.LOADING) {
            if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                Log.v("LoMoViewPagerAdapter", "Page was in error or loading state - ignoring restoration");
            }
            return;
        }
        this.preErrorState = loMoViewPagerAdapter$Memento.preErrorState;
        this.setState(state);
        this.currentAdapter.restoreFromMemento(loMoViewPagerAdapter$Memento.adapterMemento);
    }
    
    public LoMoViewPagerAdapter$Memento saveToMemento() {
        return new LoMoViewPagerAdapter$Memento(this.state, this.preErrorState, this.loMo, this.currentAdapter);
    }
    
    public void trackPresentation(final int n) {
        this.currentAdapter.trackPresentation(n);
    }
}
