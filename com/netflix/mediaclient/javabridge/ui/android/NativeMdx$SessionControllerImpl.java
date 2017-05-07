// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.invoke.registration.Ping;
import com.netflix.mediaclient.javabridge.invoke.mdx.Init;
import org.json.JSONArray;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.PairingController;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.DiscoveryController;
import com.netflix.mediaclient.javabridge.invoke.mdx.Exit;
import com.netflix.mediaclient.javabridge.invoke.mdx.Configure;
import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.mdx.ClearDeviceMap;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessageEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.SessionEndedEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessagingErrorEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessageDeliveredEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.StartSessionResponseEvent;
import com.netflix.mediaclient.event.nrdp.mdx.pair.PairingDeletedEvent;
import com.netflix.mediaclient.event.nrdp.mdx.pair.RegPairResponseEvent;
import com.netflix.mediaclient.event.nrdp.mdx.pair.PairingResponseEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.RemoteDeviceReadyEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceFoundEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceLostEvent;
import com.netflix.mediaclient.event.nrdp.mdx.StateEvent;
import com.netflix.mediaclient.event.nrdp.mdx.InitErrorEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.mdx.InitEvent;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.javabridge.invoke.mdx.session.StartSession;
import com.netflix.mediaclient.javabridge.invoke.mdx.session.SendMessage;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.session.EndSession;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.SessionController;

class NativeMdx$SessionControllerImpl implements SessionController
{
    private String mMessageName;
    private int mSessionId;
    final /* synthetic */ NativeMdx this$0;
    
    NativeMdx$SessionControllerImpl(final NativeMdx this$0) {
        this.this$0 = this$0;
        this.mSessionId = -1;
    }
    
    @Override
    public void endSession(final int n) {
        this.this$0.invokeNrdMethod(new EndSession(n));
        this.mSessionId = -1;
    }
    
    @Override
    public String getLastMessageName(final int n) {
        if (this.mSessionId == n) {
            return this.mMessageName;
        }
        return null;
    }
    
    @Override
    public long sendMessage(final int mSessionId, final String mMessageName, final JSONObject jsonObject) {
        this.mMessageName = mMessageName;
        this.mSessionId = mSessionId;
        final SendMessage sendMessage = new SendMessage(mSessionId, mMessageName, jsonObject);
        this.this$0.invokeNrdMethod(sendMessage);
        return sendMessage.getXid();
    }
    
    @Override
    public void startSession(final String s) {
        this.this$0.invokeNrdMethod(new StartSession(s));
    }
}
