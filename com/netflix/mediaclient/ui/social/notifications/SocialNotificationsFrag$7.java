// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.os.Bundle;
import android.app.Activity;
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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.HashSet;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.Set;
import android.widget.ListView;
import com.netflix.model.leafs.social.SocialNotificationsList;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class SocialNotificationsFrag$7 extends BroadcastReceiver
{
    final /* synthetic */ SocialNotificationsFrag this$0;
    
    SocialNotificationsFrag$7(final SocialNotificationsFrag this$0) {
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
