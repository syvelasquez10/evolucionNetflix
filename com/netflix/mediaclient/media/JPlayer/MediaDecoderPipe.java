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
            //    60: lstore          8
            //    62: lconst_0       
            //    63: lstore          10
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
            //    92: lload           8
            //    94: lload           10
            //    96: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat.ShowHearbeat:(JJ)V
            //    99: lload           10
            //   101: lstore          14
            //   103: lload           8
            //   105: lstore          16
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
            //   210: lload           8
            //   212: lstore          16
            //   214: lload           10
            //   216: lstore          14
            //   218: iconst_1       
            //   219: aload_0        
            //   220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   223: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   226: if_icmpeq       649
            //   229: aload_0        
            //   230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   233: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   236: i2l            
            //   237: invokestatic    java/lang/Thread.sleep:(J)V
            //   240: lload           14
            //   242: lstore          10
            //   244: lload           16
            //   246: lstore          8
            //   248: iload_3        
            //   249: istore_1       
            //   250: goto            65
            //   253: astore          18
            //   255: aload_0        
            //   256: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   259: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   262: ldc             "Thead interrupted"
            //   264: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   267: pop            
            //   268: lload           14
            //   270: lstore          10
            //   272: lload           16
            //   274: lstore          8
            //   276: iload_3        
            //   277: istore_1       
            //   278: goto            65
            //   281: astore          18
            //   283: ldc             "MediaPipe"
            //   285: new             Ljava/lang/StringBuilder;
            //   288: dup            
            //   289: invokespecial   java/lang/StringBuilder.<init>:()V
            //   292: ldc             "fail to setPriority "
            //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   297: aload           18
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
            //   390: astore          18
            //   392: aload           18
            //   394: monitorenter   
            //   395: aload_0        
            //   396: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   399: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //   402: invokevirtual   java/util/LinkedList.clear:()V
            //   405: aload           18
            //   407: monitorexit    
            //   408: aload_0        
            //   409: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //   415: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.flush:()V
            //   418: lconst_0       
            //   419: lstore          8
            //   421: lconst_0       
            //   422: lstore          10
            //   424: aload_0        
            //   425: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   428: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //   431: ifne            614
            //   434: iconst_1       
            //   435: istore_1       
            //   436: iload_2        
            //   437: ifeq            614
            //   440: aload_0        
            //   441: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   444: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   447: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   452: aload_0        
            //   453: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   456: iconst_0       
            //   457: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;Z)Z
            //   460: pop            
            //   461: aload_0        
            //   462: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   465: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   468: invokeinterface java/util/concurrent/locks/Lock.lock:()V
            //   473: aload_0        
            //   474: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   477: iconst_0       
            //   478: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   481: pop            
            //   482: aload_0        
            //   483: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   486: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
            //   489: invokeinterface java/util/concurrent/locks/Condition.signalAll:()V
            //   494: aload_0        
            //   495: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   498: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   501: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   506: ldc2_w          100
            //   509: invokestatic    java/lang/Thread.sleep:(J)V
            //   512: aload_0        
            //   513: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   516: iconst_0       
            //   517: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //   520: pop            
            //   521: aload_0        
            //   522: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   525: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   528: invokevirtual   android/media/MediaCodec.stop:()V
            //   531: aload_0        
            //   532: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   535: iconst_0       
            //   536: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
            //   539: pop            
            //   540: aload_0        
            //   541: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   544: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   547: ldc             "stopped"
            //   549: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   552: pop            
            //   553: aload_0        
            //   554: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   557: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   560: ifnull          575
            //   563: aload_0        
            //   564: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   567: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //   570: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStop:()V
            //   575: return         
            //   576: astore          18
            //   578: aload_0        
            //   579: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   582: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   585: ldc             "get un-documented exception as a result of flush() "
            //   587: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   590: pop            
            //   591: aload_0        
            //   592: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   595: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   598: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   603: goto            452
            //   606: astore          19
            //   608: aload           18
            //   610: monitorexit    
            //   611: aload           19
            //   613: athrow         
            //   614: aload_0        
            //   615: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   618: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   621: ldc             "flushed"
            //   623: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   626: pop            
            //   627: iload_1        
            //   628: istore_3       
            //   629: goto            177
            //   632: astore          18
            //   634: aload_0        
            //   635: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   638: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //   641: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //   646: aload           18
            //   648: athrow         
            //   649: lload           16
            //   651: lstore          12
            //   653: iload_2        
            //   654: istore          6
            //   656: iload_3        
            //   657: istore          4
            //   659: iload_2        
            //   660: ifne            1340
            //   663: aload_0        
            //   664: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   667: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //   670: lconst_0       
            //   671: invokevirtual   android/media/MediaCodec.dequeueInputBuffer:(J)I
            //   674: istore_1       
            //   675: iload_1        
            //   676: iflt            737
            //   679: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //   682: ifne            722
            //   685: iload_1        
            //   686: aload_0        
            //   687: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   690: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //   693: if_icmplt       722
            //   696: new             Ljava/lang/AssertionError;
            //   699: dup            
            //   700: invokespecial   java/lang/AssertionError.<init>:()V
            //   703: athrow         
            //   704: astore          18
            //   706: aload_0        
            //   707: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   710: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   713: ldc             "get un-documented exception as a result of dequeueInputBuffer() "
            //   715: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   718: pop            
            //   719: goto            452
            //   722: aload_0        
            //   723: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   726: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   729: iload_1        
            //   730: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //   733: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //   736: pop            
            //   737: lload           16
            //   739: lstore          12
            //   741: iload_2        
            //   742: istore          6
            //   744: iload_3        
            //   745: istore          4
            //   747: aload_0        
            //   748: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   751: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   754: invokevirtual   java/util/LinkedList.isEmpty:()Z
            //   757: ifne            1340
            //   760: aload_0        
            //   761: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   764: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //   767: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
            //   770: checkcast       Ljava/lang/Integer;
            //   773: invokevirtual   java/lang/Integer.intValue:()I
            //   776: istore          7
            //   778: aload_0        
            //   779: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   782: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)[Ljava/nio/ByteBuffer;
            //   785: iload           7
            //   787: aaload         
            //   788: astore          18
            //   790: aload_0        
            //   791: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   794: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource;
            //   797: aload           18
            //   799: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource.onRequestData:(Ljava/nio/ByteBuffer;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta;
            //   804: astore          18
            //   806: aload           18
            //   808: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //   811: ifgt            832
            //   814: lload           16
            //   816: lstore          12
            //   818: iload_2        
            //   819: istore          6
            //   821: iload_3        
            //   822: istore          4
            //   824: aload           18
            //   826: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   829: ifeq            1340
            //   832: iconst_0       
            //   833: istore          4
            //   835: iload           4
            //   837: istore_1       
            //   838: iload_2        
            //   839: istore          5
            //   841: aload           18
            //   843: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   846: lookupswitch {
            //                0: 929
            //                1: 1272
            //                2: 1280
            //              256: 1288
            //          default: 888
            //        }
            //   888: aload_0        
            //   889: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   892: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   895: new             Ljava/lang/StringBuilder;
            //   898: dup            
            //   899: invokespecial   java/lang/StringBuilder.<init>:()V
            //   902: ldc_w           "unknown flag "
            //   905: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   908: aload           18
            //   910: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
            //   913: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   916: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   919: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //   922: pop            
            //   923: iload_2        
            //   924: istore          5
            //   926: iload           4
            //   928: istore_1       
            //   929: lload           16
            //   931: lconst_0       
            //   932: lcmp           
            //   933: ifgt            1040
            //   936: aload_0        
            //   937: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   940: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   943: iconst_3       
            //   944: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //   947: ifeq            1040
            //   950: aload_0        
            //   951: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //   954: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //   957: new             Ljava/lang/StringBuilder;
            //   960: dup            
            //   961: invokespecial   java/lang/StringBuilder.<init>:()V
            //   964: ldc_w           "QueueInput "
            //   967: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   970: iload           7
            //   972: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   975: ldc_w           " from "
            //   978: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   981: aload           18
            //   983: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //   986: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   989: ldc_w           " size= "
            //   992: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   995: aload           18
            //   997: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1000: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1003: ldc_w           " @"
            //  1006: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1009: aload           18
            //  1011: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1014: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1017: ldc_w           " ms"
            //  1020: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1023: ldc_w           " flags "
            //  1026: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1029: iload_1        
            //  1030: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1033: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1036: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1039: pop            
            //  1040: aload_0        
            //  1041: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1044: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1047: ifnull          1165
            //  1050: aload           18
            //  1052: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1055: aload_0        
            //  1056: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1059: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1062: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1065: lcmp           
            //  1066: ifge            1165
            //  1069: aload_0        
            //  1070: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1073: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1076: iconst_3       
            //  1077: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1080: ifeq            1165
            //  1083: aload_0        
            //  1084: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1087: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1090: new             Ljava/lang/StringBuilder;
            //  1093: dup            
            //  1094: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1097: ldc_w           "STAT:DEC input late "
            //  1100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1103: lload           16
            //  1105: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1108: ldc_w           " at "
            //  1111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1114: aload_0        
            //  1115: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1118: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1121: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1124: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1127: ldc_w           " by "
            //  1130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1133: aload           18
            //  1135: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1138: aload_0        
            //  1139: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1142: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1145: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1148: lsub           
            //  1149: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1152: ldc_w           " ms"
            //  1155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1158: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1161: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1164: pop            
            //  1165: aload_0        
            //  1166: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1169: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1172: iload           7
            //  1174: aload           18
            //  1176: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
            //  1179: aload           18
            //  1181: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
            //  1184: aload           18
            //  1186: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
            //  1189: ldc2_w          1000
            //  1192: lmul           
            //  1193: iload_1        
            //  1194: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
            //  1197: aload_0        
            //  1198: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1201: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
            //  1204: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
            //  1207: pop            
            //  1208: lload           16
            //  1210: lconst_1       
            //  1211: ladd           
            //  1212: lstore          8
            //  1214: lload           8
            //  1216: lstore          12
            //  1218: iload           5
            //  1220: istore          6
            //  1222: iload_3        
            //  1223: istore          4
            //  1225: aload_0        
            //  1226: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1229: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1232: ifne            1340
            //  1235: lload           8
            //  1237: lstore          12
            //  1239: iload           5
            //  1241: istore          6
            //  1243: iload_3        
            //  1244: istore          4
            //  1246: iload_3        
            //  1247: ifeq            1340
            //  1250: iload           5
            //  1252: ifeq            1329
            //  1255: aload_0        
            //  1256: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1259: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1262: ldc_w           "Had EOS after flush"
            //  1265: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1268: pop            
            //  1269: goto            452
            //  1272: iconst_2       
            //  1273: istore_1       
            //  1274: iload_2        
            //  1275: istore          5
            //  1277: goto            929
            //  1280: iconst_1       
            //  1281: istore_1       
            //  1282: iload_2        
            //  1283: istore          5
            //  1285: goto            929
            //  1288: iconst_4       
            //  1289: istore_1       
            //  1290: iconst_1       
            //  1291: istore          5
            //  1293: aload_0        
            //  1294: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1297: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1300: ldc_w           "Had EOS"
            //  1303: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1306: pop            
            //  1307: goto            929
            //  1310: astore          18
            //  1312: aload_0        
            //  1313: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1316: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1319: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
            //  1322: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1325: pop            
            //  1326: goto            452
            //  1329: iconst_0       
            //  1330: istore          4
            //  1332: iload           5
            //  1334: istore          6
            //  1336: lload           8
            //  1338: lstore          12
            //  1340: new             Landroid/media/MediaCodec$BufferInfo;
            //  1343: dup            
            //  1344: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
            //  1347: astore          19
            //  1349: aload_0        
            //  1350: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1353: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1356: aload           19
            //  1358: lconst_0       
            //  1359: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
            //  1362: istore_1       
            //  1363: iload_1        
            //  1364: iconst_m1      
            //  1365: if_icmpne       1459
            //  1368: lload           14
            //  1370: lstore          16
            //  1372: aload_0        
            //  1373: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1376: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1379: ifeq            2000
            //  1382: lload           16
            //  1384: lstore          10
            //  1386: lload           12
            //  1388: lstore          8
            //  1390: iload           6
            //  1392: istore_2       
            //  1393: iload           4
            //  1395: istore_1       
            //  1396: lload           16
            //  1398: aload_0        
            //  1399: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1402: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1405: iconst_2       
            //  1406: idiv           
            //  1407: i2l            
            //  1408: lcmp           
            //  1409: iflt            65
            //  1412: lload           16
            //  1414: lstore          10
            //  1416: lload           12
            //  1418: lstore          8
            //  1420: iload           6
            //  1422: istore_2       
            //  1423: iload           4
            //  1425: istore_1       
            //  1426: aload_0        
            //  1427: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1430: iconst_1       
            //  1431: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  1434: ifne            65
            //  1437: goto            452
            //  1440: astore          18
            //  1442: aload_0        
            //  1443: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1446: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1449: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
            //  1452: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1455: pop            
            //  1456: goto            452
            //  1459: iload_1        
            //  1460: bipush          -3
            //  1462: if_icmpne       1493
            //  1465: aload_0        
            //  1466: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1469: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1472: ldc_w           "OUTPUT_BUFFERS_CHANGED"
            //  1475: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1478: pop            
            //  1479: aload_0        
            //  1480: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1483: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
            //  1486: lload           14
            //  1488: lstore          16
            //  1490: goto            1372
            //  1493: iload_1        
            //  1494: bipush          -2
            //  1496: if_icmpne       1568
            //  1499: aload_0        
            //  1500: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1503: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
            //  1506: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
            //  1509: astore          18
            //  1511: lload           14
            //  1513: lstore          16
            //  1515: aload_0        
            //  1516: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1519: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1522: iconst_3       
            //  1523: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1526: ifeq            1372
            //  1529: aload_0        
            //  1530: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1533: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1536: new             Ljava/lang/StringBuilder;
            //  1539: dup            
            //  1540: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1543: ldc_w           "OUTPUT_FORMAT_CHANGED "
            //  1546: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1549: aload           18
            //  1551: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //  1554: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1557: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1560: pop            
            //  1561: lload           14
            //  1563: lstore          16
            //  1565: goto            1372
            //  1568: iload_1        
            //  1569: iflt            1962
            //  1572: aload_0        
            //  1573: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1576: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1579: astore          18
            //  1581: aload           18
            //  1583: monitorenter   
            //  1584: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
            //  1587: ifne            1617
            //  1590: iload_1        
            //  1591: aload_0        
            //  1592: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1595: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1598: if_icmplt       1617
            //  1601: new             Ljava/lang/AssertionError;
            //  1604: dup            
            //  1605: invokespecial   java/lang/AssertionError.<init>:()V
            //  1608: athrow         
            //  1609: astore          19
            //  1611: aload           18
            //  1613: monitorexit    
            //  1614: aload           19
            //  1616: athrow         
            //  1617: aload_0        
            //  1618: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1621: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
            //  1624: iload_1        
            //  1625: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //  1628: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
            //  1631: pop            
            //  1632: aload_0        
            //  1633: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1636: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
            //  1639: iload_1        
            //  1640: aload           19
            //  1642: aastore        
            //  1643: aload           18
            //  1645: monitorexit    
            //  1646: lload           14
            //  1648: lconst_0       
            //  1649: lcmp           
            //  1650: ifgt            1736
            //  1653: aload_0        
            //  1654: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1657: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1660: iconst_3       
            //  1661: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1664: ifeq            1736
            //  1667: aload_0        
            //  1668: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1671: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1674: new             Ljava/lang/StringBuilder;
            //  1677: dup            
            //  1678: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1681: ldc_w           "DequeueOutputBuffer "
            //  1684: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1687: iload_1        
            //  1688: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1691: ldc_w           " size= "
            //  1694: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1697: aload           19
            //  1699: getfield        android/media/MediaCodec$BufferInfo.size:I
            //  1702: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1705: ldc_w           " @"
            //  1708: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1711: aload           19
            //  1713: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1716: ldc2_w          1000
            //  1719: ldiv           
            //  1720: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1723: ldc_w           " ms"
            //  1726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1729: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1732: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1735: pop            
            //  1736: aload_0        
            //  1737: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1740: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1743: ifnull          1869
            //  1746: aload           19
            //  1748: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1751: ldc2_w          1000
            //  1754: ldiv           
            //  1755: aload_0        
            //  1756: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1759: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1762: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1765: lcmp           
            //  1766: ifgt            1869
            //  1769: aload_0        
            //  1770: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1773: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1776: iconst_3       
            //  1777: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
            //  1780: ifeq            1869
            //  1783: aload_0        
            //  1784: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1787: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1790: new             Ljava/lang/StringBuilder;
            //  1793: dup            
            //  1794: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1797: ldc_w           "STAT:DEC output late "
            //  1800: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1803: lload           14
            //  1805: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1808: ldc_w           " at "
            //  1811: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1814: aload_0        
            //  1815: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1818: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1821: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1824: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1827: ldc_w           " by "
            //  1830: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1833: aload           19
            //  1835: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
            //  1838: ldc2_w          1000
            //  1841: ldiv           
            //  1842: aload_0        
            //  1843: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1846: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
            //  1849: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
            //  1852: lsub           
            //  1853: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
            //  1856: ldc_w           " ms"
            //  1859: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1862: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1865: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  1868: pop            
            //  1869: lload           14
            //  1871: lconst_1       
            //  1872: ladd           
            //  1873: lstore          8
            //  1875: aload_0        
            //  1876: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1879: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
            //  1882: iconst_3       
            //  1883: isub           
            //  1884: istore_2       
            //  1885: iload_2        
            //  1886: ifgt            1950
            //  1889: iconst_1       
            //  1890: istore_1       
            //  1891: lload           8
            //  1893: lstore          16
            //  1895: lload           8
            //  1897: iload_1        
            //  1898: i2l            
            //  1899: lcmp           
            //  1900: ifne            1372
            //  1903: lload           8
            //  1905: lstore          16
            //  1907: aload_0        
            //  1908: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1911: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1914: ifnull          1372
            //  1917: lload           8
            //  1919: lstore          16
            //  1921: aload_0        
            //  1922: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1925: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
            //  1928: ifne            1372
            //  1931: aload_0        
            //  1932: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1935: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
            //  1938: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
            //  1943: lload           8
            //  1945: lstore          16
            //  1947: goto            1372
            //  1950: iload_2        
            //  1951: istore_1       
            //  1952: iload_2        
            //  1953: iconst_4       
            //  1954: if_icmplt       1891
            //  1957: iconst_4       
            //  1958: istore_1       
            //  1959: goto            1891
            //  1962: aload_0        
            //  1963: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  1966: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  1969: new             Ljava/lang/StringBuilder;
            //  1972: dup            
            //  1973: invokespecial   java/lang/StringBuilder.<init>:()V
            //  1976: iload_1        
            //  1977: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //  1980: ldc_w           " is not valid"
            //  1983: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1986: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1989: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  1992: pop            
            //  1993: lload           14
            //  1995: lstore          16
            //  1997: goto            1372
            //  2000: lload           16
            //  2002: lstore          10
            //  2004: lload           12
            //  2006: lstore          8
            //  2008: iload           6
            //  2010: istore_2       
            //  2011: iload           4
            //  2013: istore_1       
            //  2014: aload_0        
            //  2015: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2018: iconst_1       
            //  2019: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
            //  2022: ifne            65
            //  2025: goto            452
            //  2028: astore          18
            //  2030: aload_0        
            //  2031: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2034: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
            //  2037: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
            //  2042: aload           18
            //  2044: athrow         
            //  2045: astore          18
            //  2047: aload_0        
            //  2048: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2051: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2054: ldc             "Thead interrupted"
            //  2056: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
            //  2059: pop            
            //  2060: goto            512
            //  2063: astore          18
            //  2065: aload_0        
            //  2066: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
            //  2069: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
            //  2072: ldc_w           "get un-documented exception as a result of stop/releas() "
            //  2075: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //  2078: pop            
            //  2079: goto            531
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  10     55     281    312    Ljava/lang/SecurityException;
            //  186    198    632    649    Any
            //  229    240    253    281    Ljava/lang/InterruptedException;
            //  363    373    576    606    Ljava/lang/Exception;
            //  395    408    606    614    Any
            //  482    494    2028   2045   Any
            //  506    512    2045   2063   Ljava/lang/InterruptedException;
            //  521    531    2063   2082   Ljava/lang/Exception;
            //  608    611    606    614    Any
            //  663    675    704    722    Ljava/lang/Exception;
            //  1165   1197   1310   1329   Ljava/lang/Exception;
            //  1349   1363   1440   1459   Ljava/lang/Exception;
            //  1584   1609   1609   1617   Any
            //  1611   1614   1609   1617   Any
            //  1617   1646   1609   1617   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 922, Size: 922
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
