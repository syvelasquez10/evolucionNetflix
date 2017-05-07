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
import android.util.Log;
import java.util.concurrent.TimeUnit;
import android.os.PowerManager$WakeLock;
import java.util.Random;
import android.app.IntentService;

public abstract class GCMBaseIntentService extends IntentService
{
    private static final String EXTRA_TOKEN = "token";
    private static final Object LOCK;
    private static final int MAX_BACKOFF_MS;
    public static final String TAG = "GCMBaseIntentService";
    private static final String TOKEN;
    private static final String WAKELOCK_KEY = "GCM_LIB";
    private static int sCounter;
    private static final Random sRandom;
    private static PowerManager$WakeLock sWakeLock;
    private final String[] mSenderIds;
    
    static {
        LOCK = GCMBaseIntentService.class;
        GCMBaseIntentService.sCounter = 0;
        sRandom = new Random();
        MAX_BACKOFF_MS = (int)TimeUnit.SECONDS.toMillis(3600L);
        TOKEN = Long.toBinaryString(GCMBaseIntentService.sRandom.nextLong());
    }
    
    protected GCMBaseIntentService() {
        this(getName("DynamicSenderIds"), null);
    }
    
    private GCMBaseIntentService(final String s, final String[] mSenderIds) {
        super(s);
        this.mSenderIds = mSenderIds;
    }
    
    protected GCMBaseIntentService(final String... array) {
        this(getName(array), array);
    }
    
    private static String getName(String string) {
        string = "GCMIntentService-" + string + "-" + ++GCMBaseIntentService.sCounter;
        Log.v("GCMBaseIntentService", "Intent service name: " + string);
        return string;
    }
    
    private static String getName(final String[] array) {
        return getName(GCMRegistrar.getFlatSenderIds(array));
    }
    
