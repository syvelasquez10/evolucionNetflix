// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.TraceUtil;
import android.media.MediaCrypto;
import android.os.SystemClock;
import android.media.MediaCodec$CryptoInfo;
import com.google.android.exoplayer.util.NalUnitUtil;
import android.media.MediaCodec$CryptoException;
import java.util.ArrayList;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import android.os.Handler;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.DrmInitData;
import java.util.List;
import android.media.MediaCodec;
import android.annotation.TargetApi;

@TargetApi(16)
public abstract class MediaCodecTrackRenderer extends SampleSourceTrackRenderer
{
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER;
    private MediaCodec codec;
    public final CodecCounters codecCounters;
    private long codecHotswapTimeMs;
    private boolean codecIsAdaptive;
    private boolean codecNeedsAdaptationWorkaround;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    private final boolean deviceNeedsAutoFrcWorkaround;
    private DrmInitData drmInitData;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    protected final Handler eventHandler;
    private final MediaCodecTrackRenderer$EventListener eventListener;
    private MediaFormat format;
    private final MediaFormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final MediaCodecSelector mediaCodecSelector;
    private boolean openedDrmSession;
    private final MediaCodec$BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private final boolean playClearSamplesWithoutKeys;
    private final SampleHolder sampleHolder;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private int sourceState;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;
    
    static {
        ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    }
    
    public MediaCodecTrackRenderer(final SampleSource sampleSource, final MediaCodecSelector mediaCodecSelector, final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, final boolean b, final Handler handler, final MediaCodecTrackRenderer$EventListener mediaCodecTrackRenderer$EventListener) {
        this(new SampleSource[] { sampleSource }, mediaCodecSelector, drmSessionManager, b, handler, mediaCodecTrackRenderer$EventListener);
    }
    
    public MediaCodecTrackRenderer(final SampleSource[] array, final MediaCodecSelector mediaCodecSelector, final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, final boolean playClearSamplesWithoutKeys, final Handler eventHandler, final MediaCodecTrackRenderer$EventListener eventListener) {
        super(array);
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = Assertions.checkNotNull(mediaCodecSelector);
        this.drmSessionManager = drmSessionManager;
        this.playClearSamplesWithoutKeys = playClearSamplesWithoutKeys;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.deviceNeedsAutoFrcWorkaround = deviceNeedsAutoFrcWorkaround();
        this.codecCounters = new CodecCounters();
        this.sampleHolder = new SampleHolder(0);
        this.formatHolder = new MediaFormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList<Long>();
        this.outputBufferInfo = new MediaCodec$BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
    }
    
    private static boolean codecNeedsAdaptationWorkaround(final String s) {
        return Util.SDK_INT < 24 && ("OMX.Nvidia.h264.decode".equals(s) || "OMX.Nvidia.h264.decode.secure".equals(s)) && (Util.DEVICE.equals("flounder") || Util.DEVICE.equals("flounder_lte") || Util.DEVICE.equals("grouper") || Util.DEVICE.equals("tilapia"));
    }
    
    private static boolean codecNeedsDiscardToSpsWorkaround(final String s, final MediaFormat mediaFormat) {
        return Util.SDK_INT < 21 && mediaFormat.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(s);
    }
    
    private static boolean codecNeedsEosFlushWorkaround(final String s) {
        return Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(s);
    }
    
