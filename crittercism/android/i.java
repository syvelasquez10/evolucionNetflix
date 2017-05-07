// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.io.IOException;
import android.os.Build$VERSION;
import android.os.Build;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.List;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.os.Debug;
import android.os.Debug$MemoryInfo;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.io.UnsupportedEncodingException;
import android.telephony.TelephonyManager;
import java.util.UUID;
import android.provider.Settings$Secure;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import com.crittercism.app.CrittercismConfig;
import org.json.JSONObject;
import android.content.Context;

public class i
{
    m a;
    final String b;
    final int c;
    private final String d;
    private k e;
    private Context f;
    private String g;
    private String h;
    private String i;
    private f j;
    private int k;
    private boolean l;
    private String m;
    private JSONObject n;
    private Object o;
    private String p;
    
    public i(final Context f, final String g, final String i, final m a) {
        this.d = "3.1.4";
        this.e = null;
        this.f = null;
        this.g = "";
        this.h = "";
        this.i = "";
        this.k = -1;
        this.b = "critter_did";
        this.c = 100;
        this.l = false;
        this.m = "";
        this.n = new JSONObject();
        this.o = new Object();
        this.p = "en";
        this.f = f;
        this.e = new k("3.1.4", this.f);
        this.g = g;
        this.h = null;
        this.i = i;
        this.j = new f(f, a);
        this.a = a;
        try {
            final String language = f.getResources().getConfiguration().locale.getLanguage();
            if (this.p != null && !this.p.equals("")) {
                this.p = language;
            }
        }
        catch (Exception ex) {
            new StringBuilder("Exception in getLocale(): ").append(ex.getClass().getName());
        }
    }
    
