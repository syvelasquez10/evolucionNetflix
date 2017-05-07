// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.os.PowerManager;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import android.os.PowerManager$WakeLock;
import java.util.Random;
import android.app.IntentService;

public abstract class GCMBaseIntentService extends IntentService
{
    private static final Object LOCK;
    private static final int MAX_BACKOFF_MS;
    public static final String TAG = "GCMBaseIntentService";
    private static final String WAKELOCK_KEY = "GCM_LIB";
    private static int sCounter;
    private static final Random sRandom;
    private static PowerManager$WakeLock sWakeLock;
    private final GCMLogger mLogger;
    private final String[] mSenderIds;
    
    static {
        LOCK = GCMBaseIntentService.class;
        GCMBaseIntentService.sCounter = 0;
        sRandom = new Random();
        MAX_BACKOFF_MS = (int)TimeUnit.SECONDS.toMillis(3600L);
    }
    
    protected GCMBaseIntentService() {
        this(getName("DynamicSenderIds"), null);
    }
    
    private GCMBaseIntentService(final String s, final String[] mSenderIds) {
        super(s);
        this.mLogger = new GCMLogger("GCMBaseIntentService", "[" + this.getClass().getName() + "]: ");
        this.mSenderIds = mSenderIds;
        this.mLogger.log(2, "Intent service name: %s", s);
    }
    
    protected GCMBaseIntentService(final String... array) {
        this(getName(array), array);
    }
    
    private static String getName(final String s) {
        return "GCMIntentService-" + s + "-" + ++GCMBaseIntentService.sCounter;
    }
    
    private static String getName(final String[] array) {
        return getName(GCMRegistrar.getFlatSenderIds(array));
    }
    
    private void handleRegistration(final Context context, Intent intent) {
        GCMRegistrar.cancelAppPendingIntent();
        final String stringExtra = intent.getStringExtra("registration_id");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("unregistered");
        this.mLogger.log(3, "handleRegistration: registrationId = %s, error = %s, unregistered = %s", stringExtra, stringExtra2, stringExtra3);
        if (stringExtra != null) {
            GCMRegistrar.resetBackoff(context);
            GCMRegistrar.setRegistrationId(context, stringExtra);
            this.onRegistered(context, stringExtra);
        }
        else {
            if (stringExtra3 != null) {
                GCMRegistrar.resetBackoff(context);
                this.onUnregistered(context, GCMRegistrar.clearRegistrationId(context));
                return;
            }
            if (!"SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
                this.onError(context, stringExtra2);
                return;
            }
            if (!this.onRecoverableError(context, stringExtra2)) {
                this.mLogger.log(2, "Not retrying failed operation", new Object[0]);
                return;
            }
            final int backoff = GCMRegistrar.getBackoff(context);
            final int n = GCMBaseIntentService.sRandom.nextInt(backoff) + backoff / 2;
            this.mLogger.log(3, "Scheduling registration retry, backoff = %d (%d)", n, backoff);
            intent = new Intent("com.google.android.gcm.intent.RETRY");
            intent.setPackage(context.getPackageName());
            ((AlarmManager)context.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + n, PendingIntent.getBroadcast(context, 0, intent, 0));
            if (backoff < GCMBaseIntentService.MAX_BACKOFF_MS) {
                GCMRegistrar.setBackoff(context, backoff * 2);
            }
        }
    }
    
