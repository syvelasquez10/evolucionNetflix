// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.text.TextUtils;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import android.os.RemoteException;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.zza$zzb;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzi;

public final class zze extends zzi<com.google.android.gms.cast.internal.zzi>
{
    private static final zzl zzQV;
    private static final Object zzUC;
    private static final Object zzUD;
    private final Cast$Listener zzQH;
    private double zzSg;
    private boolean zzSh;
    private zza$zzb<Cast$ApplicationConnectionResult> zzUA;
    private zza$zzb<Status> zzUB;
    private ApplicationMetadata zzUk;
    private final CastDevice zzUl;
    private final Map<String, Cast$MessageReceivedCallback> zzUm;
    private final long zzUn;
    private zze$zzb zzUo;
    private String zzUp;
    private boolean zzUq;
    private boolean zzUr;
    private boolean zzUs;
    private int zzUt;
    private int zzUu;
    private final AtomicLong zzUv;
    private String zzUw;
    private String zzUx;
    private Bundle zzUy;
    private final Map<Long, zza$zzb<Status>> zzUz;
    
    static {
        zzQV = new zzl("CastClientImpl");
        zzUC = new Object();
        zzUD = new Object();
    }
    
    public zze(final Context context, final Looper looper, final CastDevice zzUl, final long zzUn, final Cast$Listener zzQH, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 10, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzUl = zzUl;
        this.zzQH = zzQH;
        this.zzUn = zzUn;
        this.zzUm = new HashMap<String, Cast$MessageReceivedCallback>();
        this.zzUv = new AtomicLong(0L);
        this.zzUz = new HashMap<Long, zza$zzb<Status>>();
        this.zzlJ();
    }
    
    private void zza(final ApplicationStatus applicationStatus) {
        final String zzlG = applicationStatus.zzlG();
        boolean b;
        if (!zzf.zza(zzlG, this.zzUp)) {
            this.zzUp = zzlG;
            b = true;
        }
        else {
            b = false;
        }
        zze.zzQV.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", b, this.zzUq);
        if (this.zzQH != null && (b || this.zzUq)) {
            this.zzQH.onApplicationStatusChanged();
        }
        this.zzUq = false;
    }
    
    private void zza(final DeviceStatus deviceStatus) {
        final ApplicationMetadata applicationMetadata = deviceStatus.getApplicationMetadata();
        if (!zzf.zza(applicationMetadata, this.zzUk)) {
            this.zzUk = applicationMetadata;
            this.zzQH.onApplicationMetadataChanged(this.zzUk);
        }
        final double zzlM = deviceStatus.zzlM();
        boolean b;
        if (zzlM != Double.NaN && Math.abs(zzlM - this.zzSg) > 1.0E-7) {
            this.zzSg = zzlM;
            b = true;
        }
        else {
            b = false;
        }
        final boolean zzlV = deviceStatus.zzlV();
        if (zzlV != this.zzSh) {
            this.zzSh = zzlV;
            b = true;
        }
        zze.zzQV.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", b, this.zzUr);
        if (this.zzQH != null && (b || this.zzUr)) {
            this.zzQH.onVolumeChanged();
        }
        final int zzlN = deviceStatus.zzlN();
        boolean b2;
        if (zzlN != this.zzUt) {
            this.zzUt = zzlN;
            b2 = true;
        }
        else {
            b2 = false;
        }
        zze.zzQV.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", b2, this.zzUr);
        if (this.zzQH != null && (b2 || this.zzUr)) {
            this.zzQH.onActiveInputStateChanged(this.zzUt);
        }
        final int zzlO = deviceStatus.zzlO();
        boolean b3;
        if (zzlO != this.zzUu) {
            this.zzUu = zzlO;
            b3 = true;
        }
        else {
            b3 = false;
        }
        zze.zzQV.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", b3, this.zzUr);
        if (this.zzQH != null && (b3 || this.zzUr)) {
            this.zzQH.onStandbyStateChanged(this.zzUu);
        }
        this.zzUr = false;
    }
    
    private void zzc(final zza$zzb<Cast$ApplicationConnectionResult> zzUA) {
        synchronized (zze.zzUC) {
            if (this.zzUA != null) {
                this.zzUA.zzm(new zze$zza(new Status(2002)));
            }
            this.zzUA = zzUA;
        }
    }
    
    private void zze(final zza$zzb<Status> zzUB) {
        synchronized (zze.zzUD) {
            if (this.zzUB != null) {
                zzUB.zzm(new Status(2001));
                return;
            }
            this.zzUB = zzUB;
        }
    }
    
    private void zzlJ() {
        this.zzUs = false;
        this.zzUt = -1;
        this.zzUu = -1;
        this.zzUk = null;
        this.zzUp = null;
        this.zzSg = 0.0;
        this.zzSh = false;
    }
    
    private void zzlP() {
        zze.zzQV.zzb("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzUm) {
            this.zzUm.clear();
        }
    }
    
