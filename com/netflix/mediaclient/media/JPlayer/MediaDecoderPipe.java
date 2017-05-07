// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCrypto;
import com.netflix.mediaclient.Log;
import java.util.concurrent.locks.ReentrantLock;
import android.view.Surface;
import android.media.MediaFormat;
import android.media.MediaCodec$BufferInfo;
import java.util.concurrent.locks.Lock;
import java.util.LinkedList;
import java.nio.ByteBuffer;
import android.media.MediaCodec;
import java.util.concurrent.locks.Condition;
import android.annotation.TargetApi;

@TargetApi(16)
abstract class MediaDecoderPipe
{
    static final int COMMAND_FLUSH = 3;
    static final int COMMAND_NONE = 0;
    static final int COMMAND_PAUSE = 1;
    static final int COMMAND_STOP = 4;
    static final int COMMAND_UNPAUSE = 2;
    static final int STATE_INIT = -1;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 1;
    static final int STATE_STOPPED = 0;
    private static final String TAG = "MediaPipe";
    protected Clock mClock;
    private volatile int mCommand;
    final Condition mCommandDone;
    private InputDataSource mDataSource;
    protected MediaCodec mDecoder;
    private boolean mDecoderCreadted;
    protected EventListener mEventListener;
    DecoderHeartbeat mHearbeat;
    private int mInputBufferCnt;
    private ByteBuffer[] mInputBuffers;
    private LinkedList<Integer> mInputBuffersQ;
    private boolean mIsAudio;
    final Lock mLock;
    private Thread mMainThread;
    private int mOutputBufferCnt;
    protected MediaCodec$BufferInfo[] mOutputBufferInfo;
    protected ByteBuffer[] mOutputBuffers;
    protected LinkedList<Integer> mOutputBuffersQ;
    protected Clock mRefClock;
    private volatile boolean mRunningMainThread;
    private int mSleepMs;
    private volatile int mState;
    private String mTag;
    
