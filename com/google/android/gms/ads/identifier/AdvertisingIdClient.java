// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import com.google.android.gms.internal.fq;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.ServiceConnection;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import com.google.android.gms.common.a;
import android.content.Context;

public final class AdvertisingIdClient
{
    static Info a(final Context p0, final a p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/google/android/gms/common/a.dV:()Landroid/os/IBinder;
        //     4: invokestatic    com/google/android/gms/internal/t$a.b:(Landroid/os/IBinder;)Lcom/google/android/gms/internal/t;
        //     7: astore_2       
        //     8: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //    11: dup            
        //    12: aload_2        
        //    13: invokeinterface com/google/android/gms/internal/t.getId:()Ljava/lang/String;
        //    18: aload_2        
        //    19: iconst_1       
        //    20: invokeinterface com/google/android/gms/internal/t.a:(Z)Z
        //    25: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.<init>:(Ljava/lang/String;Z)V
        //    28: astore_2       
        //    29: aload_0        
        //    30: aload_1        
        //    31: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //    34: aload_2        
        //    35: areturn        
        //    36: astore_0       
        //    37: ldc             "AdvertisingIdClient"
        //    39: ldc             "getAdvertisingIdInfo unbindService failed."
        //    41: aload_0        
        //    42: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    45: pop            
        //    46: aload_2        
        //    47: areturn        
        //    48: astore_2       
        //    49: ldc             "AdvertisingIdClient"
        //    51: ldc             "GMS remote exception "
        //    53: aload_2        
        //    54: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    57: pop            
        //    58: new             Ljava/io/IOException;
        //    61: dup            
        //    62: ldc             "Remote exception"
        //    64: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    67: athrow         
        //    68: astore_2       
        //    69: aload_0        
        //    70: aload_1        
        //    71: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //    74: aload_2        
        //    75: athrow         
        //    76: astore_2       
        //    77: new             Ljava/io/IOException;
        //    80: dup            
        //    81: ldc             "Interrupted exception"
        //    83: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    86: athrow         
        //    87: astore_0       
        //    88: ldc             "AdvertisingIdClient"
        //    90: ldc             "getAdvertisingIdInfo unbindService failed."
        //    92: aload_0        
        //    93: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: goto            74
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      29     48     68     Landroid/os/RemoteException;
        //  0      29     76     87     Ljava/lang/InterruptedException;
        //  0      29     68     76     Any
        //  29     34     36     48     Ljava/lang/IllegalArgumentException;
        //  49     68     68     76     Any
        //  69     74     87     100    Ljava/lang/IllegalArgumentException;
        //  77     87     68     76     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    
    static a g(final Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            final Context context2 = context;
            GooglePlayServicesUtil.s(context2);
            final a a = new a();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = a;
            final int n = 1;
            final boolean b = context3.bindService(intent4, (ServiceConnection)o, n);
            if (b) {
                return a;
            }
            throw new IOException("Connection failure");
        }
        catch (PackageManager$NameNotFoundException ex2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        try {
            final Context context2 = context;
            GooglePlayServicesUtil.s(context2);
            final a a = new a();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = a;
            final int n = 1;
            final boolean b = context3.bindService(intent4, (ServiceConnection)o, n);
            if (b) {
                return a;
            }
        }
        catch (GooglePlayServicesNotAvailableException ex) {
            throw new IOException(ex);
        }
        throw new IOException("Connection failure");
    }
    
    public static Info getAdvertisingIdInfo(final Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        fq.ak("Calling this from your main thread can lead to deadlock");
        return a(context, g(context));
    }
    
    public static final class Info
    {
        private final String kw;
        private final boolean kx;
        
        public Info(final String kw, final boolean kx) {
            this.kw = kw;
            this.kx = kx;
        }
        
        public String getId() {
            return this.kw;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.kx;
        }
    }
}
