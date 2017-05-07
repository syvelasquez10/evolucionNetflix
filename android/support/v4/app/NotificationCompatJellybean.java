// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification$Builder;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.util.Log;
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
    static final String EXTRA_ACTION_EXTRAS = "android.support.actionExtras";
    static final String EXTRA_GROUP_KEY = "android.support.groupKey";
    static final String EXTRA_GROUP_SUMMARY = "android.support.isGroupSummary";
    static final String EXTRA_LOCAL_ONLY = "android.support.localOnly";
    static final String EXTRA_REMOTE_INPUTS = "android.support.remoteInputs";
    static final String EXTRA_SORT_KEY = "android.support.sortKey";
    static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
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
    
    private static boolean ensureActionReflectionReadyLocked() {
        boolean b = true;
        if (NotificationCompatJellybean.sActionsAccessFailed) {
            return false;
        }
        while (true) {
            while (true) {
                try {
                    if (NotificationCompatJellybean.sActionsField == null) {
                        NotificationCompatJellybean.sActionClass = Class.forName("android.app.Notification$Action");
                        NotificationCompatJellybean.sActionIconField = NotificationCompatJellybean.sActionClass.getDeclaredField("icon");
                        NotificationCompatJellybean.sActionTitleField = NotificationCompatJellybean.sActionClass.getDeclaredField("title");
                        NotificationCompatJellybean.sActionIntentField = NotificationCompatJellybean.sActionClass.getDeclaredField("actionIntent");
                        (NotificationCompatJellybean.sActionsField = Notification.class.getDeclaredField("actions")).setAccessible(true);
                    }
                    if (!NotificationCompatJellybean.sActionsAccessFailed) {
                        return b;
                    }
                }
                catch (ClassNotFoundException ex) {
                    Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex);
                    NotificationCompatJellybean.sActionsAccessFailed = true;
                    continue;
                }
                catch (NoSuchFieldException ex2) {
                    Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex2);
                    NotificationCompatJellybean.sActionsAccessFailed = true;
                    continue;
                }
                break;
            }
            b = false;
            return b;
        }
    }
    
    public static NotificationCompatBase$Action getAction(final Notification notification, final int n, final NotificationCompatBase$Action$Factory notificationCompatBase$Action$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        while (true) {
            while (true) {
                synchronized (NotificationCompatJellybean.sActionsLock) {
                    try {
                        final Object o = getActionObjectsLocked(notification)[n];
                        final Bundle extras = getExtras(notification);
                        if (extras != null) {
                            final SparseArray sparseParcelableArray = extras.getSparseParcelableArray("android.support.actionExtras");
                            if (sparseParcelableArray != null) {
                                final Bundle bundle = (Bundle)sparseParcelableArray.get(n);
                                return readAction(notificationCompatBase$Action$Factory, remoteInputCompatBase$RemoteInput$Factory, NotificationCompatJellybean.sActionIconField.getInt(o), (CharSequence)NotificationCompatJellybean.sActionTitleField.get(o), (PendingIntent)NotificationCompatJellybean.sActionIntentField.get(o), bundle);
                            }
                        }
                    }
                    catch (IllegalAccessException ex) {
                        Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex);
                        NotificationCompatJellybean.sActionsAccessFailed = true;
                        return null;
                    }
                }
                final Bundle bundle = null;
                continue;
            }
        }
    }
    
    public static int getActionCount(final Notification notification) {
        while (true) {
            synchronized (NotificationCompatJellybean.sActionsLock) {
                final Object[] actionObjectsLocked = getActionObjectsLocked(notification);
                if (actionObjectsLocked != null) {
                    return actionObjectsLocked.length;
                }
            }
            return 0;
        }
    }
    
    private static NotificationCompatBase$Action getActionFromBundle(final Bundle bundle, final NotificationCompatBase$Action$Factory notificationCompatBase$Action$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        return notificationCompatBase$Action$Factory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), remoteInputCompatBase$RemoteInput$Factory));
    }
    
    private static Object[] getActionObjectsLocked(final Notification p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/NotificationCompatJellybean.sActionsLock:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: invokestatic    android/support/v4/app/NotificationCompatJellybean.ensureActionReflectionReadyLocked:()Z
        //     9: ifne            16
        //    12: aload_1        
        //    13: monitorexit    
        //    14: aconst_null    
        //    15: areturn        
        //    16: getstatic       android/support/v4/app/NotificationCompatJellybean.sActionsField:Ljava/lang/reflect/Field;
        //    19: aload_0        
        //    20: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    23: checkcast       [Ljava/lang/Object;
        //    26: checkcast       [Ljava/lang/Object;
        //    29: astore_0       
        //    30: aload_1        
        //    31: monitorexit    
        //    32: aload_0        
        //    33: areturn        
        //    34: astore_0       
        //    35: aload_1        
        //    36: monitorexit    
        //    37: aload_0        
        //    38: athrow         
        //    39: astore_0       
        //    40: ldc             "NotificationCompat"
        //    42: ldc             "Unable to access notification actions"
        //    44: aload_0        
        //    45: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    48: pop            
        //    49: iconst_1       
        //    50: putstatic       android/support/v4/app/NotificationCompatJellybean.sActionsAccessFailed:Z
        //    53: aload_1        
        //    54: monitorexit    
        //    55: aconst_null    
        //    56: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      14     34     39     Any
        //  16     30     39     57     Ljava/lang/IllegalAccessException;
        //  16     30     34     39     Any
        //  30     32     34     39     Any
        //  35     37     34     39     Any
        //  40     55     34     39     Any
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
    
    public static NotificationCompatBase$Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list, final NotificationCompatBase$Action$Factory notificationCompatBase$Action$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        if (list == null) {
            return null;
        }
        final NotificationCompatBase$Action[] array = notificationCompatBase$Action$Factory.newArray(list.size());
        for (int i = 0; i < array.length; ++i) {
            array[i] = getActionFromBundle((Bundle)list.get(i), notificationCompatBase$Action$Factory, remoteInputCompatBase$RemoteInput$Factory);
        }
        return array;
    }
    
    private static Bundle getBundleForAction(final NotificationCompatBase$Action notificationCompatBase$Action) {
        final Bundle bundle = new Bundle();
        bundle.putInt("icon", notificationCompatBase$Action.getIcon());
        bundle.putCharSequence("title", notificationCompatBase$Action.getTitle());
        bundle.putParcelable("actionIntent", (Parcelable)notificationCompatBase$Action.getActionIntent());
        bundle.putBundle("extras", notificationCompatBase$Action.getExtras());
        bundle.putParcelableArray("remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(notificationCompatBase$Action.getRemoteInputs()));
        return bundle;
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
        //    19: ifnonnull       68
        //    22: ldc             Landroid/app/Notification;.class
        //    24: ldc             "extras"
        //    26: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    29: astore_1       
        //    30: ldc             Landroid/os/Bundle;.class
        //    32: aload_1        
        //    33: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //    36: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    39: ifne            59
        //    42: ldc             "NotificationCompat"
        //    44: ldc_w           "Notification.extras field is not of type Bundle"
        //    47: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    50: pop            
        //    51: iconst_1       
        //    52: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //    55: aload_3        
        //    56: monitorexit    
        //    57: aconst_null    
        //    58: areturn        
        //    59: aload_1        
        //    60: iconst_1       
        //    61: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    64: aload_1        
        //    65: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    68: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    71: aload_0        
        //    72: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    75: checkcast       Landroid/os/Bundle;
        //    78: astore_2       
        //    79: aload_2        
        //    80: astore_1       
        //    81: aload_2        
        //    82: ifnonnull       101
        //    85: new             Landroid/os/Bundle;
        //    88: dup            
        //    89: invokespecial   android/os/Bundle.<init>:()V
        //    92: astore_1       
        //    93: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    96: aload_0        
        //    97: aload_1        
        //    98: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   101: aload_3        
        //   102: monitorexit    
        //   103: aload_1        
        //   104: areturn        
        //   105: astore_0       
        //   106: aload_3        
        //   107: monitorexit    
        //   108: aload_0        
        //   109: athrow         
        //   110: astore_0       
        //   111: ldc             "NotificationCompat"
        //   113: ldc_w           "Unable to access notification extras"
        //   116: aload_0        
        //   117: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   120: pop            
        //   121: iconst_1       
        //   122: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //   125: aload_3        
        //   126: monitorexit    
        //   127: aconst_null    
        //   128: areturn        
        //   129: astore_0       
        //   130: ldc             "NotificationCompat"
        //   132: ldc_w           "Unable to access notification extras"
        //   135: aload_0        
        //   136: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   139: pop            
        //   140: goto            121
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      14     105    110    Any
        //  16     55     110    121    Ljava/lang/IllegalAccessException;
        //  16     55     129    143    Ljava/lang/NoSuchFieldException;
        //  16     55     105    110    Any
        //  55     57     105    110    Any
        //  59     68     110    121    Ljava/lang/IllegalAccessException;
        //  59     68     129    143    Ljava/lang/NoSuchFieldException;
        //  59     68     105    110    Any
        //  68     79     110    121    Ljava/lang/IllegalAccessException;
        //  68     79     129    143    Ljava/lang/NoSuchFieldException;
        //  68     79     105    110    Any
        //  85     101    110    121    Ljava/lang/IllegalAccessException;
        //  85     101    129    143    Ljava/lang/NoSuchFieldException;
        //  85     101    105    110    Any
        //  101    103    105    110    Any
        //  106    108    105    110    Any
        //  111    121    105    110    Any
        //  121    127    105    110    Any
        //  130    140    105    110    Any
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
    
    public static String getGroup(final Notification notification) {
        return getExtras(notification).getString("android.support.groupKey");
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return getExtras(notification).getBoolean("android.support.localOnly");
    }
    
    public static ArrayList<Parcelable> getParcelableArrayListForActions(final NotificationCompatBase$Action[] array) {
        ArrayList<Parcelable> list;
        if (array == null) {
            list = null;
        }
        else {
            final ArrayList<Parcelable> list2 = new ArrayList<Parcelable>(array.length);
            final int length = array.length;
            int n = 0;
            while (true) {
                list = list2;
                if (n >= length) {
                    break;
                }
                list2.add((Parcelable)getBundleForAction(array[n]));
                ++n;
            }
        }
        return list;
    }
    
    public static String getSortKey(final Notification notification) {
        return getExtras(notification).getString("android.support.sortKey");
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return getExtras(notification).getBoolean("android.support.isGroupSummary");
    }
    
    public static NotificationCompatBase$Action readAction(final NotificationCompatBase$Action$Factory notificationCompatBase$Action$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory, final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle) {
        RemoteInputCompatBase$RemoteInput[] fromBundleArray = null;
        if (bundle != null) {
            fromBundleArray = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), remoteInputCompatBase$RemoteInput$Factory);
        }
        return notificationCompatBase$Action$Factory.build(n, charSequence, pendingIntent, bundle, fromBundleArray);
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
