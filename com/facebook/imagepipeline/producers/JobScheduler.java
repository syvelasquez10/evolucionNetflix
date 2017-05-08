// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.EncodedImage;

public class JobScheduler
{
    private final Runnable mDoJobRunnable;
    EncodedImage mEncodedImage;
    private final Executor mExecutor;
    boolean mIsLast;
    private final JobScheduler$JobRunnable mJobRunnable;
    long mJobStartTime;
    JobScheduler$JobState mJobState;
    long mJobSubmitTime;
    private final int mMinimumJobIntervalMs;
    private final Runnable mSubmitJobRunnable;
    
    public JobScheduler(final Executor mExecutor, final JobScheduler$JobRunnable mJobRunnable, final int mMinimumJobIntervalMs) {
        this.mExecutor = mExecutor;
        this.mJobRunnable = mJobRunnable;
        this.mMinimumJobIntervalMs = mMinimumJobIntervalMs;
        this.mDoJobRunnable = new JobScheduler$1(this);
        this.mSubmitJobRunnable = new JobScheduler$2(this);
        this.mEncodedImage = null;
        this.mIsLast = false;
        this.mJobState = JobScheduler$JobState.IDLE;
        this.mJobSubmitTime = 0L;
        this.mJobStartTime = 0L;
    }
    
    private void doJob() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    android/os/SystemClock.uptimeMillis:()J
        //     3: lstore_1       
        //     4: aload_0        
        //     5: monitorenter   
        //     6: aload_0        
        //     7: getfield        com/facebook/imagepipeline/producers/JobScheduler.mEncodedImage:Lcom/facebook/imagepipeline/image/EncodedImage;
        //    10: astore          4
        //    12: aload_0        
        //    13: getfield        com/facebook/imagepipeline/producers/JobScheduler.mIsLast:Z
        //    16: istore_3       
        //    17: aload_0        
        //    18: aconst_null    
        //    19: putfield        com/facebook/imagepipeline/producers/JobScheduler.mEncodedImage:Lcom/facebook/imagepipeline/image/EncodedImage;
        //    22: aload_0        
        //    23: iconst_0       
        //    24: putfield        com/facebook/imagepipeline/producers/JobScheduler.mIsLast:Z
        //    27: aload_0        
        //    28: getstatic       com/facebook/imagepipeline/producers/JobScheduler$JobState.RUNNING:Lcom/facebook/imagepipeline/producers/JobScheduler$JobState;
        //    31: putfield        com/facebook/imagepipeline/producers/JobScheduler.mJobState:Lcom/facebook/imagepipeline/producers/JobScheduler$JobState;
        //    34: aload_0        
        //    35: lload_1        
        //    36: putfield        com/facebook/imagepipeline/producers/JobScheduler.mJobStartTime:J
        //    39: aload_0        
        //    40: monitorexit    
        //    41: aload           4
        //    43: iload_3        
        //    44: invokestatic    com/facebook/imagepipeline/producers/JobScheduler.shouldProcess:(Lcom/facebook/imagepipeline/image/EncodedImage;Z)Z
        //    47: ifeq            62
        //    50: aload_0        
        //    51: getfield        com/facebook/imagepipeline/producers/JobScheduler.mJobRunnable:Lcom/facebook/imagepipeline/producers/JobScheduler$JobRunnable;
        //    54: aload           4
        //    56: iload_3        
        //    57: invokeinterface com/facebook/imagepipeline/producers/JobScheduler$JobRunnable.run:(Lcom/facebook/imagepipeline/image/EncodedImage;Z)V
        //    62: aload           4
        //    64: invokestatic    com/facebook/imagepipeline/image/EncodedImage.closeSafely:(Lcom/facebook/imagepipeline/image/EncodedImage;)V
        //    67: aload_0        
        //    68: invokespecial   com/facebook/imagepipeline/producers/JobScheduler.onJobFinished:()V
        //    71: return         
        //    72: astore          4
        //    74: aload_0        
        //    75: monitorexit    
        //    76: aload           4
        //    78: athrow         
        //    79: astore          5
        //    81: aload           4
        //    83: invokestatic    com/facebook/imagepipeline/image/EncodedImage.closeSafely:(Lcom/facebook/imagepipeline/image/EncodedImage;)V
        //    86: aload_0        
        //    87: invokespecial   com/facebook/imagepipeline/producers/JobScheduler.onJobFinished:()V
        //    90: aload           5
        //    92: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      41     72     79     Any
        //  41     62     79     93     Any
        //  74     76     72     79     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    
    private void enqueueJob(final long n) {
        if (n > 0L) {
            JobScheduler$JobStartExecutorSupplier.get().schedule(this.mSubmitJobRunnable, n, TimeUnit.MILLISECONDS);
            return;
        }
        this.mSubmitJobRunnable.run();
    }
    
    private void onJobFinished() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        long max = 0L;
        boolean b = false;
        synchronized (this) {
            if (this.mJobState == JobScheduler$JobState.RUNNING_AND_PENDING) {
                max = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                b = true;
                this.mJobSubmitTime = uptimeMillis;
                this.mJobState = JobScheduler$JobState.QUEUED;
            }
            else {
                this.mJobState = JobScheduler$JobState.IDLE;
            }
            // monitorexit(this)
            if (b) {
                this.enqueueJob(max - uptimeMillis);
            }
        }
    }
    
    private static boolean shouldProcess(final EncodedImage encodedImage, final boolean b) {
        return b || EncodedImage.isValid(encodedImage);
    }
    
    private void submitJob() {
        this.mExecutor.execute(this.mDoJobRunnable);
    }
    
    public void clearJob() {
        synchronized (this) {
            final EncodedImage mEncodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mIsLast = false;
            // monitorexit(this)
            EncodedImage.closeSafely(mEncodedImage);
        }
    }
    
    public long getQueuedTime() {
        synchronized (this) {
            return this.mJobStartTime - this.mJobSubmitTime;
        }
    }
    
    public boolean scheduleJob() {
        while (true) {
            final boolean b = false;
            final long uptimeMillis = SystemClock.uptimeMillis();
            final long n = 0L;
            while (true) {
                synchronized (this) {
                    if (!shouldProcess(this.mEncodedImage, this.mIsLast)) {
                        return false;
                    }
                    int n2 = b ? 1 : 0;
                    long max = n;
                    switch (JobScheduler$3.$SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[this.mJobState.ordinal()]) {
                        case 3: {
                            this.mJobState = JobScheduler$JobState.RUNNING_AND_PENDING;
                            n2 = (b ? 1 : 0);
                            max = n;
                            break Label_0072;
                        }
                        case 1: {
                            max = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                            this.mJobSubmitTime = uptimeMillis;
                            this.mJobState = JobScheduler$JobState.QUEUED;
                            n2 = 1;
                        }
                        case 2: {
                            // monitorexit(this)
                            if (n2 != 0) {
                                this.enqueueJob(max - uptimeMillis);
                            }
                            return true;
                        }
                    }
                }
                int n2 = b ? 1 : 0;
                long max = n;
                continue;
            }
        }
    }
    
    public boolean updateJob(final EncodedImage encodedImage, final boolean mIsLast) {
        if (!shouldProcess(encodedImage, mIsLast)) {
            return false;
        }
        synchronized (this) {
            final EncodedImage mEncodedImage = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mIsLast = mIsLast;
            // monitorexit(this)
            EncodedImage.closeSafely(mEncodedImage);
            return true;
        }
    }
}
