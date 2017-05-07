// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import android.os.RemoteException;
import android.util.Log;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.zzb;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import com.google.android.gms.internal.zzav$zza;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.internal.zzav;
import com.google.android.gms.common.zza;
import android.content.Context;

public class AdvertisingIdClient
{
    private final Context mContext;
    zza zznX;
    zzav zznY;
    boolean zznZ;
    Object zzoa;
    AdvertisingIdClient$zza zzob;
    final long zzoc;
    
    public AdvertisingIdClient(final Context mContext, final long zzoc) {
        this.zzoa = new Object();
        zzu.zzu(mContext);
        this.mContext = mContext;
        this.zznZ = false;
        this.zzoc = zzoc;
    }
    
    public static AdvertisingIdClient$Info getAdvertisingIdInfo(Context context) {
        context = (Context)new AdvertisingIdClient(context, -1L);
        try {
            ((AdvertisingIdClient)context).zzb(false);
            return ((AdvertisingIdClient)context).getInfo();
        }
        finally {
            ((AdvertisingIdClient)context).finish();
        }
    }
    
    static zzav zza(final Context context, final zza zza) {
        try {
            return zzav$zza.zzb(zza.zzmf());
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
    }
    
    private void zzaJ() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoa:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzob:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    11: ifnull          28
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzob:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    18: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.cancel:()V
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzob:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    25: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.join:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoc:J
        //    32: lconst_0       
        //    33: lcmp           
        //    34: ifle            53
        //    37: aload_0        
        //    38: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    41: dup            
        //    42: aload_0        
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoc:J
        //    47: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.<init>:(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;J)V
        //    50: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzob:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    53: aload_1        
        //    54: monitorexit    
        //    55: return         
        //    56: astore_2       
        //    57: aload_1        
        //    58: monitorexit    
        //    59: aload_2        
        //    60: athrow         
        //    61: astore_2       
        //    62: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      21     56     61     Any
        //  21     28     61     65     Ljava/lang/InterruptedException;
        //  21     28     56     61     Any
        //  28     53     56     61     Any
        //  53     55     56     61     Any
        //  57     59     56     61     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    static zza zzo(final Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            final Context context2 = context;
            GooglePlayServicesUtil.zzY(context2);
            final zza zza = new zza();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final zzb zzb = com.google.android.gms.common.stats.zzb.zzoM();
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = zza;
            final int n = 1;
            final boolean b = zzb.zza(context3, intent4, (ServiceConnection)o, n);
            if (b) {
                return zza;
            }
            throw new IOException("Connection failure");
        }
        catch (PackageManager$NameNotFoundException ex2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        try {
            final Context context2 = context;
            GooglePlayServicesUtil.zzY(context2);
            final zza zza = new zza();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final zzb zzb = com.google.android.gms.common.stats.zzb.zzoM();
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = zza;
            final int n = 1;
            final boolean b = zzb.zza(context3, intent4, (ServiceConnection)o, n);
            if (b) {
                return zza;
            }
        }
        catch (GooglePlayServicesNotAvailableException ex) {
            throw new IOException(ex);
        }
        throw new IOException("Connection failure");
    }
    
    @Override
    protected void finalize() {
        this.finish();
        super.finalize();
    }
    
    public void finish() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Calling this from your main thread can lead to deadlock"
        //     2: invokestatic    com/google/android/gms/common/internal/zzu.zzbZ:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    11: ifnull          21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznX:Lcom/google/android/gms/common/zza;
        //    18: ifnonnull       24
        //    21: aload_0        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznZ:Z
        //    28: ifeq            45
        //    31: invokestatic    com/google/android/gms/common/stats/zzb.zzoM:()Lcom/google/android/gms/common/stats/zzb;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznX:Lcom/google/android/gms/common/zza;
        //    42: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznZ:Z
        //    50: aload_0        
        //    51: aconst_null    
        //    52: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznY:Lcom/google/android/gms/internal/zzav;
        //    55: aload_0        
        //    56: aconst_null    
        //    57: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zznX:Lcom/google/android/gms/common/zza;
        //    60: aload_0        
        //    61: monitorexit    
        //    62: return         
        //    63: astore_1       
        //    64: aload_0        
        //    65: monitorexit    
        //    66: aload_1        
        //    67: athrow         
        //    68: astore_1       
        //    69: ldc             "AdvertisingIdClient"
        //    71: ldc             "AdvertisingIdClient unbindService failed."
        //    73: aload_1        
        //    74: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    77: pop            
        //    78: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      21     63     68     Any
        //  21     23     63     68     Any
        //  24     45     68     81     Ljava/lang/IllegalArgumentException;
        //  24     45     63     68     Any
        //  45     62     63     68     Any
        //  64     66     63     68     Any
        //  69     78     63     68     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public AdvertisingIdClient$Info getInfo() {
        zzu.zzbZ("Calling this from your main thread can lead to deadlock");
        // monitorexit(t)
        Label_0094: {
            synchronized (this) {
                if (this.zznZ) {
                    break Label_0094;
                }
                synchronized (this.zzoa) {
                    if (this.zzob == null || !this.zzob.zzaK()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zzb(false);
                if (!this.zznZ) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        zzu.zzu(this.zznX);
        zzu.zzu(this.zznY);
        try {
            final AdvertisingIdClient$Info advertisingIdClient$Info = new AdvertisingIdClient$Info(this.zznY.getId(), this.zznY.zzc(true));
            // monitorexit(this)
            this.zzaJ();
            return advertisingIdClient$Info;
        }
        catch (RemoteException ex2) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex2);
            throw new IOException("Remote exception");
        }
    }
    
    protected void zzb(final boolean b) {
        zzu.zzbZ("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zznZ) {
                this.finish();
            }
            this.zznX = zzo(this.mContext);
            this.zznY = zza(this.mContext, this.zznX);
            this.zznZ = true;
            if (b) {
                this.zzaJ();
            }
        }
    }
}
