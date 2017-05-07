// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.os.Handler;
import android.content.IntentFilter;
import android.content.SharedPreferences$Editor;
import android.content.BroadcastReceiver;
import java.sql.Timestamp;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.content.Intent;
import java.util.Set;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.content.Context;

public final class GCMRegistrar
{
    private static final String BACKOFF_MS = "backoff_ms";
    private static final int DEFAULT_BACKOFF_MS = 3000;
    public static final long DEFAULT_ON_SERVER_LIFESPAN_MS = 604800000L;
    private static final String GSF_PACKAGE = "com.google.android.gsf";
    private static final String PREFERENCES = "com.google.android.gcm";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_ON_SERVER = "onServer";
    private static final String PROPERTY_ON_SERVER_EXPIRATION_TIME = "onServerExpirationTime";
    private static final String PROPERTY_ON_SERVER_LIFESPAN = "onServerLifeSpan";
    private static final String PROPERTY_REG_ID = "regId";
    private static final String TAG = "GCMRegistrar";
    private static GCMBroadcastReceiver sRetryReceiver;
    private static String sRetryReceiverClassName;
    
    private GCMRegistrar() {
        throw new UnsupportedOperationException();
    }
    
    public static void checkDevice(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT < 8) {
            throw new UnsupportedOperationException("Device must be at least API Level 8 (instead of " + sdk_INT + ")");
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo("com.google.android.gsf", 0);
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new UnsupportedOperationException("Device does not have package com.google.android.gsf");
        }
    }
    
    public static void checkManifest(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     4: astore          5
        //     6: aload_0        
        //     7: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    10: astore_3       
        //    11: new             Ljava/lang/StringBuilder;
        //    14: dup            
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: aload_3        
        //    19: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    22: ldc             ".permission.C2D_MESSAGE"
        //    24: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    27: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    30: astore          4
        //    32: aload           5
        //    34: aload           4
        //    36: sipush          4096
        //    39: invokevirtual   android/content/pm/PackageManager.getPermissionInfo:(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
        //    42: pop            
        //    43: aload           5
        //    45: aload_3        
        //    46: iconst_2       
        //    47: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    50: astore          4
        //    52: aload           4
        //    54: getfield        android/content/pm/PackageInfo.receivers:[Landroid/content/pm/ActivityInfo;
        //    57: astore          4
        //    59: aload           4
        //    61: ifnull          70
        //    64: aload           4
        //    66: arraylength    
        //    67: ifne            154
        //    70: new             Ljava/lang/IllegalStateException;
        //    73: dup            
        //    74: new             Ljava/lang/StringBuilder;
        //    77: dup            
        //    78: invokespecial   java/lang/StringBuilder.<init>:()V
        //    81: ldc             "No receiver for package "
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload_3        
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    96: athrow         
        //    97: astore_0       
        //    98: new             Ljava/lang/IllegalStateException;
        //   101: dup            
        //   102: new             Ljava/lang/StringBuilder;
        //   105: dup            
        //   106: invokespecial   java/lang/StringBuilder.<init>:()V
        //   109: ldc             "Application does not define permission "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: aload           4
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   125: athrow         
        //   126: astore_0       
        //   127: new             Ljava/lang/IllegalStateException;
        //   130: dup            
        //   131: new             Ljava/lang/StringBuilder;
        //   134: dup            
        //   135: invokespecial   java/lang/StringBuilder.<init>:()V
        //   138: ldc             "Could not get receivers for package "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload_3        
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   153: athrow         
        //   154: ldc             "GCMRegistrar"
        //   156: iconst_2       
        //   157: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   160: ifeq            199
        //   163: ldc             "GCMRegistrar"
        //   165: new             Ljava/lang/StringBuilder;
        //   168: dup            
        //   169: invokespecial   java/lang/StringBuilder.<init>:()V
        //   172: ldc             "number of receivers for "
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: aload_3        
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: ldc             ": "
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: aload           4
        //   188: arraylength    
        //   189: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   192: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   195: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   198: pop            
        //   199: new             Ljava/util/HashSet;
        //   202: dup            
        //   203: invokespecial   java/util/HashSet.<init>:()V
        //   206: astore_3       
        //   207: aload           4
        //   209: arraylength    
        //   210: istore_2       
        //   211: iconst_0       
        //   212: istore_1       
        //   213: iload_1        
        //   214: iload_2        
        //   215: if_icmpge       256
        //   218: aload           4
        //   220: iload_1        
        //   221: aaload         
        //   222: astore          5
        //   224: ldc             "com.google.android.c2dm.permission.SEND"
        //   226: aload           5
        //   228: getfield        android/content/pm/ActivityInfo.permission:Ljava/lang/String;
        //   231: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   234: ifeq            249
        //   237: aload_3        
        //   238: aload           5
        //   240: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //   243: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   248: pop            
        //   249: iload_1        
        //   250: iconst_1       
        //   251: iadd           
        //   252: istore_1       
        //   253: goto            213
        //   256: aload_3        
        //   257: invokeinterface java/util/Set.isEmpty:()Z
        //   262: ifeq            275
        //   265: new             Ljava/lang/IllegalStateException;
        //   268: dup            
        //   269: ldc             "No receiver allowed to receive com.google.android.c2dm.permission.SEND"
        //   271: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   274: athrow         
        //   275: aload_0        
        //   276: aload_3        
        //   277: ldc             "com.google.android.c2dm.intent.REGISTRATION"
        //   279: invokestatic    com/google/android/gcm/GCMRegistrar.checkReceiver:(Landroid/content/Context;Ljava/util/Set;Ljava/lang/String;)V
        //   282: aload_0        
        //   283: aload_3        
        //   284: ldc             "com.google.android.c2dm.intent.RECEIVE"
        //   286: invokestatic    com/google/android/gcm/GCMRegistrar.checkReceiver:(Landroid/content/Context;Ljava/util/Set;Ljava/lang/String;)V
        //   289: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  32     43     97     126    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  43     52     126    154    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    private static void checkReceiver(final Context context, final Set<String> set, String name) {
        final PackageManager packageManager = context.getPackageManager();
        final String packageName = context.getPackageName();
        final Intent intent = new Intent(name);
        intent.setPackage(packageName);
        final List queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 32);
        if (queryBroadcastReceivers.isEmpty()) {
            throw new IllegalStateException("No receivers for action " + name);
        }
        if (Log.isLoggable("GCMRegistrar", 2)) {
            Log.v("GCMRegistrar", "Found " + queryBroadcastReceivers.size() + " receivers for action " + name);
        }
        final Iterator<ResolveInfo> iterator = queryBroadcastReceivers.iterator();
        while (iterator.hasNext()) {
            name = iterator.next().activityInfo.name;
            if (!set.contains(name)) {
                throw new IllegalStateException("Receiver " + name + " is not set with permission " + "com.google.android.c2dm.permission.SEND");
            }
        }
    }
    
    static String clearRegistrationId(final Context context) {
        return setRegistrationId(context, "");
    }
    
    private static int getAppVersion(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new RuntimeException("Coult not get package name: " + ex);
        }
    }
    
    static int getBackoff(final Context context) {
        return getGCMPreferences(context).getInt("backoff_ms", 3000);
    }
    
    static String getFlatSenderIds(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }
    
    private static SharedPreferences getGCMPreferences(final Context context) {
        return context.getSharedPreferences("com.google.android.gcm", 0);
    }
    
    public static long getRegisterOnServerLifespan(final Context context) {
        return getGCMPreferences(context).getLong("onServerLifeSpan", 604800000L);
    }
    
    public static String getRegistrationId(final Context context) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final String string = gcmPreferences.getString("regId", "");
        final int int1 = gcmPreferences.getInt("appVersion", Integer.MIN_VALUE);
        final int appVersion = getAppVersion(context);
        String s = string;
        if (int1 != Integer.MIN_VALUE) {
            s = string;
            if (int1 != appVersion) {
                Log.v("GCMRegistrar", "App version changed from " + int1 + " to " + appVersion + "; resetting registration id");
                clearRegistrationId(context);
                s = "";
            }
        }
        return s;
    }
    
    static void internalRegister(final Context context, final String... array) {
        final String flatSenderIds = getFlatSenderIds(array);
        Log.v("GCMRegistrar", "Registering app " + context.getPackageName() + " of senders " + flatSenderIds);
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        intent.putExtra("sender", flatSenderIds);
        context.startService(intent);
    }
    
    static void internalUnregister(final Context context) {
        Log.v("GCMRegistrar", "Unregistering app " + context.getPackageName());
        final Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        context.startService(intent);
    }
    
    public static boolean isRegistered(final Context context) {
        return getRegistrationId(context).length() > 0;
    }
    
    public static boolean isRegisteredOnServer(final Context context) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final boolean boolean1 = gcmPreferences.getBoolean("onServer", false);
        Log.v("GCMRegistrar", "Is registered on server: " + boolean1);
        boolean b = boolean1;
        if (boolean1) {
            final long long1 = gcmPreferences.getLong("onServerExpirationTime", -1L);
            b = boolean1;
            if (System.currentTimeMillis() > long1) {
                Log.v("GCMRegistrar", "flag expired on: " + new Timestamp(long1));
                b = false;
            }
        }
        return b;
    }
    
    public static void onDestroy(final Context context) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sRetryReceiver != null) {
                Log.v("GCMRegistrar", "Unregistering receiver");
                context.unregisterReceiver((BroadcastReceiver)GCMRegistrar.sRetryReceiver);
                GCMRegistrar.sRetryReceiver = null;
            }
        }
    }
    
    public static void register(final Context context, final String... array) {
        resetBackoff(context);
        internalRegister(context, array);
    }
    
    static void resetBackoff(final Context context) {
        Log.d("GCMRegistrar", "resetting backoff for " + context.getPackageName());
        setBackoff(context, 3000);
    }
    
    static void setBackoff(final Context context, final int n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putInt("backoff_ms", n);
        edit.commit();
    }
    
    public static void setRegisterOnServerLifespan(final Context context, final long n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putLong("onServerLifeSpan", n);
        edit.commit();
    }
    
    public static void setRegisteredOnServer(final Context context, final boolean b) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putBoolean("onServer", b);
        final long n = System.currentTimeMillis() + getRegisterOnServerLifespan(context);
        Log.v("GCMRegistrar", "Setting registeredOnServer status as " + b + " until " + new Timestamp(n));
        edit.putLong("onServerExpirationTime", n);
        edit.commit();
    }
    
    static String setRegistrationId(final Context context, final String s) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final String string = gcmPreferences.getString("regId", "");
        final int appVersion = getAppVersion(context);
        Log.v("GCMRegistrar", "Saving regId on app version " + appVersion);
        final SharedPreferences$Editor edit = gcmPreferences.edit();
        edit.putString("regId", s);
        edit.putInt("appVersion", appVersion);
        edit.commit();
        return string;
    }
    
    static void setRetryBroadcastReceiver(final Context context) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sRetryReceiver == null) {
                if (GCMRegistrar.sRetryReceiverClassName == null) {
                    Log.e("GCMRegistrar", "internal error: retry receiver class not set yet");
                    GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                }
                else {
                    try {
                        GCMRegistrar.sRetryReceiver = (GCMBroadcastReceiver)Class.forName(GCMRegistrar.sRetryReceiverClassName).newInstance();
                    }
                    catch (Exception ex) {
                        Log.e("GCMRegistrar", "Could not create instance of " + GCMRegistrar.sRetryReceiverClassName + ". Using " + GCMBroadcastReceiver.class.getName() + " directly.");
                        GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                    }
                }
                final String packageName = context.getPackageName();
                final IntentFilter intentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
                intentFilter.addCategory(packageName);
                final String string = packageName + ".permission.C2D_MESSAGE";
                Log.v("GCMRegistrar", "Registering receiver");
                context.registerReceiver((BroadcastReceiver)GCMRegistrar.sRetryReceiver, intentFilter, string, (Handler)null);
            }
        }
    }
    
    static void setRetryReceiverClassName(final String sRetryReceiverClassName) {
        Log.v("GCMRegistrar", "Setting the name of retry receiver class to " + sRetryReceiverClassName);
        GCMRegistrar.sRetryReceiverClassName = sRetryReceiverClassName;
    }
    
    public static void unregister(final Context context) {
        resetBackoff(context);
        internalUnregister(context);
    }
}
