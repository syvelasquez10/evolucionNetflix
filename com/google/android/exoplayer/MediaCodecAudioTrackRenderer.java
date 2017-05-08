// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.util.MimeTypes;
import android.media.PlaybackParams;
import android.view.Surface;
import android.media.MediaCrypto;
import android.media.MediaCodec;
import com.google.android.exoplayer.audio.AudioTrack$WriteException;
import com.google.android.exoplayer.audio.AudioTrack$InitializationException;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.audio.AudioCapabilities;
import android.os.Handler;
import com.google.android.exoplayer.drm.DrmSessionManager;
import android.media.MediaFormat;
import com.google.android.exoplayer.audio.AudioTrack;
import android.annotation.TargetApi;

@TargetApi(16)
public class MediaCodecAudioTrackRenderer extends MediaCodecTrackRenderer implements MediaClock
{
    private boolean allowPositionDiscontinuity;
    private int audioSessionId;
    private final AudioTrack audioTrack;
    private boolean audioTrackHasData;
    private long currentPositionUs;
    private final MediaCodecAudioTrackRenderer$EventListener eventListener;
    private long lastFeedElapsedRealtimeMs;
    private boolean passthroughEnabled;
    private MediaFormat passthroughMediaFormat;
    private int pcmEncoding;
    
    public MediaCodecAudioTrackRenderer(final SampleSource sampleSource, final MediaCodecSelector mediaCodecSelector, final DrmSessionManager drmSessionManager, final boolean b, final Handler handler, final MediaCodecAudioTrackRenderer$EventListener mediaCodecAudioTrackRenderer$EventListener, final AudioCapabilities audioCapabilities, final int n) {
        this(new SampleSource[] { sampleSource }, mediaCodecSelector, drmSessionManager, b, handler, mediaCodecAudioTrackRenderer$EventListener, audioCapabilities, n);
    }
    
    public MediaCodecAudioTrackRenderer(final SampleSource[] array, final MediaCodecSelector mediaCodecSelector, final DrmSessionManager drmSessionManager, final boolean b, final Handler handler, final MediaCodecAudioTrackRenderer$EventListener eventListener, final AudioCapabilities audioCapabilities, final int n) {
        super(array, mediaCodecSelector, drmSessionManager, b, handler, eventListener);
        this.eventListener = eventListener;
        this.audioSessionId = 0;
        this.audioTrack = new AudioTrack(audioCapabilities, n);
    }
    
