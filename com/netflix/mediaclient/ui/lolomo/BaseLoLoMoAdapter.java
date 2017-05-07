// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import java.util.Collection;
import com.netflix.mediaclient.util.ThreadUtils;
import android.view.ViewGroup;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.LoMoType;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.content.res.Resources;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.util.AndroidUtils;
import com.viewpagerindicator.CirclePageIndicator;
import android.widget.LinearLayout;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.List;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.BasicLoMo;

public abstract class BaseLoLoMoAdapter<T extends BasicLoMo> extends BaseAdapter implements ManagerStatusListener, LoadingStatus
{
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    protected static final String TAG = "LoLoMoAdapter";
    private final NetflixActivity activity;
    private final View dummyView;
    private final LoLoMoFrag frag;
    private boolean hasMoreData;
    private boolean isLoading;
    private int loMoStartIndex;
    private final List<T> loMos;
    private final String lolomoId;
    private long lomoRequestId;
    private boolean lomoRequestPending;
    protected LoadingStatusCallback mLoadingStatusCallback;
    private ServiceManager manager;
    private final Set<LoMoViewPager> pagerSet;
    private final ViewRecycler viewRecycler;
    
    public BaseLoLoMoAdapter(final LoLoMoFrag frag, final String lolomoId) {
        this.pagerSet = new HashSet<LoMoViewPager>();
        this.isLoading = true;
        this.loMos = new ArrayList<T>(40);
        this.lomoRequestPending = true;
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.viewRecycler = frag.getViewRecycler();
        this.lolomoId = lolomoId;
        this.dummyView = new View((Context)this.activity);
    }
    
    private RowHolder createViewsAndHolder(final View view) {
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(2131230914);
        linearLayout.setFocusable(false);
        final CirclePageIndicator circlePageIndicator = new CirclePageIndicator((Context)this.activity);
        final LoMoViewPager viewPager = new LoMoViewPager(this.frag, this.manager, circlePageIndicator, this.viewRecycler, view.findViewById(2131230916), this.isGenreList());
        this.pagerSet.add(viewPager);
        viewPager.setFocusable(false);
        linearLayout.addView((View)viewPager);
        circlePageIndicator.setRadius(AndroidUtils.dipToPixels((Context)this.activity, 4));
        circlePageIndicator.setPageColor(1627389951);
        circlePageIndicator.setStrokeColor(0);
        circlePageIndicator.setStrokeWidth(0.0f);
        circlePageIndicator.setOnPageChangeListener(viewPager.getOnPageChangeListener());
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setVisibility(8);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams.topMargin = (int)(2.0f * circlePageIndicator.getRadius() + circlePageIndicator.getPaddingTop() + circlePageIndicator.getPaddingBottom() + 1.0f) * -2;
        linearLayout.addView((View)circlePageIndicator, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        final TextView textView = (TextView)view.findViewById(2131230917);
        final Resources resources = this.activity.getResources();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)textView.getLayoutParams();
        layoutParams.leftMargin = resources.getDimensionPixelOffset(2131492938) + resources.getDimensionPixelOffset(2131492939);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return new RowHolder(textView, viewPager, view.findViewById(2131230913));
    }
    
    private void fetchMoreData() {
        this.isLoading = true;
        this.lomoRequestId = System.nanoTime();
        final int n = this.loMoStartIndex + 20 - 1;
        if (Log.isLoggable("LoLoMoAdapter", 2)) {
            Log.v("LoLoMoAdapter", "fetching more data, starting at index: " + this.loMoStartIndex);
            Log.v("LoLoMoAdapter", "fetching from: " + this.loMoStartIndex + " to: " + n + ", id: " + this.lolomoId);
        }
        if (this.manager == null) {
            Log.w("LoLoMoAdapter", "Manager is null - can't refresh data");
            return;
        }
        this.makeFetchRequest(this.lolomoId, this.loMoStartIndex, n, new LoMoCallbacks(this.lomoRequestId, n - this.loMoStartIndex));
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
    }
    
