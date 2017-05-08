// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

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
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.RemoteDeviceReadyEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceLostEvent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceFoundEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class MdxAgent$DiscoveryEventListener implements EventListener
{
    final /* synthetic */ MdxAgent this$0;
    
    MdxAgent$DiscoveryEventListener(final MdxAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        if (uiEvent instanceof DeviceFoundEvent) {
            final DeviceFoundEvent deviceFoundEvent = (DeviceFoundEvent)uiEvent;
            final String uuid = deviceFoundEvent.getRemoteDevice().uuid;
            final String dialUuid = deviceFoundEvent.getRemoteDevice().dialUuid;
            if (this.this$0.isSameDevice(uuid, this.this$0.mCurrentTargetUuid) || this.this$0.isSameDevice(dialUuid, this.this$0.mCurrentTargetUuid)) {
                final RemoteDevice access$2100 = this.this$0.getDeviceFromUuid(this.this$0.mCurrentTargetUuid);
                if (access$2100 != null) {
                    this.this$0.mTargetManager.targetFound(access$2100);
                }
            }
            if (this.this$0.mNotifier != null) {
                this.this$0.mNotifier.targetList();
            }
            this.this$0.getService().getClientLogging().getCustomerEventLogging().logMdxTarget("found", uuid, dialUuid, deviceFoundEvent.getRemoteDevice().serviceType);
            if (StringUtils.isNotEmpty(uuid)) {
                this.this$0.mTargetRestartingList.remove(uuid);
            }
        }
        else if (uiEvent instanceof DeviceLostEvent) {
            final String[] devices = ((DeviceLostEvent)uiEvent).getDevices();
            for (int length = devices.length, i = 0; i < length; ++i) {
                final String s = devices[i];
                if (this.this$0.isSameDevice(s, this.this$0.mCurrentTargetUuid) || this.this$0.mTargetManager.isTargetHaveContext(s)) {
                    if (!this.this$0.mTargetRestartingList.contains(s)) {
                        if (this.this$0.mNotifier != null) {
                            this.this$0.mNotifier.error(this.this$0.mCurrentTargetUuid, 200, "device lost");
                        }
                        this.this$0.sessionGone(false);
                        this.this$0.mMdxNrdpLogger.logDebug("current target device lost");
                    }
                    else if (Log.isLoggable()) {
                        Log.d("nf_mdx_MdxAgent", "MdxAgent: mTargetRestartingList has " + s + ", ignore device lost");
                    }
                }
                this.this$0.getService().getClientLogging().getCustomerEventLogging().logMdxTarget("lost", s, null, null);
            }
            if (this.this$0.mNotifier != null) {
                this.this$0.mNotifier.targetList();
            }
        }
        else if (uiEvent instanceof RemoteDeviceReadyEvent) {
            final RemoteDeviceReadyEvent remoteDeviceReadyEvent = (RemoteDeviceReadyEvent)uiEvent;
            if (this.this$0.isSameDevice(remoteDeviceReadyEvent.getUuid(), this.this$0.mCurrentTargetUuid)) {
                if (remoteDeviceReadyEvent.getLaunchStatus() == 1) {
                    Log.d("nf_mdx_MdxAgent", "MdxAgent: RemoteDeviceReadyEvent, app's launched");
                    this.this$0.mTargetManager.targetLaunched(this.this$0.mCurrentTargetUuid, true);
                    this.this$0.mMdxNrdpLogger.logDebug("current target device launched");
                    return;
                }
                Log.d("nf_mdx_MdxAgent", "MdxAgent: RemoteDeviceReadyEvent, app's launch failed");
                this.this$0.mTargetManager.targetLaunched(this.this$0.mCurrentTargetUuid, false);
                if (this.this$0.mTargetManager.isCurrentSessionEnded()) {
                    this.this$0.mTargetManager.targetGone(this.this$0.mCurrentTargetUuid);
                    return;
                }
                if (this.this$0.mNotifier != null) {
                    final RemoteDevice access$2101 = this.this$0.getDeviceFromUuid(this.this$0.mCurrentTargetUuid);
                    String friendlyName = new String();
                    if (access$2101 != null) {
                        friendlyName = access$2101.friendlyName;
                    }
                    this.this$0.mNotifier.error(this.this$0.mCurrentTargetUuid, 106, friendlyName);
                    this.this$0.mMdxNrdpLogger.logError("current target device fails to launched");
                }
            }
        }
    }
}
