// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.invoke.registration.Ping;
import com.netflix.mediaclient.javabridge.invoke.mdx.Init;
import org.json.JSONArray;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.SessionController;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.DiscoveryController;
import com.netflix.mediaclient.javabridge.invoke.mdx.Exit;
import com.netflix.mediaclient.javabridge.invoke.mdx.Configure;
import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.mdx.ClearDeviceMap;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessageEvent;
import com.netflix.mediaclient.event.nrdp.mdx.TargetRestartingEvent;
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
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.RegistrationPairingRequest;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.PairingRequest;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.DeletePairing;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.PairingController;

class NativeMdx$PairingControllerImpl implements PairingController
{
    final /* synthetic */ NativeMdx this$0;
    
    NativeMdx$PairingControllerImpl(final NativeMdx this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void deletePairing(final String s) {
        this.this$0.invokeNrdMethod(new DeletePairing(s));
    }
    
    @Override
    public void pairingRequest(final String s) {
        this.this$0.invokeNrdMethod(new PairingRequest(s));
    }
    
    @Override
    public void registrationPairingRequest(final String s) {
        this.this$0.invokeNrdMethod(new RegistrationPairingRequest(s, null));
    }
    
    @Override
    public void registrationPairingRequest(final String s, final String s2) {
        this.this$0.invokeNrdMethod(new RegistrationPairingRequest(s, s2));
    }
}
