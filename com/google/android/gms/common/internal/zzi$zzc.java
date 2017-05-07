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

public abstract class zzi$zzc<TListener>
{
    private TListener mListener;
    final /* synthetic */ zzi zzaaw;
    private boolean zzaax;
    
    public zzi$zzc(final zzi zzaaw, final TListener mListener) {
        this.zzaaw = zzaaw;
        this.mListener = mListener;
        this.zzaax = false;
    }
    
    public void unregister() {
        this.zznP();
        synchronized (this.zzaaw.zzaan) {
            this.zzaaw.zzaan.remove(this);
        }
    }
    
    protected abstract void zznN();
    
    public void zznO() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/common/internal/zzi$zzc.mListener:Ljava/lang/Object;
        //     6: astore_1       
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/internal/zzi$zzc.zzaax:Z
        //    11: ifeq            44
        //    14: ldc             "GmsClient"
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc             "Callback proxy "
        //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    28: aload_0        
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    32: ldc             " being reused. This is not safe."
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    40: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    43: pop            
        //    44: aload_0        
        //    45: monitorexit    
        //    46: aload_1        
        //    47: ifnull          81
        //    50: aload_0        
        //    51: aload_1        
        //    52: invokevirtual   com/google/android/gms/common/internal/zzi$zzc.zzr:(Ljava/lang/Object;)V
        //    55: aload_0        
        //    56: monitorenter   
        //    57: aload_0        
        //    58: iconst_1       
        //    59: putfield        com/google/android/gms/common/internal/zzi$zzc.zzaax:Z
        //    62: aload_0        
        //    63: monitorexit    
        //    64: aload_0        
        //    65: invokevirtual   com/google/android/gms/common/internal/zzi$zzc.unregister:()V
        //    68: return         
        //    69: astore_1       
        //    70: aload_0        
        //    71: monitorexit    
        //    72: aload_1        
        //    73: athrow         
        //    74: astore_1       
        //    75: aload_0        
        //    76: invokevirtual   com/google/android/gms/common/internal/zzi$zzc.zznN:()V
        //    79: aload_1        
        //    80: athrow         
        //    81: aload_0        
        //    82: invokevirtual   com/google/android/gms/common/internal/zzi$zzc.zznN:()V
        //    85: goto            55
        //    88: astore_1       
        //    89: aload_0        
        //    90: monitorexit    
        //    91: aload_1        
        //    92: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  2      44     69     74     Any
        //  44     46     69     74     Any
        //  50     55     74     81     Ljava/lang/RuntimeException;
        //  57     64     88     93     Any
        //  70     72     69     74     Any
        //  89     91     88     93     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 56, Size: 56
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
    
    public void zznP() {
        synchronized (this) {
            this.mListener = null;
        }
    }
    
    protected abstract void zzr(final TListener p0);
}
