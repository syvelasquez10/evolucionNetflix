// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gcm;

import java.util.concurrent.TimeUnit;
import android.os.Parcelable;
import java.io.IOException;
import android.os.Bundle;
import android.os.Message;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;
import android.os.Messenger;
import android.os.Handler;
import android.content.Intent;
import java.util.concurrent.BlockingQueue;
import android.app.PendingIntent;

public class GoogleCloudMessaging
{
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    static GoogleCloudMessaging Nq;
    private PendingIntent Nr;
    final BlockingQueue<Intent> Ns;
    private Handler Nt;
    private Messenger Nu;
    private Context kI;
    
    public GoogleCloudMessaging() {
        this.Ns = new LinkedBlockingQueue<Intent>();
        this.Nt = new Handler(Looper.getMainLooper()) {
            public void handleMessage(final Message message) {
                GoogleCloudMessaging.this.Ns.add((Intent)message.obj);
            }
        };
        this.Nu = new Messenger(this.Nt);
    }
    
    private void a(final String s, final String s2, final long n, final int n2, final Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (s == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        final Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.putExtras(bundle);
        this.c(intent);
        intent.setPackage("com.google.android.gms");
        intent.putExtra("google.to", s);
        intent.putExtra("google.message_id", s2);
        intent.putExtra("google.ttl", Long.toString(n));
        intent.putExtra("google.delay", Integer.toString(n2));
        this.kI.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
    
    private void c(final String... array) {
        final String d = this.d(array);
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("google.messenger", (Parcelable)this.Nu);
        this.c(intent);
        intent.putExtra("sender", d);
        this.kI.startService(intent);
    }
    
    public static GoogleCloudMessaging getInstance(final Context ki) {
        synchronized (GoogleCloudMessaging.class) {
            if (GoogleCloudMessaging.Nq == null) {
                GoogleCloudMessaging.Nq = new GoogleCloudMessaging();
                GoogleCloudMessaging.Nq.kI = ki;
            }
            return GoogleCloudMessaging.Nq;
        }
    }
    
    private void hL() {
        final Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gms");
        this.Ns.clear();
        intent.putExtra("google.messenger", (Parcelable)this.Nu);
        this.c(intent);
        this.kI.startService(intent);
    }
    
    void c(final Intent intent) {
        synchronized (this) {
            if (this.Nr == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.Nr = PendingIntent.getBroadcast(this.kI, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)this.Nr);
        }
    }
    
    public void close() {
        this.hM();
    }
    
    String d(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
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
    
    void hM() {
        synchronized (this) {
            if (this.Nr != null) {
                this.Nr.cancel();
                this.Nr = null;
            }
        }
    }
    
    public String register(final String... array) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.Ns.clear();
        this.c(array);
        Intent intent;
        try {
            intent = this.Ns.poll(5000L, TimeUnit.MILLISECONDS);
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
        this.a(s, s2, n, -1, bundle);
    }
    
    public void send(final String s, final String s2, final Bundle bundle) throws IOException {
        this.send(s, s2, -1L, bundle);
    }
    
    public void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.hL();
        Intent intent;
        try {
            intent = this.Ns.poll(5000L, TimeUnit.MILLISECONDS);
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
