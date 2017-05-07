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
    protected MediaDecoderPipe$Clock mClock;
    private volatile int mCommand;
    final Condition mCommandDone;
    private MediaDecoderPipe$InputDataSource mDataSource;
    protected MediaCodec mDecoder;
    private boolean mDecoderCreadted;
    protected MediaDecoderPipe$EventListener mEventListener;
    MediaDecoderPipe$DecoderHeartbeat mHearbeat;
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
    protected MediaDecoderPipe$Clock mRefClock;
    private volatile boolean mRunningMainThread;
    private int mSleepMs;
    private volatile int mState;
    private String mTag;
    
    public MediaDecoderPipe(final MediaDecoderPipe$InputDataSource mDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2, final JSONObject mjPlayerConfig) {
        this.mDecoder = null;
        this.mRunningMainThread = false;
        this.mLock = new ReentrantLock();
        this.mCommandDone = this.mLock.newCondition();
        this.mSleepMs = 10;
        this.mJPlayerConfig = null;
        this.mHearbeat = new MediaDecoderPipe$DecoderHeartbeat(this);
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
        else if (Log.isLoggable()) {
            Log.e(this.mTag, s + " is not valid");
            assert false;
        }
        Log.d(this.mTag, "creating ... ");
        this.mDecoderCreadted = false;
        this.mDataSource = mDataSource;
        this.mDecoder = MediaCodec.createDecoderByType(s);
        assert this.mDecoder != null;
        if (Log.isLoggable()) {
            Log.d(this.mTag, "configuring with input format " + mediaFormat);
        }
        this.mDecoder.configure(mediaFormat, surface, (MediaCrypto)null, 0);
        this.mDecoder.start();
        this.mInputBuffers = this.mDecoder.getInputBuffers();
        this.mInputBufferCnt = this.mInputBuffers.length;
        if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
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
    
    public MediaDecoderPipe$Clock getClock() {
        return this.mClock;
    }
    
    protected int getThreadPriority(final String s) {
        while (true) {
            Label_0100: {
                if (this.mJPlayerConfig == null) {
                    break Label_0100;
                }
                while (true) {
                    try {
                        final int int1 = this.mJPlayerConfig.getInt(s);
                        int n;
                        if (int1 < -19 || (n = int1) > 19) {
                            n = 0;
                        }
                        if (Log.isLoggable()) {
                            Log.d(this.mTag, "ThreadPriority " + n);
                        }
                        return n;
                    }
                    catch (JSONException ex) {
                        Log.e("MediaPipe", "Failed to extract JPlayerThreadPriority " + ex);
                        final int int1 = 0;
                        continue;
                    }
                    break;
                }
            }
            int n = 0;
            continue;
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
                if (Log.isLoggable()) {
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
    
    public void setEventListener(final MediaDecoderPipe$EventListener mEventListener) {
        this.mEventListener = mEventListener;
    }
    
    public void setReferenceClock(final MediaDecoderPipe$Clock mRefClock) {
        this.mRefClock = mRefClock;
    }
    
    public void start() {
        this.mClock = new MediaDecoderPipe$Clock(this);
        this.mRunningMainThread = true;
        this.mCommand = 0;
        this.mState = 1;
        (this.mMainThread = new Thread(new MediaDecoderPipe$MainThread(this, null), this.mTag)).start();
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
}
