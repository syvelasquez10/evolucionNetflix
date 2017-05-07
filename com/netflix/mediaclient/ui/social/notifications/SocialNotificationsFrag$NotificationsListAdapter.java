// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import java.util.Iterator;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import android.widget.ListView;
import com.netflix.model.leafs.social.SocialNotificationsList;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.widget.BaseAdapter;

class SocialNotificationsFrag$NotificationsListAdapter extends BaseAdapter
{
    final /* synthetic */ SocialNotificationsFrag this$0;
    
    private SocialNotificationsFrag$NotificationsListAdapter(final SocialNotificationsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public int getCount() {
        if (this.this$0.mNetworkErrorOccured) {
            return 0;
        }
        if (this.this$0.mNotifications == null || this.this$0.mNotifications.getSocialNotifications() == null || this.this$0.mNotifications.getSocialNotifications().size() == 0) {
            return 1;
        }
        if (this.this$0.mLoadMoreAvailable) {
            return this.this$0.mNotifications.getSocialNotifications().size() + 1;
        }
        return this.this$0.mNotifications.getSocialNotifications().size();
    }
    
    public SocialNotificationSummary getItem(final int n) {
        if (this.this$0.mNotifications == null || this.this$0.mNotifications.getSocialNotifications() == null || n > this.this$0.mNotifications.getSocialNotifications().size() - 1) {
            return null;
        }
        return this.this$0.mNotifications.getSocialNotifications().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final SocialNotificationSummary item = this.getItem(n);
        SocialNotificationSummary$NotificationTypes type;
        if (item == null) {
            type = null;
        }
        else {
            type = item.getType();
        }
        final SocialNotification notificationByType = SocialNotificationsStaticFactory.getNotificationByType(type);
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.mLayoutInflater.inflate(SocialNotification.getLayoutResourceId(), viewGroup, false);
            inflate.setTag((Object)SocialNotification.getViewHolder(inflate, type));
        }
        final SocialNotificationViewHolder socialNotificationViewHolder = (SocialNotificationViewHolder)inflate.getTag();
        if (!this.this$0.mLoadMoreAvailable && (this.this$0.mNotifications == null || this.this$0.mNotifications.getSocialNotifications() == null || this.this$0.mNotifications.getSocialNotifications().size() == 0)) {
            SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493370);
            inflate.setOnClickListener((View$OnClickListener)null);
        }
        else {
            if (this.this$0.mLoadMoreAvailable && n == this.getCount() - 1) {
                SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493340);
                inflate.setOnClickListener((View$OnClickListener)null);
                this.this$0.loadMoreNotifications();
                return inflate;
            }
            if (notificationByType == null) {
                if (Log.isLoggable(SocialNotificationsFrag.TAG, 6)) {
                    Log.e(SocialNotificationsFrag.TAG, "Got null notification for type: " + item.getType());
                }
                SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493367);
                inflate.setOnClickListener((View$OnClickListener)null);
                return inflate;
            }
            if (this.this$0.getActivity() != null && NetflixActivity.getImageLoader((Context)this.this$0.getActivity()) != null) {
                notificationByType.initView(inflate, socialNotificationViewHolder, item, (Context)this.this$0.getActivity());
                this.this$0.updateAvailableButtons(notificationByType, item, socialNotificationViewHolder, inflate);
                if (!item.getWasRead()) {
                    this.this$0.mNotificationsToBeRead.add(item);
                }
                if (n == 0 && this.this$0.mWasRefreshForTopViewScheduled) {
                    this.this$0.fetchNotificationsList(false);
                    this.this$0.mWasRefreshForTopViewScheduled = false;
                    return inflate;
                }
            }
        }
        return inflate;
    }
}
