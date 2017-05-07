// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.Collection;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class NotificationsFrag$3 extends LoggingManagerCallback
{
    final /* synthetic */ NotificationsFrag this$0;
    
    NotificationsFrag$3(final NotificationsFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        super.onNotificationsListFetched(list, status);
        if (!this.this$0.checkForNetworkError(status)) {
            this.this$0.mLoadMoreAvailable = (list != null && list.getSocialNotifications() != null && list.getSocialNotifications().size() == this.this$0.getNumNotificationsPerPage());
            if (list != null && list.getSocialNotifications() != null) {
                this.this$0.mNotifications.getSocialNotifications().addAll(list.getSocialNotifications());
                this.this$0.refreshNotificationsListStatus();
            }
            if (this.this$0.mAdapter != null) {
                this.this$0.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