    private static boolean codecNeedsEosPropagationWorkaround(final String s) {
        return Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(s) || "OMX.allwinner.video.decoder.avc".equals(s));
    }
    
    private static boolean codecNeedsFlushWorkaround(final String s) {
        return Util.SDK_INT < 18 || (Util.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(s) || "OMX.SEC.avc.dec.secure".equals(s))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(s) || "OMX.Exynos.avc.dec.secure".equals(s)));
    }
    
    private static boolean codecNeedsMonoChannelCountWorkaround(final String s, final MediaFormat mediaFormat) {
        return Util.SDK_INT <= 18 && mediaFormat.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(s);
    }
    
    private static boolean deviceNeedsAutoFrcWorkaround() {
        return Util.SDK_INT <= 22 && "foster".equals(Util.DEVICE) && "NVIDIA".equals(Util.MANUFACTURER);
    }
    
    private boolean drainOutputBuffer(final long n, final long n2) {
        if (this.outputStreamEnded) {
            return false;
        }
        if (this.outputIndex < 0) {
            this.outputIndex = this.codec.dequeueOutputBuffer(this.outputBufferInfo, this.getDequeueOutputBufferTimeoutUs());
        }
        if (this.outputIndex == -2) {
            this.processOutputFormat();
            return true;
        }
        if (this.outputIndex == -3) {
            this.outputBuffers = this.codec.getOutputBuffers();
            final CodecCounters codecCounters = this.codecCounters;
            ++codecCounters.outputBuffersChangedCount;
            return true;
        }
        if (this.outputIndex < 0) {
            if (this.codecNeedsEosPropagationWorkaround && (this.inputStreamEnded || this.codecReinitializationState == 2)) {
                this.processEndOfStream();
                return true;
            }
            return false;
        }
        else {
            if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                this.codec.releaseOutputBuffer(this.outputIndex, false);
                this.outputIndex = -1;
                return true;
            }
            if ((this.outputBufferInfo.flags & 0x4) != 0x0) {
                this.processEndOfStream();
                return false;
            }
            final int decodeOnlyIndex = this.getDecodeOnlyIndex(this.outputBufferInfo.presentationTimeUs);
            if (this.processOutputBuffer(n, n2, this.codec, this.outputBuffers[this.outputIndex], this.outputBufferInfo, this.outputIndex, decodeOnlyIndex != -1)) {
                this.onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
                if (decodeOnlyIndex != -1) {
                    this.decodeOnlyPresentationTimestamps.remove(decodeOnlyIndex);
                }
                this.outputIndex = -1;
                return true;
            }
            return false;
        }
    }
    
    private boolean feedInputBuffer(long timeUs, final boolean b) {
        if (this.inputStreamEnded || this.codecReinitializationState == 2) {
            return false;
        }
        if (this.inputIndex < 0) {
            this.inputIndex = this.codec.dequeueInputBuffer(0L);
            if (this.inputIndex < 0) {
                return false;
            }
            this.sampleHolder.data = this.inputBuffers[this.inputIndex];
            this.sampleHolder.clearData();
        }
        if (this.codecReinitializationState == 1) {
            if (!this.codecNeedsEosPropagationWorkaround) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                this.inputIndex = -1;
            }
            this.codecReinitializationState = 2;
            return false;
        }
        if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            this.sampleHolder.data.put(MediaCodecTrackRenderer.ADAPTATION_WORKAROUND_BUFFER);
            this.codec.queueInputBuffer(this.inputIndex, 0, MediaCodecTrackRenderer.ADAPTATION_WORKAROUND_BUFFER.length, 0L, 0);
            this.inputIndex = -1;
            return this.codecReceivedBuffers = true;
        }
        int source;
        if (this.waitingForKeys) {
            source = -3;
        }
        else {
            if (this.codecReconfigurationState == 1) {
                for (int i = 0; i < this.format.initializationData.size(); ++i) {
                    this.sampleHolder.data.put(this.format.initializationData.get(i));
                }
                this.codecReconfigurationState = 2;
            }
            final int n = source = this.readSource(timeUs, this.formatHolder, this.sampleHolder);
            if (b) {
                source = n;
                if (this.sourceState == 1 && (source = n) == -2) {
                    this.sourceState = 2;
                    source = n;
                }
            }
        }
        if (source == -2) {
            return false;
        }
        if (source == -4) {
            if (this.codecReconfigurationState == 2) {
                this.sampleHolder.clearData();
                this.codecReconfigurationState = 1;
            }
            this.onInputFormatChanged(this.formatHolder);
            return true;
        }
        if (source == -1) {
            if (this.codecReconfigurationState == 2) {
                this.sampleHolder.clearData();
                this.codecReconfigurationState = 1;
            }
            this.inputStreamEnded = true;
            if (!this.codecReceivedBuffers) {
                this.processEndOfStream();
                return false;
            }
            try {
                if (this.codecNeedsEosPropagationWorkaround) {
                    return false;
                }
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                this.inputIndex = -1;
                return false;
            }
            catch (MediaCodec$CryptoException ex) {
                this.notifyCryptoError(ex);
                throw new ExoPlaybackException((Throwable)ex);
            }
        }
        if (this.waitingForFirstSyncFrame) {
            if (!this.sampleHolder.isSyncFrame()) {
                this.sampleHolder.clearData();
                if (this.codecReconfigurationState == 2) {
                    this.codecReconfigurationState = 1;
                }
                return true;
            }
            this.waitingForFirstSyncFrame = false;
        }
        final boolean encrypted = this.sampleHolder.isEncrypted();
        this.waitingForKeys = this.shouldWaitForKeys(encrypted);
        if (this.waitingForKeys) {
            return false;
        }
        if (this.codecNeedsDiscardToSpsWorkaround && !encrypted) {
            NalUnitUtil.discardToSps(this.sampleHolder.data);
            if (this.sampleHolder.data.position() == 0) {
                return true;
            }
            this.codecNeedsDiscardToSpsWorkaround = false;
        }
        try {
            final int position = this.sampleHolder.data.position();
            final int size = this.sampleHolder.size;
            timeUs = this.sampleHolder.timeUs;
            if (this.sampleHolder.isDecodeOnly()) {
                this.decodeOnlyPresentationTimestamps.add(timeUs);
            }
            this.onQueuedInputBuffer(timeUs, this.sampleHolder.data, position, encrypted);
            if (encrypted) {
                this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.sampleHolder, position - size), timeUs, 0);
            }
            else {
                this.codec.queueInputBuffer(this.inputIndex, 0, position, timeUs, 0);
            }
            this.inputIndex = -1;
            this.codecReceivedBuffers = true;
            this.codecReconfigurationState = 0;
            final CodecCounters codecCounters = this.codecCounters;
            ++codecCounters.inputBufferCount;
            return true;
        }
        catch (MediaCodec$CryptoException ex2) {
            this.notifyCryptoError(ex2);
            throw new ExoPlaybackException((Throwable)ex2);
        }
        return false;
    }
    
    private int getDecodeOnlyIndex(final long n) {
        for (int size = this.decodeOnlyPresentationTimestamps.size(), i = 0; i < size; ++i) {
            if (this.decodeOnlyPresentationTimestamps.get(i) == n) {
                return i;
            }
        }
        return -1;
    }
    
    private static MediaCodec$CryptoInfo getFrameworkCryptoInfo(final SampleHolder sampleHolder, final int n) {
        final MediaCodec$CryptoInfo frameworkCryptoInfoV16 = sampleHolder.cryptoInfo.getFrameworkCryptoInfoV16();
        if (n == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        final int[] numBytesOfClearData = frameworkCryptoInfoV16.numBytesOfClearData;
        numBytesOfClearData[0] += n;
        return frameworkCryptoInfoV16;
    }
    
    private android.media.MediaFormat getFrameworkMediaFormat(final MediaFormat mediaFormat) {
        final android.media.MediaFormat frameworkMediaFormatV16 = mediaFormat.getFrameworkMediaFormatV16();
        if (this.deviceNeedsAutoFrcWorkaround) {
            frameworkMediaFormatV16.setInteger("auto-frc", 0);
        }
        return frameworkMediaFormatV16;
    }
    
    private boolean isWithinHotswapPeriod() {
        return SystemClock.elapsedRealtime() < this.codecHotswapTimeMs + 1000L;
    }
    
    private void notifyAndThrowDecoderInitError(final MediaCodecTrackRenderer$DecoderInitializationException ex) {
        this.notifyDecoderInitializationError(ex);
        throw new ExoPlaybackException(ex);
    }
    
    private void notifyCryptoError(final MediaCodec$CryptoException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecTrackRenderer$2(this, ex));
        }
    }
    
    private void notifyDecoderInitializationError(final MediaCodecTrackRenderer$DecoderInitializationException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecTrackRenderer$1(this, ex));
        }
    }
    
    private void notifyDecoderInitialized(final String s, final long n, final long n2) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new MediaCodecTrackRenderer$3(this, s, n, n2));
        }
    }
    
    private void processEndOfStream() {
        if (this.codecReinitializationState == 2) {
            this.releaseCodec();
            this.maybeInitCodec();
            return;
        }
        this.outputStreamEnded = true;
        this.onOutputStreamEnded();
    }
    
    private void processOutputFormat() {
        final android.media.MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecNeedsAdaptationWorkaround && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        this.onOutputFormatChanged(this.codec, outputFormat);
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.outputFormatChangedCount;
    }
    
    private void readFormat(final long n) {
        if (this.readSource(n, this.formatHolder, null) == -4) {
            this.onInputFormatChanged(this.formatHolder);
        }
    }
    
    private boolean shouldWaitForKeys(final boolean b) {
        if (this.openedDrmSession) {
            final int state = this.drmSessionManager.getState();
            if (state == 0) {
                throw new ExoPlaybackException(this.drmSessionManager.getError());
            }
            if (state != 4 && (b || !this.playClearSamplesWithoutKeys)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean canReconfigureCodec(final MediaCodec mediaCodec, final boolean b, final MediaFormat mediaFormat, final MediaFormat mediaFormat2) {
        return false;
    }
    
    protected final boolean codecInitialized() {
        return this.codec != null;
    }
    
    protected abstract void configureCodec(final MediaCodec p0, final boolean p1, final android.media.MediaFormat p2, final MediaCrypto p3);
    
    @Override
    protected void doSomeWork(final long n, final long n2, final boolean b) {
        int sourceState;
        if (b) {
            if (this.sourceState == 0) {
                sourceState = 1;
            }
            else {
                sourceState = this.sourceState;
            }
        }
        else {
            sourceState = 0;
        }
        this.sourceState = sourceState;
        if (this.format == null) {
            this.readFormat(n);
        }
        this.maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            while (this.drainOutputBuffer(n, n2)) {}
            if (this.feedInputBuffer(n, true)) {
                while (this.feedInputBuffer(n, false)) {}
            }
            TraceUtil.endSection();
        }
        this.codecCounters.ensureUpdated();
    }
    
    protected void flushCodec() {
        this.codecHotswapTimeMs = -1L;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos)) {
            this.releaseCodec();
            this.maybeInitCodec();
        }
        else if (this.codecReinitializationState != 0) {
            this.releaseCodec();
            this.maybeInitCodec();
        }
        else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        if (this.codecReconfigured && this.format != null) {
            this.codecReconfigurationState = 1;
        }
    }
    
    protected DecoderInfo getDecoderInfo(final MediaCodecSelector mediaCodecSelector, final String s, final boolean b) {
        return mediaCodecSelector.getDecoderInfo(s, b);
    }
    
    protected long getDequeueOutputBufferTimeoutUs() {
        return 0L;
    }
    
    protected final int getSourceState() {
        return this.sourceState;
    }
    
    protected abstract boolean handlesTrack(final MediaCodecSelector p0, final MediaFormat p1);
    
    @Override
    protected final boolean handlesTrack(final MediaFormat mediaFormat) {
        return this.handlesTrack(this.mediaCodecSelector, mediaFormat);
    }
    
    @Override
    protected boolean isEnded() {
        return this.outputStreamEnded;
    }
    
    @Override
    protected boolean isReady() {
        return this.format != null && !this.waitingForKeys && (this.sourceState != 0 || this.outputIndex >= 0 || this.isWithinHotswapPeriod());
    }
    
    protected final void maybeInitCodec() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/exoplayer/MediaCodecTrackRenderer.shouldInitCodec:()Z
        //     4: ifne            8
        //     7: return         
        //     8: aload_0        
        //     9: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //    12: getfield        com/google/android/exoplayer/MediaFormat.mimeType:Ljava/lang/String;
        //    15: astore          8
        //    17: aload_0        
        //    18: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmInitData:Lcom/google/android/exoplayer/drm/DrmInitData;
        //    21: ifnull          498
        //    24: aload_0        
        //    25: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //    28: ifnonnull       42
        //    31: new             Lcom/google/android/exoplayer/ExoPlaybackException;
        //    34: dup            
        //    35: ldc_w           "Media requires a DrmSessionManager"
        //    38: invokespecial   com/google/android/exoplayer/ExoPlaybackException.<init>:(Ljava/lang/String;)V
        //    41: athrow         
        //    42: aload_0        
        //    43: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.openedDrmSession:Z
        //    46: ifne            67
        //    49: aload_0        
        //    50: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //    53: aload_0        
        //    54: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmInitData:Lcom/google/android/exoplayer/drm/DrmInitData;
        //    57: invokeinterface com/google/android/exoplayer/drm/DrmSessionManager.open:(Lcom/google/android/exoplayer/drm/DrmInitData;)V
        //    62: aload_0        
        //    63: iconst_1       
        //    64: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.openedDrmSession:Z
        //    67: aload_0        
        //    68: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //    71: invokeinterface com/google/android/exoplayer/drm/DrmSessionManager.getState:()I
        //    76: istore_1       
        //    77: iload_1        
        //    78: ifne            98
        //    81: new             Lcom/google/android/exoplayer/ExoPlaybackException;
        //    84: dup            
        //    85: aload_0        
        //    86: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //    89: invokeinterface com/google/android/exoplayer/drm/DrmSessionManager.getError:()Ljava/lang/Exception;
        //    94: invokespecial   com/google/android/exoplayer/ExoPlaybackException.<init>:(Ljava/lang/Throwable;)V
        //    97: athrow         
        //    98: iload_1        
        //    99: iconst_3       
        //   100: if_icmpeq       108
        //   103: iload_1        
        //   104: iconst_4       
        //   105: if_icmpne       7
        //   108: aload_0        
        //   109: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //   112: invokeinterface com/google/android/exoplayer/drm/DrmSessionManager.getMediaCrypto:()Lcom/google/android/exoplayer/drm/ExoMediaCrypto;
        //   117: checkcast       Lcom/google/android/exoplayer/drm/FrameworkMediaCrypto;
        //   120: invokevirtual   com/google/android/exoplayer/drm/FrameworkMediaCrypto.getWrappedMediaCrypto:()Landroid/media/MediaCrypto;
        //   123: astore          7
        //   125: aload_0        
        //   126: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.drmSessionManager:Lcom/google/android/exoplayer/drm/DrmSessionManager;
        //   129: aload           8
        //   131: invokeinterface com/google/android/exoplayer/drm/DrmSessionManager.requiresSecureDecoderComponent:(Ljava/lang/String;)Z
        //   136: istore_2       
        //   137: aload_0        
        //   138: aload_0        
        //   139: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.mediaCodecSelector:Lcom/google/android/exoplayer/MediaCodecSelector;
        //   142: aload           8
        //   144: iload_2        
        //   145: invokevirtual   com/google/android/exoplayer/MediaCodecTrackRenderer.getDecoderInfo:(Lcom/google/android/exoplayer/MediaCodecSelector;Ljava/lang/String;Z)Lcom/google/android/exoplayer/DecoderInfo;
        //   148: astore          8
        //   150: aload           8
        //   152: ifnonnull       175
        //   155: aload_0        
        //   156: new             Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;
        //   159: dup            
        //   160: aload_0        
        //   161: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   164: aconst_null    
        //   165: iload_2        
        //   166: ldc_w           -49999
        //   169: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException.<init>:(Lcom/google/android/exoplayer/MediaFormat;Ljava/lang/Throwable;ZI)V
        //   172: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer.notifyAndThrowDecoderInitError:(Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;)V
        //   175: aload           8
        //   177: getfield        com/google/android/exoplayer/DecoderInfo.name:Ljava/lang/String;
        //   180: astore          9
        //   182: aload_0        
        //   183: aload           8
        //   185: getfield        com/google/android/exoplayer/DecoderInfo.adaptive:Z
        //   188: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecIsAdaptive:Z
        //   191: aload_0        
        //   192: aload           9
        //   194: aload_0        
        //   195: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   198: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsDiscardToSpsWorkaround:(Ljava/lang/String;Lcom/google/android/exoplayer/MediaFormat;)Z
        //   201: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsDiscardToSpsWorkaround:Z
        //   204: aload_0        
        //   205: aload           9
        //   207: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsFlushWorkaround:(Ljava/lang/String;)Z
        //   210: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsFlushWorkaround:Z
        //   213: aload_0        
        //   214: aload           9
        //   216: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsAdaptationWorkaround:(Ljava/lang/String;)Z
        //   219: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsAdaptationWorkaround:Z
        //   222: aload_0        
        //   223: aload           9
        //   225: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsEosPropagationWorkaround:(Ljava/lang/String;)Z
        //   228: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsEosPropagationWorkaround:Z
        //   231: aload_0        
        //   232: aload           9
        //   234: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsEosFlushWorkaround:(Ljava/lang/String;)Z
        //   237: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsEosFlushWorkaround:Z
        //   240: aload_0        
        //   241: aload           9
        //   243: aload_0        
        //   244: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   247: invokestatic    com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsMonoChannelCountWorkaround:(Ljava/lang/String;Lcom/google/android/exoplayer/MediaFormat;)Z
        //   250: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecNeedsMonoChannelCountWorkaround:Z
        //   253: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   256: lstore_3       
        //   257: new             Ljava/lang/StringBuilder;
        //   260: dup            
        //   261: invokespecial   java/lang/StringBuilder.<init>:()V
        //   264: ldc_w           "createByCodecName("
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload           9
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: ldc_w           ")"
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: invokestatic    com/google/android/exoplayer/util/TraceUtil.beginSection:(Ljava/lang/String;)V
        //   287: aload_0        
        //   288: aload           9
        //   290: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //   293: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codec:Landroid/media/MediaCodec;
        //   296: invokestatic    com/google/android/exoplayer/util/TraceUtil.endSection:()V
        //   299: ldc_w           "configureCodec"
        //   302: invokestatic    com/google/android/exoplayer/util/TraceUtil.beginSection:(Ljava/lang/String;)V
        //   305: aload_0        
        //   306: aload_0        
        //   307: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codec:Landroid/media/MediaCodec;
        //   310: aload           8
        //   312: getfield        com/google/android/exoplayer/DecoderInfo.adaptive:Z
        //   315: aload_0        
        //   316: aload_0        
        //   317: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   320: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer.getFrameworkMediaFormat:(Lcom/google/android/exoplayer/MediaFormat;)Landroid/media/MediaFormat;
        //   323: aload           7
        //   325: invokevirtual   com/google/android/exoplayer/MediaCodecTrackRenderer.configureCodec:(Landroid/media/MediaCodec;ZLandroid/media/MediaFormat;Landroid/media/MediaCrypto;)V
        //   328: invokestatic    com/google/android/exoplayer/util/TraceUtil.endSection:()V
        //   331: ldc_w           "codec.start()"
        //   334: invokestatic    com/google/android/exoplayer/util/TraceUtil.beginSection:(Ljava/lang/String;)V
        //   337: aload_0        
        //   338: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codec:Landroid/media/MediaCodec;
        //   341: invokevirtual   android/media/MediaCodec.start:()V
        //   344: invokestatic    com/google/android/exoplayer/util/TraceUtil.endSection:()V
        //   347: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   350: lstore          5
        //   352: aload_0        
        //   353: aload           9
        //   355: lload           5
        //   357: lload           5
        //   359: lload_3        
        //   360: lsub           
        //   361: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer.notifyDecoderInitialized:(Ljava/lang/String;JJ)V
        //   364: aload_0        
        //   365: aload_0        
        //   366: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codec:Landroid/media/MediaCodec;
        //   369: invokevirtual   android/media/MediaCodec.getInputBuffers:()[Ljava/nio/ByteBuffer;
        //   372: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.inputBuffers:[Ljava/nio/ByteBuffer;
        //   375: aload_0        
        //   376: aload_0        
        //   377: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codec:Landroid/media/MediaCodec;
        //   380: invokevirtual   android/media/MediaCodec.getOutputBuffers:()[Ljava/nio/ByteBuffer;
        //   383: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.outputBuffers:[Ljava/nio/ByteBuffer;
        //   386: aload_0        
        //   387: invokevirtual   com/google/android/exoplayer/MediaCodecTrackRenderer.getState:()I
        //   390: iconst_3       
        //   391: if_icmpne       491
        //   394: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   397: lstore_3       
        //   398: aload_0        
        //   399: lload_3        
        //   400: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecHotswapTimeMs:J
        //   403: aload_0        
        //   404: iconst_m1      
        //   405: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.inputIndex:I
        //   408: aload_0        
        //   409: iconst_m1      
        //   410: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.outputIndex:I
        //   413: aload_0        
        //   414: iconst_1       
        //   415: putfield        com/google/android/exoplayer/MediaCodecTrackRenderer.waitingForFirstSyncFrame:Z
        //   418: aload_0        
        //   419: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.codecCounters:Lcom/google/android/exoplayer/CodecCounters;
        //   422: astore          7
        //   424: aload           7
        //   426: aload           7
        //   428: getfield        com/google/android/exoplayer/CodecCounters.codecInitCount:I
        //   431: iconst_1       
        //   432: iadd           
        //   433: putfield        com/google/android/exoplayer/CodecCounters.codecInitCount:I
        //   436: return         
        //   437: astore          8
        //   439: aload_0        
        //   440: new             Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;
        //   443: dup            
        //   444: aload_0        
        //   445: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   448: aload           8
        //   450: iload_2        
        //   451: ldc_w           -49998
        //   454: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException.<init>:(Lcom/google/android/exoplayer/MediaFormat;Ljava/lang/Throwable;ZI)V
        //   457: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer.notifyAndThrowDecoderInitError:(Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;)V
        //   460: aconst_null    
        //   461: astore          8
        //   463: goto            150
        //   466: astore          7
        //   468: aload_0        
        //   469: new             Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;
        //   472: dup            
        //   473: aload_0        
        //   474: getfield        com/google/android/exoplayer/MediaCodecTrackRenderer.format:Lcom/google/android/exoplayer/MediaFormat;
        //   477: aload           7
        //   479: iload_2        
        //   480: aload           9
        //   482: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException.<init>:(Lcom/google/android/exoplayer/MediaFormat;Ljava/lang/Throwable;ZLjava/lang/String;)V
        //   485: invokespecial   com/google/android/exoplayer/MediaCodecTrackRenderer.notifyAndThrowDecoderInitError:(Lcom/google/android/exoplayer/MediaCodecTrackRenderer$DecoderInitializationException;)V
        //   488: goto            386
        //   491: ldc2_w          -1
        //   494: lstore_3       
        //   495: goto            398
        //   498: iconst_0       
        //   499: istore_2       
        //   500: aconst_null    
        //   501: astore          7
        //   503: goto            137
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                               
        //  -----  -----  -----  -----  -------------------------------------------------------------------
        //  137    150    437    466    Lcom/google/android/exoplayer/MediaCodecUtil$DecoderQueryException;
        //  253    386    466    491    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0386:
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
    
    @Override
    protected void onDisabled() {
        this.format = null;
        this.drmInitData = null;
        try {
            this.releaseCodec();
            try {
                if (this.openedDrmSession) {
                    this.drmSessionManager.close();
                    this.openedDrmSession = false;
                }
            }
            finally {
                super.onDisabled();
            }
        }
        finally {
            try {
                if (this.openedDrmSession) {
                    this.drmSessionManager.close();
                    this.openedDrmSession = false;
                }
                super.onDisabled();
            }
            finally {
                super.onDisabled();
            }
        }
    }
    
    @Override
    protected void onDiscontinuity(final long n) {
        this.sourceState = 0;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.codec != null) {
            this.flushCodec();
        }
    }
    
    protected void onInputFormatChanged(final MediaFormatHolder mediaFormatHolder) {
        boolean codecNeedsAdaptationWorkaroundBuffer = true;
        final MediaFormat format = this.format;
        this.format = mediaFormatHolder.format;
        this.drmInitData = mediaFormatHolder.drmInitData;
        if (Util.areEqual(this.format, format)) {
            return;
        }
        if (this.codec != null && this.canReconfigureCodec(this.codec, this.codecIsAdaptive, format, this.format)) {
            this.codecReconfigured = true;
            this.codecReconfigurationState = 1;
            if (!this.codecNeedsAdaptationWorkaround || this.format.width != format.width || this.format.height != format.height) {
                codecNeedsAdaptationWorkaroundBuffer = false;
            }
            this.codecNeedsAdaptationWorkaroundBuffer = codecNeedsAdaptationWorkaroundBuffer;
            return;
        }
        if (this.codecReceivedBuffers) {
            this.codecReinitializationState = 1;
            return;
        }
        this.releaseCodec();
        this.maybeInitCodec();
    }
    
    protected void onOutputFormatChanged(final MediaCodec mediaCodec, final android.media.MediaFormat mediaFormat) {
    }
    
    protected void onOutputStreamEnded() {
    }
    
    protected void onProcessedOutputBuffer(final long n) {
    }
    
    protected void onQueuedInputBuffer(final long n, final ByteBuffer byteBuffer, final int n2, final boolean b) {
    }
    
    @Override
    protected void onStarted() {
    }
    
    @Override
    protected void onStopped() {
    }
    
    protected abstract boolean processOutputBuffer(final long p0, final long p1, final MediaCodec p2, final ByteBuffer p3, final MediaCodec$BufferInfo p4, final int p5, final boolean p6);
    
    protected void releaseCodec() {
        if (this.codec == null) {
            return;
        }
        this.codecHotswapTimeMs = -1L;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.waitingForKeys = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.inputBuffers = null;
        this.outputBuffers = null;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecIsAdaptive = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecNeedsAdaptationWorkaround = false;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.codecReleaseCount;
        try {
            this.codec.stop();
            try {
                this.codec.release();
            }
            finally {
                this.codec = null;
            }
        }
        finally {
            try {
                this.codec.release();
                this.codec = null;
            }
            finally {
                this.codec = null;
            }
        }
    }
    
    protected boolean shouldInitCodec() {
        return this.codec == null && this.format != null;
    }
}
