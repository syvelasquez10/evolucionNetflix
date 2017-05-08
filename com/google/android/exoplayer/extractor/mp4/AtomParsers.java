// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import java.util.Arrays;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.NalUnitUtil;
import java.util.ArrayList;
import android.util.Pair;
import java.util.Collections;
import java.util.List;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;

final class AtomParsers
{
    private static final int TYPE_cenc;
    
    static {
        TYPE_cenc = Util.getIntegerCodeForString("cenc");
    }
    
    private static int findEsdsPosition(final ParsableByteArray parsableByteArray, final int n, final int n2) {
        int int1;
        for (int position = parsableByteArray.getPosition(); position - n < n2; position += int1) {
            parsableByteArray.setPosition(position);
            int1 = parsableByteArray.readInt();
            Assertions.checkArgument(int1 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
        }
        return -1;
    }
    
    private static void parseAudioSampleEntry(final ParsableByteArray parsableByteArray, int intValue, int n, final int n2, final int n3, final long n4, final String s, final boolean b, final AtomParsers$StsdData atomParsers$StsdData, int position) {
        parsableByteArray.setPosition(n + 8);
        int unsignedShort;
        if (b) {
            parsableByteArray.skipBytes(8);
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        }
        else {
            parsableByteArray.skipBytes(16);
            unsignedShort = 0;
        }
        int unsignedFixedPoint1616;
        int unsignedIntToInt;
        if (unsignedShort == 0 || unsignedShort == 1) {
            final int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            unsignedIntToInt = unsignedShort2;
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
                unsignedIntToInt = unsignedShort2;
                unsignedFixedPoint1616 = unsignedFixedPoint1616;
            }
        }
        else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int)Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        final int position2 = parsableByteArray.getPosition();
        int sampleEntryEncryptionData;
        if ((sampleEntryEncryptionData = intValue) == Atom.TYPE_enca) {
            sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, n, n2, atomParsers$StsdData, position);
            parsableByteArray.setPosition(position2);
        }
        String s2 = null;
        if (sampleEntryEncryptionData == Atom.TYPE_ac_3) {
            s2 = "audio/ac3";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_ec_3) {
            s2 = "audio/eac3";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_dtsc) {
            s2 = "audio/vnd.dts";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_dtsh || sampleEntryEncryptionData == Atom.TYPE_dtsl) {
            s2 = "audio/vnd.dts.hd";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_dtse) {
            s2 = "audio/vnd.dts.hd;profile=lbr";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_samr) {
            s2 = "audio/3gpp";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_sawb) {
            s2 = "audio/amr-wb";
        }
        else if (sampleEntryEncryptionData == Atom.TYPE_lpcm || sampleEntryEncryptionData == Atom.TYPE_sowt) {
            s2 = "audio/raw";
        }
        byte[] array = null;
        intValue = unsignedFixedPoint1616;
        int intValue2 = unsignedIntToInt;
        int int1;
        int int2;
        int esdsPosition;
        Pair<String, byte[]> esdsFromParent;
        String s3;
        byte[] array2;
        Pair<Integer, Integer> aacAudioSpecificConfig;
        byte[] array3;
        int n5;
        int n6;
        String s4;
        for (position = position2; position - n < n2; position += int1, s2 = s4, intValue2 = n6, intValue = n5, array = array3) {
            parsableByteArray.setPosition(position);
            int1 = parsableByteArray.readInt();
            Assertions.checkArgument(int1 > 0, "childAtomSize should be positive");
            int2 = parsableByteArray.readInt();
            if (int2 == Atom.TYPE_esds || (b && int2 == Atom.TYPE_wave)) {
                if (int2 == Atom.TYPE_esds) {
                    esdsPosition = position;
                }
                else {
                    esdsPosition = findEsdsPosition(parsableByteArray, position, int1);
                }
                if (esdsPosition != -1) {
                    esdsFromParent = parseEsdsFromParent(parsableByteArray, esdsPosition);
                    s3 = (String)esdsFromParent.first;
                    array2 = (byte[])esdsFromParent.second;
                    s2 = s3;
                    array = array2;
                    if ("audio/mp4a-latm".equals(s3)) {
                        aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(array2);
                        intValue = (int)aacAudioSpecificConfig.first;
                        intValue2 = (int)aacAudioSpecificConfig.second;
                        array = array2;
                        s2 = s3;
                    }
                }
                array3 = array;
                n5 = intValue;
                n6 = intValue2;
                s4 = s2;
            }
            else if (int2 == Atom.TYPE_dac3) {
                parsableByteArray.setPosition(position + 8);
                atomParsers$StsdData.mediaFormat = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(n3), n4, s);
                s4 = s2;
                n6 = intValue2;
                n5 = intValue;
                array3 = array;
            }
            else if (int2 == Atom.TYPE_dec3) {
                parsableByteArray.setPosition(position + 8);
                atomParsers$StsdData.mediaFormat = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(n3), n4, s);
                s4 = s2;
                n6 = intValue2;
                n5 = intValue;
                array3 = array;
            }
            else {
                s4 = s2;
                n6 = intValue2;
                n5 = intValue;
                array3 = array;
                if (int2 == Atom.TYPE_ddts) {
                    atomParsers$StsdData.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(n3), s2, -1, -1, n4, intValue2, intValue, null, s);
                    s4 = s2;
                    n6 = intValue2;
                    n5 = intValue;
                    array3 = array;
                }
            }
        }
        if (atomParsers$StsdData.mediaFormat == null && s2 != null) {
            if ("audio/raw".equals(s2)) {
                n = 2;
            }
            else {
                n = -1;
            }
            final String string = Integer.toString(n3);
            List<byte[]> singletonList;
            if (array == null) {
                singletonList = null;
            }
            else {
                singletonList = Collections.singletonList(array);
            }
            atomParsers$StsdData.mediaFormat = MediaFormat.createAudioFormat(string, s2, -1, -1, n4, intValue2, intValue, singletonList, s, n);
        }
    }
    
    private static AtomParsers$AvcCData parseAvcCFromParent(final ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        final int n = (parsableByteArray.readUnsignedByte() & 0x3) + 1;
        if (n == 3) {
            throw new IllegalStateException();
        }
        final ArrayList<byte[]> list = new ArrayList<byte[]>();
        float pixelWidthAspectRatio = 1.0f;
        int n2;
        for (n2 = (parsableByteArray.readUnsignedByte() & 0x1F), i = 0; i < n2; ++i) {
            list.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        int unsignedByte;
        for (unsignedByte = parsableByteArray.readUnsignedByte(), i = 0; i < unsignedByte; ++i) {
            list.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        if (n2 > 0) {
            final ParsableBitArray parsableBitArray = new ParsableBitArray(list.get(0));
            parsableBitArray.setPosition((n + 1) * 8);
            pixelWidthAspectRatio = NalUnitUtil.parseSpsNalUnit(parsableBitArray).pixelWidthAspectRatio;
        }
        return new AtomParsers$AvcCData(list, n, pixelWidthAspectRatio);
    }
    
    private static Pair<long[], long[]> parseEdts(final Atom$ContainerAtom atom$ContainerAtom) {
        if (atom$ContainerAtom != null) {
            final Atom$LeafAtom leafAtomOfType = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (leafAtomOfType != null) {
                final ParsableByteArray data = leafAtomOfType.data;
                data.setPosition(8);
                final int fullAtomVersion = Atom.parseFullAtomVersion(data.readInt());
                final int unsignedIntToInt = data.readUnsignedIntToInt();
                final long[] array = new long[unsignedIntToInt];
                final long[] array2 = new long[unsignedIntToInt];
                for (int i = 0; i < unsignedIntToInt; ++i) {
                    long n;
                    if (fullAtomVersion == 1) {
                        n = data.readUnsignedLongToLong();
                    }
                    else {
                        n = data.readUnsignedInt();
                    }
                    array[i] = n;
                    long long1;
                    if (fullAtomVersion == 1) {
                        long1 = data.readLong();
                    }
                    else {
                        long1 = data.readInt();
                    }
                    array2[i] = long1;
                    if (data.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    data.skipBytes(2);
                }
                return (Pair<long[], long[]>)Pair.create((Object)array, (Object)array2);
            }
        }
        return (Pair<long[], long[]>)Pair.create((Object)null, (Object)null);
    }
    
    private static Pair<String, byte[]> parseEsdsFromParent(final ParsableByteArray parsableByteArray, int n) {
        Object o = null;
        parsableByteArray.setPosition(n + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        n = parsableByteArray.readUnsignedByte();
        if ((n & 0x80) != 0x0) {
            parsableByteArray.skipBytes(2);
        }
        if ((n & 0x40) != 0x0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((n & 0x20) != 0x0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        switch (parsableByteArray.readUnsignedByte()) {
            case 107: {
                return (Pair<String, byte[]>)Pair.create((Object)"audio/mpeg", (Object)null);
            }
            case 32: {
                o = "video/mp4v-es";
                break;
            }
            case 33: {
                o = "video/avc";
                break;
            }
            case 35: {
                o = "video/hevc";
                break;
            }
            case 64:
            case 102:
            case 103:
            case 104: {
                o = "audio/mp4a-latm";
                break;
            }
            case 165: {
                o = "audio/ac3";
                break;
            }
            case 166: {
                o = "audio/eac3";
                break;
            }
            case 169:
            case 172: {
                return (Pair<String, byte[]>)Pair.create((Object)"audio/vnd.dts", (Object)null);
            }
            case 170:
            case 171: {
                return (Pair<String, byte[]>)Pair.create((Object)"audio/vnd.dts.hd", (Object)null);
            }
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        n = parseExpandableClassSize(parsableByteArray);
        final byte[] array = new byte[n];
        parsableByteArray.readBytes(array, 0, n);
        return (Pair<String, byte[]>)Pair.create(o, (Object)array);
    }
    
    private static int parseExpandableClassSize(final ParsableByteArray parsableByteArray) {
        int n;
        int n2;
        for (n = parsableByteArray.readUnsignedByte(), n2 = (n & 0x7F); (n & 0x80) == 0x80; n = parsableByteArray.readUnsignedByte(), n2 = (n2 << 7 | (n & 0x7F))) {}
        return n2;
    }
    
    private static int parseHdlr(final ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }
    
    private static Pair<List<byte[]>, Integer> parseHvcCFromParent(final ParsableByteArray parsableByteArray, int n) {
        parsableByteArray.setPosition(n + 8 + 21);
        final int unsignedByte = parsableByteArray.readUnsignedByte();
        final int unsignedByte2 = parsableByteArray.readUnsignedByte();
        final int position = parsableByteArray.getPosition();
        int i = 0;
        n = 0;
        while (i < unsignedByte2) {
            parsableByteArray.skipBytes(1);
            for (int unsignedShort = parsableByteArray.readUnsignedShort(), j = 0; j < unsignedShort; ++j) {
                final int unsignedShort2 = parsableByteArray.readUnsignedShort();
                n += unsignedShort2 + 4;
                parsableByteArray.skipBytes(unsignedShort2);
            }
            ++i;
        }
        parsableByteArray.setPosition(position);
        final byte[] array = new byte[n];
        int k = 0;
        int n2 = 0;
        while (k < unsignedByte2) {
            parsableByteArray.skipBytes(1);
            for (int unsignedShort3 = parsableByteArray.readUnsignedShort(), l = 0; l < unsignedShort3; ++l) {
                final int unsignedShort4 = parsableByteArray.readUnsignedShort();
                System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, array, n2, NalUnitUtil.NAL_START_CODE.length);
                final int n3 = n2 + NalUnitUtil.NAL_START_CODE.length;
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), array, n3, unsignedShort4);
                n2 = n3 + unsignedShort4;
                parsableByteArray.skipBytes(unsignedShort4);
            }
            ++k;
        }
        Object singletonList;
        if (n == 0) {
            singletonList = null;
        }
        else {
            singletonList = Collections.singletonList(array);
        }
        return (Pair<List<byte[]>, Integer>)Pair.create(singletonList, (Object)((unsignedByte & 0x3) + 1));
    }
    
    private static Pair<Long, String> parseMdhd(final ParsableByteArray parsableByteArray) {
        final int n = 8;
        parsableByteArray.setPosition(8);
        final int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int n2;
        if (fullAtomVersion == 0) {
            n2 = 8;
        }
        else {
            n2 = 16;
        }
        parsableByteArray.skipBytes(n2);
        final long unsignedInt = parsableByteArray.readUnsignedInt();
        int n3 = n;
        if (fullAtomVersion == 0) {
            n3 = 4;
        }
        parsableByteArray.skipBytes(n3);
        final int unsignedShort = parsableByteArray.readUnsignedShort();
        return (Pair<Long, String>)Pair.create((Object)unsignedInt, (Object)("" + (char)((unsignedShort >> 10 & 0x1F) + 96) + (char)((unsignedShort >> 5 & 0x1F) + 96) + (char)((unsignedShort & 0x1F) + 96)));
    }
    
    private static long parseMvhd(final ParsableByteArray parsableByteArray) {
        int n = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            n = 16;
        }
        parsableByteArray.skipBytes(n);
        return parsableByteArray.readUnsignedInt();
    }
    
    private static float parsePaspFromParent(final ParsableByteArray parsableByteArray, int unsignedIntToInt) {
        parsableByteArray.setPosition(unsignedIntToInt + 8);
        unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        return unsignedIntToInt / parsableByteArray.readUnsignedIntToInt();
    }
    
    private static byte[] parseProjFromParent(final ParsableByteArray parsableByteArray, final int n, final int n2) {
        int int1;
        for (int position = n + 8; position - n < n2; position += int1) {
            parsableByteArray.setPosition(position);
            int1 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, position, int1 + position);
            }
        }
        return null;
    }
    
    private static int parseSampleEntryEncryptionData(final ParsableByteArray parsableByteArray, final int n, final int n2, final AtomParsers$StsdData atomParsers$StsdData, final int n3) {
        final boolean b = false;
        int position = parsableByteArray.getPosition();
        int intValue;
        while (true) {
            intValue = (b ? 1 : 0);
            if (position - n >= n2) {
                break;
            }
            parsableByteArray.setPosition(position);
            final int int1 = parsableByteArray.readInt();
            Assertions.checkArgument(int1 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf) {
                final Pair<Integer, TrackEncryptionBox> sinfFromParent = parseSinfFromParent(parsableByteArray, position, int1);
                if (sinfFromParent != null) {
                    atomParsers$StsdData.trackEncryptionBoxes[n3] = (TrackEncryptionBox)sinfFromParent.second;
                    intValue = (int)sinfFromParent.first;
                    break;
                }
            }
            position += int1;
        }
        return intValue;
    }
    
    private static TrackEncryptionBox parseSchiFromParent(final ParsableByteArray parsableByteArray, int unsignedByte, final int n) {
        boolean b = true;
        int int1;
        for (int position = unsignedByte + 8; position - unsignedByte < n; position += int1) {
            parsableByteArray.setPosition(position);
            int1 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                parsableByteArray.skipBytes(6);
                if (parsableByteArray.readUnsignedByte() != 1) {
                    b = false;
                }
                unsignedByte = parsableByteArray.readUnsignedByte();
                final byte[] array = new byte[16];
                parsableByteArray.readBytes(array, 0, array.length);
                return new TrackEncryptionBox(b, unsignedByte, array);
            }
        }
        return null;
    }
    
    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(final ParsableByteArray parsableByteArray, final int n, final int n2) {
        final boolean b = true;
        Object schiFromParent = null;
        int n3 = 0;
        int position = n + 8;
        Integer n4 = null;
        while (position - n < n2) {
            parsableByteArray.setPosition(position);
            final int int1 = parsableByteArray.readInt();
            final int int2 = parsableByteArray.readInt();
            Integer value;
            int n5;
            if (int2 == Atom.TYPE_frma) {
                value = parsableByteArray.readInt();
                n5 = n3;
            }
            else if (int2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() == AtomParsers.TYPE_cenc) {
                    n5 = 1;
                    value = n4;
                }
                else {
                    n5 = 0;
                    value = n4;
                }
            }
            else {
                value = n4;
                n5 = n3;
                if (int2 == Atom.TYPE_schi) {
                    schiFromParent = parseSchiFromParent(parsableByteArray, position, int1);
                    value = n4;
                    n5 = n3;
                }
            }
            position += int1;
            n4 = value;
            n3 = n5;
        }
        if (n3 != 0) {
            Assertions.checkArgument(n4 != null, "frma atom is mandatory");
            Assertions.checkArgument(schiFromParent != null && b, "schi->tenc atom is mandatory");
            return (Pair<Integer, TrackEncryptionBox>)Pair.create((Object)n4, schiFromParent);
        }
        return null;
    }
    
    private static AtomParsers$StsdData parseStsd(final ParsableByteArray parsableByteArray, final int n, final long n2, final int n3, final String s, final boolean b) {
        parsableByteArray.setPosition(12);
        final int int1 = parsableByteArray.readInt();
        final AtomParsers$StsdData atomParsers$StsdData = new AtomParsers$StsdData(int1);
        for (int i = 0; i < int1; ++i) {
            final int position = parsableByteArray.getPosition();
            final int int2 = parsableByteArray.readInt();
            Assertions.checkArgument(int2 > 0, "childAtomSize should be positive");
            final int int3 = parsableByteArray.readInt();
            if (int3 == Atom.TYPE_avc1 || int3 == Atom.TYPE_avc3 || int3 == Atom.TYPE_encv || int3 == Atom.TYPE_mp4v || int3 == Atom.TYPE_hvc1 || int3 == Atom.TYPE_hev1 || int3 == Atom.TYPE_s263 || int3 == Atom.TYPE_vp08 || int3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, int3, position, int2, n, n2, n3, atomParsers$StsdData, i);
            }
            else if (int3 == Atom.TYPE_mp4a || int3 == Atom.TYPE_enca || int3 == Atom.TYPE_ac_3 || int3 == Atom.TYPE_ec_3 || int3 == Atom.TYPE_dtsc || int3 == Atom.TYPE_dtse || int3 == Atom.TYPE_dtsh || int3 == Atom.TYPE_dtsl || int3 == Atom.TYPE_samr || int3 == Atom.TYPE_sawb || int3 == Atom.TYPE_lpcm || int3 == Atom.TYPE_sowt) {
                parseAudioSampleEntry(parsableByteArray, int3, position, int2, n, n2, s, b, atomParsers$StsdData, i);
            }
            else if (int3 == Atom.TYPE_TTML) {
                atomParsers$StsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(n), "application/ttml+xml", -1, n2, s);
            }
            else if (int3 == Atom.TYPE_tx3g) {
                atomParsers$StsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(n), "application/x-quicktime-tx3g", -1, n2, s);
            }
            else if (int3 == Atom.TYPE_wvtt) {
                atomParsers$StsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(n), "application/x-mp4vtt", -1, n2, s);
            }
            else if (int3 == Atom.TYPE_stpp) {
                atomParsers$StsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(n), "application/ttml+xml", -1, n2, s, 0L);
            }
            parsableByteArray.setPosition(position + int2);
        }
        return atomParsers$StsdData;
    }
    
    private static AtomParsers$TkhdData parseTkhd(final ParsableByteArray parsableByteArray) {
        final int n = 8;
        parsableByteArray.setPosition(8);
        final int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int n2;
        if (fullAtomVersion == 0) {
            n2 = 8;
        }
        else {
            n2 = 16;
        }
        parsableByteArray.skipBytes(n2);
        final int int1 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        final boolean b = true;
        final int position = parsableByteArray.getPosition();
        int n3 = n;
        if (fullAtomVersion == 0) {
            n3 = 4;
        }
        int n4 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n4 >= n3) {
                break;
            }
            if (parsableByteArray.data[position + n4] != -1) {
                b2 = false;
                break;
            }
            ++n4;
        }
        long n5;
        if (b2) {
            parsableByteArray.skipBytes(n3);
            n5 = -1L;
        }
        else {
            long n6;
            if (fullAtomVersion == 0) {
                n6 = parsableByteArray.readUnsignedInt();
            }
            else {
                n6 = parsableByteArray.readUnsignedLongToLong();
            }
            n5 = n6;
            if (n6 == 0L) {
                n5 = -1L;
            }
        }
        parsableByteArray.skipBytes(16);
        final int int2 = parsableByteArray.readInt();
        final int int3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        final int int4 = parsableByteArray.readInt();
        final int int5 = parsableByteArray.readInt();
        int n7;
        if (int2 == 0 && int3 == 65536 && int4 == -65536 && int5 == 0) {
            n7 = 90;
        }
        else if (int2 == 0 && int3 == -65536 && int4 == 65536 && int5 == 0) {
            n7 = 270;
        }
        else if (int2 == -65536 && int3 == 0 && int4 == 0 && int5 == -65536) {
            n7 = 180;
        }
        else {
            n7 = 0;
        }
        return new AtomParsers$TkhdData(int1, n5, n7);
    }
    
    public static Track parseTrak(final Atom$ContainerAtom atom$ContainerAtom, final Atom$LeafAtom atom$LeafAtom, long n, final boolean b) {
        final Atom$ContainerAtom containerAtomOfType = atom$ContainerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        final int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr != Track.TYPE_soun && hdlr != Track.TYPE_vide && hdlr != Track.TYPE_text && hdlr != Track.TYPE_sbtl && hdlr != Track.TYPE_subt) {
            return null;
        }
        final AtomParsers$TkhdData tkhd = parseTkhd(atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        if (n == -1L) {
            n = tkhd.duration;
        }
        final long mvhd = parseMvhd(atom$LeafAtom.data);
        if (n == -1L) {
            n = -1L;
        }
        else {
            n = Util.scaleLargeTimestamp(n, 1000000L, mvhd);
        }
        final Atom$ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        final Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        final AtomParsers$StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, n, tkhd.rotationDegrees, (String)mdhd.second, b);
        final Pair<long[], long[]> edts = parseEdts(atom$ContainerAtom.getContainerAtomOfType(Atom.TYPE_edts));
        if (stsd.mediaFormat == null) {
            return null;
        }
        return new Track(tkhd.id, hdlr, (long)mdhd.first, mvhd, n, stsd.mediaFormat, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, (long[])edts.first, (long[])edts.second);
    }
    
    private static void parseVideoSampleEntry(final ParsableByteArray parsableByteArray, final int n, final int n2, final int n3, final int n4, final long n5, final int n6, final AtomParsers$StsdData atomParsers$StsdData, int n7) {
        parsableByteArray.setPosition(n2 + 8);
        parsableByteArray.skipBytes(24);
        final int unsignedShort = parsableByteArray.readUnsignedShort();
        final int unsignedShort2 = parsableByteArray.readUnsignedShort();
        final int n8 = 0;
        float n9 = 1.0f;
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        if (n == Atom.TYPE_encv) {
            parseSampleEntryEncryptionData(parsableByteArray, n2, n3, atomParsers$StsdData, n7);
            parsableByteArray.setPosition(position);
        }
        List<byte[]> list = null;
        String s = null;
        byte[] projFromParent = null;
        int n10 = -1;
        n7 = n8;
        while (position - n2 < n3) {
            parsableByteArray.setPosition(position);
            final int position2 = parsableByteArray.getPosition();
            final int int1 = parsableByteArray.readInt();
            if (int1 == 0 && parsableByteArray.getPosition() - n2 == n3) {
                break;
            }
            Assertions.checkArgument(int1 > 0, "childAtomSize should be positive");
            final int int2 = parsableByteArray.readInt();
            if (int2 == Atom.TYPE_avcC) {
                Assertions.checkState(s == null);
                s = "video/avc";
                final AtomParsers$AvcCData avcCFromParent = parseAvcCFromParent(parsableByteArray, position2);
                list = avcCFromParent.initializationData;
                atomParsers$StsdData.nalUnitLengthFieldLength = avcCFromParent.nalUnitLengthFieldLength;
                if (n7 == 0) {
                    n9 = avcCFromParent.pixelWidthAspectRatio;
                }
            }
            else if (int2 == Atom.TYPE_hvcC) {
                Assertions.checkState(s == null);
                final Pair<List<byte[]>, Integer> hvcCFromParent = parseHvcCFromParent(parsableByteArray, position2);
                list = (List<byte[]>)hvcCFromParent.first;
                atomParsers$StsdData.nalUnitLengthFieldLength = (int)hvcCFromParent.second;
                s = "video/hevc";
            }
            else if (int2 == Atom.TYPE_d263) {
                Assertions.checkState(s == null);
                s = "video/3gpp";
            }
            else if (int2 == Atom.TYPE_esds) {
                Assertions.checkState(s == null);
                final Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                s = (String)esdsFromParent.first;
                list = Collections.singletonList(esdsFromParent.second);
            }
            else if (int2 == Atom.TYPE_pasp) {
                n9 = parsePaspFromParent(parsableByteArray, position2);
                n7 = 1;
            }
            else if (int2 == Atom.TYPE_vpcC) {
                Assertions.checkState(s == null);
                if (n == Atom.TYPE_vp08) {
                    s = "video/x-vnd.on2.vp8";
                }
                else {
                    s = "video/x-vnd.on2.vp9";
                }
            }
            else if (int2 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, int1);
            }
            else if (int2 == Atom.TYPE_st3d) {
                final int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    switch (parsableByteArray.readUnsignedByte()) {
                        case 0: {
                            n10 = 0;
                            break;
                        }
                        case 1: {
                            n10 = 1;
                            break;
                        }
                        case 2: {
                            n10 = 2;
                            break;
                        }
                    }
                }
            }
            position += int1;
        }
        if (s == null) {
            return;
        }
        atomParsers$StsdData.mediaFormat = MediaFormat.createVideoFormat(Integer.toString(n4), s, -1, -1, n5, unsignedShort, unsignedShort2, list, n6, n9, projFromParent, n10);
    }
}
