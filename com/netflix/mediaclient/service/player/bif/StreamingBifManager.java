// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.TrickplayUrl;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.RandomAccessFile;
import android.content.Context;

public class StreamingBifManager extends BasicBifManager
{
    private static final String BIF_FILE_NAME = "bif.tmp";
    private static final String MDX_BIF_FILE_NAME = "mdxbif.tmp";
    private static final String TAG = "BifManager";
    private final Context mAppContext;
    private Thread mBifLoadingThread;
    private RandomAccessFile mFile;
    private AtomicBoolean mIsBifLoaded;
    private final String mSavedFileName;
    private final ArrayList<String> mUrlList;
    
    public StreamingBifManager(final Context mAppContext, final String s) {
        this.mIsBifLoaded = new AtomicBoolean(false);
        this.mUrlList = new ArrayList<String>(4);
        this.mAppContext = mAppContext;
        this.mSavedFileName = "mdxbif.tmp";
        this.mUrlList.add(s);
        this.loadBif();
    }
    
    public StreamingBifManager(final Context mAppContext, final TrickplayUrl[] array) {
        this.mIsBifLoaded = new AtomicBoolean(false);
        this.mUrlList = new ArrayList<String>(4);
        this.mAppContext = mAppContext;
        this.mSavedFileName = "bif.tmp";
        if (Log.isLoggable()) {
            Log.d("BifManager", "BifManager start, pick urls..." + array);
        }
        while (true) {
            for (int i = 0; i < array.length; ++i) {
                final String[] url = array[i].getUrl();
                if (array[i].getAspect() == 1.0f) {
                    int n = 0;
                    int n2;
                    while (true) {
                        n2 = i;
                        if (n >= url.length) {
                            break;
                        }
                        if (Log.isLoggable()) {
                            Log.d("BifManager", "preferred url" + n + " == " + url[n]);
                        }
                        this.mUrlList.add(url[n]);
                        ++n;
                    }
                    for (int j = 0; j < array.length; ++j) {
                        if (n2 != j) {
                            final String[] url2 = array[j].getUrl();
                            for (int k = 0; k < url2.length; ++k) {
                                this.mUrlList.add(url2[k]);
                            }
                        }
                    }
                    if (Log.isLoggable()) {
                        Log.d("BifManager", array.length + " TrickplayUrls entities with " + this.mUrlList.size() + " urls");
                    }
                    this.loadBif();
                    return;
                }
            }
            int n2 = -1;
            continue;
        }
    }
    
    private void loadBif() {
        (this.mBifLoadingThread = new Thread(new StreamingBifManager$1(this), "OfflineBifManagerThread")).start();
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
        //     1: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mBifLoadingThread:Ljava/lang/Thread;
        //     4: invokevirtual   java/lang/Thread.interrupt:()V
        //     7: aload_0        
        //     8: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mBifLoadingThread:Ljava/lang/Thread;
        //    11: invokevirtual   java/lang/Thread.join:()V
        //    14: aload_0        
        //    15: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mFile:Ljava/io/RandomAccessFile;
        //    18: ifnull          28
        //    21: aload_0        
        //    22: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mFile:Ljava/io/RandomAccessFile;
        //    25: invokevirtual   java/io/RandomAccessFile.close:()V
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mAppContext:Landroid/content/Context;
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/service/player/bif/StreamingBifManager.mSavedFileName:Ljava/lang/String;
        //    36: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    39: pop            
        //    40: ldc             "BifManager"
        //    42: ldc             "released"
        //    44: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    47: pop            
        //    48: return         
        //    49: astore_1       
        //    50: ldc             "BifManager"
        //    52: aload_1        
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: ldc             "release "
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: aload_1        
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: iconst_0       
        //    73: anewarray       Ljava/lang/Object;
        //    76: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //    79: pop            
        //    80: goto            14
        //    83: astore_1       
        //    84: ldc             "BifManager"
        //    86: aload_1        
        //    87: new             Ljava/lang/StringBuilder;
        //    90: dup            
        //    91: invokespecial   java/lang/StringBuilder.<init>:()V
        //    94: ldc             "close file "
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload_1        
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   103: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   106: iconst_0       
        //   107: anewarray       Ljava/lang/Object;
        //   110: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   113: pop            
        //   114: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      14     49     83     Ljava/lang/InterruptedException;
        //  21     28     83     117    Ljava/io/IOException;
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
