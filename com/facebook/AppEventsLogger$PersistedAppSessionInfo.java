// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility$FetchedAppSettings;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import com.facebook.internal.Logger;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import java.util.Map;

class AppEventsLogger$PersistedAppSessionInfo
{
    private static final Runnable appSessionInfoFlushRunnable;
    private static Map<AppEventsLogger$AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
    private static boolean hasChanges;
    private static boolean isLoaded;
    private static final Object staticLock;
    
    static {
        staticLock = new Object();
        AppEventsLogger$PersistedAppSessionInfo.hasChanges = false;
        AppEventsLogger$PersistedAppSessionInfo.isLoaded = false;
        appSessionInfoFlushRunnable = new AppEventsLogger$PersistedAppSessionInfo$1();
    }
    
    private static FacebookTimeSpentData getTimeSpentData(final Context context, final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair) {
        restoreAppSessionInformation(context);
        FacebookTimeSpentData facebookTimeSpentData;
        if ((facebookTimeSpentData = AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap.get(appEventsLogger$AccessTokenAppIdPair)) == null) {
            facebookTimeSpentData = new FacebookTimeSpentData();
            AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap.put(appEventsLogger$AccessTokenAppIdPair, facebookTimeSpentData);
        }
        return facebookTimeSpentData;
    }
    
    static void onResume(final Context context, final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n, final String s) {
        synchronized (AppEventsLogger$PersistedAppSessionInfo.staticLock) {
            getTimeSpentData(context, appEventsLogger$AccessTokenAppIdPair).onResume(appEventsLogger, n, s);
            onTimeSpentDataUpdate();
        }
    }
    
