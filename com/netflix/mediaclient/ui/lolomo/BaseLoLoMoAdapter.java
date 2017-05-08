// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.Typeface;
import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.Coppola2Utils;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.Iterator;
import android.widget.ListView;
import android.view.ViewGroup;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.TextView;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.viewpagerindicator.CirclePageIndicator;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.ums.UserMessageAreaView;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;

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
    private LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    private UserMessageAreaView mUserMessageAreaView;
    private ServiceManager manager;
    private final Set<LoMoViewPager> pagerSet;
    private final ObjectRecycler$ViewRecycler viewRecycler;
    
    public BaseLoLoMoAdapter(final LoLoMoFrag frag, final String lolomoId) {
        this.pagerSet = new HashSet<LoMoViewPager>();
        this.isLoading = true;
        this.lomos = new ArrayList<T>(40);
        this.lomoRequestPending = true;
        this.mUserMessageAreaView = null;
        this.frag = frag;
        this.activity = (NetflixActivity)frag.getActivity();
        this.viewRecycler = frag.getViewRecycler();
        this.lolomoId = lolomoId;
    }
    
    private boolean areRequestsPending() {
        return this.lomoRequestPending;
    }
    
    private BaseLoLoMoAdapter$LoMoRowContent createRowContent(final LinearLayout linearLayout, final View view) {
        final CirclePageIndicator circlePageIndicator = new CirclePageIndicator((Context)this.activity);
        final LoMoViewPager viewPager = new LoMoViewPager(this.frag, this.manager, circlePageIndicator, this.viewRecycler, view, this.isGenreList());
        this.pagerSet.add(viewPager);
        viewPager.setFocusable(false);
        linearLayout.addView((View)viewPager);
        circlePageIndicator.setFillColor(-1);
        circlePageIndicator.setPageColor(-11513776);
        circlePageIndicator.setRadius(AndroidUtils.dipToPixels((Context)this.activity, 4));
        circlePageIndicator.setStrokeColor(0);
        circlePageIndicator.setStrokeWidth(0.0f);
        circlePageIndicator.setOnPageChangeListener(viewPager.getOnPageChangeListener());
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setVisibility(8);
        circlePageIndicator.measure(0, 0);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams.topMargin = circlePageIndicator.getMeasuredHeight() * -2;
        linearLayout.addView((View)circlePageIndicator, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        return viewPager;
    }
    
    private BaseLoLoMoAdapter$RowHolder createViewsAndHolder(final View view) {
        Log.v("BaseLoLoMoAdapter", "creating views and holder");
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(2131689892);
        linearLayout.setFocusable(false);
        final TextView textView = (TextView)view.findViewById(2131689894);
        final Resources resources = this.activity.getResources();
        int n;
        if (BrowseExperience.showKidsExperience()) {
            n = 2131624002;
        }
        else {
            n = 2131624102;
        }
        textView.setTextColor(resources.getColor(n));
        return this.createHolder(view, linearLayout, this.initTitleView(view), this.createRowContent(linearLayout, (View)textView), view.findViewById(2131689920));
    }
    
    private void fetchMoreData() {
        this.isLoading = true;
        this.lomoRequestId = System.nanoTime();
        final int n = this.loMoStartIndex + 20 - 1;
        if (Log.isLoggable()) {
            Log.v("BaseLoLoMoAdapter", "fetching more data, starting at index: " + this.loMoStartIndex);
            Log.v("BaseLoLoMoAdapter", "fetching from: " + this.loMoStartIndex + " to: " + n + ", id: " + this.lolomoId);
        }
        if (this.manager == null) {
            Log.w("BaseLoLoMoAdapter", "Manager is null - can't refresh data");
            return;
        }
        this.makeFetchRequest(this.lolomoId, this.loMoStartIndex, n, new BaseLoLoMoAdapter$LoMoCallbacks(this, this.lomoRequestId, n - this.loMoStartIndex));
    }
    
    private UmaAlert getUserMessage() {
        if (this.manager != null && this.manager.isReady()) {
            final UmaAlert userMessageAlert = this.manager.getUserMessageAlert();
            if (userMessageAlert != null) {
                return userMessageAlert;
            }
        }
        return null;
    }
    
    private UserMessageAreaView handleUserMessage() {
        final ListView listView = this.frag.getListView();
        if (listView != null) {
            final UmaAlert userMessage = this.getUserMessage();
            if (userMessage != null && !userMessage.isConsumed()) {
                final UserMessageAreaView userMessageAreaView = new UserMessageAreaView(listView.getContext());
                userMessageAreaView.show(userMessage, listView, (ViewGroup)this.frag.getContentView());
                return userMessageAreaView;
            }
        }
        return null;
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
    
    private void refreshUserMessage() {
        final UmaAlert userMessage = this.getUserMessage();
        if (userMessage != null && (userMessage.isStale() || userMessage.isConsumed())) {
            Log.v("BaseLoLoMoAdapter", "User message is stale or consumed, refreshing");
            this.activity.getServiceManager().refreshCurrentUserMessageArea();
        }
    }
    
    private void showErrorView() {
        this.frag.showErrorView();
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    protected View createDummyView() {
        final View view = new View((Context)this.activity);
        view.setVisibility(8);
        return view;
    }
    
    protected BaseLoLoMoAdapter$RowHolder createHolder(final View view, final LinearLayout linearLayout, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2) {
        return new BaseLoLoMoAdapter$RowHolder((View)linearLayout, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
    }
    
    public int getCount() {
        return this.lomos.size();
    }
    
    protected String getGenreId() {
        return this.lolomoId;
    }
    
    public T getItem(final int n) {
        return this.lomos.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        if (this.lomos.get(n).getType() == LoMoType.DISCOVERY_ROW) {
            return 1;
        }
        return 0;
    }
    
    protected ServiceManager getServiceManager() {
        return this.manager;
    }
    
    protected int getShelfVisibility(final T t, final int n) {
        if (this.isRowAfterBillboardOrCwRow(n, t.getType()) && !BrowseExperience.showKidsExperience()) {
            return 0;
        }
        return 8;
    }
    
    public View getView(final int n, View dummyView, final ViewGroup viewGroup) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.activity)) {
            Log.d("BaseLoLoMoAdapter", "activity is destroyed - can't getView");
            dummyView = this.createDummyView();
        }
        else {
            View inflate;
            if ((inflate = dummyView) == null) {
                inflate = this.activity.getLayoutInflater().inflate(this.getViewLayoutId(), viewGroup, false);
                inflate.setTag((Object)this.createViewsAndHolder(inflate));
            }
            final BasicLoMo item = this.getItem(n);
            if (item == null) {
                Log.w("BaseLoLoMoAdapter", "Trying to show data for null lomo! Position: " + n);
            }
            else {
                this.updateRowViews((BaseLoLoMoAdapter$RowHolder)inflate.getTag(), (T)item, n);
            }
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
    
    protected int getViewLayoutId() {
        return 2130903173;
    }
    
    public int getViewTypeCount() {
        if (Coppola2Utils.isCoppolaDiscovery((Context)this.activity)) {
            return 2;
        }
        return 1;
    }
    
    protected void initLoadingState() {
        ThreadUtils.assertOnMain();
        this.lomos.clear();
        if (this.mUserMessageAreaView != null) {
            this.mUserMessageAreaView.dismiss(false);
            this.mUserMessageAreaView = null;
        }
        this.lomoRequestId = -2147483648L;
        this.lomoRequestPending = true;
        this.hasMoreData = false;
        this.loMoStartIndex = 0;
        this.notifyDataSetChanged();
    }
    
    protected TextView initTitleView(final View view) {
        final TextView textView = (TextView)view.findViewById(2131689893);
        if (Log.isLoggable()) {
            Log.v("BaseLoLoMoAdapter", "Manipulating title padding, view: " + textView);
        }
        if (textView != null) {
            ViewUtils.setPaddingStart((View)textView, LoMoUtils.getLomoFragImageOffsetLeftPx(this.activity));
        }
        return textView;
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
    
    protected void onDataLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
        if (this.activity != null) {
            this.activity.onLoaded(status);
        }
    }
    
    public void onDestroyView() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().destroy();
        }
    }
    
    public final void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        this.refreshData();
        this.refreshUserMessage();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
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
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
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
        if (list.size() > 0 && this.mUserMessageAreaView == null) {
            this.mUserMessageAreaView = this.handleUserMessage();
        }
        this.lomos.addAll((Collection<? extends T>)list);
        this.loMoStartIndex += list.size();
        this.notifyDataSetChanged();
    }
    
    protected void updateRowViews(final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder, final T t, final int n) {
        if (Log.isLoggable()) {
            Log.v("BaseLoLoMoAdapter", "Updating LoMo row content: " + t.getTitle() + ", type: " + t.getType() + ", pos: " + n);
        }
        final TextView title = baseLoLoMoAdapter$RowHolder.title;
        String text;
        if (t.getType() == LoMoType.BILLBOARD) {
            text = this.activity.getString(2131231208);
        }
        else {
            text = t.getTitle();
        }
        title.setText((CharSequence)text);
        if (t.getType() == LoMoType.DISCOVERY_ROW) {
            baseLoLoMoAdapter$RowHolder.title.setVisibility(0);
            baseLoLoMoAdapter$RowHolder.title.setText(2131231005);
            baseLoLoMoAdapter$RowHolder.title.setTypeface(Typeface.create("sans-serif-light", 0));
            baseLoLoMoAdapter$RowHolder.title.setLineSpacing(0.0f, 1.0f);
            ((RelativeLayout$LayoutParams)baseLoLoMoAdapter$RowHolder.title.getLayoutParams()).removeRule(20);
            ((RelativeLayout$LayoutParams)baseLoLoMoAdapter$RowHolder.title.getLayoutParams()).addRule(14, -1);
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131362068);
            ((RelativeLayout$LayoutParams)baseLoLoMoAdapter$RowHolder.title.getLayoutParams()).setMargins(0, dimensionPixelSize * 2, 0, dimensionPixelSize);
        }
        else {
            ((RelativeLayout$LayoutParams)baseLoLoMoAdapter$RowHolder.title.getLayoutParams()).addRule(20, -1);
            ((RelativeLayout$LayoutParams)baseLoLoMoAdapter$RowHolder.title.getLayoutParams()).removeRule(14);
            baseLoLoMoAdapter$RowHolder.title.setVisibility(BrowseExperience.get().getLomoRowTitleVisibility(this.activity, t));
        }
        if (baseLoLoMoAdapter$RowHolder.shelf != null) {
            baseLoLoMoAdapter$RowHolder.shelf.setVisibility(this.getShelfVisibility(t, n));
        }
        baseLoLoMoAdapter$RowHolder.rowContent.refresh(t, n);
        if (BrowseExperience.showKidsExperience()) {
            Api16Util.setBackgroundDrawableCompat(baseLoLoMoAdapter$RowHolder.contentGroup, null);
            baseLoLoMoAdapter$RowHolder.contentGroup.setPadding(0, 0, 0, this.activity.getResources().getDimensionPixelSize(2131362204));
            baseLoLoMoAdapter$RowHolder.title.setTextColor(baseLoLoMoAdapter$RowHolder.defaultTitleColors);
        }
    }
}
