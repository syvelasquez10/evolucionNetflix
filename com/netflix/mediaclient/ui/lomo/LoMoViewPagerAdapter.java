// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.ViewGroup;
import android.widget.LinearLayout$LayoutParams;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoDuplicate;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoHeroDuplicate;
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
    private final LoMoViewPagerAdapter$IRowAdapterProvider adapters;
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
        this.adapters = RowAdapterProvider.create(serviceManager, this.pagerAdapterCallbacks, viewRecycler, b);
        this.registerBrowseNotificationReceiver();
        reloadView.setOnClickListener(this.onReloadClickListener);
        this.currentAdapter = this.adapters.getLoadingAdapter();
    }
    
    private LoMoViewPagerAdapter$Type convertLomoTypeToAdapterType(final BasicLoMo basicLoMo) {
        LoMoViewPagerAdapter$Type kubrick_HERO;
        if (basicLoMo instanceof KubrickLoMoHeroDuplicate) {
            kubrick_HERO = LoMoViewPagerAdapter$Type.KUBRICK_HERO;
        }
        else {
            if (basicLoMo instanceof KubrickLoMoDuplicate) {
                return LoMoViewPagerAdapter$Type.STANDARD;
            }
            kubrick_HERO = LoMoViewPagerAdapter.LOMO_TYPE_TABLE.get(basicLoMo.getType());
            if (this.activity.isKubrick() && (kubrick_HERO = kubrick_HERO) == LoMoViewPagerAdapter$Type.STANDARD) {
                return LoMoViewPagerAdapter$Type.KUBRICK_GALLERY;
            }
        }
        return kubrick_HERO;
    }
    
    private View getView(final int n) {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "getView pos: " + n);
        }
        if (this.currentAdapter.hasMoreData() && this.isLastItem(n)) {
            return this.adapters.getLoadingAdapter().getView(n);
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
                this.currentAdapter = this.adapters.getStandardAdapter();
            }
            case 2: {
                this.currentAdapter = this.adapters.getLoadingAdapter();
            }
            case 3: {
                this.currentAdapter = this.adapters.getErrorAdapter();
            }
            case 4: {
                this.currentAdapter = this.adapters.getIqAdapter();
            }
            case 5: {
                this.currentAdapter = this.adapters.getCwAdapter();
            }
            case 6: {
                this.currentAdapter = this.adapters.getBillboardAdapter();
            }
            case 7: {
                this.currentAdapter = this.adapters.getCharacterAdapter();
            }
            case 8: {
                this.currentAdapter = this.adapters.getKubrickHeroAdapter();
            }
            case 9: {
                this.currentAdapter = this.adapters.getKubrickGalleryAdapter();
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
    
    public LinearLayout$LayoutParams createLayoutParams() {
        final int rowHeightInPx = this.currentAdapter.getRowHeightInPx();
        Log.v("LoMoViewPagerAdapter", "Creating layout params with height: " + rowHeightInPx);
        return new LinearLayout$LayoutParams(-1, rowHeightInPx);
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
        final LoMoViewPagerAdapter$Type convertLomoTypeToAdapterType = this.convertLomoTypeToAdapterType(loMo);
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            final StringBuilder append = new StringBuilder().append("refreshing: ").append(listViewPos).append(", ");
            String title;
            if (loMo == null) {
                title = "n/a";
            }
            else {
                title = loMo.getTitle();
            }
            Log.v("LoMoViewPagerAdapter", append.append(title).append(", new state: ").append(convertLomoTypeToAdapterType).toString());
        }
        this.listViewPos = listViewPos;
        this.hideReloadViews();
        this.loMo = loMo;
        this.showLoading();
        this.setState(convertLomoTypeToAdapterType);
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
    
    public boolean shouldOverlapPages() {
        return this.currentAdapter.shouldOverlapPages();
    }
    
    public void trackPresentation(final int n) {
        this.currentAdapter.trackPresentation(n);
    }
}
