// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsListSummary;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import java.util.Iterator;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import java.util.List;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.netflix.mediaclient.StatusCode;
import android.view.View;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import java.util.ArrayList;
import android.widget.ListView;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class SocialNotificationsFrag extends NetflixFrag
{
    private static final String BUNDLE_EXTRA_HAS_LOAD_MORE = "has_load_more_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST = "notifications_list";
    private static final String BUNDLE_EXTRA_NOTIFICATIONS_LIST_TO_BE_READ = "notifications_list_to_be_read";
    private static final String TAG;
    private final ErrorWrapper.Callback errorCallback;
    private LoadingAndErrorWrapper leWrapper;
    private NotificationsListAdapter mAdapter;
    private boolean mIsLoadingData;
    private LayoutInflater mLayoutInflater;
    private boolean mLoadMoreAvailable;
    private boolean mNetworkErrorOccured;
    private SocialNotificationsList mNotifications;
    protected ListView mNotificationsList;
    private ArrayList<SocialNotificationSummary> mNotificationsToBeRead;
    private ServiceManager mServiceManager;
    private boolean mWasRefreshForTopViewScheduled;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    
    static {
        TAG = SocialNotificationsFrag.class.getSimpleName();
    }
    
    public SocialNotificationsFrag() {
        this.mLoadMoreAvailable = true;
        this.errorCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                SocialNotificationsFrag.this.fetchNotificationsList(true);
            }
        };
        this.socialNotificationsListUpdateReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (SocialNotificationsFrag.this.mNotificationsList != null && SocialNotificationsFrag.this.mNotificationsList.getFirstVisiblePosition() == 0) {
                    SocialNotificationsFrag.this.fetchNotificationsList(false);
                    return;
                }
                SocialNotificationsFrag.this.mWasRefreshForTopViewScheduled = true;
            }
        };
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
                this.leWrapper.showErrorView(2131493408, true, false);
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
            this.mServiceManager.getBrowse().fetchSocialNotificationsList(0, new SimpleManagerCallback() {
                @Override
                public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
                    if (!SocialNotificationsFrag.this.checkForNetworkError(status)) {
                        SocialNotificationsFrag.this.mLoadMoreAvailable = (list != null && list.getSocialNotifications() != null && list.getSocialNotifications().size() == 20);
                        SocialNotificationsFrag.this.mNotifications = list;
                        if (SocialNotificationsFrag.this.mAdapter != null) {
                            SocialNotificationsFrag.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
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
            this.mServiceManager.getBrowse().fetchSocialNotificationsList(this.mNotifications.getSocialNotifications().size(), new SimpleManagerCallback() {
                @Override
                public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
                    if (!SocialNotificationsFrag.this.checkForNetworkError(status)) {
                        SocialNotificationsFrag.this.mLoadMoreAvailable = (list != null && list.getSocialNotifications() != null && list.getSocialNotifications().size() == 20);
                        if (list != null && list.getSocialNotifications() != null) {
                            SocialNotificationsFrag.this.mNotifications.getSocialNotifications().addAll(list.getSocialNotifications());
                        }
                        if (SocialNotificationsFrag.this.mAdapter != null) {
                            SocialNotificationsFrag.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
    
    private void markNotificationsAsRead() {
        if (this.mServiceManager != null && this.mNotificationsToBeRead.size() > 0) {
            this.mServiceManager.getBrowse().markSocialNotificationsAsRead(this.mNotificationsToBeRead);
            for (final SocialNotificationSummary socialNotificationSummary : this.mNotificationsToBeRead) {
                SocialLoggingUtils.reportRecommendImplicitFeedbackReadEvent((Context)this.getActivity(), socialNotificationSummary.getId(), socialNotificationSummary.getVideoSummary().getId(), this.mNotifications.getSocialNotificationsListSummary().getBaseTrackId());
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
                    this.mServiceManager.updateMyListState(item.getVideoSummary().getId(), item.getInQueue().inQueue);
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
            final String id = socialNotificationSummary.getVideoSummary().getId();
            final SocialNotificationsListSummary socialNotificationsListSummary = this.mNotifications.getSocialNotificationsListSummary();
            final String requestId = socialNotificationsListSummary.getRequestId();
            final TextView addToMyListButton = socialNotification.getAddToMyListButton(socialNotificationViewHolder);
            if (addToMyListButton != null && this.mServiceManager != null && netflixActivity != null && socialNotificationSummary.getInQueue() != null) {
                this.mServiceManager.registerAddToMyListListener(id, this.mServiceManager.createAddToMyListWrapper(netflixActivity, addToMyListButton, id, socialNotificationsListSummary.getBaseTrackId(), true));
                this.mServiceManager.updateMyListState(id, socialNotificationSummary.getInQueue().inQueue);
            }
            final View playMovieButton = socialNotification.getPlayMovieButton(socialNotificationViewHolder);
            final VideoType type = socialNotificationSummary.getVideoSummary().getType();
            if (playMovieButton != null) {
                playMovieButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    final /* synthetic */ PlayContext val$playContext = new PlayContextImp(requestId, socialNotificationsListSummary.getPlayerTrackId(), 0, 0);
                    
                    public void onClick(final View view) {
                        SocialNotificationsFrag.this.playVideo(netflixActivity, id, this.val$playContext, type);
                    }
                });
            }
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    DetailsActivity.show(netflixActivity, type, id, socialNotificationSummary.getVideoSummary().getTitle(), new PlayContextImp(requestId, socialNotificationsListSummary.getMDPTrackId(), 0, 0));
                }
            });
            final View sayThanksButton = socialNotification.getSayThanksButton(socialNotificationViewHolder);
            if (sayThanksButton != null) {
                sayThanksButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        sayThanksButton.setEnabled(false);
                        UserActionLogUtils.reportSayThanksActionStarted((Context)SocialNotificationsFrag.this.getActivity(), null, IClientLogging.ModalView.socialNotifications);
                        SocialNotificationsFrag.this.mServiceManager.getBrowse().sendThanksToSocialNotification(socialNotificationSummary, new SimpleManagerCallback() {
                            @Override
                            public void onSocialNotificationWasThanked(final SocialNotificationSummary socialNotificationSummary, final Status status) {
                                if (netflixActivity != null && !netflixActivity.isFinishing()) {
                                    sayThanksButton.setEnabled(true);
                                    final Activity activity = SocialNotificationsFrag.this.getActivity();
                                    IClientLogging.CompletionReason completionReason;
                                    if (status.isSucces()) {
                                        completionReason = IClientLogging.CompletionReason.success;
                                    }
                                    else {
                                        completionReason = IClientLogging.CompletionReason.failed;
                                    }
                                    UserActionLogUtils.reportSayThanksActionEnded((Context)activity, completionReason, status.getError());
                                    if (!SocialNotificationsFrag.this.checkForNetworkError(status) && SocialNotificationsFrag.this.mAdapter != null) {
                                        SocialNotificationsFrag.this.mAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
    }
    
    public boolean isLoadingData() {
        return this.mIsLoadingData;
    }
    
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
            this.mNotificationsToBeRead = (ArrayList<SocialNotificationSummary>)bundle.getParcelableArrayList("notifications_list_to_be_read");
            return;
        }
        this.mNotificationsToBeRead = new ArrayList<SocialNotificationSummary>();
    }
    
    public View onCreateView(final LayoutInflater mLayoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(SocialNotificationsFrag.TAG, "Creating new frag view...");
        this.mLayoutInflater = mLayoutInflater;
        final View inflate = this.mLayoutInflater.inflate(2130903185, viewGroup, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        (this.mNotificationsList = (ListView)inflate.findViewById(2131165657)).setItemsCanFocus(true);
        this.mAdapter = new NotificationsListAdapter();
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
            bundle.putParcelableArrayList("notifications_list_to_be_read", (ArrayList)this.mNotificationsToBeRead);
        }
    }
    
    public void onStop() {
        if (!this.getActivity().isChangingConfigurations()) {
            this.markNotificationsAsRead();
        }
        super.onStop();
    }
    
    private class NotificationsListAdapter extends BaseAdapter
    {
        public int getCount() {
            if (SocialNotificationsFrag.this.mNetworkErrorOccured) {
                return 0;
            }
            if (SocialNotificationsFrag.this.mNotifications == null || SocialNotificationsFrag.this.mNotifications.getSocialNotifications() == null || SocialNotificationsFrag.this.mNotifications.getSocialNotifications().size() == 0) {
                return 1;
            }
            if (SocialNotificationsFrag.this.mLoadMoreAvailable) {
                return SocialNotificationsFrag.this.mNotifications.getSocialNotifications().size() + 1;
            }
            return SocialNotificationsFrag.this.mNotifications.getSocialNotifications().size();
        }
        
        public SocialNotificationSummary getItem(final int n) {
            if (SocialNotificationsFrag.this.mNotifications == null || SocialNotificationsFrag.this.mNotifications.getSocialNotifications() == null || n > SocialNotificationsFrag.this.mNotifications.getSocialNotifications().size() - 1) {
                return null;
            }
            return SocialNotificationsFrag.this.mNotifications.getSocialNotifications().get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            final SocialNotificationSummary item = this.getItem(n);
            Enum<SocialNotificationSummary.NotificationTypes> type;
            if (item == null) {
                type = null;
            }
            else {
                type = item.getType();
            }
            final SocialNotification notificationByType = SocialNotificationsStaticFactory.getNotificationByType((SocialNotificationSummary.NotificationTypes)type);
            View inflate = view;
            if (view == null) {
                inflate = SocialNotificationsFrag.this.mLayoutInflater.inflate(SocialNotification.getLayoutResourceId(), viewGroup, false);
                inflate.setTag((Object)SocialNotification.getViewHolder(inflate));
            }
            final SocialNotificationViewHolder socialNotificationViewHolder = (SocialNotificationViewHolder)inflate.getTag();
            if (!SocialNotificationsFrag.this.mLoadMoreAvailable && (SocialNotificationsFrag.this.mNotifications == null || SocialNotificationsFrag.this.mNotifications.getSocialNotifications() == null || SocialNotificationsFrag.this.mNotifications.getSocialNotifications().size() == 0)) {
                SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493410);
            }
            else {
                if (SocialNotificationsFrag.this.mLoadMoreAvailable && n == this.getCount() - 1) {
                    SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493386);
                    SocialNotificationsFrag.this.loadMoreNotifications();
                    return inflate;
                }
                if (notificationByType == null) {
                    if (Log.isLoggable(SocialNotificationsFrag.TAG, 6)) {
                        Log.e(SocialNotificationsFrag.TAG, "Got null notification for type: " + item.getType());
                    }
                    SocialNotification.showSingleLineText(socialNotificationViewHolder, 2131493409);
                    return inflate;
                }
                if (SocialNotificationsFrag.this.getActivity() != null && NetflixActivity.getImageLoader((Context)SocialNotificationsFrag.this.getActivity()) != null) {
                    notificationByType.initView(inflate, socialNotificationViewHolder, item, (Context)SocialNotificationsFrag.this.getActivity());
                    SocialNotificationsFrag.this.updateAvailableButtons(notificationByType, item, socialNotificationViewHolder, inflate);
                    if (!item.getWasRead()) {
                        SocialNotificationsFrag.this.mNotificationsToBeRead.add(item);
                    }
                    if (n == 0 && SocialNotificationsFrag.this.mWasRefreshForTopViewScheduled) {
                        SocialNotificationsFrag.this.fetchNotificationsList(false);
                        SocialNotificationsFrag.this.mWasRefreshForTopViewScheduled = false;
                        return inflate;
                    }
                }
            }
            return inflate;
        }
    }
}
