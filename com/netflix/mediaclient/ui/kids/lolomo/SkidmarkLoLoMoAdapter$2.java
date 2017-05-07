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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class SkidmarkLoLoMoAdapter$2 extends BroadcastReceiver
{
    final /* synthetic */ SkidmarkLoLoMoAdapter this$0;
    
    SkidmarkLoLoMoAdapter$2(final SkidmarkLoLoMoAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.w("SkidmarkLoLoMoAdapter", "Received null intent");
        }
        else {
            final String action = intent.getAction();
            if (Log.isLoggable("SkidmarkLoLoMoAdapter", 2)) {
                Log.v("SkidmarkLoLoMoAdapter", "browseReceiver inovoked with Action: " + action);
            }
            if ("com.netflix.mediaclient.intent.action.BA_CW_REFRESH".equals(action)) {
                this.this$0.refreshCwData();
            }
        }
    }
}
