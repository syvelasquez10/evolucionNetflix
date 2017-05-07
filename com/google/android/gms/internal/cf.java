// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;

@ez
public final class cf
{
    public static void a(final Context context, final b b) {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            b.a(bn.bs());
            return;
        }
        new a(context, b);
    }
    
    public static final class a implements ConnectionCallbacks, OnConnectionFailedListener
    {
        private final Object mw;
        private final b pN;
        private final cg pO;
        
        public a(final Context context, final b b) {
            this(context, b, false);
        }
        
        a(final Context context, final b pn, final boolean b) {
            this.mw = new Object();
            this.pN = pn;
            this.pO = new cg(context, this, this, 6111000);
            if (!b) {
                this.pO.connect();
            }
        }
        
        @Override
        public void onConnected(final Bundle p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: invokestatic    com/google/android/gms/internal/bn.bs:()Landroid/os/Bundle;
            //     3: astore_1       
            //     4: aload_0        
            //     5: getfield        com/google/android/gms/internal/cf$a.mw:Ljava/lang/Object;
            //     8: astore_3       
            //     9: aload_3        
            //    10: monitorenter   
            //    11: aload_0        
            //    12: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    15: invokevirtual   com/google/android/gms/internal/cg.bC:()Lcom/google/android/gms/internal/ch;
            //    18: astore_2       
            //    19: aload_2        
            //    20: ifnull          186
            //    23: aload_2        
            //    24: invokeinterface com/google/android/gms/internal/ch.bD:()Landroid/os/Bundle;
            //    29: astore_2       
            //    30: aload_0        
            //    31: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    34: invokevirtual   com/google/android/gms/internal/cg.isConnected:()Z
            //    37: ifne            52
            //    40: aload_2        
            //    41: astore_1       
            //    42: aload_0        
            //    43: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    46: invokevirtual   com/google/android/gms/internal/cg.isConnecting:()Z
            //    49: ifeq            61
            //    52: aload_0        
            //    53: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    56: invokevirtual   com/google/android/gms/internal/cg.disconnect:()V
            //    59: aload_2        
            //    60: astore_1       
            //    61: aload_3        
            //    62: monitorexit    
            //    63: aload_0        
            //    64: getfield        com/google/android/gms/internal/cf$a.pN:Lcom/google/android/gms/internal/cf$b;
            //    67: aload_1        
            //    68: invokeinterface com/google/android/gms/internal/cf$b.a:(Landroid/os/Bundle;)V
            //    73: return         
            //    74: astore_2       
            //    75: ldc             "Error when get Gservice values"
            //    77: aload_2        
            //    78: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //    81: aload_0        
            //    82: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    85: invokevirtual   com/google/android/gms/internal/cg.isConnected:()Z
            //    88: ifne            101
            //    91: aload_0        
            //    92: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //    95: invokevirtual   com/google/android/gms/internal/cg.isConnecting:()Z
            //    98: ifeq            183
            //   101: aload_0        
            //   102: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   105: invokevirtual   com/google/android/gms/internal/cg.disconnect:()V
            //   108: goto            61
            //   111: astore_2       
            //   112: ldc             "Error when get Gservice values"
            //   114: aload_2        
            //   115: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //   118: aload_0        
            //   119: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   122: invokevirtual   com/google/android/gms/internal/cg.isConnected:()Z
            //   125: ifne            138
            //   128: aload_0        
            //   129: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   132: invokevirtual   com/google/android/gms/internal/cg.isConnecting:()Z
            //   135: ifeq            183
            //   138: aload_0        
            //   139: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   142: invokevirtual   com/google/android/gms/internal/cg.disconnect:()V
            //   145: goto            61
            //   148: astore_1       
            //   149: aload_0        
            //   150: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   153: invokevirtual   com/google/android/gms/internal/cg.isConnected:()Z
            //   156: ifne            169
            //   159: aload_0        
            //   160: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   163: invokevirtual   com/google/android/gms/internal/cg.isConnecting:()Z
            //   166: ifeq            176
            //   169: aload_0        
            //   170: getfield        com/google/android/gms/internal/cf$a.pO:Lcom/google/android/gms/internal/cg;
            //   173: invokevirtual   com/google/android/gms/internal/cg.disconnect:()V
            //   176: aload_1        
            //   177: athrow         
            //   178: astore_1       
            //   179: aload_3        
            //   180: monitorexit    
            //   181: aload_1        
            //   182: athrow         
            //   183: goto            61
            //   186: aload_1        
            //   187: astore_2       
            //   188: goto            30
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  11     19     74     111    Ljava/lang/IllegalStateException;
            //  11     19     111    148    Landroid/os/RemoteException;
            //  11     19     148    178    Any
            //  23     30     74     111    Ljava/lang/IllegalStateException;
            //  23     30     111    148    Landroid/os/RemoteException;
            //  23     30     148    178    Any
            //  30     40     178    183    Any
            //  42     52     178    183    Any
            //  52     59     178    183    Any
            //  61     63     178    183    Any
            //  75     81     148    178    Any
            //  81     101    178    183    Any
            //  101    108    178    183    Any
            //  112    118    148    178    Any
            //  118    138    178    183    Any
            //  138    145    178    183    Any
            //  149    169    178    183    Any
            //  169    176    178    183    Any
            //  176    178    178    183    Any
            //  179    181    178    183    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            this.pN.a(bn.bs());
        }
        
        @Override
        public void onDisconnected() {
            gs.S("Disconnected from remote ad request service.");
        }
    }
    
    public interface b
    {
        void a(final Bundle p0);
    }
}
