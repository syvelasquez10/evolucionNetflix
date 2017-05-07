// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gcm;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Message;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.Messenger;
import android.os.Handler;
import android.content.Intent;
import java.util.concurrent.BlockingQueue;
import android.app.PendingIntent;
import android.content.Context;

public class GoogleCloudMessaging
{
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    static GoogleCloudMessaging xf;
    private Context eh;
    private PendingIntent xg;
    final BlockingQueue<Intent> xh;
    private Handler xi;
    private Messenger xj;
    
    public GoogleCloudMessaging() {
        this.xh = new LinkedBlockingQueue<Intent>();
        this.xi = new Handler(Looper.getMainLooper()) {
            public void handleMessage(final Message message) {
                GoogleCloudMessaging.this.xh.add((Intent)message.obj);
            }
        };
        this.xj = new Messenger(this.xi);
    }
    
    private void b(final String... array) {
        final String c = this.c(array);
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("google.messenger", (Parcelable)this.xj);
        this.c(intent);
        intent.putExtra("sender", c);
        this.eh.startService(intent);
    }
    
    private void dz() {
        final Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gms");
        this.xh.clear();
        intent.putExtra("google.messenger", (Parcelable)this.xj);
        this.c(intent);
        this.eh.startService(intent);
    }
    
    public static GoogleCloudMessaging getInstance(final Context eh) {
        synchronized (GoogleCloudMessaging.class) {
            if (GoogleCloudMessaging.xf == null) {
                GoogleCloudMessaging.xf = new GoogleCloudMessaging();
                GoogleCloudMessaging.xf.eh = eh;
            }
            return GoogleCloudMessaging.xf;
        }
    }
    
    String c(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }
    
    void c(final Intent intent) {
        synchronized (this) {
            if (this.xg == null) {
                this.xg = PendingIntent.getBroadcast(this.eh, 0, new Intent(), 0);
            }
            intent.putExtra("app", (Parcelable)this.xg);
        }
    }
    
    public void close() {
        this.dA();
    }
    
    void dA() {
        synchronized (this) {
            if (this.xg != null) {
                this.xg.cancel();
                this.xg = null;
            }
        }
    }
    
    public String getMessageType(final Intent intent) {
        String stringExtra;
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = null;
        }
        else if ((stringExtra = intent.getStringExtra("message_type")) == null) {
            return "gcm";
        }
        return stringExtra;
    }
    
    public String register(final String... array) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.xh.clear();
        this.b(array);
        Intent intent;
        try {
            intent = this.xh.poll(5000L, TimeUnit.MILLISECONDS);
            if (intent == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        }
        catch (InterruptedException ex) {
            throw new IOException(ex.getMessage());
        }
        final String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra != null) {
            return stringExtra;
        }
        intent.getStringExtra("error");
        final String stringExtra2 = intent.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    
    public void send(final String s, final String s2, final long n, final Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (s == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        final Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.putExtras(bundle);
        this.c(intent);
        intent.putExtra("google.to", s);
        intent.putExtra("google.message_id", s2);
        intent.putExtra("google.ttl", Long.toString(n));
        this.eh.sendOrderedBroadcast(intent, (String)null);
    }
    
    public void send(final String s, final String s2, final Bundle bundle) throws IOException {
        this.send(s, s2, -1L, bundle);
    }
    
    public void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.dz();
        Intent intent;
        try {
            intent = this.xh.poll(5000L, TimeUnit.MILLISECONDS);
            if (intent == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        }
        catch (InterruptedException ex) {
            throw new IOException(ex.getMessage());
        }
        if (intent.getStringExtra("unregistered") != null) {
            return;
        }
        final String stringExtra = intent.getStringExtra("error");
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}
