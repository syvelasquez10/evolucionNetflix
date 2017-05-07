// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.ThreadUtils;
import android.view.ViewGroup;
import android.app.Activity;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.content.res.Resources;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.TextView;
import android.widget.LinearLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.ArrayList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;

public abstract class BaseLoLoMoAdapter<T extends BasicLoMo> extends BaseAdapter implements LoLoMoFrag$ILoLoMoAdapter
{
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
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    protected ServiceManager manager;
    protected final ObjectRecycler$ViewRecycler viewRecycler;
    
    public BaseLoLoMoAdapter(final LoLoMoFrag frag, final String lolomoId) {
        this.isLoading = true;
        this.lomos = new ArrayList<T>(40);
        this.lomoRequestPending = true;
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.viewRecycler = frag.getViewRecycler();
        this.lolomoId = lolomoId;
    }
    
    private BaseLoLoMoAdapter$RowHolder createViewsAndHolder(final View view) {
        Log.v("BaseLoLoMoAdapter", "creating views and holder");
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(2131165457);
        linearLayout.setFocusable(false);
        final TextView textView = (TextView)view.findViewById(2131165459);
        final Resources resources = this.activity.getResources();
        int n;
        if (this.activity.isForKids()) {
            n = 2131296352;
        }
        else {
            n = 2131296364;
        }
        textView.setTextColor(resources.getColor(n));
        final BaseLoLoMoAdapter$LoMoRowContent rowContent = this.createRowContent(linearLayout, (View)textView);
        TextView textView2;
        if (this.activity.isForKids()) {
            view.findViewById(2131165460).setVisibility(8);
            textView2 = (TextView)view.findViewById(2131165461);
            textView2.setVisibility(0);
        }
        else {
            textView2 = (TextView)view.findViewById(2131165460);
        }
        ((RelativeLayout$LayoutParams)textView2.getLayoutParams()).leftMargin = this.activity.getResources().getDimensionPixelOffset(2131361897) + LoMoUtils.getLomoFragOffsetLeftPx(this.activity);
        return new BaseLoLoMoAdapter$RowHolder((View)linearLayout, textView2, rowContent, view.findViewById(2131165456));
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
        this.makeFetchRequest(this.lolomoId, this.loMoStartIndex, n, new BaseLoLoMoAdapter$LoMoCallbacks(this, this.lomoRequestId, n - this.loMoStartIndex));
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
    
    private void updateRowViews(View contentGroup, int dipToPixels) {
        final int n = 8;
        final boolean b = false;
        final BasicLoMo item = this.getItem(dipToPixels);
        if (item == null) {
            Log.w("BaseLoLoMoAdapter", "Trying to show data for null lomo! Position: " + dipToPixels);
        }
        else {
            if (Log.isLoggable("BaseLoLoMoAdapter", 2)) {
                Log.v("BaseLoLoMoAdapter", "Updating LoMo row content: " + item.getTitle() + ", type: " + item.getType() + ", pos: " + dipToPixels);
            }
            final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder = (BaseLoLoMoAdapter$RowHolder)contentGroup.getTag();
            final TextView title = baseLoLoMoAdapter$RowHolder.title;
            String text;
            if (item.getType() == LoMoType.BILLBOARD) {
                text = this.activity.getString(2131493259);
            }
            else {
                text = item.getTitle();
            }
            title.setText((CharSequence)text);
            final TextView title2 = baseLoLoMoAdapter$RowHolder.title;
            int visibility;
            if (item.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(this.activity)) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            title2.setVisibility(visibility);
            final View shelf = baseLoLoMoAdapter$RowHolder.shelf;
            int visibility2 = n;
            if (this.isRowAfterBillboardOrCwRow(dipToPixels, item.getType())) {
                visibility2 = n;
                if (!this.activity.isForKids()) {
                    visibility2 = 0;
                }
            }
            shelf.setVisibility(visibility2);
            baseLoLoMoAdapter$RowHolder.rowContent.refresh(item, dipToPixels);
            if (this.activity.isForKids()) {
                if (item.getType() == LoMoType.CONTINUE_WATCHING) {
                    baseLoLoMoAdapter$RowHolder.contentGroup.setBackgroundResource(2130837709);
                    baseLoLoMoAdapter$RowHolder.contentGroup.setPadding(0, 0, 0, AndroidUtils.dipToPixels((Context)this.activity, 22));
                    baseLoLoMoAdapter$RowHolder.title.setTextColor(this.activity.getResources().getColor(2131296355));
                    return;
                }
                ViewUtils.setBackgroundDrawableCompat(baseLoLoMoAdapter$RowHolder.contentGroup, null);
                if (dipToPixels == this.getCount() - 1) {
                    dipToPixels = 1;
                }
                else {
                    dipToPixels = 0;
                }
                contentGroup = baseLoLoMoAdapter$RowHolder.contentGroup;
                if (dipToPixels != 0) {
                    dipToPixels = AndroidUtils.dipToPixels((Context)this.activity, 24);
                }
                else {
                    dipToPixels = 0;
                }
                contentGroup.setPadding(0, 0, 0, dipToPixels);
                baseLoLoMoAdapter$RowHolder.title.setTextColor(baseLoLoMoAdapter$RowHolder.defaultTitleColors);
            }
            else {
                final StickyListHeadersListView listView = this.frag.getListView();
                int headerViewsCount;
                if (listView == null) {
                    headerViewsCount = 0;
                }
                else {
                    headerViewsCount = listView.getHeaderViewsCount();
                }
                if (headerViewsCount == 0) {
                    int actionBarHeight = b ? 1 : 0;
                    if (dipToPixels == 0) {
                        actionBarHeight = this.activity.getActionBarHeight();
                    }
                    ViewUtils.setPaddingTop(contentGroup, actionBarHeight);
                }
            }
        }
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
    
    protected abstract BaseLoLoMoAdapter$LoMoRowContent createRowContent(final LinearLayout p0, final View p1);
    
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
    
    public View getView(final int n, View dummyView, final ViewGroup viewGroup) {
        if (this.activity.destroyed()) {
            Log.d("BaseLoLoMoAdapter", "activity is destroyed - can't getView");
            dummyView = this.createDummyView();
        }
        else {
            View inflate;
            if ((inflate = dummyView) == null) {
                inflate = this.activity.getLayoutInflater().inflate(2130903122, (ViewGroup)null);
                inflate.setTag((Object)this.createViewsAndHolder(inflate));
            }
            this.updateRowViews(inflate, n);
            dummyView = inflate;
            if (this.hasMoreData) {
                dummyView = inflate;
                if (n == this.getCount() - 1) {
                    this.fetchMoreData();
                    return inflate;
                }
            }
        }
        return dummyView;
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
    
    protected void onDataLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        this.refreshData();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
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
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    protected void updateLoMoData(final List<T> list) {
        this.lomos.addAll((Collection<? extends T>)list);
        this.loMoStartIndex += list.size();
        this.notifyDataSetChanged();
    }
}
