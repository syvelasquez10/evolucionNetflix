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
            //     0: iconst_0       
            //     1: istore_2       
            //     2: iconst_0       
            //     3: istore_1       
            //     4: lconst_0       
            //     5: lstore          8
            //     7: lconst_0       
            //     8: lstore          10
            //    10: aload_0        
            //    11: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    14: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    17: ifeq            366
            //    20: aload_0        
            //    21: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    24: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    27: ifne            44
            //    30: aload_0        
            //    31: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    34: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat;
            //    37: lload           8
            //    39: lload           10
            //    41: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat.ShowHearbeat:(JJ)V
            //    44: lload           10
            //    46: lstore          14
            //    48: lload           8
            //    50: lstore          16
            //    52: iload_1        
            //    53: istore_3       
            //    54: aload_0        
            //    55: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    58: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //    61: ifeq            163
            //    64: aload_0        
            //    65: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    68: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //    71: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //    76: iconst_1       
            //    77: aload_0        
            //    78: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    81: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //    84: if_icmpne       226
            //    87: aload_0        
            //    88: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    91: iconst_2       
            //    92: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //    95: pop            
            //    96: aload_0        
            //    97: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   100: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   103: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.pause:()J
            //   106: pop2           
            //   107: aload_0        
            //   108: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   111: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   114: ldc             "paused"
            //   116: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   119: pop            
            //   120: iload_1        
            //   121: istore_3       
            //   122: aload_0        
            //   123: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   126: iconst_0       
            //   127: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   130: pop            
            //   131: aload_0        
            //   132: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   135: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   138: invokeinterface java/util/concurrent/locks/Condition.signal:()V
            //   143: aload_0        
            //   144: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   147: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   150: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   155: lload           8
            //   157: lstore          16
            //   159: lload           10
            //   161: lstore          14
            //   163: iconst_1       
            //   164: aload_0        
            //   165: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   168: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   171: if_icmpeq       563
            //   174: aload_0        
            //   175: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   178: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   181: i2l            
            //   182: invokestatic    java/lang/Thread.sleep:(J)V
            //   185: lload           14
            //   187: lstore          10
            //   189: lload           16
            //   191: lstore          8
            //   193: iload_3        
            //   194: istore_1       
            //   195: goto            10
            //   198: astore          18
            //   200: aload_0        
            //   201: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   204: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   207: ldc             "Thead interrupted"
            //   209: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   212: pop            
            //   213: lload           14
            //   215: lstore          10
            //   217: lload           16
            //   219: lstore          8
            //   221: iload_3        
            //   222: istore_1       
            //   223: goto            10
            //   226: iconst_2       
            //   227: aload_0        
            //   228: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   231: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   234: if_icmpne       264
            //   237: aload_0        
            //   238: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   241: iconst_1       
            //   242: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   245: pop            
            //   246: aload_0        
            //   247: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   250: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   253: ldc             "unpaused"
            //   255: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   258: pop            
            //   259: iload_1        
            //   260: istore_3       
            //   261: goto            122
            //   264: iload_1        
            //   265: istore_3       
            //   266: iconst_3       
            //   267: aload_0        
            //   268: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   271: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   274: if_icmpne       122
            //   277: aload_0        
            //   278: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   281: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   284: invokevirtual   android/media/MediaCodec.flush:()V
            //   287: aload_0        
            //   288: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   291: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   294: invokevirtual   java/util/LinkedList.clear:()V
            //   297: aload_0        
            //   298: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   301: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   304: astore          18
            //   306: aload           18
            //   308: monitorenter   
            //   309: aload_0        
            //   310: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   313: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   316: invokevirtual   java/util/LinkedList.clear:()V
            //   319: aload           18
            //   321: monitorexit    
            //   322: aload_0        
            //   323: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   326: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   329: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.flush:()V
            //   332: lconst_0       
            //   333: lstore          8
            //   335: lconst_0       
            //   336: lstore          10
            //   338: aload_0        
            //   339: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   342: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //   345: ifne            528
            //   348: iconst_1       
            //   349: istore_1       
            //   350: iload_2        
            //   351: ifeq            528
            //   354: aload_0        
            //   355: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   358: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   361: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   366: aload_0        
            //   367: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   370: iconst_0       
            //   371: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;Z)Z
            //   374: pop            
            //   375: aload_0        
            //   376: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   379: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   382: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //   387: aload_0        
            //   388: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   391: iconst_0       
            //   392: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   395: pop            
            //   396: aload_0        
            //   397: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   400: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   403: invokeinterface java/util/concurrent/locks/Condition.signalAll:()V
            //   408: aload_0        
            //   409: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   415: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   420: ldc2_w          100
            //   423: invokestatic    java/lang/Thread.sleep:(J)V
            //   426: aload_0        
            //   427: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   430: iconst_0       
            //   431: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //   434: pop            
            //   435: aload_0        
            //   436: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   439: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   442: invokevirtual   android/media/MediaCodec.stop:()V
            //   445: aload_0        
            //   446: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   449: iconst_0       
            //   450: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   453: pop            
            //   454: aload_0        
            //   455: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   458: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   461: ldc             "stopped"
            //   463: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   466: pop            
            //   467: aload_0        
            //   468: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   471: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   474: ifnull          489
            //   477: aload_0        
            //   478: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   481: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   484: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStop:()V
            //   489: return         
            //   490: astore          18
            //   492: aload_0        
            //   493: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   496: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   499: ldc             "get un-documented exception as a result of flush() "
            //   501: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   504: pop            
            //   505: aload_0        
            //   506: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   509: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   512: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   517: goto            366
            //   520: astore          19
            //   522: aload           18
            //   524: monitorexit    
            //   525: aload           19
            //   527: athrow         
            //   528: aload_0        
            //   529: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   532: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   535: ldc             "flushed"
            //   537: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   540: pop            
            //   541: iload_1        
            //   542: istore_3       
            //   543: goto            122
            //   546: astore          18
            //   548: aload_0        
            //   549: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   552: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   555: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   560: aload           18
            //   562: athrow         
            //   563: lload           16
            //   565: lstore          12
            //   567: iload_2        
            //   568: istore          6
            //   570: iload_3        
            //   571: istore          4
            //   573: iload_2        
            //   574: ifne            1255
            //   577: aload_0        
            //   578: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   581: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   584: lconst_0       
            //   585: invokevirtual   android/media/MediaCodec.dequeueInputBuffer:(J)I
            //   588: istore_1       
            //   589: iload_1        
            //   590: iflt            651
            //   593: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //   596: ifne            636
            //   599: iload_1        
            //   600: aload_0        
            //   601: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   604: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   607: if_icmplt       636
            //   610: new             Ljava/lang/AssertionError;
            //   613: dup            
            //   614: invokespecial   java/lang/AssertionError.<init>:()V
            //   617: athrow         
            //   618: astore          18
            //   620: aload_0        
            //   621: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   624: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   627: ldc             "get un-documented exception as a result of dequeueInputBuffer() "
            //   629: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   632: pop            
            //   633: goto            366
            //   636: aload_0        
            //   637: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   640: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   643: iload_1        
            //   644: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //   647: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //   650: pop            
            //   651: lload           16
            //   653: lstore          12
            //   655: iload_2        
            //   656: istore          6
            //   658: iload_3        
            //   659: istore          4
            //   661: aload_0        
            //   662: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   665: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   668: invokevirtual   java/util/LinkedList.isEmpty:()Z
            //   671: ifne            1255
            //   674: aload_0        
            //   675: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   678: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   681: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
            //   684: checkcast       Ljava/lang/Integer;
            //   687: invokevirtual   java/lang/Integer.intValue:()I
            //   690: istore          7
            //   692: aload_0        
            //   693: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   696: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)[Ljava/nio/ByteBuffer;
            //   699: iload           7
            //   701: aaload         
            //   702: astore          18
            //   704: aload_0        
            //   705: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   708: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource;
            //   711: aload           18
            //   713: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource.onRequestData:(Ljava/nio/ByteBuffer;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta;
            //   718: astore          18
            //   720: aload           18
            //   722: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   725: ifgt            746
            //   728: lload           16
            //   730: lstore          12
            //   732: iload_2        
            //   733: istore          6
            //   735: iload_3        
            //   736: istore          4
            //   738: aload           18
            //   740: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   743: ifeq            1255
            //   746: iconst_0       
            //   747: istore          4
            //   749: iload           4
            //   751: istore_1       
            //   752: iload_2        
            //   753: istore          5
            //   755: aload           18
            //   757: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   760: lookupswitch {
            //                0: 844
            //                1: 1187
            //                2: 1195
            //              256: 1203
            //          default: 804
            //        }
            //   804: aload_0        
            //   805: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   808: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   811: new             Ljava/lang/StringBuilder;
            //   814: dup            
            //   815: invokespecial   java/lang/StringBuilder.<init>:()V
            //   818: ldc             "unknown flag "
            //   820: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   823: aload           18
            //   825: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   828: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   831: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   834: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   837: pop            
            //   838: iload_2        
            //   839: istore          5
            //   841: iload           4
            //   843: istore_1       
            //   844: lload           16
            //   846: lconst_0       
            //   847: lcmp           
            //   848: ifgt            955
            //   851: aload_0        
            //   852: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   855: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   858: iconst_3       
            //   859: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //   862: ifeq            955
            //   865: aload_0        
            //   866: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   869: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   872: new             Ljava/lang/StringBuilder;
            //   875: dup            
            //   876: invokespecial   java/lang/StringBuilder.<init>:()V
            //   879: ldc_w           "QueueInput "
            //   882: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   885: iload           7
            //   887: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   890: ldc_w           " from "
            //   893: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   896: aload           18
            //   898: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //   901: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   904: ldc_w           " size= "
            //   907: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   910: aload           18
            //   912: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   915: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   918: ldc_w           " @"
            //   921: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   924: aload           18
            //   926: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //   929: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //   932: ldc_w           " ms"
            //   935: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   938: ldc_w           " flags "
            //   941: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   944: iload_1        
            //   945: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   948: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   951: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   954: pop            
            //   955: aload_0        
            //   956: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   959: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   962: ifnull          1080
            //   965: aload           18
            //   967: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //   970: aload_0        
            //   971: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   974: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   977: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //   980: lcmp           
            //   981: ifge            1080
            //   984: aload_0        
            //   985: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   988: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   991: iconst_3       
            //   992: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //   995: ifeq            1080
            //   998: aload_0        
            //   999: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1002: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1005: new             Ljava/lang/StringBuilder;
            //  1008: dup            
            //  1009: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1012: ldc_w           "STAT:DEC input late "
            //  1015: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1018: lload           16
            //  1020: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1023: ldc_w           " at "
            //  1026: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1029: aload_0        
            //  1030: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1033: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1036: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1039: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1042: ldc_w           " by "
            //  1045: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1048: aload           18
            //  1050: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1053: aload_0        
            //  1054: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1057: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1060: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1063: lsub           
            //  1064: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1067: ldc_w           " ms"
            //  1070: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1073: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1076: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1079: pop            
            //  1080: aload_0        
            //  1081: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1084: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1087: iload           7
            //  1089: aload           18
            //  1091: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //  1094: aload           18
            //  1096: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1099: aload           18
            //  1101: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1104: ldc2_w          1000
            //  1107: lmul           
            //  1108: iload_1        
            //  1109: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
            //  1112: aload_0        
            //  1113: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1116: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //  1119: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
            //  1122: pop            
            //  1123: lload           16
            //  1125: lconst_1       
            //  1126: ladd           
            //  1127: lstore          8
            //  1129: lload           8
            //  1131: lstore          12
            //  1133: iload           5
            //  1135: istore          6
            //  1137: iload_3        
            //  1138: istore          4
            //  1140: aload_0        
            //  1141: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1144: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1147: ifne            1255
            //  1150: lload           8
            //  1152: lstore          12
            //  1154: iload           5
            //  1156: istore          6
            //  1158: iload_3        
            //  1159: istore          4
            //  1161: iload_3        
            //  1162: ifeq            1255
            //  1165: iload           5
            //  1167: ifeq            1244
            //  1170: aload_0        
            //  1171: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1174: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1177: ldc_w           "Had EOS after flush"
            //  1180: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1183: pop            
            //  1184: goto            366
            //  1187: iconst_2       
            //  1188: istore_1       
            //  1189: iload_2        
            //  1190: istore          5
            //  1192: goto            844
            //  1195: iconst_1       
            //  1196: istore_1       
            //  1197: iload_2        
            //  1198: istore          5
            //  1200: goto            844
            //  1203: iconst_4       
            //  1204: istore_1       
            //  1205: iconst_1       
            //  1206: istore          5
            //  1208: aload_0        
            //  1209: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1212: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1215: ldc_w           "Had EOS"
            //  1218: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1221: pop            
            //  1222: goto            844
            //  1225: astore          18
            //  1227: aload_0        
            //  1228: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1231: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1234: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
            //  1237: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1240: pop            
            //  1241: goto            366
            //  1244: iconst_0       
            //  1245: istore          4
            //  1247: iload           5
            //  1249: istore          6
            //  1251: lload           8
            //  1253: lstore          12
            //  1255: new             Landroid/media/MediaCodec$BufferInfo;
            //  1258: dup            
            //  1259: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
            //  1262: astore          19
            //  1264: aload_0        
            //  1265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1268: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1271: aload           19
            //  1273: lconst_0       
            //  1274: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
            //  1277: istore_1       
            //  1278: iload_1        
            //  1279: iconst_m1      
            //  1280: if_icmpne       1374
            //  1283: lload           14
            //  1285: lstore          16
            //  1287: aload_0        
            //  1288: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1291: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1294: ifeq            1915
            //  1297: lload           16
            //  1299: lstore          10
            //  1301: lload           12
            //  1303: lstore          8
            //  1305: iload           6
            //  1307: istore_2       
            //  1308: iload           4
            //  1310: istore_1       
            //  1311: lload           16
            //  1313: aload_0        
            //  1314: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1317: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1320: iconst_2       
            //  1321: idiv           
            //  1322: i2l            
            //  1323: lcmp           
            //  1324: iflt            10
            //  1327: lload           16
            //  1329: lstore          10
            //  1331: lload           12
            //  1333: lstore          8
            //  1335: iload           6
            //  1337: istore_2       
            //  1338: iload           4
            //  1340: istore_1       
            //  1341: aload_0        
            //  1342: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1345: iconst_1       
            //  1346: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1349: ifne            10
            //  1352: goto            366
            //  1355: astore          18
            //  1357: aload_0        
            //  1358: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1361: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1364: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
            //  1367: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1370: pop            
            //  1371: goto            366
            //  1374: iload_1        
            //  1375: bipush          -3
            //  1377: if_icmpne       1408
            //  1380: aload_0        
            //  1381: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1384: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1387: ldc_w           "OUTPUT_BUFFERS_CHANGED"
            //  1390: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1393: pop            
            //  1394: aload_0        
            //  1395: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1398: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
            //  1401: lload           14
            //  1403: lstore          16
            //  1405: goto            1287
            //  1408: iload_1        
            //  1409: bipush          -2
            //  1411: if_icmpne       1483
            //  1414: aload_0        
            //  1415: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1418: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1421: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
            //  1424: astore          18
            //  1426: lload           14
            //  1428: lstore          16
            //  1430: aload_0        
            //  1431: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1434: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1437: iconst_3       
            //  1438: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1441: ifeq            1287
            //  1444: aload_0        
            //  1445: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1448: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1451: new             Ljava/lang/StringBuilder;
            //  1454: dup            
            //  1455: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1458: ldc_w           "OUTPUT_FORMAT_CHANGED "
            //  1461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1464: aload           18
            //  1466: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //  1469: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1472: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1475: pop            
            //  1476: lload           14
            //  1478: lstore          16
            //  1480: goto            1287
            //  1483: iload_1        
            //  1484: iflt            1877
            //  1487: aload_0        
            //  1488: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1491: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1494: astore          18
            //  1496: aload           18
            //  1498: monitorenter   
            //  1499: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //  1502: ifne            1532
            //  1505: iload_1        
            //  1506: aload_0        
            //  1507: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1510: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1513: if_icmplt       1532
            //  1516: new             Ljava/lang/AssertionError;
            //  1519: dup            
            //  1520: invokespecial   java/lang/AssertionError.<init>:()V
            //  1523: athrow         
            //  1524: astore          19
            //  1526: aload           18
            //  1528: monitorexit    
            //  1529: aload           19
            //  1531: athrow         
            //  1532: aload_0        
            //  1533: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1536: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1539: iload_1        
            //  1540: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //  1543: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //  1546: pop            
            //  1547: aload_0        
            //  1548: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1551: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
            //  1554: iload_1        
            //  1555: aload           19
            //  1557: aastore        
            //  1558: aload           18
            //  1560: monitorexit    
            //  1561: lload           14
            //  1563: lconst_0       
            //  1564: lcmp           
            //  1565: ifgt            1651
            //  1568: aload_0        
            //  1569: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1572: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1575: iconst_3       
            //  1576: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1579: ifeq            1651
            //  1582: aload_0        
            //  1583: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1586: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1589: new             Ljava/lang/StringBuilder;
            //  1592: dup            
            //  1593: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1596: ldc_w           "DequeueOutputBuffer "
            //  1599: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1602: iload_1        
            //  1603: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1606: ldc_w           " size= "
            //  1609: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1612: aload           19
            //  1614: getfield        android/media/MediaCodec$BufferInfo.size:I
            //  1617: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1620: ldc_w           " @"
            //  1623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1626: aload           19
            //  1628: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1631: ldc2_w          1000
            //  1634: ldiv           
            //  1635: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1638: ldc_w           " ms"
            //  1641: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1644: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1647: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1650: pop            
            //  1651: aload_0        
            //  1652: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1655: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1658: ifnull          1784
            //  1661: aload           19
            //  1663: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1666: ldc2_w          1000
            //  1669: ldiv           
            //  1670: aload_0        
            //  1671: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1674: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1677: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1680: lcmp           
            //  1681: ifgt            1784
            //  1684: aload_0        
            //  1685: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1688: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1691: iconst_3       
            //  1692: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1695: ifeq            1784
            //  1698: aload_0        
            //  1699: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1702: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1705: new             Ljava/lang/StringBuilder;
            //  1708: dup            
            //  1709: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1712: ldc_w           "STAT:DEC output late "
            //  1715: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1718: lload           14
            //  1720: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1723: ldc_w           " at "
            //  1726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1729: aload_0        
            //  1730: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1733: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1736: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1739: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1742: ldc_w           " by "
            //  1745: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1748: aload           19
            //  1750: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1753: ldc2_w          1000
            //  1756: ldiv           
            //  1757: aload_0        
            //  1758: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1761: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1764: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1767: lsub           
            //  1768: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1771: ldc_w           " ms"
            //  1774: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1777: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1780: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1783: pop            
            //  1784: lload           14
            //  1786: lconst_1       
            //  1787: ladd           
            //  1788: lstore          8
            //  1790: aload_0        
            //  1791: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1794: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1797: iconst_3       
            //  1798: isub           
            //  1799: istore_2       
            //  1800: iload_2        
            //  1801: ifgt            1865
            //  1804: iconst_1       
            //  1805: istore_1       
            //  1806: lload           8
            //  1808: lstore          16
            //  1810: lload           8
            //  1812: iload_1        
            //  1813: i2l            
            //  1814: lcmp           
            //  1815: ifne            1287
            //  1818: lload           8
            //  1820: lstore          16
            //  1822: aload_0        
            //  1823: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1826: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1829: ifnull          1287
            //  1832: lload           8
            //  1834: lstore          16
            //  1836: aload_0        
            //  1837: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1840: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1843: ifne            1287
            //  1846: aload_0        
            //  1847: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1850: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1853: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
            //  1858: lload           8
            //  1860: lstore          16
            //  1862: goto            1287
            //  1865: iload_2        
            //  1866: istore_1       
            //  1867: iload_2        
            //  1868: iconst_4       
            //  1869: if_icmplt       1806
            //  1872: iconst_4       
            //  1873: istore_1       
            //  1874: goto            1806
            //  1877: aload_0        
            //  1878: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1881: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1884: new             Ljava/lang/StringBuilder;
            //  1887: dup            
            //  1888: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1891: iload_1        
            //  1892: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1895: ldc_w           " is not valid"
            //  1898: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1901: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1904: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  1907: pop            
            //  1908: lload           14
            //  1910: lstore          16
            //  1912: goto            1287
            //  1915: lload           16
            //  1917: lstore          10
            //  1919: lload           12
            //  1921: lstore          8
            //  1923: iload           6
            //  1925: istore_2       
            //  1926: iload           4
            //  1928: istore_1       
            //  1929: aload_0        
            //  1930: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1933: iconst_1       
            //  1934: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1937: ifne            10
            //  1940: goto            366
            //  1943: astore          18
            //  1945: aload_0        
            //  1946: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1949: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //  1952: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //  1957: aload           18
            //  1959: athrow         
            //  1960: astore          18
            //  1962: aload_0        
            //  1963: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1966: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1969: ldc             "Thead interrupted"
            //  1971: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  1974: pop            
            //  1975: goto            426
            //  1978: astore          18
            //  1980: aload_0        
            //  1981: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1984: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1987: ldc_w           "get un-documented exception as a result of stop/releas() "
            //  1990: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1993: pop            
            //  1994: goto            445
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  131    143    546    563    Any
            //  174    185    198    226    Ljava/lang/InterruptedException;
            //  277    287    490    520    Ljava/lang/Exception;
            //  309    322    520    528    Any
            //  396    408    1943   1960   Any
            //  420    426    1960   1978   Ljava/lang/InterruptedException;
            //  435    445    1978   1997   Ljava/lang/Exception;
            //  522    525    520    528    Any
            //  577    589    618    636    Ljava/lang/Exception;
            //  1080   1112   1225   1244   Ljava/lang/Exception;
            //  1264   1278   1355   1374   Ljava/lang/Exception;
            //  1499   1524   1524   1532   Any
            //  1526   1529   1524   1532   Any
            //  1532   1561   1524   1532   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 886, Size: 886
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