    private void handleRegistration(final Context context, Intent intent) {
        final String stringExtra = intent.getStringExtra("registration_id");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("unregistered");
        Log.d("GCMBaseIntentService", "handleRegistration: registrationId = " + stringExtra + ", error = " + stringExtra2 + ", unregistered = " + stringExtra3);
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
            Log.d("GCMBaseIntentService", "Registration error: " + stringExtra2);
            if (!"SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
                this.onError(context, stringExtra2);
                return;
            }
            if (!this.onRecoverableError(context, stringExtra2)) {
                Log.d("GCMBaseIntentService", "Not retrying failed operation");
                return;
            }
            final int backoff = GCMRegistrar.getBackoff(context);
            final int n = backoff / 2 + GCMBaseIntentService.sRandom.nextInt(backoff);
            Log.d("GCMBaseIntentService", "Scheduling registration retry, backoff = " + n + " (" + backoff + ")");
            intent = new Intent("com.google.android.gcm.intent.RETRY");
            intent.putExtra("token", GCMBaseIntentService.TOKEN);
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
            Log.v("GCMBaseIntentService", "Acquiring wakelock");
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
    
    public final void onHandleIntent(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gcm/GCMBaseIntentService.getApplicationContext:()Landroid/content/Context;
        //     4: astore_3       
        //     5: aload_1        
        //     6: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //     9: astore          4
        //    11: aload           4
        //    13: ldc_w           "com.google.android.c2dm.intent.REGISTRATION"
        //    16: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    19: ifeq            62
        //    22: aload_3        
        //    23: invokestatic    com/google/android/gcm/GCMRegistrar.setRetryBroadcastReceiver:(Landroid/content/Context;)V
        //    26: aload_0        
        //    27: aload_3        
        //    28: aload_1        
        //    29: invokespecial   com/google/android/gcm/GCMBaseIntentService.handleRegistration:(Landroid/content/Context;Landroid/content/Intent;)V
        //    32: getstatic       com/google/android/gcm/GCMBaseIntentService.LOCK:Ljava/lang/Object;
        //    35: astore_1       
        //    36: aload_1        
        //    37: monitorenter   
        //    38: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //    41: ifnull          377
        //    44: ldc             "GCMBaseIntentService"
        //    46: ldc_w           "Releasing wakelock"
        //    49: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    52: pop            
        //    53: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //    56: invokevirtual   android/os/PowerManager$WakeLock.release:()V
        //    59: aload_1        
        //    60: monitorexit    
        //    61: return         
        //    62: aload           4
        //    64: ldc_w           "com.google.android.c2dm.intent.RECEIVE"
        //    67: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    70: ifeq            251
        //    73: aload_1        
        //    74: ldc_w           "message_type"
        //    77: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    80: astore          4
        //    82: aload           4
        //    84: ifnull          242
        //    87: aload           4
        //    89: ldc_w           "deleted_messages"
        //    92: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    95: ifeq            212
        //    98: aload_1        
        //    99: ldc_w           "total_deleted"
        //   102: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   105: astore_1       
        //   106: aload_1        
        //   107: ifnull          32
        //   110: aload_1        
        //   111: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   114: istore_2       
        //   115: ldc             "GCMBaseIntentService"
        //   117: new             Ljava/lang/StringBuilder;
        //   120: dup            
        //   121: invokespecial   java/lang/StringBuilder.<init>:()V
        //   124: ldc_w           "Received deleted messages notification: "
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: iload_2        
        //   131: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   134: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   137: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   140: pop            
        //   141: aload_0        
        //   142: aload_3        
        //   143: iload_2        
        //   144: invokevirtual   com/google/android/gcm/GCMBaseIntentService.onDeletedMessages:(Landroid/content/Context;I)V
        //   147: goto            32
        //   150: astore_3       
        //   151: ldc             "GCMBaseIntentService"
        //   153: new             Ljava/lang/StringBuilder;
        //   156: dup            
        //   157: invokespecial   java/lang/StringBuilder.<init>:()V
        //   160: ldc_w           "GCM returned invalid number of deleted messages: "
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: aload_1        
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   173: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   176: pop            
        //   177: goto            32
        //   180: astore_3       
        //   181: getstatic       com/google/android/gcm/GCMBaseIntentService.LOCK:Ljava/lang/Object;
        //   184: astore_1       
        //   185: aload_1        
        //   186: monitorenter   
        //   187: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //   190: ifnull          394
        //   193: ldc             "GCMBaseIntentService"
        //   195: ldc_w           "Releasing wakelock"
        //   198: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop            
        //   202: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //   205: invokevirtual   android/os/PowerManager$WakeLock.release:()V
        //   208: aload_1        
        //   209: monitorexit    
        //   210: aload_3        
        //   211: athrow         
        //   212: ldc             "GCMBaseIntentService"
        //   214: new             Ljava/lang/StringBuilder;
        //   217: dup            
        //   218: invokespecial   java/lang/StringBuilder.<init>:()V
        //   221: ldc_w           "Received unknown special message: "
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: aload           4
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   235: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   238: pop            
        //   239: goto            32
        //   242: aload_0        
        //   243: aload_3        
        //   244: aload_1        
        //   245: invokevirtual   com/google/android/gcm/GCMBaseIntentService.onMessage:(Landroid/content/Context;Landroid/content/Intent;)V
        //   248: goto            32
        //   251: aload           4
        //   253: ldc             "com.google.android.gcm.intent.RETRY"
        //   255: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   258: ifeq            32
        //   261: aload_1        
        //   262: ldc             "token"
        //   264: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   267: astore_1       
        //   268: getstatic       com/google/android/gcm/GCMBaseIntentService.TOKEN:Ljava/lang/String;
        //   271: aload_1        
        //   272: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   275: ifne            351
        //   278: ldc             "GCMBaseIntentService"
        //   280: new             Ljava/lang/StringBuilder;
        //   283: dup            
        //   284: invokespecial   java/lang/StringBuilder.<init>:()V
        //   287: ldc_w           "Received invalid token: "
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: aload_1        
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   300: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   303: pop            
        //   304: getstatic       com/google/android/gcm/GCMBaseIntentService.LOCK:Ljava/lang/Object;
        //   307: astore_1       
        //   308: aload_1        
        //   309: monitorenter   
        //   310: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //   313: ifnull          339
        //   316: ldc             "GCMBaseIntentService"
        //   318: ldc_w           "Releasing wakelock"
        //   321: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   324: pop            
        //   325: getstatic       com/google/android/gcm/GCMBaseIntentService.sWakeLock:Landroid/os/PowerManager$WakeLock;
        //   328: invokevirtual   android/os/PowerManager$WakeLock.release:()V
        //   331: aload_1        
        //   332: monitorexit    
        //   333: return         
        //   334: astore_3       
        //   335: aload_1        
        //   336: monitorexit    
        //   337: aload_3        
        //   338: athrow         
        //   339: ldc             "GCMBaseIntentService"
        //   341: ldc_w           "Wakelock reference is null"
        //   344: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   347: pop            
        //   348: goto            331
        //   351: aload_3        
        //   352: invokestatic    com/google/android/gcm/GCMRegistrar.isRegistered:(Landroid/content/Context;)Z
        //   355: ifeq            365
        //   358: aload_3        
        //   359: invokestatic    com/google/android/gcm/GCMRegistrar.internalUnregister:(Landroid/content/Context;)V
        //   362: goto            32
        //   365: aload_3        
        //   366: aload_0        
        //   367: aload_3        
        //   368: invokevirtual   com/google/android/gcm/GCMBaseIntentService.getSenderIds:(Landroid/content/Context;)[Ljava/lang/String;
        //   371: invokestatic    com/google/android/gcm/GCMRegistrar.internalRegister:(Landroid/content/Context;[Ljava/lang/String;)V
        //   374: goto            32
        //   377: ldc             "GCMBaseIntentService"
        //   379: ldc_w           "Wakelock reference is null"
        //   382: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   385: pop            
        //   386: goto            59
        //   389: astore_3       
        //   390: aload_1        
        //   391: monitorexit    
        //   392: aload_3        
        //   393: athrow         
        //   394: ldc             "GCMBaseIntentService"
        //   396: ldc_w           "Wakelock reference is null"
        //   399: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   402: pop            
        //   403: goto            208
        //   406: astore_3       
        //   407: aload_1        
        //   408: monitorexit    
        //   409: aload_3        
        //   410: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      32     180    411    Any
        //  38     59     389    394    Any
        //  59     61     389    394    Any
        //  62     82     180    411    Any
        //  87     106    180    411    Any
        //  110    147    150    180    Ljava/lang/NumberFormatException;
        //  110    147    180    411    Any
        //  151    177    180    411    Any
        //  187    208    406    411    Any
        //  208    210    406    411    Any
        //  212    239    180    411    Any
        //  242    248    180    411    Any
        //  251    304    180    411    Any
        //  310    331    334    339    Any
        //  331    333    334    339    Any
        //  335    337    334    339    Any
        //  339    348    334    339    Any
        //  351    362    180    411    Any
        //  365    374    180    411    Any
        //  377    386    389    394    Any
        //  390    392    389    394    Any
        //  394    403    406    411    Any
        //  407    409    406    411    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected abstract void onMessage(final Context p0, final Intent p1);
    
    protected boolean onRecoverableError(final Context context, final String s) {
        return true;
    }
    
    protected abstract void onRegistered(final Context p0, final String p1);
    
    protected abstract void onUnregistered(final Context p0, final String p1);
}