    private boolean isAnyPagerLoading() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isLoading()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isRowAfterBillboardOrCwRow(final int n, LoMoType type) {
        if (n == 1) {
            type = this.getItem(0).getType();
            if (type == LoMoType.BILLBOARD || type == LoMoType.CONTINUE_WATCHING) {
                return true;
            }
        }
        return false;
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    protected boolean areRequestsPending() {
        return this.lomoRequestPending;
    }
    
    public void destroy() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().destroy();
        }
    }
    
    protected Activity getActivity() {
        return this.activity;
    }
    
    public int getCount() {
        return this.loMos.size();
    }
    
    protected String getGenreId() {
        return this.lolomoId;
    }
    
    public T getItem(final int n) {
        return this.loMos.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.frag.getActivity() == null || ((NetflixActivity)this.frag.getActivity()).destroyed()) {
            Log.d("LoLoMoAdapter", "activity is null or destroyed - can't getView");
            return this.dummyView;
        }
        View inflate;
        if ((inflate = view) == null) {
            inflate = this.activity.getLayoutInflater().inflate(2130903100, (ViewGroup)null);
            inflate.setTag((Object)this.createViewsAndHolder(inflate));
        }
        this.updateRowViews((RowHolder)inflate.getTag(), n);
        if (this.hasMoreData && n == this.getCount() - 1) {
            this.fetchMoreData();
        }
        return inflate;
    }
    
    protected void initLoadingState() {
        ThreadUtils.assertOnMain();
        this.loMos.clear();
        this.notifyDataSetChanged();
        this.lomoRequestId = -2147483648L;
        this.lomoRequestPending = true;
        this.hasMoreData = false;
        this.loMoStartIndex = 0;
    }
    
    public boolean isEnabled(final int n) {
        return false;
    }
    
    protected abstract boolean isGenreList();
    
    public boolean isLoadingData() {
        return this.isLoading || this.isAnyPagerLoading();
    }
    
    protected abstract void makeFetchRequest(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        ThreadUtils.assertOnMain();
        if (this.getCount() > 0) {
            this.hideLoadingAndErrorViews();
        }
        else if (!this.areRequestsPending()) {
            this.showErrorView();
        }
    }
    
    protected void onLoaded(final int n) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(n);
        }
    }
    
    public void onManagerReady(final ServiceManager manager, final int n) {
        this.manager = manager;
        this.refresh();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        this.manager = null;
    }
    
    public void onPause() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().onPause();
        }
    }
    
    public void onResume() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().onResume();
        }
    }
    
    public void refresh() {
        Log.v("LoLoMoAdapter", "Refreshing data");
        this.isLoading = true;
        this.initLoadingState();
        this.fetchMoreData();
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(0);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    protected void updateLoMoData(final List<T> list) {
        this.loMos.addAll((Collection<? extends T>)list);
        this.loMoStartIndex += list.size();
        this.notifyDataSetChanged();
    }
    
    protected void updateRowViews(final RowHolder rowHolder, final int n) {
        final boolean b = false;
        final BasicLoMo item = this.getItem(n);
        if (item == null) {
            Log.w("LoLoMoAdapter", "Trying to show data for null lomo! Position: " + n);
            return;
        }
        if (Log.isLoggable("LoLoMoAdapter", 2)) {
            Log.v("LoLoMoAdapter", "Updating LoMo row content: " + item.getTitle() + ", type: " + item.getType() + ", pos: " + n);
        }
        final TextView title = rowHolder.title;
        String text;
        if (item.getType() == LoMoType.BILLBOARD) {
            text = this.activity.getString(2131296674);
        }
        else {
            text = item.getTitle();
        }
        title.setText((CharSequence)text);
        final TextView title2 = rowHolder.title;
        int visibility;
        if (item.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(this.activity)) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        title2.setVisibility(visibility);
        final View shelf = rowHolder.shelf;
        int visibility2;
        if (this.isRowAfterBillboardOrCwRow(n, item.getType())) {
            visibility2 = (b ? 1 : 0);
        }
        else {
            visibility2 = 8;
        }
        shelf.setVisibility(visibility2);
        rowHolder.pager.refresh(item, n);
    }
    
    private class LoMoCallbacks extends LoggingManagerCallback
    {
        private final int numItems;
        private final long requestId;
        
        public LoMoCallbacks(final long requestId, final int numItems) {
            super("LoLoMoAdapter");
            this.requestId = requestId;
            this.numItems = numItems;
        }
        
        private void handleResult(final List<T> list, final int n) {
            BaseLoLoMoAdapter.this.hasMoreData = true;
            BaseLoLoMoAdapter.this.lomoRequestPending = false;
            if (this.requestId != BaseLoLoMoAdapter.this.lomoRequestId) {
                Log.v("LoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
                return;
            }
            BaseLoLoMoAdapter.this.isLoading = false;
            BaseLoLoMoAdapter.this.onLoaded(n);
            if (n != 0) {
                Log.w("LoLoMoAdapter", "Invalid status code");
                BaseLoLoMoAdapter.this.notifyDataSetChanged();
                BaseLoLoMoAdapter.this.hasMoreData = false;
                return;
            }
            if (list == null || list.size() <= 0) {
                Log.v("LoLoMoAdapter", "No loMos in response");
                BaseLoLoMoAdapter.this.hasMoreData = false;
                BaseLoLoMoAdapter.this.notifyDataSetChanged();
                return;
            }
            if (Log.isLoggable("LoLoMoAdapter", 2)) {
                Log.v("LoLoMoAdapter", "Got " + list.size() + " items, expected " + this.numItems);
            }
            if (list.size() < this.numItems) {
                BaseLoLoMoAdapter.this.hasMoreData = false;
            }
            BaseLoLoMoAdapter.this.updateLoMoData(list);
        }
        
        @Override
        public void onGenresFetched(final List<Genre> list, final int n) {
            super.onGenresFetched(list, n);
            this.handleResult((List<T>)list, n);
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> list, final int n) {
            super.onLoMosFetched(list, n);
            this.handleResult((List<T>)list, n);
        }
    }
    
    static class RowHolder
    {
        public final LoMoViewPager pager;
        public final View shelf;
        public final TextView title;
        
        RowHolder(final TextView title, final LoMoViewPager pager, final View shelf) {
            this.title = title;
            this.pager = pager;
            this.shelf = shelf;
        }
    }
}
