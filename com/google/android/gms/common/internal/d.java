// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.api.Api$a;
import android.os.IInterface;

public abstract class d<T extends IInterface> implements Api$a, e$b
{
    public static final String[] Lw;
    private final String[] Ds;
    private final Looper IB;
    private final e IQ;
    private T Lr;
    private final ArrayList<d$b<?>> Ls;
    private d$f Lt;
    private volatile int Lu;
    boolean Lv;
    private final Context mContext;
    final Handler mHandler;
    
    static {
        Lw = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected d(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String... ds) {
        this.Ls = new ArrayList<d$b<?>>();
        this.Lu = 1;
        this.Lv = false;
        this.mContext = n.i(context);
        this.IB = n.b(looper, "Looper must not be null");
        this.IQ = new e(context, looper, this);
        this.mHandler = new d$a(this, looper);
        this.c(ds);
        this.Ds = ds;
        this.registerConnectionCallbacks(n.i(googleApiClient$ConnectionCallbacks));
        this.registerConnectionFailedListener(n.i(googleApiClient$OnConnectionFailedListener));
    }
    
    private void az(final int lu) {
        final int lu2 = this.Lu;
        this.Lu = lu;
        if (lu2 != lu) {
            if (lu == 3) {
                this.onConnected();
            }
            else if (lu2 == 3 && lu == 1) {
                this.onDisconnected();
            }
        }
    }
    
    protected final void N(final IBinder binder) {
        try {
            this.a(k$a.Q(binder), new d$e(this));
        }
        catch (RemoteException ex) {
            Log.w("GmsClient", "service died");
        }
    }
    
    protected void a(final int p0, final IBinder p1, final Bundle p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/internal/d.mHandler:Landroid/os/Handler;
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/common/internal/d.mHandler:Landroid/os/Handler;
        //     8: iconst_1       
        //     9: new             new            !!! ERROR
        //    12: dup            
        //    13: aload_0        
        //    14: iload_1        
        //    15: aload_2        
        //    16: aload_3        
        //    17: invokespecial   invokespecial  !!! ERROR
        //    20: invokevirtual   android/os/Handler.obtainMessage:(ILjava/lang/Object;)Landroid/os/Message;
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
    
    protected abstract void a(final k p0, final d$e p1);
    
    public void aA(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)n));
    }
    
    protected void c(final String... array) {
    }
    
    @Override
    public void connect() {
        this.Lv = true;
        this.az(2);
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.az(1);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)googlePlayServicesAvailable));
        }
        else {
            if (this.Lt != null) {
                Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
                this.Lr = null;
                f.J(this.mContext).b(this.getStartServiceAction(), this.Lt);
            }
            this.Lt = new d$f(this);
            if (!f.J(this.mContext).a(this.getStartServiceAction(), this.Lt)) {
                Log.e("GmsClient", "unable to connect to service: " + this.getStartServiceAction());
                this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)9));
            }
        }
    }
    
    protected final void dK() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    @Override
    public void disconnect() {
        this.Lv = false;
        synchronized (this.Ls) {
            for (int size = this.Ls.size(), i = 0; i < size; ++i) {
                this.Ls.get(i).gV();
            }
            this.Ls.clear();
            // monitorexit(this.Ls)
            this.az(1);
            this.Lr = null;
            if (this.Lt != null) {
                f.J(this.mContext).b(this.getStartServiceAction(), this.Lt);
                this.Lt = null;
            }
        }
    }
    
    @Override
    public Bundle fD() {
        return null;
    }
    
    public final T gS() {
        this.dK();
        return this.Lr;
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public final Looper getLooper() {
        return this.IB;
    }
    
    protected abstract String getServiceDescriptor();
    
    protected abstract String getStartServiceAction();
    
    @Override
    public boolean gr() {
        return this.Lv;
    }
    
    @Override
    public boolean isConnected() {
        return this.Lu == 3;
    }
    
    public boolean isConnecting() {
        return this.Lu == 2;
    }
    
    protected abstract T j(final IBinder p0);
    
    protected void onConnected() {
    }
    
    protected void onDisconnected() {
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.IQ.registerConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.IQ.registerConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
}
