// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Handler;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class PlatformServiceClient implements ServiceConnection
{
    private final String applicationId;
    private final Context context;
    private final Handler handler;
    private PlatformServiceClient$CompletedListener listener;
    private final int protocolVersion;
    private int replyMessage;
    private int requestMessage;
    private boolean running;
    private Messenger sender;
    
    public PlatformServiceClient(Context context, final int requestMessage, final int replyMessage, final int protocolVersion, final String applicationId) {
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.context = context;
        this.requestMessage = requestMessage;
        this.replyMessage = replyMessage;
        this.applicationId = applicationId;
        this.protocolVersion = protocolVersion;
        this.handler = new PlatformServiceClient$1(this);
    }
    
    private void callback(final Bundle bundle) {
        if (this.running) {
            this.running = false;
            final PlatformServiceClient$CompletedListener listener = this.listener;
            if (listener != null) {
                listener.completed(bundle);
            }
        }
    }
    
    private void sendMessage() {
        final Bundle data = new Bundle();
        data.putString("com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
        this.populateRequestBundle(data);
        final Message obtain = Message.obtain((Handler)null, this.requestMessage);
        obtain.arg1 = this.protocolVersion;
        obtain.setData(data);
        obtain.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(obtain);
        }
        catch (RemoteException ex) {
            this.callback(null);
        }
    }
    
    public void cancel() {
        this.running = false;
    }
    
    protected Context getContext() {
        return this.context;
    }
    
    protected void handleMessage(final Message message) {
        if (message.what == this.replyMessage) {
            final Bundle data = message.getData();
            if (data.getString("com.facebook.platform.status.ERROR_TYPE") != null) {
                this.callback(null);
            }
            else {
                this.callback(data);
            }
            this.context.unbindService((ServiceConnection)this);
        }
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.sender = new Messenger(binder);
        this.sendMessage();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.sender = null;
        while (true) {
            try {
                this.context.unbindService((ServiceConnection)this);
                this.callback(null);
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            break;
        }
    }
    
    protected abstract void populateRequestBundle(final Bundle p0);
    
    public void setCompletedListener(final PlatformServiceClient$CompletedListener listener) {
        this.listener = listener;
    }
    
    public boolean start() {
        if (!this.running && NativeProtocol.getLatestAvailableProtocolVersionForService(this.context, this.protocolVersion) != -1) {
            final Intent platformServiceIntent = NativeProtocol.createPlatformServiceIntent(this.context);
            if (platformServiceIntent != null) {
                this.running = true;
                this.context.bindService(platformServiceIntent, (ServiceConnection)this, 1);
                return true;
            }
        }
        return false;
    }
}
