// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.MimeTypes;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import android.util.Pair;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.DrmInitData$Universal;
import com.google.android.exoplayer.drm.DrmInitData$SchemeInitData;
import java.util.Locale;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.TrackOutput;
import java.util.Arrays;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.NalUnitUtil;
import android.util.SparseArray;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.LongArray;
import java.util.UUID;
import com.google.android.exoplayer.extractor.Extractor;

public final class WebmExtractor implements Extractor
{
    private static final byte[] SUBRIP_PREFIX;
    private static final byte[] SUBRIP_TIMECODE_EMPTY;
    private static final UUID WAVE_SUBFORMAT_PCM;
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private WebmExtractor$Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentDrmInitData;
    private boolean sentSeekMap;
    private final ParsableByteArray subripSample;
    private long timecodeScale;
    private final SparseArray<WebmExtractor$Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;
    
    static {
        SUBRIP_PREFIX = new byte[] { 49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10 };
        SUBRIP_TIMECODE_EMPTY = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
        WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    }
    
    public WebmExtractor() {
        this(new DefaultEbmlReader());
    }
    
    WebmExtractor(final EbmlReader reader) {
        this.segmentContentPosition = -1L;
        this.segmentContentSize = -1L;
        this.timecodeScale = -1L;
        this.durationTimecode = -1L;
        this.durationUs = -1L;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = -1L;
        (this.reader = reader).init(new WebmExtractor$InnerEbmlReaderOutput(this, null));
        this.varintReader = new VarintReader();
        this.tracks = (SparseArray<WebmExtractor$Track>)new SparseArray();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subripSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }
    
    private SeekMap buildSeekMap() {
        final int n = 0;
        if (this.segmentContentPosition == -1L || this.durationUs == -1L || this.cueTimesUs == null || this.cueTimesUs.size() == 0 || this.cueClusterPositions == null || this.cueClusterPositions.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return SeekMap.UNSEEKABLE;
        }
        final int size = this.cueTimesUs.size();
        final int[] array = new int[size];
        final long[] array2 = new long[size];
        final long[] array3 = new long[size];
        final long[] array4 = new long[size];
        int n2 = 0;
        int i;
        while (true) {
            i = n;
            if (n2 >= size) {
                break;
            }
            array4[n2] = this.cueTimesUs.get(n2);
            array2[n2] = this.segmentContentPosition + this.cueClusterPositions.get(n2);
            ++n2;
        }
        while (i < size - 1) {
            array[i] = (int)(array2[i + 1] - array2[i]);
            array3[i] = array4[i + 1] - array4[i];
            ++i;
        }
        array[size - 1] = (int)(this.segmentContentPosition + this.segmentContentSize - array2[size - 1]);
        array3[size - 1] = this.durationUs - array4[size - 1];
        this.cueTimesUs = null;
        this.cueClusterPositions = null;
        return new ChunkIndex(array, array2, array3, array4);
    }
    
    private void commitSampleToOutput(final WebmExtractor$Track webmExtractor$Track, final long n) {
        if ("S_TEXT/UTF8".equals(webmExtractor$Track.codecId)) {
            this.writeSubripSample(webmExtractor$Track);
        }
        webmExtractor$Track.output.sampleMetadata(n, this.blockFlags, this.sampleBytesWritten, 0, webmExtractor$Track.encryptionKeyId);
        this.sampleRead = true;
        this.resetSample();
    }
    
    private static int[] ensureArrayCapacity(final int[] array, final int n) {
        int[] array2;
        if (array == null) {
            array2 = new int[n];
        }
        else {
            array2 = array;
            if (array.length < n) {
                return new int[Math.max(array.length * 2, n)];
            }
        }
        return array2;
    }
    
    private static boolean isCodecSupported(final String s) {
        return "V_VP8".equals(s) || "V_VP9".equals(s) || "V_MPEG2".equals(s) || "V_MPEG4/ISO/SP".equals(s) || "V_MPEG4/ISO/ASP".equals(s) || "V_MPEG4/ISO/AP".equals(s) || "V_MPEG4/ISO/AVC".equals(s) || "V_MPEGH/ISO/HEVC".equals(s) || "V_MS/VFW/FOURCC".equals(s) || "A_OPUS".equals(s) || "A_VORBIS".equals(s) || "A_AAC".equals(s) || "A_MPEG/L3".equals(s) || "A_AC3".equals(s) || "A_EAC3".equals(s) || "A_TRUEHD".equals(s) || "A_DTS".equals(s) || "A_DTS/EXPRESS".equals(s) || "A_DTS/LOSSLESS".equals(s) || "A_FLAC".equals(s) || "A_MS/ACM".equals(s) || "A_PCM/INT/LIT".equals(s) || "S_TEXT/UTF8".equals(s) || "S_VOBSUB".equals(s) || "S_HDMV/PGS".equals(s);
    }
    
