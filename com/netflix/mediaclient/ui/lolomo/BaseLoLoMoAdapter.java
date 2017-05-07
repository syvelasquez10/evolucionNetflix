// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.content.res.ColorStateList;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import java.util.Collection;
import com.netflix.mediaclient.util.ThreadUtils;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.content.res.Resources;
import com.netflix.mediaclient.servicemgr.LoMoUtils;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.TextView;
import android.widget.LinearLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.ArrayList;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.LoadingStatus;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.BasicLoMo;

public abstract class BaseLoLoMoAdapter<T extends BasicLoMo> extends BaseAdapter implements ILoLoMoAdapter
{
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final String TAG = "BaseLoLoMoAdapter";
    protected final NetflixActivity activity;
    protected final LoLoMoFrag frag;
    private boolean hasMoreData;
    private boolean isLoading;
    private int loMoStartIndex;
    private final String lolomoId;
    private long lomoRequestId;
    private boolean lomoRequestPending;
    private final List<T> lomos;
    protected LoadingStatusCallback mLoadingStatusCallback;
    protected ServiceManager manager;
    protected final ViewRecycler viewRecycler;
    
    public BaseLoLoMoAdapter(final LoLoMoFrag frag, final String lolomoId) {
        this.isLoading = true;
        this.lomos = new ArrayList<T>(40);
        this.lomoRequestPending = true;
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.viewRecycler = frag.getViewRecycler();
        this.lolomoId = lolomoId;
    }
    
    private RowHolder createViewsAndHolder(final View view) {
        Log.v("BaseLoLoMoAdapter", "creating views and holder");
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(2131165428);
        linearLayout.setFocusable(false);
        final TextView textView = (TextView)view.findViewById(2131165430);
        final Resources resources = this.activity.getResources();
        int n;
        if (this.activity.isForKids()) {
            n = 2131296306;
        }
        else {
            n = 2131296315;
        }
        textView.setTextColor(resources.getColor(n));
        final LoMoRowContent rowContent = this.createRowContent(linearLayout, (View)textView);
        TextView textView2;
        if (this.activity.isForKids()) {
            view.findViewById(2131165431).setVisibility(8);
            textView2 = (TextView)view.findViewById(2131165432);
            textView2.setVisibility(0);
        }
        else {
            textView2 = (TextView)view.findViewById(2131165431);
        }
        ((RelativeLayout$LayoutParams)textView2.getLayoutParams()).leftMargin = LoMoUtils.getLomoFragOffsetLeftPx(this.activity) + this.activity.getResources().getDimensionPixelOffset(2131361870);
        return new RowHolder((View)linearLayout, textView2, rowContent, view.findViewById(2131165427));
    }
    
    private void fetchMoreData() {
        this.isLoading = true;
        this.lomoRequestId = System.nanoTime();
        final int n = this.loMoStartIndex + 20 - 1;
        if (Log.isLoggable("BaseLoLoMoAdapter", 2)) {
            Log.v("BaseLoLoMoAdapter", "fetching more data, starting at index: " + this.loMoStartIndex);
            Log.v("BaseLoLoMoAdapter", "fetching from: " + this.loMoStartIndex + " to: " + n + ", id: " + this.lolomoId);
        }
        if (this.manager == null) {
            Log.w("BaseLoLoMoAdapter", "Manager is null - can't refresh data");
            return;
        }
        this.makeFetchRequest(this.lolomoId, this.loMoStartIndex, n, new LoMoCallbacks(this.lomoRequestId, n - this.loMoStartIndex));
    }
    
    private void hideLoadingAndErrorViews() {
        this.frag.hideLoadingAndErrorViews();
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
    
    protected View createDummyView() {
        final View view = new View((Context)this.activity);
        view.setVisibility(8);
        return view;
    }
    
    protected abstract LoMoRowContent createRowContent(final LinearLayout p0, final View p1);
    
    public void destroy() {
    }
    
    protected Activity getActivity() {
        return this.activity;
    }
    
    public int getCount() {
        return this.lomos.size();
    }
    
    protected String getGenreId() {
        return this.lolomoId;
    }
    
    public long getHeaderId(final int n) {
        return -1L;
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        View dummyView = view;
        if (view == null) {
            dummyView = this.createDummyView();
        }
        return dummyView;
    }
    
    public T getItem(final int n) {
        return this.lomos.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.activity.destroyed()) {
            Log.d("BaseLoLoMoAdapter", "activity is destroyed - can't getView");
            return this.createDummyView();
        }
        View inflate;
        if ((inflate = view) == null) {
            inflate = this.activity.getLayoutInflater().inflate(2130903114, (ViewGroup)null);
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
        this.lomos.clear();
        this.lomoRequestId = -2147483648L;
        this.lomoRequestPending = true;
        this.hasMoreData = false;
        this.loMoStartIndex = 0;
        this.notifyDataSetChanged();
    }
    
    public boolean isEnabled(final int n) {
        return false;
    }
    
    protected abstract boolean isGenreList();
    
    public boolean isLoadingData() {
        return this.isLoading;
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
    
    protected void onDataLoaded(final int n) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(n);
        }
    }
    
