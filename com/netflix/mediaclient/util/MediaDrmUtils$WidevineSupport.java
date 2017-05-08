// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.os.Build$VERSION;
import java.util.UUID;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
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
        //    45: ifne            138
        //    48: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //    51: ldc             "Failed to release MediaDrm"
        //    53: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    56: pop            
        //    57: aload_0        
        //    58: ldc             "RELEASE_FAILURE"
        //    60: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //    63: iconst_0       
        //    64: ireturn        
        //    65: astore_2       
        //    66: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //    69: ldc             "Failed to create MediaDrm with Widevine scheme"
        //    71: aload_2        
        //    72: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    75: pop            
        //    76: aload_2        
        //    77: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //    80: iconst_0       
        //    81: ireturn        
        //    82: astore_3       
        //    83: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //    86: ldc             "Failed to get property security level"
        //    88: aload_3        
        //    89: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    92: pop            
        //    93: aload_0        
        //    94: ldc             "SECURITY_LEVEL_GET_FAILURE"
        //    96: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //    99: aload_3        
        //   100: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //   103: aload_2        
        //   104: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.releaseMediaDrmSafely:(Landroid/media/MediaDrm;)Z
        //   107: pop            
        //   108: iconst_0       
        //   109: ireturn        
        //   110: astore_3       
        //   111: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.access$500:()Ljava/lang/String;
        //   114: ldc             "Failed to get system ID from MediaDrm"
        //   116: aload_3        
        //   117: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   120: pop            
        //   121: aload_0        
        //   122: ldc             "SYSTEMID_GET_FAILURE"
        //   124: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.systemId:Ljava/lang/String;
        //   127: aload_3        
        //   128: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/Throwable;)V
        //   131: aload_2        
        //   132: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.releaseMediaDrmSafely:(Landroid/media/MediaDrm;)Z
        //   135: pop            
        //   136: iconst_0       
        //   137: ireturn        
        //   138: aload_0        
        //   139: aload_0        
        //   140: getfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.securityLevel:Ljava/lang/String;
        //   143: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isWidevineSecurityLevelL1:(Ljava/lang/String;)Z
        //   146: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL1:Z
        //   149: aload_0        
        //   150: getfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL1:Z
        //   153: ifne            158
        //   156: iconst_1       
        //   157: istore_1       
        //   158: aload_0        
        //   159: iload_1        
        //   160: putfield        com/netflix/mediaclient/util/MediaDrmUtils$WidevineSupport.isL3:Z
        //   163: iconst_1       
        //   164: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     21     65     82     Ljava/lang/Throwable;
        //  21     31     82     110    Ljava/lang/Throwable;
        //  31     41     110    138    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 90, Size: 90
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
        boolean cryptoSchemeSupported = false;
        if (AndroidUtils.getAndroidVersion() < 18) {
            return cryptoSchemeSupported;
        }
        try {
            cryptoSchemeSupported = MediaDrm.isCryptoSchemeSupported(MediaDrmUtils.WIDEVINE_SCHEME);
            return cryptoSchemeSupported;
        }
        catch (Throwable t) {
            Log.e(MediaDrmUtils.TAG, "Failed to check crypto scheme", t);
            ErrorLoggingManager.logHandledException(t);
            return false;
        }
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
