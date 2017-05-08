// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.RandomAccessFile;

public class OfflineBifManager extends BasicBifManager
{
    private static final String TAG = "OfflineBifManager";
    private Thread mBifLoadingThread;
    private RandomAccessFile mFile;
    private AtomicBoolean mIsBifLoaded;
    
    public OfflineBifManager(final String s) {
        this.mIsBifLoaded = new AtomicBoolean(false);
        (this.mBifLoadingThread = new Thread(new OfflineBifManager$1(this, s), "OfflineBifManagerThread")).start();
    }
    
    @Override
    protected RandomAccessFile getRandomAccessFile() {
        return this.mFile;
    }
    
    @Override
    protected boolean isBifLoaded() {
        return this.mIsBifLoaded.get();
    }
    
    @Override
    public void release() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/service/player/bif/OfflineBifManager.mBifLoadingThread:Ljava/lang/Thread;
        //     4: invokevirtual   java/lang/Thread.interrupt:()V
        //     7: aload_0        
        //     8: getfield        com/netflix/mediaclient/service/player/bif/OfflineBifManager.mBifLoadingThread:Ljava/lang/Thread;
        //    11: invokevirtual   java/lang/Thread.join:()V
        //    14: aload_0        
        //    15: getfield        com/netflix/mediaclient/service/player/bif/OfflineBifManager.mFile:Ljava/io/RandomAccessFile;
        //    18: ifnull          28
        //    21: aload_0        
        //    22: getfield        com/netflix/mediaclient/service/player/bif/OfflineBifManager.mFile:Ljava/io/RandomAccessFile;
        //    25: invokevirtual   java/io/RandomAccessFile.close:()V
        //    28: ldc             "OfflineBifManager"
        //    30: ldc             "released"
        //    32: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    35: pop            
        //    36: return         
        //    37: astore_1       
        //    38: ldc             "OfflineBifManager"
        //    40: aload_1        
        //    41: new             Ljava/lang/StringBuilder;
        //    44: dup            
        //    45: invokespecial   java/lang/StringBuilder.<init>:()V
        //    48: ldc             "release "
        //    50: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    53: aload_1        
        //    54: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    57: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    60: iconst_0       
        //    61: anewarray       Ljava/lang/Object;
        //    64: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //    67: pop            
        //    68: goto            14
        //    71: astore_1       
        //    72: ldc             "OfflineBifManager"
        //    74: aload_1        
        //    75: new             Ljava/lang/StringBuilder;
        //    78: dup            
        //    79: invokespecial   java/lang/StringBuilder.<init>:()V
        //    82: ldc             "close file "
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: aload_1        
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    94: iconst_0       
        //    95: anewarray       Ljava/lang/Object;
        //    98: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   101: pop            
        //   102: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      14     37     71     Ljava/lang/InterruptedException;
        //  21     28     71     105    Ljava/io/IOException;
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
}
