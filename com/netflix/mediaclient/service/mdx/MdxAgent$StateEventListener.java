// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import java.util.List;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import android.text.TextUtils;
import android.app.Notification;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import android.content.IntentFilter;
import android.annotation.SuppressLint;
import android.os.PowerManager;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManagerFactory;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.PendingIntent;
import android.content.Intent;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.service.NetflixService;
import android.net.wifi.WifiManager$WifiLock;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import android.content.BroadcastReceiver;
import android.os.PowerManager$WakeLock;
import com.netflix.mediaclient.service.mdx.notification.IMdxNotificationManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.service.mdx.cast.CastManager;
import android.graphics.Bitmap;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManager$MdxNotificationIntentRetriever;
import com.netflix.mediaclient.service.mdx.cast.CastAgent;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.mdx.TargetRestartingEvent;
import com.netflix.mediaclient.event.nrdp.mdx.StateEvent;
import com.netflix.mediaclient.event.nrdp.mdx.InitErrorEvent;
import com.netflix.mediaclient.event.nrdp.mdx.InitEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class MdxAgent$StateEventListener implements EventListener
{
    final /* synthetic */ MdxAgent this$0;
    
    MdxAgent$StateEventListener(final MdxAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        if (uiEvent instanceof InitEvent) {
            this.this$0.mReady.set(true);
            this.this$0.mTargetMap.clear();
            this.this$0.mTargetRestartingList.clear();
            this.this$0.mNotifier.ready();
            if (this.this$0.mCastManager != null) {
                this.this$0.mCastManager.start();
            }
        }
        else if (uiEvent instanceof InitErrorEvent) {
            this.this$0.mMdxNrdpLogger.logDebug("MDX init error");
            this.this$0.mReady.set(false);
            this.this$0.mMdxNativeExitCompleted.set(true);
            if (this.this$0.mNotifier != null) {
                this.this$0.mNotifier.error(this.this$0.mCurrentTargetUuid, 103, ((InitErrorEvent)uiEvent).getErrorDesc());
            }
        }
        else if (uiEvent instanceof StateEvent) {
            if (((StateEvent)uiEvent).isReady()) {
                this.this$0.mMdxNrdpLogger.logDebug("MDX state READY");
                this.this$0.mReady.set(true);
                this.this$0.mTargetMap.clear();
                this.this$0.mTargetRestartingList.clear();
                if (this.this$0.mNotifier != null) {
                    this.this$0.mNotifier.ready();
                }
                if (this.this$0.mCastManager != null) {
                    this.this$0.mCastManager.start();
                }
            }
            else {
                this.this$0.mMdxNrdpLogger.logDebug("MDX state NOT_READY");
                this.this$0.mReady.set(false);
                this.this$0.mMdxNativeExitCompleted.set(true);
                this.this$0.mTargetMap.clear();
                this.this$0.mTargetRestartingList.clear();
                if (this.this$0.mNotifier != null) {
                    this.this$0.mNotifier.notready();
                }
                this.this$0.sessionGone();
                if (this.this$0.mCastManager != null) {
                    this.this$0.mCastManager.stop();
                }
            }
        }
        else if (uiEvent instanceof TargetRestartingEvent) {
            final TargetRestartingEvent targetRestartingEvent = (TargetRestartingEvent)uiEvent;
            final String fromUuid = targetRestartingEvent.getFromUuid();
            final int duration = targetRestartingEvent.getDuration();
            if (Log.isLoggable()) {
                Log.d("nf_mdx_MdxAgent", "MdxAgent: TargetRestartingEvent " + fromUuid + ", " + duration);
            }
            if (!this.this$0.mTargetRestartingList.contains(fromUuid)) {
                this.this$0.mTargetRestartingList.add(fromUuid);
                Log.d("nf_mdx_MdxAgent", "MdxAgent: add to mTargetRestartingList");
            }
        }
    }
}
