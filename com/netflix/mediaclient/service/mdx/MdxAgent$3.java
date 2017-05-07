// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import android.text.TextUtils;
import android.util.Pair;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.annotation.SuppressLint;
import android.os.PowerManager;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.Iterator;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManagerFactory;
import android.app.PendingIntent;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.service.NetflixService;
import android.net.wifi.WifiManager$WifiLock;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import android.os.PowerManager$WakeLock;
import com.netflix.mediaclient.service.mdx.notification.IMdxNotificationManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.mdx.cast.CastManager;
import android.graphics.Bitmap;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManager$MdxNotificationIntentRetriever;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.servicemgr.IMdxSharedState$MdxPlaybackState;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.MdxPostplayState$PostplayTitle;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.MdxPostplayState;
import com.netflix.mediaclient.util.StringUtils;
import android.app.Service;
import android.app.Notification;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.BroadcastReceiver;

class MdxAgent$3 extends BroadcastReceiver
{
    final /* synthetic */ MdxAgent this$0;
    
    MdxAgent$3(final MdxAgent this$0) {
        this.this$0 = this$0;
    }
    
    private void doMDXPlayBackEnd(final Intent intent) {
        if (!MdxAgent$Utils.isInPostPlay(intent)) {
            Log.d("nf_mdx_agent", "MdxAgent: receive MDXUPDATE_PLAYBACKEND");
            this.this$0.mMdxSessionWatchDog.stop();
            this.this$0.clearVideoDetails();
            this.this$0.stopAllNotifications();
            this.this$0.releaseWiFi();
            if (this.this$0.mTargetSelector != null) {
                this.this$0.mTargetSelector.targetBecomeInactive(this.this$0.mCurrentTargetUuid);
            }
            if (this.this$0.mSwitchTarget != null) {
                this.this$0.mSwitchTarget.targetPlaybackStopped(this.this$0.mCurrentTargetUuid);
            }
        }
    }
    
    private void doMDXPlaybackStart() {
        Log.d("nf_mdx_agent", "MdxAgent: receive MDXUPDATE_PLAYBACKSTART");
        this.this$0.mMdxSessionWatchDog.start();
        this.this$0.lockWiFi();
        this.this$0.ensureManagers();
        if (AndroidUtils.getAndroidVersion() < 21) {
            this.this$0.mRemoteControlClientManager.start(false, null, this.this$0.mCurrentTargetUuid);
            this.this$0.updateMdxRemoteClient(false);
            if (this.this$0.mBoxartBitmap != null) {
                this.this$0.mRemoteControlClientManager.setState(false, false, false);
                this.this$0.mRemoteControlClientManager.setBoxart(this.this$0.mBoxartBitmap);
            }
        }
        else if (this.this$0.mVolumeController != null) {
            this.this$0.mVolumeController.startMediaSession();
        }
        this.this$0.mMdxNotificationManager.startNotification((Notification)this.this$0.getMdxNotification(false).second, this.this$0.getService(), false);
        this.this$0.mMdxNotificationManager.setPlayerStateNotify(false, false);
        this.this$0.mMdxNotificationManager.setBoxartNotify(this.this$0.mBoxartBitmap);
        if (this.this$0.mTargetSelector != null) {
            this.this$0.mTargetSelector.targetBecomeActive(this.this$0.mCurrentTargetUuid);
        }
    }
    
    private void doMDXPostPlay(final Intent intent) {
        final String string = intent.getExtras().getString("postplayState");
        if (!StringUtils.isEmpty(string)) {
            final MdxPostplayState mdxPostplayState = new MdxPostplayState(string);
            if (mdxPostplayState.isInCountdown()) {
                this.doMDXPostPlayCountdownStart(intent, string);
            }
            else if (mdxPostplayState.isInPrompt()) {
                this.doMDXPostPlayPrompt(intent, string);
            }
        }
    }
    
    private void doMDXPostPlayCountdownStart(final Intent intent, final String s) {
        this.this$0.ensureManagers();
        this.updateVideoIdsForPostplay(s);
        if (AndroidUtils.getAndroidVersion() < 21) {
            this.this$0.mRemoteControlClientManager.start(true, this.this$0.mVideoDetailsPostplay, this.this$0.mCurrentTargetUuid);
            this.this$0.updateMdxRemoteClient(true);
            this.this$0.mRemoteControlClientManager.setState(false, false, true);
        }
        this.this$0.mMdxNotificationManager.startNotification((Notification)this.this$0.getMdxNotification(true).second, this.this$0.getService(), true);
        this.this$0.mMdxNotificationManager.setUpNextStateNotify(false, false, true);
    }
    
