// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import com.netflix.mediaclient.Log;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaCodec$BufferInfo;
import android.os.HandlerThread;
import android.os.Handler;
import java.util.LinkedList;
import java.nio.ByteBuffer;
import android.media.MediaCodec;
import android.annotation.TargetApi;

@TargetApi(16)
public abstract class MediaDecoderPipe2 extends MediaDecoderBase
{
    private static final long INPUTBUFFER_TO = -1L;
    private static final int MSG_DECODER_FLUSH = 2;
    private static final int MSG_DECODER_GET_FRAME = 1;
    private static final int MSG_DECODER_INITIALIZED = 3;
    private static final int MSG_DECODER_STOP = 4;
    private static final long OUTPUTBUFFER_TO = -1L;
    private static final String TAG = "MediaDecoder2";
    protected static final long TIME_TO_NEXT_RETRY = 20L;
    protected static final boolean USE_ANDROID_L_API;
    private final MediaDecoderBase$InputDataSource mDataSource;
    protected MediaCodec mDecoder;
    private boolean mDecoderPause;
    private boolean mEncrypted;
    private int mInputBufferCnt;
    private ByteBuffer[] mInputBuffers;
    private LinkedList<Integer> mInputBuffersQ;
    private Handler mInputHandler;
    private final MediaDecoderPipe2$LocalStateNotifier mInputState;
    private HandlerThread mInputThread;
    private boolean mIsAudio;
    private int mOutputBufferCnt;
    protected MediaCodec$BufferInfo[] mOutputBufferInfo;
    protected ByteBuffer[] mOutputBuffers;
    protected LinkedList<Integer> mOutputBuffersQ;
    private Handler mOutputHandler;
    private final MediaDecoderPipe2$LocalStateNotifier mOutputState;
    private HandlerThread mOutputThread;
    private String mTag;
    
    static {
        USE_ANDROID_L_API = (AndroidUtils.getAndroidVersion() >= 21);
    }
    
    public MediaDecoderPipe2(final MediaDecoderBase$InputDataSource mDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final MediaCrypto mediaCrypto, final MediaDecoderBase$EventListener eventListener) {
        this.mDecoder = null;
        this.mInputState = new MediaDecoderPipe2$LocalStateNotifier(this);
        this.mOutputState = new MediaDecoderPipe2$LocalStateNotifier(this);
        this.setEventListener(eventListener);
        final StringBuilder sb = new StringBuilder("MediaDecoder2");
        if (s.startsWith("audio/")) {
            this.mIsAudio = true;
            sb.append("Audio");
            this.mTag = sb.toString();
        }
        else if (s.startsWith("video/")) {
            this.mIsAudio = false;
            sb.append("Video");
            this.mTag = sb.toString();
        }
        else if (Log.isLoggable(this.mTag, 6)) {
            Log.e(this.mTag, s + " is not valid");
        }
        Log.d(this.mTag, "creating ... ");
        this.mDataSource = mDataSource;
        this.createDecoder(s, mediaCrypto);
        if (mediaCrypto != null) {
            this.mEncrypted = true;
        }
        else {
            this.mEncrypted = false;
        }
        this.configureDecoder(mediaFormat, surface, mediaCrypto);
        this.startDecoder();
        this.configureOutputBuffers();
        this.mState = -1;
    }
    
