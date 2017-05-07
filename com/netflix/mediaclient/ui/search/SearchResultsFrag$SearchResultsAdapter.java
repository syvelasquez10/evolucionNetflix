// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import java.util.Locale;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.GridView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.AdapterView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ListAdapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.BaseAdapter;

class SearchResultsFrag$SearchResultsAdapter extends BaseAdapter implements AdapterView$OnItemClickListener, ListAdapter
{
    private final boolean ignoreClicks;
    private int maxCount;
    private int resId;
    private final SearchResultsFrag$SearchCategory searchCategory;
    final /* synthetic */ SearchResultsFrag this$0;
    
    public SearchResultsFrag$SearchResultsAdapter(final SearchResultsFrag this$0, final SearchResultsFrag$SearchCategory searchCategory, final boolean ignoreClicks) {
        this.this$0 = this$0;
        this.searchCategory = searchCategory;
        this.ignoreClicks = ignoreClicks;
        this.setResid();
    }
    
    private void adjustHeight(final SearchResultView searchResultView) {
        int n = 0;
        if (searchResultView.getImage() != null) {
            switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[this.searchCategory.ordinal()]) {
                default: {
                    n = this.this$0.imgHeightVideo;
                    break;
                }
                case 2: {
                    n = this.this$0.imgHeightPeople;
                    break;
                }
            }
        }
        else {
            n = -2;
        }
        searchResultView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n));
    }
    
    private View createView() {
        final SearchResultView searchResultView = new SearchResultView((Context)this.this$0.getActivity(), this.resId);
        this.adjustHeight(searchResultView);
        if (this.ignoreClicks) {
            searchResultView.setIgnoreClicks();
        }
        return (View)searchResultView;
    }
    
    private SearchTrackable getTrackableForPos() {
        switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[this.searchCategory.ordinal()]) {
            default: {
                return null;
            }
            case 3: {
                return this.this$0.results.getVideosListTrackable();
            }
            case 2: {
                return this.this$0.results.getPeopleListTrackable();
            }
            case 1: {
                return this.this$0.results.getSuggestionsListTrackable();
            }
        }
    }
    
    private void setResid() {
        switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[this.searchCategory.ordinal()]) {
            default: {
                this.resId = 2130903187;
            }
            case 2: {
                this.resId = SearchUtils.getSearchViewLayoutPeople();
            }
            case 1: {
                this.resId = SearchUtils.getSearchViewLayoutRelated();
            }
        }
    }
    
    public int getCount() {
        int n = 0;
        if (this.this$0.results != null) {
            switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[this.searchCategory.ordinal()]) {
                case 1: {
                    n = this.this$0.results.getNumResultsSuggestions();
                    break;
                }
                case 3: {
                    n = this.this$0.results.getNumResultsVideos();
                    break;
                }
                case 2: {
                    n = this.this$0.results.getNumResultsPeople();
                    break;
                }
            }
        }
        return Math.min(n, this.maxCount);
    }
    
    public Object getItem(final int n) {
        return SearchResultsFrag.getItem(this.this$0.results, this.searchCategory, n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        if (view == null || !(view instanceof SearchResultView)) {
            view = this.createView();
        }
        final SearchTrackable trackableForPos = this.getTrackableForPos();
        ((SearchResultView)view).update(this.getItem(n), new PlayContextImp(trackableForPos, n), this.this$0.query, trackableForPos.getReferenceId());
        return view;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (this.this$0.progressBar != null) {
            this.this$0.progressBar.setVisibility(0);
        }
        view.performClick();
    }
    
    public void setMaxCount(final int maxCount) {
        this.maxCount = maxCount;
    }
}
