// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.SocialUtils;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.SocialUtils$NotificationsListStatus;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.ui.common.VideoDetailsProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public abstract class DetailsActivity extends FragmentHostActivity implements ErrorWrapper$Callback, ManagerStatusListener, VideoDetailsProvider
{
    private static final String NOTIFICATION_BEACON_SENT = "notification_beacon_sent";
    private static final String TAG = "DetailsActivity";
    public static final boolean USE_DUMMY_DATA = false;
    private DetailsMenu detailsMenu;
    private String episodeId;
    private DetailsActivity$Action mAction;
    private String mActionToken;
    protected MessageData mMessageData;
    private boolean mNotificationOpenedReportAlreadySent;
    private SocialUtils$NotificationsListStatus notificationsListStatus;
    protected PlayContext playContext;
    private final BroadcastReceiver reloadReceiver;
    private ServiceManager serviceMan;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    protected String videoId;
    
    public DetailsActivity() {
        this.notificationsListStatus = SocialUtils$NotificationsListStatus.NO_MESSAGES;
        this.socialNotificationsListUpdateReceiver = new DetailsActivity$1(this);
        this.reloadReceiver = new DetailsActivity$2(this);
    }
    
    private void handleAction() {
        if (this.getAction() == null) {
            Log.d("DetailsActivity", "No action task specified");
            return;
        }
        if (DetailsActivity$Action.AddToMyList.equals(this.getAction())) {
            Log.d("DetailsActivity", "Action add to my list started");
            this.handleAddToMyList();
        }
        else if (DetailsActivity$Action.RemoveFromMyList.equals(this.getAction())) {
            Log.d("DetailsActivity", "Action remove from my list started");
            this.handleRemoveFromMyList();
        }
        else if (Log.isLoggable()) {
            Log.w("DetailsActivity", "Not supported action " + this.getAction());
        }
        this.mAction = null;
        this.mActionToken = null;
        this.setIntent((Intent)null);
    }
    
    private void handleAddToMyList() {
        if (Log.isLoggable()) {
            Log.d("DetailsActivity", "handleAddToMyList:: msg token " + this.mActionToken);
        }
        UserActionLogUtils.reportAddToQueueActionStarted((Context)this, null, this.getUiScreen());
        this.serviceMan.getBrowse().addToQueue(this.videoId, this.getVideoType(), this.getTrackId(), BrowseExperience.shouldLoadKubrickLeaves(), this.mActionToken, new DetailsActivity$MyListCallback(this, "DetailsActivity"));
    }
    
    private void handleRemoveFromMyList() {
        UserActionLogUtils.reportRemoveFromQueueActionStarted((Context)this, null, this.getUiScreen());
        this.serviceMan.getBrowse().removeFromQueue(this.videoId, this.getVideoType(), this.mActionToken, new DetailsActivity$MyListCallback(this, "DetailsActivity"));
    }
    
    private void registerReceivers() {
        this.registerReceiverLocallyWithAutoUnregister(this.socialNotificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED");
        this.registerReceiverLocallyWithAutoUnregister(this.reloadReceiver, "com.netflix.mediaclient.intent.action.DETAIL_PAGE_REFRESH");
    }
    
    private void reloadData() {
        this.sendReloadRequest(this.getPrimaryFrag());
        this.sendReloadRequest(this.getSecondaryFrag());
    }
    
    private void sendReloadRequest(final Fragment fragment) {
        if (fragment == null || !(fragment instanceof DetailsActivity$Reloader)) {
            return;
        }
        Log.v("DetailsActivity", "Found frag to execute reload request...");
        ((DetailsActivity$Reloader)fragment).reload();
    }
    
    private void sendRetryRequest(final Fragment fragment) {
        if (fragment == null || !(fragment instanceof ErrorWrapper$Callback)) {
            return;
        }
        Log.v("DetailsActivity", "Found frag to execute retry request...");
        ((ErrorWrapper$Callback)fragment).onRetryRequested();
    }
    
    private void updateSocialNotificationsState() {
        if (this.serviceMan != null && SocialUtils.isNotificationsFeatureSupported(this.serviceMan)) {
            this.serviceMan.getBrowse().refreshSocialNotifications(false);
        }
    }
    
    @Override
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this;
    }
    
    public DetailsActivity$Action getAction() {
        return this.mAction;
    }
    
    public String getActionToken() {
        return this.mActionToken;
    }
    
    @Override
    public DataContext getDataContext() {
        return new DataContext(this.playContext, this.videoId);
    }
    
    public String getEpisodeId() {
        return this.episodeId;
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public int getTrackId() {
        if (this.playContext instanceof PlayContext) {
            Log.d("DetailsActivity", "TrackId found in PlayContextImpl");
            return ((PlayContextImp)this.playContext).getTrackId();
        }
        Log.d("DetailsActivity", "TrackId not found!");
        return -1;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.movieDetails;
    }
    
    @Override
    public VideoDetails getVideoDetails() {
        return null;
    }
    
    public VideoDetailsViewGroup getVideoDetailsViewGroup() {
        final Fragment[] array = { this.getPrimaryFrag(), this.getSecondaryFrag() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final Fragment fragment = array[i];
            if (fragment != null) {
                if (!(fragment instanceof VideoDetailsViewGroup$VideoDetailsViewGroupProvider)) {
                    throw new DetailsActivity$VideoDetailsViewGroupProviderException("Fragment does not implement VideoDetailsViewGroupProvider");
                }
                final VideoDetailsViewGroup videoDetailsViewGroup = ((VideoDetailsViewGroup$VideoDetailsViewGroupProvider)fragment).getVideoDetailsViewGroup();
                if (ViewUtils.isVisible((View)videoDetailsViewGroup)) {
                    return videoDetailsViewGroup;
                }
            }
        }
        throw new DetailsActivity$VideoDetailsViewGroupProviderException("No visible VideoDetailsViewGroup found");
    }
    
    @Override
    public String getVideoId() {
        return this.videoId;
    }
    
    @Override
    public void onActionExecuted() {
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        if (!this.getIntent().hasExtra("extra_video_type")) {
            throw new IllegalStateException("Start intent must provide extra value: extra_video_type");
        }
        if (bundle != null) {
            this.mNotificationOpenedReportAlreadySent = bundle.getBoolean("notification_beacon_sent");
        }
        this.videoId = this.getIntent().getStringExtra("extra_video_id");
        this.episodeId = this.getIntent().getStringExtra("extra_episode_id");
        this.mAction = (DetailsActivity$Action)this.getIntent().getSerializableExtra("extra_action");
        this.mActionToken = this.getIntent().getStringExtra("extra_action_token");
        final PlayContextImp playContext = (PlayContextImp)this.getIntent().getParcelableExtra("extra_playcontext");
        this.setPlayContext(playContext);
        if (Log.isLoggable()) {
            Log.v("DetailsActivity", "TRACK_ID: " + playContext.getTrackId());
        }
        super.onCreate(bundle);
        this.registerReceivers();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        SocialUtils.addShareIconIfNeeded(this, menu);
        MdxMenu.addSelectPlayTarget(this, menu);
        this.detailsMenu = new DetailsMenu(this, menu, this.videoId);
        SocialUtils.addNotificationsIconIfNeeded(this, this.notificationsListStatus, menu);
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceMan, final Status status) {
        Log.v("DetailsActivity", "ServiceManager ready");
        this.serviceMan = serviceMan;
        if (this.detailsMenu != null) {
            this.invalidateOptionsMenu();
        }
        ((ManagerStatusListener)this.getPrimaryFrag()).onManagerReady(serviceMan, status);
        final Fragment secondaryFrag = this.getSecondaryFrag();
        if (secondaryFrag != null) {
            ((ManagerStatusListener)secondaryFrag).onManagerReady(serviceMan, status);
        }
        this.updateSocialNotificationsState();
        if (!this.mNotificationOpenedReportAlreadySent) {
            this.mNotificationOpenedReportAlreadySent = true;
            NflxProtocolUtils.reportUserOpenedNotification(this.serviceMan, this.getIntent());
        }
        this.handleAction();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.w("DetailsActivity", "ServiceManager unavailable");
        ((ManagerStatusListener)this.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
        final Fragment secondaryFrag = this.getSecondaryFrag();
        if (secondaryFrag != null) {
            ((ManagerStatusListener)secondaryFrag).onManagerUnavailable(serviceManager, status);
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return SocialUtils.tryHandleMenuItemClick(menuItem, (Context)this) || super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    public void onRetryRequested() {
        this.sendRetryRequest(this.getPrimaryFrag());
        this.sendRetryRequest(this.getSecondaryFrag());
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("notification_beacon_sent", this.mNotificationOpenedReportAlreadySent);
        super.onSaveInstanceState(bundle);
    }
    
    protected void setAction(final DetailsActivity$Action mAction, final String mActionToken) {
        if (Log.isLoggable()) {
            Log.d("DetailsActivity", "Action " + mAction + ", msg token: " + mActionToken);
        }
        this.mAction = mAction;
        this.mActionToken = mActionToken;
    }
    
    protected void setPlayContext(final PlayContext playContext) {
        this.playContext = playContext;
    }
    
    protected void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    @Override
    public boolean shouldApplyPaddingToSlidingPanel() {
        return false;
    }
    
    public void updateMenus(final VideoDetails videoDetails) {
        if (this.detailsMenu != null) {
            this.detailsMenu.updateShareItem(this.serviceMan, videoDetails);
        }
    }
}
