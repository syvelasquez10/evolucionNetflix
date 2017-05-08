// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.javabridge.error.CrashReport;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.transport.TransportFactory;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.transport.Transport;
import com.netflix.mediaclient.javabridge.error.CrashListener;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdProxy;

public abstract class BaseNrdProxy implements NrdProxy
{
    protected static final String DATA = "data";
    protected static final String NAME = "name";
    public static final String NAME_BGD = "background";
    public static final String NAME_IMC = "IMediaControl";
    public static final String NAME_NETWORK = "INetwork";
    public static final String NAME_OBJECT_SYNC_COMPLETE = "ObjectSyncComplete";
    protected static final String OBJECT = "object";
    protected static final String TYPE = "type";
    protected Bridge bridge;
    protected CrashListener listener;
    protected Transport transport;
    
    public BaseNrdProxy(final Bridge bridge) {
        if (bridge == null) {
            throw new IllegalArgumentException("Bridge can not be null");
        }
        this.bridge = bridge;
    }
    
    @Override
    public void connect() {
        this.transport.uiLoaded();
    }
    
    @Override
    public void destroy() {
        Log.d(this.getLogTag(), "NrdProxy::destroy");
        if (this.transport != null) {
            this.transport.disconnect();
            this.transport.close();
        }
    }
    
    @Override
    public void disconnect() {
        final Transport transport = this.transport;
        if (transport != null) {
            try {
                transport.uiUnloaded();
                return;
            }
            catch (Throwable t) {
                Log.e(this.getLogTag(), "SPY-10406: Exception form native, ignore...", t);
                return;
            }
        }
        Log.d(this.getLogTag(), "NrdProxy::disconnect: transport is NULL!");
    }
    
    protected boolean getBoolean(final JSONObject jsonObject, final String s, final boolean b) {
        return JsonUtils.getBoolean(jsonObject, s, b);
    }
    
    protected int getInt(final JSONObject jsonObject, final String s, final int n) {
        return JsonUtils.getInt(jsonObject, s, n);
    }
    
    protected JSONArray getJSONArray(final JSONObject jsonObject, final String s) {
        return JsonUtils.getJSONArray(jsonObject, s);
    }
    
    protected JSONObject getJSONObject(final JSONObject jsonObject, final String s, final JSONObject jsonObject2) {
        return this.getJSONObject(jsonObject, s, jsonObject2);
    }
    
    protected abstract String getLogTag();
    
    protected String getString(final JSONObject jsonObject, final String s, final String s2) {
        return JsonUtils.getString(jsonObject, s, s2);
    }
    
    @Override
    public void init(final String s) {
        Log.d(this.getLogTag(), "NrdProxy::init");
        (this.transport = TransportFactory.createTransport(s, this.bridge, this)).connect();
    }
    
    @Override
    public void invokeMethod(final Invoke invoke) {
        if (invoke == null) {
            Log.w(this.getLogTag(), "Command is nul, noop");
            return;
        }
        invoke.execute(this.transport);
    }
    
    @Override
    public void invokeMethod(final String s, final String s2, final String s3) {
        this.transport.invokeMethod(s, s2, s3);
    }
    
    @Override
    public void postCrashReport(final CrashReport crashReport) {
        synchronized (this) {
            if (this.listener != null && crashReport != null) {
                this.listener.onCrash(crashReport);
            }
            else {
                Log.e(this.getLogTag(), "Listener or report are null!");
            }
        }
    }
    
    @Override
    public void removeCrashListener() {
        synchronized (this) {
            this.listener = null;
        }
    }
    
    @Override
    public void setCrashListener(final CrashListener listener) {
        synchronized (this) {
            this.listener = listener;
        }
    }
    
    @Override
    public void setProperty(final String s, final String s2, final String s3) {
        this.transport.setProperty(s, s2, s3);
    }
}
