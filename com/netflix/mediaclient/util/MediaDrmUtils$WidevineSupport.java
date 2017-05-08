// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.os.Build;
import android.os.Build$VERSION;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import java.util.UUID;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.annotation.SuppressLint;
import android.media.MediaDrm;
import com.netflix.mediaclient.Log;

public class MediaDrmUtils$WidevineSupport
{
    private boolean isL1;
    private boolean isL3;
    private String securityLevel;
    private boolean supported;
    private String systemId;
    
    private MediaDrmUtils$WidevineSupport() {
        this.systemId = "";
        this.supported = this.init();
        if (Log.isLoggable()) {
            Log.d(MediaDrmUtils.TAG, "System capabilities: " + this.toString());
        }
    }
    
    private boolean init() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isWidewineSupported:()Z
        //     5: ifne            10
        //     8: iconst_0       
        //     9: ireturn        
        //    10: new             Landroid/media/MediaDrm;
        //    13: dup            
        //    14: getstatic       com/netflix/mediaclient/util/MediaDrmUtils.WIDEVINE_SCHEME:Ljava/util/UUID;
        //    17: invokespecial   android/media/MediaDrm.<init>:(Ljava/util/UUID;)V
        //    20: astore_2       
        //    21: aload_0        
        //    22: aload_2        
        //    23: ldc             "securityLevel"
        //    25: invokevirtual   android/media/MediaDrm.getPropertyString:(Ljava/lang/String;)Ljava/lang/String;
        //    28: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.securityLevel:Ljava/lang/String;
        //    31: aload_0        
        //    32: aload_2        
        //    33: ldc             "systemId"
        //    35: invokevirtual   android/media/MediaDrm.getPropertyString:(Ljava/lang/String;)Ljava/lang/String;
        //    38: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //    41: aload_2        
        //    42: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.releaseMediaDrmSafely:(Landroid/media/MediaDrm;)Z
        //    45: pop            
        //    46: aload_0        
        //    47: aload_0        
        //    48: getfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.securityLevel:Ljava/lang/String;
        //    51: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isWidevineSecurityLevelL1:(Ljava/lang/String;)Z
        //    54: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL1:Z
        //    57: aload_0        
        //    58: getfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL1:Z
        //    61: ifne            66
        //    64: iconst_1       
        //    65: istore_1       
        //    66: aload_0        
        //    67: iload_1        
        //    68: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL3:Z
        //    71: iconst_1       
        //    72: ireturn        
        //    73: astore_2       
        //    74: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //    77: ldc             "Failed to create MediaDrm with Widevine scheme"
        //    79: aload_2        
        //    80: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    83: pop            
        //    84: aload_2        
        //    85: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //    88: iconst_0       
        //    89: ireturn        
        //    90: astore_3       
        //    91: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //    94: ldc             "Failed to get property security level"
        //    96: aload_3        
        //    97: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   100: pop            
        //   101: aload_0        
        //   102: ldc             "SECURITY_LEVEL_GET_FAILURE"
        //   104: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //   107: aload_3        
        //   108: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //   111: aload_2        
        //   112: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.releaseMediaDrmSafely:(Landroid/media/MediaDrm;)Z
        //   115: pop            
        //   116: iconst_0       
        //   117: ireturn        
        //   118: astore_3       
        //   119: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //   122: ldc             "Failed to get system ID from MediaDrm"
        //   124: aload_3        
        //   125: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   128: pop            
        //   129: aload_0        
        //   130: ldc             "SYSTEMID_GET_FAILURE"
        //   132: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //   135: aload_3        
        //   136: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //   139: aload_2        
        //   140: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.releaseMediaDrmSafely:(Landroid/media/MediaDrm;)Z
        //   143: pop            
        //   144: iconst_0       
        //   145: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     21     73     90     Ljava/lang/Throwable;
        //  21     31     90     118    Ljava/lang/Throwable;
        //  31     41     118    146    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 81, Size: 81
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
    
    private static boolean isWidevineSecurityLevelL1(final String s) {
        return "L1".equalsIgnoreCase(s);
    }
    
    @SuppressLint({ "NewApi" })
    static boolean isWidewineSupported() {
        return AndroidUtils.getAndroidVersion() >= 18 && MediaDrm.isCryptoSchemeSupported(MediaDrmUtils.WIDEVINE_SCHEME);
    }
    
    private static boolean releaseMediaDrmSafely(final MediaDrm mediaDrm) {
        if (mediaDrm == null) {
            return true;
        }
        try {
            mediaDrm.release();
            return true;
        }
        catch (Throwable t) {
            Log.e(MediaDrmUtils.TAG, "Failed to release MediaDrm", t);
            ErrorLoggingManager.logHandledException(t);
            return false;
        }
    }
    
    public String getSecurityLevel() {
        return this.securityLevel;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
    
    public boolean isL1() {
        return this.isL1;
    }
    
    public boolean isL3() {
        return this.isL3;
    }
    
    public boolean isSupported() {
        return this.supported;
    }
    
    @Override
    public String toString() {
        return "Widevine{supported=" + this.supported + ", isL1=" + this.isL1 + ", isL3=" + this.isL3 + ", securityLevel='" + this.securityLevel + '\'' + ", systemId='" + this.systemId + '\'' + '}';
    }
}
