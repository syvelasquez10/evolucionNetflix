// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.lang.reflect.Method;
import com.facebook.FacebookException;
import android.os.Looper;
import android.content.Context;
import android.net.Uri;

public class AttributionIdentifiers
{
    private static final Uri ATTRIBUTION_ID_CONTENT_URI;
    private static final String TAG;
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;
    
    static {
        TAG = AttributionIdentifiers.class.getCanonicalName();
        ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    }
    
    private static AttributionIdentifiers getAndroidId(final Context context) {
        final AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
        Label_0036: {
            try {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    throw new FacebookException("getAndroidId cannot be called on the main thread.");
                }
                break Label_0036;
            }
            catch (Exception ex) {
                Utility.logd("android_id", ex);
            }
            return attributionIdentifiers;
        }
        final Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (methodQuietly == null) {
            return attributionIdentifiers;
        }
        final Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
        if (!(invokeMethodQuietly instanceof Integer) || (int)invokeMethodQuietly != 0) {
            return attributionIdentifiers;
        }
        final Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
        if (methodQuietly2 == null) {
            return attributionIdentifiers;
        }
        final Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
        if (invokeMethodQuietly2 == null) {
            return attributionIdentifiers;
        }
        final Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", (Class<?>[])new Class[0]);
        final Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "isLimitAdTrackingEnabled", (Class<?>[])new Class[0]);
        if (methodQuietly3 != null && methodQuietly4 != null) {
            attributionIdentifiers.androidAdvertiserId = (String)Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly3, new Object[0]);
            attributionIdentifiers.limitTracking = (boolean)Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly4, new Object[0]);
            return attributionIdentifiers;
        }
        return attributionIdentifiers;
    }
    
    public static AttributionIdentifiers getAttributionIdentifiers(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/facebook/internal/AttributionIdentifiers.recentlyFetchedIdentifiers:Lcom/facebook/internal/AttributionIdentifiers;
        //     3: ifnull          27
        //     6: invokestatic    java/lang/System.currentTimeMillis:()J
        //     9: getstatic       com/facebook/internal/AttributionIdentifiers.recentlyFetchedIdentifiers:Lcom/facebook/internal/AttributionIdentifiers;
        //    12: getfield        com/facebook/internal/AttributionIdentifiers.fetchTime:J
        //    15: lsub           
        //    16: ldc2_w          3600000
        //    19: lcmp           
        //    20: ifge            27
        //    23: getstatic       com/facebook/internal/AttributionIdentifiers.recentlyFetchedIdentifiers:Lcom/facebook/internal/AttributionIdentifiers;
        //    26: areturn        
        //    27: aload_0        
        //    28: invokestatic    com/facebook/internal/AttributionIdentifiers.getAndroidId:(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
        //    31: astore          6
        //    33: aload_0        
        //    34: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    37: getstatic       com/facebook/internal/AttributionIdentifiers.ATTRIBUTION_ID_CONTENT_URI:Landroid/net/Uri;
        //    40: iconst_3       
        //    41: anewarray       Ljava/lang/String;
        //    44: dup            
        //    45: iconst_0       
        //    46: ldc             "aid"
        //    48: aastore        
        //    49: dup            
        //    50: iconst_1       
        //    51: ldc             "androidid"
        //    53: aastore        
        //    54: dup            
        //    55: iconst_2       
        //    56: ldc             "limit_tracking"
        //    58: aastore        
        //    59: aconst_null    
        //    60: aconst_null    
        //    61: aconst_null    
        //    62: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    65: astore          5
        //    67: aload           5
        //    69: ifnull          89
        //    72: aload           5
        //    74: astore_0       
        //    75: aload           5
        //    77: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    82: istore          4
        //    84: iload           4
        //    86: ifne            104
        //    89: aload           5
        //    91: ifnull          101
        //    94: aload           5
        //    96: invokeinterface android/database/Cursor.close:()V
        //   101: aload           6
        //   103: areturn        
        //   104: aload           5
        //   106: astore_0       
        //   107: aload           5
        //   109: ldc             "aid"
        //   111: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   116: istore_1       
        //   117: aload           5
        //   119: astore_0       
        //   120: aload           5
        //   122: ldc             "androidid"
        //   124: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   129: istore_2       
        //   130: aload           5
        //   132: astore_0       
        //   133: aload           5
        //   135: ldc             "limit_tracking"
        //   137: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   142: istore_3       
        //   143: aload           5
        //   145: astore_0       
        //   146: aload           6
        //   148: aload           5
        //   150: iload_1        
        //   151: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   156: putfield        com/facebook/internal/AttributionIdentifiers.attributionId:Ljava/lang/String;
        //   159: iload_2        
        //   160: ifle            213
        //   163: iload_3        
        //   164: ifle            213
        //   167: aload           5
        //   169: astore_0       
        //   170: aload           6
        //   172: invokevirtual   com/facebook/internal/AttributionIdentifiers.getAndroidAdvertiserId:()Ljava/lang/String;
        //   175: ifnonnull       213
        //   178: aload           5
        //   180: astore_0       
        //   181: aload           6
        //   183: aload           5
        //   185: iload_2        
        //   186: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   191: putfield        com/facebook/internal/AttributionIdentifiers.androidAdvertiserId:Ljava/lang/String;
        //   194: aload           5
        //   196: astore_0       
        //   197: aload           6
        //   199: aload           5
        //   201: iload_3        
        //   202: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   207: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
        //   210: putfield        com/facebook/internal/AttributionIdentifiers.limitTracking:Z
        //   213: aload           5
        //   215: ifnull          225
        //   218: aload           5
        //   220: invokeinterface android/database/Cursor.close:()V
        //   225: aload           6
        //   227: invokestatic    java/lang/System.currentTimeMillis:()J
        //   230: putfield        com/facebook/internal/AttributionIdentifiers.fetchTime:J
        //   233: aload           6
        //   235: putstatic       com/facebook/internal/AttributionIdentifiers.recentlyFetchedIdentifiers:Lcom/facebook/internal/AttributionIdentifiers;
        //   238: aload           6
        //   240: areturn        
        //   241: astore          6
        //   243: aconst_null    
        //   244: astore          5
        //   246: aload           5
        //   248: astore_0       
        //   249: getstatic       com/facebook/internal/AttributionIdentifiers.TAG:Ljava/lang/String;
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: ldc             "Caught unexpected exception in getAttributionId(): "
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload           6
        //   266: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   269: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   272: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   275: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   278: pop            
        //   279: aload           5
        //   281: ifnull          291
        //   284: aload           5
        //   286: invokeinterface android/database/Cursor.close:()V
        //   291: aconst_null    
        //   292: areturn        
        //   293: astore          5
        //   295: aconst_null    
        //   296: astore_0       
        //   297: aload_0        
        //   298: ifnull          307
        //   301: aload_0        
        //   302: invokeinterface android/database/Cursor.close:()V
        //   307: aload           5
        //   309: athrow         
        //   310: astore          5
        //   312: goto            297
        //   315: astore          6
        //   317: goto            246
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     67     241    246    Ljava/lang/Exception;
        //  33     67     293    297    Any
        //  75     84     315    320    Ljava/lang/Exception;
        //  75     84     310    315    Any
        //  107    117    315    320    Ljava/lang/Exception;
        //  107    117    310    315    Any
        //  120    130    315    320    Ljava/lang/Exception;
        //  120    130    310    315    Any
        //  133    143    315    320    Ljava/lang/Exception;
        //  133    143    310    315    Any
        //  146    159    315    320    Ljava/lang/Exception;
        //  146    159    310    315    Any
        //  170    178    315    320    Ljava/lang/Exception;
        //  170    178    310    315    Any
        //  181    194    315    320    Ljava/lang/Exception;
        //  181    194    310    315    Any
        //  197    213    315    320    Ljava/lang/Exception;
        //  197    213    310    315    Any
        //  249    279    310    315    Any
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
    
    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }
    
    public String getAttributionId() {
        return this.attributionId;
    }
    
    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
