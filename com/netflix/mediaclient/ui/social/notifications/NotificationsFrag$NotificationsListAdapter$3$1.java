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
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import java.util.Iterator;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class NotificationsFrag$NotificationsListAdapter$3$1 extends LoggingManagerCallback
{
    final /* synthetic */ NotificationsFrag$NotificationsListAdapter$3 this$2;
    
    NotificationsFrag$NotificationsListAdapter$3$1(final NotificationsFrag$NotificationsListAdapter$3 this$2, final String s) {
        this.this$2 = this$2;
        super(s);
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        super.onSocialNotificationWasThanked(status);
        if (this.this$2.val$activity != null && !this.this$2.val$activity.isFinishing()) {
            this.this$2.val$thanksButton.setEnabled(true);
            final Activity activity = this.this$2.this$1.this$0.getActivity();
            IClientLogging$CompletionReason clientLogging$CompletionReason;
            if (status.isSucces()) {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.success;
            }
            else {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.failed;
            }
            UserActionLogUtils.reportSayThanksActionEnded((Context)activity, clientLogging$CompletionReason, status.getError());
            if (!this.this$2.this$1.this$0.checkForNetworkError(status)) {
                this.this$2.this$1.notifyDataSetChanged();
            }
        }
    }
}
