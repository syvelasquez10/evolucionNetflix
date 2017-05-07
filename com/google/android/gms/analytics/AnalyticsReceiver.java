// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.Intent;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import com.google.android.gms.internal.zzqd;
import android.content.BroadcastReceiver;

public final class AnalyticsReceiver extends BroadcastReceiver
{
    static zzqd zzKc;
    static Boolean zzKd;
    static Object zzpm;
    
    static {
        AnalyticsReceiver.zzpm = new Object();
    }
    
    public static boolean zzV(final Context context) {
        zzx.zzv(context);
        if (AnalyticsReceiver.zzKd != null) {
            return AnalyticsReceiver.zzKd;
        }
        final boolean zza = zzam.zza(context, AnalyticsReceiver.class, false);
        AnalyticsReceiver.zzKd = zza;
        return zza;
    }
    
    public void onReceive(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/analytics/internal/zzf.zzX:(Landroid/content/Context;)Lcom/google/android/gms/analytics/internal/zzf;
        //     4: astore          5
        //     6: aload           5
        //     8: invokevirtual   com/google/android/gms/analytics/internal/zzf.zzie:()Lcom/google/android/gms/analytics/internal/zzaf;
        //    11: astore          4
        //    13: aload_2        
        //    14: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //    17: astore_2       
        //    18: aload           5
        //    20: invokevirtual   com/google/android/gms/analytics/internal/zzf.zzif:()Lcom/google/android/gms/analytics/internal/zzr;
        //    23: invokevirtual   com/google/android/gms/analytics/internal/zzr.zzjk:()Z
        //    26: ifeq            91
        //    29: aload           4
        //    31: ldc             "Device AnalyticsReceiver got"
        //    33: aload_2        
        //    34: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //    37: ldc             "com.google.android.gms.analytics.ANALYTICS_DISPATCH"
        //    39: aload_2        
        //    40: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    43: ifeq            90
        //    46: aload_1        
        //    47: invokestatic    com/google/android/gms/analytics/AnalyticsService.zzW:(Landroid/content/Context;)Z
        //    50: istore_3       
        //    51: new             Landroid/content/Intent;
        //    54: dup            
        //    55: aload_1        
        //    56: ldc             Lcom/google/android/gms/analytics/AnalyticsService;.class
        //    58: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //    61: astore          5
        //    63: aload           5
        //    65: ldc             "com.google.android.gms.analytics.ANALYTICS_DISPATCH"
        //    67: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    70: pop            
        //    71: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzpm:Ljava/lang/Object;
        //    74: astore_2       
        //    75: aload_2        
        //    76: monitorenter   
        //    77: aload_1        
        //    78: aload           5
        //    80: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //    83: pop            
        //    84: iload_3        
        //    85: ifne            102
        //    88: aload_2        
        //    89: monitorexit    
        //    90: return         
        //    91: aload           4
        //    93: ldc             "Local AnalyticsReceiver got"
        //    95: aload_2        
        //    96: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //    99: goto            37
        //   102: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzKc:Lcom/google/android/gms/internal/zzqd;
        //   105: ifnonnull       129
        //   108: new             Lcom/google/android/gms/internal/zzqd;
        //   111: dup            
        //   112: aload_1        
        //   113: iconst_1       
        //   114: ldc             "Analytics WakeLock"
        //   116: invokespecial   com/google/android/gms/internal/zzqd.<init>:(Landroid/content/Context;ILjava/lang/String;)V
        //   119: putstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzKc:Lcom/google/android/gms/internal/zzqd;
        //   122: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzKc:Lcom/google/android/gms/internal/zzqd;
        //   125: iconst_0       
        //   126: invokevirtual   com/google/android/gms/internal/zzqd.setReferenceCounted:(Z)V
        //   129: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzKc:Lcom/google/android/gms/internal/zzqd;
        //   132: ldc2_w          1000
        //   135: invokevirtual   com/google/android/gms/internal/zzqd.acquire:(J)V
        //   138: aload_2        
        //   139: monitorexit    
        //   140: return         
        //   141: astore_1       
        //   142: aload_2        
        //   143: monitorexit    
        //   144: aload_1        
        //   145: athrow         
        //   146: astore_1       
        //   147: aload           4
        //   149: ldc             "Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions."
        //   151: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zzbb:(Ljava/lang/String;)V
        //   154: goto            138
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  77     84     141    146    Any
        //  88     90     141    146    Any
        //  102    129    146    157    Ljava/lang/SecurityException;
        //  102    129    141    146    Any
        //  129    138    146    157    Ljava/lang/SecurityException;
        //  129    138    141    146    Any
        //  138    140    141    146    Any
        //  142    144    141    146    Any
        //  147    154    141    146    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0102:
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
