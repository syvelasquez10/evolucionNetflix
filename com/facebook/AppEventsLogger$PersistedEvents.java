// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import android.content.Context;

class AppEventsLogger$PersistedEvents
{
    private static Object staticLock;
    private Context context;
    private HashMap<AppEventsLogger$AccessTokenAppIdPair, List<AppEventsLogger$AppEvent>> persistedEvents;
    
    static {
        AppEventsLogger$PersistedEvents.staticLock = new Object();
    }
    
    private AppEventsLogger$PersistedEvents(final Context context) {
        this.persistedEvents = new HashMap<AppEventsLogger$AccessTokenAppIdPair, List<AppEventsLogger$AppEvent>>();
        this.context = context;
    }
    
    public static void persistEvents(final Context context, final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair, final AppEventsLogger$SessionEventsState appEventsLogger$SessionEventsState) {
        final HashMap<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState> hashMap = new HashMap<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState>();
        hashMap.put(appEventsLogger$AccessTokenAppIdPair, appEventsLogger$SessionEventsState);
        persistEvents(context, hashMap);
    }
    
    public static void persistEvents(final Context context, final Map<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState> map) {
        synchronized (AppEventsLogger$PersistedEvents.staticLock) {
            final AppEventsLogger$PersistedEvents andClearStore = readAndClearStore(context);
            for (final Map.Entry<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState> entry : map.entrySet()) {
                final List<AppEventsLogger$AppEvent> eventsToPersist = entry.getValue().getEventsToPersist();
                if (eventsToPersist.size() != 0) {
                    andClearStore.addEvents(entry.getKey(), eventsToPersist);
                }
            }
        }
        final AppEventsLogger$PersistedEvents appEventsLogger$PersistedEvents;
        appEventsLogger$PersistedEvents.write();
    }
    // monitorexit(o)
    
    public static AppEventsLogger$PersistedEvents readAndClearStore(final Context context) {
        synchronized (AppEventsLogger$PersistedEvents.staticLock) {
            final AppEventsLogger$PersistedEvents appEventsLogger$PersistedEvents = new AppEventsLogger$PersistedEvents(context);
            appEventsLogger$PersistedEvents.readAndClearStore();
            return appEventsLogger$PersistedEvents;
        }
    }
    
    private void readAndClearStore() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: new             Ljava/io/ObjectInputStream;
        //     5: dup            
        //     6: new             Ljava/io/BufferedInputStream;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/facebook/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    14: ldc             "AppEventsLogger.persistedevents"
        //    16: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    19: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    22: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    25: astore_1       
        //    26: aload_1        
        //    27: astore_2       
        //    28: aload_1        
        //    29: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    32: checkcast       Ljava/util/HashMap;
        //    35: astore_3       
        //    36: aload_1        
        //    37: astore_2       
        //    38: aload_0        
        //    39: getfield        com/facebook/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    42: ldc             "AppEventsLogger.persistedevents"
        //    44: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //    47: invokevirtual   java/io/File.delete:()Z
        //    50: pop            
        //    51: aload_1        
        //    52: astore_2       
        //    53: aload_0        
        //    54: aload_3        
        //    55: putfield        com/facebook/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
        //    58: aload_1        
        //    59: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    62: return         
        //    63: astore_1       
        //    64: aload_2        
        //    65: astore_1       
        //    66: aload_1        
        //    67: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    70: return         
        //    71: astore_3       
        //    72: aconst_null    
        //    73: astore_1       
        //    74: aload_1        
        //    75: astore_2       
        //    76: invokestatic    com/facebook/AppEventsLogger.access$1300:()Ljava/lang/String;
        //    79: new             Ljava/lang/StringBuilder;
        //    82: dup            
        //    83: invokespecial   java/lang/StringBuilder.<init>:()V
        //    86: ldc             "Got unexpected exception: "
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: aload_3        
        //    92: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   101: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   104: pop            
        //   105: aload_1        
        //   106: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   109: return         
        //   110: astore_1       
        //   111: aconst_null    
        //   112: astore_2       
        //   113: aload_2        
        //   114: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   117: aload_1        
        //   118: athrow         
        //   119: astore_1       
        //   120: goto            113
        //   123: astore_3       
        //   124: goto            74
        //   127: astore_2       
        //   128: goto            66
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  2      26     63     66     Ljava/io/FileNotFoundException;
        //  2      26     71     74     Ljava/lang/Exception;
        //  2      26     110    113    Any
        //  28     36     127    131    Ljava/io/FileNotFoundException;
        //  28     36     123    127    Ljava/lang/Exception;
        //  28     36     119    123    Any
        //  38     51     127    131    Ljava/io/FileNotFoundException;
        //  38     51     123    127    Ljava/lang/Exception;
        //  38     51     119    123    Any
        //  53     58     127    131    Ljava/io/FileNotFoundException;
        //  53     58     123    127    Ljava/lang/Exception;
        //  53     58     119    123    Any
        //  76     105    119    123    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    private void write() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/ObjectOutputStream;
        //     3: dup            
        //     4: new             Ljava/io/BufferedOutputStream;
        //     7: dup            
        //     8: aload_0        
        //     9: getfield        com/facebook/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    12: ldc             "AppEventsLogger.persistedevents"
        //    14: iconst_0       
        //    15: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    18: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    21: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    24: astore_2       
        //    25: aload_2        
        //    26: astore_1       
        //    27: aload_2        
        //    28: aload_0        
        //    29: getfield        com/facebook/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
        //    32: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    35: aload_2        
        //    36: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    39: return         
        //    40: astore_3       
        //    41: aconst_null    
        //    42: astore_2       
        //    43: aload_2        
        //    44: astore_1       
        //    45: invokestatic    com/facebook/AppEventsLogger.access$1300:()Ljava/lang/String;
        //    48: new             Ljava/lang/StringBuilder;
        //    51: dup            
        //    52: invokespecial   java/lang/StringBuilder.<init>:()V
        //    55: ldc             "Got unexpected exception: "
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: aload_3        
        //    61: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    70: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    73: pop            
        //    74: aload_2        
        //    75: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    78: return         
        //    79: astore_2       
        //    80: aconst_null    
        //    81: astore_1       
        //    82: aload_1        
        //    83: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    86: aload_2        
        //    87: athrow         
        //    88: astore_2       
        //    89: goto            82
        //    92: astore_3       
        //    93: goto            43
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      25     40     43     Ljava/lang/Exception;
        //  0      25     79     82     Any
        //  27     35     92     96     Ljava/lang/Exception;
        //  27     35     88     92     Any
        //  45     74     88     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    public void addEvents(final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair, final List<AppEventsLogger$AppEvent> list) {
        if (!this.persistedEvents.containsKey(appEventsLogger$AccessTokenAppIdPair)) {
            this.persistedEvents.put(appEventsLogger$AccessTokenAppIdPair, new ArrayList<AppEventsLogger$AppEvent>());
        }
        this.persistedEvents.get(appEventsLogger$AccessTokenAppIdPair).addAll(list);
    }
    
    public List<AppEventsLogger$AppEvent> getEvents(final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair) {
        return this.persistedEvents.get(appEventsLogger$AccessTokenAppIdPair);
    }
    
    public Set<AppEventsLogger$AccessTokenAppIdPair> keySet() {
        return this.persistedEvents.keySet();
    }
}