    private boolean maybeSeekForCues(final PositionHolder positionHolder, final long seekPositionAfterBuildingCues) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = seekPositionAfterBuildingCues;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap && this.seekPositionAfterBuildingCues != -1L) {
            positionHolder.position = this.seekPositionAfterBuildingCues;
            this.seekPositionAfterBuildingCues = -1L;
            return true;
        }
        return false;
    }
    
    private void readScratch(final ExtractorInput extractorInput, final int limit) {
        if (this.scratch.limit() >= limit) {
            return;
        }
        if (this.scratch.capacity() < limit) {
            this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, limit)), this.scratch.limit());
        }
        extractorInput.readFully(this.scratch.data, this.scratch.limit(), limit - this.scratch.limit());
        this.scratch.setLimit(limit);
    }
    
    private int readToOutput(final ExtractorInput extractorInput, final TrackOutput trackOutput, int n) {
        final int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            n = Math.min(n, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, n);
        }
        else {
            n = trackOutput.sampleData(extractorInput, n, false);
        }
        this.sampleBytesRead += n;
        this.sampleBytesWritten += n;
        return n;
    }
    
    private void readToTarget(final ExtractorInput extractorInput, final byte[] array, final int n, final int n2) {
        final int min = Math.min(n2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(array, n + min, n2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(array, n, min);
        }
        this.sampleBytesRead += n2;
    }
    
    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }
    
    private long scaleTimecodeToUs(final long n) {
        if (this.timecodeScale == -1L) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(n, this.timecodeScale, 1000L);
    }
    
    private static void setSubripSampleEndTimecode(final byte[] array, long n) {
        byte[] array2;
        if (n == -1L) {
            array2 = WebmExtractor.SUBRIP_TIMECODE_EMPTY;
        }
        else {
            final int n2 = (int)(n / 3600000000L);
            n -= n2 * 3600000000L;
            final int n3 = (int)(n / 60000000L);
            n -= 60000000 * n3;
            final int n4 = (int)(n / 1000000L);
            array2 = String.format(Locale.US, "%02d:%02d:%02d,%03d", n2, n3, n4, (int)((n - 1000000 * n4) / 1000L)).getBytes();
        }
        System.arraycopy(array2, 0, array, 19, 12);
    }
    
    private void writeSampleData(final ExtractorInput extractorInput, final WebmExtractor$Track webmExtractor$Track, int n) {
        if ("S_TEXT/UTF8".equals(webmExtractor$Track.codecId)) {
            final int limit = WebmExtractor.SUBRIP_PREFIX.length + n;
            if (this.subripSample.capacity() < limit) {
                this.subripSample.data = Arrays.copyOf(WebmExtractor.SUBRIP_PREFIX, limit + n);
            }
            extractorInput.readFully(this.subripSample.data, WebmExtractor.SUBRIP_PREFIX.length, n);
            this.subripSample.setPosition(0);
            this.subripSample.setLimit(limit);
        }
        else {
            final TrackOutput output = webmExtractor$Track.output;
            if (!this.sampleEncodingHandled) {
                if (webmExtractor$Track.hasContentEncryption) {
                    this.blockFlags &= 0xFFFFFFFD;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        ++this.sampleBytesRead;
                        if ((this.scratch.data[0] & 0x80) == 0x80) {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                        this.sampleSignalByte = this.scratch.data[0];
                        this.sampleSignalByteRead = true;
                    }
                    int n2;
                    if ((this.sampleSignalByte & 0x1) == 0x1) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    if (n2 != 0) {
                        boolean b;
                        if ((this.sampleSignalByte & 0x2) == 0x2) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        this.blockFlags |= 0x2;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            final byte[] data = this.scratch.data;
                            int n3;
                            if (b) {
                                n3 = 128;
                            }
                            else {
                                n3 = 0;
                            }
                            data[0] = (byte)(n3 | 0x8);
                            this.scratch.setPosition(0);
                            output.sampleData(this.scratch, 1);
                            ++this.sampleBytesWritten;
                            this.encryptionInitializationVector.setPosition(0);
                            output.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (b) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                ++this.sampleBytesRead;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            final int limit2 = this.samplePartitionCount * 4;
                            if (this.scratch.limit() < limit2) {
                                this.scratch.reset(new byte[limit2], limit2);
                            }
                            extractorInput.readFully(this.scratch.data, 0, limit2);
                            this.sampleBytesRead += limit2;
                            this.scratch.setPosition(0);
                            this.scratch.setLimit(limit2);
                            final short n4 = (short)(this.samplePartitionCount / 2 + 1);
                            final short n5 = (short)(n4 * 6 + 2);
                            if (this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < n5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(n5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(n4);
                            int i = 0;
                            int n6 = 0;
                            while (i < this.samplePartitionCount) {
                                final int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short)(unsignedIntToInt - n6));
                                }
                                else {
                                    this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - n6);
                                }
                                ++i;
                                n6 = unsignedIntToInt;
                            }
                            final int n7 = n - this.sampleBytesRead - n6;
                            if (this.samplePartitionCount % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(n7);
                            }
                            else {
                                this.encryptionSubsampleDataBuffer.putShort((short)n7);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), n5);
                            output.sampleData(this.encryptionSubsampleData, n5);
                            this.sampleBytesWritten += n5;
                        }
                    }
                }
                else if (webmExtractor$Track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(webmExtractor$Track.sampleStrippedBytes, webmExtractor$Track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            n += this.sampleStrippedBytes.limit();
            if ("V_MPEG4/ISO/AVC".equals(webmExtractor$Track.codecId) || "V_MPEGH/ISO/HEVC".equals(webmExtractor$Track.codecId)) {
                final byte[] data2 = this.nalLength.data;
                data2[0] = 0;
                data2[2] = (data2[1] = 0);
                final int nalUnitLengthFieldLength = webmExtractor$Track.nalUnitLengthFieldLength;
                final int nalUnitLengthFieldLength2 = webmExtractor$Track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < n) {
                    if (this.sampleCurrentNalBytesRemaining == 0) {
                        this.readToTarget(extractorInput, data2, 4 - nalUnitLengthFieldLength2, nalUnitLengthFieldLength);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        output.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    }
                    else {
                        this.sampleCurrentNalBytesRemaining -= this.readToOutput(extractorInput, output, this.sampleCurrentNalBytesRemaining);
                    }
                }
            }
            else {
                while (this.sampleBytesRead < n) {
                    this.readToOutput(extractorInput, output, n - this.sampleBytesRead);
                }
            }
            if ("A_VORBIS".equals(webmExtractor$Track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                output.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }
    
    private void writeSubripSample(final WebmExtractor$Track webmExtractor$Track) {
        setSubripSampleEndTimecode(this.subripSample.data, this.blockDurationUs);
        webmExtractor$Track.output.sampleData(this.subripSample, this.subripSample.limit());
        this.sampleBytesWritten += this.subripSample.limit();
    }
    
    void binaryElement(final int n, int n2, final ExtractorInput extractorInput) {
        switch (n) {
            default: {
                throw new ParserException("Unexpected id: " + n);
            }
            case 21419: {
                Arrays.fill(this.seekEntryIdBytes.data, (byte)0);
                extractorInput.readFully(this.seekEntryIdBytes.data, 4 - n2, n2);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int)this.seekEntryIdBytes.readUnsignedInt();
            }
            case 25506: {
                extractorInput.readFully(this.currentTrack.codecPrivate = new byte[n2], 0, n2);
            }
            case 16981: {
                extractorInput.readFully(this.currentTrack.sampleStrippedBytes = new byte[n2], 0, n2);
            }
            case 18402: {
                extractorInput.readFully(this.currentTrack.encryptionKeyId = new byte[n2], 0, n2);
            }
            case 161:
            case 163: {
                if (this.blockState == 0) {
                    this.blockTrackNumber = (int)this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                    this.blockTrackNumberLength = this.varintReader.getLastLength();
                    this.blockDurationUs = -1L;
                    this.blockState = 1;
                    this.scratch.reset();
                }
                final WebmExtractor$Track webmExtractor$Track = (WebmExtractor$Track)this.tracks.get(this.blockTrackNumber);
                if (webmExtractor$Track == null) {
                    extractorInput.skipFully(n2 - this.blockTrackNumberLength);
                    this.blockState = 0;
                    return;
                }
                if (this.blockState == 1) {
                    this.readScratch(extractorInput, 3);
                    final int n3 = (this.scratch.data[2] & 0x6) >> 1;
                    if (n3 == 0) {
                        this.blockLacingSampleCount = 1;
                        (this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, 1))[0] = n2 - this.blockTrackNumberLength - 3;
                    }
                    else {
                        if (n != 163) {
                            throw new ParserException("Lacing only supported in SimpleBlocks.");
                        }
                        this.readScratch(extractorInput, 4);
                        this.blockLacingSampleCount = (this.scratch.data[3] & 0xFF) + 1;
                        this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, this.blockLacingSampleCount);
                        if (n3 == 2) {
                            n2 = (n2 - this.blockTrackNumberLength - 4) / this.blockLacingSampleCount;
                            Arrays.fill(this.blockLacingSampleSizes, 0, this.blockLacingSampleCount, n2);
                        }
                        else if (n3 == 1) {
                            int n4 = 0;
                            int n5 = 4;
                            for (int i = 0; i < this.blockLacingSampleCount - 1; ++i) {
                                this.blockLacingSampleSizes[i] = 0;
                                int n6 = n5;
                                int j;
                                do {
                                    n5 = n6 + 1;
                                    this.readScratch(extractorInput, n5);
                                    j = (this.scratch.data[n5 - 1] & 0xFF);
                                    final int[] blockLacingSampleSizes = this.blockLacingSampleSizes;
                                    blockLacingSampleSizes[i] += j;
                                    n6 = n5;
                                } while (j == 255);
                                n4 += this.blockLacingSampleSizes[i];
                            }
                            this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = n2 - this.blockTrackNumberLength - n5 - n4;
                        }
                        else {
                            if (n3 != 3) {
                                throw new ParserException("Unexpected lacing value: " + n3);
                            }
                            int n7 = 0;
                            int n8 = 4;
                            for (int k = 0; k < this.blockLacingSampleCount - 1; ++k) {
                                this.blockLacingSampleSizes[k] = 0;
                                final int n9 = n8 + 1;
                                this.readScratch(extractorInput, n9);
                                if (this.scratch.data[n9 - 1] == 0) {
                                    throw new ParserException("No valid varint length mask found");
                                }
                                final long n10 = 0L;
                                int n11 = 0;
                                long n12;
                                while (true) {
                                    n8 = n9;
                                    n12 = n10;
                                    if (n11 >= 8) {
                                        break;
                                    }
                                    final int n13 = 1 << 7 - n11;
                                    if ((this.scratch.data[n9 - 1] & n13) != 0x0) {
                                        final int n14 = n9 - 1;
                                        final int n15 = n9 + n11;
                                        this.readScratch(extractorInput, n15);
                                        long n16 = this.scratch.data[n14] & 0xFF & ~n13;
                                        for (int l = n14 + 1; l < n15; ++l) {
                                            n16 = ((this.scratch.data[l] & 0xFF) | n16 << 8);
                                        }
                                        n8 = n15;
                                        n12 = n16;
                                        if (k > 0) {
                                            n12 = n16 - ((1L << n11 * 7 + 6) - 1L);
                                            n8 = n15;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        ++n11;
                                    }
                                }
                                if (n12 < -2147483648L || n12 > 2147483647L) {
                                    throw new ParserException("EBML lacing sample size out of range.");
                                }
                                int n17 = (int)n12;
                                final int[] blockLacingSampleSizes2 = this.blockLacingSampleSizes;
                                if (k != 0) {
                                    n17 += this.blockLacingSampleSizes[k - 1];
                                }
                                blockLacingSampleSizes2[k] = n17;
                                n7 += this.blockLacingSampleSizes[k];
                            }
                            this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = n2 - this.blockTrackNumberLength - n8 - n7;
                        }
                    }
                    n2 = this.scratch.data[0];
                    this.blockTimeUs = this.clusterTimecodeUs + this.scaleTimecodeToUs(n2 << 8 | (this.scratch.data[1] & 0xFF));
                    if ((this.scratch.data[2] & 0x8) == 0x8) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    int n18;
                    if (webmExtractor$Track.type == 2 || (n == 163 && (this.scratch.data[2] & 0x80) == 0x80)) {
                        n18 = 1;
                    }
                    else {
                        n18 = 0;
                    }
                    boolean b;
                    if (n18 != 0) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (n2 != 0) {
                        n2 = 134217728;
                    }
                    else {
                        n2 = 0;
                    }
                    this.blockFlags = (n2 | (b ? 1 : 0));
                    this.blockState = 2;
                    this.blockLacingSampleIndex = 0;
                }
                if (n == 163) {
                    while (this.blockLacingSampleIndex < this.blockLacingSampleCount) {
                        this.writeSampleData(extractorInput, webmExtractor$Track, this.blockLacingSampleSizes[this.blockLacingSampleIndex]);
                        this.commitSampleToOutput(webmExtractor$Track, this.blockTimeUs + this.blockLacingSampleIndex * webmExtractor$Track.defaultSampleDurationNs / 1000);
                        ++this.blockLacingSampleIndex;
                    }
                    this.blockState = 0;
                    return;
                }
                this.writeSampleData(extractorInput, webmExtractor$Track, this.blockLacingSampleSizes[0]);
            }
        }
    }
    
    void endMasterElement(final int n) {
        switch (n) {
            case 357149030: {
                if (this.timecodeScale == -1L) {
                    this.timecodeScale = 1000000L;
                }
                if (this.durationTimecode != -1L) {
                    this.durationUs = this.scaleTimecodeToUs(this.durationTimecode);
                    return;
                }
                break;
            }
            case 19899: {
                if (this.seekEntryId == -1 || this.seekEntryPosition == -1L) {
                    throw new ParserException("Mandatory element SeekID or SeekPosition not found");
                }
                if (this.seekEntryId == 475249515) {
                    this.cuesContentPosition = this.seekEntryPosition;
                    return;
                }
                break;
            }
            case 475249515: {
                if (!this.sentSeekMap) {
                    this.extractorOutput.seekMap(this.buildSeekMap());
                    this.sentSeekMap = true;
                    return;
                }
                break;
            }
            case 160: {
                if (this.blockState == 2) {
                    if (!this.sampleSeenReferenceBlock) {
                        this.blockFlags |= 0x1;
                    }
                    this.commitSampleToOutput((WebmExtractor$Track)this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
                    this.blockState = 0;
                    return;
                }
                break;
            }
            case 25152: {
                if (!this.currentTrack.hasContentEncryption) {
                    break;
                }
                if (this.currentTrack.encryptionKeyId == null) {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                }
                if (!this.sentDrmInitData) {
                    this.extractorOutput.drmInitData(new DrmInitData$Universal(new DrmInitData$SchemeInitData("video/webm", this.currentTrack.encryptionKeyId)));
                    this.sentDrmInitData = true;
                    return;
                }
                break;
            }
            case 28032: {
                if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
                break;
            }
            case 174: {
                if (this.tracks.get(this.currentTrack.number) == null && isCodecSupported(this.currentTrack.codecId)) {
                    this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number, this.durationUs);
                    this.tracks.put(this.currentTrack.number, (Object)this.currentTrack);
                }
                this.currentTrack = null;
            }
            case 374648427: {
                if (this.tracks.size() == 0) {
                    throw new ParserException("No valid tracks were found");
                }
                this.extractorOutput.endTracks();
            }
        }
    }
    
    void floatElement(final int n, final double n2) {
        switch (n) {
            default: {}
            case 17545: {
                this.durationTimecode = (long)n2;
            }
            case 181: {
                this.currentTrack.sampleRate = (int)n2;
            }
        }
    }
    
    int getElementType(final int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 25152:
            case 28032:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317: {
                return 1;
            }
            case 131:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21680:
            case 21682:
            case 21690:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729: {
                return 2;
            }
            case 134:
            case 17026:
            case 2274716: {
                return 3;
            }
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506: {
                return 4;
            }
            case 181:
            case 17545: {
                return 5;
            }
        }
    }
    
    @Override
    public void init(final ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }
    
    void integerElement(final int n, final long seekPreRollNs) {
        switch (n) {
            case 17143: {
                if (seekPreRollNs != 1L) {
                    throw new ParserException("EBMLReadVersion " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 17029: {
                if (seekPreRollNs < 1L || seekPreRollNs > 2L) {
                    throw new ParserException("DocTypeReadVersion " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 21420: {
                this.seekEntryPosition = this.segmentContentPosition + seekPreRollNs;
            }
            case 2807729: {
                this.timecodeScale = seekPreRollNs;
            }
            case 176: {
                this.currentTrack.width = (int)seekPreRollNs;
            }
            case 186: {
                this.currentTrack.height = (int)seekPreRollNs;
            }
            case 21680: {
                this.currentTrack.displayWidth = (int)seekPreRollNs;
            }
            case 21690: {
                this.currentTrack.displayHeight = (int)seekPreRollNs;
            }
            case 21682: {
                this.currentTrack.displayUnit = (int)seekPreRollNs;
            }
            case 215: {
                this.currentTrack.number = (int)seekPreRollNs;
            }
            case 131: {
                this.currentTrack.type = (int)seekPreRollNs;
            }
            case 2352003: {
                this.currentTrack.defaultSampleDurationNs = (int)seekPreRollNs;
            }
            case 22186: {
                this.currentTrack.codecDelayNs = seekPreRollNs;
            }
            case 22203: {
                this.currentTrack.seekPreRollNs = seekPreRollNs;
            }
            case 159: {
                this.currentTrack.channelCount = (int)seekPreRollNs;
            }
            case 25188: {
                this.currentTrack.audioBitDepth = (int)seekPreRollNs;
            }
            case 251: {
                this.sampleSeenReferenceBlock = true;
            }
            case 20529: {
                if (seekPreRollNs != 0L) {
                    throw new ParserException("ContentEncodingOrder " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 20530: {
                if (seekPreRollNs != 1L) {
                    throw new ParserException("ContentEncodingScope " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 16980: {
                if (seekPreRollNs != 3L) {
                    throw new ParserException("ContentCompAlgo " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 18401: {
                if (seekPreRollNs != 5L) {
                    throw new ParserException("ContentEncAlgo " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 18408: {
                if (seekPreRollNs != 1L) {
                    throw new ParserException("AESSettingsCipherMode " + seekPreRollNs + " not supported");
                }
                break;
            }
            case 179: {
                this.cueTimesUs.add(this.scaleTimecodeToUs(seekPreRollNs));
            }
            case 241: {
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(seekPreRollNs);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                break;
            }
            case 231: {
                this.clusterTimecodeUs = this.scaleTimecodeToUs(seekPreRollNs);
            }
            case 155: {
                this.blockDurationUs = this.scaleTimecodeToUs(seekPreRollNs);
            }
        }
    }
    
    boolean isLevel1Element(final int n) {
        return n == 357149030 || n == 524531317 || n == 475249515 || n == 374648427;
    }
    
    @Override
    public int read(final ExtractorInput extractorInput, final PositionHolder positionHolder) {
        boolean b = false;
        this.sampleRead = false;
        boolean read = true;
        while (read && !this.sampleRead) {
            final boolean b2 = read = this.reader.read(extractorInput);
            if (b2) {
                read = b2;
                if (this.maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                    b = true;
                    return b ? 1 : 0;
                }
                continue;
            }
        }
        if (!read) {
            return -1;
        }
        return b ? 1 : 0;
    }
    
    @Override
    public void seek() {
        this.clusterTimecodeUs = -1L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        this.resetSample();
    }
    
    void startMasterElement(final int n, final long segmentContentPosition, final long segmentContentSize) {
        switch (n) {
            case 408125543: {
                if (this.segmentContentPosition != -1L && this.segmentContentPosition != segmentContentPosition) {
                    throw new ParserException("Multiple Segment elements not supported");
                }
                this.segmentContentPosition = segmentContentPosition;
                this.segmentContentSize = segmentContentSize;
            }
            case 19899: {
                this.seekEntryId = -1;
                this.seekEntryPosition = -1L;
            }
            case 475249515: {
                this.cueTimesUs = new LongArray();
                this.cueClusterPositions = new LongArray();
            }
            case 187: {
                this.seenClusterPositionForCurrentCuePoint = false;
            }
            case 524531317: {
                if (this.sentSeekMap) {
                    break;
                }
                if (this.cuesContentPosition != -1L) {
                    this.seekForCues = true;
                    return;
                }
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.sentSeekMap = true;
            }
            case 160: {
                this.sampleSeenReferenceBlock = false;
            }
            case 20533: {
                this.currentTrack.hasContentEncryption = true;
            }
            case 174: {
                this.currentTrack = new WebmExtractor$Track(null);
            }
        }
    }
    
    void stringElement(final int n, final String codecId) {
        switch (n) {
            case 17026: {
                if (!"webm".equals(codecId) && !"matroska".equals(codecId)) {
                    throw new ParserException("DocType " + codecId + " not supported");
                }
                break;
            }
            case 134: {
                this.currentTrack.codecId = codecId;
            }
            case 2274716: {
                this.currentTrack.language = codecId;
            }
        }
    }
}
