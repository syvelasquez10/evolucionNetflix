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
import com.netflix.mediaclient.util.Triple;
import android.widget.TextView;
import android.view.ViewGroup;
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
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SkidmarkLoLoMoAdapter$RefreshCwCallback extends LoggingManagerCallback
{
    final /* synthetic */ SkidmarkLoLoMoAdapter this$0;
    
    public SkidmarkLoLoMoAdapter$RefreshCwCallback(final SkidmarkLoLoMoAdapter this$0) {
        this.this$0 = this$0;
        super("SkidmarkLoLoMoAdapter");
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        super.onCWVideosFetched(list, status);
        if (status.isError()) {
            Log.w("SkidmarkLoLoMoAdapter", "Invalid status code for CW refresh");
            return;
        }
        if (list == null) {
            Log.d("SkidmarkLoLoMoAdapter", "CW videos list is null");
            return;
        }
        final LoMo access$1200 = this.this$0.getLomoByType(LoMoType.CONTINUE_WATCHING);
        if (access$1200 == null) {
            Log.d("SkidmarkLoLoMoAdapter", "CW lomo is now null - aborting refresh operation");
            return;
        }
        if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
            Log.v("SkidmarkLoLoMoAdapter", "Got " + list.size() + " CW videos - adding to existing lomo data");
        }
        final List<Video> list2 = this.this$0.lomoData.get(access$1200);
        list2.clear();
        list2.add(this.this$0.spacerPlaceholder);
        list2.addAll(list);
        if (this.this$0.shouldAddMoreButton(access$1200, list2)) {
            list2.add(this.this$0.moreButtonPlaceholder);
        }
        this.this$0.notifyDataSetChanged();
        this.this$0.onDataLoaded(status);
    }
}
