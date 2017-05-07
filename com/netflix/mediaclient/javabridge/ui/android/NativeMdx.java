// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.invoke.mdx.session.StartSession;
import com.netflix.mediaclient.javabridge.invoke.mdx.session.SendMessage;
import com.netflix.mediaclient.javabridge.invoke.mdx.session.EndSession;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.RegistrationPairingRequest;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.PairingRequest;
import com.netflix.mediaclient.javabridge.invoke.mdx.pair.DeletePairing;
import com.netflix.mediaclient.javabridge.invoke.mdx.discovery.LaunchNetflix;
import com.netflix.mediaclient.javabridge.invoke.mdx.discovery.IsRemoteDeviceReady;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.invoke.registration.Ping;
import com.netflix.mediaclient.javabridge.invoke.mdx.Init;
import org.json.JSONArray;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.SessionController;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.PairingController;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.DiscoveryController;
import com.netflix.mediaclient.javabridge.invoke.mdx.Exit;
import com.netflix.mediaclient.javabridge.invoke.mdx.Configure;
import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.mdx.ClearDeviceMap;
import org.json.JSONException;
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
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;

public final class NativeMdx extends NativeNrdObject implements MdxController
{
    protected static final String MDX_EVENT_DATA_FIELD_TYPE = "type";
    private static final String TAG = "nf_mdx";
    DiscoveryControllerImpl mDiscovery;
    PairingControllerImpl mPairing;
    private PropertyUpdateListener mPropertyListener;
    SessionControllerImpl mSession;
    
    public NativeMdx(final Bridge bridge) {
        super(bridge);
        this.mDiscovery = new DiscoveryControllerImpl();
        this.mPairing = new PairingControllerImpl();
        this.mSession = new SessionControllerImpl();
    }
    
