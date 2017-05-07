// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Handler;
import android.content.Context;
import android.content.ServiceConnection;

final class GetTokenClient implements ServiceConnection
{
    final String applicationId;
    final Context context;
    final Handler handler;
    CompletedListener listener;
    boolean running;
    Messenger sender;
    
    GetTokenClient(Context context, final String applicationId) {
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.context = context;
        this.applicationId = applicationId;
        this.handler = new Handler() {
            public void handleMessage(final Message message) {
                GetTokenClient.this.handleMessage(message);
            }
        };
    }
    
    private void callback(final Bundle bundle) {
        if (this.running) {
            this.running = false;
            final CompletedListener listener = this.listener;
            if (listener != null) {
                listener.completed(bundle);
            }
        }
    }
    
    private void getToken() {
        final Bundle data = new Bundle();
        data.putString("com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
        final Message obtain = Message.obtain((Handler)null, 65536);
        obtain.arg1 = 20121101;
        obtain.setData(data);
        obtain.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(obtain);
        }
        catch (RemoteException ex) {
            this.callback(null);
        }
    }
    
    private void handleMessage(final Message message) {
        if (message.what == 65537) {
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
    
    void cancel() {
        this.running = false;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.sender = new Messenger(binder);
        this.getToken();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.sender = null;
        this.context.unbindService((ServiceConnection)this);
        this.callback(null);
    }
    
    void setCompletedListener(final CompletedListener listener) {
        this.listener = listener;
    }
    
    boolean start() {
        final Intent intent = new Intent("com.facebook.platform.PLATFORM_SERVICE");
        intent.addCategory("android.intent.category.DEFAULT");
        final Intent validateKatanaServiceIntent = NativeProtocol.validateKatanaServiceIntent(this.context, intent);
        if (validateKatanaServiceIntent == null) {
            this.callback(null);
            return false;
        }
        this.running = true;
        this.context.bindService(validateKatanaServiceIntent, (ServiceConnection)this, 1);
        return true;
    }
    
    interface CompletedListener
    {
        void completed(final Bundle p0);
    }
}
