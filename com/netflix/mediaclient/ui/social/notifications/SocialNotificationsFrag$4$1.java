// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.widget.TextView;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import java.util.Iterator;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.StatusCode;
import android.view.View;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.Set;
import android.widget.ListView;
import com.netflix.model.leafs.social.SocialNotificationsList;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class SocialNotificationsFrag$4$1 extends SimpleManagerCallback
{
    final /* synthetic */ SocialNotificationsFrag$4 this$1;
    
    SocialNotificationsFrag$4$1(final SocialNotificationsFrag$4 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        if (this.this$1.val$activity != null && !this.this$1.val$activity.isFinishing()) {
            this.this$1.val$thanksButton.setEnabled(true);
            final Activity activity = this.this$1.this$0.getActivity();
            IClientLogging$CompletionReason clientLogging$CompletionReason;
            if (status.isSucces()) {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.success;
            }
            else {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.failed;
            }
            UserActionLogUtils.reportSayThanksActionEnded((Context)activity, clientLogging$CompletionReason, status.getError());
            if (!this.this$1.this$0.checkForNetworkError(status) && this.this$1.this$0.mAdapter != null) {
                this.this$1.this$0.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
