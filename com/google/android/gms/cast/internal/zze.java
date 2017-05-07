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
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc$zzb;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.zzj;

public final class zze extends zzj<zzi>
{
    private static final zzl zzTy;
    private static final Object zzXf;
    private static final Object zzXg;
    private final Cast$Listener zzTk;
    private double zzUJ;
    private boolean zzUK;
    private ApplicationMetadata zzWN;
    private final CastDevice zzWO;
    private final Map<String, Cast$MessageReceivedCallback> zzWP;
    private final long zzWQ;
    private zze$zzb zzWR;
    private String zzWS;
    private boolean zzWT;
    private boolean zzWU;
    private boolean zzWV;
    private int zzWW;
    private int zzWX;
    private final AtomicLong zzWY;
    private String zzWZ;
    private String zzXa;
    private Bundle zzXb;
    private final Map<Long, zzc$zzb<Status>> zzXc;
    private zzc$zzb<Cast$ApplicationConnectionResult> zzXd;
    private zzc$zzb<Status> zzXe;
    
    static {
        zzTy = new zzl("CastClientImpl");
        zzXf = new Object();
        zzXg = new Object();
    }
    
    public zze(final Context context, final Looper looper, final zzf zzf, final CastDevice zzWO, final long zzWQ, final Cast$Listener zzTk, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 10, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzWO = zzWO;
        this.zzTk = zzTk;
        this.zzWQ = zzWQ;
        this.zzWP = new HashMap<String, Cast$MessageReceivedCallback>();
        this.zzWY = new AtomicLong(0L);
        this.zzXc = new HashMap<Long, zzc$zzb<Status>>();
        this.zzmv();
    }
    
    private void zza(final ApplicationStatus applicationStatus) {
        final String zzms = applicationStatus.zzms();
        boolean b;
        if (!com.google.android.gms.cast.internal.zzf.zza(zzms, this.zzWS)) {
            this.zzWS = zzms;
            b = true;
        }
        else {
            b = false;
        }
        zze.zzTy.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", b, this.zzWT);
        if (this.zzTk != null && (b || this.zzWT)) {
            this.zzTk.onApplicationStatusChanged();
        }
        this.zzWT = false;
    }
    
    private void zza(final DeviceStatus deviceStatus) {
        final ApplicationMetadata applicationMetadata = deviceStatus.getApplicationMetadata();
        if (!com.google.android.gms.cast.internal.zzf.zza(applicationMetadata, this.zzWN)) {
            this.zzWN = applicationMetadata;
            this.zzTk.onApplicationMetadataChanged(this.zzWN);
        }
        final double zzmy = deviceStatus.zzmy();
        boolean b;
        if (zzmy != Double.NaN && Math.abs(zzmy - this.zzUJ) > 1.0E-7) {
            this.zzUJ = zzmy;
            b = true;
        }
        else {
            b = false;
        }
        final boolean zzmH = deviceStatus.zzmH();
        if (zzmH != this.zzUK) {
            this.zzUK = zzmH;
            b = true;
        }
        zze.zzTy.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", b, this.zzWU);
        if (this.zzTk != null && (b || this.zzWU)) {
            this.zzTk.onVolumeChanged();
        }
        final int zzmz = deviceStatus.zzmz();
        boolean b2;
        if (zzmz != this.zzWW) {
            this.zzWW = zzmz;
            b2 = true;
        }
        else {
            b2 = false;
        }
        zze.zzTy.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", b2, this.zzWU);
        if (this.zzTk != null && (b2 || this.zzWU)) {
            this.zzTk.onActiveInputStateChanged(this.zzWW);
        }
        final int zzmA = deviceStatus.zzmA();
        boolean b3;
        if (zzmA != this.zzWX) {
            this.zzWX = zzmA;
            b3 = true;
        }
        else {
            b3 = false;
        }
        zze.zzTy.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", b3, this.zzWU);
        if (this.zzTk != null && (b3 || this.zzWU)) {
            this.zzTk.onStandbyStateChanged(this.zzWX);
        }
        this.zzWU = false;
    }
    
    private void zzc(final zzc$zzb<Cast$ApplicationConnectionResult> zzXd) {
        synchronized (zze.zzXf) {
            if (this.zzXd != null) {
                this.zzXd.zzn(new zze$zza(new Status(2002)));
            }
            this.zzXd = zzXd;
        }
    }
    
    private void zze(final zzc$zzb<Status> zzXe) {
        synchronized (zze.zzXg) {
            if (this.zzXe != null) {
                zzXe.zzn(new Status(2001));
                return;
            }
            this.zzXe = zzXe;
        }
    }
    
    private void zzmB() {
        zze.zzTy.zzb("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzWP) {
            this.zzWP.clear();
        }
    }
    
