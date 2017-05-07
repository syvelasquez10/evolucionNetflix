// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import java.util.Collections;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.Collection;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KidsLomoDetailAdapter$FetchVideosCallback extends LoggingManagerCallback
{
    private final int numItems;
    private final long requestId;
    final /* synthetic */ KidsLomoDetailAdapter this$0;
    
    public KidsLomoDetailAdapter$FetchVideosCallback(final KidsLomoDetailAdapter this$0, final long requestId, final int numItems) {
        this.this$0 = this$0;
        super("KidsLomoDetailAdapter");
        this.requestId = requestId;
        this.numItems = numItems;
    }
    
    private void handleResponse(final List<? extends Video> list, final Status status) {
        this.this$0.hasMoreData = true;
        if (this.requestId != this.this$0.requestId) {
            Log.v("KidsLomoDetailAdapter", "Ignoring stale callback");
            return;
        }
        this.this$0.isLoading = false;
        try {
            if (status.isError()) {
                Log.w("KidsLomoDetailAdapter", "Invalid status code");
                this.this$0.hasMoreData = false;
                this.this$0.notifyDataSetChanged();
                return;
            }
            if (list == null || list.size() <= 0) {
                Log.v("KidsLomoDetailAdapter", "No videos in response");
                this.this$0.hasMoreData = false;
                this.this$0.notifyDataSetChanged();
                return;
            }
            if (list.size() < this.numItems) {
                this.this$0.hasMoreData = false;
            }
            if (Log.isLoggable("KidsLomoDetailAdapter", 2)) {
                Log.v("KidsLomoDetailAdapter", "Got " + list.size() + " items, expected " + this.numItems + ", hasMoreData: " + this.this$0.hasMoreData);
            }
            this.this$0.videoData.addAll(list);
            KidsLomoDetailAdapter.access$312(this.this$0, list.size());
            this.this$0.notifyDataSetChanged();
        }
        finally {
            this.this$0.onDataLoaded(status);
        }
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        super.onCWVideosFetched(list, status);
        this.handleResponse(list, status);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        super.onVideosFetched(list, status);
        this.handleResponse(list, status);
    }
}
