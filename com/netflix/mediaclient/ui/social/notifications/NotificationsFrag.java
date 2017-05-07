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
import com.netflix.mediaclient.android.app.Status;
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
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class NotificationsFrag extends NetflixFrag
{
    private static final String BUNDLE_EXTRA_HAS_LOAD_MORE = "has_load_more_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST = "notifications_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST_TO_BE_READ = "notifications_list_to_be_read";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_WERE_FETCHED = "were_notifications_fetched";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_WERE_FOUND = "are_there_noitifcations";
    private static final String TAG;
    private final ErrorWrapper$Callback errorCallback;
    private NotificationsFrag$NotificationsListAdapter mAdapter;
    private boolean mAreNotificationsPresent;
    private boolean mAreReceiversRegistered;
    private boolean mAreViewsCreated;
    private boolean mIsLoadingData;
    private NotificationsFrag$NotificationsListStatusListener mListener;
    private boolean mLoadMoreAvailable;
    private boolean mNetworkErrorOccured;
    private SocialNotificationsList mNotifications;
    protected StaticListView mNotificationsList;
    private final Set<SocialNotificationSummary> mNotificationsToBeRead;
    private ServiceManager mServiceManager;
    private boolean mWasRefreshForTopViewScheduled;
    private boolean mWereNotificationsFetched;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    
    static {
        TAG = NotificationsFrag.class.getSimpleName();
    }
    
    public NotificationsFrag() {
        this.mLoadMoreAvailable = true;
        this.mNotificationsToBeRead = new HashSet<SocialNotificationSummary>();
        this.errorCallback = new NotificationsFrag$1(this);
        this.socialNotificationsListUpdateReceiver = new NotificationsFrag$4(this);
    }
    
    private boolean areMoreNotificationsAvailable() {
        return this.canLoadMultiplePages() && this.mLoadMoreAvailable;
    }
    
    private boolean checkForNetworkError(final Status status) {
        this.mIsLoadingData = false;
        if (status.getStatusCode() == StatusCode.NETWORK_ERROR) {
            this.mNetworkErrorOccured = true;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            return true;
        }
        return this.mNetworkErrorOccured = false;
    }
    
    private void completeInitIfPossible() {
        if (!this.mAreViewsCreated) {
            Log.v(NotificationsFrag.TAG, "Can't complete init - views not created");
        }
        else {
            if (this.mServiceManager == null) {
                Log.v(NotificationsFrag.TAG, "Can't complete init - manager not ready");
                return;
            }
            if (this.mNotifications == null) {
                this.fetchNotificationsList(true);
                return;
            }
            this.mIsLoadingData = false;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }
    
    private void fetchNotificationsList(final boolean b) {
        if (this.mServiceManager != null) {
            this.mIsLoadingData = true;
            this.mServiceManager.getBrowse().fetchNotificationsList(0, this.getNumNotificationsPerPage() - 1, new NotificationsFrag$2(this, NotificationsFrag.TAG));
        }
    }
    
    private void loadMoreNotifications() {
        if (this.mServiceManager != null && this.mNotifications != null && this.mNotifications.getSocialNotifications() != null) {
            this.mIsLoadingData = true;
            this.mServiceManager.getBrowse().fetchNotificationsList(this.mNotifications.getSocialNotifications().size(), this.mNotifications.getSocialNotifications().size() + this.getNumNotificationsPerPage() - 1, new NotificationsFrag$3(this, NotificationsFrag.TAG));
        }
    }
    
    private void playVideo(final NetflixActivity netflixActivity, final String s, final PlayContext playContext, final VideoType videoType) {
        this.startActivity(PlayerActivity.createColdStartIntent(netflixActivity, s, videoType, playContext));
    }
    
    private void refreshNotificationMyListButtons() {
        if (this.mServiceManager != null) {
            for (int i = this.mNotificationsList.getFirstVisiblePosition(); i <= this.mNotificationsList.getLastVisiblePosition(); ++i) {
                if (this.mAdapter != null && this.mAdapter.getItem(i) != null) {
                    final SocialNotificationSummary item = this.mAdapter.getItem(i);
                    this.mServiceManager.updateMyListState(item.getVideoId(), item.getInQueueValue());
                }
                else if (Log.isLoggable()) {
                    Log.e(NotificationsFrag.TAG, "refreshNotificationMyListButtons() got null details for position: " + i);
                }
            }
        }
    }
    
    private void refreshNotificationsListStatus() {
        this.mAreNotificationsPresent = (this.mNotifications != null && this.mNotifications.getSocialNotifications() != null && this.mNotifications.getSocialNotifications().size() > 0);
        if (this.mListener != null) {
            this.mListener.onNotificationsListUpdated(this.mAreNotificationsPresent);
        }
    }
    
    private void registerReceivers() {
        if (this.isAdded() && !this.mAreReceiversRegistered) {
            this.mAreReceiversRegistered = true;
            LocalBroadcastManager.getInstance((Context)this.getActivity()).registerReceiver(this.socialNotificationsListUpdateReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED"));
        }
    }
    
    private void unregisterReceivers() {
        if (this.mAreReceiversRegistered) {
            LocalBroadcastManager.getInstance((Context)this.getActivity()).unregisterReceiver(this.socialNotificationsListUpdateReceiver);
        }
    }
    
    public boolean areNotificationsPresent() {
        return this.mAreNotificationsPresent;
    }
    
    protected boolean canLoadMultiplePages() {
        return true;
    }
    
    protected int computeRowCount() {
        if (!this.mAreNotificationsPresent) {
            return 0;
        }
        if (this.areMoreNotificationsAvailable()) {
            return this.mNotifications.getSocialNotifications().size() + 1;
        }
        return this.mNotifications.getSocialNotifications().size();
    }
    
    protected int getNumNotificationsPerPage() {
        return 20;
    }
    
    protected int getRowLayoutResourceId() {
        return 2130903198;
    }
    
    protected boolean isListViewStatic() {
        return false;
    }
    
    public boolean isLoadingData() {
        return this.mIsLoadingData;
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.registerReceivers();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("notifications_list")) {
            this.mLoadMoreAvailable = bundle.getBoolean("has_load_more_list");
            this.mNotifications = (SocialNotificationsList)bundle.getParcelable("notifications_list");
            SocialUtils.castArrayToSet(bundle.getParcelableArray("notifications_list_to_be_read"), this.mNotificationsToBeRead);
            this.mWereNotificationsFetched = bundle.getBoolean("were_notifications_fetched");
            this.mAreNotificationsPresent = bundle.getBoolean("are_there_noitifcations");
            this.refreshNotificationsListStatus();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(NotificationsFrag.TAG, "Creating new frag view...");
        this.mAreViewsCreated = true;
        final View inflate = layoutInflater.inflate(2130903197, viewGroup, false);
        (this.mNotificationsList = (StaticListView)inflate.findViewById(2131427822)).setItemsCanFocus(true);
        this.mNotificationsList.setAsStatic(this.isListViewStatic());
        this.mAdapter = new NotificationsFrag$NotificationsListAdapter(this);
        this.mNotificationsList.setAdapter((ListAdapter)this.mAdapter);
        this.mIsLoadingData = true;
        this.completeInitIfPossible();
        return inflate;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        this.unregisterReceivers();
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        super.onManagerReady(mServiceManager, status);
        this.mServiceManager = mServiceManager;
        this.completeInitIfPossible();
    }
    
    public void onResume() {
        super.onResume();
        this.refreshNotificationMyListButtons();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mNotifications != null) {
            bundle.putBoolean("has_load_more_list", this.areMoreNotificationsAvailable());
            bundle.putParcelable("notifications_list", this.mNotifications.getParcelable());
            bundle.putParcelableArray("notifications_list_to_be_read", (Parcelable[])this.mNotificationsToBeRead.toArray((Parcelable[])new SocialNotificationSummary[this.mNotificationsToBeRead.size()]));
            bundle.putBoolean("were_notifications_fetched", this.mWereNotificationsFetched);
        }
        bundle.putBoolean("are_there_noitifcations", this.mAreNotificationsPresent);
    }
    
    public void refresh() {
        this.fetchNotificationsList(true);
    }
    
    public void setNotificationsListStatusListener(final NotificationsFrag$NotificationsListStatusListener mListener) {
        this.mListener = mListener;
    }
    
    protected boolean shouldShowPlayButtonFromNotifications() {
        return true;
    }
}
