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
import android.content.Context;
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

public class SocialNotificationsFrag extends NetflixFrag
{
    private static final String BUNDLE_EXTRA_HAS_LOAD_MORE = "has_load_more_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST = "notifications_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST_TO_BE_READ = "notifications_list_to_be_read";
    private static final String TAG;
    private final ErrorWrapper$Callback errorCallback;
    private LoadingAndErrorWrapper leWrapper;
    private SocialNotificationsFrag$NotificationsListAdapter mAdapter;
    private boolean mIsLoadingData;
    private LayoutInflater mLayoutInflater;
    private boolean mLoadMoreAvailable;
    private boolean mNetworkErrorOccured;
    private SocialNotificationsList mNotifications;
    protected ListView mNotificationsList;
    private final Set<SocialNotificationSummary> mNotificationsToBeRead;
    private ServiceManager mServiceManager;
    private boolean mWasRefreshForTopViewScheduled;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    
    static {
        TAG = SocialNotificationsFrag.class.getSimpleName();
    }
    
    public SocialNotificationsFrag() {
        this.mLoadMoreAvailable = true;
        this.mNotificationsToBeRead = new HashSet<SocialNotificationSummary>();
        this.errorCallback = new SocialNotificationsFrag$1(this);
        this.socialNotificationsListUpdateReceiver = new SocialNotificationsFrag$7(this);
    }
    
    private boolean checkForNetworkError(final Status status) {
        if (this.leWrapper != null) {
            this.leWrapper.hide(false);
        }
        this.mIsLoadingData = false;
        if (status.getStatusCode() == StatusCode.NETWORK_ERROR) {
            this.mNetworkErrorOccured = true;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            if (this.leWrapper != null) {
                this.leWrapper.showErrorView(2131493363, true, false);
            }
            return true;
        }
        return this.mNetworkErrorOccured = false;
    }
    
    private void fetchNotificationsList(final boolean b) {
        if (this.mServiceManager != null) {
            if (b && this.leWrapper != null) {
                this.leWrapper.showLoadingView(false);
            }
            this.mIsLoadingData = true;
            this.mServiceManager.getBrowse().fetchSocialNotificationsList(0, new SocialNotificationsFrag$5(this));
        }
    }
    