    private void doMDXPostPlayPrompt(final Intent intent, final String s) {
        this.this$0.getBrowseAgent().fetchPostPlayVideos(String.valueOf(this.this$0.getVideoIds().episodeId), new MdxAgent$3$1(this));
    }
    
    private void dpMDXSimplePlaybackState(final Intent intent) {
        final boolean booleanExtra = intent.getBooleanExtra("paused", false);
        final boolean booleanExtra2 = intent.getBooleanExtra("transitioning", false);
        final boolean inPostPlay = MdxAgent$Utils.isInPostPlay(intent);
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: simplePlaybackState : paused " + booleanExtra + ", transitioning " + booleanExtra2);
        }
        this.this$0.ensureManagers();
        if (AndroidUtils.getAndroidVersion() < 21) {
            this.this$0.mRemoteControlClientManager.setState(booleanExtra, booleanExtra2, inPostPlay);
        }
        this.this$0.mMdxNotificationManager.setPlayerStateNotify(booleanExtra, booleanExtra2);
    }
    
    private void updateVideoIdsForPostplay(final String s) {
        final MdxPostplayState mdxPostplayState = new MdxPostplayState(s);
        if (mdxPostplayState.isInCountdown()) {
            final MdxPostplayState$PostplayTitle[] postplayTitle = mdxPostplayState.getPostplayTitle();
            if (postplayTitle.length > 0 && postplayTitle[0].isEpisode() && postplayTitle[0].getId() > 0) {
                this.this$0.mVideoIdsPostplay = new WebApiUtils$VideoIds();
                this.this$0.mVideoIdsPostplay.episode = true;
                this.this$0.mVideoIdsPostplay.episodeId = postplayTitle[0].getId();
                this.this$0.fetchVideoDetail(false, true);
            }
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        boolean b = false;
        if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX")) {
            if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND")) {
                this.doMDXPlayBackEnd(intent);
            }
            else {
                if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART")) {
                    this.doMDXPlaybackStart();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE".equals(intent.getAction())) {
                    if (this.this$0.getSharedState() != null && this.this$0.getSharedState().getMdxPlaybackState() == IMdxSharedState$MdxPlaybackState.Transitioning) {
                        this.this$0.ensureManagers();
                        if (this.this$0.mMdxNotificationManager.isInPostPlay()) {
                            this.this$0.mMdxNotificationManager.stopPostplayNotification(this.this$0.getService());
                            if (AndroidUtils.getAndroidVersion() < 21) {
                                this.this$0.mRemoteControlClientManager.stop();
                            }
                        }
                    }
                    if (AndroidUtils.getAndroidVersion() >= 21 && this.this$0.mVolumeController != null) {
                        this.this$0.mVolumeController.updateCurrentVolume(intent.getIntExtra("volume", 0), false);
                    }
                }
                else {
                    if ("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY".equals(intent.getAction())) {
                        this.doMDXPostPlay(intent);
                        return;
                    }
                    if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE")) {
                        this.dpMDXSimplePlaybackState(intent);
                        return;
                    }
                    if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR")) {
                        final int intExtra = intent.getIntExtra("errorCode", 0);
                        this.this$0.stopAllNotifications();
                        if (this.this$0.mNotifier != null) {
                            final MdxSharedState sharedState = this.this$0.mNotifier.getSharedState(this.this$0.mCurrentTargetUuid);
                            if (sharedState != null) {
                                if (IMdxSharedState$MdxPlaybackState.Loading == sharedState.getMdxPlaybackState() || IMdxSharedState$MdxPlaybackState.Transitioning == sharedState.getMdxPlaybackState()) {
                                    b = true;
                                }
                                if (intExtra >= 100 && intExtra < 200 && b) {
                                    Log.d("nf_mdx_agent", "MdxAgent: received error, clear video detail");
                                    this.this$0.clearVideoDetails();
                                }
                            }
                        }
                        if (intExtra >= 100 && intExtra < 200) {
                            this.this$0.resetTargetSelection();
                        }
                    }
                }
            }
        }
    }
}
