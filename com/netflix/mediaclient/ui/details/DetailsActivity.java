// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.widget.Toast;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.ui.kids.details.KidsDetailsActivity;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.io.Serializable;
import android.os.Parcelable;
import android.app.Activity;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.MenuItem;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.ui.common.VideoDetailsProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public abstract class DetailsActivity extends FragmentHostActivity implements ErrorWrapper.Callback, ManagerStatusListener, VideoDetailsProvider
{
    public static final String EXTRA_ACTION = "extra_action";
    public static final String EXTRA_ACTION_TOKEN = "extra_action_token";
    public static final String EXTRA_EPISODE_ID = "extra_episode_id";
    public static final String EXTRA_PLAY_CONTEXT = "extra_playcontext";
    public static final String EXTRA_VIDEO_ID = "extra_video_id";
    public static final String EXTRA_VIDEO_TITLE = "extra_video_title";
    public static final String EXTRA_VIDEO_TYPE = "extra_video_type";
    public static final String INTENT_MDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS";
    public static final String INTENT_SDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS";
    private static final String TAG = "DetailsActivity";
    static final boolean USE_DUMMY_DATA = false;
    private DetailsMenu detailsMenu;
    private String episodeId;
    private Action mAction;
    private String mActionToken;
    protected MessageData mMessageData;
    private PlayContext mPlayContext;
    private MenuItem notificationsMenuItem;
    private ServiceManager serviceMan;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    private String videoId;
    
    public DetailsActivity() {
        this.socialNotificationsListUpdateReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                SocialNotificationsUtils.handleNotificationsUpdateReceiver(intent, DetailsActivity.this.notificationsMenuItem, "DetailsActivity");
            }
        };
    }
    
    public static Intent getEpisodeDetailsIntent(final Activity activity, final String s, final String s2, final PlayContext playContext) {
        return getEpisodeDetailsIntent(activity, s, s2, playContext, null, null);
    }
    
    public static Intent getEpisodeDetailsIntent(final Activity activity, final String s, final String s2, final PlayContext playContext, final Action action, final String s3) {
        final Intent putExtra = new Intent((Context)activity, (Class)ShowDetailsActivity.class).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext);
        if (action != null) {
            putExtra.putExtra("extra_action", (Serializable)action);
        }
        if (s3 != null) {
            putExtra.putExtra("extra_action_token", s3);
        }
        return putExtra;
    }
    
    public static Intent getIntent(final Context context, final Class<?> clazz, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final Action action, final String s3) {
        final Intent intent = new Intent(context, (Class)clazz);
        intent.putExtra("extra_video_id", s);
        intent.putExtra("extra_video_title", s2);
        intent.putExtra("extra_video_type", (Serializable)videoType);
        intent.putExtra("extra_playcontext", (Parcelable)playContext);
        if (action != null) {
            intent.putExtra("extra_action", (Serializable)action);
        }
        if (s3 != null) {
            intent.putExtra("extra_action_token", s3);
        }
        return intent;
    }
    
    public static Intent getIntentForBroadcastReceiver(final VideoType videoType, final String s, final String s2, final PlayContext playContext, final String s3, final MessageData messageData) {
        String s4;
        if (videoType == VideoType.MOVIE) {
            s4 = "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS";
        }
        else {
            s4 = "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS";
        }
        final Intent intent = new Intent(s4);
        intent.putExtra("extra_video_id", s);
        intent.putExtra("extra_video_title", s2);
        intent.putExtra("extra_video_type", (Serializable)videoType);
        intent.putExtra("extra_playcontext", (Parcelable)playContext);
        if (s3 != null) {
            intent.putExtra("extra_action_token", s3);
        }
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    private void handleAction() {
        if (this.getAction() == null) {
            Log.d("DetailsActivity", "Action not required.");
            return;
        }
        if (Action.AddToMyList.equals(this.getAction())) {
            Log.d("DetailsActivity", "Action add to my list started");
            this.handleAddToMyList();
        }
        else if (Action.RemoveFromMyList.equals(this.getAction())) {
            Log.d("DetailsActivity", "Action remove from my list started");
            this.handleRemoveFromMyList();
        }
        else if (Log.isLoggable("DetailsActivity", 5)) {
            Log.w("DetailsActivity", "Not supported action " + this.getAction());
        }
        this.mAction = null;
        this.mActionToken = null;
        this.setIntent((Intent)null);
    }
    
    private void handleAddToMyList() {
        if (Log.isLoggable("DetailsActivity", 3)) {
            Log.d("DetailsActivity", "handleAddToMyList:: msg token " + this.mActionToken);
        }
        UserActionLogUtils.reportAddToQueueActionStarted((Context)this, null, this.getUiScreen());
        this.serviceMan.getBrowse().addToQueue(this.videoId, this.getTrackId(), this.mActionToken, new MyListCallback("DetailsActivity"));
    }
    
    private void handleRemoveFromMyList() {
        UserActionLogUtils.reportRemoveFromQueueActionStarted((Context)this, null, this.getUiScreen());
        this.serviceMan.getBrowse().removeFromQueue(this.videoId, this.mActionToken, new MyListCallback("DetailsActivity"));
    }
    
    private void registerReceivers() {
        this.registerReceiverLocallyWithAutoUnregister(this.socialNotificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED");
    }
    
    private void sendRetryRequest(final Fragment fragment) {
        if (fragment == null || !(fragment instanceof ErrorWrapper.Callback)) {
            return;
        }
        Log.v("DetailsActivity", "Found frag to execute retry request...");
        ((ErrorWrapper.Callback)fragment).onRetryRequested();
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null);
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext, final Action action, final String s) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, action, s);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext) {
        show(netflixActivity, videoType, s, s2, playContext, null, null);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final Action action, final String s3) {
        Class<?> clazz = null;
        final boolean forKids = netflixActivity.isForKids();
        final boolean equals = VideoType.MOVIE.equals(videoType);
        if (forKids) {
            clazz = KidsDetailsActivity.class;
        }
        else if (equals) {
            clazz = MovieDetailsActivity.class;
        }
        else if (VideoType.SHOW.equals(videoType)) {
            clazz = ShowDetailsActivity.class;
        }
        else if (com.netflix.mediaclient.service.webclient.model.branches.Video.isSocialVideoType(videoType)) {
            Log.w("DetailsActivity", "Asked to show details for a social video type - shouldn't happen");
        }
        else {
            netflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(new IllegalStateException(String.format("Don't know how to handle %s type: %s, playContext:%s", s, videoType, playContext)));
        }
        if (clazz != null) {
            netflixActivity.startActivity(getIntent((Context)netflixActivity, clazz, videoType, s, s2, playContext, action, s3));
        }
    }
    
    public static void showEpisodeDetails(final Activity activity, final String s, final String s2, final PlayContext playContext, final Action action, final String s3) {
        final Intent putExtra = new Intent((Context)activity, (Class)ShowDetailsActivity.class).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext);
        if (action != null) {
            putExtra.putExtra("extra_action", (Serializable)action);
        }
        if (s3 != null) {
            putExtra.putExtra("extra_action_token", s3);
        }
        activity.startActivity(putExtra);
    }
    
    private void updateSocialNotificationsState() {
        if (this.serviceMan != null && SocialNotificationsUtils.isSocialNotificationsFeatureSupported(this.serviceMan)) {
            this.serviceMan.getBrowse().refreshSocialNotifications(false);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this;
    }
    
    public Action getAction() {
        return this.mAction;
    }
    
    public String getActionToken() {
        return this.mActionToken;
    }
    
    @Override
    public DataContext getDataContext() {
        return new DataContext(this.mPlayContext, this.videoId);
    }
    
    public String getEpisodeId() {
        return this.episodeId;
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.mPlayContext;
    }
    
    public int getTrackId() {
        if (this.mPlayContext instanceof PlayContext) {
            Log.d("DetailsActivity", "TrackId found in PlayContextImpl");
            return ((PlayContextImp)this.mPlayContext).getTrackId();
        }
        Log.d("DetailsActivity", "TrackId not found!");
        return -1;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.movieDetails;
    }
    
    @Override
    public VideoDetails getVideoDetails() {
        return null;
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
        this.videoId = this.getIntent().getStringExtra("extra_video_id");
        this.episodeId = this.getIntent().getStringExtra("extra_episode_id");
        this.mAction = (Action)this.getIntent().getSerializableExtra("extra_action");
        this.mActionToken = this.getIntent().getStringExtra("extra_action_token");
        final PlayContextImp playContext = (PlayContextImp)this.getIntent().getParcelableExtra("extra_playcontext");
        this.setPlayContext(playContext);
        if (Log.isLoggable("DetailsActivity", 2)) {
            Log.v("DetailsActivity", "TRACK_ID: " + playContext.getTrackId());
        }
        super.onCreate(bundle);
        this.registerReceivers();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MdxMenu.addSelectPlayTarget(this.getMdxMiniPlayerFrag(), menu);
        this.detailsMenu = new DetailsMenu(this, menu, this.videoId);
        this.notificationsMenuItem = SocialNotificationsUtils.addSocialNotificationsIconIfNeeded(this, menu);
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
        NflxProtocolUtils.reportUserOpenedNotification(this.serviceMan, this.getIntent());
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
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return SocialNotificationsUtils.tryHandleMenuItemClick(menuItem, (Context)this) || super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    public void onRetryRequested() {
        this.sendRetryRequest(this.getPrimaryFrag());
        this.sendRetryRequest(this.getSecondaryFrag());
    }
    
    void setAction(final Action mAction, final String mActionToken) {
        if (Log.isLoggable("DetailsActivity", 3)) {
            Log.d("DetailsActivity", "Action " + mAction + ", msg token: " + mActionToken);
        }
        this.mAction = mAction;
        this.mActionToken = mActionToken;
    }
    
    protected void setPlayContext(final PlayContext mPlayContext) {
        this.mPlayContext = mPlayContext;
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
    
    public enum Action
    {
        AddToMyList, 
        RemoveFromMyList;
    }
    
    private class MyListCallback extends LoggingManagerCallback
    {
        public MyListCallback(final String s) {
            super(s);
        }
        
        @Override
        public void onQueueAdd(final Status status) {
            super.onQueueAdd(status);
            int n = 2131493205;
            if (CommonStatus.OK == status) {
                n = 2131493379;
            }
            else if (status.getStatusCode() == StatusCode.ALREADY_IN_QUEUE) {
                n = 2131493381;
            }
            else if (status.getStatusCode() == StatusCode.NOT_VALID) {
                n = 2131493380;
            }
            Toast.makeText((Context)DetailsActivity.this, n, 1).show();
        }
        
        @Override
        public void onQueueRemove(final Status status) {
            super.onQueueRemove(status);
            int n = 2131493383;
            if (CommonStatus.OK == status) {
                n = 2131493382;
            }
            else if (status.getStatusCode() == StatusCode.NOT_IN_QUEUE) {
                Log.w("DetailsActivity", "It was already removed");
                n = 2131493382;
            }
            Toast.makeText((Context)DetailsActivity.this, n, 1).show();
        }
    }
}
