// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.DrmInitData$Universal;
import com.google.android.exoplayer.drm.DrmInitData$SchemeInitData;
import java.util.Locale;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.SeekMap;
import android.util.SparseArray;
import com.google.android.exoplayer.util.LongArray;
import java.util.UUID;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import java.util.Collections;
import java.util.Arrays;
import com.google.android.exoplayer.util.NalUnitUtil;
import java.util.ArrayList;
import com.google.android.exoplayer.ParserException;
import java.util.List;
import android.util.Pair;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.extractor.TrackOutput;

final class WebmExtractor$Track
{
    public int audioBitDepth;
    public int channelCount;
    public long codecDelayNs;
    public String codecId;
    public byte[] codecPrivate;
    public int defaultSampleDurationNs;
    public int displayHeight;
    public int displayUnit;
    public int displayWidth;
    public byte[] encryptionKeyId;
    public boolean hasContentEncryption;
    public int height;
    private String language;
    public int nalUnitLengthFieldLength;
    public int number;
    public TrackOutput output;
    public int sampleRate;
    public byte[] sampleStrippedBytes;
    public long seekPreRollNs;
    public int type;
    public int width;
    
    private WebmExtractor$Track() {
        this.width = -1;
        this.height = -1;
        this.displayWidth = -1;
        this.displayHeight = -1;
        this.displayUnit = 0;
        this.channelCount = 1;
        this.audioBitDepth = -1;
        this.sampleRate = 8000;
        this.codecDelayNs = 0L;
        this.seekPreRollNs = 0L;
        this.language = "eng";
    }
    
    private static Pair<List<byte[]>, Integer> parseAvcCodecPrivate(final ParsableByteArray parsableByteArray) {
        final int n = 0;
        int n2;
        try {
            parsableByteArray.setPosition(4);
            n2 = (parsableByteArray.readUnsignedByte() & 0x3) + 1;
            if (n2 == 3) {
                throw new ParserException();
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException("Error parsing AVC codec private");
        }
        final ArrayList<byte[]> list = new ArrayList<byte[]>();
        for (int unsignedByte = parsableByteArray.readUnsignedByte(), i = 0; i < (unsignedByte & 0x1F); ++i) {
            list.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        for (int unsignedByte2 = parsableByteArray.readUnsignedByte(), j = n; j < unsignedByte2; ++j) {
            list.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        return (Pair<List<byte[]>, Integer>)Pair.create((Object)list, (Object)n2);
    }
    
    private static List<byte[]> parseFourCcVc1Private(final ParsableByteArray parsableByteArray) {
        try {
            parsableByteArray.skipBytes(16);
            final long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
            if (littleEndianUnsignedInt != 826496599L) {
                throw new ParserException("Unsupported FourCC compression type: " + littleEndianUnsignedInt);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException("Error parsing FourCC VC1 codec private");
        }
        int i = parsableByteArray.getPosition() + 20;
        for (byte[] data = parsableByteArray.data; i < data.length - 4; ++i) {
            if (data[i] == 0 && data[i + 1] == 0 && data[i + 2] == 1 && data[i + 3] == 15) {
                return Collections.singletonList(Arrays.copyOfRange(data, i, data.length));
            }
        }
        throw new ParserException("Failed to find FourCC VC1 initialization data");
    }
    
    private static Pair<List<byte[]>, Integer> parseHevcCodecPrivate(final ParsableByteArray parsableByteArray) {
        int unsignedByte;
        int unsignedByte2;
        int position;
        int n = 0;
        int n2 = 0;
        int unsignedShort2;
        byte[] array;
        int n3 = 0;
        int n4;
        int unsignedShort4;
        int n5;
        List<byte[]> singletonList;
        Label_0094_Outer:Label_0198_Outer:
        while (true) {
        Label_0184_Outer:
            while (true) {
                while (true) {
                Label_0232:
                    while (true) {
                    Label_0225:
                        while (true) {
                            Label_0218: {
                                try {
                                    parsableByteArray.setPosition(21);
                                    unsignedByte = parsableByteArray.readUnsignedByte();
                                    unsignedByte2 = parsableByteArray.readUnsignedByte();
                                    position = parsableByteArray.getPosition();
                                    n = 0;
                                    n2 = 0;
                                    if (n < unsignedByte2) {
                                        parsableByteArray.skipBytes(1);
                                        for (int unsignedShort = parsableByteArray.readUnsignedShort(), i = 0; i < unsignedShort; ++i) {
                                            unsignedShort2 = parsableByteArray.readUnsignedShort();
                                            n2 += unsignedShort2 + 4;
                                            parsableByteArray.skipBytes(unsignedShort2);
                                        }
                                        break Label_0218;
                                    }
                                    parsableByteArray.setPosition(position);
                                    array = new byte[n2];
                                    n3 = 0;
                                    n4 = 0;
                                    if (n3 < unsignedByte2) {
                                        parsableByteArray.skipBytes(1);
                                        for (int unsignedShort3 = parsableByteArray.readUnsignedShort(), j = 0; j < unsignedShort3; ++j) {
                                            unsignedShort4 = parsableByteArray.readUnsignedShort();
                                            System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, array, n4, NalUnitUtil.NAL_START_CODE.length);
                                            n5 = n4 + NalUnitUtil.NAL_START_CODE.length;
                                            System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), array, n5, unsignedShort4);
                                            n4 = n5 + unsignedShort4;
                                            parsableByteArray.skipBytes(unsignedShort4);
                                        }
                                        break Label_0225;
                                    }
                                    break Label_0232;
                                    singletonList = Collections.singletonList(array);
                                    return (Pair<List<byte[]>, Integer>)Pair.create((Object)singletonList, (Object)((unsignedByte & 0x3) + 1));
                                }
                                catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new ParserException("Error parsing HEVC codec private");
                                }
                            }
                            ++n;
                            continue Label_0094_Outer;
                        }
                        ++n3;
                        continue Label_0198_Outer;
                    }
                    if (n2 == 0) {
                        singletonList = null;
                        continue;
                    }
                    break;
                }
                continue Label_0184_Outer;
            }
        }
    }
    
