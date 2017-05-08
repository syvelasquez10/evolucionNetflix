// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.util.IrisUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import org.json.JSONException;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View$OnClickListener;
import java.util.HashSet;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NotificationsFrag$6 extends BroadcastReceiver
{
    final /* synthetic */ NotificationsFrag this$0;
    
    NotificationsFrag$6(final NotificationsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (this.this$0.mNotificationsList != null && this.this$0.mNotificationsList.getFirstVisiblePosition() == 0) {
            this.this$0.fetchNotificationsList(false);
            return;
        }
        this.this$0.mWasRefreshForTopViewScheduled = true;
    }
}
