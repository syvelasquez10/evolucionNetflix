// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.util.Triple;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.MathUtils;
import java.util.Map;
import android.content.IntentFilter;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.view.View;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SkidmarkLoLoMoAdapter$FetchLoMoCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ SkidmarkLoLoMoAdapter this$0;
    
    public SkidmarkLoLoMoAdapter$FetchLoMoCallback(final SkidmarkLoLoMoAdapter this$0, final long requestId) {
        this.this$0 = this$0;
        super("SkidmarkLoLoMoAdapter");
        this.requestId = requestId;
    }
    
    private void handleResult(final List<LoMo> list, final Status status) {
        if (this.requestId != this.this$0.lomoRequestId) {
            Log.v("SkidmarkLoLoMoAdapter", "Ignoring stale onLoMosFetched callback");
        }
        else {
            if (status.isError()) {
                Log.w("SkidmarkLoLoMoAdapter", "Invalid status code");
                this.this$0.notifyDataSetChanged();
                return;
            }
            if (list == null || list.size() <= 0) {
                Log.v("SkidmarkLoLoMoAdapter", "No loMos in response");
                this.this$0.notifyDataSetChanged();
                return;
            }
            if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " items");
            }
            this.this$0.lomoData.clear();
            this.this$0.callbackCount = list.size();
            for (final LoMo loMo : list) {
                this.this$0.lomoData.put(loMo, new ArrayList(10));
                Log.v("SkidmarkLoLoMoAdapter", "Fetching videos for lomo: " + loMo.getId() + ", type: " + loMo.getType());
                if (loMo.getType() == LoMoType.CONTINUE_WATCHING) {
                    this.this$0.manager.getBrowse().fetchCWVideos(0, 2, new SkidmarkLoLoMoAdapter$FetchVideosCallback(this.this$0, loMo));
                }
                else {
                    this.this$0.manager.getBrowse().fetchVideos(loMo, 0, 4, new SkidmarkLoLoMoAdapter$FetchVideosCallback(this.this$0, loMo));
                }
                if (loMo.getType() == LoMoType.CHARACTERS) {
                    this.this$0.callbackCount++;
                    this.this$0.manager.getBrowse().fetchVideos(loMo, 5, 9, new SkidmarkLoLoMoAdapter$FetchVideosCallback(this.this$0, loMo));
                }
            }
        }
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        super.onLoMosFetched(list, status);
        this.handleResult(list, status);
    }
}
