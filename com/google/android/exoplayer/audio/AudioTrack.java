// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.PlaybackParams;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.C;
import android.annotation.TargetApi;
import android.util.Log;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.DtsUtil;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;
import android.os.ConditionVariable;
import java.lang.reflect.Method;

public final class AudioTrack
{
    public static boolean enablePreV21AudioSessionWorkaround;
    public static boolean failOnSpuriousAudioTimestamp;
    private final AudioCapabilities audioCapabilities;
    private boolean audioTimestampSet;
    private android.media.AudioTrack audioTrack;
    private final AudioTrack$AudioTrackUtil audioTrackUtil;
    private int bufferBytesRemaining;
    private int bufferSize;
    private long bufferSizeUs;
    private int channelConfig;
    private int framesPerEncodedSample;
    private Method getLatencyMethod;
    private android.media.AudioTrack keepSessionIdAudioTrack;
    private long lastPlayheadSampleTimeUs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private int nextPlayheadOffsetIndex;
    private boolean passthrough;
    private int pcmFrameSize;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private final ConditionVariable releasingConditionVariable;
    private ByteBuffer resampledBuffer;
    private long resumeSystemTimeUs;
    private int sampleRate;
    private long smoothedPlayheadOffsetUs;
    private int sourceEncoding;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private final int streamType;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private int targetEncoding;
    private byte[] temporaryBuffer;
    private int temporaryBufferOffset;
    private boolean useResampledBuffer;
    private float volume;
    
    static {
        AudioTrack.enablePreV21AudioSessionWorkaround = false;
        AudioTrack.failOnSpuriousAudioTimestamp = false;
    }
    
    public AudioTrack() {
        this(null, 3);
    }
    
    public AudioTrack(final AudioCapabilities audioCapabilities, final int streamType) {
        this.audioCapabilities = audioCapabilities;
        this.streamType = streamType;
        this.releasingConditionVariable = new ConditionVariable(true);
        while (true) {
            if (Util.SDK_INT < 18) {
                break Label_0049;
            }
            try {
                this.getLatencyMethod = android.media.AudioTrack.class.getMethod("getLatency", (Class<?>[])null);
                if (Util.SDK_INT >= 23) {
                    this.audioTrackUtil = new AudioTrack$AudioTrackUtilV23();
                }
                else if (Util.SDK_INT >= 19 && !AudioTrack$AudioTrackUtil.needPlaybackHeadPositionWorkaround()) {
                    this.audioTrackUtil = new AudioTrack$AudioTrackUtilV19();
                }
                else if (Util.SDK_INT >= 19 && AudioTrack$AudioTrackUtil.needPlaybackHeadPositionWorkaround()) {
                    this.audioTrackUtil = new AudioTrack$AudioTrackUtilWithNfWorkAround1(null);
                }
                else {
                    this.audioTrackUtil = new AudioTrack$AudioTrackUtil(null);
                }
                this.playheadOffsets = new long[10];
                this.volume = 1.0f;
                this.startMediaTimeState = 0;
            }
            catch (NoSuchMethodException ex) {
                continue;
            }
            break;
        }
    }
    
    private void checkAudioTrackInitialized() {
        final int state = this.audioTrack.getState();
        if (state == 1) {
            return;
        }
        try {
            this.audioTrack.release();
            this.audioTrack = null;
            throw new AudioTrack$InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
        }
        catch (Exception ex) {
            this.audioTrack = null;
            throw new AudioTrack$InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
        }
        finally {
            this.audioTrack = null;
        }
    }
    
    private long durationUsToFrames(final long n) {
        return this.sampleRate * n / 1000000L;
    }
    
    private long framesToDurationUs(final long n) {
        return 1000000L * n / this.sampleRate;
    }
    
    private static int getEncodingForMimeType(final String s) {
        switch (s) {
            default: {
                return 0;
            }
            case "audio/ac3": {
                return 5;
            }
            case "audio/eac3": {
                return 6;
            }
            case "audio/vnd.dts": {
                return 7;
            }
            case "audio/vnd.dts.hd": {
                return 8;
            }
        }
    }
    
