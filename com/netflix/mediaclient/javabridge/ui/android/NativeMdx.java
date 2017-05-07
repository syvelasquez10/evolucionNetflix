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
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.PairingController;
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
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;

public final class NativeMdx extends NativeNrdObject implements MdxController
{
    protected static final String MDX_EVENT_DATA_FIELD_TYPE = "type";
    private static final String TAG = "nf_mdx";
    NativeMdx$DiscoveryControllerImpl mDiscovery;
    NativeMdx$PairingControllerImpl mPairing;
    private MdxController$PropertyUpdateListener mPropertyListener;
    NativeMdx$SessionControllerImpl mSession;
    
    public NativeMdx(final Bridge bridge) {
        super(bridge);
        this.mDiscovery = new NativeMdx$DiscoveryControllerImpl(this);
        this.mPairing = new NativeMdx$PairingControllerImpl(this);
        this.mSession = new NativeMdx$SessionControllerImpl(this);
    }
    
    private void handleEvents(final JSONObject jsonObject) {
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
        if (TargetRestartingEvent.TYPE.getName().equalsIgnoreCase(string)) {
            Log.d("nf_mdx", "NativeMdx: TargetRestartingEvent event");
            this.handleListener(TargetRestartingEvent.TYPE.getName(), new TargetRestartingEvent(jsonObject));
            return;
        }
        if (Log.isLoggable()) {
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
                Label_0395: {
                    try {
                        final String string = this.getString(jsonObject, "type", null);
                        if (Log.isLoggable()) {
                            Log.d("nf_mdx", "NativeMdx: processUpdate: handle type " + string);
                        }
                        if ("PropertyUpdate".equalsIgnoreCase(string)) {
                            if (jsonObject != null && Log.isLoggable()) {
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
                                if (Log.isLoggable()) {
                                    Log.d("nf_mdx", "NativeMdx: Devices found: " + jsonArray.length());
                                }
                                final ArrayList<RemoteDevice> list = new ArrayList<RemoteDevice>();
                                n = 0;
                                if (n >= jsonArray.length()) {
                                    this.mPropertyListener.onRemoteDeviceMap(list);
                                    return 1;
                                }
                                final RemoteDevice remoteDevice = RemoteDevice.toRemoteDevice(jsonArray.getJSONObject(n));
                                if (Log.isLoggable()) {
                                    Log.d("nf_mdx", "NativeMdx: Found: " + remoteDevice);
                                }
                                if (remoteDevice != null) {
                                    Log.d("nf_mdx", "NativeMdx: add to list");
                                    list.add(remoteDevice);
                                }
                                break Label_0395;
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
                            if (Log.isLoggable()) {
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
    public void setPropertyUpdateListener(final MdxController$PropertyUpdateListener mPropertyListener) {
        this.mPropertyListener = mPropertyListener;
    }
}
