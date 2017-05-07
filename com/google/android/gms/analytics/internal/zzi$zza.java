// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public class zzi$zza implements ServiceConnection
{
    final /* synthetic */ zzi zzMa;
    private volatile zzac zzMb;
    private volatile boolean zzMc;
    
    protected zzi$zza(final zzi zzMa) {
        this.zzMa = zzMa;
    }
    
    public void onServiceConnected(final ComponentName p0, final IBinder p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "AnalyticsServiceConnection.onServiceConnected"
        //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzch:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_2        
        //     8: ifnonnull       27
        //    11: aload_0        
        //    12: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    15: ldc             "Service connected with null binder"
        //    17: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbc:(Ljava/lang/String;)V
        //    20: aload_0        
        //    21: invokevirtual   java/lang/Object.notifyAll:()V
        //    24: aload_0        
        //    25: monitorexit    
        //    26: return         
        //    27: aconst_null    
        //    28: astore          4
        //    30: aconst_null    
        //    31: astore_3       
        //    32: aload           4
        //    34: astore_1       
        //    35: aload_2        
        //    36: invokeinterface android/os/IBinder.getInterfaceDescriptor:()Ljava/lang/String;
        //    41: astore          5
        //    43: aload           4
        //    45: astore_1       
        //    46: ldc             "com.google.android.gms.analytics.internal.IAnalyticsService"
        //    48: aload           5
        //    50: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    53: ifeq            113
        //    56: aload           4
        //    58: astore_1       
        //    59: aload_2        
        //    60: invokestatic    com/google/android/gms/analytics/internal/zzac$zza.zzae:(Landroid/os/IBinder;)Lcom/google/android/gms/analytics/internal/zzac;
        //    63: astore_2       
        //    64: aload_2        
        //    65: astore_1       
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    70: ldc             "Bound to IAnalyticsService interface"
        //    72: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzaY:(Ljava/lang/String;)V
        //    75: aload_2        
        //    76: astore_1       
        //    77: aload_1        
        //    78: ifnonnull       152
        //    81: invokestatic    com/google/android/gms/common/stats/zzb.zzpD:()Lcom/google/android/gms/common/stats/zzb;
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    88: invokevirtual   com/google/android/gms/analytics/internal/zzi.getContext:()Landroid/content/Context;
        //    91: aload_0        
        //    92: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    95: invokestatic    com/google/android/gms/analytics/internal/zzi.zza:(Lcom/google/android/gms/analytics/internal/zzi;)Lcom/google/android/gms/analytics/internal/zzi$zza;
        //    98: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //   101: aload_0        
        //   102: invokevirtual   java/lang/Object.notifyAll:()V
        //   105: aload_0        
        //   106: monitorexit    
        //   107: return         
        //   108: astore_1       
        //   109: aload_0        
        //   110: monitorexit    
        //   111: aload_1        
        //   112: athrow         
        //   113: aload           4
        //   115: astore_1       
        //   116: aload_0        
        //   117: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   120: ldc             "Got binder with a wrong descriptor"
        //   122: aload           5
        //   124: invokevirtual   com/google/android/gms/analytics/internal/zzi.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   127: aload_3        
        //   128: astore_1       
        //   129: goto            77
        //   132: astore_2       
        //   133: aload_0        
        //   134: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   137: ldc             "Service connect failed to get IAnalyticsService"
        //   139: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbc:(Ljava/lang/String;)V
        //   142: goto            77
        //   145: astore_1       
        //   146: aload_0        
        //   147: invokevirtual   java/lang/Object.notifyAll:()V
        //   150: aload_1        
        //   151: athrow         
        //   152: aload_0        
        //   153: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMc:Z
        //   156: ifne            190
        //   159: aload_0        
        //   160: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   163: ldc             "onServiceConnected received after the timeout limit"
        //   165: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbb:(Ljava/lang/String;)V
        //   168: aload_0        
        //   169: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   172: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzig:()Lcom/google/android/gms/internal/zzof;
        //   175: new             Lcom/google/android/gms/analytics/internal/zzi$zza$1;
        //   178: dup            
        //   179: aload_0        
        //   180: aload_1        
        //   181: invokespecial   com/google/android/gms/analytics/internal/zzi$zza$1.<init>:(Lcom/google/android/gms/analytics/internal/zzi$zza;Lcom/google/android/gms/analytics/internal/zzac;)V
        //   184: invokevirtual   com/google/android/gms/internal/zzof.zzf:(Ljava/lang/Runnable;)V
        //   187: goto            101
        //   190: aload_0        
        //   191: aload_1        
        //   192: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMb:Lcom/google/android/gms/analytics/internal/zzac;
        //   195: goto            101
        //   198: astore_1       
        //   199: goto            101
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  11     20     145    152    Any
        //  20     26     108    113    Any
        //  35     43     132    145    Landroid/os/RemoteException;
        //  35     43     145    152    Any
        //  46     56     132    145    Landroid/os/RemoteException;
        //  46     56     145    152    Any
        //  59     64     132    145    Landroid/os/RemoteException;
        //  59     64     145    152    Any
        //  66     75     132    145    Landroid/os/RemoteException;
        //  66     75     145    152    Any
        //  81     101    198    202    Ljava/lang/IllegalArgumentException;
        //  81     101    145    152    Any
        //  101    107    108    113    Any
        //  109    111    108    113    Any
        //  116    127    132    145    Landroid/os/RemoteException;
        //  116    127    145    152    Any
        //  133    142    145    152    Any
        //  146    152    108    113    Any
        //  152    187    145    152    Any
        //  190    195    145    152    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public void onServiceDisconnected(final ComponentName componentName) {
        zzx.zzch("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzMa.zzig().zzf(new zzi$zza$2(this, componentName));
    }
    
    public zzac zziD() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //     4: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzic:()V
        //     7: new             Landroid/content/Intent;
        //    10: dup            
        //    11: ldc             "com.google.android.gms.analytics.service.START"
        //    13: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //    16: astore_2       
        //    17: aload_2        
        //    18: new             Landroid/content/ComponentName;
        //    21: dup            
        //    22: ldc             "com.google.android.gms"
        //    24: ldc             "com.google.android.gms.analytics.service.AnalyticsService"
        //    26: invokespecial   android/content/ComponentName.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    29: invokevirtual   android/content/Intent.setComponent:(Landroid/content/ComponentName;)Landroid/content/Intent;
        //    32: pop            
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    37: invokevirtual   com/google/android/gms/analytics/internal/zzi.getContext:()Landroid/content/Context;
        //    40: astore_3       
        //    41: aload_2        
        //    42: ldc             "app_package_name"
        //    44: aload_3        
        //    45: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    48: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //    51: pop            
        //    52: invokestatic    com/google/android/gms/common/stats/zzb.zzpD:()Lcom/google/android/gms/common/stats/zzb;
        //    55: astore          4
        //    57: aload_0        
        //    58: monitorenter   
        //    59: aload_0        
        //    60: aconst_null    
        //    61: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMb:Lcom/google/android/gms/analytics/internal/zzac;
        //    64: aload_0        
        //    65: iconst_1       
        //    66: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMc:Z
        //    69: aload           4
        //    71: aload_3        
        //    72: aload_2        
        //    73: aload_0        
        //    74: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    77: invokestatic    com/google/android/gms/analytics/internal/zzi.zza:(Lcom/google/android/gms/analytics/internal/zzi;)Lcom/google/android/gms/analytics/internal/zzi$zza;
        //    80: sipush          129
        //    83: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
        //    86: istore_1       
        //    87: aload_0        
        //    88: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //    91: ldc             "Bind to service requested"
        //    93: iload_1        
        //    94: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    97: invokevirtual   com/google/android/gms/analytics/internal/zzi.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   100: iload_1        
        //   101: ifne            113
        //   104: aload_0        
        //   105: iconst_0       
        //   106: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMc:Z
        //   109: aload_0        
        //   110: monitorexit    
        //   111: aconst_null    
        //   112: areturn        
        //   113: aload_0        
        //   114: aload_0        
        //   115: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   118: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzif:()Lcom/google/android/gms/analytics/internal/zzr;
        //   121: invokevirtual   com/google/android/gms/analytics/internal/zzr.zzjG:()J
        //   124: invokevirtual   java/lang/Object.wait:(J)V
        //   127: aload_0        
        //   128: iconst_0       
        //   129: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMc:Z
        //   132: aload_0        
        //   133: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMb:Lcom/google/android/gms/analytics/internal/zzac;
        //   136: astore_2       
        //   137: aload_0        
        //   138: aconst_null    
        //   139: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzMb:Lcom/google/android/gms/analytics/internal/zzac;
        //   142: aload_2        
        //   143: ifnonnull       155
        //   146: aload_0        
        //   147: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   150: ldc             "Successfully bound to service but never got onServiceConnected callback"
        //   152: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbc:(Ljava/lang/String;)V
        //   155: aload_0        
        //   156: monitorexit    
        //   157: aload_2        
        //   158: areturn        
        //   159: astore_2       
        //   160: aload_0        
        //   161: monitorexit    
        //   162: aload_2        
        //   163: athrow         
        //   164: astore_2       
        //   165: aload_0        
        //   166: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzMa:Lcom/google/android/gms/analytics/internal/zzi;
        //   169: ldc             "Wait for service connect was interrupted"
        //   171: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbb:(Ljava/lang/String;)V
        //   174: goto            127
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  59     100    159    164    Any
        //  104    111    159    164    Any
        //  113    127    164    177    Ljava/lang/InterruptedException;
        //  113    127    159    164    Any
        //  127    142    159    164    Any
        //  146    155    159    164    Any
        //  155    157    159    164    Any
        //  160    162    159    164    Any
        //  165    174    159    164    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
