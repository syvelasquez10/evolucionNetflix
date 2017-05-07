// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke;

import com.netflix.mediaclient.javabridge.transport.Transport;
import com.netflix.mediaclient.Log;

public abstract class BaseInvoke implements Invoke
{
    protected static final String TAG = "nf_invoke";
    protected String arguments;
    protected String method;
    protected String path;
    protected String target;
    
    public BaseInvoke(final String s, final String method) {
        this.arguments = "{}";
        if (s == null) {
            throw new IllegalArgumentException("Target can not be null!");
        }
        if (method == null) {
            throw new IllegalArgumentException("Method can not be null!");
        }
        this.target = s;
        this.method = method;
        if (s.startsWith("nrdp")) {
            Log.d("nf_invoke", "Target is nrdp or somebody is setting whole path to target");
            this.path = s;
            return;
        }
        this.path = "nrdp." + s;
    }
    
    @Override
    public void execute(final Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport is null");
        }
        transport.invokeMethod(this);
    }
    
    @Override
    public String getArguments() {
        return this.arguments;
    }
    
    @Override
    public String getMethod() {
        return this.method;
    }
    
    @Override
    public String getObject() {
        return this.path;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    @Override
    public String toString() {
        return "Invoke [target=" + this.target + ", method=" + this.method + ", arguments=" + this.arguments + "]";
    }
}
