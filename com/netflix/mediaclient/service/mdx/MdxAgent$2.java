// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import android.app.Service;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import android.text.TextUtils;
import android.app.Notification;
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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManagerFactory;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.PendingIntent;
import android.content.Intent;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.net.wifi.WifiManager$WifiLock;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
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
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.Log;

class MdxAgent$2 implements Runnable
{
    final /* synthetic */ MdxAgent this$0;
    
    MdxAgent$2(final MdxAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mMdxNativeExitCompleted.get()) {
            Log.d("nf_mdx_agent", "notifyIsUserLogin: login, init native");
            this.this$0.getMainHandler().removeCallbacks(this.this$0.mInitMdxNative);
            this.this$0.mReady.set(false);
            this.this$0.addStateEventListener();
            this.this$0.addDiscoveryEventListener();
            this.this$0.addPairingEventListener(this.this$0.mTargetManager);
            this.this$0.addSessionEventListener(this.this$0.mTargetManager);
            this.this$0.mMdxController.init(new HashMap<String, String>(), this.this$0.mDisableWebSocket, this.this$0.getService().getConfiguration().getMdxBlackListTargets());
            this.this$0.mTargetMap.clear();
            return;
        }
        Log.d("nf_mdx_agent", "notifyIsUserLogin: login, already exited check back in 1 sec ");
        this.this$0.getMainHandler().postDelayed(this.this$0.mInitMdxNative, 1000L);
    }
}
