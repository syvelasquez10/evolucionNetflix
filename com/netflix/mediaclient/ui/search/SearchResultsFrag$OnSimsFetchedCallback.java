// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
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
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SearchResultsFrag$OnSimsFetchedCallback extends LoggingManagerCallback
{
    SearchResultsFrag$SearchCategory searchCategory;
    final /* synthetic */ SearchResultsFrag this$0;
    
    public SearchResultsFrag$OnSimsFetchedCallback(final SearchResultsFrag this$0, final long n, final String s, final SearchResultsFrag$SearchCategory searchCategory) {
        this.this$0 = this$0;
        super("SearchResultsFrag");
        this.searchCategory = searchCategory;
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        super.onSimilarVideosFetched(searchVideoListProvider, status);
        if (status.isError()) {
            Log.w("SearchResultsFrag", "Invalid status code");
            ((SearchActivity)this.this$0.getActivity()).showError();
            return;
        }
        final List<SearchVideo> videosList = searchVideoListProvider.getVideosList();
        if (videosList == null || videosList.size() < 1) {
            Log.v("SearchResultsFrag", "No details in response");
            ((SearchActivity)this.this$0.getActivity()).showError();
            return;
        }
        if (this.this$0.simAdapter != null) {
            this.this$0.secondarySearch = this.searchCategory;
            this.this$0.trackableTitles = searchVideoListProvider.getVideosListTrackable();
            this.this$0.simAdapter.setData(videosList, this.this$0.trackableTitles);
        }
        if (this.this$0.progressBar != null) {
            this.this$0.progressBar.setVisibility(8);
        }
        this.this$0.fireImpressionEvents();
    }
}