    private static String a(final String s) {
        String s2 = s;
        while (true) {
            if (s == null) {
                break Label_0050;
            }
            s2 = s;
            if (s.equals("")) {
                break Label_0050;
            }
            try {
                s2 = new String(new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(s.getBytes())).toString(16));
                if (s2.equals("")) {
                    return null;
                }
            }
            catch (NoSuchAlgorithmException ex) {
                s2 = null;
                continue;
            }
            break;
        }
        return s2;
    }
    
    private int b(final String s) {
        try {
            return this.f.getPackageManager().checkPermission(s, this.f.getPackageName());
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    private String l() {
        final String string = Settings$Secure.getString(this.f.getContentResolver(), "android_id");
        Label_0073: {
            if (string == null || string.equals("") || "9774d56d682e549c".equals(string)) {
                final String s = null;
                break Label_0073;
            }
            while (true) {
                while (true) {
                    try {
                        final UUID nameUUIDFromBytes = UUID.nameUUIDFromBytes(string.getBytes("utf8"));
                        if (nameUUIDFromBytes != null) {
                            final String string2 = nameUUIDFromBytes.toString();
                            String s = string2;
                            if (string2 != null) {
                                s = string2;
                                if (string2.equals("")) {
                                    s = null;
                                }
                            }
                            String a;
                            if ((a = s) == null) {
                                a = s;
                                if (this.f.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", crittercism.android.l.i().j()) == 0) {
                                    a = a(((TelephonyManager)this.f.getSystemService("phone")).getDeviceId());
                                }
                            }
                            String string3;
                            if ((string3 = a) == null) {
                                string3 = UUID.randomUUID().toString();
                            }
                            return string3;
                        }
                    }
                    catch (UnsupportedEncodingException ex) {}
                    final String string2 = null;
                    continue;
                }
            }
        }
    }
    
    private double m() {
        final double n = 1.0;
        try {
            final Intent registerReceiver = this.f.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            final int intExtra = registerReceiver.getIntExtra("level", -1);
            final double n2 = registerReceiver.getIntExtra("scale", -1);
            double n3 = n;
            if (intExtra >= 0) {
                n3 = n;
                if (n2 > 0.0) {
                    n3 = intExtra / n2;
                }
            }
            return n3;
        }
        catch (Exception ex) {
            return 1.0;
        }
    }
    
    private static long n() {
        int n = -1;
        try {
            final Debug$MemoryInfo debug$MemoryInfo = new Debug$MemoryInfo();
            Debug.getMemoryInfo(debug$MemoryInfo);
            n = (debug$MemoryInfo.otherPss + (debug$MemoryInfo.dalvikPss + debug$MemoryInfo.nativePss)) * 1024;
            return n;
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private JSONObject o() {
        final JSONObject jsonObject = new JSONObject();
        try {
            final NetworkInfo networkInfo = ((ConnectivityManager)this.f.getSystemService("connectivity")).getNetworkInfo(1);
            jsonObject.put("available", networkInfo.isAvailable());
            jsonObject.put("connected", networkInfo.isConnected());
            if (!networkInfo.isConnected()) {
                jsonObject.put("connecting", networkInfo.isConnectedOrConnecting());
            }
            jsonObject.put("failover", networkInfo.isFailover());
            return jsonObject;
        }
        catch (Exception ex) {
            i.class.getCanonicalName();
            return jsonObject;
        }
    }
    
    private JSONObject p() {
        final JSONObject jsonObject = new JSONObject();
        try {
            final NetworkInfo networkInfo = ((ConnectivityManager)this.f.getSystemService("connectivity")).getNetworkInfo(0);
            jsonObject.put("available", networkInfo.isAvailable());
            jsonObject.put("connected", networkInfo.isConnected());
            if (!networkInfo.isConnected()) {
                jsonObject.put("connecting", networkInfo.isConnectedOrConnecting());
            }
            jsonObject.put("failover", networkInfo.isFailover());
            jsonObject.put("roaming", networkInfo.isRoaming());
            return jsonObject;
        }
        catch (Exception ex) {
            i.class.getCanonicalName();
            new StringBuilder().append(ex.toString()).append(" in getMobileNetworkStatus");
            return jsonObject;
        }
    }
    
    private String q() {
        try {
            if (this.b("android.permission.GET_TASKS") == 0) {
                final List runningTasks = ((ActivityManager)this.f.getSystemService("activity")).getRunningTasks(1);
                new StringBuilder("CURRENT Activity ::").append(runningTasks.get(0).topActivity.getClassName());
                return runningTasks.get(0).topActivity.flattenToShortString().replace("/", "");
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    private JSONArray r() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONArray;
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore          4
        //     9: new             Ljava/lang/StringBuilder;
        //    12: dup            
        //    13: invokespecial   java/lang/StringBuilder.<init>:()V
        //    16: astore_3       
        //    17: aload_0        
        //    18: getfield        crittercism/android/i.o:Ljava/lang/Object;
        //    21: astore          6
        //    23: aload           6
        //    25: monitorenter   
        //    26: invokestatic    java/util/concurrent/Executors.newCachedThreadPool:()Ljava/util/concurrent/ExecutorService;
        //    29: astore          7
        //    31: new             Lcrittercism/android/i$a;
        //    34: dup            
        //    35: aload_0        
        //    36: getfield        crittercism/android/i.o:Ljava/lang/Object;
        //    39: invokespecial   crittercism/android/i$a.<init>:(Ljava/lang/Object;)V
        //    42: astore          9
        //    44: aload           7
        //    46: aload           9
        //    48: invokeinterface java/util/concurrent/ExecutorService.submit:(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
        //    53: astore          8
        //    55: invokestatic    crittercism/android/i$a.a:()Z
        //    58: ifne            297
        //    61: aload           8
        //    63: ldc2_w          5
        //    66: getstatic       java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
        //    69: invokeinterface java/util/concurrent/Future.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //    74: checkcast       Ljava/lang/StringBuilder;
        //    77: astore          5
        //    79: aload           5
        //    81: astore_3       
        //    82: aload           8
        //    84: iconst_1       
        //    85: invokeinterface java/util/concurrent/Future.cancel:(Z)Z
        //    90: pop            
        //    91: aload           7
        //    93: invokeinterface java/util/concurrent/ExecutorService.shutdownNow:()Ljava/util/List;
        //    98: pop            
        //    99: aload           6
        //   101: monitorexit    
        //   102: aload_3        
        //   103: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   106: invokevirtual   java/lang/String.length:()I
        //   109: ifle            209
        //   112: aload_3        
        //   113: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   116: ldc_w           "\n"
        //   119: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   122: astore_3       
        //   123: iconst_0       
        //   124: istore_1       
        //   125: iload_1        
        //   126: aload_3        
        //   127: arraylength    
        //   128: if_icmpge       209
        //   131: aload           4
        //   133: aload_3        
        //   134: iload_1        
        //   135: aaload         
        //   136: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   139: pop            
        //   140: iload_1        
        //   141: iconst_1       
        //   142: iadd           
        //   143: istore_1       
        //   144: goto            125
        //   147: astore          5
        //   149: invokestatic    crittercism/android/i$a.b:()V
        //   152: aload           9
        //   154: invokevirtual   crittercism/android/i$a.c:()V
        //   157: ldc2_w          200
        //   160: invokestatic    java/lang/Thread.sleep:(J)V
        //   163: aload           8
        //   165: iconst_1       
        //   166: invokeinterface java/util/concurrent/Future.cancel:(Z)Z
        //   171: pop            
        //   172: aload           7
        //   174: invokeinterface java/util/concurrent/ExecutorService.shutdownNow:()Ljava/util/List;
        //   179: pop            
        //   180: goto            99
        //   183: astore_3       
        //   184: aload           8
        //   186: iconst_1       
        //   187: invokeinterface java/util/concurrent/Future.cancel:(Z)Z
        //   192: pop            
        //   193: aload           7
        //   195: invokeinterface java/util/concurrent/ExecutorService.shutdownNow:()Ljava/util/List;
        //   200: pop            
        //   201: aload_3        
        //   202: athrow         
        //   203: astore_3       
        //   204: aload           6
        //   206: monitorexit    
        //   207: aload_3        
        //   208: athrow         
        //   209: aload           4
        //   211: invokevirtual   org/json/JSONArray.length:()I
        //   214: istore_2       
        //   215: iload_2        
        //   216: bipush          100
        //   218: if_icmple       292
        //   221: new             Lorg/json/JSONArray;
        //   224: dup            
        //   225: invokespecial   org/json/JSONArray.<init>:()V
        //   228: astore          5
        //   230: iload_2        
        //   231: bipush          100
        //   233: isub           
        //   234: istore_1       
        //   235: aload           5
        //   237: astore_3       
        //   238: iload_1        
        //   239: iload_2        
        //   240: if_icmpge       295
        //   243: aload           5
        //   245: aload           4
        //   247: iload_1        
        //   248: invokevirtual   org/json/JSONArray.getString:(I)Ljava/lang/String;
        //   251: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   254: pop            
        //   255: iload_1        
        //   256: iconst_1       
        //   257: iadd           
        //   258: istore_1       
        //   259: goto            235
        //   262: astore_3       
        //   263: new             Ljava/lang/StringBuilder;
        //   266: dup            
        //   267: ldc_w           "Caught exception in second try-catch of getLogCat():"
        //   270: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   273: aload_3        
        //   274: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   277: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: pop            
        //   284: goto            255
        //   287: astore          5
        //   289: goto            163
        //   292: aload           4
        //   294: astore_3       
        //   295: aload_3        
        //   296: areturn        
        //   297: goto            82
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  26     55     203    209    Any
        //  55     79     147    183    Ljava/lang/Exception;
        //  55     79     183    203    Any
        //  82     99     203    209    Any
        //  99     102    203    209    Any
        //  149    157    183    203    Any
        //  157    163    287    292    Ljava/lang/InterruptedException;
        //  157    163    183    203    Any
        //  163    180    203    209    Any
        //  184    203    203    209    Any
        //  243    255    262    287    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    
    private float s() {
        try {
            return this.f.getResources().getDisplayMetrics().density;
        }
        catch (Exception ex) {
            return 1.0f;
        }
    }
    
    private float t() {
        try {
            return this.f.getResources().getDisplayMetrics().ydpi;
        }
        catch (Exception ex) {
            return 0.0f;
        }
    }
    
    private boolean u() {
        int n = 30;
        try {
            while (!this.l && n > 0) {
                --n;
                Thread.sleep(1000L);
            }
        }
        catch (Exception ex) {}
        return this.l;
    }
    
    public final String a() {
        return this.g;
    }
    
    public final JSONObject a(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          7
        //     3: new             Ljava/lang/String;
        //     6: dup            
        //     7: invokespecial   java/lang/String.<init>:()V
        //    10: astore          5
        //    12: new             Lorg/json/JSONObject;
        //    15: dup            
        //    16: invokespecial   org/json/JSONObject.<init>:()V
        //    19: astore          6
        //    21: new             Lorg/json/JSONObject;
        //    24: dup            
        //    25: invokespecial   org/json/JSONObject.<init>:()V
        //    28: astore          8
        //    30: new             Ljava/lang/String;
        //    33: dup            
        //    34: invokespecial   java/lang/String.<init>:()V
        //    37: pop            
        //    38: aload           5
        //    40: astore_3       
        //    41: aload           5
        //    43: astore          4
        //    45: aload           8
        //    47: ldc_w           "success"
        //    50: iconst_0       
        //    51: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    54: pop            
        //    55: aload           5
        //    57: astore_3       
        //    58: aload           5
        //    60: astore          4
        //    62: aload_1        
        //    63: ldc_w           "requestUrl"
        //    66: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    69: astore          5
        //    71: aload           5
        //    73: astore_3       
        //    74: aload           5
        //    76: astore          4
        //    78: aload_1        
        //    79: ldc_w           "requestData"
        //    82: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    85: astore_1       
        //    86: aload           5
        //    88: astore_3       
        //    89: aload_1        
        //    90: ldc_w           "filenames"
        //    93: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    96: ifeq            390
        //    99: aload_1        
        //   100: ldc_w           "filename_prefix"
        //   103: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   106: ifeq            390
        //   109: new             Lorg/json/JSONArray;
        //   112: dup            
        //   113: invokespecial   org/json/JSONArray.<init>:()V
        //   116: pop            
        //   117: aload_1        
        //   118: ldc_w           "filenames"
        //   121: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //   124: astore          4
        //   126: aload           4
        //   128: invokevirtual   org/json/JSONArray.length:()I
        //   131: anewarray       Ljava/lang/String;
        //   134: astore          5
        //   136: iconst_0       
        //   137: istore_2       
        //   138: iload_2        
        //   139: aload           4
        //   141: invokevirtual   org/json/JSONArray.length:()I
        //   144: if_icmpge       189
        //   147: aload           5
        //   149: iload_2        
        //   150: aload           4
        //   152: iload_2        
        //   153: invokevirtual   org/json/JSONArray.getString:(I)Ljava/lang/String;
        //   156: aastore        
        //   157: iload_2        
        //   158: iconst_1       
        //   159: iadd           
        //   160: istore_2       
        //   161: goto            138
        //   164: astore_1       
        //   165: aload_1        
        //   166: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   169: aload           6
        //   171: astore_1       
        //   172: goto            89
        //   175: astore_1       
        //   176: aload_1        
        //   177: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   180: aload           4
        //   182: astore_3       
        //   183: aload           6
        //   185: astore_1       
        //   186: goto            89
        //   189: aload_1        
        //   190: ldc_w           "filename_prefix"
        //   193: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   196: astore          4
        //   198: aload_1        
        //   199: ldc_w           "filenames"
        //   202: invokevirtual   org/json/JSONObject.remove:(Ljava/lang/String;)Ljava/lang/Object;
        //   205: pop            
        //   206: aload_1        
        //   207: ldc_w           "filename_prefix"
        //   210: invokevirtual   org/json/JSONObject.remove:(Ljava/lang/String;)Ljava/lang/Object;
        //   213: pop            
        //   214: aload           5
        //   216: ifnull          323
        //   219: aload           4
        //   221: ifnull          323
        //   224: aload_0        
        //   225: getfield        crittercism/android/i.e:Lcrittercism/android/k;
        //   228: aload_3        
        //   229: aload_1        
        //   230: aload           5
        //   232: aload           4
        //   234: invokevirtual   crittercism/android/k.a:(Ljava/lang/String;Lorg/json/JSONObject;[Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   237: astore          4
        //   239: aload           4
        //   241: ifnull          387
        //   244: aload           4
        //   246: ldc             ""
        //   248: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   251: ifne            387
        //   254: new             Lorg/json/JSONObject;
        //   257: dup            
        //   258: aload           4
        //   260: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   263: astore          4
        //   265: new             Ljava/lang/StringBuilder;
        //   268: dup            
        //   269: ldc_w           "POSTING JSON DATA: url = "
        //   272: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   275: aload_3        
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: ldc_w           "\tdata = "
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload_1        
        //   286: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: pop            
        //   293: new             Ljava/lang/StringBuilder;
        //   296: dup            
        //   297: ldc_w           "POSTING JSON DATA: response = "
        //   300: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   303: aload           4
        //   305: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: pop            
        //   312: aload           4
        //   314: areturn        
        //   315: astore_1       
        //   316: aload_1        
        //   317: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   320: aload           8
        //   322: areturn        
        //   323: aload_0        
        //   324: getfield        crittercism/android/i.e:Lcrittercism/android/k;
        //   327: aload_3        
        //   328: aload_1        
        //   329: invokevirtual   crittercism/android/k.a:(Ljava/lang/String;Lorg/json/JSONObject;)Ljava/lang/String;
        //   332: astore          4
        //   334: goto            239
        //   337: astore_1       
        //   338: aload_1        
        //   339: athrow         
        //   340: astore_1       
        //   341: aload_1        
        //   342: athrow         
        //   343: astore_1       
        //   344: new             Ljava/lang/StringBuilder;
        //   347: dup            
        //   348: ldc_w           "Exception in postJsonDataNew: "
        //   351: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   354: aload_1        
        //   355: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   358: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: pop            
        //   365: new             Lorg/json/JSONObject;
        //   368: dup            
        //   369: invokespecial   org/json/JSONObject.<init>:()V
        //   372: astore_1       
        //   373: aload_1        
        //   374: ldc_w           "success"
        //   377: iconst_0       
        //   378: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   381: pop            
        //   382: aload_1        
        //   383: areturn        
        //   384: astore_3       
        //   385: aload_1        
        //   386: areturn        
        //   387: aload           8
        //   389: areturn        
        //   390: aconst_null    
        //   391: astore          5
        //   393: aload           7
        //   395: astore          4
        //   397: goto            214
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  45     55     164    175    Lorg/json/JSONException;
        //  45     55     175    189    Ljava/lang/Exception;
        //  62     71     164    175    Lorg/json/JSONException;
        //  62     71     175    189    Ljava/lang/Exception;
        //  78     86     164    175    Lorg/json/JSONException;
        //  78     86     175    189    Ljava/lang/Exception;
        //  89     136    315    323    Ljava/lang/Exception;
        //  138    157    315    323    Ljava/lang/Exception;
        //  189    214    315    323    Ljava/lang/Exception;
        //  224    239    337    340    Lcrittercism/android/ad;
        //  224    239    340    343    Ljava/io/IOException;
        //  224    239    343    387    Ljava/lang/Exception;
        //  244    312    337    340    Lcrittercism/android/ad;
        //  244    312    340    343    Ljava/io/IOException;
        //  244    312    343    387    Ljava/lang/Exception;
        //  323    334    337    340    Lcrittercism/android/ad;
        //  323    334    340    343    Ljava/io/IOException;
        //  323    334    343    387    Ljava/lang/Exception;
        //  373    382    384    387    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
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
    
    public final JSONObject a(final boolean... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: iconst_1       
        //     3: istore_3       
        //     4: aload_1        
        //     5: arraylength    
        //     6: ifle            500
        //     9: aload_1        
        //    10: iconst_0       
        //    11: baload         
        //    12: istore          4
        //    14: aload_1        
        //    15: arraylength    
        //    16: iconst_1       
        //    17: if_icmple       494
        //    20: aload_1        
        //    21: iconst_1       
        //    22: baload         
        //    23: istore          5
        //    25: new             Lorg/json/JSONObject;
        //    28: dup            
        //    29: invokespecial   org/json/JSONObject.<init>:()V
        //    32: astore          7
        //    34: aload_0        
        //    35: invokevirtual   crittercism/android/i.f:()Lorg/json/JSONObject;
        //    38: astore          6
        //    40: aload           6
        //    42: ldc_w           "battery_level"
        //    45: aload_0        
        //    46: invokespecial   crittercism/android/i.m:()D
        //    49: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //    52: pop            
        //    53: aload           6
        //    55: ldc_w           "memory_usage"
        //    58: invokestatic    crittercism/android/i.n:()J
        //    61: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //    64: pop            
        //    65: aload_0        
        //    66: getfield        crittercism/android/i.f:Landroid/content/Context;
        //    69: ldc_w           "activity"
        //    72: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //    75: checkcast       Landroid/app/ActivityManager;
        //    78: astore_1       
        //    79: new             Landroid/app/ActivityManager$MemoryInfo;
        //    82: dup            
        //    83: invokespecial   android/app/ActivityManager$MemoryInfo.<init>:()V
        //    86: astore          7
        //    88: aload_1        
        //    89: aload           7
        //    91: invokevirtual   android/app/ActivityManager.getMemoryInfo:(Landroid/app/ActivityManager$MemoryInfo;)V
        //    94: aload           7
        //    96: getfield        android/app/ActivityManager$MemoryInfo.lowMemory:Z
        //    99: ifeq            104
        //   102: iconst_1       
        //   103: istore_2       
        //   104: aload           6
        //   106: ldc_w           "low_memory"
        //   109: iload_2        
        //   110: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   113: pop            
        //   114: aload_0        
        //   115: ldc_w           "android.permission.ACCESS_NETWORK_STATE"
        //   118: invokespecial   crittercism/android/i.b:(Ljava/lang/String;)I
        //   121: ifne            150
        //   124: aload           6
        //   126: ldc_w           "wifi"
        //   129: aload_0        
        //   130: invokespecial   crittercism/android/i.o:()Lorg/json/JSONObject;
        //   133: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   136: pop            
        //   137: aload           6
        //   139: ldc_w           "mobile_network"
        //   142: aload_0        
        //   143: invokespecial   crittercism/android/i.p:()Lorg/json/JSONObject;
        //   146: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   149: pop            
        //   150: aload           6
        //   152: ldc_w           "disk_space_free"
        //   155: invokestatic    crittercism/android/ah.a:()Ljava/math/BigInteger;
        //   158: invokevirtual   java/math/BigInteger.toString:()Ljava/lang/String;
        //   161: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   164: pop            
        //   165: aload           6
        //   167: ldc_w           "disk_space_total"
        //   170: invokestatic    crittercism/android/ah.b:()Ljava/math/BigInteger;
        //   173: invokevirtual   java/math/BigInteger.toString:()Ljava/lang/String;
        //   176: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   179: pop            
        //   180: aload           6
        //   182: ldc_w           "sd_space_free"
        //   185: invokestatic    crittercism/android/ah.c:()Ljava/math/BigInteger;
        //   188: invokevirtual   java/math/BigInteger.toString:()Ljava/lang/String;
        //   191: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   194: pop            
        //   195: aload           6
        //   197: ldc_w           "sd_space_total"
        //   200: invokestatic    crittercism/android/ah.d:()Ljava/math/BigInteger;
        //   203: invokevirtual   java/math/BigInteger.toString:()Ljava/lang/String;
        //   206: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   209: pop            
        //   210: aload_0        
        //   211: getfield        crittercism/android/i.f:Landroid/content/Context;
        //   214: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   217: invokevirtual   android/content/res/Resources.getConfiguration:()Landroid/content/res/Configuration;
        //   220: getfield        android/content/res/Configuration.orientation:I
        //   223: istore_2       
        //   224: iload_2        
        //   225: ifne            491
        //   228: aload_0        
        //   229: getfield        crittercism/android/i.f:Landroid/content/Context;
        //   232: ldc_w           "window"
        //   235: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   238: checkcast       Landroid/view/WindowManager;
        //   241: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
        //   246: astore_1       
        //   247: aload_1        
        //   248: invokevirtual   android/view/Display.getWidth:()I
        //   251: aload_1        
        //   252: invokevirtual   android/view/Display.getHeight:()I
        //   255: if_icmpne       343
        //   258: iconst_3       
        //   259: istore_2       
        //   260: aload           6
        //   262: ldc_w           "orientation"
        //   265: iload_2        
        //   266: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   269: pop            
        //   270: aload           6
        //   272: ldc_w           "activity"
        //   275: aload_0        
        //   276: invokespecial   crittercism/android/i.q:()Ljava/lang/String;
        //   279: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   282: pop            
        //   283: iload           5
        //   285: ifeq            301
        //   288: aload           6
        //   290: ldc_w           "metadata"
        //   293: aload_0        
        //   294: getfield        crittercism/android/i.n:Lorg/json/JSONObject;
        //   297: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   300: pop            
        //   301: iload           4
        //   303: ifeq            455
        //   306: aload_0        
        //   307: ldc_w           "android.permission.READ_LOGS"
        //   310: invokespecial   crittercism/android/i.b:(Ljava/lang/String;)I
        //   313: ifne            386
        //   316: aload_0        
        //   317: invokespecial   crittercism/android/i.r:()Lorg/json/JSONArray;
        //   320: astore_1       
        //   321: aload_1        
        //   322: invokevirtual   org/json/JSONArray.length:()I
        //   325: istore_2       
        //   326: iload_2        
        //   327: ifle            506
        //   330: aload           6
        //   332: ldc_w           "logcat"
        //   335: aload_1        
        //   336: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   339: pop            
        //   340: aload           6
        //   342: areturn        
        //   343: iload_3        
        //   344: istore_2       
        //   345: aload_1        
        //   346: invokevirtual   android/view/Display.getWidth:()I
        //   349: aload_1        
        //   350: invokevirtual   android/view/Display.getHeight:()I
        //   353: if_icmple       260
        //   356: iconst_2       
        //   357: istore_2       
        //   358: goto            260
        //   361: astore_1       
        //   362: new             Ljava/lang/StringBuilder;
        //   365: dup            
        //   366: ldc_w           "put logcat EXCEPTION: "
        //   369: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   372: aload_1        
        //   373: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   376: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   379: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: pop            
        //   383: goto            506
        //   386: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   389: bipush          16
        //   391: if_icmplt       455
        //   394: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //   397: getfield        crittercism/android/l.t:Lcrittercism/android/m;
        //   400: invokevirtual   com/crittercism/app/CrittercismConfig.isLogcatReportingEnabled:()Z
        //   403: ifeq            455
        //   406: aload_0        
        //   407: invokespecial   crittercism/android/i.r:()Lorg/json/JSONArray;
        //   410: astore_1       
        //   411: aload_1        
        //   412: invokevirtual   org/json/JSONArray.length:()I
        //   415: istore_2       
        //   416: iload_2        
        //   417: ifle            455
        //   420: aload           6
        //   422: ldc_w           "logcat"
        //   425: aload_1        
        //   426: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   429: pop            
        //   430: aload           6
        //   432: areturn        
        //   433: astore_1       
        //   434: new             Ljava/lang/StringBuilder;
        //   437: dup            
        //   438: ldc_w           "put logcat EXCEPTION: "
        //   441: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   444: aload_1        
        //   445: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   448: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: pop            
        //   455: aload           6
        //   457: areturn        
        //   458: astore_1       
        //   459: aload           7
        //   461: astore          6
        //   463: new             Ljava/lang/StringBuilder;
        //   466: dup            
        //   467: ldc_w           "Exception with getStateInfo(): "
        //   470: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   473: aload_1        
        //   474: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   477: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   480: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   483: pop            
        //   484: aload           6
        //   486: areturn        
        //   487: astore_1       
        //   488: goto            463
        //   491: goto            260
        //   494: iconst_0       
        //   495: istore          5
        //   497: goto            25
        //   500: iconst_1       
        //   501: istore          4
        //   503: goto            14
        //   506: aload           6
        //   508: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  34     40     458    463    Ljava/lang/Exception;
        //  40     94     487    491    Ljava/lang/Exception;
        //  94     102    487    491    Ljava/lang/Exception;
        //  104    150    487    491    Ljava/lang/Exception;
        //  150    224    487    491    Ljava/lang/Exception;
        //  228    258    487    491    Ljava/lang/Exception;
        //  260    283    487    491    Ljava/lang/Exception;
        //  288    301    487    491    Ljava/lang/Exception;
        //  306    326    487    491    Ljava/lang/Exception;
        //  330    340    361    386    Ljava/lang/Exception;
        //  345    356    487    491    Ljava/lang/Exception;
        //  362    383    487    491    Ljava/lang/Exception;
        //  386    416    487    491    Ljava/lang/Exception;
        //  420    430    433    455    Ljava/lang/Exception;
        //  434    455    487    491    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0104:
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
    
    public final int b() {
        return this.k;
    }
    
    public final boolean b(JSONObject a) {
        while (true) {
            boolean b = true;
            synchronized (this) {
                try {
                    if (a.has("username")) {
                        this.m = a.getString("username");
                    }
                    else if (!this.m.equals("")) {
                        a.put("username", (Object)this.m);
                    }
                    new StringBuilder("sendCustomMetadata$metadata = ").append(a.toString());
                    if (this.u()) {
                        final JSONObject jsonObject = new JSONObject();
                        new JSONObject();
                        final JSONObject d = this.d();
                        d.put("metadata", (Object)a);
                        jsonObject.put("requestUrl", (Object)(this.a.a() + "/android_v2/update_user_metadata"));
                        jsonObject.put("requestData", (Object)d);
                        a = this.a(jsonObject);
                        if (a.has("success") && a.getInt("success") == 1) {
                            return b;
                        }
                    }
                }
                catch (ad ad) {
                    throw ad;
                }
                catch (Exception ex) {
                    i.class.getCanonicalName();
                }
            }
            b = false;
            return b;
        }
    }
    
    public final String c() {
        return this.h;
    }
    
    public final void c(final JSONObject jsonObject) {
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            try {
                this.n.put(s, jsonObject.get(s));
            }
            catch (JSONException ex) {}
        }
    }
    
    public final JSONObject d() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_id", (Object)this.g);
            if (this.h == null) {
                this.h = this.l();
            }
            jsonObject.put("hashed_device_id", (Object)this.h);
            jsonObject.put("device_name", (Object)"android");
            jsonObject.put("library_version", (Object)this.i);
            return jsonObject;
        }
        catch (Exception ex) {
            new StringBuilder("Exception in getRequiredParams(): ").append(ex.getClass().getName());
            return jsonObject;
        }
        catch (JSONException ex2) {
            return jsonObject;
        }
    }
    
    public final String e() {
        try {
            return ((TelephonyManager)this.f.getSystemService("phone")).getNetworkOperatorName();
        }
        catch (Exception ex) {
            return Build.BRAND;
        }
    }
    
    public final JSONObject f() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("arch", (Object)System.getProperty("os.arch"));
            jsonObject.put("locale", (Object)this.p);
            jsonObject.put("dpi", (double)this.s());
            jsonObject.put("xdpi", (double)this.g());
            jsonObject.put("ydpi", (double)this.t());
            jsonObject.put("name", (Object)"");
            jsonObject.put("system", (Object)"android");
            jsonObject.put("model", (Object)Build.MODEL);
            jsonObject.put("carrier", (Object)this.e());
            jsonObject.put("system_version", (Object)Build$VERSION.RELEASE);
            jsonObject.put("app_version_code", this.j.b);
            jsonObject.put("app_version", (Object)this.j.a);
            return jsonObject;
        }
        catch (Exception ex) {
            return new JSONObject();
        }
    }
    
    public final float g() {
        try {
            return this.f.getResources().getDisplayMetrics().xdpi;
        }
        catch (Exception ex) {
            return 0.0f;
        }
    }
    
    public final String h() {
        new JSONObject();
        new JSONObject();
        String s5;
        String s4;
        String s3;
        String s2;
        final String s = s2 = (s3 = (s4 = (s5 = new String())));
        String a;
        try {
            final String string = this.a.a() + "/android_v2/update_package_name";
            s5 = s;
            s4 = s;
            s3 = s;
            s2 = s;
            final JSONObject d = this.d();
            s5 = s;
            s4 = s;
            s3 = s;
            s2 = s;
            d.put("pkg", (Object)this.f.getPackageName());
            s5 = s;
            s4 = s;
            s3 = s;
            s2 = s;
            final String s6 = a = this.e.a(string, d);
            if (s6 != null) {
                s5 = s6;
                s4 = s6;
                s3 = s6;
                s2 = s6;
                a = s6;
                if (!s6.equals("")) {
                    s5 = s6;
                    s4 = s6;
                    s3 = s6;
                    s2 = s6;
                    final JSONObject jsonObject = new JSONObject(s6);
                    s5 = s6;
                    s4 = s6;
                    s3 = s6;
                    s2 = s6;
                    if (!jsonObject.has("success")) {
                        s5 = s6;
                        s4 = s6;
                        s3 = s6;
                        s2 = s6;
                        new StringBuilder().append(string).append(" response: ").append(s6);
                        return s6;
                    }
                    s5 = s6;
                    s4 = s6;
                    s3 = s6;
                    s2 = s6;
                    a = s6;
                    if (jsonObject.getInt("success") == 1) {
                        s5 = s6;
                        s4 = s6;
                        s3 = s6;
                        s2 = s6;
                        new StringBuilder("app_id: ").append(jsonObject.getString("app_id"));
                        s5 = s6;
                        s4 = s6;
                        s3 = s6;
                        s2 = s6;
                        new StringBuilder("package name: ").append(jsonObject.getString("pkg"));
                        s5 = s6;
                        s4 = s6;
                        s3 = s6;
                        s2 = s6;
                        new StringBuilder("updated settings: ").append(jsonObject.getJSONObject("updated_settings"));
                        return s6;
                    }
                }
            }
        }
        catch (ad ad) {
            return s5;
        }
        catch (Exception ex) {
            new StringBuilder("sendPackageName: Exception! ").append(ex.getClass().getName());
            return s4;
        }
        catch (JSONException ex2) {
            return s3;
        }
        catch (IOException ex3) {
            a = s2;
        }
        return a;
    }
    
    public final void i() {
        this.l = true;
    }
    
    public final JSONObject j() {
        return this.n;
    }
    
    public final String k() {
        return this.j.a;
    }
    
    static final class a implements Callable
    {
        private static boolean d;
        private static Object f;
        private StringBuilder a;
        private StringBuilder b;
        private String[] c;
        private Process e;
        private b g;
        private b h;
        
        static {
            a.d = false;
        }
        
        public a(final Object f) {
            this.a = new StringBuilder();
            this.b = new StringBuilder();
            this.e = null;
            a.f = f;
            if (Build$VERSION.SDK_INT >= 8) {
                (this.c = new String[5])[0] = "logcat";
                this.c[1] = "-t";
                this.c[2] = "100";
                this.c[3] = "-v";
                this.c[4] = "time";
                return;
            }
            (this.c = new String[4])[0] = "logcat";
            this.c[1] = "-d";
            this.c[2] = "-v";
            this.c[3] = "time";
        }
        
        public static boolean a() {
            return a.d;
        }
        
        public static void b() {
            a.d = true;
        }
        
        private StringBuilder d() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aconst_null    
            //     2: putfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //     5: aload_0        
            //     6: aconst_null    
            //     7: putfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //    10: getstatic       crittercism/android/i$a.d:Z
            //    13: ifne            208
            //    16: aload_0        
            //    17: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
            //    20: aload_0        
            //    21: getfield        crittercism/android/i$a.c:[Ljava/lang/String;
            //    24: invokevirtual   java/lang/Runtime.exec:([Ljava/lang/String;)Ljava/lang/Process;
            //    27: putfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    30: aload_0        
            //    31: new             Lcrittercism/android/i$b;
            //    34: dup            
            //    35: aload_0        
            //    36: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    39: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
            //    42: invokespecial   crittercism/android/i$b.<init>:(Ljava/io/InputStream;)V
            //    45: putfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //    48: aload_0        
            //    49: new             Lcrittercism/android/i$b;
            //    52: dup            
            //    53: aload_0        
            //    54: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    57: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
            //    60: invokespecial   crittercism/android/i$b.<init>:(Ljava/io/InputStream;)V
            //    63: putfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //    66: aload_0        
            //    67: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //    70: invokevirtual   crittercism/android/i$b.start:()V
            //    73: aload_0        
            //    74: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //    77: invokevirtual   crittercism/android/i$b.start:()V
            //    80: aload_0        
            //    81: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    84: invokevirtual   java/lang/Process.waitFor:()I
            //    87: pop            
            //    88: aload_0        
            //    89: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //    92: ifnull          126
            //    95: aload_0        
            //    96: aload_0        
            //    97: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //   100: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   103: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   106: new             Ljava/lang/StringBuilder;
            //   109: dup            
            //   110: ldc             "this.inputStreamStringBuilder.toString() = "
            //   112: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   115: aload_0        
            //   116: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   125: pop            
            //   126: aload_0        
            //   127: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   130: ifnull          164
            //   133: aload_0        
            //   134: aload_0        
            //   135: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   138: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   141: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   144: new             Ljava/lang/StringBuilder;
            //   147: dup            
            //   148: ldc             "this.errorStreamStringBuilder.toString() = "
            //   150: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   153: aload_0        
            //   154: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   157: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   163: pop            
            //   164: aload_0        
            //   165: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   168: ifnull          208
            //   171: aload_0        
            //   172: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   175: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
            //   178: invokevirtual   java/io/InputStream.close:()V
            //   181: aload_0        
            //   182: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   185: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
            //   188: invokevirtual   java/io/InputStream.close:()V
            //   191: aload_0        
            //   192: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   195: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
            //   198: invokevirtual   java/io/OutputStream.close:()V
            //   201: aload_0        
            //   202: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   205: invokevirtual   java/lang/Process.destroy:()V
            //   208: aload_0        
            //   209: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   212: areturn        
            //   213: astore_1       
            //   214: iconst_1       
            //   215: putstatic       crittercism/android/i$a.d:Z
            //   218: aload_0        
            //   219: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //   222: ifnull          256
            //   225: aload_0        
            //   226: aload_0        
            //   227: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //   230: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   233: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   236: new             Ljava/lang/StringBuilder;
            //   239: dup            
            //   240: ldc             "this.inputStreamStringBuilder.toString() = "
            //   242: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   245: aload_0        
            //   246: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   249: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   255: pop            
            //   256: aload_0        
            //   257: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   260: ifnull          294
            //   263: aload_0        
            //   264: aload_0        
            //   265: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   268: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   271: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   274: new             Ljava/lang/StringBuilder;
            //   277: dup            
            //   278: ldc             "this.errorStreamStringBuilder.toString() = "
            //   280: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   283: aload_0        
            //   284: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   287: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   293: pop            
            //   294: aload_0        
            //   295: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   298: ifnull          208
            //   301: aload_0        
            //   302: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   305: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
            //   308: invokevirtual   java/io/InputStream.close:()V
            //   311: aload_0        
            //   312: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   315: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
            //   318: invokevirtual   java/io/InputStream.close:()V
            //   321: aload_0        
            //   322: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   325: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
            //   328: invokevirtual   java/io/OutputStream.close:()V
            //   331: aload_0        
            //   332: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   335: invokevirtual   java/lang/Process.destroy:()V
            //   338: goto            208
            //   341: astore_1       
            //   342: goto            208
            //   345: astore_1       
            //   346: aload_0        
            //   347: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //   350: ifnull          384
            //   353: aload_0        
            //   354: aload_0        
            //   355: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //   358: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   361: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   364: new             Ljava/lang/StringBuilder;
            //   367: dup            
            //   368: ldc             "this.inputStreamStringBuilder.toString() = "
            //   370: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   373: aload_0        
            //   374: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
            //   377: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   383: pop            
            //   384: aload_0        
            //   385: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   388: ifnull          422
            //   391: aload_0        
            //   392: aload_0        
            //   393: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //   396: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
            //   399: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   402: new             Ljava/lang/StringBuilder;
            //   405: dup            
            //   406: ldc             "this.errorStreamStringBuilder.toString() = "
            //   408: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   411: aload_0        
            //   412: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
            //   415: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   421: pop            
            //   422: aload_0        
            //   423: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   426: ifnull          466
            //   429: aload_0        
            //   430: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   433: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
            //   436: invokevirtual   java/io/InputStream.close:()V
            //   439: aload_0        
            //   440: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   443: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
            //   446: invokevirtual   java/io/InputStream.close:()V
            //   449: aload_0        
            //   450: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   453: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
            //   456: invokevirtual   java/io/OutputStream.close:()V
            //   459: aload_0        
            //   460: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //   463: invokevirtual   java/lang/Process.destroy:()V
            //   466: aload_1        
            //   467: athrow         
            //   468: astore_2       
            //   469: goto            466
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  16     88     213    341    Ljava/lang/Exception;
            //  16     88     345    472    Any
            //  171    208    341    345    Ljava/lang/Exception;
            //  214    218    345    472    Any
            //  301    338    341    345    Ljava/lang/Exception;
            //  429    466    468    472    Ljava/lang/Exception;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0208:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public final void c() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: getstatic       crittercism/android/i$a.f:Ljava/lang/Object;
            //     3: astore_1       
            //     4: aload_1        
            //     5: monitorenter   
            //     6: aload_0        
            //     7: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
            //    10: invokevirtual   crittercism/android/i$b.b:()V
            //    13: aload_0        
            //    14: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
            //    17: invokevirtual   crittercism/android/i$b.b:()V
            //    20: aload_0        
            //    21: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    24: ifnull          57
            //    27: aload_0        
            //    28: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    31: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
            //    34: invokevirtual   java/io/InputStream.close:()V
            //    37: aload_0        
            //    38: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    41: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
            //    44: invokevirtual   java/io/InputStream.close:()V
            //    47: aload_0        
            //    48: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    51: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
            //    54: invokevirtual   java/io/OutputStream.close:()V
            //    57: aload_0        
            //    58: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    61: ifnull          71
            //    64: aload_0        
            //    65: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
            //    68: invokevirtual   java/lang/Process.destroy:()V
            //    71: aload_1        
            //    72: monitorexit    
            //    73: return         
            //    74: astore_2       
            //    75: aload_1        
            //    76: monitorexit    
            //    77: aload_2        
            //    78: athrow         
            //    79: astore_2       
            //    80: goto            71
            //    83: astore_2       
            //    84: goto            57
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  6      57     83     87     Ljava/lang/Exception;
            //  6      57     74     79     Any
            //  57     71     79     83     Ljava/lang/Exception;
            //  57     71     74     79     Any
            //  71     73     74     79     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
    }
    
    static final class b extends Thread
    {
        private InputStream a;
        private StringBuilder b;
        private BufferedReader c;
        
        public b(final InputStream a) {
            this.b = new StringBuilder();
            this.c = null;
            this.a = a;
        }
        
        public final StringBuilder a() {
            return this.b;
        }
        
        public final void b() {
            if (this.c == null) {
                return;
            }
            try {
                this.c.close();
            }
            catch (Exception ex) {
                this.c = null;
            }
        }
        
        @Override
        public final void run() {
            new String();
            this.c = new BufferedReader(new InputStreamReader(this.a));
            Label_0073: {
                try {
                    while (true) {
                        final String line = this.c.readLine();
                        if (line == null) {
                            break Label_0073;
                        }
                        this.b.append(line);
                        this.b.append("\n");
                    }
                }
                catch (Exception ex4) {
                    try {
                        this.c.close();
                        return;
                        try {
                            this.c.close();
                        }
                        catch (Exception ex) {
                            new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex.getClass().getName());
                        }
                    }
                    catch (Exception ex2) {
                        new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex2.getClass().getName());
                    }
                }
                finally {
                    try {
                        this.c.close();
                    }
                    catch (Exception ex3) {
                        new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex3.getClass().getName());
                    }
                }
            }
        }
    }
}
