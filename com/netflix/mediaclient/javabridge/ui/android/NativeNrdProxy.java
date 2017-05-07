// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.invoke.Invoke;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.javabridge.Bridge;
import java.util.Map;
import com.netflix.mediaclient.javabridge.NrdpObject;
import com.netflix.mediaclient.javabridge.ui.BaseNrdProxy;

public final class NativeNrdProxy extends BaseNrdProxy
{
    private final String TAG;
    private NrdpObject media;
    private NrdpObject nrdp;
    private Map<String, NrdpObject> objMap;
    
    public NativeNrdProxy(final Bridge bridge) {
        super(bridge);
        this.TAG = "native_nrd_proxy";
        this.objMap = new HashMap<String, NrdpObject>();
        Log.d("native_nrd_proxy", "Add all NRD objects");
        this.nrdp = new NativeNrdp(bridge);
        this.media = new NativeMedia(bridge);
        final NativeDevice nativeDevice = new NativeDevice(bridge);
        final NativeStorage nativeStorage = new NativeStorage(bridge);
        final NativeRegistration nativeRegistration = new NativeRegistration(bridge);
        final NativeLog nativeLog = new NativeLog(bridge);
        final NativeMdx nativeMdx = new NativeMdx(bridge);
        final NativeNetworkDiagnosis nativeNetworkDiagnosis = new NativeNetworkDiagnosis(bridge);
        this.objMap.put(this.nrdp.getPath(), this.nrdp);
        this.objMap.put(this.media.getPath(), this.media);
        this.objMap.put(nativeDevice.getPath(), nativeDevice);
        this.objMap.put(nativeStorage.getPath(), nativeStorage);
        this.objMap.put(nativeRegistration.getPath(), nativeRegistration);
        this.objMap.put(nativeMdx.getPath(), nativeMdx);
        this.objMap.put(nativeLog.getPath(), nativeLog);
        this.objMap.put(nativeNetworkDiagnosis.getPath(), nativeNetworkDiagnosis);
    }
    
    private String getKey(final String s) {
        String s2;
        if (s == null) {
            s2 = "nrdp";
        }
        else {
            s2 = s;
            if (!s.startsWith("nrdp")) {
                return "nrdp." + s;
            }
        }
        return s2;
    }
    
    private int handleName(final JSONObject jsonObject) throws JSONException {
        Log.d("native_nrd_proxy", "MUNRDP::handleName: start");
        final String string = this.getString(jsonObject, "name", null);
        if (string == null) {
            Log.d("native_nrd_proxy", "handleName:: name not found");
        }
        else {
            if ("IMediaControl".equalsIgnoreCase(string)) {
                Log.d("native_nrd_proxy", "handleName:: IMC event, pass to Media proxy");
                return this.media.processUpdate(jsonObject);
            }
            if ("background".equalsIgnoreCase(string)) {
                Log.d("native_nrd_proxy", "handleName:: background event, pass to Media proxy");
                return this.media.processUpdate(jsonObject);
            }
            if ("ObjectSyncComplete".equalsIgnoreCase(string)) {
                Log.d("native_nrd_proxy", "handleName:: ObjectSyncComplete event, pass to Nrdp proxy");
                return this.nrdp.processUpdate(jsonObject);
            }
            if (Log.isLoggable("native_nrd_proxy", 3)) {
                Log.d("native_nrd_proxy", "Pass to UI. Handler not found for name " + string);
                return 0;
            }
        }
        return 0;
    }
    
    private int handleObject(final JSONObject jsonObject) throws JSONException {
        Log.d("native_nrd_proxy", "MUNRDP::handleObject: start");
        final String string = this.getString(jsonObject, "object", null);
        if (string == null) {
            Log.d("native_nrd_proxy", "handleObject:: object not found");
        }
        else {
            final NrdpObject nrdpObject = this.objMap.get(this.getKey(string));
            if (nrdpObject != null) {
                if (Log.isLoggable("native_nrd_proxy", 3)) {
                    Log.d("native_nrd_proxy", "Handler found for object " + string);
                }
                return nrdpObject.processUpdate(jsonObject);
            }
            if (Log.isLoggable("native_nrd_proxy", 3)) {
                Log.d("native_nrd_proxy", "Pass to UI. Handler not found for object " + string);
                return 0;
            }
        }
        return 0;
    }
    
    @Override
    public NrdpObject findObjectCache(final String s) {
        if (Log.isLoggable("native_nrd_proxy", 3)) {
            Log.d("native_nrd_proxy", "findObjectCache::  " + s);
        }
        return this.objMap.get(s);
    }
    
    @Override
    protected String getLogTag() {
        return "native_nrd_proxy";
    }
    
    @Override
    public void invokeMethod(final Invoke invoke) {
        if (invoke == null) {
            Log.w(this.getLogTag(), "Command is nul, noop");
            return;
        }
        this.transport.invokeMethod(invoke);
    }
    
    @Override
    public void invokeMethod(final String s, final String s2, final String s3) {
        if (Log.isLoggable("native_nrd_proxy", 3)) {
            Log.d("native_nrd_proxy", "invokeMethod(SSS):: object: " + s + ", method: " + s2 + ", arguments: " + s3);
        }
        this.transport.invokeMethod(s, s2, s3);
    }
    
    @Override
    public void processUpdate(final String s) {
        if (Log.isLoggable("native_nrd_proxy", 3)) {
            Log.d("native_nrd_proxy", "processUpdate:: " + s);
        }
        JSONObject jsonObject;
        int handleName;
        try {
            jsonObject = new JSONObject(s);
            handleName = this.handleName(jsonObject);
            if (handleName != 0) {
                Log.d("native_nrd_proxy", "MUNRDP::processUpdate: update consumed by name handler");
                return;
            }
            if (!jsonObject.has("object")) {
                Log.d("native_nrd_proxy", "Object property not found. Push update!");
                return;
            }
        }
        catch (JSONException ex) {
            Log.e("native_nrd_proxy", "Invalid JSON string received from NRDlib", (Throwable)ex);
            return;
        }
        if (handleName == 0 && 1 == this.handleObject(jsonObject)) {
            Log.d("native_nrd_proxy", "MUNRDP::processUpdate: update consumed by object handler");
        }
    }
    
    @Override
    public void setProperty(final String s, final String s2, final String s3) {
        if (Log.isLoggable("native_nrd_proxy", 3)) {
            Log.d("native_nrd_proxy", "setProperty:: object: " + s + ", property: " + s2 + ", value: " + s3);
        }
        this.transport.setProperty(s, s2, s3);
    }
}
