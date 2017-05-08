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
import java.util.Iterator;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.IrisUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import org.json.JSONException;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View$OnClickListener;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class NotificationsFrag$4 extends LoggingManagerCallback
{
    final /* synthetic */ NotificationsFrag this$0;
    final /* synthetic */ ServiceManager val$serviceManager;
    
    NotificationsFrag$4(final NotificationsFrag this$0, final String s, final ServiceManager val$serviceManager) {
        this.this$0 = this$0;
        this.val$serviceManager = val$serviceManager;
        super(s);
    }
    
    @Override
    public void onNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        super.onNotificationsListFetched(list, status);
        if (!this.this$0.checkForNetworkError(status)) {
            this.this$0.mLoadMoreAvailable = (list != null && list.getSocialNotifications() != null && list.getSocialNotifications().size() == this.this$0.getNumNotificationsPerPage());
            this.this$0.mNotifications = list;
            if (!this.this$0.mWereNotificationsFetched) {
                this.val$serviceManager.getBrowse().refreshIrisNotifications(false);
                this.this$0.mWereNotificationsFetched = true;
            }
            this.this$0.refreshNotificationsListStatus();
            if (this.this$0.mAdapter != null) {
                this.this$0.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
