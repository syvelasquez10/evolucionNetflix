// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class KidsShowDetailsFrag$2 extends BroadcastReceiver
{
    final /* synthetic */ KidsShowDetailsFrag this$0;
    
    KidsShowDetailsFrag$2(final KidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (!this.this$0.isDestroyed()) {
            final String action = intent.getAction();
            Log.v("KidsShowDetailsFrag", "receiver inovoked, action: " + action);
            if ("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH".equals(action)) {
                return;
            }
        }
    }
}
