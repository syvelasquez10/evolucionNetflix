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
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.util.Triple;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.MathUtils;
import java.util.Map;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.view.View;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SkidmarkLoLoMoAdapter$1 extends LoggingManagerCallback
{
    final /* synthetic */ SkidmarkLoLoMoAdapter this$0;
    final /* synthetic */ long val$requestIdClone;
    
    SkidmarkLoLoMoAdapter$1(final SkidmarkLoLoMoAdapter this$0, final String s, final long val$requestIdClone) {
        this.this$0 = this$0;
        this.val$requestIdClone = val$requestIdClone;
        super(s);
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        super.onLoLoMoPrefetched(status);
        if (!status.isSucces()) {
            Log.w("SkidmarkLoLoMoAdapter", "Prefetch failed");
            this.this$0.showErrorView();
            return;
        }
        if (this.val$requestIdClone != this.this$0.prefetchRequestId) {
            Log.d("SkidmarkLoLoMoAdapter", "Ignoring stale prefetch request id");
            return;
        }
        this.this$0.handlePrefetchComplete();
    }
}
