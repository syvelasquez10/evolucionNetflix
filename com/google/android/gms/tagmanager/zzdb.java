// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.HashSet;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import java.util.Set;
import android.os.HandlerThread;
import android.os.Handler;
import android.content.Context;

class zzdb extends zzak
{
    private static final String ID;
    private static final String NAME;
    private static final String zzaSA;
    private static final String zzaSy;
    private static final String zzaSz;
    private final Context mContext;
    private Handler mHandler;
    private DataLayer zzaOT;
    private boolean zzaSB;
    private boolean zzaSC;
    private final HandlerThread zzaSD;
    private final Set<String> zzaSE;
    
    static {
        ID = zzad.zzcQ.toString();
        NAME = zzae.zzfR.toString();
        zzaSy = zzae.zzfw.toString();
        zzaSz = zzae.zzfE.toString();
        zzaSA = zzae.zzhv.toString();
    }
    
    public zzdb(final Context mContext, final DataLayer zzaOT) {
        super(zzdb.ID, new String[] { zzdb.zzaSy, zzdb.NAME });
        this.zzaSE = new HashSet<String>();
        this.mContext = mContext;
        this.zzaOT = zzaOT;
        (this.zzaSD = new HandlerThread("Google GTM SDK Timer", 10)).start();
        this.mHandler = new Handler(this.zzaSD.getLooper());
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       com/google/android/gms/tagmanager/zzdb.NAME:Ljava/lang/String;
        //     4: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //     9: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    12: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    15: astore          7
        //    17: aload_1        
        //    18: getstatic       com/google/android/gms/tagmanager/zzdb.zzaSA:Ljava/lang/String;
        //    21: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    26: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    29: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    32: astore          6
        //    34: aload_1        
        //    35: getstatic       com/google/android/gms/tagmanager/zzdb.zzaSy:Ljava/lang/String;
        //    38: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    43: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    46: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    49: astore          8
        //    51: aload_1        
        //    52: getstatic       com/google/android/gms/tagmanager/zzdb.zzaSz:Ljava/lang/String;
        //    55: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    60: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    63: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    66: astore_1       
        //    67: aload           8
        //    69: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //    72: lstore_2       
        //    73: aload_1        
        //    74: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //    77: lstore          4
        //    79: lload_2        
        //    80: lconst_0       
        //    81: lcmp           
        //    82: ifle            168
        //    85: aload           7
        //    87: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    90: ifne            168
        //    93: aload           6
        //    95: ifnull          109
        //    98: aload           6
        //   100: astore_1       
        //   101: aload           6
        //   103: invokevirtual   java/lang/String.isEmpty:()Z
        //   106: ifeq            112
        //   109: ldc             "0"
        //   111: astore_1       
        //   112: aload_0        
        //   113: getfield        com/google/android/gms/tagmanager/zzdb.zzaSE:Ljava/util/Set;
        //   116: aload_1        
        //   117: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   122: ifne            168
        //   125: ldc             "0"
        //   127: aload_1        
        //   128: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   131: ifne            145
        //   134: aload_0        
        //   135: getfield        com/google/android/gms/tagmanager/zzdb.zzaSE:Ljava/util/Set;
        //   138: aload_1        
        //   139: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: aload_0        
        //   146: getfield        com/google/android/gms/tagmanager/zzdb.mHandler:Landroid/os/Handler;
        //   149: new             Lcom/google/android/gms/tagmanager/zzdb$zza;
        //   152: dup            
        //   153: aload_0        
        //   154: aload           7
        //   156: aload_1        
        //   157: lload_2        
        //   158: lload           4
        //   160: invokespecial   com/google/android/gms/tagmanager/zzdb$zza.<init>:(Lcom/google/android/gms/tagmanager/zzdb;Ljava/lang/String;Ljava/lang/String;JJ)V
        //   163: lload_2        
        //   164: invokevirtual   android/os/Handler.postDelayed:(Ljava/lang/Runnable;J)Z
        //   167: pop            
        //   168: invokestatic    com/google/android/gms/tagmanager/zzdf.zzBg:()Lcom/google/android/gms/internal/zzag$zza;
        //   171: areturn        
        //   172: astore          8
        //   174: lconst_0       
        //   175: lstore_2       
        //   176: goto            73
        //   179: astore_1       
        //   180: lconst_0       
        //   181: lstore          4
        //   183: goto            79
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Lcom/google/android/gms/internal/zzag$zza;>;)Lcom/google/android/gms/internal/zzag$zza;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  67     73     172    179    Ljava/lang/NumberFormatException;
        //  73     79     179    186    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    public boolean zzzx() {
        return false;
    }
}
