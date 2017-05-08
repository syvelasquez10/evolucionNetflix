// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.PendingIntent;
import android.content.Context;
import android.app.Notification;
import java.lang.reflect.Method;
import android.annotation.TargetApi;

@TargetApi(9)
public class NotificationCompatBase
{
    private static Method sSetLatestEventInfo;
    
    public static Notification add(final Notification p0, final Context p1, final CharSequence p2, final CharSequence p3, final PendingIntent p4, final PendingIntent p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/NotificationCompatBase.sSetLatestEventInfo:Ljava/lang/reflect/Method;
        //     3: ifnonnull       40
        //     6: ldc             Landroid/app/Notification;.class
        //     8: ldc             "setLatestEventInfo"
        //    10: iconst_4       
        //    11: anewarray       Ljava/lang/Class;
        //    14: dup            
        //    15: iconst_0       
        //    16: ldc             Landroid/content/Context;.class
        //    18: aastore        
        //    19: dup            
        //    20: iconst_1       
        //    21: ldc             Ljava/lang/CharSequence;.class
        //    23: aastore        
        //    24: dup            
        //    25: iconst_2       
        //    26: ldc             Ljava/lang/CharSequence;.class
        //    28: aastore        
        //    29: dup            
        //    30: iconst_3       
        //    31: ldc             Landroid/app/PendingIntent;.class
        //    33: aastore        
        //    34: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    37: putstatic       android/support/v4/app/NotificationCompatBase.sSetLatestEventInfo:Ljava/lang/reflect/Method;
        //    40: getstatic       android/support/v4/app/NotificationCompatBase.sSetLatestEventInfo:Ljava/lang/reflect/Method;
        //    43: aload_0        
        //    44: iconst_4       
        //    45: anewarray       Ljava/lang/Object;
        //    48: dup            
        //    49: iconst_0       
        //    50: aload_1        
        //    51: aastore        
        //    52: dup            
        //    53: iconst_1       
        //    54: aload_2        
        //    55: aastore        
        //    56: dup            
        //    57: iconst_2       
        //    58: aload_3        
        //    59: aastore        
        //    60: dup            
        //    61: iconst_3       
        //    62: aload           4
        //    64: aastore        
        //    65: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    68: pop            
        //    69: aload_0        
        //    70: aload           5
        //    72: putfield        android/app/Notification.fullScreenIntent:Landroid/app/PendingIntent;
        //    75: aload_0        
        //    76: areturn        
        //    77: astore_0       
        //    78: new             Ljava/lang/RuntimeException;
        //    81: dup            
        //    82: aload_0        
        //    83: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    86: athrow         
        //    87: astore_0       
        //    88: new             Ljava/lang/RuntimeException;
        //    91: dup            
        //    92: aload_0        
        //    93: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    96: athrow         
        //    97: astore_0       
        //    98: goto            88
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  6      40     77     87     Ljava/lang/NoSuchMethodException;
        //  40     69     87     88     Ljava/lang/IllegalAccessException;
        //  40     69     97     101    Ljava/lang/reflect/InvocationTargetException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
