// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.LogUtils;
import android.media.MediaCryptoException;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.media.MediaDrm;
import android.media.MediaCrypto;
import java.util.UUID;
import android.annotation.TargetApi;

@TargetApi(18)
public class WidevineMediaDrmEngine
{
    static final UUID NetflixWidevineUUID;
    static final UUID WideVineUUID;
    static boolean mKeyAdded;
    private final String TAG;
    private MediaCrypto mCrypto;
    private MediaDrm mDrm;
    private byte[] mSessionId;
    private boolean mWidevineL3;
    
    static {
        WideVineUUID = new UUID(-1301668207276963122L, -6645017420763422227L);
        NetflixWidevineUUID = new UUID(2985921618079337012L, -8332874748677350841L);
        WidevineMediaDrmEngine.mKeyAdded = false;
    }
    
    WidevineMediaDrmEngine(final boolean mWidevineL3) {
        this.TAG = "WidevineMediaDrmEngine";
        this.mSessionId = null;
        this.mWidevineL3 = false;
        Log.i("WidevineMediaDrmEngine", "create WidevineMediaDrmEngine");
        this.mWidevineL3 = mWidevineL3;
        Log.i("WidevineMediaDrmEngine", "WidevineMediaDrmEngine done");
    }
    
    private void cleanup() {
        if (this.mDrm == null) {
            return;
        }
        try {
            if (this.mCrypto != null) {
                this.mCrypto.release();
                this.mCrypto = null;
            }
            if (this.mSessionId != null) {
                this.mDrm.closeSession(this.mSessionId);
                this.mSessionId = null;
            }
            this.mDrm.release();
            this.mDrm = null;
        }
        catch (Throwable t) {
            if (Log.isLoggable()) {
                Log.d("WidevineMediaDrmEngine", "clean up has exception " + t);
            }
        }
    }
    
    public void clearLicense(final byte[] array) {
        if (this.mSessionId != null) {
            this.mDrm.removeKeys(this.mSessionId);
        }
    }
    
