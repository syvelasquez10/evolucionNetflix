// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.os.Parcelable;
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
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.common.PlayContext;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.model.leafs.social.IrisNotificationSummary$NotificationTypes;
import android.content.Context;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.ui.iris.notifications.type.BaseNotification;
import android.widget.BaseAdapter;

class NotificationsFrag$NotificationsListAdapter extends BaseAdapter
{
    final /* synthetic */ NotificationsFrag this$0;
    
    private NotificationsFrag$NotificationsListAdapter(final NotificationsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void updateAvailableButtons(final BaseNotification baseNotification, final IrisNotificationSummary irisNotificationSummary, final NotificationViewHolder notificationViewHolder, final View view, final int n) {
        if (this.this$0.mNotifications == null || this.this$0.mNotifications.getSocialNotificationsListSummary() == null) {
            Log.e(NotificationsFrag.TAG, "Got null notifications list data");
            return;
        }
        final NetflixActivity netflixActivity = this.this$0.getNetflixActivity();
        final String videoId = irisNotificationSummary.getVideoId();
        final VideoType videoType = irisNotificationSummary.getVideoType();
        final IrisNotificationsListSummary socialNotificationsListSummary = this.this$0.mNotifications.getSocialNotificationsListSummary();
        final TextView addToMyListButton = baseNotification.getAddToMyListButton(notificationViewHolder);
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (addToMyListButton != null && serviceManager != null) {
            serviceManager.registerAddToMyListListener(videoId, serviceManager.createAddToMyListWrapper(netflixActivity, addToMyListButton, videoId, videoType, socialNotificationsListSummary.getBaseTrackId(), true));
            serviceManager.updateMyListState(videoId, irisNotificationSummary.getInQueueValue());
        }
        final View playMovieButton = baseNotification.getPlayMovieButton(notificationViewHolder);
        if (this.this$0.shouldShowPlayButtonFromNotifications() && playMovieButton != null) {
            playMovieButton.setOnClickListener(this.this$0.getPlaybackListener(irisNotificationSummary, n));
        }
        notificationViewHolder.getNSAArtImage().setOnClickListener(this.this$0.getClickListener(irisNotificationSummary.getImageTarget(), irisNotificationSummary, n));
        view.setOnClickListener(this.this$0.getClickListener(irisNotificationSummary.getTextTarget(), irisNotificationSummary, n));
    }
    
    public int getCount() {
        if (!this.this$0.mWereNotificationsFetched || this.this$0.mNetworkErrorOccured) {
            return 0;
        }
        return this.this$0.computeRowCount();
    }
    
    public IrisNotificationSummary getItem(final int n) {
        if (!this.this$0.mAreNotificationsPresent || n > this.this$0.mNotifications.getSocialNotifications().size() - 1) {
            return null;
        }
        return this.this$0.mNotifications.getSocialNotifications().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final IrisNotificationSummary item = this.getItem(n);
        IrisNotificationSummary$NotificationTypes type;
        if (item == null) {
            type = null;
        }
        else {
            type = item.getType();
        }
        final BaseNotification notificationByType = NotificationsStaticFactory.getNotificationByType(type);
        if (inflate == null) {
            inflate = this.this$0.getActivity().getLayoutInflater().inflate(this.this$0.getRowLayoutResourceId(), viewGroup, false);
            inflate.setTag((Object)BaseNotification.getViewHolder(inflate, type));
        }
        final NotificationViewHolder notificationViewHolder = (NotificationViewHolder)inflate.getTag();
        if (!this.this$0.areMoreNotificationsAvailable() && !this.this$0.mAreNotificationsPresent) {
            BaseNotification.showSingleLineText(notificationViewHolder, 2131296524);
            inflate.setOnClickListener((View$OnClickListener)null);
        }
        else {
            if (this.this$0.areMoreNotificationsAvailable() && n == this.getCount() - 1) {
                BaseNotification.showSingleLineText(notificationViewHolder, 2131296522);
                inflate.setOnClickListener((View$OnClickListener)null);
                this.this$0.loadMoreNotifications();
                return inflate;
            }
            if (notificationByType == null) {
                if (Log.isLoggable()) {
                    Log.e(NotificationsFrag.TAG, "Got null notification for type: " + item.getType());
                }
                BaseNotification.showSingleLineText(notificationViewHolder, 2131296857);
                inflate.setOnClickListener((View$OnClickListener)null);
                return inflate;
            }
            if (this.this$0.getActivity() != null && NetflixActivity.getImageLoader((Context)this.this$0.getActivity()) != null) {
                notificationByType.initView(notificationViewHolder, item, (Context)this.this$0.getActivity());
                this.updateAvailableButtons(notificationByType, item, notificationViewHolder, inflate, n);
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