    public MediaDecoderPipe(final InputDataSource mDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2) throws Exception {
        this.mDecoder = null;
        this.mRunningMainThread = false;
        this.mLock = new ReentrantLock();
        this.mCommandDone = this.mLock.newCondition();
        this.mSleepMs = 10;
        this.mHearbeat = new DecoderHeartbeat();
        final StringBuilder sb = new StringBuilder("MediaPipe");
        if (s.startsWith("audio/")) {
            this.mIsAudio = true;
            sb.append("Audio");
            sb.append(s2);
            this.mTag = sb.toString();
        }
        else if (s.startsWith("video/")) {
            this.mIsAudio = false;
            sb.append("Video");
            sb.append(s2);
            this.mTag = sb.toString();
        }
        else if (Log.isLoggable(this.mTag, 6)) {
            Log.e(this.mTag, s + " is not valid");
            assert false;
        }
        Log.d(this.mTag, "creating ... ");
        this.mDecoderCreadted = false;
        this.mDataSource = mDataSource;
        this.mDecoder = MediaCodec.createDecoderByType(s);
        assert this.mDecoder != null;
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "configuring with input format " + mediaFormat);
        }
        this.mDecoder.configure(mediaFormat, surface, (MediaCrypto)null, 0);
        this.mDecoder.start();
        this.mInputBuffers = this.mDecoder.getInputBuffers();
        this.mInputBufferCnt = this.mInputBuffers.length;
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "has " + this.mInputBufferCnt + " input buffers");
        }
        assert this.mInputBufferCnt > 0;
        this.mInputBuffersQ = new LinkedList<Integer>();
        this.mOutputBuffersQ = new LinkedList<Integer>();
        this.configureOutputBuffers();
        this.mState = -1;
        this.mDecoderCreadted = true;
    }
    
    private void configureOutputBuffers() {
        this.mOutputBuffers = this.mDecoder.getOutputBuffers();
        this.mOutputBufferCnt = this.mOutputBuffers.length;
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "has " + this.mOutputBufferCnt + " output buffers");
        }
        assert this.mOutputBufferCnt > 0;
        this.mOutputBufferInfo = new MediaCodec$BufferInfo[this.mOutputBufferCnt];
    }
    
    public void flush() {
        Log.d(this.mTag, "to flush");
        if (!this.mRunningMainThread || (1 != this.mState && 2 != this.mState)) {
            Log.d(this.mTag, "no action");
            return;
        }
        this.mLock.lock();
        if (this.mCommand != 0) {
            Log.d(this.mTag, "no action 2");
            this.mLock.unlock();
            return;
        }
        this.mCommand = 3;
        while (true) {
            try {
                this.mCommandDone.await();
                this.mLock.unlock();
                Log.d(this.mTag, "flush done");
            }
            catch (InterruptedException ex) {
                Log.d("MediaPipe", "command is interrupted");
                this.mLock.unlock();
                continue;
            }
            finally {
                this.mLock.unlock();
            }
            break;
        }
    }
    
    public Clock getClock() {
        return this.mClock;
    }
    
    public boolean isDecoderCreated() {
        return this.mDecoderCreadted;
    }
    
    public boolean isPauseded() {
        return this.mState == 2;
    }
    
    public boolean isStopped() {
        return this.mState == 0;
    }
    
    public void pause() {
        Log.d(this.mTag, "to pause");
        if (1 != this.mState || !this.mRunningMainThread) {
            Log.d(this.mTag, "no action");
            return;
        }
        this.mLock.lock();
        if (this.mCommand != 0) {
            Log.d(this.mTag, "no action 2");
            this.mLock.unlock();
            return;
        }
        this.mCommand = 1;
        try {
            while (true) {
                Label_0138: {
                    try {
                        while (2 != this.mState && this.mRunningMainThread) {
                            this.mCommandDone.await();
                        }
                        break Label_0138;
                    }
                    catch (InterruptedException ex) {
                        Log.d("MediaPipe", "command is interrupted");
                    }
                    Log.d(this.mTag, "pause done");
                    return;
                }
                this.mLock.unlock();
                continue;
            }
        }
        finally {
            this.mLock.unlock();
        }
    }
    
    abstract boolean renderOutput(final boolean p0);
    
    public void setEventListener(final EventListener mEventListener) {
        this.mEventListener = mEventListener;
    }
    
    public void setReferenceClock(final Clock mRefClock) {
        this.mRefClock = mRefClock;
    }
    
    public void start() {
        this.mClock = new Clock();
        this.mRunningMainThread = true;
        this.mCommand = 0;
        this.mState = 1;
        (this.mMainThread = new Thread(new MainThread(), this.mTag)).start();
    }
    
    public void stop() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_0       
        //     2: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRunningMainThread:Z
        //     5: aload_0        
        //     6: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mMainThread:Ljava/lang/Thread;
        //     9: invokevirtual   java/lang/Thread.join:()V
        //    12: aload_0        
        //    13: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //    16: invokevirtual   android/media/MediaCodec.release:()V
        //    19: aload_0        
        //    20: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mTag:Ljava/lang/String;
        //    23: ldc_w           "released"
        //    26: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    29: pop            
        //    30: return         
        //    31: astore_1       
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mTag:Ljava/lang/String;
        //    36: ldc_w           "MainThread is interrupted"
        //    39: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    42: pop            
        //    43: goto            12
        //    46: astore_1       
        //    47: aload_0        
        //    48: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mTag:Ljava/lang/String;
        //    51: ldc_w           "get un-documented exception as a result of releas() "
        //    54: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    57: pop            
        //    58: goto            19
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  5      12     31     46     Ljava/lang/InterruptedException;
        //  12     19     46     61     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0012:
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
    
    public void unpause() {
        Log.d(this.mTag, "to unpause");
        if (1 == this.mState || !this.mRunningMainThread) {
            Log.d(this.mTag, "no action");
            return;
        }
        this.mLock.lock();
        if (this.mCommand != 0) {
            Log.d(this.mTag, "no action 2");
            this.mLock.unlock();
            return;
        }
        this.mCommand = 2;
        try {
            while (true) {
                Label_0138: {
                    try {
                        while (1 != this.mState && this.mRunningMainThread) {
                            this.mCommandDone.await();
                        }
                        break Label_0138;
                    }
                    catch (InterruptedException ex) {
                        Log.d("MediaPipe", "command is interrupted");
                    }
                    Log.d(this.mTag, "unpause done");
                    return;
                }
                this.mLock.unlock();
                continue;
            }
        }
        finally {
            this.mLock.unlock();
        }
    }
    
    public class Clock
    {
        private long mLastPts;
        private long mLastUpdateTime;
        private boolean mRunning;
        
        Clock() {
            this.mLastPts = -1L;
            this.mLastUpdateTime = -1L;
            this.mRunning = false;
        }
        
        Clock(final long mLastPts) {
            this.mLastPts = mLastPts;
            this.mLastUpdateTime = -1L;
            this.mRunning = false;
        }
        
        public void flush() {
            this.mLastPts = -1L;
            this.mLastUpdateTime = -1L;
        }
        
        public long get() {
            if (this.mLastPts < 0L) {
                return -1L;
            }
            if (this.mRunning && this.mLastUpdateTime >= 0L) {
                return this.mLastPts + (System.currentTimeMillis() - this.mLastUpdateTime);
            }
            return this.mLastPts;
        }
        
        public long pause() {
            final long currentTimeMillis = System.currentTimeMillis();
            final long mLastUpdateTime = this.mLastUpdateTime;
            if (this.mLastPts >= 0L) {
                this.mLastPts += currentTimeMillis - mLastUpdateTime;
            }
            this.mRunning = false;
            this.mLastUpdateTime = -1L;
            return this.mLastPts;
        }
        
        public long unpause() {
            this.mRunning = true;
            return this.mLastPts;
        }
        
        public void update(final long mLastPts) {
            this.mLastPts = mLastPts;
            this.mLastUpdateTime = System.currentTimeMillis();
            this.unpause();
        }
    }
    
    private class DecoderHeartbeat
    {
        static final long HRATBEAT_INTERVAL = 5000L;
        private long mLastBeat;
        
        DecoderHeartbeat() {
            this.mLastBeat = System.currentTimeMillis();
        }
        
        void ShowHearbeat(final long n, final long n2) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= this.mLastBeat + 5000L) {
                this.mLastBeat = currentTimeMillis;
                if (Log.isLoggable(MediaDecoderPipe.this.mTag, 3)) {
                    Log.d(MediaDecoderPipe.this.mTag, "decoder alive, received frame " + n + ",decoded frame " + n2);
                }
            }
        }
    }
    
    public interface EventListener
    {
        void onDecoderStarted();
        
        void onStartRender();
        
        void onStop();
    }
    
    public interface InputDataSource
    {
        BufferMeta onRequestData(final ByteBuffer p0);
        
        public static class BufferMeta
        {
            int flags;
            int offset;
            int size;
            long timestamp;
        }
    }
    
    @TargetApi(16)
    private class MainThread implements Runnable
    {
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: bipush          -2
            //     2: invokestatic    android/os/Process.setThreadPriority:(I)V
            //     5: iconst_0       
            //     6: istore_2       
            //     7: iconst_0       
            //     8: istore_1       
            //     9: lconst_0       
            //    10: lstore          8
            //    12: lconst_0       
            //    13: lstore          10
            //    15: aload_0        
            //    16: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    19: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    22: ifeq            402
            //    25: aload_0        
            //    26: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    29: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    32: ifne            49
            //    35: aload_0        
            //    36: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    39: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat;
            //    42: lload           8
            //    44: lload           10
            //    46: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat.ShowHearbeat:(JJ)V
            //    49: lload           10
            //    51: lstore          14
            //    53: lload           8
            //    55: lstore          16
            //    57: iload_1        
            //    58: istore_3       
            //    59: aload_0        
            //    60: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    63: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //    66: ifeq            168
            //    69: aload_0        
            //    70: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    73: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //    76: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //    81: iconst_1       
            //    82: aload_0        
            //    83: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    86: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //    89: if_icmpne       262
            //    92: aload_0        
            //    93: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    96: iconst_2       
            //    97: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   100: pop            
            //   101: aload_0        
            //   102: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   105: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   108: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.pause:()J
            //   111: pop2           
            //   112: aload_0        
            //   113: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   116: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   119: ldc             "paused"
            //   121: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   124: pop            
            //   125: iload_1        
            //   126: istore_3       
            //   127: aload_0        
            //   128: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   131: iconst_0       
            //   132: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   135: pop            
            //   136: aload_0        
            //   137: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   140: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   143: invokeinterface java/util/concurrent/locks/Condition.signal:()V
            //   148: aload_0        
            //   149: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   152: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   155: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   160: lload           8
            //   162: lstore          16
            //   164: lload           10
            //   166: lstore          14
            //   168: iconst_1       
            //   169: aload_0        
            //   170: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   173: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   176: if_icmpeq       599
            //   179: aload_0        
            //   180: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   183: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   186: i2l            
            //   187: invokestatic    java/lang/Thread.sleep:(J)V
            //   190: lload           14
            //   192: lstore          10
            //   194: lload           16
            //   196: lstore          8
            //   198: iload_3        
            //   199: istore_1       
            //   200: goto            15
            //   203: astore          18
            //   205: aload_0        
            //   206: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   209: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   212: ldc             "Thead interrupted"
            //   214: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   217: pop            
            //   218: lload           14
            //   220: lstore          10
            //   222: lload           16
            //   224: lstore          8
            //   226: iload_3        
            //   227: istore_1       
            //   228: goto            15
            //   231: astore          18
            //   233: ldc             "MediaPipe"
            //   235: new             Ljava/lang/StringBuilder;
            //   238: dup            
            //   239: invokespecial   java/lang/StringBuilder.<init>:()V
            //   242: ldc             "fail to setPriority "
            //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   247: aload           18
            //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   255: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   258: pop            
            //   259: goto            5
            //   262: iconst_2       
            //   263: aload_0        
            //   264: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   267: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   270: if_icmpne       300
            //   273: aload_0        
            //   274: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   277: iconst_1       
            //   278: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   281: pop            
            //   282: aload_0        
            //   283: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   286: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   289: ldc             "unpaused"
            //   291: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   294: pop            
            //   295: iload_1        
            //   296: istore_3       
            //   297: goto            127
            //   300: iload_1        
            //   301: istore_3       
            //   302: iconst_3       
            //   303: aload_0        
            //   304: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   307: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   310: if_icmpne       127
            //   313: aload_0        
            //   314: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   317: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   320: invokevirtual   android/media/MediaCodec.flush:()V
            //   323: aload_0        
            //   324: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   327: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   330: invokevirtual   java/util/LinkedList.clear:()V
            //   333: aload_0        
            //   334: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   337: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   340: astore          18
            //   342: aload           18
            //   344: monitorenter   
            //   345: aload_0        
            //   346: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   349: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   352: invokevirtual   java/util/LinkedList.clear:()V
            //   355: aload           18
            //   357: monitorexit    
            //   358: aload_0        
            //   359: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   362: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   365: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.flush:()V
            //   368: lconst_0       
            //   369: lstore          8
            //   371: lconst_0       
            //   372: lstore          10
            //   374: aload_0        
            //   375: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   378: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //   381: ifne            564
            //   384: iconst_1       
            //   385: istore_1       
            //   386: iload_2        
            //   387: ifeq            564
            //   390: aload_0        
            //   391: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   394: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   397: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   402: aload_0        
            //   403: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   406: iconst_0       
            //   407: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;Z)Z
            //   410: pop            
            //   411: aload_0        
            //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   415: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   418: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //   423: aload_0        
            //   424: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   427: iconst_0       
            //   428: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   431: pop            
            //   432: aload_0        
            //   433: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   436: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   439: invokeinterface java/util/concurrent/locks/Condition.signalAll:()V
            //   444: aload_0        
            //   445: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   448: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   451: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   456: ldc2_w          100
            //   459: invokestatic    java/lang/Thread.sleep:(J)V
            //   462: aload_0        
            //   463: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   466: iconst_0       
            //   467: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //   470: pop            
            //   471: aload_0        
            //   472: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   475: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   478: invokevirtual   android/media/MediaCodec.stop:()V
            //   481: aload_0        
            //   482: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   485: iconst_0       
            //   486: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   489: pop            
            //   490: aload_0        
            //   491: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   494: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   497: ldc             "stopped"
            //   499: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   502: pop            
            //   503: aload_0        
            //   504: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   507: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   510: ifnull          525
            //   513: aload_0        
            //   514: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   517: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   520: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStop:()V
            //   525: return         
            //   526: astore          18
            //   528: aload_0        
            //   529: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   532: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   535: ldc             "get un-documented exception as a result of flush() "
            //   537: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   540: pop            
            //   541: aload_0        
            //   542: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   545: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   548: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   553: goto            402
            //   556: astore          19
            //   558: aload           18
            //   560: monitorexit    
            //   561: aload           19
            //   563: athrow         
            //   564: aload_0        
            //   565: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   568: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   571: ldc             "flushed"
            //   573: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   576: pop            
            //   577: iload_1        
            //   578: istore_3       
            //   579: goto            127
            //   582: astore          18
            //   584: aload_0        
            //   585: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   588: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   591: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   596: aload           18
            //   598: athrow         
            //   599: lload           16
            //   601: lstore          12
            //   603: iload_2        
            //   604: istore          6
            //   606: iload_3        
            //   607: istore          4
            //   609: iload_2        
            //   610: ifne            1292
            //   613: aload_0        
            //   614: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   617: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   620: lconst_0       
            //   621: invokevirtual   android/media/MediaCodec.dequeueInputBuffer:(J)I
            //   624: istore_1       
            //   625: iload_1        
            //   626: iflt            687
            //   629: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //   632: ifne            672
            //   635: iload_1        
            //   636: aload_0        
            //   637: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   640: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   643: if_icmplt       672
            //   646: new             Ljava/lang/AssertionError;
            //   649: dup            
            //   650: invokespecial   java/lang/AssertionError.<init>:()V
            //   653: athrow         
            //   654: astore          18
            //   656: aload_0        
            //   657: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   660: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   663: ldc             "get un-documented exception as a result of dequeueInputBuffer() "
            //   665: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   668: pop            
            //   669: goto            402
            //   672: aload_0        
            //   673: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   676: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   679: iload_1        
            //   680: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //   683: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //   686: pop            
            //   687: lload           16
            //   689: lstore          12
            //   691: iload_2        
            //   692: istore          6
            //   694: iload_3        
            //   695: istore          4
            //   697: aload_0        
            //   698: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   701: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   704: invokevirtual   java/util/LinkedList.isEmpty:()Z
            //   707: ifne            1292
            //   710: aload_0        
            //   711: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   714: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   717: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
            //   720: checkcast       Ljava/lang/Integer;
            //   723: invokevirtual   java/lang/Integer.intValue:()I
            //   726: istore          7
            //   728: aload_0        
            //   729: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   732: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)[Ljava/nio/ByteBuffer;
            //   735: iload           7
            //   737: aaload         
            //   738: astore          18
            //   740: aload_0        
            //   741: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   744: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource;
            //   747: aload           18
            //   749: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource.onRequestData:(Ljava/nio/ByteBuffer;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta;
            //   754: astore          18
            //   756: aload           18
            //   758: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   761: ifgt            782
            //   764: lload           16
            //   766: lstore          12
            //   768: iload_2        
            //   769: istore          6
            //   771: iload_3        
            //   772: istore          4
            //   774: aload           18
            //   776: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   779: ifeq            1292
            //   782: iconst_0       
            //   783: istore          4
            //   785: iload           4
            //   787: istore_1       
            //   788: iload_2        
            //   789: istore          5
            //   791: aload           18
            //   793: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   796: lookupswitch {
            //                0: 881
            //                1: 1224
            //                2: 1232
            //              256: 1240
            //          default: 840
            //        }
            //   840: aload_0        
            //   841: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   844: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   847: new             Ljava/lang/StringBuilder;
            //   850: dup            
            //   851: invokespecial   java/lang/StringBuilder.<init>:()V
            //   854: ldc_w           "unknown flag "
            //   857: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   860: aload           18
            //   862: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   865: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   868: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   871: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   874: pop            
            //   875: iload_2        
            //   876: istore          5
            //   878: iload           4
            //   880: istore_1       
            //   881: lload           16
            //   883: lconst_0       
            //   884: lcmp           
            //   885: ifgt            992
            //   888: aload_0        
            //   889: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   892: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   895: iconst_3       
            //   896: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //   899: ifeq            992
            //   902: aload_0        
            //   903: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   906: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   909: new             Ljava/lang/StringBuilder;
            //   912: dup            
            //   913: invokespecial   java/lang/StringBuilder.<init>:()V
            //   916: ldc_w           "QueueInput "
            //   919: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   922: iload           7
            //   924: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   927: ldc_w           " from "
            //   930: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   933: aload           18
            //   935: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //   938: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   941: ldc_w           " size= "
            //   944: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   947: aload           18
            //   949: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   952: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   955: ldc_w           " @"
            //   958: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   961: aload           18
            //   963: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //   966: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //   969: ldc_w           " ms"
            //   972: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   975: ldc_w           " flags "
            //   978: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   981: iload_1        
            //   982: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   985: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   988: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   991: pop            
            //   992: aload_0        
            //   993: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   996: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   999: ifnull          1117
            //  1002: aload           18
            //  1004: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1007: aload_0        
            //  1008: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1011: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1014: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1017: lcmp           
            //  1018: ifge            1117
            //  1021: aload_0        
            //  1022: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1025: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1028: iconst_3       
            //  1029: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1032: ifeq            1117
            //  1035: aload_0        
            //  1036: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1039: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1042: new             Ljava/lang/StringBuilder;
            //  1045: dup            
            //  1046: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1049: ldc_w           "STAT:DEC input late "
            //  1052: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1055: lload           16
            //  1057: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1060: ldc_w           " at "
            //  1063: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1066: aload_0        
            //  1067: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1070: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1073: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1076: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1079: ldc_w           " by "
            //  1082: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1085: aload           18
            //  1087: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1090: aload_0        
            //  1091: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1094: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1097: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1100: lsub           
            //  1101: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1104: ldc_w           " ms"
            //  1107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1113: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1116: pop            
            //  1117: aload_0        
            //  1118: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1121: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1124: iload           7
            //  1126: aload           18
            //  1128: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //  1131: aload           18
            //  1133: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1136: aload           18
            //  1138: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1141: ldc2_w          1000
            //  1144: lmul           
            //  1145: iload_1        
            //  1146: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
            //  1149: aload_0        
            //  1150: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1153: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //  1156: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
            //  1159: pop            
            //  1160: lload           16
            //  1162: lconst_1       
            //  1163: ladd           
            //  1164: lstore          8
            //  1166: lload           8
            //  1168: lstore          12
            //  1170: iload           5
            //  1172: istore          6
            //  1174: iload_3        
            //  1175: istore          4
            //  1177: aload_0        
            //  1178: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1181: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1184: ifne            1292
            //  1187: lload           8
            //  1189: lstore          12
            //  1191: iload           5
            //  1193: istore          6
            //  1195: iload_3        
            //  1196: istore          4
            //  1198: iload_3        
            //  1199: ifeq            1292
            //  1202: iload           5
            //  1204: ifeq            1281
            //  1207: aload_0        
            //  1208: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1211: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1214: ldc_w           "Had EOS after flush"
            //  1217: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1220: pop            
            //  1221: goto            402
            //  1224: iconst_2       
            //  1225: istore_1       
            //  1226: iload_2        
            //  1227: istore          5
            //  1229: goto            881
            //  1232: iconst_1       
            //  1233: istore_1       
            //  1234: iload_2        
            //  1235: istore          5
            //  1237: goto            881
            //  1240: iconst_4       
            //  1241: istore_1       
            //  1242: iconst_1       
            //  1243: istore          5
            //  1245: aload_0        
            //  1246: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1249: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1252: ldc_w           "Had EOS"
            //  1255: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1258: pop            
            //  1259: goto            881
            //  1262: astore          18
            //  1264: aload_0        
            //  1265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1268: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1271: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
            //  1274: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1277: pop            
            //  1278: goto            402
            //  1281: iconst_0       
            //  1282: istore          4
            //  1284: iload           5
            //  1286: istore          6
            //  1288: lload           8
            //  1290: lstore          12
            //  1292: new             Landroid/media/MediaCodec$BufferInfo;
            //  1295: dup            
            //  1296: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
            //  1299: astore          19
            //  1301: aload_0        
            //  1302: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1305: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1308: aload           19
            //  1310: lconst_0       
            //  1311: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
            //  1314: istore_1       
            //  1315: iload_1        
            //  1316: iconst_m1      
            //  1317: if_icmpne       1411
            //  1320: lload           14
            //  1322: lstore          16
            //  1324: aload_0        
            //  1325: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1328: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1331: ifeq            1952
            //  1334: lload           16
            //  1336: lstore          10
            //  1338: lload           12
            //  1340: lstore          8
            //  1342: iload           6
            //  1344: istore_2       
            //  1345: iload           4
            //  1347: istore_1       
            //  1348: lload           16
            //  1350: aload_0        
            //  1351: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1354: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1357: iconst_2       
            //  1358: idiv           
            //  1359: i2l            
            //  1360: lcmp           
            //  1361: iflt            15
            //  1364: lload           16
            //  1366: lstore          10
            //  1368: lload           12
            //  1370: lstore          8
            //  1372: iload           6
            //  1374: istore_2       
            //  1375: iload           4
            //  1377: istore_1       
            //  1378: aload_0        
            //  1379: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1382: iconst_1       
            //  1383: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1386: ifne            15
            //  1389: goto            402
            //  1392: astore          18
            //  1394: aload_0        
            //  1395: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1398: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1401: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
            //  1404: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1407: pop            
            //  1408: goto            402
            //  1411: iload_1        
            //  1412: bipush          -3
            //  1414: if_icmpne       1445
            //  1417: aload_0        
            //  1418: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1421: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1424: ldc_w           "OUTPUT_BUFFERS_CHANGED"
            //  1427: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1430: pop            
            //  1431: aload_0        
            //  1432: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1435: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
            //  1438: lload           14
            //  1440: lstore          16
            //  1442: goto            1324
            //  1445: iload_1        
            //  1446: bipush          -2
            //  1448: if_icmpne       1520
            //  1451: aload_0        
            //  1452: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1455: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1458: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
            //  1461: astore          18
            //  1463: lload           14
            //  1465: lstore          16
            //  1467: aload_0        
            //  1468: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1471: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1474: iconst_3       
            //  1475: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1478: ifeq            1324
            //  1481: aload_0        
            //  1482: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1485: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1488: new             Ljava/lang/StringBuilder;
            //  1491: dup            
            //  1492: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1495: ldc_w           "OUTPUT_FORMAT_CHANGED "
            //  1498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1501: aload           18
            //  1503: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //  1506: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1509: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1512: pop            
            //  1513: lload           14
            //  1515: lstore          16
            //  1517: goto            1324
            //  1520: iload_1        
            //  1521: iflt            1914
            //  1524: aload_0        
            //  1525: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1528: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1531: astore          18
            //  1533: aload           18
            //  1535: monitorenter   
            //  1536: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //  1539: ifne            1569
            //  1542: iload_1        
            //  1543: aload_0        
            //  1544: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1547: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1550: if_icmplt       1569
            //  1553: new             Ljava/lang/AssertionError;
            //  1556: dup            
            //  1557: invokespecial   java/lang/AssertionError.<init>:()V
            //  1560: athrow         
            //  1561: astore          19
            //  1563: aload           18
            //  1565: monitorexit    
            //  1566: aload           19
            //  1568: athrow         
            //  1569: aload_0        
            //  1570: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1573: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1576: iload_1        
            //  1577: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //  1580: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //  1583: pop            
            //  1584: aload_0        
            //  1585: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1588: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
            //  1591: iload_1        
            //  1592: aload           19
            //  1594: aastore        
            //  1595: aload           18
            //  1597: monitorexit    
            //  1598: lload           14
            //  1600: lconst_0       
            //  1601: lcmp           
            //  1602: ifgt            1688
            //  1605: aload_0        
            //  1606: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1609: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1612: iconst_3       
            //  1613: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1616: ifeq            1688
            //  1619: aload_0        
            //  1620: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1623: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1626: new             Ljava/lang/StringBuilder;
            //  1629: dup            
            //  1630: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1633: ldc_w           "DequeueOutputBuffer "
            //  1636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1639: iload_1        
            //  1640: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1643: ldc_w           " size= "
            //  1646: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1649: aload           19
            //  1651: getfield        android/media/MediaCodec$BufferInfo.size:I
            //  1654: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1657: ldc_w           " @"
            //  1660: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1663: aload           19
            //  1665: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1668: ldc2_w          1000
            //  1671: ldiv           
            //  1672: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1675: ldc_w           " ms"
            //  1678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1681: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1684: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1687: pop            
            //  1688: aload_0        
            //  1689: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1692: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1695: ifnull          1821
            //  1698: aload           19
            //  1700: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1703: ldc2_w          1000
            //  1706: ldiv           
            //  1707: aload_0        
            //  1708: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1711: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1714: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1717: lcmp           
            //  1718: ifgt            1821
            //  1721: aload_0        
            //  1722: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1725: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1728: iconst_3       
            //  1729: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1732: ifeq            1821
            //  1735: aload_0        
            //  1736: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1739: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1742: new             Ljava/lang/StringBuilder;
            //  1745: dup            
            //  1746: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1749: ldc_w           "STAT:DEC output late "
            //  1752: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1755: lload           14
            //  1757: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1760: ldc_w           " at "
            //  1763: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1766: aload_0        
            //  1767: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1770: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1773: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1776: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1779: ldc_w           " by "
            //  1782: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1785: aload           19
            //  1787: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1790: ldc2_w          1000
            //  1793: ldiv           
            //  1794: aload_0        
            //  1795: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1798: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1801: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1804: lsub           
            //  1805: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1808: ldc_w           " ms"
            //  1811: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1814: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1817: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1820: pop            
            //  1821: lload           14
            //  1823: lconst_1       
            //  1824: ladd           
            //  1825: lstore          8
            //  1827: aload_0        
            //  1828: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1831: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1834: iconst_3       
            //  1835: isub           
            //  1836: istore_2       
            //  1837: iload_2        
            //  1838: ifgt            1902
            //  1841: iconst_1       
            //  1842: istore_1       
            //  1843: lload           8
            //  1845: lstore          16
            //  1847: lload           8
            //  1849: iload_1        
            //  1850: i2l            
            //  1851: lcmp           
            //  1852: ifne            1324
            //  1855: lload           8
            //  1857: lstore          16
            //  1859: aload_0        
            //  1860: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1863: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1866: ifnull          1324
            //  1869: lload           8
            //  1871: lstore          16
            //  1873: aload_0        
            //  1874: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1877: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1880: ifne            1324
            //  1883: aload_0        
            //  1884: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1887: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1890: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
            //  1895: lload           8
            //  1897: lstore          16
            //  1899: goto            1324
            //  1902: iload_2        
            //  1903: istore_1       
            //  1904: iload_2        
            //  1905: iconst_4       
            //  1906: if_icmplt       1843
            //  1909: iconst_4       
            //  1910: istore_1       
            //  1911: goto            1843
            //  1914: aload_0        
            //  1915: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1918: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1921: new             Ljava/lang/StringBuilder;
            //  1924: dup            
            //  1925: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1928: iload_1        
            //  1929: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1932: ldc_w           " is not valid"
            //  1935: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1938: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1941: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  1944: pop            
            //  1945: lload           14
            //  1947: lstore          16
            //  1949: goto            1324
            //  1952: lload           16
            //  1954: lstore          10
            //  1956: lload           12
            //  1958: lstore          8
            //  1960: iload           6
            //  1962: istore_2       
            //  1963: iload           4
            //  1965: istore_1       
            //  1966: aload_0        
            //  1967: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1970: iconst_1       
            //  1971: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1974: ifne            15
            //  1977: goto            402
            //  1980: astore          18
            //  1982: aload_0        
            //  1983: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1986: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //  1989: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //  1994: aload           18
            //  1996: athrow         
            //  1997: astore          18
            //  1999: aload_0        
            //  2000: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2003: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2006: ldc             "Thead interrupted"
            //  2008: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  2011: pop            
            //  2012: goto            462
            //  2015: astore          18
            //  2017: aload_0        
            //  2018: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2021: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2024: ldc_w           "get un-documented exception as a result of stop/releas() "
            //  2027: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  2030: pop            
            //  2031: goto            481
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      5      231    262    Ljava/lang/SecurityException;
            //  136    148    582    599    Any
            //  179    190    203    231    Ljava/lang/InterruptedException;
            //  313    323    526    556    Ljava/lang/Exception;
            //  345    358    556    564    Any
            //  432    444    1980   1997   Any
            //  456    462    1997   2015   Ljava/lang/InterruptedException;
            //  471    481    2015   2034   Ljava/lang/Exception;
            //  558    561    556    564    Any
            //  613    625    654    672    Ljava/lang/Exception;
            //  1117   1149   1262   1281   Ljava/lang/Exception;
            //  1301   1315   1392   1411   Ljava/lang/Exception;
            //  1536   1561   1561   1569   Any
            //  1563   1566   1561   1569   Any
            //  1569   1598   1561   1569   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 901, Size: 901
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
    }
}