    public byte[] getChallenge(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //     4: ifnonnull       26
        //     7: ldc             "WidevineMediaDrmEngine"
        //     9: ldc             "create Session"
        //    11: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    14: pop            
        //    15: aload_0        
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    20: invokevirtual   android/media/MediaDrm.openSession:()[B
        //    23: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    26: new             Ljava/util/HashMap;
        //    29: dup            
        //    30: invokespecial   java/util/HashMap.<init>:()V
        //    33: astore          4
        //    35: aload_0        
        //    36: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    39: ldc             "provisioningUniqueId"
        //    41: invokevirtual   android/media/MediaDrm.getPropertyByteArray:(Ljava/lang/String;)[B
        //    44: astore_3       
        //    45: aload_3        
        //    46: ifnull          212
        //    49: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    52: ifeq            163
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: astore          5
        //    64: iconst_0       
        //    65: istore_2       
        //    66: iload_2        
        //    67: aload_3        
        //    68: arraylength    
        //    69: if_icmpge       134
        //    72: aload           5
        //    74: ldc             "%02x  "
        //    76: iconst_1       
        //    77: anewarray       Ljava/lang/Object;
        //    80: dup            
        //    81: iconst_0       
        //    82: aload_3        
        //    83: iload_2        
        //    84: baload         
        //    85: invokestatic    java/lang/Byte.valueOf:(B)Ljava/lang/Byte;
        //    88: aastore        
        //    89: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: pop            
        //    96: iload_2        
        //    97: iconst_1       
        //    98: iadd           
        //    99: istore_2       
        //   100: goto            66
        //   103: astore_1       
        //   104: ldc             "WidevineMediaDrmEngine"
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: invokespecial   java/lang/StringBuilder.<init>:()V
        //   113: ldc             "fail to openSession "
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: aload_1        
        //   119: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   131: pop            
        //   132: aconst_null    
        //   133: areturn        
        //   134: ldc             "WidevineMediaDrmEngine"
        //   136: new             Ljava/lang/StringBuilder;
        //   139: dup            
        //   140: invokespecial   java/lang/StringBuilder.<init>:()V
        //   143: ldc             "provisioningUniqueId "
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: aload           5
        //   150: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   159: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   162: pop            
        //   163: aload_3        
        //   164: invokestatic    com/netflix/mediaclient/util/Base64.encodeBytes:([B)Ljava/lang/String;
        //   167: astore_3       
        //   168: aload_3        
        //   169: ifnull          212
        //   172: aload           4
        //   174: ldc             "CDMID"
        //   176: aload_3        
        //   177: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   180: pop            
        //   181: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   184: ifeq            212
        //   187: ldc             "WidevineMediaDrmEngine"
        //   189: new             Ljava/lang/StringBuilder;
        //   192: dup            
        //   193: invokespecial   java/lang/StringBuilder.<init>:()V
        //   196: ldc             "CDMID "
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload_3        
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   208: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop            
        //   212: aload_0        
        //   213: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //   216: aload_0        
        //   217: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //   220: aload_1        
        //   221: new             Ljava/lang/String;
        //   224: dup            
        //   225: invokespecial   java/lang/String.<init>:()V
        //   228: iconst_1       
        //   229: aload           4
        //   231: invokevirtual   android/media/MediaDrm.getKeyRequest:([B[BLjava/lang/String;ILjava/util/HashMap;)Landroid/media/MediaDrm$KeyRequest;
        //   234: astore_1       
        //   235: aload_1        
        //   236: ifnull          318
        //   239: ldc             "WidevineMediaDrmEngine"
        //   241: new             Ljava/lang/StringBuilder;
        //   244: dup            
        //   245: invokespecial   java/lang/StringBuilder.<init>:()V
        //   248: ldc             "getChallenge of size "
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: aload_1        
        //   254: invokevirtual   android/media/MediaDrm$KeyRequest.getData:()[B
        //   257: arraylength    
        //   258: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   261: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   264: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   267: pop            
        //   268: aload_1        
        //   269: invokevirtual   android/media/MediaDrm$KeyRequest.getData:()[B
        //   272: areturn        
        //   273: astore_3       
        //   274: ldc             "WidevineMediaDrmEngine"
        //   276: ldc             "fail to encode property provisioningUniqueId"
        //   278: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   281: pop            
        //   282: aconst_null    
        //   283: astore_3       
        //   284: goto            168
        //   287: astore_1       
        //   288: ldc             "WidevineMediaDrmEngine"
        //   290: new             Ljava/lang/StringBuilder;
        //   293: dup            
        //   294: invokespecial   java/lang/StringBuilder.<init>:()V
        //   297: ldc             "fail to getChallenge"
        //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   302: aload_1        
        //   303: invokevirtual   android/media/NotProvisionedException.getMessage:()Ljava/lang/String;
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   312: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   315: pop            
        //   316: aconst_null    
        //   317: areturn        
        //   318: ldc             "WidevineMediaDrmEngine"
        //   320: ldc             "getChallenge done"
        //   322: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   325: pop            
        //   326: aconst_null    
        //   327: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  15     26     103    134    Ljava/lang/Throwable;
        //  163    168    273    287    Ljava/io/IOException;
        //  212    235    287    318    Landroid/media/NotProvisionedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0212:
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
    
    public MediaCrypto getMediaCrypto() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "WidevineMediaDrmEngine"
        //     2: ldc             "get MediaCrypto"
        //     4: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //     7: pop            
        //     8: aload_0        
        //     9: invokespecial   com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.cleanup:()V
        //    12: aload_0        
        //    13: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    16: ifnonnull       51
        //    19: aload_0        
        //    20: new             Landroid/media/MediaDrm;
        //    23: dup            
        //    24: getstatic       com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.WideVineUUID:Ljava/util/UUID;
        //    27: invokespecial   android/media/MediaDrm.<init>:(Ljava/util/UUID;)V
        //    30: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    33: aload_0        
        //    34: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mWidevineL3:Z
        //    37: ifeq            51
        //    40: aload_0        
        //    41: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    44: ldc             "securityLevel"
        //    46: ldc             "L3"
        //    48: invokevirtual   android/media/MediaDrm.setPropertyString:(Ljava/lang/String;Ljava/lang/String;)V
        //    51: aload_0        
        //    52: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    55: ifnonnull       77
        //    58: ldc             "WidevineMediaDrmEngine"
        //    60: ldc             "create Session"
        //    62: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    65: pop            
        //    66: aload_0        
        //    67: aload_0        
        //    68: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    71: invokevirtual   android/media/MediaDrm.openSession:()[B
        //    74: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    77: aload_0        
        //    78: new             Landroid/media/MediaCrypto;
        //    81: dup            
        //    82: getstatic       com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.WideVineUUID:Ljava/util/UUID;
        //    85: aload_0        
        //    86: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    89: invokespecial   android/media/MediaCrypto.<init>:(Ljava/util/UUID;[B)V
        //    92: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mCrypto:Landroid/media/MediaCrypto;
        //    95: aload_0        
        //    96: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mCrypto:Landroid/media/MediaCrypto;
        //    99: areturn        
        //   100: astore_1       
        //   101: ldc             "WidevineMediaDrmEngine"
        //   103: ldc             "fail to create MediaDrm: "
        //   105: aload_1        
        //   106: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   109: pop            
        //   110: aconst_null    
        //   111: areturn        
        //   112: astore_1       
        //   113: ldc             "WidevineMediaDrmEngine"
        //   115: ldc             "fail to openSession "
        //   117: aload_1        
        //   118: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   121: pop            
        //   122: aconst_null    
        //   123: areturn        
        //   124: astore_1       
        //   125: ldc             "WidevineMediaDrmEngine"
        //   127: ldc             "fail to create MediaCrypto: "
        //   129: aload_1        
        //   130: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   133: pop            
        //   134: aconst_null    
        //   135: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     51     100    112    Landroid/media/MediaDrmException;
        //  66     77     112    124    Ljava/lang/Throwable;
        //  77     95     124    136    Landroid/media/MediaCryptoException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 70, Size: 70
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
    
    public List<byte[]> getSecureStops() {
        return (List<byte[]>)this.mDrm.getSecureStops();
    }
    
    public boolean isKeyAdded() {
        return WidevineMediaDrmEngine.mKeyAdded;
    }
    
    public void releaseMediaCrypto() {
        Log.i("WidevineMediaDrmEngine", "release MediaCrypto and SessionId");
        this.cleanup();
    }
    
    public void releaseSecureStops(final byte[] array) {
        this.mDrm.releaseSecureStops(array);
    }
    
    public MediaCrypto renewMediaCrypto() {
        Log.i("WidevineMediaDrmEngine", "renewMediaCrypto");
        try {
            return this.mCrypto = new MediaCrypto(WidevineMediaDrmEngine.WideVineUUID, this.mSessionId);
        }
        catch (MediaCryptoException ex) {
            Log.e("WidevineMediaDrmEngine", "fail to create MediaCrypto: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    public byte[] storeLicense(final byte[] array) {
        try {
            this.mDrm.provideKeyResponse(this.mSessionId, array);
            WidevineMediaDrmEngine.mKeyAdded = true;
            return this.mSessionId;
        }
        catch (Throwable t) {
            LogUtils.reportErrorSafely("fail to storeLicense", t);
            return new byte[0];
        }
    }
}
