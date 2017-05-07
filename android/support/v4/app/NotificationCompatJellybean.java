// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcelable;
import android.app.Notification$Builder;
import android.app.Notification;
import android.util.SparseArray;
import android.os.Bundle;
import java.util.List;
import java.util.Iterator;
import android.app.Notification$InboxStyle;
import java.util.ArrayList;
import android.app.Notification$BigTextStyle;
import android.app.Notification$BigPictureStyle;
import android.graphics.Bitmap;
import java.lang.reflect.Field;

class NotificationCompatJellybean
{
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;
    
    static {
        sExtrasLock = new Object();
        sActionsLock = new Object();
    }
    
    public static void addBigPictureStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final Bitmap bitmap, final Bitmap bitmap2, final boolean b2) {
        final Notification$BigPictureStyle bigPicture = new Notification$BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle).bigPicture(bitmap);
        if (b2) {
            bigPicture.bigLargeIcon(bitmap2);
        }
        if (b) {
            bigPicture.setSummaryText(summaryText);
        }
    }
    
    public static void addBigTextStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final CharSequence charSequence) {
        final Notification$BigTextStyle bigText = new Notification$BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle).bigText(charSequence);
        if (b) {
            bigText.setSummaryText(summaryText);
        }
    }
    
    public static void addInboxStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final ArrayList<CharSequence> list) {
        final Notification$InboxStyle setBigContentTitle = new Notification$InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle);
        if (b) {
            setBigContentTitle.setSummaryText(summaryText);
        }
        final Iterator<CharSequence> iterator = list.iterator();
        while (iterator.hasNext()) {
            setBigContentTitle.addLine((CharSequence)iterator.next());
        }
    }
    
    public static SparseArray<Bundle> buildActionExtrasMap(final List<Bundle> list) {
        SparseArray sparseArray = null;
        SparseArray sparseArray2;
        for (int size = list.size(), i = 0; i < size; ++i, sparseArray = sparseArray2) {
            final Bundle bundle = list.get(i);
            sparseArray2 = sparseArray;
            if (bundle != null) {
                if ((sparseArray2 = sparseArray) == null) {
                    sparseArray2 = new SparseArray();
                }
                sparseArray2.put(i, (Object)bundle);
            }
        }
        return (SparseArray<Bundle>)sparseArray;
    }
    
    public static Bundle getExtras(final Notification p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasLock:Ljava/lang/Object;
        //     3: astore_3       
        //     4: aload_3        
        //     5: monitorenter   
        //     6: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //     9: ifeq            16
        //    12: aload_3        
        //    13: monitorexit    
        //    14: aconst_null    
        //    15: areturn        
        //    16: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    19: ifnonnull       67
        //    22: ldc             Landroid/app/Notification;.class
        //    24: ldc             "extras"
        //    26: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    29: astore_1       
        //    30: ldc             Landroid/os/Bundle;.class
        //    32: aload_1        
        //    33: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //    36: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    39: ifne            58
        //    42: ldc             "NotificationCompat"
        //    44: ldc             "Notification.extras field is not of type Bundle"
        //    46: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    49: pop            
        //    50: iconst_1       
        //    51: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //    54: aload_3        
        //    55: monitorexit    
        //    56: aconst_null    
        //    57: areturn        
        //    58: aload_1        
        //    59: iconst_1       
        //    60: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    63: aload_1        
        //    64: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    67: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    70: aload_0        
        //    71: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    74: checkcast       Landroid/os/Bundle;
        //    77: astore_2       
        //    78: aload_2        
        //    79: astore_1       
        //    80: aload_2        
        //    81: ifnonnull       100
        //    84: new             Landroid/os/Bundle;
        //    87: dup            
        //    88: invokespecial   android/os/Bundle.<init>:()V
        //    91: astore_1       
        //    92: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    95: aload_0        
        //    96: aload_1        
        //    97: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   100: aload_3        
        //   101: monitorexit    
        //   102: aload_1        
        //   103: areturn        
        //   104: astore_0       
        //   105: aload_3        
        //   106: monitorexit    
        //   107: aload_0        
        //   108: athrow         
        //   109: astore_0       
        //   110: ldc             "NotificationCompat"
        //   112: ldc             "Unable to access notification extras"
        //   114: aload_0        
        //   115: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   118: pop            
        //   119: iconst_1       
        //   120: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //   123: aload_3        
        //   124: monitorexit    
        //   125: aconst_null    
        //   126: areturn        
        //   127: astore_0       
        //   128: ldc             "NotificationCompat"
        //   130: ldc             "Unable to access notification extras"
        //   132: aload_0        
        //   133: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   136: pop            
        //   137: goto            119
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      14     104    109    Any
        //  16     54     109    119    Ljava/lang/IllegalAccessException;
        //  16     54     127    140    Ljava/lang/NoSuchFieldException;
        //  16     54     104    109    Any
        //  54     56     104    109    Any
        //  58     67     109    119    Ljava/lang/IllegalAccessException;
        //  58     67     127    140    Ljava/lang/NoSuchFieldException;
        //  58     67     104    109    Any
        //  67     78     109    119    Ljava/lang/IllegalAccessException;
        //  67     78     127    140    Ljava/lang/NoSuchFieldException;
        //  67     78     104    109    Any
        //  84     100    109    119    Ljava/lang/IllegalAccessException;
        //  84     100    127    140    Ljava/lang/NoSuchFieldException;
        //  84     100    104    109    Any
        //  100    102    104    109    Any
        //  105    107    104    109    Any
        //  110    119    104    109    Any
        //  119    125    104    109    Any
        //  128    137    104    109    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    public static Bundle writeActionAndGetExtras(final Notification$Builder notification$Builder, final NotificationCompatBase$Action notificationCompatBase$Action) {
        notification$Builder.addAction(notificationCompatBase$Action.getIcon(), notificationCompatBase$Action.getTitle(), notificationCompatBase$Action.getActionIntent());
        final Bundle bundle = new Bundle(notificationCompatBase$Action.getExtras());
        if (notificationCompatBase$Action.getRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(notificationCompatBase$Action.getRemoteInputs()));
        }
        return bundle;
    }
}
