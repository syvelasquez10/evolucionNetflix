// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.transport;

import org.json.JSONObject;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.StorageStateUtils;
import com.netflix.mediaclient.util.MediaUtils;
import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.media.JPlayer.AdaptiveMediaDecoderHelper;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.error.CrashReport;
import android.os.Process;
import com.netflix.mediaclient.javabridge.error.Signal;
import com.netflix.mediaclient.javabridge.invoke.android.InitVisualOn;
import com.netflix.mediaclient.javabridge.invoke.android.SetVideoSurface;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.util.DeviceCategory;
import java.lang.ref.WeakReference;
import android.os.HandlerThread;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.media.PlayerType;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.annotation.SuppressLint;
import android.os.Handler;

@SuppressLint({ "HandlerLeak" })
class NativeTransport$TransportEventHandler extends Handler
{
    final /* synthetic */ NativeTransport this$0;
    
    public NativeTransport$TransportEventHandler(final NativeTransport this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        if (message == null) {
            Log.e("nf-NativeTransport", "Received null message!");
            return;
        }
        if (!(message.obj instanceof String)) {
            Log.e("nf-NativeTransport", "Received obj is NOT string in message!");
            return;
        }
        final String s = (String)message.obj;
        if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", "Received message: " + s);
        }
        if (this.this$0.proxy != null) {
            this.this$0.proxy.processUpdate(s);
            return;
        }
        Log.e("nf-NativeTransport", "Unable to publish event, na not available");
    }
}