    private void handleManagerReady() {
        if (this.mNotifications == null) {
            this.fetchNotificationsList(true);
        }
        else {
            if (this.leWrapper != null) {
                this.leWrapper.hide(false);
            }
            this.mIsLoadingData = false;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }
    
    private void loadMoreNotifications() {
        if (this.mServiceManager != null && this.mNotifications != null && this.mNotifications.getSocialNotifications() != null) {
            this.mIsLoadingData = true;
            this.mServiceManager.getBrowse().fetchSocialNotificationsList(this.mNotifications.getSocialNotifications().size(), new SocialNotificationsFrag$6(this));
        }
    }
    
    private void markNotificationsAsRead() {
        if (this.mServiceManager != null && this.mNotificationsToBeRead.size() > 0) {
            this.mServiceManager.getBrowse().markSocialNotificationsAsRead(new ArrayList<SocialNotificationSummary>(this.mNotificationsToBeRead));
            for (final SocialNotificationSummary socialNotificationSummary : this.mNotificationsToBeRead) {
                SocialLoggingUtils.reportRecommendImplicitFeedbackReadEvent((Context)this.getActivity(), socialNotificationSummary.getId(), socialNotificationSummary.getVideo().getId(), this.mNotifications.getSocialNotificationsListSummary().getBaseTrackId());
            }
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
                    this.mServiceManager.updateMyListState(item.getVideo().getId(), item.getInQueueValue());
                }
                else if (Log.isLoggable(SocialNotificationsFrag.TAG, 6)) {
                    Log.e(SocialNotificationsFrag.TAG, "refreshNotificationMyListButtons() got null details for position: " + i);
                }
            }
        }
    }
    
    private void registerReceivers() {
        ((NetflixActivity)this.getActivity()).registerReceiverLocallyWithAutoUnregister(this.socialNotificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED");
    }
    
    private void updateAvailableButtons(final SocialNotification socialNotification, final SocialNotificationSummary socialNotificationSummary, final SocialNotificationViewHolder socialNotificationViewHolder, final View view) {
        if (this.mNotifications == null || this.mNotifications.getSocialNotificationsListSummary() == null) {
            Log.e(SocialNotificationsFrag.TAG, "Got null notifications list data");
        }
        else {
            final NetflixActivity netflixActivity = (NetflixActivity)this.getActivity();
            final String id = socialNotificationSummary.getVideo().getId();
            final VideoType type = socialNotificationSummary.getVideo().getType();
            final SocialNotificationsListSummary socialNotificationsListSummary = this.mNotifications.getSocialNotificationsListSummary();
            final String requestId = socialNotificationsListSummary.getRequestId();
            final TextView addToMyListButton = socialNotification.getAddToMyListButton(socialNotificationViewHolder);
            if (addToMyListButton != null && this.mServiceManager != null && netflixActivity != null) {
                this.mServiceManager.registerAddToMyListListener(id, this.mServiceManager.createAddToMyListWrapper(netflixActivity, addToMyListButton, id, type, socialNotificationsListSummary.getBaseTrackId(), true));
                this.mServiceManager.updateMyListState(id, socialNotificationSummary.getInQueueValue());
            }
            final View playMovieButton = socialNotification.getPlayMovieButton(socialNotificationViewHolder);
            if (playMovieButton != null) {
                playMovieButton.setOnClickListener((View$OnClickListener)new SocialNotificationsFrag$2(this, netflixActivity, id, new PlayContextImp(requestId, socialNotificationsListSummary.getPlayerTrackId(), 0, 0), type));
            }
            view.setOnClickListener((View$OnClickListener)new SocialNotificationsFrag$3(this, requestId, socialNotificationsListSummary, netflixActivity, type, id, socialNotificationSummary));
            final View sayThanksButton = socialNotification.getSayThanksButton(socialNotificationViewHolder);
            if (sayThanksButton != null) {
                sayThanksButton.setOnClickListener((View$OnClickListener)new SocialNotificationsFrag$4(this, sayThanksButton, socialNotificationSummary, netflixActivity));
            }
        }
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
            SocialNotificationsUtils.castArrayToSet(bundle.getParcelableArray("notifications_list_to_be_read"), this.mNotificationsToBeRead);
        }
    }
    
    public View onCreateView(final LayoutInflater mLayoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(SocialNotificationsFrag.TAG, "Creating new frag view...");
        this.mLayoutInflater = mLayoutInflater;
        final View inflate = this.mLayoutInflater.inflate(2130903193, viewGroup, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        (this.mNotificationsList = (ListView)inflate.findViewById(2131165668)).setItemsCanFocus(true);
        this.mAdapter = new SocialNotificationsFrag$NotificationsListAdapter(this, null);
        this.mNotificationsList.setAdapter((ListAdapter)this.mAdapter);
        this.leWrapper.showLoadingView(false);
        this.mIsLoadingData = true;
        if (this.getActivity() != null && ((NetflixActivity)this.getActivity()).getServiceManager() != null) {
            this.mServiceManager = ((NetflixActivity)this.getActivity()).getServiceManager();
            this.handleManagerReady();
        }
        return inflate;
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        super.onManagerReady(mServiceManager, status);
        this.mServiceManager = mServiceManager;
        this.handleManagerReady();
    }
    
    public void onResume() {
        super.onResume();
        this.refreshNotificationMyListButtons();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mNotifications != null) {
            bundle.putBoolean("has_load_more_list", this.mLoadMoreAvailable);
            bundle.putParcelable("notifications_list", this.mNotifications.getParcelable());
            bundle.putParcelableArray("notifications_list_to_be_read", (Parcelable[])this.mNotificationsToBeRead.toArray((Parcelable[])new SocialNotificationSummary[this.mNotificationsToBeRead.size()]));
        }
    }
    
    public void onStop() {
        if (!this.getActivity().isChangingConfigurations()) {
            this.markNotificationsAsRead();
        }
        super.onStop();
    }
}
