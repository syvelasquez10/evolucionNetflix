// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import android.widget.BaseAdapter;

class NotificationsFrag$NotificationsListAdapter extends BaseAdapter
{
    final /* synthetic */ NotificationsFrag this$0;
    
    private NotificationsFrag$NotificationsListAdapter(final NotificationsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void updateAvailableButtons(final SocialNotification socialNotification, final SocialNotificationSummary socialNotificationSummary, final NotificationViewHolder notificationViewHolder, final View view) {
        if (this.this$0.mNotifications == null || this.this$0.mNotifications.getSocialNotificationsListSummary() == null) {
            Log.e(NotificationsFrag.TAG, "Got null notifications list data");
        }
        else {
            final NetflixActivity netflixActivity = (NetflixActivity)this.this$0.getActivity();
            final String videoId = socialNotificationSummary.getVideoId();
            final VideoType videoType = socialNotificationSummary.getVideoType();
            final SocialNotificationsListSummary socialNotificationsListSummary = this.this$0.mNotifications.getSocialNotificationsListSummary();
            final String requestId = socialNotificationsListSummary.getRequestId();
            final TextView addToMyListButton = socialNotification.getAddToMyListButton(notificationViewHolder);
            if (addToMyListButton != null && this.this$0.mServiceManager != null && netflixActivity != null) {
                this.this$0.mServiceManager.registerAddToMyListListener(videoId, this.this$0.mServiceManager.createAddToMyListWrapper(netflixActivity, addToMyListButton, videoId, videoType, socialNotificationsListSummary.getBaseTrackId(), true));
                this.this$0.mServiceManager.updateMyListState(videoId, socialNotificationSummary.getInQueueValue());
            }
            final View playMovieButton = socialNotification.getPlayMovieButton(notificationViewHolder);
            if (this.this$0.shouldShowPlayButtonFromNotifications() && playMovieButton != null) {
                playMovieButton.setOnClickListener((View$OnClickListener)new NotificationsFrag$NotificationsListAdapter$1(this, netflixActivity, videoId, new PlayContextImp(requestId, socialNotificationsListSummary.getPlayerTrackId(), 0, 0), videoType));
            }
            view.setOnClickListener((View$OnClickListener)new NotificationsFrag$NotificationsListAdapter$2(this, socialNotificationSummary, requestId, socialNotificationsListSummary, netflixActivity, videoType, videoId));
            final View sayThanksButton = socialNotification.getSayThanksButton(notificationViewHolder);
            if (sayThanksButton != null) {
                sayThanksButton.setOnClickListener((View$OnClickListener)new NotificationsFrag$NotificationsListAdapter$3(this, sayThanksButton, socialNotificationSummary, netflixActivity));
            }
        }
    }
    
    public int getCount() {
        if (!this.this$0.mWereNotificationsFetched || this.this$0.mNetworkErrorOccured) {
            return 0;
        }
        return this.this$0.computeRowCount();
    }
    
    public SocialNotificationSummary getItem(final int n) {
        if (!this.this$0.mAreNotificationsPresent || n > this.this$0.mNotifications.getSocialNotifications().size() - 1) {
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
        final SocialNotification notificationByType = NotificationsStaticFactory.getNotificationByType(type);
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.getActivity().getLayoutInflater().inflate(this.this$0.getRowLayoutResourceId(), viewGroup, false);
            inflate.setTag((Object)SocialNotification.getViewHolder(inflate, type));
        }
        final NotificationViewHolder notificationViewHolder = (NotificationViewHolder)inflate.getTag();
        if (!this.this$0.areMoreNotificationsAvailable() && !this.this$0.mAreNotificationsPresent) {
            SocialNotification.showSingleLineText(notificationViewHolder, 2131493374);
            inflate.setOnClickListener((View$OnClickListener)null);
        }
        else {
            if (this.this$0.areMoreNotificationsAvailable() && n == this.getCount() - 1) {
                SocialNotification.showSingleLineText(notificationViewHolder, 2131493345);
                inflate.setOnClickListener((View$OnClickListener)null);
                this.this$0.loadMoreNotifications();
                return inflate;
            }
            if (notificationByType == null) {
                if (Log.isLoggable()) {
                    Log.e(NotificationsFrag.TAG, "Got null notification for type: " + item.getType());
                }
                SocialNotification.showSingleLineText(notificationViewHolder, 2131493372);
                inflate.setOnClickListener((View$OnClickListener)null);
                return inflate;
            }
            if (this.this$0.getActivity() != null && NetflixActivity.getImageLoader((Context)this.this$0.getActivity()) != null) {
                notificationByType.initView(notificationViewHolder, item, (Context)this.this$0.getActivity());
                this.updateAvailableButtons(notificationByType, item, notificationViewHolder, inflate);
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
