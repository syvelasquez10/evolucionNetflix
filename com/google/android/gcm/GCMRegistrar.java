// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.content.IntentFilter;
import android.os.Parcelable;
import android.content.SharedPreferences$Editor;
import android.content.BroadcastReceiver;
import android.util.Log;
import java.sql.Timestamp;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import java.util.Set;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.content.Context;
import android.app.PendingIntent;

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
    private static PendingIntent sAppPendingIntent;
    private static GCMBroadcastReceiver sRetryReceiver;
    private static String sRetryReceiverClassName;
    private static Context sRetryReceiverContext;
    
    private GCMRegistrar() {
        throw new UnsupportedOperationException();
    }
    
    static void cancelAppPendingIntent() {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sAppPendingIntent != null) {
                GCMRegistrar.sAppPendingIntent.cancel();
                GCMRegistrar.sAppPendingIntent = null;
            }
        }
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
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     6: astore          5
        //     8: aload_0        
        //     9: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    12: astore_3       
        //    13: new             Ljava/lang/StringBuilder;
        //    16: dup            
        //    17: invokespecial   java/lang/StringBuilder.<init>:()V
        //    20: aload_3        
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: ldc             ".permission.C2D_MESSAGE"
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: astore          4
        //    34: aload           5
        //    36: aload           4
        //    38: sipush          4096
        //    41: invokevirtual   android/content/pm/PackageManager.getPermissionInfo:(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
        //    44: pop            
        //    45: aload           5
        //    47: aload_3        
        //    48: iconst_2       
        //    49: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    52: astore          4
        //    54: aload           4
        //    56: getfield        android/content/pm/PackageInfo.receivers:[Landroid/content/pm/ActivityInfo;
        //    59: astore          4
        //    61: aload           4
        //    63: ifnull          72
        //    66: aload           4
        //    68: arraylength    
        //    69: ifne            156
        //    72: new             Ljava/lang/IllegalStateException;
        //    75: dup            
        //    76: new             Ljava/lang/StringBuilder;
        //    79: dup            
        //    80: invokespecial   java/lang/StringBuilder.<init>:()V
        //    83: ldc             "No receiver for package "
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: aload_3        
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    92: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    95: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    98: athrow         
        //    99: astore_0       
        //   100: new             Ljava/lang/IllegalStateException;
        //   103: dup            
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: ldc             "Application does not define permission "
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: aload           4
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: astore_0       
        //   129: new             Ljava/lang/IllegalStateException;
        //   132: dup            
        //   133: new             Ljava/lang/StringBuilder;
        //   136: dup            
        //   137: invokespecial   java/lang/StringBuilder.<init>:()V
        //   140: ldc             "Could not get receivers for package "
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: aload_3        
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   152: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   155: athrow         
        //   156: aload_0        
        //   157: iconst_2       
        //   158: ldc             "number of receivers for %s: %d"
        //   160: iconst_2       
        //   161: anewarray       Ljava/lang/Object;
        //   164: dup            
        //   165: iconst_0       
        //   166: aload_3        
        //   167: aastore        
        //   168: dup            
        //   169: iconst_1       
        //   170: aload           4
        //   172: arraylength    
        //   173: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   176: aastore        
        //   177: invokestatic    com/google/android/gcm/GCMRegistrar.log:(Landroid/content/Context;ILjava/lang/String;[Ljava/lang/Object;)V
        //   180: new             Ljava/util/HashSet;
        //   183: dup            
        //   184: invokespecial   java/util/HashSet.<init>:()V
        //   187: astore_3       
        //   188: aload           4
        //   190: arraylength    
        //   191: istore_2       
        //   192: iload_1        
        //   193: iload_2        
        //   194: if_icmpge       235
        //   197: aload           4
        //   199: iload_1        
        //   200: aaload         
        //   201: astore          5
        //   203: ldc             "com.google.android.c2dm.permission.SEND"
        //   205: aload           5
        //   207: getfield        android/content/pm/ActivityInfo.permission:Ljava/lang/String;
        //   210: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   213: ifeq            228
        //   216: aload_3        
        //   217: aload           5
        //   219: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //   222: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   227: pop            
        //   228: iload_1        
        //   229: iconst_1       
        //   230: iadd           
        //   231: istore_1       
        //   232: goto            192
        //   235: aload_3        
        //   236: invokeinterface java/util/Set.isEmpty:()Z
        //   241: ifeq            254
        //   244: new             Ljava/lang/IllegalStateException;
        //   247: dup            
        //   248: ldc             "No receiver allowed to receive com.google.android.c2dm.permission.SEND"
        //   250: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   253: athrow         
        //   254: aload_0        
        //   255: aload_3        
        //   256: ldc             "com.google.android.c2dm.intent.REGISTRATION"
        //   258: invokestatic    com/google/android/gcm/GCMRegistrar.checkReceiver:(Landroid/content/Context;Ljava/util/Set;Ljava/lang/String;)V
        //   261: aload_0        
        //   262: aload_3        
        //   263: ldc             "com.google.android.c2dm.intent.RECEIVE"
        //   265: invokestatic    com/google/android/gcm/GCMRegistrar.checkReceiver:(Landroid/content/Context;Ljava/util/Set;Ljava/lang/String;)V
        //   268: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  34     45     99     128    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  45     54     128    156    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
        log(context, 2, "Found %d receivers for action %s", queryBroadcastReceivers.size(), name);
        final Iterator<ResolveInfo> iterator = queryBroadcastReceivers.iterator();
        while (iterator.hasNext()) {
            name = iterator.next().activityInfo.name;
            if (!set.contains(name)) {
                throw new IllegalStateException("Receiver " + name + " is not set with permission " + "com.google.android.c2dm.permission.SEND");
            }
        }
    }
    
    static String clearRegistrationId(final Context context) {
        setRegisteredOnServer(context, null, 0L);
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
                log(context, 2, "App version changed from %d to %d;resetting registration id", int1, appVersion);
                clearRegistrationId(context);
                s = "";
            }
        }
        return s;
    }
    
    static void internalRegister(final Context context, final String... array) {
        final String flatSenderIds = getFlatSenderIds(array);
        log(context, 2, "Registering app for senders %s", flatSenderIds);
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        setPackageNameExtra(context, intent);
        intent.putExtra("sender", flatSenderIds);
        context.startService(intent);
    }
    
    static void internalUnregister(final Context context) {
        log(context, 2, "Unregistering app", new Object[0]);
        final Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        setPackageNameExtra(context, intent);
        context.startService(intent);
    }
    
    public static boolean isRegistered(final Context context) {
        return getRegistrationId(context).length() > 0;
    }
    
    public static boolean isRegisteredOnServer(final Context context) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final boolean boolean1 = gcmPreferences.getBoolean("onServer", false);
        log(context, 2, "Is registered on server: %b", boolean1);
        if (boolean1) {
            final long long1 = gcmPreferences.getLong("onServerExpirationTime", -1L);
            if (System.currentTimeMillis() > long1) {
                log(context, 2, "flag expired on: %s", new Timestamp(long1));
                return false;
            }
        }
        return boolean1;
    }
    
    private static void log(final Context context, final int n, String format, final Object... array) {
        if (Log.isLoggable("GCMRegistrar", n)) {
            format = String.format(format, array);
            Log.println(n, "GCMRegistrar", "[" + context.getPackageName() + "]: " + format);
        }
    }
    
    public static void onDestroy(final Context context) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sRetryReceiver != null) {
                log(context, 2, "Unregistering retry receiver", new Object[0]);
                GCMRegistrar.sRetryReceiverContext.unregisterReceiver((BroadcastReceiver)GCMRegistrar.sRetryReceiver);
                GCMRegistrar.sRetryReceiver = null;
                GCMRegistrar.sRetryReceiverContext = null;
            }
        }
    }
    
    public static void register(final Context context, final String... array) {
        resetBackoff(context);
        internalRegister(context, array);
    }
    
    static void resetBackoff(final Context context) {
        log(context, 2, "Resetting backoff", new Object[0]);
        setBackoff(context, 3000);
    }
    
    static void setBackoff(final Context context, final int n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putInt("backoff_ms", n);
        edit.commit();
    }
    
    private static void setPackageNameExtra(final Context context, final Intent intent) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sAppPendingIntent == null) {
                log(context, 2, "Creating pending intent to get package name", new Object[0]);
                GCMRegistrar.sAppPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            }
            intent.putExtra("app", (Parcelable)GCMRegistrar.sAppPendingIntent);
        }
    }
    
    public static void setRegisterOnServerLifespan(final Context context, final long n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putLong("onServerLifeSpan", n);
        edit.commit();
    }
    
    private static void setRegisteredOnServer(final Context context, final Boolean b, final long n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        if (b != null) {
            edit.putBoolean("onServer", (boolean)b);
            log(context, 2, "Setting registeredOnServer flag as %b until %s", b, new Timestamp(n));
        }
        else {
            log(context, 2, "Setting registeredOnServer expiration to %s", new Timestamp(n));
        }
        edit.putLong("onServerExpirationTime", n);
        edit.commit();
    }
    
    public static void setRegisteredOnServer(final Context context, final boolean b) {
        setRegisteredOnServer(context, b, getRegisterOnServerLifespan(context) + System.currentTimeMillis());
    }
    
    static String setRegistrationId(final Context context, final String s) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final String string = gcmPreferences.getString("regId", "");
        final int appVersion = getAppVersion(context);
        log(context, 2, "Saving regId on app version %d", appVersion);
        final SharedPreferences$Editor edit = gcmPreferences.edit();
        edit.putString("regId", s);
        edit.putInt("appVersion", appVersion);
        edit.commit();
        return string;
    }
    
    static void setRetryBroadcastReceiver(final Context sRetryReceiverContext) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sRetryReceiver == null) {
                if (GCMRegistrar.sRetryReceiverClassName == null) {
                    log(sRetryReceiverContext, 6, "internal error: retry receiver class not set yet", new Object[0]);
                    GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                }
                else {
                    try {
                        GCMRegistrar.sRetryReceiver = (GCMBroadcastReceiver)Class.forName(GCMRegistrar.sRetryReceiverClassName).newInstance();
                    }
                    catch (Exception ex) {
                        log(sRetryReceiverContext, 6, "Could not create instance of %s. Using %s directly.", GCMRegistrar.sRetryReceiverClassName, GCMBroadcastReceiver.class.getName());
                        GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                    }
                }
                final String packageName = sRetryReceiverContext.getPackageName();
                final IntentFilter intentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
                intentFilter.addCategory(packageName);
                log(sRetryReceiverContext, 2, "Registering retry receiver", new Object[0]);
                (GCMRegistrar.sRetryReceiverContext = sRetryReceiverContext).registerReceiver((BroadcastReceiver)GCMRegistrar.sRetryReceiver, intentFilter);
            }
        }
    }
    
    static void setRetryReceiverClassName(final Context context, final String sRetryReceiverClassName) {
        synchronized (GCMRegistrar.class) {
            log(context, 2, "Setting the name of retry receiver class to %s", sRetryReceiverClassName);
            GCMRegistrar.sRetryReceiverClassName = sRetryReceiverClassName;
        }
    }
    
    public static void unregister(final Context context) {
        resetBackoff(context);
        internalUnregister(context);
    }
}