    static void runIntentInService(final Context context, final Intent intent, final String s) {
        synchronized (GCMBaseIntentService.LOCK) {
            if (GCMBaseIntentService.sWakeLock == null) {
                GCMBaseIntentService.sWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, "GCM_LIB");
            }
            // monitorexit(GCMBaseIntentService.LOCK)
            GCMBaseIntentService.sWakeLock.acquire();
            intent.setClassName(context, s);
            context.startService(intent);
        }
    }
    
    protected String[] getSenderIds(final Context context) {
        if (this.mSenderIds == null) {
            throw new IllegalStateException("sender id not set on constructor");
        }
        return this.mSenderIds;
    }
    
    protected void onDeletedMessages(final Context context, final int n) {
    }
    
    protected abstract void onError(final Context p0, final String p1);
    
    public final void onHandleIntent(Intent intent) {
        Context applicationContext;
        String s;
        int int1;
        final Context context;
        Block_29_Outer:Label_0386_Outer:
        while (true) {
            Label_0265: {
                try {
                    applicationContext = this.getApplicationContext();
                    if (intent == null) {
                        synchronized (GCMBaseIntentService.LOCK) {
                            if (GCMBaseIntentService.sWakeLock != null && GCMBaseIntentService.sWakeLock.isHeld()) {
                                GCMBaseIntentService.sWakeLock.release();
                            }
                            else {
                                this.mLogger.log(6, "Wakelock reference is null", new Object[0]);
                            }
                            return;
                        }
                    }
                    s = intent.getAction();
                    Label_0125: {
                        if (!s.equals("com.google.android.c2dm.intent.REGISTRATION")) {
                            break Label_0125;
                        }
                        GCMRegistrar.setRetryBroadcastReceiver(applicationContext);
                        this.handleRegistration(applicationContext, intent);
                        synchronized (GCMBaseIntentService.LOCK) {
                            if (GCMBaseIntentService.sWakeLock != null && GCMBaseIntentService.sWakeLock.isHeld()) {
                                GCMBaseIntentService.sWakeLock.release();
                                return;
                            }
                            break Label_0265;
                        }
                    }
                    if (!s.equals("com.google.android.c2dm.intent.RECEIVE")) {
                        break Label_0265;
                    }
                    s = intent.getStringExtra("message_type");
                    if (s == null) {
                        break Label_0265;
                    }
                    if (!s.equals("deleted_messages")) {
                        break Label_0265;
                    }
                    intent = (Intent)intent.getStringExtra("total_deleted");
                    if (intent != null) {
                        try {
                            int1 = Integer.parseInt((String)intent);
                            this.mLogger.log(2, "Received notification for %d deletedmessages", int1);
                            this.onDeletedMessages(applicationContext, int1);
                        }
                        catch (NumberFormatException ex) {
                            this.mLogger.log(6, "GCM returned invalid number of deleted messages (%d)", intent);
                        }
                    }
                    continue;
                }
                finally {
                    intent = (Intent)GCMBaseIntentService.LOCK;
                    EndFinally_4: {
                        synchronized (intent) {
                            if (GCMBaseIntentService.sWakeLock != null && GCMBaseIntentService.sWakeLock.isHeld()) {
                                GCMBaseIntentService.sWakeLock.release();
                            }
                            else {
                                this.mLogger.log(6, "Wakelock reference is null", new Object[0]);
                            }
                            // monitorexit(intent)
                            break EndFinally_4;
                            // iftrue(Label_0090:, !s.equals((Object)"com.google.android.gcm.intent.RETRY"))
                            // iftrue(Label_0419:, !GCMRegistrar.isRegistered(context))
                            Block_26: {
                                while (true) {
                                    GCMRegistrar.internalUnregister(context);
                                    continue Block_29_Outer;
                                    break Block_26;
                                    this.mLogger.log(6, "Wakelock reference is null", new Object[0]);
                                    return;
                                    this.mLogger.log(6, "Received unknown special message: %s", s);
                                    continue Block_29_Outer;
                                    Label_0405: {
                                        continue Label_0386_Outer;
                                    }
                                }
                                this.onMessage(context, intent);
                                continue Block_29_Outer;
                            }
                            intent = (Intent)intent.getPackage();
                            // iftrue(Label_0405:, intent != null && intent.equals((Object)this.getApplicationContext().getPackageName()))
                            while (true) {
                                Label_0331: {
                                    break Label_0331;
                                    this.mLogger.log(6, "Wakelock reference is null", new Object[0]);
                                    return;
                                }
                                this.mLogger.log(6, "Ignoring retry intent from another package (%s)", intent);
                                synchronized (GCMBaseIntentService.LOCK) {
                                    if (GCMBaseIntentService.sWakeLock != null && GCMBaseIntentService.sWakeLock.isHeld()) {
                                        GCMBaseIntentService.sWakeLock.release();
                                        return;
                                    }
                                }
                                continue;
                            }
                            Label_0419: {
                                GCMRegistrar.internalRegister(context, this.getSenderIds(context));
                            }
                        }
                    }
                }
            }
            break;
        }
    }
    
    protected abstract void onMessage(final Context p0, final Intent p1);
    
    protected boolean onRecoverableError(final Context context, final String s) {
        return true;
    }
    
    protected abstract void onRegistered(final Context p0, final String p1);
    
    protected abstract void onUnregistered(final Context p0, final String p1);
}