    private static boolean parseMsAcmCodecPrivate(final ParsableByteArray parsableByteArray) {
        try {
            final int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
            if (littleEndianUnsignedShort == 1) {
                return true;
            }
            if (littleEndianUnsignedShort != 65534) {
                return false;
            }
            parsableByteArray.setPosition(24);
            if (parsableByteArray.readLong() != WebmExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() || parsableByteArray.readLong() != WebmExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException("Error parsing MS/ACM codec private");
        }
        return true;
    }
    
    private static List<byte[]> parseVorbisCodecPrivate(final byte[] array) {
        final byte b = 0;
        if (array[0] != 2) {
            try {
                throw new ParserException("Error parsing vorbis codec private");
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }
        int n;
        int n2;
        for (n = 0, n2 = 1; array[n2] == -1; ++n2, n += 255) {}
        final int n3 = n2 + 1;
        final byte b2 = (byte)(n + array[n2]);
        int n4 = n3;
        int n5 = b;
        while (array[n4] == -1) {
            n5 += 255;
            ++n4;
        }
        final int n6 = n4 + 1;
        final byte b3 = array[n4];
        if (array[n6] != 1) {
            throw new ParserException("Error parsing vorbis codec private");
        }
        final byte[] array2 = new byte[b2];
        System.arraycopy(array, n6, array2, 0, b2);
        final byte b4 = (byte)(b2 + n6);
        if (array[b4] != 3) {
            throw new ParserException("Error parsing vorbis codec private");
        }
        final byte b5 = (byte)(n5 + b3 + b4);
        if (array[b5] != 5) {
            throw new ParserException("Error parsing vorbis codec private");
        }
        final byte[] array3 = new byte[array.length - b5];
        System.arraycopy(array, b5, array3, 0, array.length - b5);
        final ArrayList<byte[]> list = new ArrayList<byte[]>(2);
        list.add(array2);
        list.add(array3);
        return list;
    }
    
    public void initializeOutput(final ExtractorOutput extractorOutput, final int n, final long n2) {
        List<byte[]> singletonList = null;
        final String codecId = this.codecId;
        int n3 = 0;
        Label_0227: {
            switch (codecId.hashCode()) {
                case 82338133: {
                    if (codecId.equals("V_VP8")) {
                        n3 = 0;
                        break Label_0227;
                    }
                    break;
                }
                case 82338134: {
                    if (codecId.equals("V_VP9")) {
                        n3 = 1;
                        break Label_0227;
                    }
                    break;
                }
                case 1809237540: {
                    if (codecId.equals("V_MPEG2")) {
                        n3 = 2;
                        break Label_0227;
                    }
                    break;
                }
                case -2095575984: {
                    if (codecId.equals("V_MPEG4/ISO/SP")) {
                        n3 = 3;
                        break Label_0227;
                    }
                    break;
                }
                case -538363189: {
                    if (codecId.equals("V_MPEG4/ISO/ASP")) {
                        n3 = 4;
                        break Label_0227;
                    }
                    break;
                }
                case -2095576542: {
                    if (codecId.equals("V_MPEG4/ISO/AP")) {
                        n3 = 5;
                        break Label_0227;
                    }
                    break;
                }
                case -538363109: {
                    if (codecId.equals("V_MPEG4/ISO/AVC")) {
                        n3 = 6;
                        break Label_0227;
                    }
                    break;
                }
                case 855502857: {
                    if (codecId.equals("V_MPEGH/ISO/HEVC")) {
                        n3 = 7;
                        break Label_0227;
                    }
                    break;
                }
                case -1373388978: {
                    if (codecId.equals("V_MS/VFW/FOURCC")) {
                        n3 = 8;
                        break Label_0227;
                    }
                    break;
                }
                case -1730367663: {
                    if (codecId.equals("A_VORBIS")) {
                        n3 = 9;
                        break Label_0227;
                    }
                    break;
                }
                case 1951062397: {
                    if (codecId.equals("A_OPUS")) {
                        n3 = 10;
                        break Label_0227;
                    }
                    break;
                }
                case 62923557: {
                    if (codecId.equals("A_AAC")) {
                        n3 = 11;
                        break Label_0227;
                    }
                    break;
                }
                case -1482641357: {
                    if (codecId.equals("A_MPEG/L3")) {
                        n3 = 12;
                        break Label_0227;
                    }
                    break;
                }
                case 62923603: {
                    if (codecId.equals("A_AC3")) {
                        n3 = 13;
                        break Label_0227;
                    }
                    break;
                }
                case 1950749482: {
                    if (codecId.equals("A_EAC3")) {
                        n3 = 14;
                        break Label_0227;
                    }
                    break;
                }
                case -1784763192: {
                    if (codecId.equals("A_TRUEHD")) {
                        n3 = 15;
                        break Label_0227;
                    }
                    break;
                }
                case 62927045: {
                    if (codecId.equals("A_DTS")) {
                        n3 = 16;
                        break Label_0227;
                    }
                    break;
                }
                case 542569478: {
                    if (codecId.equals("A_DTS/EXPRESS")) {
                        n3 = 17;
                        break Label_0227;
                    }
                    break;
                }
                case -356037306: {
                    if (codecId.equals("A_DTS/LOSSLESS")) {
                        n3 = 18;
                        break Label_0227;
                    }
                    break;
                }
                case 1950789798: {
                    if (codecId.equals("A_FLAC")) {
                        n3 = 19;
                        break Label_0227;
                    }
                    break;
                }
                case -1985379776: {
                    if (codecId.equals("A_MS/ACM")) {
                        n3 = 20;
                        break Label_0227;
                    }
                    break;
                }
                case 725957860: {
                    if (codecId.equals("A_PCM/INT/LIT")) {
                        n3 = 21;
                        break Label_0227;
                    }
                    break;
                }
                case 1422270023: {
                    if (codecId.equals("S_TEXT/UTF8")) {
                        n3 = 22;
                        break Label_0227;
                    }
                    break;
                }
                case -425012669: {
                    if (codecId.equals("S_VOBSUB")) {
                        n3 = 23;
                        break Label_0227;
                    }
                    break;
                }
                case 99146302: {
                    if (codecId.equals("S_HDMV/PGS")) {
                        n3 = 24;
                        break Label_0227;
                    }
                    break;
                }
            }
            n3 = -1;
        }
        while (true) {
            Label_1774: {
                String s = null;
                List<byte[]> list = null;
                int n4 = 0;
                int n5 = 0;
                switch (n3) {
                    default: {
                        throw new ParserException("Unrecognized codec identifier.");
                    }
                    case 0: {
                        s = "video/x-vnd.on2.vp8";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 1: {
                        s = "video/x-vnd.on2.vp9";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 2: {
                        s = "video/mpeg2";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        final String s2 = "video/mp4v-es";
                        if (this.codecPrivate != null) {
                            singletonList = Collections.singletonList(this.codecPrivate);
                        }
                        list = singletonList;
                        n4 = -1;
                        n5 = -1;
                        s = s2;
                        break;
                    }
                    case 6: {
                        final Pair<List<byte[]>, Integer> avcCodecPrivate = parseAvcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                        list = (List<byte[]>)avcCodecPrivate.first;
                        this.nalUnitLengthFieldLength = (int)avcCodecPrivate.second;
                        n4 = -1;
                        s = "video/avc";
                        n5 = -1;
                        break;
                    }
                    case 7: {
                        final Pair<List<byte[]>, Integer> hevcCodecPrivate = parseHevcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                        list = (List<byte[]>)hevcCodecPrivate.first;
                        this.nalUnitLengthFieldLength = (int)hevcCodecPrivate.second;
                        n4 = -1;
                        s = "video/hevc";
                        n5 = -1;
                        break;
                    }
                    case 8: {
                        s = "video/wvc1";
                        list = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 9: {
                        s = "audio/vorbis";
                        n5 = 8192;
                        list = parseVorbisCodecPrivate(this.codecPrivate);
                        n4 = -1;
                        break;
                    }
                    case 10: {
                        s = "audio/opus";
                        n5 = 5760;
                        list = new ArrayList<byte[]>(3);
                        list.add(this.codecPrivate);
                        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                        n4 = -1;
                        break;
                    }
                    case 11: {
                        s = "audio/mp4a-latm";
                        list = Collections.singletonList(this.codecPrivate);
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 12: {
                        s = "audio/mpeg";
                        n5 = 4096;
                        list = null;
                        n4 = -1;
                        break;
                    }
                    case 13: {
                        s = "audio/ac3";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 14: {
                        s = "audio/eac3";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 15: {
                        s = "audio/true-hd";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 16:
                    case 17: {
                        s = "audio/vnd.dts";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 18: {
                        s = "audio/vnd.dts.hd";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 19: {
                        s = "audio/x-flac";
                        list = Collections.singletonList(this.codecPrivate);
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 20: {
                        if (!parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                            throw new ParserException("Non-PCM MS/ACM is unsupported");
                        }
                        if ((n4 = Util.getPcmEncoding(this.audioBitDepth)) == 0) {
                            throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                        }
                        break Label_1774;
                    }
                    case 21: {
                        if ((n4 = Util.getPcmEncoding(this.audioBitDepth)) == 0) {
                            throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                        }
                        break Label_1774;
                    }
                    case 22: {
                        s = "application/x-subrip";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 23: {
                        s = "application/vobsub";
                        list = Collections.singletonList(this.codecPrivate);
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                    case 24: {
                        s = "application/pgs";
                        list = null;
                        n4 = -1;
                        n5 = -1;
                        break;
                    }
                }
                MediaFormat mediaFormat;
                if (MimeTypes.isAudio(s)) {
                    mediaFormat = MediaFormat.createAudioFormat(Integer.toString(n), s, -1, n5, n2, this.channelCount, this.sampleRate, list, this.language, n4);
                }
                else if (MimeTypes.isVideo(s)) {
                    if (this.displayUnit == 0) {
                        int displayWidth;
                        if (this.displayWidth == -1) {
                            displayWidth = this.width;
                        }
                        else {
                            displayWidth = this.displayWidth;
                        }
                        this.displayWidth = displayWidth;
                        int displayHeight;
                        if (this.displayHeight == -1) {
                            displayHeight = this.height;
                        }
                        else {
                            displayHeight = this.displayHeight;
                        }
                        this.displayHeight = displayHeight;
                    }
                    float n6 = -1.0f;
                    if (this.displayWidth != -1) {
                        n6 = n6;
                        if (this.displayHeight != -1) {
                            n6 = this.height * this.displayWidth / (this.width * this.displayHeight);
                        }
                    }
                    mediaFormat = MediaFormat.createVideoFormat(Integer.toString(n), s, -1, n5, n2, this.width, this.height, list, -1, n6);
                }
                else if ("application/x-subrip".equals(s)) {
                    mediaFormat = MediaFormat.createTextFormat(Integer.toString(n), s, -1, n2, this.language);
                }
                else {
                    if (!"application/vobsub".equals(s) && !"application/pgs".equals(s)) {
                        throw new ParserException("Unexpected MIME type.");
                    }
                    mediaFormat = MediaFormat.createImageFormat(Integer.toString(n), s, -1, n2, list, this.language);
                }
                (this.output = extractorOutput.track(this.number)).format(mediaFormat);
                return;
            }
            String s = "audio/raw";
            List<byte[]> list = null;
            int n5 = -1;
            continue;
        }
    }
}