    private void handleEvents(final JSONObject jsonObject) throws JSONException {
        final String string = this.getString(jsonObject, "type", null);
        if (InitEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: InitEvent event");
            this.handleListener(InitEvent.TYPE.getName(), new InitEvent(jsonObject));
            return;
        }
        if (InitErrorEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: InitErrorEvent event");
            this.handleListener(InitErrorEvent.TYPE.getName(), new InitErrorEvent(jsonObject));
            return;
        }
        if (StateEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: StateEvent event");
            this.handleListener(StateEvent.TYPE.getName(), new StateEvent(jsonObject));
            return;
        }
        if (DeviceLostEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: DeviceLostEvent event");
            this.handleListener(DeviceLostEvent.TYPE.getName(), new DeviceLostEvent(jsonObject));
            return;
        }
        if (DeviceFoundEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: DeviceFoundEvent event");
            this.handleListener(DeviceFoundEvent.TYPE.getName(), new DeviceFoundEvent(jsonObject));
            return;
        }
        if (RemoteDeviceReadyEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: RemoteDeviceReadyEvent event");
            this.handleListener(RemoteDeviceReadyEvent.TYPE.getName(), new RemoteDeviceReadyEvent(jsonObject));
            return;
        }
        if (PairingResponseEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: PairingResponseEvent event");
            this.handleListener(PairingResponseEvent.TYPE.getName(), new PairingResponseEvent(jsonObject));
            return;
        }
        if (RegPairResponseEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: RegPairResponseEvent event");
            this.handleListener(RegPairResponseEvent.TYPE.getName(), new RegPairResponseEvent(jsonObject));
            return;
        }
        if (PairingDeletedEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: PairingDeletedEvent event");
            this.handleListener(PairingDeletedEvent.TYPE.getName(), new PairingDeletedEvent(jsonObject));
            return;
        }
        if (StartSessionResponseEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: StartSessionResponseEvent event");
            this.handleListener(StartSessionResponseEvent.TYPE.getName(), new StartSessionResponseEvent(jsonObject));
            return;
        }
        if (MessageDeliveredEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: MessageDeliveredEvent event");
            this.handleListener(MessageDeliveredEvent.TYPE.getName(), new MessageDeliveredEvent(jsonObject));
            return;
        }
        if (MessagingErrorEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: MessagingErrorEvent event");
            this.handleListener(MessagingErrorEvent.TYPE.getName(), new MessagingErrorEvent(jsonObject));
            return;
        }
        if (SessionEndedEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: SessionEndedEvent event");
            this.handleListener(SessionEndedEvent.TYPE.getName(), new SessionEndedEvent(jsonObject));
            return;
        }
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "NativeMdx: MessageEvent event = " + string);
        }
        this.handleListener(MessageEvent.TYPE.getName(), new MessageEvent(jsonObject));
    }
    
    private void invokeNrdMethod(final Invoke invoke) {
        if (this.bridge != null && this.bridge.getNrdProxy() != null) {
            this.bridge.getNrdProxy().invokeMethod(invoke);
            return;
        }
        Log.e("nf_mdx", "fail to invokeNrdMethod " + invoke.toString());
    }
    
    @Override
    public void clearDeviceMap() {
        this.invokeNrdMethod(new ClearDeviceMap());
    }
    
    @Override
    public void configure(final Map<String, String> map) {
        this.invokeNrdMethod(new Configure(map));
    }
    
    @Override
    public void exit() {
        this.invokeNrdMethod(new Exit());
    }
    
    @Override
    public DiscoveryController getDiscovery() {
        return this.mDiscovery;
    }
    
    @Override
    public String getName() {
        return "mdx";
    }
    
    @Override
    public PairingController getPairing() {
        return this.mPairing;
    }
    
    @Override
    public String getPath() {
        return "nrdp.mdx";
    }
    
    @Override
    public SessionController getSession() {
        return this.mSession;
    }
    
    @Override
    public void init(final Map<String, String> map, final boolean b, final JSONArray jsonArray) {
        this.invokeNrdMethod(new Init(true, map, b, jsonArray));
    }
    
    @Override
    public void pingNccp() {
        this.invokeNrdMethod(new Ping());
    }
    
    @Override
    public int processUpdate(JSONObject jsonObject) {
        while (true) {
            while (true) {
                int n = 0;
                Label_0410: {
                    try {
                        final String string = this.getString(jsonObject, "type", null);
                        if (Log.isLoggable("nf_mdx", 3)) {
                            Log.d("nf_mdx", "NativeMdx: processUpdate: handle type " + string);
                        }
                        if ("PropertyUpdate".equalsIgnoreCase(string)) {
                            if (jsonObject != null && Log.isLoggable("nf_mdx", 3)) {
                                Log.d("nf_mdx", "NativeMdx: processUpdate: handle prop update " + jsonObject.toString());
                            }
                            jsonObject = this.getJSONObject(jsonObject, "properties", null);
                            if (jsonObject != null && jsonObject.has("remoteDeviceMap")) {
                                Log.d("nf_mdx", "NativeMdx: property update for remoteDeviceMap");
                                if (this.mPropertyListener == null) {
                                    return 1;
                                }
                                final JSONArray jsonArray = jsonObject.getJSONArray("remoteDeviceMap");
                                if (jsonArray == null) {
                                    return 0;
                                }
                                if (Log.isLoggable("nf_mdx", 3)) {
                                    Log.d("nf_mdx", "NativeMdx: Devices found: " + jsonArray.length());
                                }
                                final ArrayList<RemoteDevice> list = new ArrayList<RemoteDevice>();
                                n = 0;
                                if (n >= jsonArray.length()) {
                                    this.mPropertyListener.onRemoteDeviceMap(list);
                                    return 1;
                                }
                                final RemoteDevice remoteDevice = RemoteDevice.toRemoteDevice(jsonArray.getJSONObject(n));
                                if (Log.isLoggable("nf_mdx", 3)) {
                                    Log.d("nf_mdx", "NativeMdx: Found: " + remoteDevice);
                                }
                                if (remoteDevice != null) {
                                    Log.d("nf_mdx", "NativeMdx: add to list");
                                    list.add(remoteDevice);
                                }
                                break Label_0410;
                            }
                            else if (jsonObject.has("isReady")) {
                                if (this.mPropertyListener != null) {
                                    this.mPropertyListener.onIsReady(this.getBoolean(jsonObject, "isReady", false));
                                    return 1;
                                }
                                return 1;
                            }
                        }
                        else {
                            if ("Event".equalsIgnoreCase(string)) {
                                Log.d("nf_mdx", "NativeMdx: processUpdate: handle event");
                                this.handleEvents(this.getJSONObject(jsonObject, "data", null));
                                return 1;
                            }
                            if (Log.isLoggable("nf_mdx", 3)) {
                                Log.d("nf_mdx", "NativeMdx: processUpdate: type not handled ??? " + string);
                                return 0;
                            }
                        }
                    }
                    catch (Exception ex) {
                        Log.e("nf_mdx", "NativeMdx: processUpdate: Failed", ex);
                    }
                    return 0;
                }
                ++n;
                continue;
            }
        }
    }
    
    @Override
    public void removePropertyUpdateListener() {
        this.mPropertyListener = null;
    }
    
    @Override
    public void setPropertyUpdateListener(final PropertyUpdateListener mPropertyListener) {
        this.mPropertyListener = mPropertyListener;
    }
    
    class DiscoveryControllerImpl implements DiscoveryController
    {
        @Override
        public void isRemoteDeviceReady(final String s) {
            NativeMdx.this.invokeNrdMethod(new IsRemoteDeviceReady(s));
        }
        
        @Override
        public void launchNetflix(final String s, final Map<String, String> map) {
            NativeMdx.this.invokeNrdMethod(new LaunchNetflix(s, map));
        }
    }
    
    class PairingControllerImpl implements PairingController
    {
        @Override
        public void deletePairing(final String s) {
            NativeMdx.this.invokeNrdMethod(new DeletePairing(s));
        }
        
        @Override
        public void pairingRequest(final String s) {
            NativeMdx.this.invokeNrdMethod(new PairingRequest(s));
        }
        
        @Override
        public void registrationPairingRequest(final String s) {
            NativeMdx.this.invokeNrdMethod(new RegistrationPairingRequest(s, null));
        }
        
        @Override
        public void registrationPairingRequest(final String s, final String s2) {
            NativeMdx.this.invokeNrdMethod(new RegistrationPairingRequest(s, s2));
        }
    }
    
    class SessionControllerImpl implements SessionController
    {
        @Override
        public void endSession(final int n) {
            NativeMdx.this.invokeNrdMethod(new EndSession(n));
        }
        
        @Override
        public long sendMessage(final int n, final String s, final JSONObject jsonObject) {
            final SendMessage sendMessage = new SendMessage(n, s, jsonObject);
            NativeMdx.this.invokeNrdMethod(sendMessage);
            return sendMessage.getXid();
        }
        
        @Override
        public void startSession(final String s) {
            NativeMdx.this.invokeNrdMethod(new StartSession(s));
        }
    }
}
