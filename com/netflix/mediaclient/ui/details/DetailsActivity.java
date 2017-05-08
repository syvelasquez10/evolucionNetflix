// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.HashMap;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import java.util.Map;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public abstract class DetailsActivity extends FragmentHostActivity implements ErrorWrapper$Callback, ManagerStatusListener, NetflixRatingBar$RatingBarDataProvider
{
    private static final String ACTION_FINISH_DETAILS_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_DETAILS_ACTIVITIES";
    private static final String NOTIFICATION_BEACON_SENT = "notification_beacon_sent";
    private static final String TAG = "DetailsActivity";
    public static final boolean USE_DUMMY_DATA = false;
    protected String episodeId;
    private DetailsActivity$Action mAction;
    private String mActionToken;
    private boolean mNotificationOpenedReportAlreadySent;
    protected PlayContext playContext;
    private final BroadcastReceiver reloadReceiver;
    private ServiceManager serviceMan;
    private boolean shareMenuCreated;
    private boolean startDPTTISession;
    protected String videoId;
    
    public DetailsActivity() {
        this.reloadReceiver = new DetailsActivity$2(this);
    }
    
    public static void finishAllDetailsActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_DETAILS_ACTIVITIES"));
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
        UserActionLogUtils.reportAddToQueueActionStarted((Context)this, (UserActionLogging$CommandName)null, this.getUiScreen());
        this.serviceMan.getBrowse().addToQueue(this.videoId, this.getVideoType(), this.getTrackId(), BrowseExperience.shouldLoadKubrickLeavesInDetails(), this.mActionToken, new DetailsActivity$MyListCallback(this, "DetailsActivity"));
    }
    
    private void handleRemoveFromMyList() {
        UserActionLogUtils.reportRemoveFromQueueActionStarted((Context)this, (UserActionLogging$CommandName)null, this.getUiScreen());
        this.serviceMan.getBrowse().removeFromQueue(this.videoId, this.getVideoType(), this.mActionToken, new DetailsActivity$MyListCallback(this, "DetailsActivity"));
    }
    
    private void registerReceivers() {
        this.registerReceiverLocallyWithAutoUnregister(this.reloadReceiver, "com.netflix.mediaclient.intent.action.DETAIL_PAGE_REFRESH");
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_DETAILS_ACTIVITIES");
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
    
    @Override
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    @Override
    public boolean canShowSnackBar() {
        return true;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this;
    }
    
    public void endDPTTISession(final Map<String, String> map) {
        this.startDPTTISession = false;
        PerformanceProfiler.getInstance().endSession(Sessions.DP_TTI, this.populateDPTTISession(map));
        PerformanceProfiler.getInstance().flushApmEvents(this.getApmSafely());
    }
    
    protected void fillVideoAndEpisodeIds() {
        this.videoId = this.getIntent().getStringExtra("extra_video_id");
        this.episodeId = this.getIntent().getStringExtra("extra_episode_id");
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
        if (this.playContext != null) {
            Log.d("DetailsActivity", "TrackId found in PlayContextImpl");
            return this.playContext.getTrackId();
        }
        Log.d("DetailsActivity", "TrackId not found!");
        return -1;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.movieDetails;
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
    protected void onCreate(final Bundle bundle) {
        if (!this.getIntent().hasExtra("extra_video_type")) {
            throw new IllegalStateException("Start intent must provide extra value: extra_video_type");
        }
        if (bundle != null) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.mNotificationOpenedReportAlreadySent = bundle.getBoolean("notification_beacon_sent");
        }
        else {
            this.startDPTTISession();
        }
        this.fillVideoAndEpisodeIds();
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
        MdxMenu.addSelectPlayTarget((NetflixActivity)this, menu, false);
        IrisUtils.addShareIcon(this.serviceMan, menu, (Context)this);
        this.shareMenuCreated = true;
        DetailsMenu.addItems(this, menu, false);
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.startDPTTISession) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("reason", IClientLogging$CompletionReason.canceled.name());
            this.endDPTTISession(hashMap);
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceMan, final Status status) {
        Log.v("DetailsActivity", "ServiceManager ready");
        this.serviceMan = serviceMan;
        if (this.shareMenuCreated) {
            this.invalidateOptionsMenu();
        }
        Coppola1Utils.injectPlayerFragmentIfNeeded((Activity)this, this.videoId, this.getVideoType(), this.getPlayContext(), serviceMan, status);
        Coppola1Utils.forceToPortraitIfNeeded((Activity)this);
        ((ManagerStatusListener)this.getPrimaryFrag()).onManagerReady(serviceMan, status);
        final Fragment secondaryFrag = this.getSecondaryFrag();
        if (secondaryFrag != null) {
            ((ManagerStatusListener)secondaryFrag).onManagerReady(serviceMan, status);
        }
        if (!this.mNotificationOpenedReportAlreadySent) {
            this.mNotificationOpenedReportAlreadySent = true;
            NflxProtocolUtils.reportUserOpenedNotification(this.serviceMan, this.getIntent());
        }
        this.handleAction();
        this.registerLoadingStatusCallback();
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
        return IrisUtils.tryHandleMenuItemClick(menuItem, (Context)this) || super.onOptionsItemSelected(menuItem);
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
    
    public Map<String, String> populateDPTTISession(final Map<String, String> map) {
        Map<String, String> map2 = map;
        if (this.getVideoType() != null) {
            if ((map2 = map) == null) {
                map2 = new HashMap<String, String>();
            }
            map2.put("videoType", this.getVideoType().name());
        }
        return map2;
    }
    
    public void registerLoadingStatusCallback() {
        this.setLoadingStatusCallback(new DetailsActivity$1(this));
    }
    
    @Override
    protected boolean requiresDownloadButtonListener() {
        return true;
    }
    
    protected void setAction(final DetailsActivity$Action mAction, final String mActionToken) {
        if (Log.isLoggable()) {
            Log.d("DetailsActivity", "Action " + mAction + ", msg token: " + mActionToken);
        }
        this.mAction = mAction;
        this.mActionToken = mActionToken;
    }
    
    public void setPlayContext(final PlayContext playContext) {
        this.playContext = playContext;
    }
    
    public void setVideoAndEpisodeIds(final String videoId, final String episodeId) {
        this.videoId = videoId;
        this.episodeId = episodeId;
    }
    
    protected void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    @Override
    public boolean shouldApplyPaddingToSlidingPanel() {
        return false;
    }
    
    public void startDPTTISession() {
        this.startDPTTISession = true;
        PerformanceProfiler.getInstance().startSession(Sessions.DP_TTI);
    }
}