    private void notifyAudioTrackInitializationError(final AudioTrack$InitializationException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecAudioTrackRenderer$1(this, ex));
        }
    }
    
    private void notifyAudioTrackUnderrun(final int n, final long n2, final long n3) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecAudioTrackRenderer$3(this, n, n2, n3));
        }
    }
    
    private void notifyAudioTrackWriteError(final AudioTrack$WriteException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecAudioTrackRenderer$2(this, ex));
        }
    }
    
    protected boolean allowPassthrough(final String s) {
        return this.audioTrack.isPassthroughSupported(s);
    }
    
    @Override
    protected void configureCodec(final MediaCodec mediaCodec, final boolean b, final MediaFormat passthroughMediaFormat, final MediaCrypto mediaCrypto) {
        final String string = passthroughMediaFormat.getString("mime");
        if (this.passthroughEnabled) {
            passthroughMediaFormat.setString("mime", "audio/raw");
            mediaCodec.configure(passthroughMediaFormat, (Surface)null, mediaCrypto, 0);
            passthroughMediaFormat.setString("mime", string);
            this.passthroughMediaFormat = passthroughMediaFormat;
            return;
        }
        mediaCodec.configure(passthroughMediaFormat, (Surface)null, mediaCrypto, 0);
        this.passthroughMediaFormat = null;
    }
    
    @Override
    protected DecoderInfo getDecoderInfo(final MediaCodecSelector mediaCodecSelector, final String s, final boolean b) {
        if (this.allowPassthrough(s)) {
            final DecoderInfo passthroughDecoderInfo = mediaCodecSelector.getPassthroughDecoderInfo();
            if (passthroughDecoderInfo != null) {
                this.passthroughEnabled = true;
                return passthroughDecoderInfo;
            }
        }
        this.passthroughEnabled = false;
        return super.getDecoderInfo(mediaCodecSelector, s, b);
    }
    
    @Override
    protected MediaClock getMediaClock() {
        return this;
    }
    
    @Override
    public long getPositionUs() {
        long currentPositionUs = this.audioTrack.getCurrentPositionUs(this.isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
        return this.currentPositionUs;
    }
    
    protected void handleAudioTrackDiscontinuity() {
    }
    
    @Override
    public void handleMessage(final int n, final Object o) {
        switch (n) {
            default: {
                super.handleMessage(n, o);
            }
            case 1: {
                this.audioTrack.setVolume((float)o);
            }
            case 2: {
                this.audioTrack.setPlaybackParams((PlaybackParams)o);
            }
        }
    }
    
    @Override
    protected boolean handlesTrack(final MediaCodecSelector mediaCodecSelector, final com.google.android.exoplayer.MediaFormat mediaFormat) {
        final boolean b = false;
        final String mimeType = mediaFormat.mimeType;
        boolean b2 = b;
        if (MimeTypes.isAudio(mimeType)) {
            if (!"audio/x-unknown".equals(mimeType) && (!this.allowPassthrough(mimeType) || mediaCodecSelector.getPassthroughDecoderInfo() == null)) {
                b2 = b;
                if (mediaCodecSelector.getDecoderInfo(mimeType, false) == null) {
                    return b2;
                }
            }
            b2 = true;
        }
        return b2;
    }
    
    @Override
    protected boolean isEnded() {
        return super.isEnded() && !this.audioTrack.hasPendingData();
    }
    
    @Override
    protected boolean isReady() {
        return this.audioTrack.hasPendingData() || super.isReady();
    }
    
    protected void onAudioSessionId(final int n) {
    }
    
    @Override
    protected void onDisabled() {
        this.audioSessionId = 0;
        try {
            this.audioTrack.release();
        }
        finally {
            super.onDisabled();
        }
    }
    
    @Override
    protected void onDiscontinuity(final long currentPositionUs) {
        super.onDiscontinuity(currentPositionUs);
        this.audioTrack.reset();
        this.currentPositionUs = currentPositionUs;
        this.allowPositionDiscontinuity = true;
    }
    
    @Override
    protected void onInputFormatChanged(final MediaFormatHolder mediaFormatHolder) {
        super.onInputFormatChanged(mediaFormatHolder);
        int pcmEncoding;
        if ("audio/raw".equals(mediaFormatHolder.format.mimeType)) {
            pcmEncoding = mediaFormatHolder.format.pcmEncoding;
        }
        else {
            pcmEncoding = 2;
        }
        this.pcmEncoding = pcmEncoding;
    }
    
    @Override
    protected void onOutputFormatChanged(final MediaCodec mediaCodec, MediaFormat passthroughMediaFormat) {
        boolean b;
        if (this.passthroughMediaFormat != null) {
            b = true;
        }
        else {
            b = false;
        }
        String string;
        if (b) {
            string = this.passthroughMediaFormat.getString("mime");
        }
        else {
            string = "audio/raw";
        }
        if (b) {
            passthroughMediaFormat = this.passthroughMediaFormat;
        }
        this.audioTrack.configure(string, passthroughMediaFormat.getInteger("channel-count"), passthroughMediaFormat.getInteger("sample-rate"), this.pcmEncoding);
    }
    
    @Override
    protected void onOutputStreamEnded() {
        this.audioTrack.handleEndOfStream();
    }
    
    @Override
    protected void onStarted() {
        super.onStarted();
        this.audioTrack.play();
    }
    
    @Override
    protected void onStopped() {
        this.audioTrack.pause();
        super.onStopped();
    }
    
    @Override
    protected boolean processOutputBuffer(final long p0, final long p1, final MediaCodec p2, final ByteBuffer p3, final MediaCodec$BufferInfo p4, final int p5, final boolean p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.passthroughEnabled:Z
        //     4: ifeq            27
        //     7: aload           7
        //     9: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //    12: iconst_2       
        //    13: iand           
        //    14: ifeq            27
        //    17: aload           5
        //    19: iload           8
        //    21: iconst_0       
        //    22: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //    25: iconst_1       
        //    26: ireturn        
        //    27: iload           9
        //    29: ifeq            67
        //    32: aload           5
        //    34: iload           8
        //    36: iconst_0       
        //    37: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //    40: aload_0        
        //    41: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.codecCounters:Lcom/google/android/exoplayer/CodecCounters;
        //    44: astore          5
        //    46: aload           5
        //    48: aload           5
        //    50: getfield        com/google/android/exoplayer/CodecCounters.skippedOutputBufferCount:I
        //    53: iconst_1       
        //    54: iadd           
        //    55: putfield        com/google/android/exoplayer/CodecCounters.skippedOutputBufferCount:I
        //    58: aload_0        
        //    59: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //    62: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.handleDiscontinuity:()V
        //    65: iconst_1       
        //    66: ireturn        
        //    67: aload_0        
        //    68: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //    71: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.isInitialized:()Z
        //    74: ifne            240
        //    77: aload_0        
        //    78: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioSessionId:I
        //    81: ifeq            200
        //    84: aload_0        
        //    85: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //    88: aload_0        
        //    89: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioSessionId:I
        //    92: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.initialize:(I)I
        //    95: pop            
        //    96: aload_0        
        //    97: iconst_0       
        //    98: putfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrackHasData:Z
        //   101: aload_0        
        //   102: invokevirtual   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.getState:()I
        //   105: iconst_3       
        //   106: if_icmpne       116
        //   109: aload_0        
        //   110: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   113: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.play:()V
        //   116: aload_0        
        //   117: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   120: aload           6
        //   122: aload           7
        //   124: getfield        android/media/MediaCodec$BufferInfo.offset:I
        //   127: aload           7
        //   129: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   132: aload           7
        //   134: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   137: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.handleBuffer:(Ljava/nio/ByteBuffer;IIJ)I
        //   140: istore          10
        //   142: aload_0        
        //   143: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   146: putfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.lastFeedElapsedRealtimeMs:J
        //   149: iload           10
        //   151: iconst_1       
        //   152: iand           
        //   153: ifeq            165
        //   156: aload_0        
        //   157: invokevirtual   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.handleAudioTrackDiscontinuity:()V
        //   160: aload_0        
        //   161: iconst_1       
        //   162: putfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.allowPositionDiscontinuity:Z
        //   165: iload           10
        //   167: iconst_2       
        //   168: iand           
        //   169: ifeq            353
        //   172: aload           5
        //   174: iload           8
        //   176: iconst_0       
        //   177: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   180: aload_0        
        //   181: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.codecCounters:Lcom/google/android/exoplayer/CodecCounters;
        //   184: astore          5
        //   186: aload           5
        //   188: aload           5
        //   190: getfield        com/google/android/exoplayer/CodecCounters.renderedOutputBufferCount:I
        //   193: iconst_1       
        //   194: iadd           
        //   195: putfield        com/google/android/exoplayer/CodecCounters.renderedOutputBufferCount:I
        //   198: iconst_1       
        //   199: ireturn        
        //   200: aload_0        
        //   201: aload_0        
        //   202: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   205: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.initialize:()I
        //   208: putfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioSessionId:I
        //   211: aload_0        
        //   212: aload_0        
        //   213: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioSessionId:I
        //   216: invokevirtual   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.onAudioSessionId:(I)V
        //   219: goto            96
        //   222: astore          5
        //   224: aload_0        
        //   225: aload           5
        //   227: invokespecial   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.notifyAudioTrackInitializationError:(Lcom/google/android/exoplayer/audio/AudioTrack$InitializationException;)V
        //   230: new             Lcom/google/android/exoplayer/ExoPlaybackException;
        //   233: dup            
        //   234: aload           5
        //   236: invokespecial   com/google/android/exoplayer/ExoPlaybackException.<init>:(Ljava/lang/Throwable;)V
        //   239: athrow         
        //   240: aload_0        
        //   241: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrackHasData:Z
        //   244: istore          9
        //   246: aload_0        
        //   247: aload_0        
        //   248: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   251: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.hasPendingData:()Z
        //   254: putfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrackHasData:Z
        //   257: iload           9
        //   259: ifeq            116
        //   262: aload_0        
        //   263: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrackHasData:Z
        //   266: ifne            116
        //   269: aload_0        
        //   270: invokevirtual   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.getState:()I
        //   273: iconst_3       
        //   274: if_icmpne       116
        //   277: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   280: lstore_3       
        //   281: aload_0        
        //   282: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.lastFeedElapsedRealtimeMs:J
        //   285: lstore          11
        //   287: aload_0        
        //   288: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   291: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.getBufferSizeUs:()J
        //   294: lstore_1       
        //   295: lload_1        
        //   296: ldc2_w          -1
        //   299: lcmp           
        //   300: ifne            326
        //   303: ldc2_w          -1
        //   306: lstore_1       
        //   307: aload_0        
        //   308: aload_0        
        //   309: getfield        com/google/android/exoplayer/MediaCodecAudioTrackRenderer.audioTrack:Lcom/google/android/exoplayer/audio/AudioTrack;
        //   312: invokevirtual   com/google/android/exoplayer/audio/AudioTrack.getBufferSize:()I
        //   315: lload_1        
        //   316: lload_3        
        //   317: lload           11
        //   319: lsub           
        //   320: invokespecial   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.notifyAudioTrackUnderrun:(IJJ)V
        //   323: goto            116
        //   326: lload_1        
        //   327: ldc2_w          1000
        //   330: ldiv           
        //   331: lstore_1       
        //   332: goto            307
        //   335: astore          5
        //   337: aload_0        
        //   338: aload           5
        //   340: invokespecial   com/google/android/exoplayer/MediaCodecAudioTrackRenderer.notifyAudioTrackWriteError:(Lcom/google/android/exoplayer/audio/AudioTrack$WriteException;)V
        //   343: new             Lcom/google/android/exoplayer/ExoPlaybackException;
        //   346: dup            
        //   347: aload           5
        //   349: invokespecial   com/google/android/exoplayer/ExoPlaybackException.<init>:(Ljava/lang/Throwable;)V
        //   352: athrow         
        //   353: iconst_0       
        //   354: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                   
        //  -----  -----  -----  -----  -----------------------------------------------------------------------
        //  77     96     222    240    Lcom/google/android/exoplayer/audio/AudioTrack$InitializationException;
        //  96     101    222    240    Lcom/google/android/exoplayer/audio/AudioTrack$InitializationException;
        //  116    149    335    353    Lcom/google/android/exoplayer/audio/AudioTrack$WriteException;
        //  200    219    222    240    Lcom/google/android/exoplayer/audio/AudioTrack$InitializationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
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