    private void zzmC() {
        if (!this.zzWV || this.zzWR == null || this.zzWR.isDisposed()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }
    
    private void zzmv() {
        this.zzWV = false;
        this.zzWW = -1;
        this.zzWX = -1;
        this.zzWN = null;
        this.zzWS = null;
        this.zzUJ = 0.0;
        this.zzUK = false;
    }
    
    @Override
    public void disconnect() {
        zze.zzTy.zzb("disconnect(); ServiceListener=%s, isConnected=%b", this.zzWR, this.isConnected());
        final zze$zzb zzWR = this.zzWR;
        this.zzWR = null;
        if (zzWR == null || zzWR.zzmG() == null) {
            zze.zzTy.zzb("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        this.zzmB();
        try {
            if (this.isConnected() || this.isConnecting()) {
                this.zzoA().disconnect();
            }
        }
        catch (RemoteException ex) {
            zze.zzTy.zzb((Throwable)ex, "Error while disconnecting the controller interface: %s", ex.getMessage());
        }
        finally {
            super.disconnect();
        }
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        this.zzmC();
        return this.zzWN;
    }
    
    public String getApplicationStatus() {
        this.zzmC();
        return this.zzWS;
    }
    
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzmB();
    }
    
    @Override
    protected void zza(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        zze.zzTy.zzb("in onPostInitHandler; statusCode=%d", n);
        if (n == 0 || n == 1001) {
            this.zzWV = true;
            this.zzWT = true;
            this.zzWU = true;
        }
        else {
            this.zzWV = false;
        }
        int n3 = n;
        if (n == 1001) {
            (this.zzXb = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n3 = 0;
        }
        super.zza(n3, binder, bundle, n2);
    }
    
    public void zza(final String s, final Cast$MessageReceivedCallback cast$MessageReceivedCallback) {
        com.google.android.gms.cast.internal.zzf.zzbL(s);
        this.zzbK(s);
        if (cast$MessageReceivedCallback == null) {
            return;
        }
        synchronized (this.zzWP) {
            this.zzWP.put(s, cast$MessageReceivedCallback);
            // monitorexit(this.zzWP)
            this.zzoA().zzbP(s);
        }
    }
    
    public void zza(final String s, final zzc$zzb<Status> zzc$zzb) {
        this.zze(zzc$zzb);
        this.zzoA().zzbO(s);
    }
    
    public void zza(final String s, final String s2, final zzc$zzb<Status> zzc$zzb) {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        com.google.android.gms.cast.internal.zzf.zzbL(s);
        this.zzmC();
        final long incrementAndGet = this.zzWY.incrementAndGet();
        try {
            this.zzXc.put(incrementAndGet, zzc$zzb);
            this.zzoA().zza(s, s2, incrementAndGet);
        }
        catch (Throwable t) {
            this.zzXc.remove(incrementAndGet);
            throw t;
        }
    }
    
    public void zza(final String s, final boolean b, final zzc$zzb<Cast$ApplicationConnectionResult> zzc$zzb) {
        this.zzc(zzc$zzb);
        this.zzoA().zzf(s, b);
    }
    
    protected zzi zzaA(final IBinder binder) {
        return zzi$zza.zzaB(binder);
    }
    
    public void zzb(final String s, final String s2, final zzc$zzb<Cast$ApplicationConnectionResult> zzc$zzb) {
        this.zzc(zzc$zzb);
        this.zzoA().zzt(s, s2);
    }
    
    public void zzbK(final String p0) {
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
        //    19: getfield        com/google/android/gms/cast/internal/zze.zzWP:Ljava/util/Map;
        //    22: astore_2       
        //    23: aload_2        
        //    24: monitorenter   
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/cast/internal/zze.zzWP:Ljava/util/Map;
        //    29: aload_1        
        //    30: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: checkcast       Lcom/google/android/gms/cast/Cast$MessageReceivedCallback;
        //    38: astore_3       
        //    39: aload_2        
        //    40: monitorexit    
        //    41: aload_3        
        //    42: ifnull          58
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/cast/internal/zze.zzoA:()Landroid/os/IInterface;
        //    49: checkcast       Lcom/google/android/gms/cast/internal/zzi;
        //    52: aload_1        
        //    53: invokeinterface com/google/android/gms/cast/internal/zzi.zzbQ:(Ljava/lang/String;)V
        //    58: return         
        //    59: astore_1       
        //    60: aload_2        
        //    61: monitorexit    
        //    62: aload_1        
        //    63: athrow         
        //    64: astore_2       
        //    65: getstatic       com/google/android/gms/cast/internal/zze.zzTy:Lcom/google/android/gms/cast/internal/zzl;
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
    protected String zzfA() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    @Override
    protected String zzfB() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    @Override
    protected Bundle zzli() {
        final Bundle bundle = new Bundle();
        zze.zzTy.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzWZ, this.zzXa);
        this.zzWO.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzWQ);
        this.zzWR = new zze$zzb(this);
        bundle.putParcelable("listener", (Parcelable)new BinderWrapper(this.zzWR.asBinder()));
        if (this.zzWZ != null) {
            bundle.putString("last_application_id", this.zzWZ);
            if (this.zzXa != null) {
                bundle.putString("last_session_id", this.zzXa);
            }
        }
        return bundle;
    }
    
    @Override
    public Bundle zzmw() {
        if (this.zzXb != null) {
            final Bundle zzXb = this.zzXb;
            this.zzXb = null;
            return zzXb;
        }
        return super.zzmw();
    }
}