    private void zzlQ() {
        if (!this.zzUs || this.zzUo == null || this.zzUo.isDisposed()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }
    
    @Override
    public void disconnect() {
        zze.zzQV.zzb("disconnect(); ServiceListener=%s, isConnected=%b", this.zzUo, this.isConnected());
        final zze$zzb zzUo = this.zzUo;
        this.zzUo = null;
        if (zzUo == null || zzUo.zzlU() == null) {
            zze.zzQV.zzb("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        this.zzlP();
        try {
            if (this.isConnected() || this.isConnecting()) {
                this.zznK().disconnect();
            }
        }
        catch (RemoteException ex) {
            zze.zzQV.zzb((Throwable)ex, "Error while disconnecting the controller interface: %s", ex.getMessage());
        }
        finally {
            super.disconnect();
        }
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        this.zzlQ();
        return this.zzUk;
    }
    
    public String getApplicationStatus() {
        this.zzlQ();
        return this.zzUp;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzlP();
    }
    
    @Override
    protected void zza(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        zze.zzQV.zzb("in onPostInitHandler; statusCode=%d", n);
        if (n == 0 || n == 1001) {
            this.zzUs = true;
            this.zzUq = true;
            this.zzUr = true;
        }
        else {
            this.zzUs = false;
        }
        int n3 = n;
        if (n == 1001) {
            (this.zzUy = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n3 = 0;
        }
        super.zza(n3, binder, bundle, n2);
    }
    
    public void zza(final String s, final Cast$MessageReceivedCallback cast$MessageReceivedCallback) {
        zzf.zzbD(s);
        this.zzbC(s);
        if (cast$MessageReceivedCallback == null) {
            return;
        }
        synchronized (this.zzUm) {
            this.zzUm.put(s, cast$MessageReceivedCallback);
            // monitorexit(this.zzUm)
            this.zznK().zzbH(s);
        }
    }
    
    public void zza(final String s, final zza$zzb<Status> zza$zzb) {
        this.zze(zza$zzb);
        this.zznK().zzbG(s);
    }
    
    public void zza(final String s, final String s2, final zza$zzb<Status> zza$zzb) {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        zzf.zzbD(s);
        this.zzlQ();
        final long incrementAndGet = this.zzUv.incrementAndGet();
        try {
            this.zzUz.put(incrementAndGet, zza$zzb);
            this.zznK().zza(s, s2, incrementAndGet);
        }
        catch (Throwable t) {
            this.zzUz.remove(incrementAndGet);
            throw t;
        }
    }
    
    public void zza(final String s, final boolean b, final zza$zzb<Cast$ApplicationConnectionResult> zza$zzb) {
        this.zzc(zza$zzb);
        this.zznK().zzf(s, b);
    }
    
    protected com.google.android.gms.cast.internal.zzi zzaw(final IBinder binder) {
        return zzi$zza.zzax(binder);
    }
    
    public void zzb(final String s, final String s2, final zza$zzb<Cast$ApplicationConnectionResult> zza$zzb) {
        this.zzc(zza$zzb);
        this.zznK().zzr(s, s2);
    }
    
    public void zzbC(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            18
        //     7: new             Ljava/lang/IllegalArgumentException;
        //    10: dup            
        //    11: ldc_w           "Channel namespace cannot be null or empty"
        //    14: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/cast/internal/zze.zzUm:Ljava/util/Map;
        //    22: astore_2       
        //    23: aload_2        
        //    24: monitorenter   
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/cast/internal/zze.zzUm:Ljava/util/Map;
        //    29: aload_1        
        //    30: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: checkcast       Lcom/google/android/gms/cast/Cast$MessageReceivedCallback;
        //    38: astore_3       
        //    39: aload_2        
        //    40: monitorexit    
        //    41: aload_3        
        //    42: ifnull          58
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/cast/internal/zze.zznK:()Landroid/os/IInterface;
        //    49: checkcast       Lcom/google/android/gms/cast/internal/zzi;
        //    52: aload_1        
        //    53: invokeinterface com/google/android/gms/cast/internal/zzi.zzbI:(Ljava/lang/String;)V
        //    58: return         
        //    59: astore_1       
        //    60: aload_2        
        //    61: monitorexit    
        //    62: aload_1        
        //    63: athrow         
        //    64: astore_2       
        //    65: getstatic       com/google/android/gms/cast/internal/zze.zzQV:Lcom/google/android/gms/cast/internal/zzl;
        //    68: aload_2        
        //    69: ldc_w           "Error unregistering namespace (%s): %s"
        //    72: iconst_2       
        //    73: anewarray       Ljava/lang/Object;
        //    76: dup            
        //    77: iconst_0       
        //    78: aload_1        
        //    79: aastore        
        //    80: dup            
        //    81: iconst_1       
        //    82: aload_2        
        //    83: invokevirtual   java/lang/IllegalStateException.getMessage:()Ljava/lang/String;
        //    86: aastore        
        //    87: invokevirtual   com/google/android/gms/cast/internal/zzl.zzb:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //    90: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  25     41     59     64     Any
        //  45     58     64     91     Ljava/lang/IllegalStateException;
        //  60     62     59     64     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    @Override
    protected Bundle zzkR() {
        final Bundle bundle = new Bundle();
        zze.zzQV.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzUw, this.zzUx);
        this.zzUl.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzUn);
        this.zzUo = new zze$zzb(this);
        bundle.putParcelable("listener", (Parcelable)new BinderWrapper(this.zzUo.asBinder()));
        if (this.zzUw != null) {
            bundle.putString("last_application_id", this.zzUw);
            if (this.zzUx != null) {
                bundle.putString("last_session_id", this.zzUx);
            }
        }
        return bundle;
    }
    
    @Override
    public Bundle zzlK() {
        if (this.zzUy != null) {
            final Bundle zzUy = this.zzUy;
            this.zzUy = null;
            return zzUy;
        }
        return super.zzlK();
    }
}