    private static int getFramesPerEncodedSample(final int n, final ByteBuffer byteBuffer) {
        if (n == 7 || n == 8) {
            return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
        }
        if (n == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (n == 6) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        throw new IllegalStateException("Unexpected audio encoding: " + n);
    }
    
    private long getSubmittedFrames() {
        if (this.passthrough) {
            return this.submittedEncodedFrames;
        }
        return this.pcmBytesToFrames(this.submittedPcmBytes);
    }
    
    private boolean hasCurrentPositionUs() {
        return this.isInitialized() && this.startMediaTimeState != 0;
    }
    
    private void maybeSampleSyncParams() {
        final long playbackHeadPositionUs = this.audioTrackUtil.getPlaybackHeadPositionUs();
        if (playbackHeadPositionUs != 0L) {
            final long n = System.nanoTime() / 1000L;
            if (n - this.lastPlayheadSampleTimeUs >= 30000L) {
                this.playheadOffsets[this.nextPlayheadOffsetIndex] = playbackHeadPositionUs - n;
                this.nextPlayheadOffsetIndex = (this.nextPlayheadOffsetIndex + 1) % 10;
                if (this.playheadOffsetCount < 10) {
                    ++this.playheadOffsetCount;
                }
                this.lastPlayheadSampleTimeUs = n;
                this.smoothedPlayheadOffsetUs = 0L;
                for (int i = 0; i < this.playheadOffsetCount; ++i) {
                    this.smoothedPlayheadOffsetUs += this.playheadOffsets[i] / this.playheadOffsetCount;
                }
            }
            if (!this.needsPassthroughWorkarounds() && n - this.lastTimestampSampleTimeUs >= 500000L) {
                this.audioTimestampSet = this.audioTrackUtil.updateTimestamp();
                Label_0322: {
                    if (this.audioTimestampSet) {
                        final long n2 = this.audioTrackUtil.getTimestampNanoTime() / 1000L;
                        final long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition();
                        if (n2 >= this.resumeSystemTimeUs) {
                            break Label_0322;
                        }
                        this.audioTimestampSet = false;
                    }
                Label_0315_Outer:
                    while (true) {
                        while (true) {
                            if (this.getLatencyMethod == null || this.passthrough) {
                                break Label_0315;
                            }
                            try {
                                this.latencyUs = (int)this.getLatencyMethod.invoke(this.audioTrack, (Object[])null) * 1000L - this.bufferSizeUs;
                                this.latencyUs = Math.max(this.latencyUs, 0L);
                                if (this.latencyUs > 5000000L) {
                                    Log.w("AudioTrack", "Ignoring impossibly large audio latency: " + this.latencyUs);
                                    this.latencyUs = 0L;
                                }
                                this.lastTimestampSampleTimeUs = n;
                                return;
                                // iftrue(Label_0514:, !AudioTrack.failOnSpuriousAudioTimestamp)
                                // iftrue(Label_0208:, Math.abs(this.framesToDurationUs(timestampFramePosition) - playbackHeadPositionUs) <= 5000000L)
                                final long n2;
                                final long timestampFramePosition;
                                while (true) {
                                    final String string = "Spurious audio timestamp (frame position mismatch): " + timestampFramePosition + ", " + n2 + ", " + n + ", " + playbackHeadPositionUs;
                                    throw new AudioTrack$InvalidAudioTrackTimestampException(string);
                                    Label_0425: {
                                        continue;
                                    }
                                }
                                // iftrue(Label_0425:, Math.abs(n2 - n) <= 5000000L)
                                final String string2 = "Spurious audio timestamp (system clock mismatch): " + timestampFramePosition + ", " + n2 + ", " + n + ", " + playbackHeadPositionUs;
                                // iftrue(Label_0408:, !AudioTrack.failOnSpuriousAudioTimestamp)
                                throw new AudioTrack$InvalidAudioTrackTimestampException(string2);
                                Label_0514: {
                                    final String string;
                                    Log.w("AudioTrack", string);
                                }
                                this.audioTimestampSet = false;
                                continue Label_0315_Outer;
                                Label_0408:
                                Log.w("AudioTrack", string2);
                                this.audioTimestampSet = false;
                                continue Label_0315_Outer;
                            }
                            catch (Exception ex) {
                                this.getLatencyMethod = null;
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private boolean needsPassthroughWorkarounds() {
        return Util.SDK_INT < 23 && (this.targetEncoding == 5 || this.targetEncoding == 6);
    }
    
    private boolean overrideHasPendingData() {
        return this.needsPassthroughWorkarounds() && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0;
    }
    
    private long pcmBytesToFrames(final long n) {
        return n / this.pcmFrameSize;
    }
    
    private void releaseKeepSessionIdAudioTrack() {
        if (this.keepSessionIdAudioTrack == null) {
            return;
        }
        final android.media.AudioTrack keepSessionIdAudioTrack = this.keepSessionIdAudioTrack;
        this.keepSessionIdAudioTrack = null;
        new AudioTrack$2(this, keepSessionIdAudioTrack).start();
    }
    
    private static ByteBuffer resampleTo16BitPcm(final ByteBuffer byteBuffer, int i, int j, final int n, final ByteBuffer byteBuffer2) {
        int n2 = 0;
        switch (n) {
            default: {
                throw new IllegalStateException();
            }
            case 3: {
                n2 = j * 2;
                break;
            }
            case Integer.MIN_VALUE: {
                n2 = j / 3 * 2;
                break;
            }
            case 1073741824: {
                n2 = j / 2;
                break;
            }
        }
        ByteBuffer allocateDirect = null;
        Label_0075: {
            if (byteBuffer2 != null) {
                allocateDirect = byteBuffer2;
                if (byteBuffer2.capacity() >= n2) {
                    break Label_0075;
                }
            }
            allocateDirect = ByteBuffer.allocateDirect(n2);
        }
        allocateDirect.position(0);
        allocateDirect.limit(n2);
        final int n3 = i + j;
        j = i;
        int k = i;
        switch (n) {
            default: {
                throw new IllegalStateException();
            }
            case 3: {
                while (j < n3) {
                    allocateDirect.put((byte)0);
                    allocateDirect.put((byte)((byteBuffer.get(j) & 0xFF) - 128));
                    ++j;
                }
                break;
            }
            case Integer.MIN_VALUE: {
                while (k < n3) {
                    allocateDirect.put(byteBuffer.get(k + 1));
                    allocateDirect.put(byteBuffer.get(k + 2));
                    k += 3;
                }
                break;
            }
            case 1073741824: {
                while (i < n3) {
                    allocateDirect.put(byteBuffer.get(i + 2));
                    allocateDirect.put(byteBuffer.get(i + 3));
                    i += 4;
                }
                break;
            }
        }
        allocateDirect.position(0);
        return allocateDirect;
    }
    
    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0L;
    }
    
    private void setAudioTrackVolume() {
        if (!this.isInitialized()) {
            return;
        }
        if (Util.SDK_INT >= 21) {
            setAudioTrackVolumeV21(this.audioTrack, this.volume);
            return;
        }
        setAudioTrackVolumeV3(this.audioTrack, this.volume);
    }
    
    @TargetApi(21)
    private static void setAudioTrackVolumeV21(final android.media.AudioTrack audioTrack, final float volume) {
        audioTrack.setVolume(volume);
    }
    
    private static void setAudioTrackVolumeV3(final android.media.AudioTrack audioTrack, final float n) {
        audioTrack.setStereoVolume(n, n);
    }
    
    @TargetApi(21)
    private static int writeNonBlockingV21(final android.media.AudioTrack audioTrack, final ByteBuffer byteBuffer, final int n) {
        return audioTrack.write(byteBuffer, n, 1);
    }
    
    public void configure(final String s, final int n, final int n2, final int n3) {
        this.configure(s, n, n2, n3, 0);
    }
    
    public void configure(final String s, int bufferSize, int sampleRate, int n, int minBufferSize) {
        int channel_OUT_7POINT1_SURROUND = 0;
        switch (bufferSize) {
            default: {
                throw new IllegalArgumentException("Unsupported channel count: " + bufferSize);
            }
            case 1: {
                channel_OUT_7POINT1_SURROUND = 4;
                break;
            }
            case 2: {
                channel_OUT_7POINT1_SURROUND = 12;
                break;
            }
            case 3: {
                channel_OUT_7POINT1_SURROUND = 28;
                break;
            }
            case 4: {
                channel_OUT_7POINT1_SURROUND = 204;
                break;
            }
            case 5: {
                channel_OUT_7POINT1_SURROUND = 220;
                break;
            }
            case 6: {
                channel_OUT_7POINT1_SURROUND = 252;
                break;
            }
            case 7: {
                channel_OUT_7POINT1_SURROUND = 1276;
                break;
            }
            case 8: {
                channel_OUT_7POINT1_SURROUND = C.CHANNEL_OUT_7POINT1_SURROUND;
                break;
            }
        }
        final boolean passthrough = !"audio/raw".equals(s);
        int encodingForMimeType;
        if (passthrough) {
            encodingForMimeType = getEncodingForMimeType(s);
        }
        else {
            encodingForMimeType = n;
            if (n != 3 && (encodingForMimeType = n) != 2 && (encodingForMimeType = n) != Integer.MIN_VALUE && (encodingForMimeType = n) != 1073741824) {
                throw new IllegalArgumentException("Unsupported PCM encoding: " + n);
            }
        }
        if (this.isInitialized() && this.sourceEncoding == encodingForMimeType && this.sampleRate == sampleRate && this.channelConfig == channel_OUT_7POINT1_SURROUND) {
            return;
        }
        this.reset();
        this.sourceEncoding = encodingForMimeType;
        this.passthrough = passthrough;
        this.sampleRate = sampleRate;
        this.channelConfig = channel_OUT_7POINT1_SURROUND;
        if (!passthrough) {
            encodingForMimeType = 2;
        }
        this.targetEncoding = encodingForMimeType;
        this.pcmFrameSize = bufferSize * 2;
        if (minBufferSize != 0) {
            this.bufferSize = minBufferSize;
        }
        else if (passthrough) {
            if (this.targetEncoding == 5 || this.targetEncoding == 6) {
                this.bufferSize = 20480;
            }
            else {
                this.bufferSize = 49152;
            }
        }
        else {
            minBufferSize = android.media.AudioTrack.getMinBufferSize(sampleRate, channel_OUT_7POINT1_SURROUND, this.targetEncoding);
            Assertions.checkState(minBufferSize != -2);
            bufferSize = minBufferSize * 4;
            sampleRate = (int)this.durationUsToFrames(250000L) * this.pcmFrameSize;
            n = (int)Math.max(minBufferSize, this.durationUsToFrames(750000L) * this.pcmFrameSize);
            if (bufferSize < sampleRate) {
                bufferSize = sampleRate;
            }
            else if (bufferSize > n) {
                bufferSize = n;
            }
            this.bufferSize = bufferSize;
            this.audioTrackUtil.setBufferFrameSize(minBufferSize / this.pcmFrameSize, this.bufferSize / this.pcmFrameSize);
        }
        long framesToDurationUs;
        if (passthrough) {
            framesToDurationUs = -1L;
        }
        else {
            framesToDurationUs = this.framesToDurationUs(this.pcmBytesToFrames(this.bufferSize));
        }
        this.bufferSizeUs = framesToDurationUs;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public long getBufferSizeUs() {
        return this.bufferSizeUs;
    }
    
    public long getCurrentPositionUs(final boolean b) {
        long n;
        if (!this.hasCurrentPositionUs()) {
            n = Long.MIN_VALUE;
        }
        else {
            if (this.audioTrack.getPlayState() == 3) {
                this.maybeSampleSyncParams();
            }
            final long n2 = System.nanoTime() / 1000L;
            if (this.audioTimestampSet) {
                return this.framesToDurationUs(this.durationUsToFrames((long)((n2 - this.audioTrackUtil.getTimestampNanoTime() / 1000L) * this.audioTrackUtil.getPlaybackSpeed())) + this.audioTrackUtil.getTimestampFramePosition()) + this.startMediaTimeUs;
            }
            long n3;
            if (this.playheadOffsetCount == 0) {
                n3 = this.audioTrackUtil.getPlaybackHeadPositionUs() + this.startMediaTimeUs;
            }
            else {
                n3 = n2 + this.smoothedPlayheadOffsetUs + this.startMediaTimeUs;
            }
            n = n3;
            if (!b) {
                return n3 - this.latencyUs;
            }
        }
        return n;
    }
    
    public int handleBuffer(ByteBuffer resampledBuffer, int n, int n2, final long n3) {
        Label_0047: {
            if (!this.needsPassthroughWorkarounds()) {
                break Label_0047;
            }
            if (this.audioTrack.getPlayState() == 2) {
                n = 0;
            }
            else {
                if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0L) {
                    return 0;
                }
                break Label_0047;
            }
            return n;
        }
        if (this.bufferBytesRemaining == 0) {
            if (n2 == 0) {
                return 2;
            }
            this.useResampledBuffer = (this.targetEncoding != this.sourceEncoding);
            ByteBuffer resampledBuffer2 = resampledBuffer;
            int position = n;
            int limit = n2;
            if (this.useResampledBuffer) {
                Assertions.checkState(this.targetEncoding == 2);
                this.resampledBuffer = resampleTo16BitPcm(resampledBuffer, n, n2, this.sourceEncoding, this.resampledBuffer);
                resampledBuffer2 = this.resampledBuffer;
                position = this.resampledBuffer.position();
                limit = this.resampledBuffer.limit();
            }
            this.bufferBytesRemaining = limit;
            resampledBuffer2.position(position);
            if (this.passthrough && this.framesPerEncodedSample == 0) {
                this.framesPerEncodedSample = getFramesPerEncodedSample(this.targetEncoding, resampledBuffer2);
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0L, n3);
                this.startMediaTimeState = 1;
                n = 0;
            }
            else {
                final long n4 = this.startMediaTimeUs + this.framesToDurationUs(this.getSubmittedFrames());
                if (this.startMediaTimeState == 1 && Math.abs(n4 - n3) > 200000L) {
                    Log.e("AudioTrack", "Discontinuity detected [expected " + n4 + ", got " + n3 + "]");
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == 2) {
                    this.startMediaTimeUs += n3 - n4;
                    this.startMediaTimeState = 1;
                    n = 1;
                }
                else {
                    n = 0;
                }
            }
            n2 = n;
            resampledBuffer = resampledBuffer2;
            if (Util.SDK_INT < 21) {
                if (this.temporaryBuffer == null || this.temporaryBuffer.length < limit) {
                    this.temporaryBuffer = new byte[limit];
                }
                resampledBuffer2.get(this.temporaryBuffer, 0, limit);
                this.temporaryBufferOffset = 0;
                resampledBuffer = resampledBuffer2;
                n2 = n;
            }
        }
        else {
            n2 = 0;
        }
        n = 0;
        if (Util.SDK_INT < 21) {
            final int n5 = (int)(this.submittedPcmBytes - this.audioTrackUtil.getPlaybackHeadPosition() * this.pcmFrameSize);
            final int bufferSize = this.bufferSize;
            final int availableSpaceBytes = this.audioTrackUtil.getAvailableSpaceBytes(this.bufferBytesRemaining);
            int max;
            if (availableSpaceBytes < 0) {
                max = 0;
            }
            else {
                max = Math.max(availableSpaceBytes, bufferSize - n5);
            }
            if (max > 0) {
                n = Math.min(this.bufferBytesRemaining, max);
                final int write = this.audioTrack.write(this.temporaryBuffer, this.temporaryBufferOffset, n);
                if ((n = write) >= 0) {
                    this.temporaryBufferOffset += write;
                    n = write;
                }
            }
        }
        else {
            if (this.useResampledBuffer) {
                resampledBuffer = this.resampledBuffer;
            }
            n = writeNonBlockingV21(this.audioTrack, resampledBuffer, this.bufferBytesRemaining);
        }
        if (n < 0) {
            throw new AudioTrack$WriteException(n);
        }
        this.bufferBytesRemaining -= n;
        if (!this.passthrough) {
            this.submittedPcmBytes += n;
            this.audioTrackUtil.pcmFrameSubmitted(n / this.pcmFrameSize);
        }
        n = n2;
        if (this.bufferBytesRemaining == 0) {
            if (this.passthrough) {
                this.submittedEncodedFrames += this.framesPerEncodedSample;
            }
            return n2 | 0x2;
        }
        return n;
    }
    
    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }
    
    public void handleEndOfStream() {
        if (this.isInitialized()) {
            this.audioTrackUtil.handleEndOfStream(this.getSubmittedFrames());
        }
    }
    
    public boolean hasPendingData() {
        return this.isInitialized() && (this.getSubmittedFrames() > this.audioTrackUtil.getPlaybackHeadPosition() || this.overrideHasPendingData());
    }
    
    public int initialize() {
        return this.initialize(0);
    }
    
    public int initialize(int audioSessionId) {
        this.releasingConditionVariable.block();
        if (audioSessionId == 0) {
            this.audioTrack = new android.media.AudioTrack(this.streamType, this.sampleRate, this.channelConfig, this.targetEncoding, this.bufferSize, 1);
        }
        else {
            this.audioTrack = new android.media.AudioTrack(this.streamType, this.sampleRate, this.channelConfig, this.targetEncoding, this.bufferSize, 1, audioSessionId);
        }
        this.checkAudioTrackInitialized();
        audioSessionId = this.audioTrack.getAudioSessionId();
        if (AudioTrack.enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            if (this.keepSessionIdAudioTrack != null && audioSessionId != this.keepSessionIdAudioTrack.getAudioSessionId()) {
                this.releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = new android.media.AudioTrack(this.streamType, 4000, 4, 2, 2, 0, audioSessionId);
            }
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, this.needsPassthroughWorkarounds());
        this.setAudioTrackVolume();
        return audioSessionId;
    }
    
    public boolean isInitialized() {
        return this.audioTrack != null;
    }
    
    public boolean isPassthroughSupported(final String s) {
        return this.audioCapabilities != null && this.audioCapabilities.supportsEncoding(getEncodingForMimeType(s));
    }
    
    public void pause() {
        if (this.isInitialized()) {
            this.resetSyncParams();
            this.audioTrackUtil.pause();
        }
    }
    
    public void play() {
        if (this.isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000L;
            this.audioTrack.play();
            this.audioTrackUtil.play();
        }
    }
    
    public void release() {
        this.reset();
        this.releaseKeepSessionIdAudioTrack();
    }
    
    public void reset() {
        if (this.isInitialized()) {
            this.submittedPcmBytes = 0L;
            this.submittedEncodedFrames = 0L;
            this.framesPerEncodedSample = 0;
            this.bufferBytesRemaining = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0L;
            this.resetSyncParams();
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.pause();
            }
            final android.media.AudioTrack audioTrack = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new AudioTrack$1(this, audioTrack).start();
        }
    }
    
    public void setPlaybackParams(final PlaybackParams playbackParameters) {
        this.audioTrackUtil.setPlaybackParameters(playbackParameters);
    }
    
    public void setVolume(final float volume) {
        if (this.volume != volume) {
            this.volume = volume;
            this.setAudioTrackVolume();
        }
    }
}
