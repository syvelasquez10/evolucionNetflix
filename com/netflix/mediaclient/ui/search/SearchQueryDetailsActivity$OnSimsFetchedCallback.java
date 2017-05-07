// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.widget.AbsListView$OnScrollListener;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.content.Context;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SearchQueryDetailsActivity$OnSimsFetchedCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ SearchQueryDetailsActivity this$0;
    
    public SearchQueryDetailsActivity$OnSimsFetchedCallback(final SearchQueryDetailsActivity this$0, final long requestId) {
        this.this$0 = this$0;
        super("SearchQueryDetailsActivity");
        this.requestId = requestId;
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        super.onSimilarVideosFetched(searchVideoListProvider, status);
        if (this.requestId != this.this$0.requestId || AndroidUtils.isActivityFinishedOrDestroyed(this.this$0)) {
            Log.v("SearchQueryDetailsActivity", "Ignoring stale callback");
            return;
        }
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("SearchQueryDetailsActivity", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        final List<SearchVideo> videosList = searchVideoListProvider.getVideosList();
        if (videosList == null || videosList.size() < 1) {
            Log.v("SearchQueryDetailsActivity", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.referenceId = searchVideoListProvider.getVideosListTrackable().getReferenceId();
        this.this$0.adapter.setData(videosList, PlayContext.EMPTY_CONTEXT);
        this.this$0.showContentView();
    }
}
