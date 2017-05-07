// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import java.net.URLEncoder;
import com.netflix.mediaclient.javabridge.ui.Storage;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.javabridge.ui.NetworkDiagnosis;
import com.netflix.mediaclient.javabridge.ui.Device;
import org.json.JSONException;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.InitEvent;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.Nrdp;

public final class NativeNrdp extends NativeNrdObject implements Nrdp
{
    public static final String METHOD_getConfigList = "getConfigList";
    public static final String METHOD_setConfigData = "setConfigData";
    private static final String TAG = "nf_nedp";
    private boolean ready;
    
    public NativeNrdp(final Bridge bridge) {
        super(bridge);
        this.ready = false;
    }
    
    private int handleEvent(JSONObject jsonObject) throws Exception {
        final String string = jsonObject.getString("name");
        final String string2 = jsonObject.getString("type");
        if (!"config".equals(string)) {
            final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "data", null);
            if (Log.isLoggable("nf_nedp", 3)) {
                Log.d("nf_nedp", "NativeNrdp received event " + jsonObject);
            }
            if ("background".equals(string)) {
                return this.handleNccpEvent(string, jsonObject2);
            }
            if ("config".equals(string)) {
                jsonObject = new JSONObject();
                jsonObject.put("type", (Object)"config");
                jsonObject.put("list", (Object)jsonObject2);
                return 1;
            }
            if (!"nrdconf".equals(string)) {
                if ("commandReceived".equals(string)) {
                    jsonObject = new JSONObject();
                    jsonObject.put("type", (Object)"command");
                    jsonObject.put("parameters", (Object)jsonObject2);
                    return 1;
                }
                if ("ObjectSyncComplete".equals(string)) {
                    this.ready = true;
                    return this.handleListener("init", new InitEvent());
                }
                if ("EventSourceError".equals(string2)) {
                    new JSONObject().put("type", (Object)"fatalerror");
                    return 1;
                }
                if ("Method".equals(string2)) {
                    final JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("type", (Object)"invalidargument");
                    jsonObject3.put("object", (Object)"");
                    jsonObject3.put("method", jsonObject.get("method"));
                    jsonObject3.put("argument", jsonObject.get("returnValue"));
                    return 1;
                }
                if (Log.isLoggable("nf_nedp", 5)) {
                    Log.w("nf_nedp", "Nobody to handle by name " + string);
                    return 1;
                }
            }
        }
        return 1;
    }
    
    private int handlePropertyUpdate(final JSONObject jsonObject) throws JSONException {
        if (this.getJSONObject(jsonObject, "properties", null) == null) {
            Log.w("nf_nedp", "handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean debug() {
        return false;
    }
    
    @Override
    public void exit() {
        Log.d("nf_nedp", "exit:: noop");
    }
    
    @Override
    public void getConfigList() {
        this.bridge.getNrdProxy().invokeMethod("", "getConfigList", null);
    }
    
    @Override
    public Device getDevice() {
        return null;
    }
    
    @Override
    public NetworkDiagnosis getDiagnosisTool() {
        return null;
    }
    
    @Override
    public com.netflix.mediaclient.javabridge.ui.Log getLog() {
        return null;
    }
    
    @Override
    public MdxController getMdxController() {
        return null;
    }
    
    @Override
    public IMedia getMedia() {
        return null;
    }
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public String getPath() {
        return "nrdp";
    }
    
    @Override
    public Registration getRegistration() {
        return null;
    }
    
    @Override
    public Storage getStorage() {
        return null;
    }
    
    @Override
    public boolean isReady() {
        return this.ready;
    }
    
    @Override
    public long now() {
        return System.currentTimeMillis();
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (Log.isLoggable("nf_nedp", 3)) {
                Log.d("nf_nedp", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && Log.isLoggable("nf_nedp", 3)) {
                    Log.d("nf_nedp", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
            Log.d("nf_nedp", "processUpdate: handle event");
            return this.handleEvent(jsonObject);
        }
        catch (Exception ex) {
            Log.e("nf_nedp", "Failed with JSON", ex);
            return 1;
        }
    }
    
    @Override
    public void setConfigData(final String s, final String s2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", (Object)URLEncoder.encode(s2, "utf8"));
            this.bridge.getNrdProxy().invokeMethod("", "setConfigData", jsonObject.toString());
        }
        catch (Exception ex) {
            Log.e("nf_nedp", "Failed to setConfigData", ex);
        }
    }
}