    private boolean configureDecoder(final MediaFormat mediaFormat, final Surface surface, final MediaCrypto mediaCrypto) {
        this.mDecoder.configure(mediaFormat, surface, mediaCrypto, 0);
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "configureDecoder " + mediaFormat);
        }
        return true;
    }
    
    private void configureOutputBuffers() {
        try {
            this.mOutputBuffers = this.mDecoder.getOutputBuffers();
            this.mOutputBufferCnt = this.mOutputBuffers.length;
            if (Log.isLoggable(this.mTag, 3)) {
                Log.d(this.mTag, "has " + this.mOutputBufferCnt + " output buffers");
            }
            this.mOutputBufferInfo = new MediaCodec$BufferInfo[this.mOutputBufferCnt];
        }
        catch (Exception ex) {
            Log.e(this.mTag, "get un-known exception while getOutputBuffers()");
            this.mOutputBufferCnt = 0;
        }
    }
    
    private boolean createDecoder(final String s, final MediaCrypto mediaCrypto) {
        if (!this.mIsAudio) {
            final boolean b = mediaCrypto != null && mediaCrypto.requiresSecureDecoderComponent(s);
            if (AndroidUtils.getAndroidVersion() > 18) {
                this.createVideoDecoderForK(s, b);
            }
            else {
                this.createVideoDecoderPreK(s, b);
            }
        }
        if (this.mDecoder == null) {
            this.mDecoder = MediaCodec.createDecoderByType(s);
            if (Log.isLoggable(this.mTag, 3)) {
                Log.d(this.mTag, "createDecoder " + s);
            }
        }
        return true;
    }
    
    private void createInputThread() {
        final StringBuilder append = new StringBuilder().append("Inputthread");
        String s;
        if (this.mIsAudio) {
            s = "Audio";
        }
        else {
            s = "Video";
        }
        (this.mInputThread = new HandlerThread(append.append(s).toString(), -2)).start();
        this.mInputHandler = new MediaDecoderPipe2$1(this, this.mInputThread.getLooper());
    }
    
    private void createOutputThread() {
        final StringBuilder append = new StringBuilder().append("Outputthread");
        String s;
        if (this.mIsAudio) {
            s = "Audio";
        }
        else {
            s = "Video";
        }
        (this.mOutputThread = new HandlerThread(append.append(s).toString(), -2)).start();
        this.mOutputHandler = new MediaDecoderPipe2$2(this, this.mOutputThread.getLooper());
    }
    
    private void createVideoDecoderForK(final String s, final boolean b) {
        final String adaptivePlaybackDecoderName = AdaptiveMediaDecoderHelper.getAdaptivePlaybackDecoderName(s);
        if (adaptivePlaybackDecoderName == null) {
            Log.e(this.mTag, "createVideoDecoderForK " + s + "has no adaptive decoder");
            return;
        }
        String string = adaptivePlaybackDecoderName;
        if (b) {
            string = adaptivePlaybackDecoderName + ".secure";
        }
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "createVideoDecoderForK " + s + ", name " + string);
        }
        try {
            this.mDecoder = MediaCodec.createByCodecName(string);
        }
        catch (Exception ex) {
            Log.d(this.mTag, "createVideoDecoderForK " + s + ", name " + string + " failed");
            this.mDecoder = null;
        }
    }
    
    private void createVideoDecoderPreK(final String p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifeq            168
        //     4: aload_0        
        //     5: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //     8: ldc_w           "try OMX.qcom.video.decoder.avc.smoothstreaming.secure"
        //    11: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    14: pop            
        //    15: aload_0        
        //    16: ldc_w           "OMX.qcom.video.decoder.avc.smoothstreaming.secure"
        //    19: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //    22: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //    25: aload_0        
        //    26: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //    29: ifnonnull       95
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.getSecureDecoderNameForMime:(Ljava/lang/String;)Ljava/lang/String;
        //    37: astore_3       
        //    38: aload_0        
        //    39: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //    42: iconst_3       
        //    43: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    46: ifeq            87
        //    49: aload_0        
        //    50: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: ldc_w           "createSecureDecoder "
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: aload_1        
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: ldc_w           ", name "
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    76: aload_3        
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    86: pop            
        //    87: aload_0        
        //    88: aload_3        
        //    89: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //    92: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //    95: return         
        //    96: astore_3       
        //    97: aload_0        
        //    98: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   101: ldc_w           "createSecureDecoder OMX.qcom.video.decoder.avc.smoothstreaming.secure failed"
        //   104: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   107: pop            
        //   108: aload_0        
        //   109: aconst_null    
        //   110: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //   113: goto            25
        //   116: astore          4
        //   118: aload_0        
        //   119: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   122: new             Ljava/lang/StringBuilder;
        //   125: dup            
        //   126: invokespecial   java/lang/StringBuilder.<init>:()V
        //   129: ldc_w           "createSecureDecoder "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload_1        
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: ldc_w           ", name "
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: aload_3        
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: ldc_w           " failed"
        //   152: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   155: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   158: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   161: pop            
        //   162: aload_0        
        //   163: aconst_null    
        //   164: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //   167: return         
        //   168: aload_0        
        //   169: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   172: ldc_w           "try OMX.qcom.video.decoder.avc.smoothstreaming"
        //   175: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   178: pop            
        //   179: aload_0        
        //   180: ldc_w           "OMX.qcom.video.decoder.avc.smoothstreaming"
        //   183: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //   186: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //   189: return         
        //   190: astore_1       
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   195: ldc_w           "createSecureDecoder OMX.qcom.video.decoder.avc.smoothstreaming failed"
        //   198: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop            
        //   202: aload_0        
        //   203: aconst_null    
        //   204: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //   207: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      25     96     116    Ljava/lang/Exception;
        //  87     95     116    168    Ljava/lang/Exception;
        //  168    189    190    208    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    private String getSecureDecoderNameForMime(final String s) {
        for (int codecCount = MediaCodecList.getCodecCount(), i = 0; i < codecCount; ++i) {
            final MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfo.isEncoder()) {
                final String[] supportedTypes = codecInfo.getSupportedTypes();
                for (int j = 0; j < supportedTypes.length; ++j) {
                    if (supportedTypes[j].equalsIgnoreCase(s)) {
                        return codecInfo.getName() + ".secure";
                    }
                }
            }
        }
        return null;
    }
    
    private boolean startDecoder() {
        this.mDecoder.start();
        this.mInputBuffers = this.mDecoder.getInputBuffers();
        this.mInputBufferCnt = this.mInputBuffers.length;
        if (Log.isLoggable(this.mTag, 3)) {
            Log.d(this.mTag, "has " + this.mInputBufferCnt + " input buffers");
        }
        this.mInputBuffersQ = new LinkedList<Integer>();
        this.mOutputBuffersQ = new LinkedList<Integer>();
        this.createInputThread();
        this.createOutputThread();
        this.mInputHandler.sendEmptyMessageDelayed(3, 20L);
        return true;
    }
    
    abstract void addToRenderer(final int p0, final MediaCodec$BufferInfo p1);
    
    abstract void createRenderer();
    
    @Override
    public void flush() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //     4: invokevirtual   android/media/MediaCodec.flush:()V
        //     7: aload_0        
        //     8: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //    11: ldc_w           "flushinput"
        //    14: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    17: pop            
        //    18: aload_0        
        //    19: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    22: ifnull          58
        //    25: aload_0        
        //    26: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    29: astore_1       
        //    30: aload_1        
        //    31: monitorenter   
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    36: iconst_1       
        //    37: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    40: aload_0        
        //    41: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    44: iconst_2       
        //    45: invokevirtual   android/os/Handler.sendEmptyMessage:(I)Z
        //    48: pop            
        //    49: aload_0        
        //    50: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    53: invokevirtual   java/lang/Object.wait:()V
        //    56: aload_1        
        //    57: monitorexit    
        //    58: aload_0        
        //    59: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //    62: ldc_w           "flushoutput"
        //    65: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    68: pop            
        //    69: aload_0        
        //    70: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    73: ifnull          109
        //    76: aload_0        
        //    77: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    80: astore_1       
        //    81: aload_1        
        //    82: monitorenter   
        //    83: aload_0        
        //    84: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    87: iconst_1       
        //    88: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    91: aload_0        
        //    92: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    95: iconst_2       
        //    96: invokevirtual   android/os/Handler.sendEmptyMessage:(I)Z
        //    99: pop            
        //   100: aload_0        
        //   101: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   104: invokevirtual   java/lang/Object.wait:()V
        //   107: aload_1        
        //   108: monitorexit    
        //   109: aload_0        
        //   110: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.flushRenderer:()V
        //   113: return         
        //   114: astore_1       
        //   115: aload_0        
        //   116: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: ldc_w           "get un-documented exception as a result of flush() "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_1        
        //   133: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   142: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   145: pop            
        //   146: goto            7
        //   149: astore_2       
        //   150: ldc             "MediaDecoder2"
        //   152: ldc_w           "flushinput interrupted"
        //   155: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   158: pop            
        //   159: goto            56
        //   162: astore_2       
        //   163: aload_1        
        //   164: monitorexit    
        //   165: aload_2        
        //   166: athrow         
        //   167: astore_2       
        //   168: ldc             "MediaDecoder2"
        //   170: ldc_w           "flushoutput interrupted"
        //   173: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   176: pop            
        //   177: goto            107
        //   180: astore_2       
        //   181: aload_1        
        //   182: monitorexit    
        //   183: aload_2        
        //   184: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      7      114    149    Ljava/lang/Exception;
        //  32     49     162    167    Any
        //  49     56     149    162    Ljava/lang/InterruptedException;
        //  49     56     162    167    Any
        //  56     58     162    167    Any
        //  83     100    180    185    Any
        //  100    107    167    180    Ljava/lang/InterruptedException;
        //  100    107    180    185    Any
        //  107    109    180    185    Any
        //  150    159    162    167    Any
        //  163    165    162    167    Any
        //  168    177    180    185    Any
        //  181    183    180    185    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 97, Size: 97
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
    
    abstract void flushRenderer();
    
    void hexprint(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(String.format("%02x  ", array[i]));
        }
        Log.d(this.mTag, sb.toString());
    }
    
    void hexprint(final int[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(String.format("%04x  ", array[i]));
        }
        Log.d(this.mTag, sb.toString());
    }
    
    @Override
    public void pause() {
        Log.d(this.mTag, "pause()");
        this.mDecoderPause = true;
        this.pauseRenderer();
    }
    
    abstract void pauseRenderer();
    
    @Override
    public void restart() {
        Log.d(this.mTag, "restart()");
        this.mDecoderPause = false;
        this.mInputHandler.sendEmptyMessage(1);
        this.mOutputHandler.sendEmptyMessage(1);
    }
    
    @Override
    public void start() {
        Log.d(this.mTag, "start()");
        this.mDecoderPause = false;
        this.mInputHandler.sendEmptyMessage(1);
        this.mOutputHandler.sendEmptyMessage(1);
        this.createRenderer();
        this.startRenderer();
    }
    
    abstract void startRenderer();
    
    @Override
    public void stop() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //     4: ldc_w           "stop()"
        //     7: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    10: pop            
        //    11: aload_0        
        //    12: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //    15: invokevirtual   android/media/MediaCodec.stop:()V
        //    18: aload_0        
        //    19: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.stopRenderer:()V
        //    22: aload_0        
        //    23: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    26: ifnull          45
        //    29: aload_0        
        //    30: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    33: iconst_1       
        //    34: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    37: aload_0        
        //    38: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputHandler:Landroid/os/Handler;
        //    41: iconst_2       
        //    42: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    45: aload_0        
        //    46: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputThread:Landroid/os/HandlerThread;
        //    49: ifnull          60
        //    52: aload_0        
        //    53: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputThread:Landroid/os/HandlerThread;
        //    56: invokevirtual   android/os/HandlerThread.quit:()Z
        //    59: pop            
        //    60: aload_0        
        //    61: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //    64: ldc_w           "input thread stopped"
        //    67: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    70: pop            
        //    71: aload_0        
        //    72: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    75: ifnull          119
        //    78: aload_0        
        //    79: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    82: iconst_1       
        //    83: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //    90: iconst_2       
        //    91: invokevirtual   android/os/Handler.removeMessages:(I)V
        //    94: aload_0        
        //    95: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    98: astore_1       
        //    99: aload_1        
        //   100: monitorenter   
        //   101: aload_0        
        //   102: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputHandler:Landroid/os/Handler;
        //   105: iconst_4       
        //   106: invokevirtual   android/os/Handler.sendEmptyMessage:(I)Z
        //   109: pop            
        //   110: aload_0        
        //   111: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputState:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   114: invokevirtual   java/lang/Object.wait:()V
        //   117: aload_1        
        //   118: monitorexit    
        //   119: aload_0        
        //   120: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputThread:Landroid/os/HandlerThread;
        //   123: ifnull          134
        //   126: aload_0        
        //   127: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputThread:Landroid/os/HandlerThread;
        //   130: invokevirtual   android/os/HandlerThread.quit:()Z
        //   133: pop            
        //   134: aload_0        
        //   135: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   138: ldc_w           "output thread stopped"
        //   141: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   144: pop            
        //   145: aload_0        
        //   146: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputThread:Landroid/os/HandlerThread;
        //   149: ifnull          159
        //   152: aload_0        
        //   153: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mInputThread:Landroid/os/HandlerThread;
        //   156: invokevirtual   android/os/HandlerThread.join:()V
        //   159: aload_0        
        //   160: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputThread:Landroid/os/HandlerThread;
        //   163: ifnull          173
        //   166: aload_0        
        //   167: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mOutputThread:Landroid/os/HandlerThread;
        //   170: invokevirtual   android/os/HandlerThread.join:()V
        //   173: aload_0        
        //   174: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   177: ldc_w           "release()"
        //   180: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   183: pop            
        //   184: aload_0        
        //   185: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mDecoder:Landroid/media/MediaCodec;
        //   188: invokevirtual   android/media/MediaCodec.release:()V
        //   191: return         
        //   192: astore_1       
        //   193: aload_0        
        //   194: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc_w           "get un-documented exception as a result of stop() "
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: aload_1        
        //   211: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   220: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   223: pop            
        //   224: goto            18
        //   227: astore_2       
        //   228: ldc             "MediaDecoder2"
        //   230: ldc_w           "stop output interrupted"
        //   233: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   236: pop            
        //   237: goto            117
        //   240: astore_2       
        //   241: aload_1        
        //   242: monitorexit    
        //   243: aload_2        
        //   244: athrow         
        //   245: astore_1       
        //   246: aload_0        
        //   247: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   250: ldc_w           "input/output thread is interrupted"
        //   253: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   256: pop            
        //   257: goto            173
        //   260: astore_1       
        //   261: aload_0        
        //   262: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.mTag:Ljava/lang/String;
        //   265: ldc_w           "get un-documented exception as a result of releas()"
        //   268: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   271: pop            
        //   272: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  11     18     192    227    Ljava/lang/Exception;
        //  101    110    240    245    Any
        //  110    117    227    240    Ljava/lang/InterruptedException;
        //  110    117    240    245    Any
        //  117    119    240    245    Any
        //  145    159    245    260    Ljava/lang/InterruptedException;
        //  159    173    245    260    Ljava/lang/InterruptedException;
        //  184    191    260    273    Ljava/lang/Exception;
        //  228    237    240    245    Any
        //  241    243    240    245    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 132, Size: 132
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
    
    abstract void stopRenderer();
    
    @Override
    public void unpause() {
        Log.d(this.mTag, "unpause()");
        this.mDecoderPause = false;
        this.mInputHandler.sendEmptyMessage(1);
        this.mOutputHandler.sendEmptyMessage(1);
        this.unpauseRenderer();
    }
    
    abstract void unpauseRenderer();
}
