// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import android.os.RemoteException;
import android.os.DeadObjectException;
import java.util.Collection;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionProgressReportCallbacks;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api$Client;
import android.os.IInterface;

public abstract class zzi<T extends IInterface> implements Api$Client, zzj$zza
{
    public static final String[] zzaau;
    private final Context mContext;
    final Handler mHandler;
    private final Account zzMY;
    private final Set<Scope> zzWI;
    private final zze zzWZ;
    private final Looper zzWs;
    private final zzk zzaaj;
    private zzp zzaak;
    private GoogleApiClient$ConnectionProgressReportCallbacks zzaal;
    private T zzaam;
    private final ArrayList<zzi$zzc<?>> zzaan;
    private zzi$zze zzaao;
    private int zzaap;
    private GoogleApiClient$ConnectionCallbacks zzaaq;
    private GoogleApiClient$OnConnectionFailedListener zzaar;
    private final int zzaas;
    protected AtomicInteger zzaat;
    private final Object zzqt;
    
    static {
        zzaau = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected zzi(final Context context, final Looper looper, final int zzaas, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzqt = new Object();
        this.zzaan = new ArrayList<zzi$zzc<?>>();
        this.zzaap = 1;
        this.zzaat = new AtomicInteger(0);
        this.mContext = zzu.zzu(context);
        this.zzWs = zzu.zzb(looper, "Looper must not be null");
        this.zzaaj = zzk.zzah(context);
        this.mHandler = new zzi$zzb(this, looper);
        this.zzaas = zzaas;
        this.zzMY = null;
        this.zzWI = Collections.emptySet();
        this.zzWZ = new GoogleApiClient$Builder(context).zzmv();
        this.zzaaq = zzu.zzu(googleApiClient$ConnectionCallbacks);
        this.zzaar = zzu.zzu(googleApiClient$OnConnectionFailedListener);
    }
    
    protected zzi(final Context context, final Looper looper, final int n, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final zze zze) {
        this(context, looper, zzk.zzah(context), n, zze, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    protected zzi(final Context context, final Looper looper, final zzk zzk, final int zzaas, final zze zze) {
        this.zzqt = new Object();
        this.zzaan = new ArrayList<zzi$zzc<?>>();
        this.zzaap = 1;
        this.zzaat = new AtomicInteger(0);
        this.mContext = zzu.zzb(context, "Context must not be null");
        this.zzWs = zzu.zzb(looper, "Looper must not be null");
        this.zzaaj = zzu.zzb(zzk, "Supervisor must not be null");
        this.mHandler = new zzi$zzb(this, looper);
        this.zzaas = zzaas;
        this.zzWZ = zzu.zzu(zze);
        this.zzMY = zze.getAccount();
        this.zzWI = this.zzb(zze.zznu());
    }
    
    protected zzi(final Context context, final Looper looper, final zzk zzk, final int n, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this(context, looper, zzk, n, zze);
        this.zzaaq = zzu.zzu(googleApiClient$ConnectionCallbacks);
        this.zzaar = zzu.zzu(googleApiClient$OnConnectionFailedListener);
    }
    
    private void zza(final int zzaap, final T zzaam) {
        boolean b = true;
        int n;
        if (zzaap == 3) {
            n = 1;
        }
        else {
            n = 0;
        }
        int n2;
        if (zzaam != null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n != n2) {
            b = false;
        }
        while (true) {
            zzu.zzV(b);
            Label_0100: {
                synchronized (this.zzqt) {
                    this.zzaap = zzaap;
                    this.zzaam = zzaam;
                    switch (zzaap) {
                        case 2: {
                            this.zznF();
                            return;
                        }
                        case 3: {
                            break;
                        }
                        case 1: {
                            break Label_0100;
                        }
                        default: {
                            return;
                        }
                    }
                }
                this.zznE();
                return;
            }
            this.zznG();
        }
    }
    
    private void zza(final GoogleApiClient$ConnectionProgressReportCallbacks googleApiClient$ConnectionProgressReportCallbacks) {
        this.zzaal = zzu.zzb(googleApiClient$ConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
    }
    
    private boolean zza(final int n, final int n2, final T t) {
        synchronized (this.zzqt) {
            if (this.zzaap != n) {
                return false;
            }
            this.zza(n2, t);
            return true;
        }
    }
    
    private Set<Scope> zzb(final Set<Scope> set) {
        final Set<Scope> zza = this.zza(set);
        if (zza == null) {
            return zza;
        }
        final Iterator<Scope> iterator = zza.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zza;
    }
    
    private void zznF() {
        if (this.zzaao != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + this.getStartServiceAction());
            this.zzaaj.zzb(this.getStartServiceAction(), (ServiceConnection)this.zzaao, this.zzkQ());
            this.zzaat.incrementAndGet();
        }
        this.zzaao = new zzi$zze(this, this.zzaat.get());
        if (!this.zzaaj.zza(this.getStartServiceAction(), (ServiceConnection)this.zzaao, this.zzkQ())) {
            Log.e("GmsClient", "unable to connect to service: " + this.getStartServiceAction());
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaat.get(), 9));
        }
    }
    
    private void zznG() {
        if (this.zzaao != null) {
            this.zzaaj.zzb(this.getStartServiceAction(), (ServiceConnection)this.zzaao, this.zzkQ());
            this.zzaao = null;
        }
    }
    
    @Override
    public void connect(final GoogleApiClient$ConnectionProgressReportCallbacks googleApiClient$ConnectionProgressReportCallbacks) {
        this.zza(googleApiClient$ConnectionProgressReportCallbacks);
        this.zza(2, null);
    }
    
    @Override
    public void disconnect() {
        this.zzaat.incrementAndGet();
        synchronized (this.zzaan) {
            for (int size = this.zzaan.size(), i = 0; i < size; ++i) {
                this.zzaan.get(i).zznP();
            }
            this.zzaan.clear();
            // monitorexit(this.zzaan)
            this.zza(1, null);
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, String[] zzaam) {
    Label_0137:
        while (true) {
            while (true) {
                Label_0127: {
                    Label_0117: {
                        Label_0107: {
                            synchronized (this.zzqt) {
                                final int zzaap = this.zzaap;
                                zzaam = (String[])(Object)this.zzaam;
                                // monitorexit(this.zzqt)
                                printWriter.append(s).append("mConnectState=");
                                switch (zzaap) {
                                    default: {
                                        printWriter.print("UNKNOWN");
                                        printWriter.append(" mService=");
                                        if (zzaam == null) {
                                            printWriter.println("null");
                                            return;
                                        }
                                        break Label_0137;
                                    }
                                    case 2: {
                                        break;
                                    }
                                    case 3: {
                                        break Label_0107;
                                    }
                                    case 4: {
                                        break Label_0117;
                                    }
                                    case 1: {
                                        break Label_0127;
                                    }
                                }
                            }
                            printWriter.print("CONNECTING");
                            continue;
                        }
                        printWriter.print("CONNECTED");
                        continue;
                    }
                    printWriter.print("DISCONNECTING");
                    continue;
                }
                printWriter.print("DISCONNECTED");
                continue;
            }
        }
        printWriter.append(this.getServiceDescriptor()).append("@").println(Integer.toHexString(System.identityHashCode(((IInterface)(Object)zzaam).asBinder())));
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    public final Looper getLooper() {
        return this.zzWs;
    }
    
    @Override
    public void getRemoteService(final IAccountAccessor accountAccessor, final Set<Scope> set) {
        try {
            final GetServiceRequest zzf = new GetServiceRequest(this.zzaas).zzcb(this.mContext.getPackageName()).zzf(this.zzkR());
            if (set != null) {
                zzf.zzb(set);
            }
            if (this.requiresSignIn()) {
                zzf.zzb(this.zznr()).zzb(accountAccessor);
            }
            else if (this.requiresAccount()) {
                zzf.zzb(this.zzMY);
            }
            this.zzaak.zza(new zzi$zzd(this, this.zzaat.get()), zzf);
        }
        catch (DeadObjectException ex2) {
            Log.w("GmsClient", "service died");
            this.zzbs(1);
        }
        catch (RemoteException ex) {
            Log.w("GmsClient", "Remote exception occurred", (Throwable)ex);
        }
    }
    
    protected abstract String getServiceDescriptor();
    
    protected abstract String getStartServiceAction();
    
    @Override
    public boolean isConnected() {
        while (true) {
            synchronized (this.zzqt) {
                if (this.zzaap == 3) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isConnecting() {
        while (true) {
            synchronized (this.zzqt) {
                if (this.zzaap == 2) {
                    return true;
                }
            }
            return false;
        }
    }
    
    protected void onConnectionFailed(final ConnectionResult connectionResult) {
    }
    
    protected void onConnectionSuspended(final int n) {
    }
    
    public boolean requiresAccount() {
        return false;
    }
    
    @Override
    public boolean requiresSignIn() {
        return false;
    }
    
    @Override
    public void validateAccount(final IAccountAccessor accountAccessor) {
        final ValidateAccountRequest validateAccountRequest = new ValidateAccountRequest(accountAccessor, this.zzWI.toArray(new Scope[this.zzWI.size()]), this.mContext.getPackageName(), this.zznL());
        try {
            this.zzaak.zza(new zzi$zzd(this, this.zzaat.get()), validateAccountRequest);
        }
        catch (DeadObjectException ex2) {
            Log.w("GmsClient", "service died");
            this.zzbs(1);
        }
        catch (RemoteException ex) {
            Log.w("GmsClient", "Remote exception occurred", (Throwable)ex);
        }
    }
    
    protected abstract T zzT(final IBinder p0);
    
    protected Set<Scope> zza(final Set<Scope> set) {
        return set;
    }
    
    protected void zza(final int p0, final Bundle p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     8: iconst_5       
        //     9: iload_3        
        //    10: iconst_m1      
        //    11: new             new            !!! ERROR
        //    14: dup            
        //    15: aload_0        
        //    16: iload_1        
        //    17: aload_2        
        //    18: invokespecial   invokespecial  !!! ERROR
        //    21: invokevirtual   android/os/Handler.obtainMessage:(IIILjava/lang/Object;)Landroid/os/Message;
        //    24: invokevirtual   android/os/Handler.sendMessage:(Landroid/os/Message;)Z
        //    27: pop            
        //    28: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
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
    
    protected void zza(final int p0, final IBinder p1, final Bundle p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     8: iconst_1       
        //     9: iload           4
        //    11: iconst_m1      
        //    12: new             new            !!! ERROR
        //    15: dup            
        //    16: aload_0        
        //    17: iload_1        
        //    18: aload_2        
        //    19: aload_3        
        //    20: invokespecial   invokespecial  !!! ERROR
        //    23: invokevirtual   android/os/Handler.obtainMessage:(IIILjava/lang/Object;)Landroid/os/Message;
        //    26: invokevirtual   android/os/Handler.sendMessage:(Landroid/os/Message;)Z
        //    29: pop            
        //    30: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
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
    
    public void zzbs(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaat.get(), n));
    }
    
    protected void zzbt(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/common/internal/zzi.mHandler:Landroid/os/Handler;
        //     8: bipush          6
        //    10: iload_1        
        //    11: iconst_m1      
        //    12: new             new            !!! ERROR
        //    15: dup            
        //    16: aload_0        
        //    17: invokespecial   invokespecial  !!! ERROR
        //    20: invokevirtual   android/os/Handler.obtainMessage:(IIILjava/lang/Object;)Landroid/os/Message;
        //    23: invokevirtual   android/os/Handler.sendMessage:(Landroid/os/Message;)Z
        //    26: pop            
        //    27: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
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
    
    protected String zzkQ() {
        return this.zzWZ.zznx();
    }
    
    protected Bundle zzkR() {
        return new Bundle();
    }
    
    @Override
    public Bundle zzlK() {
        return null;
    }
    
    protected void zznE() {
    }
    
    protected final zze zznI() {
        return this.zzWZ;
    }
    
    protected final void zznJ() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    public final T zznK() {
        synchronized (this.zzqt) {
            if (this.zzaap == 4) {
                throw new DeadObjectException();
            }
        }
        this.zznJ();
        zzu.zza(this.zzaam != null, "Client is connected but service is null");
        final IInterface zzaam = this.zzaam;
        // monitorexit(o)
        return (T)zzaam;
    }
    
    protected Bundle zznL() {
        return null;
    }
    
    public final Account zznr() {
        if (this.zzMY != null) {
            return this.zzMY;
        }
        return new Account("<<default account>>", "com.google");
    }
}