    public void onManagerReady(final ServiceManager manager, final int n) {
        this.manager = manager;
        this.refreshData();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        this.manager = null;
    }
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void refreshData() {
        Log.v("BaseLoLoMoAdapter", "Refreshing data");
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
        this.lomos.addAll((Collection<? extends T>)list);
        this.loMoStartIndex += list.size();
        this.notifyDataSetChanged();
    }
    
    protected void updateRowViews(final RowHolder rowHolder, final int n) {
        final boolean b = false;
        final BasicLoMo item = this.getItem(n);
        if (item == null) {
            Log.w("BaseLoLoMoAdapter", "Trying to show data for null lomo! Position: " + n);
        }
        else {
            if (Log.isLoggable("BaseLoLoMoAdapter", 2)) {
                Log.v("BaseLoLoMoAdapter", "Updating LoMo row content: " + item.getTitle() + ", type: " + item.getType() + ", pos: " + n);
            }
            final TextView title = rowHolder.title;
            String text;
            if (item.getType() == LoMoType.BILLBOARD) {
                text = this.activity.getString(2131493301);
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
            if (this.isRowAfterBillboardOrCwRow(n, item.getType()) && !this.activity.isForKids()) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 8;
            }
            shelf.setVisibility(visibility2);
            rowHolder.rowContent.refresh(item, n);
            if (this.activity.isForKids()) {
                if (item.getType() == LoMoType.CONTINUE_WATCHING) {
                    rowHolder.contentGroup.setBackgroundResource(2130837730);
                    rowHolder.title.setTextColor(this.activity.getResources().getColor(2131296310));
                    return;
                }
                ViewUtils.setBackgroundDrawableCompat(rowHolder.contentGroup, null);
                rowHolder.title.setTextColor(rowHolder.defaultTitleColors);
            }
        }
    }
    
    private class LoMoCallbacks extends LoggingManagerCallback
    {
        private final int numItems;
        private final long requestId;
        
        public LoMoCallbacks(final long requestId, final int numItems) {
            super("BaseLoLoMoAdapter");
            this.requestId = requestId;
            this.numItems = numItems;
        }
        
        private void handleResult(final List<T> list, final int n) {
            BaseLoLoMoAdapter.this.hasMoreData = true;
            BaseLoLoMoAdapter.this.lomoRequestPending = false;
            if (this.requestId != BaseLoLoMoAdapter.this.lomoRequestId) {
                Log.v("BaseLoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
                return;
            }
            BaseLoLoMoAdapter.this.isLoading = false;
            BaseLoLoMoAdapter.this.onDataLoaded(n);
            if (n != 0) {
                Log.w("BaseLoLoMoAdapter", "Invalid status code");
                BaseLoLoMoAdapter.this.hasMoreData = false;
                BaseLoLoMoAdapter.this.notifyDataSetChanged();
                return;
            }
            if (list == null || list.size() <= 0) {
                Log.v("BaseLoLoMoAdapter", "No loMos in response");
                BaseLoLoMoAdapter.this.hasMoreData = false;
                BaseLoLoMoAdapter.this.notifyDataSetChanged();
                return;
            }
            if (list.size() < this.numItems) {
                BaseLoLoMoAdapter.this.hasMoreData = false;
            }
            if (Log.isLoggable("BaseLoLoMoAdapter", 2)) {
                Log.v("BaseLoLoMoAdapter", "Got " + list.size() + " items, expected " + this.numItems + ", hasMoreData: " + BaseLoLoMoAdapter.this.hasMoreData);
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
    
    public interface LoMoRowContent
    {
        void invalidateRequestId();
        
        void refresh(final BasicLoMo p0, final int p1);
    }
    
    static class RowHolder
    {
        public final View contentGroup;
        public final ColorStateList defaultTitleColors;
        public final LoMoRowContent rowContent;
        public final View shelf;
        public final TextView title;
        
        RowHolder(final View contentGroup, final TextView title, final LoMoRowContent rowContent, final View shelf) {
            this.contentGroup = contentGroup;
            this.title = title;
            this.rowContent = rowContent;
            this.shelf = shelf;
            this.defaultTitleColors = title.getTextColors();
        }
        
        public void invalidateRequestId() {
            if (this.rowContent == null) {
                return;
            }
            this.rowContent.invalidateRequestId();
        }
    }
}
