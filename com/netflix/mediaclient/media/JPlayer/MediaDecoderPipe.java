// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import org.json.JSONException;
import android.media.MediaCrypto;
import com.netflix.mediaclient.Log;
import java.util.concurrent.locks.ReentrantLock;
import android.view.Surface;
import android.media.MediaFormat;
import android.media.MediaCodec$BufferInfo;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;
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
    private static final String mediaThreadPriority = "MediaThreadPriority";
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
    private JSONObject mJPlayerConfig;
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
    
    public MediaDecoderPipe(final InputDataSource mDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2, final JSONObject mjPlayerConfig) throws Exception {
        this.mDecoder = null;
        this.mRunningMainThread = false;
        this.mLock = new ReentrantLock();
        this.mCommandDone = this.mLock.newCondition();
        this.mSleepMs = 10;
        this.mJPlayerConfig = null;
        this.mHearbeat = new DecoderHeartbeat();
        final StringBuilder sb = new StringBuilder("MediaPipe");
        this.mJPlayerConfig = mjPlayerConfig;
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
    
    protected int getThreadPriority(final String s) {
        int n = 0;
        int int1 = 0;
        Label_0038: {
            if (this.mJPlayerConfig == null) {
                break Label_0038;
            }
            while (true) {
                try {
                    int1 = this.mJPlayerConfig.getInt(s);
                    if (int1 < -19 || (n = int1) > 19) {
                        n = 0;
                    }
                    if (Log.isLoggable(this.mTag, 3)) {
                        Log.d(this.mTag, "ThreadPriority " + n);
                    }
                    return n;
                }
                catch (JSONException ex) {
                    Log.e("MediaPipe", "Failed to extract JPlayerThreadPriority " + ex);
                    continue;
                }
                break;
            }
        }
    }
    
    public boolean isDecoderCreated() {
        return this.mDecoderCreadted;
    }
    
    protected boolean isJPlayerThreadConfigured() {
        boolean b = false;
        boolean boolean1 = false;
        if (this.mJPlayerConfig == null) {
            return b;
        }
        while (true) {
            try {
                boolean1 = this.mJPlayerConfig.getBoolean("JPlayerThreadConfig");
                b = boolean1;
                if (Log.isLoggable(this.mTag, 3)) {
                    Log.d(this.mTag, "JPlayerThreadConfig " + boolean1);
                    b = boolean1;
                }
                return b;
            }
            catch (JSONException ex) {
                Log.e("MediaPipe", "Failed to extract JPlayerThreadConfig " + ex);
                continue;
            }
            break;
        }
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
            //     0: aload_0        
            //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //     4: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.isJPlayerThreadConfigured:()Z
            //     7: ifeq            55
            //    10: aload_0        
            //    11: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    14: ldc             "MediaThreadPriority"
            //    16: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.getThreadPriority:(Ljava/lang/String;)I
            //    19: invokestatic    android/os/Process.setThreadPriority:(I)V
            //    22: ldc             "MediaPipe"
            //    24: new             Ljava/lang/StringBuilder;
            //    27: dup            
            //    28: invokespecial   java/lang/StringBuilder.<init>:()V
            //    31: ldc             "Updating thread priority: "
            //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    36: aload_0        
            //    37: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    40: ldc             "MediaThreadPriority"
            //    42: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.getThreadPriority:(Ljava/lang/String;)I
            //    45: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //    48: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    51: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //    54: pop            
            //    55: iconst_0       
            //    56: istore_2       
            //    57: iconst_0       
            //    58: istore_1       
            //    59: lconst_0       
            //    60: lstore          7
            //    62: lconst_0       
            //    63: lstore          9
            //    65: aload_0        
            //    66: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    69: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    72: ifeq            452
            //    75: aload_0        
            //    76: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    79: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //    82: ifne            99
            //    85: aload_0        
            //    86: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //    89: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat;
            //    92: lload           7
            //    94: lload           9
            //    96: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat.ShowHearbeat:(JJ)V
            //    99: lload           9
            //   101: lstore          13
            //   103: lload           7
            //   105: lstore          15
            //   107: iload_1        
            //   108: istore_3       
            //   109: aload_0        
            //   110: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   113: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   116: ifeq            218
            //   119: aload_0        
            //   120: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   123: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   126: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //   131: iconst_1       
            //   132: aload_0        
            //   133: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   136: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   139: if_icmpne       312
            //   142: aload_0        
            //   143: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   146: iconst_2       
            //   147: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   150: pop            
            //   151: aload_0        
            //   152: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   155: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   158: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.pause:()J
            //   161: pop2           
            //   162: aload_0        
            //   163: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   166: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   169: ldc             "paused"
            //   171: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   174: pop            
            //   175: iload_1        
            //   176: istore_3       
            //   177: aload_0        
            //   178: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   181: iconst_0       
            //   182: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   185: pop            
            //   186: aload_0        
            //   187: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   190: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   193: invokeinterface java/util/concurrent/locks/Condition.signal:()V
            //   198: aload_0        
            //   199: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   202: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   205: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   210: lload           7
            //   212: lstore          15
            //   214: lload           9
            //   216: lstore          13
            //   218: iconst_1       
            //   219: aload_0        
            //   220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   223: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   226: if_icmpeq       662
            //   229: aload_0        
            //   230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   233: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   236: i2l            
            //   237: invokestatic    java/lang/Thread.sleep:(J)V
            //   240: lload           13
            //   242: lstore          9
            //   244: lload           15
            //   246: lstore          7
            //   248: iload_3        
            //   249: istore_1       
            //   250: goto            65
            //   253: astore          17
            //   255: aload_0        
            //   256: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   259: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   262: ldc             "Thead interrupted"
            //   264: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   267: pop            
            //   268: lload           13
            //   270: lstore          9
            //   272: lload           15
            //   274: lstore          7
            //   276: iload_3        
            //   277: istore_1       
            //   278: goto            65
            //   281: astore          17
            //   283: ldc             "MediaPipe"
            //   285: new             Ljava/lang/StringBuilder;
            //   288: dup            
            //   289: invokespecial   java/lang/StringBuilder.<init>:()V
            //   292: ldc             "fail to setPriority "
            //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   297: aload           17
            //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   302: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   305: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   308: pop            
            //   309: goto            55
            //   312: iconst_2       
            //   313: aload_0        
            //   314: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   317: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   320: if_icmpne       350
            //   323: aload_0        
            //   324: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   327: iconst_1       
            //   328: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   331: pop            
            //   332: aload_0        
            //   333: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   336: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   339: ldc             "unpaused"
            //   341: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   344: pop            
            //   345: iload_1        
            //   346: istore_3       
            //   347: goto            177
            //   350: iload_1        
            //   351: istore_3       
            //   352: iconst_3       
            //   353: aload_0        
            //   354: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   357: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   360: if_icmpne       177
            //   363: aload_0        
            //   364: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   367: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   370: invokevirtual   android/media/MediaCodec.flush:()V
            //   373: aload_0        
            //   374: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   377: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   380: invokevirtual   java/util/LinkedList.clear:()V
            //   383: aload_0        
            //   384: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   387: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   390: astore          17
            //   392: aload           17
            //   394: monitorenter   
            //   395: aload_0        
            //   396: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   399: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   402: invokevirtual   java/util/LinkedList.clear:()V
            //   405: aload           17
            //   407: monitorexit    
            //   408: aload_0        
            //   409: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   415: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.flush:()V
            //   418: lconst_0       
            //   419: lstore          7
            //   421: lconst_0       
            //   422: lstore          9
            //   424: aload_0        
            //   425: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   428: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //   431: ifne            627
            //   434: iconst_1       
            //   435: istore_1       
            //   436: iload_2        
            //   437: ifeq            627
            //   440: aload_0        
            //   441: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   444: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   447: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   452: aload_0        
            //   453: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   456: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   459: ldc             "Stopping MainThread now"
            //   461: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   464: pop            
            //   465: aload_0        
            //   466: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   469: iconst_0       
            //   470: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;Z)Z
            //   473: pop            
            //   474: aload_0        
            //   475: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   478: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   481: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //   486: aload_0        
            //   487: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   490: iconst_0       
            //   491: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   494: pop            
            //   495: aload_0        
            //   496: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   499: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   502: invokeinterface java/util/concurrent/locks/Condition.signalAll:()V
            //   507: aload_0        
            //   508: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   511: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   514: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   519: ldc2_w          100
            //   522: invokestatic    java/lang/Thread.sleep:(J)V
            //   525: aload_0        
            //   526: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   529: iconst_0       
            //   530: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //   533: pop            
            //   534: aload_0        
            //   535: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   538: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   541: invokevirtual   android/media/MediaCodec.stop:()V
            //   544: aload_0        
            //   545: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   548: iconst_0       
            //   549: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   552: pop            
            //   553: aload_0        
            //   554: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   557: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   560: ldc             "stopped"
            //   562: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   565: pop            
            //   566: aload_0        
            //   567: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   570: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   573: ifnull          588
            //   576: aload_0        
            //   577: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   580: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   583: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStop:()V
            //   588: return         
            //   589: astore          17
            //   591: aload_0        
            //   592: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   595: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   598: ldc             "get un-documented exception as a result of flush() "
            //   600: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   603: pop            
            //   604: aload_0        
            //   605: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   608: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   611: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   616: goto            452
            //   619: astore          18
            //   621: aload           17
            //   623: monitorexit    
            //   624: aload           18
            //   626: athrow         
            //   627: aload_0        
            //   628: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   631: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   634: ldc             "flushed"
            //   636: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   639: pop            
            //   640: iload_1        
            //   641: istore_3       
            //   642: goto            177
            //   645: astore          17
            //   647: aload_0        
            //   648: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   651: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   654: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   659: aload           17
            //   661: athrow         
            //   662: lload           15
            //   664: lstore          11
            //   666: iload_2        
            //   667: istore          6
            //   669: iload_3        
            //   670: istore          4
            //   672: iload_2        
            //   673: ifne            1344
            //   676: aload_0        
            //   677: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   680: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   683: lconst_0       
            //   684: invokevirtual   android/media/MediaCodec.dequeueInputBuffer:(J)I
            //   687: istore_1       
            //   688: iload_1        
            //   689: iflt            750
            //   692: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //   695: ifne            735
            //   698: iload_1        
            //   699: aload_0        
            //   700: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   703: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   706: if_icmplt       735
            //   709: new             Ljava/lang/AssertionError;
            //   712: dup            
            //   713: invokespecial   java/lang/AssertionError.<init>:()V
            //   716: athrow         
            //   717: astore          17
            //   719: aload_0        
            //   720: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   723: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   726: ldc             "get un-documented exception as a result of dequeueInputBuffer() "
            //   728: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   731: pop            
            //   732: goto            452
            //   735: aload_0        
            //   736: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   739: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   742: iload_1        
            //   743: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //   746: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //   749: pop            
            //   750: lload           15
            //   752: lstore          11
            //   754: iload_2        
            //   755: istore          6
            //   757: iload_3        
            //   758: istore          4
            //   760: aload_0        
            //   761: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   764: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   767: invokevirtual   java/util/LinkedList.isEmpty:()Z
            //   770: ifne            1344
            //   773: aload_0        
            //   774: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   777: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   780: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
            //   783: checkcast       Ljava/lang/Integer;
            //   786: invokevirtual   java/lang/Integer.intValue:()I
            //   789: istore          6
            //   791: aload_0        
            //   792: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   795: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)[Ljava/nio/ByteBuffer;
            //   798: iload           6
            //   800: aaload         
            //   801: astore          17
            //   803: aload_0        
            //   804: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   807: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource;
            //   810: aload           17
            //   812: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource.onRequestData:(Ljava/nio/ByteBuffer;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta;
            //   817: astore          17
            //   819: aload           17
            //   821: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   824: ifgt            835
            //   827: aload           17
            //   829: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   832: ifeq            1444
            //   835: iconst_0       
            //   836: istore          4
            //   838: iload           4
            //   840: istore_1       
            //   841: iload_2        
            //   842: istore          5
            //   844: aload           17
            //   846: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   849: lookupswitch {
            //                0: 933
            //                1: 1276
            //                2: 1284
            //              256: 1292
            //          default: 892
            //        }
            //   892: aload_0        
            //   893: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   896: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   899: new             Ljava/lang/StringBuilder;
            //   902: dup            
            //   903: invokespecial   java/lang/StringBuilder.<init>:()V
            //   906: ldc_w           "unknown flag "
            //   909: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   912: aload           17
            //   914: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   917: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   920: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   923: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   926: pop            
            //   927: iload_2        
            //   928: istore          5
            //   930: iload           4
            //   932: istore_1       
            //   933: lload           15
            //   935: lconst_0       
            //   936: lcmp           
            //   937: ifgt            1044
            //   940: aload_0        
            //   941: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   944: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   947: iconst_3       
            //   948: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //   951: ifeq            1044
            //   954: aload_0        
            //   955: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   958: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   961: new             Ljava/lang/StringBuilder;
            //   964: dup            
            //   965: invokespecial   java/lang/StringBuilder.<init>:()V
            //   968: ldc_w           "QueueInput "
            //   971: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   974: iload           6
            //   976: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   979: ldc_w           " from "
            //   982: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   985: aload           17
            //   987: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //   990: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   993: ldc_w           " size= "
            //   996: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   999: aload           17
            //  1001: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1004: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1007: ldc_w           " @"
            //  1010: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1013: aload           17
            //  1015: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1018: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1021: ldc_w           " ms"
            //  1024: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1027: ldc_w           " flags "
            //  1030: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1033: iload_1        
            //  1034: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1037: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1040: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1043: pop            
            //  1044: aload_0        
            //  1045: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1048: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1051: ifnull          1169
            //  1054: aload           17
            //  1056: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1059: aload_0        
            //  1060: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1063: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1066: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1069: lcmp           
            //  1070: ifge            1169
            //  1073: aload_0        
            //  1074: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1077: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1080: iconst_3       
            //  1081: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1084: ifeq            1169
            //  1087: aload_0        
            //  1088: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1091: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1094: new             Ljava/lang/StringBuilder;
            //  1097: dup            
            //  1098: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1101: ldc_w           "STAT:DEC input late "
            //  1104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1107: lload           15
            //  1109: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1112: ldc_w           " at "
            //  1115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1118: aload_0        
            //  1119: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1122: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1125: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1128: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1131: ldc_w           " by "
            //  1134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1137: aload           17
            //  1139: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1142: aload_0        
            //  1143: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1146: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1149: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1152: lsub           
            //  1153: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1156: ldc_w           " ms"
            //  1159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1165: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1168: pop            
            //  1169: aload_0        
            //  1170: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1173: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1176: iload           6
            //  1178: aload           17
            //  1180: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //  1183: aload           17
            //  1185: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1188: aload           17
            //  1190: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1193: ldc2_w          1000
            //  1196: lmul           
            //  1197: iload_1        
            //  1198: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
            //  1201: aload_0        
            //  1202: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1205: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //  1208: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
            //  1211: pop            
            //  1212: lload           15
            //  1214: lconst_1       
            //  1215: ladd           
            //  1216: lstore          7
            //  1218: lload           7
            //  1220: lstore          11
            //  1222: iload           5
            //  1224: istore          6
            //  1226: iload_3        
            //  1227: istore          4
            //  1229: aload_0        
            //  1230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1233: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1236: ifne            1344
            //  1239: lload           7
            //  1241: lstore          11
            //  1243: iload           5
            //  1245: istore          6
            //  1247: iload_3        
            //  1248: istore          4
            //  1250: iload_3        
            //  1251: ifeq            1344
            //  1254: iload           5
            //  1256: ifeq            1333
            //  1259: aload_0        
            //  1260: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1263: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1266: ldc_w           "Had EOS after flush"
            //  1269: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1272: pop            
            //  1273: goto            452
            //  1276: iconst_2       
            //  1277: istore_1       
            //  1278: iload_2        
            //  1279: istore          5
            //  1281: goto            933
            //  1284: iconst_1       
            //  1285: istore_1       
            //  1286: iload_2        
            //  1287: istore          5
            //  1289: goto            933
            //  1292: iconst_4       
            //  1293: istore_1       
            //  1294: iconst_1       
            //  1295: istore          5
            //  1297: aload_0        
            //  1298: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1301: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1304: ldc_w           "Had EOS"
            //  1307: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1310: pop            
            //  1311: goto            933
            //  1314: astore          17
            //  1316: aload_0        
            //  1317: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1320: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1323: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
            //  1326: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1329: pop            
            //  1330: goto            452
            //  1333: iconst_0       
            //  1334: istore          4
            //  1336: iload           5
            //  1338: istore          6
            //  1340: lload           7
            //  1342: lstore          11
            //  1344: new             Landroid/media/MediaCodec$BufferInfo;
            //  1347: dup            
            //  1348: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
            //  1351: astore          18
            //  1353: aload_0        
            //  1354: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1357: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1360: aload           18
            //  1362: lconst_0       
            //  1363: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
            //  1366: istore_1       
            //  1367: iload_1        
            //  1368: iconst_m1      
            //  1369: if_icmpne       1498
            //  1372: lload           13
            //  1374: lstore          15
            //  1376: aload_0        
            //  1377: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1380: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1383: ifeq            2039
            //  1386: lload           15
            //  1388: lstore          9
            //  1390: lload           11
            //  1392: lstore          7
            //  1394: iload           6
            //  1396: istore_2       
            //  1397: iload           4
            //  1399: istore_1       
            //  1400: lload           15
            //  1402: aload_0        
            //  1403: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1406: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1409: iconst_2       
            //  1410: idiv           
            //  1411: i2l            
            //  1412: lcmp           
            //  1413: iflt            65
            //  1416: lload           15
            //  1418: lstore          9
            //  1420: lload           11
            //  1422: lstore          7
            //  1424: iload           6
            //  1426: istore_2       
            //  1427: iload           4
            //  1429: istore_1       
            //  1430: aload_0        
            //  1431: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1434: iconst_1       
            //  1435: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1438: ifne            65
            //  1441: goto            452
            //  1444: lload           15
            //  1446: lstore          11
            //  1448: iload_2        
            //  1449: istore          6
            //  1451: iload_3        
            //  1452: istore          4
            //  1454: aload           17
            //  1456: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1459: ifge            1344
            //  1462: aload_0        
            //  1463: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1466: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1469: ldc_w           "Had error endPlayback"
            //  1472: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1475: pop            
            //  1476: goto            452
            //  1479: astore          17
            //  1481: aload_0        
            //  1482: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1485: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1488: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
            //  1491: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1494: pop            
            //  1495: goto            452
            //  1498: iload_1        
            //  1499: bipush          -3
            //  1501: if_icmpne       1532
            //  1504: aload_0        
            //  1505: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1508: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1511: ldc_w           "OUTPUT_BUFFERS_CHANGED"
            //  1514: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1517: pop            
            //  1518: aload_0        
            //  1519: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1522: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
            //  1525: lload           13
            //  1527: lstore          15
            //  1529: goto            1376
            //  1532: iload_1        
            //  1533: bipush          -2
            //  1535: if_icmpne       1607
            //  1538: aload_0        
            //  1539: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1542: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1545: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
            //  1548: astore          17
            //  1550: lload           13
            //  1552: lstore          15
            //  1554: aload_0        
            //  1555: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1558: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1561: iconst_3       
            //  1562: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1565: ifeq            1376
            //  1568: aload_0        
            //  1569: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1572: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1575: new             Ljava/lang/StringBuilder;
            //  1578: dup            
            //  1579: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1582: ldc_w           "OUTPUT_FORMAT_CHANGED "
            //  1585: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1588: aload           17
            //  1590: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //  1593: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1596: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1599: pop            
            //  1600: lload           13
            //  1602: lstore          15
            //  1604: goto            1376
            //  1607: iload_1        
            //  1608: iflt            2001
            //  1611: aload_0        
            //  1612: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1615: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1618: astore          17
            //  1620: aload           17
            //  1622: monitorenter   
            //  1623: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //  1626: ifne            1656
            //  1629: iload_1        
            //  1630: aload_0        
            //  1631: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1634: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1637: if_icmplt       1656
            //  1640: new             Ljava/lang/AssertionError;
            //  1643: dup            
            //  1644: invokespecial   java/lang/AssertionError.<init>:()V
            //  1647: athrow         
            //  1648: astore          18
            //  1650: aload           17
            //  1652: monitorexit    
            //  1653: aload           18
            //  1655: athrow         
            //  1656: aload_0        
            //  1657: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1660: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1663: iload_1        
            //  1664: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //  1667: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //  1670: pop            
            //  1671: aload_0        
            //  1672: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1675: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
            //  1678: iload_1        
            //  1679: aload           18
            //  1681: aastore        
            //  1682: aload           17
            //  1684: monitorexit    
            //  1685: lload           13
            //  1687: lconst_0       
            //  1688: lcmp           
            //  1689: ifgt            1775
            //  1692: aload_0        
            //  1693: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1696: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1699: iconst_3       
            //  1700: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1703: ifeq            1775
            //  1706: aload_0        
            //  1707: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1710: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1713: new             Ljava/lang/StringBuilder;
            //  1716: dup            
            //  1717: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1720: ldc_w           "DequeueOutputBuffer "
            //  1723: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1726: iload_1        
            //  1727: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1730: ldc_w           " size= "
            //  1733: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1736: aload           18
            //  1738: getfield        android/media/MediaCodec$BufferInfo.size:I
            //  1741: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1744: ldc_w           " @"
            //  1747: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1750: aload           18
            //  1752: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1755: ldc2_w          1000
            //  1758: ldiv           
            //  1759: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1762: ldc_w           " ms"
            //  1765: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1768: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1771: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1774: pop            
            //  1775: aload_0        
            //  1776: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1779: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1782: ifnull          1908
            //  1785: aload           18
            //  1787: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1790: ldc2_w          1000
            //  1793: ldiv           
            //  1794: aload_0        
            //  1795: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1798: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1801: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1804: lcmp           
            //  1805: ifgt            1908
            //  1808: aload_0        
            //  1809: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1812: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1815: iconst_3       
            //  1816: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1819: ifeq            1908
            //  1822: aload_0        
            //  1823: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1826: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1829: new             Ljava/lang/StringBuilder;
            //  1832: dup            
            //  1833: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1836: ldc_w           "STAT:DEC output late "
            //  1839: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1842: lload           13
            //  1844: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1847: ldc_w           " at "
            //  1850: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1853: aload_0        
            //  1854: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1857: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1860: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1863: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1866: ldc_w           " by "
            //  1869: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1872: aload           18
            //  1874: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1877: ldc2_w          1000
            //  1880: ldiv           
            //  1881: aload_0        
            //  1882: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1885: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1888: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1891: lsub           
            //  1892: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1895: ldc_w           " ms"
            //  1898: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1901: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1904: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1907: pop            
            //  1908: lload           13
            //  1910: lconst_1       
            //  1911: ladd           
            //  1912: lstore          7
            //  1914: aload_0        
            //  1915: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1918: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1921: iconst_3       
            //  1922: isub           
            //  1923: istore_2       
            //  1924: iload_2        
            //  1925: ifgt            1989
            //  1928: iconst_1       
            //  1929: istore_1       
            //  1930: lload           7
            //  1932: lstore          15
            //  1934: lload           7
            //  1936: iload_1        
            //  1937: i2l            
            //  1938: lcmp           
            //  1939: ifne            1376
            //  1942: lload           7
            //  1944: lstore          15
            //  1946: aload_0        
            //  1947: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1950: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1953: ifnull          1376
            //  1956: lload           7
            //  1958: lstore          15
            //  1960: aload_0        
            //  1961: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1964: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1967: ifne            1376
            //  1970: aload_0        
            //  1971: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1974: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1977: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
            //  1982: lload           7
            //  1984: lstore          15
            //  1986: goto            1376
            //  1989: iload_2        
            //  1990: istore_1       
            //  1991: iload_2        
            //  1992: iconst_4       
            //  1993: if_icmplt       1930
            //  1996: iconst_4       
            //  1997: istore_1       
            //  1998: goto            1930
            //  2001: aload_0        
            //  2002: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2005: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2008: new             Ljava/lang/StringBuilder;
            //  2011: dup            
            //  2012: invokespecial   java/lang/StringBuilder.<init>:()V
            //  2015: iload_1        
            //  2016: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  2019: ldc_w           " is not valid"
            //  2022: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  2025: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  2028: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  2031: pop            
            //  2032: lload           13
            //  2034: lstore          15
            //  2036: goto            1376
            //  2039: lload           15
            //  2041: lstore          9
            //  2043: lload           11
            //  2045: lstore          7
            //  2047: iload           6
            //  2049: istore_2       
            //  2050: iload           4
            //  2052: istore_1       
            //  2053: aload_0        
            //  2054: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2057: iconst_1       
            //  2058: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  2061: ifne            65
            //  2064: goto            452
            //  2067: astore          17
            //  2069: aload_0        
            //  2070: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2073: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //  2076: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //  2081: aload           17
            //  2083: athrow         
            //  2084: astore          17
            //  2086: aload_0        
            //  2087: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2090: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2093: ldc             "Thead interrupted"
            //  2095: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  2098: pop            
            //  2099: goto            525
            //  2102: astore          17
            //  2104: aload_0        
            //  2105: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2108: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2111: ldc_w           "get un-documented exception as a result of stop/releas() "
            //  2114: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  2117: pop            
            //  2118: goto            544
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  10     55     281    312    Ljava/lang/SecurityException;
            //  186    198    645    662    Any
            //  229    240    253    281    Ljava/lang/InterruptedException;
            //  363    373    589    619    Ljava/lang/Exception;
            //  395    408    619    627    Any
            //  495    507    2067   2084   Any
            //  519    525    2084   2102   Ljava/lang/InterruptedException;
            //  534    544    2102   2121   Ljava/lang/Exception;
            //  621    624    619    627    Any
            //  676    688    717    735    Ljava/lang/Exception;
            //  1169   1201   1314   1333   Ljava/lang/Exception;
            //  1353   1367   1479   1498   Ljava/lang/Exception;
            //  1623   1648   1648   1656   Any
            //  1650   1653   1648   1656   Any
            //  1656   1685   1648   1656   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 938, Size: 938
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