    private static void onTimeSpentDataUpdate() {
        if (!AppEventsLogger$PersistedAppSessionInfo.hasChanges) {
            AppEventsLogger$PersistedAppSessionInfo.hasChanges = true;
            AppEventsLogger.backgroundExecutor.schedule(AppEventsLogger$PersistedAppSessionInfo.appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
        }
    }
    
    private static void restoreAppSessionInformation(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
        //     5: astore          5
        //     7: aload           5
        //     9: monitorenter   
        //    10: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //    13: istore_1       
        //    14: iload_1        
        //    15: ifne            91
        //    18: new             Ljava/io/ObjectInputStream;
        //    21: dup            
        //    22: aload_0        
        //    23: ldc             "AppEventsLogger.persistedsessioninfo"
        //    25: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    28: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    31: astore_2       
        //    32: aload_2        
        //    33: astore_3       
        //    34: aload_2        
        //    35: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    38: checkcast       Ljava/util/HashMap;
        //    41: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    44: aload_2        
        //    45: astore_3       
        //    46: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
        //    49: ldc             "AppEvents"
        //    51: ldc             "App session info loaded"
        //    53: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    56: aload_2        
        //    57: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    60: aload_0        
        //    61: ldc             "AppEventsLogger.persistedsessioninfo"
        //    63: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    66: pop            
        //    67: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    70: ifnonnull       83
        //    73: new             Ljava/util/HashMap;
        //    76: dup            
        //    77: invokespecial   java/util/HashMap.<init>:()V
        //    80: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    83: iconst_1       
        //    84: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //    87: iconst_0       
        //    88: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    91: aload           5
        //    93: monitorexit    
        //    94: return         
        //    95: aload_2        
        //    96: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    99: aload_0        
        //   100: ldc             "AppEventsLogger.persistedsessioninfo"
        //   102: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   105: pop            
        //   106: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   109: ifnonnull       122
        //   112: new             Ljava/util/HashMap;
        //   115: dup            
        //   116: invokespecial   java/util/HashMap.<init>:()V
        //   119: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   122: iconst_1       
        //   123: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   126: iconst_0       
        //   127: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   130: goto            91
        //   133: astore_0       
        //   134: aload           5
        //   136: monitorexit    
        //   137: aload_0        
        //   138: athrow         
        //   139: astore          4
        //   141: aconst_null    
        //   142: astore_2       
        //   143: aload_2        
        //   144: astore_3       
        //   145: invokestatic    com/facebook/AppEventsLogger.access$1300:()Ljava/lang/String;
        //   148: new             Ljava/lang/StringBuilder;
        //   151: dup            
        //   152: invokespecial   java/lang/StringBuilder.<init>:()V
        //   155: ldc             "Got unexpected exception: "
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: aload           4
        //   162: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   171: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   174: pop            
        //   175: aload_2        
        //   176: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   179: aload_0        
        //   180: ldc             "AppEventsLogger.persistedsessioninfo"
        //   182: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   185: pop            
        //   186: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   189: ifnonnull       202
        //   192: new             Ljava/util/HashMap;
        //   195: dup            
        //   196: invokespecial   java/util/HashMap.<init>:()V
        //   199: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   202: iconst_1       
        //   203: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   206: iconst_0       
        //   207: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   210: goto            91
        //   213: aload_3        
        //   214: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   217: aload_0        
        //   218: ldc             "AppEventsLogger.persistedsessioninfo"
        //   220: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   223: pop            
        //   224: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   227: ifnonnull       240
        //   230: new             Ljava/util/HashMap;
        //   233: dup            
        //   234: invokespecial   java/util/HashMap.<init>:()V
        //   237: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   240: iconst_1       
        //   241: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   244: iconst_0       
        //   245: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   248: aload_2        
        //   249: athrow         
        //   250: astore_2       
        //   251: goto            213
        //   254: astore          4
        //   256: goto            143
        //   259: astore_3       
        //   260: goto            95
        //   263: astore_2       
        //   264: aload_3        
        //   265: astore_2       
        //   266: goto            95
        //   269: astore_2       
        //   270: aconst_null    
        //   271: astore_3       
        //   272: goto            213
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  10     14     133    139    Any
        //  18     32     263    269    Ljava/io/FileNotFoundException;
        //  18     32     139    143    Ljava/lang/Exception;
        //  18     32     269    275    Any
        //  34     44     259    263    Ljava/io/FileNotFoundException;
        //  34     44     254    259    Ljava/lang/Exception;
        //  34     44     250    254    Any
        //  46     56     259    263    Ljava/io/FileNotFoundException;
        //  46     56     254    259    Ljava/lang/Exception;
        //  46     56     250    254    Any
        //  56     83     133    139    Any
        //  83     91     133    139    Any
        //  91     94     133    139    Any
        //  95     122    133    139    Any
        //  122    130    133    139    Any
        //  134    137    133    139    Any
        //  145    175    250    254    Any
        //  175    202    133    139    Any
        //  202    210    133    139    Any
        //  213    240    133    139    Any
        //  240    250    133    139    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 136, Size: 136
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    static void saveAppSessionInformation(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
        //     3: astore          4
        //     5: aload           4
        //     7: monitorenter   
        //     8: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    11: istore_1       
        //    12: iload_1        
        //    13: ifeq            69
        //    16: new             Ljava/io/ObjectOutputStream;
        //    19: dup            
        //    20: new             Ljava/io/BufferedOutputStream;
        //    23: dup            
        //    24: aload_0        
        //    25: ldc             "AppEventsLogger.persistedsessioninfo"
        //    27: iconst_0       
        //    28: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    31: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    34: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    37: astore_2       
        //    38: aload_2        
        //    39: astore_0       
        //    40: aload_2        
        //    41: getstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    44: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    47: aload_2        
        //    48: astore_0       
        //    49: iconst_0       
        //    50: putstatic       com/facebook/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    53: aload_2        
        //    54: astore_0       
        //    55: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
        //    58: ldc             "AppEvents"
        //    60: ldc             "App session info saved"
        //    62: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    65: aload_2        
        //    66: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    69: aload           4
        //    71: monitorexit    
        //    72: return         
        //    73: astore_3       
        //    74: aconst_null    
        //    75: astore_2       
        //    76: aload_2        
        //    77: astore_0       
        //    78: invokestatic    com/facebook/AppEventsLogger.access$1300:()Ljava/lang/String;
        //    81: new             Ljava/lang/StringBuilder;
        //    84: dup            
        //    85: invokespecial   java/lang/StringBuilder.<init>:()V
        //    88: ldc             "Got unexpected exception: "
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    93: aload_3        
        //    94: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   103: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   106: pop            
        //   107: aload_2        
        //   108: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   111: goto            69
        //   114: astore_0       
        //   115: aload           4
        //   117: monitorexit    
        //   118: aload_0        
        //   119: athrow         
        //   120: astore_2       
        //   121: aconst_null    
        //   122: astore_0       
        //   123: aload_0        
        //   124: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   127: aload_2        
        //   128: athrow         
        //   129: astore_2       
        //   130: goto            123
        //   133: astore_3       
        //   134: goto            76
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      12     114    120    Any
        //  16     38     73     76     Ljava/lang/Exception;
        //  16     38     120    123    Any
        //  40     47     133    137    Ljava/lang/Exception;
        //  40     47     129    133    Any
        //  49     53     133    137    Ljava/lang/Exception;
        //  49     53     129    133    Any
        //  55     65     133    137    Ljava/lang/Exception;
        //  55     65     129    133    Any
        //  65     69     114    120    Any
        //  69     72     114    120    Any
        //  78     107    129    133    Any
        //  107    111    114    120    Any
        //  115    118    114    120    Any
        //  123    129    114    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
}
