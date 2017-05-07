// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import java.util.Collection;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView;
import android.widget.ListView;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.TextView;
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
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BaseLoLoMoAdapter$LoMoCallbacks extends LoggingManagerCallback
{
    private final int numItems;
    private final long requestId;
    final /* synthetic */ BaseLoLoMoAdapter this$0;
    
    public BaseLoLoMoAdapter$LoMoCallbacks(final BaseLoLoMoAdapter this$0, final long requestId, final int numItems) {
        this.this$0 = this$0;
        super("BaseLoLoMoAdapter");
        this.requestId = requestId;
        this.numItems = numItems;
    }
    
    private void handleResult(final List list, final Status status) {
        this.this$0.hasMoreData = true;
        this.this$0.lomoRequestPending = false;
        if (this.requestId != this.this$0.lomoRequestId) {
            Log.v("BaseLoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
            return;
        }
        this.this$0.isLoading = false;
        this.this$0.onDataLoaded(status);
        if (status.isError()) {
            Log.w("BaseLoLoMoAdapter", "Invalid status code");
            this.this$0.hasMoreData = false;
            this.this$0.notifyDataSetChanged();
            return;
        }
        if (list == null || list.size() <= 0) {
            Log.v("BaseLoLoMoAdapter", "No loMos in response");
            this.this$0.hasMoreData = false;
            this.this$0.notifyDataSetChanged();
            return;
        }
        if (list.size() < this.numItems) {
            this.this$0.hasMoreData = false;
        }
        if (Log.isLoggable()) {
            Log.v("BaseLoLoMoAdapter", "Got " + list.size() + " items, expected " + this.numItems + ", hasMoreData: " + this.this$0.hasMoreData);
        }
        this.this$0.updateLoMoData(list);
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        super.onGenresFetched(list, status);
        this.handleResult(list, status);
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        super.onLoMosFetched(list, status);
        this.handleResult(list, status);
    }
}
