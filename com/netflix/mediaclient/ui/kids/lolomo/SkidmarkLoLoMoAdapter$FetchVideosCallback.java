// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.CommonStatus;
import android.widget.AbsListView;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.Triple;
import com.netflix.mediaclient.util.MathUtils;
import java.util.Map;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Iterator;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.view.View;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SkidmarkLoLoMoAdapter$FetchVideosCallback extends LoggingManagerCallback
{
    private final LoMo lomo;
    final /* synthetic */ SkidmarkLoLoMoAdapter this$0;
    
    public SkidmarkLoLoMoAdapter$FetchVideosCallback(final SkidmarkLoLoMoAdapter this$0, final LoMo lomo) {
        this.this$0 = this$0;
        super("SkidmarkLoLoMoAdapter");
        this.lomo = lomo;
    }
    
    private void handleResponse(final List<? extends Video> list, final Status status) {
        this.this$0.callbackCount--;
        if (status.isError()) {
            Log.w("SkidmarkLoLoMoAdapter", "Invalid status code");
            this.this$0.notifyDataSetChanged();
        }
        else {
            if (list == null || list.size() <= 0) {
                Log.v("SkidmarkLoLoMoAdapter", "No videos in response");
                this.this$0.notifyDataSetChanged();
                return;
            }
            if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " items, callback count: " + this.this$0.callbackCount);
            }
            final List<Video> list2 = this.this$0.lomoData.get(this.lomo);
            if (list2.size() == 0) {
                list2.add(this.this$0.spacerPlaceholder);
                if (this.lomo.getType() == LoMoType.CHARACTERS) {
                    list2.add(this.this$0.spacerPlaceholder);
                }
            }
            list2.addAll(list);
            if (this.this$0.shouldAddMoreButton(this.lomo, list2)) {
                list2.add(this.this$0.moreButtonPlaceholder);
            }
            if (this.this$0.callbackCount <= 0) {
                this.this$0.isLoading = false;
                this.this$0.onDataLoaded(status);
                this.this$0.notifyDataSetChanged();
            }
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
