// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import android.os.RemoteException;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.zzb;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GoogleApiAvailability;
import android.util.Log;
import java.io.IOException;
import com.google.android.gms.internal.zzav$zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzav;
import com.google.android.gms.common.zza;
import android.content.Context;

public class AdvertisingIdClient
{
    private static boolean zzog;
    private final Context mContext;
    zza zzoa;
    zzav zzob;
    boolean zzoc;
    Object zzod;
    AdvertisingIdClient$zza zzoe;
    final long zzof;
    
    static {
        AdvertisingIdClient.zzog = false;
    }
    
    public AdvertisingIdClient(final Context mContext, final long zzof) {
        this.zzod = new Object();
        zzx.zzv(mContext);
        this.mContext = mContext;
        this.zzoc = false;
        this.zzof = zzof;
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
            return zzav$zza.zzb(zza.zzmS());
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
    }
    
    private void zzaJ() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzod:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoe:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    11: ifnull          28
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoe:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    18: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.cancel:()V
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoe:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    25: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.join:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzof:J
        //    32: lconst_0       
        //    33: lcmp           
        //    34: ifle            53
        //    37: aload_0        
        //    38: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    41: dup            
        //    42: aload_0        
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzof:J
        //    47: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.<init>:(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;J)V
        //    50: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoe:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
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
        zza zza = null;
        Intent intent = null;
        Label_0085: {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                if (AdvertisingIdClient.zzog) {
                    Log.d("Ads", "Skipping gmscore version check");
                    switch (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)) {
                        default: {
                            throw new IOException("Google Play services not available");
                        }
                        case 0:
                        case 2: {
                            break Label_0085;
                            break Label_0085;
                        }
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex2) {
                throw new GooglePlayServicesNotAvailableException(9);
            }
            try {
                GooglePlayServicesUtil.zzaa(context);
                zza = new zza();
                intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                final zzb zzb = com.google.android.gms.common.stats.zzb.zzpD();
                final Context context2 = context;
                final Intent intent2 = intent;
                final Object o = zza;
                final int n = 1;
                final boolean zza2 = zzb.zza(context2, intent2, (ServiceConnection)o, n);
                final boolean zza3 = zza2;
                if (zza3) {
                    return zza;
                }
                throw new IOException("Connection failure");
            }
            catch (GooglePlayServicesNotAvailableException ex) {
                throw new IOException(ex);
            }
        }
        try {
            final zzb zzb = com.google.android.gms.common.stats.zzb.zzpD();
            final Context context2 = context;
            final Intent intent2 = intent;
            final Object o = zza;
            final int n = 1;
            final boolean zza3;
            final boolean zza2 = zza3 = zzb.zza(context2, intent2, (ServiceConnection)o, n);
            if (zza3) {
                return zza;
            }
        }
        catch (Throwable t) {
            throw new IOException(t);
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
        //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzci:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    11: ifnull          21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoa:Lcom/google/android/gms/common/zza;
        //    18: ifnonnull       24
        //    21: aload_0        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoc:Z
        //    28: ifeq            45
        //    31: invokestatic    com/google/android/gms/common/stats/zzb.zzpD:()Lcom/google/android/gms/common/stats/zzb;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoa:Lcom/google/android/gms/common/zza;
        //    42: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoc:Z
        //    50: aload_0        
        //    51: aconst_null    
        //    52: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzob:Lcom/google/android/gms/internal/zzav;
        //    55: aload_0        
        //    56: aconst_null    
        //    57: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoa:Lcom/google/android/gms/common/zza;
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
        zzx.zzci("Calling this from your main thread can lead to deadlock");
        // monitorexit(t)
        Label_0094: {
            synchronized (this) {
                if (this.zzoc) {
                    break Label_0094;
                }
                synchronized (this.zzod) {
                    if (this.zzoe == null || !this.zzoe.zzaK()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zzb(false);
                if (!this.zzoc) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        zzx.zzv(this.zzoa);
        zzx.zzv(this.zzob);
        try {
            final AdvertisingIdClient$Info advertisingIdClient$Info = new AdvertisingIdClient$Info(this.zzob.getId(), this.zzob.zzc(true));
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
        zzx.zzci("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzoc) {
                this.finish();
            }
            this.zzoa = zzo(this.mContext);
            this.zzob = zza(this.mContext, this.zzoa);
            this.zzoc = true;
            if (b) {
                this.zzaJ();
            }
        }
    }
}
