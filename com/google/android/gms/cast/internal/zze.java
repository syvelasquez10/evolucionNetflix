// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.text.TextUtils;
import com.google.android.gms.cast.JoinOptions;
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
import com.google.android.gms.internal.zzlb$zzb;
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
    private static final zzl zzVo;
    private static final Object zzYX;
    private static final Object zzYY;
    private final Cast$Listener zzUZ;
    private double zzWA;
    private boolean zzWB;
    private ApplicationMetadata zzYF;
    private final CastDevice zzYG;
    private final Map<String, Cast$MessageReceivedCallback> zzYH;
    private final long zzYI;
    private zze$zzb zzYJ;
    private String zzYK;
    private boolean zzYL;
    private boolean zzYM;
    private boolean zzYN;
    private int zzYO;
    private int zzYP;
    private final AtomicLong zzYQ;
    private String zzYR;
    private String zzYS;
    private Bundle zzYT;
    private final Map<Long, zzlb$zzb<Status>> zzYU;
    private zzlb$zzb<Cast$ApplicationConnectionResult> zzYV;
    private zzlb$zzb<Status> zzYW;
    
    static {
        zzVo = new zzl("CastClientImpl");
        zzYX = new Object();
        zzYY = new Object();
    }
    
    public zze(final Context context, final Looper looper, final zzf zzf, final CastDevice zzYG, final long zzYI, final Cast$Listener zzUZ, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 10, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzYG = zzYG;
        this.zzUZ = zzUZ;
        this.zzYI = zzYI;
        this.zzYH = new HashMap<String, Cast$MessageReceivedCallback>();
        this.zzYQ = new AtomicLong(0L);
        this.zzYU = new HashMap<Long, zzlb$zzb<Status>>();
        this.zzmR();
    }
    
    private void zza(final ApplicationStatus applicationStatus) {
        final String zzmO = applicationStatus.zzmO();
        boolean b;
        if (!com.google.android.gms.cast.internal.zzf.zza(zzmO, this.zzYK)) {
            this.zzYK = zzmO;
            b = true;
        }
        else {
            b = false;
        }
        zze.zzVo.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", b, this.zzYL);
        if (this.zzUZ != null && (b || this.zzYL)) {
            this.zzUZ.onApplicationStatusChanged();
        }
        this.zzYL = false;
    }
    
    private void zza(final DeviceStatus deviceStatus) {
        final ApplicationMetadata applicationMetadata = deviceStatus.getApplicationMetadata();
        if (!com.google.android.gms.cast.internal.zzf.zza(applicationMetadata, this.zzYF)) {
            this.zzYF = applicationMetadata;
            this.zzUZ.onApplicationMetadataChanged(this.zzYF);
        }
        final double zzmU = deviceStatus.zzmU();
        boolean b;
        if (zzmU != Double.NaN && Math.abs(zzmU - this.zzWA) > 1.0E-7) {
            this.zzWA = zzmU;
            b = true;
        }
        else {
            b = false;
        }
        final boolean zznd = deviceStatus.zznd();
        if (zznd != this.zzWB) {
            this.zzWB = zznd;
            b = true;
        }
        zze.zzVo.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", b, this.zzYM);
        if (this.zzUZ != null && (b || this.zzYM)) {
            this.zzUZ.onVolumeChanged();
        }
        final int zzmV = deviceStatus.zzmV();
        boolean b2;
        if (zzmV != this.zzYO) {
            this.zzYO = zzmV;
            b2 = true;
        }
        else {
            b2 = false;
        }
        zze.zzVo.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", b2, this.zzYM);
        if (this.zzUZ != null && (b2 || this.zzYM)) {
            this.zzUZ.onActiveInputStateChanged(this.zzYO);
        }
        final int zzmW = deviceStatus.zzmW();
        boolean b3;
        if (zzmW != this.zzYP) {
            this.zzYP = zzmW;
            b3 = true;
        }
        else {
            b3 = false;
        }
        zze.zzVo.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", b3, this.zzYM);
        if (this.zzUZ != null && (b3 || this.zzYM)) {
            this.zzUZ.onStandbyStateChanged(this.zzYP);
        }
        this.zzYM = false;
    }
    
    private void zza(final zzlb$zzb<Cast$ApplicationConnectionResult> zzYV) {
        synchronized (zze.zzYX) {
            if (this.zzYV != null) {
                this.zzYV.zzp(new zze$zza(new Status(2002)));
            }
            this.zzYV = zzYV;
        }
    }
    
    private void zzc(final zzlb$zzb<Status> zzYW) {
        synchronized (zze.zzYY) {
            if (this.zzYW != null) {
                zzYW.zzp(new Status(2001));
                return;
            }
            this.zzYW = zzYW;
        }
    }
    
    private void zzmR() {
        this.zzYN = false;
        this.zzYO = -1;
        this.zzYP = -1;
        this.zzYF = null;
        this.zzYK = null;
        this.zzWA = 0.0;
        this.zzWB = false;
    }
    
    private void zzmX() {
        zze.zzVo.zzb("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzYH) {
            this.zzYH.clear();
        }
    }
    
    private void zzmY() {
        if (!this.zzYN || this.zzYJ == null || this.zzYJ.isDisposed()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }
    
    @Override
    public void disconnect() {
        zze.zzVo.zzb("disconnect(); ServiceListener=%s, isConnected=%b", this.zzYJ, this.isConnected());
        final zze$zzb zzYJ = this.zzYJ;
        this.zzYJ = null;
        if (zzYJ == null || zzYJ.zznc() == null) {
            zze.zzVo.zzb("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        this.zzmX();
        try {
            this.zzpc().disconnect();
        }
        catch (IllegalStateException ex) {}
        catch (RemoteException zzYJ) {}
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        this.zzmY();
        return this.zzYF;
    }
    
    public String getApplicationStatus() {
        this.zzmY();
        return this.zzYK;
    }
    
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzmX();
    }
    
    @Override
    protected void zza(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        zze.zzVo.zzb("in onPostInitHandler; statusCode=%d", n);
        if (n == 0 || n == 1001) {
            this.zzYN = true;
            this.zzYL = true;
            this.zzYM = true;
        }
        else {
            this.zzYN = false;
        }
        int n3 = n;
        if (n == 1001) {
            (this.zzYT = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n3 = 0;
        }
        super.zza(n3, binder, bundle, n2);
    }
    
    public void zza(final String s, final Cast$MessageReceivedCallback cast$MessageReceivedCallback) {
        com.google.android.gms.cast.internal.zzf.zzbM(s);
        this.zzbL(s);
        if (cast$MessageReceivedCallback == null) {
            return;
        }
        synchronized (this.zzYH) {
            this.zzYH.put(s, cast$MessageReceivedCallback);
            // monitorexit(this.zzYH)
            this.zzpc().zzbQ(s);
        }
    }
    
    public void zza(final String s, final zzlb$zzb<Status> zzlb$zzb) {
        this.zzc(zzlb$zzb);
        this.zzpc().zzbP(s);
    }
    
    public void zza(final String s, final String s2, final JoinOptions joinOptions, final zzlb$zzb<Cast$ApplicationConnectionResult> zzlb$zzb) {
        this.zza(zzlb$zzb);
        JoinOptions joinOptions2 = joinOptions;
        if (joinOptions == null) {
            joinOptions2 = new JoinOptions();
        }
        this.zzpc().zza(s, s2, joinOptions2);
    }
    
    public void zza(final String s, final String s2, final zzlb$zzb<Status> zzlb$zzb) {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        com.google.android.gms.cast.internal.zzf.zzbM(s);
        this.zzmY();
        final long incrementAndGet = this.zzYQ.incrementAndGet();
        try {
            this.zzYU.put(incrementAndGet, zzlb$zzb);
            this.zzpc().zzb(s, s2, incrementAndGet);
        }
        catch (Throwable t) {
            this.zzYU.remove(incrementAndGet);
            throw t;
        }
    }
    
    public void zza(final String s, final boolean b, final zzlb$zzb<Cast$ApplicationConnectionResult> zzlb$zzb) {
        this.zza(zzlb$zzb);
        this.zzpc().zzf(s, b);
    }
    
    protected zzi zzaA(final IBinder binder) {
        return zzi$zza.zzaB(binder);
    }
    
    public void zzbL(final String p0) {
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
        //    19: getfield        com/google/android/gms/cast/internal/zze.zzYH:Ljava/util/Map;
        //    22: astore_2       
        //    23: aload_2        
        //    24: monitorenter   
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/cast/internal/zze.zzYH:Ljava/util/Map;
        //    29: aload_1        
        //    30: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: checkcast       Lcom/google/android/gms/cast/Cast$MessageReceivedCallback;
        //    38: astore_3       
        //    39: aload_2        
        //    40: monitorexit    
        //    41: aload_3        
        //    42: ifnull          58
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/cast/internal/zze.zzpc:()Landroid/os/IInterface;
        //    49: checkcast       Lcom/google/android/gms/cast/internal/zzi;
        //    52: aload_1        
        //    53: invokeinterface com/google/android/gms/cast/internal/zzi.zzbR:(Ljava/lang/String;)V
        //    58: return         
        //    59: astore_1       
        //    60: aload_2        
        //    61: monitorexit    
        //    62: aload_1        
        //    63: athrow         
        //    64: astore_2       
        //    65: getstatic       com/google/android/gms/cast/internal/zze.zzVo:Lcom/google/android/gms/cast/internal/zzl;
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
    protected String zzfK() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    @Override
    protected Bundle zzly() {
        final Bundle bundle = new Bundle();
        zze.zzVo.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzYR, this.zzYS);
        this.zzYG.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzYI);
        this.zzYJ = new zze$zzb(this);
        bundle.putParcelable("listener", (Parcelable)new BinderWrapper(this.zzYJ.asBinder()));
        if (this.zzYR != null) {
            bundle.putString("last_application_id", this.zzYR);
            if (this.zzYS != null) {
                bundle.putString("last_session_id", this.zzYS);
            }
        }
        return bundle;
    }
    
    @Override
    public Bundle zzmS() {
        if (this.zzYT != null) {
            final Bundle zzYT = this.zzYT;
            this.zzYT = null;
            return zzYT;
        }
        return super.zzmS();
    }
}
