// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import android.media.NotProvisionedException;
import android.media.DeniedByServerException;
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
    
    static {
        WideVineUUID = new UUID(-1301668207276963122L, -6645017420763422227L);
        NetflixWidevineUUID = new UUID(2985921618079337012L, -8332874748677350841L);
        WidevineMediaDrmEngine.mKeyAdded = false;
    }
    
    WidevineMediaDrmEngine() {
        this.TAG = "WidevineMediaDrmEngine";
        this.mSessionId = null;
        Log.i("WidevineMediaDrmEngine", "create WidevineMediaDrmEngine");
        Log.i("WidevineMediaDrmEngine", "WidevineMediaDrmEngine done");
    }
    
    private void cleanup() {
        if (this.mDrm != null) {
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
        //    46: ifnull          218
        //    49: ldc             "WidevineMediaDrmEngine"
        //    51: iconst_3       
        //    52: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    55: ifeq            166
        //    58: new             Ljava/lang/StringBuilder;
        //    61: dup            
        //    62: invokespecial   java/lang/StringBuilder.<init>:()V
        //    65: astore          5
        //    67: iconst_0       
        //    68: istore_2       
        //    69: iload_2        
        //    70: aload_3        
        //    71: arraylength    
        //    72: if_icmpge       137
        //    75: aload           5
        //    77: ldc             "%02x  "
        //    79: iconst_1       
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: iconst_0       
        //    85: aload_3        
        //    86: iload_2        
        //    87: baload         
        //    88: invokestatic    java/lang/Byte.valueOf:(B)Ljava/lang/Byte;
        //    91: aastore        
        //    92: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: pop            
        //    99: iload_2        
        //   100: iconst_1       
        //   101: iadd           
        //   102: istore_2       
        //   103: goto            69
        //   106: astore_1       
        //   107: ldc             "WidevineMediaDrmEngine"
        //   109: new             Ljava/lang/StringBuilder;
        //   112: dup            
        //   113: invokespecial   java/lang/StringBuilder.<init>:()V
        //   116: ldc             "fail to openSession "
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: aload_1        
        //   122: invokevirtual   android/media/NotProvisionedException.getMessage:()Ljava/lang/String;
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   131: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   134: pop            
        //   135: aconst_null    
        //   136: areturn        
        //   137: ldc             "WidevineMediaDrmEngine"
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: invokespecial   java/lang/StringBuilder.<init>:()V
        //   146: ldc             "provisioningUniqueId "
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: aload           5
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   162: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   165: pop            
        //   166: aload_3        
        //   167: invokestatic    com/netflix/mediaclient/util/Base64.encodeBytes:([B)Ljava/lang/String;
        //   170: astore_3       
        //   171: aload_3        
        //   172: ifnull          218
        //   175: aload           4
        //   177: ldc             "CDMID"
        //   179: aload_3        
        //   180: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   183: pop            
        //   184: ldc             "WidevineMediaDrmEngine"
        //   186: iconst_3       
        //   187: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   190: ifeq            218
        //   193: ldc             "WidevineMediaDrmEngine"
        //   195: new             Ljava/lang/StringBuilder;
        //   198: dup            
        //   199: invokespecial   java/lang/StringBuilder.<init>:()V
        //   202: ldc             "CDMID "
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: aload_3        
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   217: pop            
        //   218: aload_0        
        //   219: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //   222: aload_0        
        //   223: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //   226: aload_1        
        //   227: new             Ljava/lang/String;
        //   230: dup            
        //   231: invokespecial   java/lang/String.<init>:()V
        //   234: iconst_1       
        //   235: aload           4
        //   237: invokevirtual   android/media/MediaDrm.getKeyRequest:([B[BLjava/lang/String;ILjava/util/HashMap;)Landroid/media/MediaDrm$KeyRequest;
        //   240: astore_1       
        //   241: aload_1        
        //   242: ifnull          324
        //   245: ldc             "WidevineMediaDrmEngine"
        //   247: new             Ljava/lang/StringBuilder;
        //   250: dup            
        //   251: invokespecial   java/lang/StringBuilder.<init>:()V
        //   254: ldc             "getChallenge of size "
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload_1        
        //   260: invokevirtual   android/media/MediaDrm$KeyRequest.getData:()[B
        //   263: arraylength    
        //   264: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   267: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   270: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   273: pop            
        //   274: aload_1        
        //   275: invokevirtual   android/media/MediaDrm$KeyRequest.getData:()[B
        //   278: areturn        
        //   279: astore_3       
        //   280: ldc             "WidevineMediaDrmEngine"
        //   282: ldc             "fail to encode property provisioningUniqueId"
        //   284: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   287: pop            
        //   288: aconst_null    
        //   289: astore_3       
        //   290: goto            171
        //   293: astore_1       
        //   294: ldc             "WidevineMediaDrmEngine"
        //   296: new             Ljava/lang/StringBuilder;
        //   299: dup            
        //   300: invokespecial   java/lang/StringBuilder.<init>:()V
        //   303: ldc             "fail to getChallenge"
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload_1        
        //   309: invokevirtual   android/media/NotProvisionedException.getMessage:()Ljava/lang/String;
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   318: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   321: pop            
        //   322: aconst_null    
        //   323: areturn        
        //   324: ldc             "WidevineMediaDrmEngine"
        //   326: ldc             "getChallenge done"
        //   328: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   331: pop            
        //   332: aconst_null    
        //   333: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  15     26     106    137    Landroid/media/NotProvisionedException;
        //  166    171    279    293    Ljava/io/IOException;
        //  218    241    293    324    Landroid/media/NotProvisionedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0218:
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
        //    16: ifnonnull       33
        //    19: aload_0        
        //    20: new             Landroid/media/MediaDrm;
        //    23: dup            
        //    24: getstatic       com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.WideVineUUID:Ljava/util/UUID;
        //    27: invokespecial   android/media/MediaDrm.<init>:(Ljava/util/UUID;)V
        //    30: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    33: aload_0        
        //    34: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    37: ifnonnull       59
        //    40: ldc             "WidevineMediaDrmEngine"
        //    42: ldc             "create Session"
        //    44: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    47: pop            
        //    48: aload_0        
        //    49: aload_0        
        //    50: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mDrm:Landroid/media/MediaDrm;
        //    53: invokevirtual   android/media/MediaDrm.openSession:()[B
        //    56: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    59: aload_0        
        //    60: new             Landroid/media/MediaCrypto;
        //    63: dup            
        //    64: getstatic       com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.WideVineUUID:Ljava/util/UUID;
        //    67: aload_0        
        //    68: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mSessionId:[B
        //    71: invokespecial   android/media/MediaCrypto.<init>:(Ljava/util/UUID;[B)V
        //    74: putfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mCrypto:Landroid/media/MediaCrypto;
        //    77: aload_0        
        //    78: getfield        com/netflix/mediaclient/service/configuration/drm/WidevineMediaDrmEngine.mCrypto:Landroid/media/MediaCrypto;
        //    81: areturn        
        //    82: astore_1       
        //    83: ldc             "WidevineMediaDrmEngine"
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "fail to create MediaDrm: "
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: aload_1        
        //    98: invokevirtual   android/media/MediaDrmException.getMessage:()Ljava/lang/String;
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   107: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   110: pop            
        //   111: aconst_null    
        //   112: areturn        
        //   113: astore_1       
        //   114: ldc             "WidevineMediaDrmEngine"
        //   116: new             Ljava/lang/StringBuilder;
        //   119: dup            
        //   120: invokespecial   java/lang/StringBuilder.<init>:()V
        //   123: ldc             "fail to openSession "
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: aload_1        
        //   129: invokevirtual   android/media/NotProvisionedException.getMessage:()Ljava/lang/String;
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   138: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   141: pop            
        //   142: aconst_null    
        //   143: areturn        
        //   144: astore_1       
        //   145: ldc             "WidevineMediaDrmEngine"
        //   147: new             Ljava/lang/StringBuilder;
        //   150: dup            
        //   151: invokespecial   java/lang/StringBuilder.<init>:()V
        //   154: ldc             "fail to create MediaCrypto: "
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload_1        
        //   160: invokevirtual   android/media/MediaCryptoException.getMessage:()Ljava/lang/String;
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   169: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   172: pop            
        //   173: aload_1        
        //   174: invokevirtual   android/media/MediaCryptoException.printStackTrace:()V
        //   177: aconst_null    
        //   178: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  19     33     82     113    Landroid/media/MediaDrmException;
        //  48     59     113    144    Landroid/media/NotProvisionedException;
        //  59     77     144    179    Landroid/media/MediaCryptoException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 85, Size: 85
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
    
    public byte[] storeLicense(final byte[] array) {
        try {
            this.mDrm.provideKeyResponse(this.mSessionId, array);
            WidevineMediaDrmEngine.mKeyAdded = true;
            return this.mSessionId;
        }
        catch (DeniedByServerException ex) {
            Log.i("WidevineMediaDrmEngine", "fail to storeLicense DeniedByServerException " + ex.getMessage());
            return new byte[0];
        }
        catch (NotProvisionedException ex2) {
            Log.i("WidevineMediaDrmEngine", "fail to storeLicense NotProvisionedException " + ex2.getMessage());
            return new byte[0];
        }
    }
}
