// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import android.content.ActivityNotFoundException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import java.util.Collections;
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.List;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Intent;
import java.util.Date;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.content.ServiceConnection;

class Session$TokenRefreshRequest implements ServiceConnection
{
    final Messenger messageReceiver;
    Messenger messageSender;
    final /* synthetic */ Session this$0;
    
    Session$TokenRefreshRequest(final Session this$0) {
        this.this$0 = this$0;
        this.messageReceiver = new Messenger((Handler)new Session$TokenRefreshRequestHandler(this.this$0, this));
        this.messageSender = null;
    }
    
    private void cleanup() {
        if (this.this$0.currentTokenRefreshRequest == this) {
            this.this$0.currentTokenRefreshRequest = null;
        }
    }
    
    private void refreshToken() {
        final Bundle data = new Bundle();
        data.putString("access_token", this.this$0.getTokenInfo().getToken());
        final Message obtain = Message.obtain();
        obtain.setData(data);
        obtain.replyTo = this.messageReceiver;
        try {
            this.messageSender.send(obtain);
        }
        catch (RemoteException ex) {
            this.cleanup();
        }
    }
    
    public void bind() {
        final Intent tokenRefreshIntent = NativeProtocol.createTokenRefreshIntent(Session.getStaticContext());
        if (tokenRefreshIntent != null && Session.staticContext.bindService(tokenRefreshIntent, (ServiceConnection)new Session$TokenRefreshRequest(this.this$0), 1)) {
            this.this$0.setLastAttemptedTokenExtendDate(new Date());
            return;
        }
        this.cleanup();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.messageSender = new Messenger(binder);
        this.refreshToken();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.cleanup();
        Session.staticContext.unbindService((ServiceConnection)this);
    }
}
