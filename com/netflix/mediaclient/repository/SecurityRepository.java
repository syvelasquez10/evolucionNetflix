// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.repository;

import android.annotation.SuppressLint;

public final class SecurityRepository
{
    private static final String BOOTLOADER_PARAMETER_CERTIFICATION_VERSION = "certification_version";
    private static final String BOOTLOADER_PARAMETER_DEVICE_CATEGORY = "device_cat";
    private static final String BOOTLOADER_PARAMETER_DEVICE_TYPE = "device_type";
    private static final String BOOTLOADER_PARAMETER_FULL_ESN = "e";
    private static final String BOOTLOADER_PARAMETER_OS = "os";
    private static final String BOOTLOADER_PARAMETER_SDK_VERSION = "sdk_version";
    private static final String BOOTLOADER_PARAMETER_SOFTWARE_VERSION = "sw_version";
    private static final String BOOTLOADER_PARAMETER_WEB_API_VERSION = "v";
    private static final String BOOTLOADER_URL = "https://www.netflix.com/applanding/android";
    private static final String BOOTLOADER_WEB_API_VERSION = "2.0";
    private static final int CONSTANT_CRITTERCISM_APP_ID = 2;
    private static final int CONSTANT_CRITTERCISM_OAUTH_KEY = 3;
    private static final int CONSTANT_CRITTERCISM_OAUTH_SECRET = 4;
    private static final int CONSTANT_DEVICE_ID_TOKEN = 1;
    private static final int CONSTANT_FACEBOOK_ID = 0;
    private static final String ESN_DELIM = "-";
    private static final String MDXLIB_VERSION_VALUE = "2013.2";
    private static final String MODEL_DELIM = "_";
    public static final String NCCP_VERSION = "NCCP/2.15";
    private static final String NRDAPP_VERSION_VALUE = "2013.2";
    private static final String NRDLIB_VERSION_VALUE = "2013.2";
    public static final String SENDER_ID = "484286080282";
    private static String crittercismAppId;
    private static String crittercismOauthKey;
    private static String crittercismOauthSecret;
    private static String deviceIdToken;
    private static String esnPrefix;
    private static String facebookId;
    private static boolean loaded;
    
    static {
        SecurityRepository.loaded = loadLibrary();
        native_init(new byte[0]);
        SecurityRepository.deviceIdToken = native_getConstant(1);
        SecurityRepository.facebookId = native_getConstant(0);
        SecurityRepository.crittercismAppId = native_getConstant(2);
        SecurityRepository.crittercismOauthKey = native_getConstant(3);
        SecurityRepository.crittercismOauthSecret = native_getConstant(4);
    }
    
    public static String getBootloaderParameterCertificationVersion() {
        return "certification_version";
    }
    
    public static String getBootloaderParameterDeviceCategory() {
        return "device_cat";
    }
    
    public static String getBootloaderParameterDeviceType() {
        return "device_type";
    }
    
    public static String getBootloaderParameterFullEsn() {
        return "e";
    }
    
    public static String getBootloaderParameterOs() {
        return "os";
    }
    
    public static String getBootloaderParameterSdkVersion() {
        return "sdk_version";
    }
    
    public static String getBootloaderParameterSoftwareVersion() {
        return "sw_version";
    }
    
    public static String getBootloaderParameterWebApiVersion() {
        return "v";
    }
    
    public static String getBootloaderUrl() {
        return "https://www.netflix.com/applanding/android";
    }
    
    public static String getBootloaderWebApiVersion() {
        return "2.0";
    }
    
    public static String getCrittercismAppId() {
        return SecurityRepository.crittercismAppId;
    }
    
    public static String getCrittercismOathKey() {
        return SecurityRepository.crittercismOauthKey;
    }
    
    public static String getCrittercismOathSecret() {
        return SecurityRepository.crittercismOauthSecret;
    }
    
    public static String getDeviceIdToken() {
        return SecurityRepository.deviceIdToken;
    }
    
    public static String getEsnDelim() {
        return "-";
    }
    
    public static String getEsnPrefix() {
        return SecurityRepository.esnPrefix;
    }
    
    public static String getFacebookId() {
        return SecurityRepository.facebookId;
    }
    
    public static final int getLibraryVersion() {
        return native_getLibraryVersion();
    }
    
    public static String getMdxLibVersion() {
        return "2013.2";
    }
    
    public static String getModelDelim() {
        return "_";
    }
    
    public static String getNrdAppVersion() {
        return "2013.2";
    }
    
    public static String getNrdLibVersion() {
        return "2013.2";
    }
    
    public static boolean isLoaded() {
        return SecurityRepository.loaded;
    }
    
    @SuppressLint({ "SdCardPath" })
    private static boolean loadLibrary() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_0       
        //     2: ldc             "SEC"
        //     4: ldc             "Loading from /data/data/com.netflix.mediaclient/lib/libnetflixmp_jni.so"
        //     6: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     9: pop            
        //    10: ldc             "/data/data/com.netflix.mediaclient/lib/libnetflixmp_jni.so"
        //    12: invokestatic    java/lang/System.load:(Ljava/lang/String;)V
        //    15: iconst_1       
        //    16: istore_0       
        //    17: iload_0        
        //    18: istore_1       
        //    19: iload_0        
        //    20: ifne            38
        //    23: ldc             "SEC"
        //    25: ldc             "Loading library leaving to android to find mapping"
        //    27: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    30: pop            
        //    31: ldc             "netflixmp_jni"
        //    33: invokestatic    java/lang/System.loadLibrary:(Ljava/lang/String;)V
        //    36: iconst_1       
        //    37: istore_1       
        //    38: iload_1        
        //    39: ireturn        
        //    40: astore_2       
        //    41: ldc             "SEC"
        //    43: ldc             "Failed to load library from assumed location, try to load it using Android mapping"
        //    45: aload_2        
        //    46: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    49: pop            
        //    50: goto            17
        //    53: astore_2       
        //    54: ldc             "SEC"
        //    56: ldc             "Failed to load library using Android mapping, no recourse left"
        //    58: aload_2        
        //    59: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    62: pop            
        //    63: iload_0        
        //    64: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      15     40     53     Ljava/lang/UnsatisfiedLinkError;
        //  23     36     53     65     Ljava/lang/UnsatisfiedLinkError;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    private static final native String native_getConstant(final int p0);
    
    private static final native int native_getLibraryVersion();
    
    private static final native void native_init(final byte[] p0);
}
