// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ChunkIndex;
import java.util.Arrays;
import com.google.android.exoplayer.ParserException;
import android.util.Pair;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.drm.DrmInitData$SchemeInitData;
import android.util.Log;
import com.google.android.exoplayer.drm.DrmInitData$Mapped;
import java.util.List;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.Util;
import android.util.SparseArray;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import java.util.Stack;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.extractor.Extractor;

public class FragmentedMp4Extractor implements Extractor
{
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE;
    private static final int SAMPLE_GROUP_TYPE_seig;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final Stack<Atom$ContainerAtom> containerAtoms;
    private FragmentedMp4Extractor$TrackBundle currentTrackBundle;
    private final ParsableByteArray encryptionSignalByte;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final Track sideloadedTrack;
    private final SparseArray<FragmentedMp4Extractor$TrackBundle> trackBundles;
    
    static {
        SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
        PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = new byte[] { -94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12 };
    }
    
    public FragmentedMp4Extractor() {
        this(0);
    }
    
    public FragmentedMp4Extractor(final int n) {
        this(n, null);
    }
    
    public FragmentedMp4Extractor(final int n, final Track sideloadedTrack) {
        this.sideloadedTrack = sideloadedTrack;
        int n2;
        if (sideloadedTrack != null) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.flags = (n2 | n);
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.encryptionSignalByte = new ParsableByteArray(1);
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new Stack<Atom$ContainerAtom>();
        this.trackBundles = (SparseArray<FragmentedMp4Extractor$TrackBundle>)new SparseArray();
        this.enterReadingAtomHeaderState();
    }
    
    private int appendSampleEncryptionData(final FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle) {
        final TrackFragment fragment = fragmentedMp4Extractor$TrackBundle.fragment;
        final ParsableByteArray sampleEncryptionData = fragment.sampleEncryptionData;
        final int sampleDescriptionIndex = fragment.header.sampleDescriptionIndex;
        TrackEncryptionBox trackEncryptionBox;
        if (fragment.trackEncryptionBox != null) {
            trackEncryptionBox = fragment.trackEncryptionBox;
        }
        else {
            trackEncryptionBox = fragmentedMp4Extractor$TrackBundle.track.sampleDescriptionEncryptionBoxes[sampleDescriptionIndex];
        }
        final int initializationVectorSize = trackEncryptionBox.initializationVectorSize;
        final boolean b = fragment.sampleHasSubsampleEncryptionTable[fragmentedMp4Extractor$TrackBundle.currentSampleIndex];
        final byte[] data = this.encryptionSignalByte.data;
        int n;
        if (b) {
            n = 128;
        }
        else {
            n = 0;
        }
        data[0] = (byte)(n | initializationVectorSize);
        this.encryptionSignalByte.setPosition(0);
        final TrackOutput output = fragmentedMp4Extractor$TrackBundle.output;
        output.sampleData(this.encryptionSignalByte, 1);
        output.sampleData(sampleEncryptionData, initializationVectorSize);
        if (!b) {
            return initializationVectorSize + 1;
        }
        final int unsignedShort = sampleEncryptionData.readUnsignedShort();
        sampleEncryptionData.skipBytes(-2);
        final int n2 = unsignedShort * 6 + 2;
        output.sampleData(sampleEncryptionData, n2);
        return initializationVectorSize + 1 + n2;
    }
    
    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }
    
    private static DrmInitData$Mapped getDrmInitDataFromAtoms(final List<Atom$LeafAtom> list) {
        DrmInitData$Mapped drmInitData$Mapped = null;
        DrmInitData$Mapped drmInitData$Mapped2;
        for (int size = list.size(), i = 0; i < size; ++i, drmInitData$Mapped = drmInitData$Mapped2) {
            final Atom$LeafAtom atom$LeafAtom = list.get(i);
            drmInitData$Mapped2 = drmInitData$Mapped;
            if (atom$LeafAtom.type == Atom.TYPE_pssh) {
                if ((drmInitData$Mapped2 = drmInitData$Mapped) == null) {
                    drmInitData$Mapped2 = new DrmInitData$Mapped();
                }
                final byte[] data = atom$LeafAtom.data.data;
                if (PsshAtomUtil.parseUuid(data) == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                }
                else {
                    drmInitData$Mapped2.put(PsshAtomUtil.parseUuid(data), new DrmInitData$SchemeInitData("video/mp4", data));
                }
            }
        }
        return drmInitData$Mapped;
    }
    
    private static FragmentedMp4Extractor$TrackBundle getNextFragmentRun(final SparseArray<FragmentedMp4Extractor$TrackBundle> sparseArray) {
        FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle = null;
        long n = Long.MAX_VALUE;
        for (int size = sparseArray.size(), i = 0; i < size; ++i) {
            final FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle2 = (FragmentedMp4Extractor$TrackBundle)sparseArray.valueAt(i);
            if (fragmentedMp4Extractor$TrackBundle2.currentSampleIndex != fragmentedMp4Extractor$TrackBundle2.fragment.length) {
                final long dataPosition = fragmentedMp4Extractor$TrackBundle2.fragment.dataPosition;
                if (dataPosition < n) {
                    fragmentedMp4Extractor$TrackBundle = fragmentedMp4Extractor$TrackBundle2;
                    n = dataPosition;
                }
            }
        }
        return fragmentedMp4Extractor$TrackBundle;
    }
    
    private void onContainerAtomRead(final Atom$ContainerAtom atom$ContainerAtom) {
        if (atom$ContainerAtom.type == Atom.TYPE_moov) {
            this.onMoovContainerAtomRead(atom$ContainerAtom);
        }
        else {
            if (atom$ContainerAtom.type == Atom.TYPE_moof) {
                this.onMoofContainerAtomRead(atom$ContainerAtom);
                return;
            }
            if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(atom$ContainerAtom);
            }
        }
    }
    
    private void onLeafAtomRead(final Atom$LeafAtom atom$LeafAtom, final long n) {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(atom$LeafAtom);
        }
        else {
            if (atom$LeafAtom.type == Atom.TYPE_sidx) {
                this.extractorOutput.seekMap(parseSidx(atom$LeafAtom.data, n));
                this.haveOutputSeekMap = true;
                return;
            }
            if (atom$LeafAtom.type == Atom.TYPE_emsg) {
                this.parseEmsg(atom$LeafAtom.data, n);
            }
        }
    }
    
    private void onMoofContainerAtomRead(final Atom$ContainerAtom atom$ContainerAtom) {
        parseMoof(atom$ContainerAtom, this.trackBundles, this.flags, this.extendedTypeScratch);
        final DrmInitData$Mapped drmInitDataFromAtoms = getDrmInitDataFromAtoms(atom$ContainerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            this.extractorOutput.drmInitData(drmInitDataFromAtoms);
        }
    }
    
    private void onMoovContainerAtomRead(final Atom$ContainerAtom atom$ContainerAtom) {
        final boolean b = true;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        final DrmInitData$Mapped drmInitDataFromAtoms = getDrmInitDataFromAtoms(atom$ContainerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            this.extractorOutput.drmInitData(drmInitDataFromAtoms);
        }
        final Atom$ContainerAtom containerAtomOfType = atom$ContainerAtom.getContainerAtomOfType(Atom.TYPE_mvex);
        final SparseArray sparseArray = new SparseArray();
        long mehd = -1L;
        for (int size = containerAtomOfType.leafChildren.size(), i = 0; i < size; ++i) {
            final Atom$LeafAtom atom$LeafAtom = containerAtomOfType.leafChildren.get(i);
            if (atom$LeafAtom.type == Atom.TYPE_trex) {
                final Pair<Integer, DefaultSampleValues> trex = parseTrex(atom$LeafAtom.data);
                sparseArray.put((int)trex.first, trex.second);
            }
            else if (atom$LeafAtom.type == Atom.TYPE_mehd) {
                mehd = parseMehd(atom$LeafAtom.data);
            }
        }
        final SparseArray sparseArray2 = new SparseArray();
        for (int size2 = atom$ContainerAtom.containerChildren.size(), j = 0; j < size2; ++j) {
            final Atom$ContainerAtom atom$ContainerAtom2 = atom$ContainerAtom.containerChildren.get(j);
            if (atom$ContainerAtom2.type == Atom.TYPE_trak) {
                final Track trak = AtomParsers.parseTrak(atom$ContainerAtom2, atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), mehd, false);
                if (trak != null) {
                    sparseArray2.put(trak.id, (Object)trak);
                }
            }
        }
        final int size3 = sparseArray2.size();
        if (this.trackBundles.size() == 0) {
            for (int k = 0; k < size3; ++k) {
                this.trackBundles.put(((Track)sparseArray2.valueAt(k)).id, (Object)new FragmentedMp4Extractor$TrackBundle(this.extractorOutput.track(k)));
            }
            this.extractorOutput.endTracks();
        }
        else {
            Assertions.checkState(this.trackBundles.size() == size3 && b);
        }
        for (int l = 0; l < size3; ++l) {
            final Track track = (Track)sparseArray2.valueAt(l);
            ((FragmentedMp4Extractor$TrackBundle)this.trackBundles.get(track.id)).init(track, (DefaultSampleValues)sparseArray.get(track.id));
        }
    }
    
    private static long parseMehd(final ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0) {
            return parsableByteArray.readUnsignedInt();
        }
        return parsableByteArray.readUnsignedLongToLong();
    }
    
    private static void parseMoof(final Atom$ContainerAtom atom$ContainerAtom, final SparseArray<FragmentedMp4Extractor$TrackBundle> sparseArray, final int n, final byte[] array) {
        for (int size = atom$ContainerAtom.containerChildren.size(), i = 0; i < size; ++i) {
            final Atom$ContainerAtom atom$ContainerAtom2 = atom$ContainerAtom.containerChildren.get(i);
            if (atom$ContainerAtom2.type == Atom.TYPE_traf) {
                parseTraf(atom$ContainerAtom2, sparseArray, n, array);
            }
        }
    }
    
    private static void parseSaio(final ParsableByteArray parsableByteArray, final TrackFragment trackFragment) {
        parsableByteArray.setPosition(8);
        final int int1 = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(int1) & 0x1) == 0x1) {
            parsableByteArray.skipBytes(8);
        }
        final int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != 1) {
            throw new ParserException("Unexpected saio entry count: " + unsignedIntToInt);
        }
        final int fullAtomVersion = Atom.parseFullAtomVersion(int1);
        final long auxiliaryDataPosition = trackFragment.auxiliaryDataPosition;
        long n;
        if (fullAtomVersion == 0) {
            n = parsableByteArray.readUnsignedInt();
        }
        else {
            n = parsableByteArray.readUnsignedLongToLong();
        }
        trackFragment.auxiliaryDataPosition = n + auxiliaryDataPosition;
    }
    
    private static void parseSaiz(final TrackEncryptionBox trackEncryptionBox, final ParsableByteArray parsableByteArray, final TrackFragment trackFragment) {
        boolean b = true;
        final int initializationVectorSize = trackEncryptionBox.initializationVectorSize;
        parsableByteArray.setPosition(8);
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 0x1) == 0x1) {
            parsableByteArray.skipBytes(8);
        }
        final int unsignedByte = parsableByteArray.readUnsignedByte();
        final int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.length) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.length);
        }
        int n3;
        if (unsignedByte == 0) {
            final boolean[] sampleHasSubsampleEncryptionTable = trackFragment.sampleHasSubsampleEncryptionTable;
            int n = 0;
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= unsignedIntToInt) {
                    break;
                }
                final int unsignedByte2 = parsableByteArray.readUnsignedByte();
                sampleHasSubsampleEncryptionTable[n] = (unsignedByte2 > initializationVectorSize);
                ++n;
                n2 += unsignedByte2;
            }
        }
        else {
            if (unsignedByte <= initializationVectorSize) {
                b = false;
            }
            n3 = unsignedByte * unsignedIntToInt + 0;
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, b);
        }
        trackFragment.initEncryptionData(n3);
    }
    
    private static void parseSenc(final ParsableByteArray parsableByteArray, int n, final TrackFragment trackFragment) {
        parsableByteArray.setPosition(n + 8);
        n = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((n & 0x1) != 0x0) {
            throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        final boolean b = (n & 0x2) != 0x0;
        n = parsableByteArray.readUnsignedIntToInt();
        if (n != trackFragment.length) {
            throw new ParserException("Length mismatch: " + n + ", " + trackFragment.length);
        }
        Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, n, b);
        trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
        trackFragment.fillEncryptionData(parsableByteArray);
    }
    
    private static void parseSenc(final ParsableByteArray parsableByteArray, final TrackFragment trackFragment) {
        parseSenc(parsableByteArray, 0, trackFragment);
    }
    
    private static void parseSgpd(final ParsableByteArray parsableByteArray, final ParsableByteArray parsableByteArray2, final TrackFragment trackFragment) {
        parsableByteArray.setPosition(8);
        final int int1 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == FragmentedMp4Extractor.SAMPLE_GROUP_TYPE_seig) {
            if (Atom.parseFullAtomVersion(int1) == 1) {
                parsableByteArray.skipBytes(4);
            }
            if (parsableByteArray.readInt() != 1) {
                throw new ParserException("Entry count in sbgp != 1 (unsupported).");
            }
            parsableByteArray2.setPosition(8);
            final int int2 = parsableByteArray2.readInt();
            if (parsableByteArray2.readInt() == FragmentedMp4Extractor.SAMPLE_GROUP_TYPE_seig) {
                final int fullAtomVersion = Atom.parseFullAtomVersion(int2);
                if (fullAtomVersion == 1) {
                    if (parsableByteArray2.readUnsignedInt() == 0L) {
                        throw new ParserException("Variable length decription in sgpd found (unsupported)");
                    }
                }
                else if (fullAtomVersion >= 2) {
                    parsableByteArray2.skipBytes(4);
                }
                if (parsableByteArray2.readUnsignedInt() != 1L) {
                    throw new ParserException("Entry count in sgpd != 1 (unsupported).");
                }
                parsableByteArray2.skipBytes(2);
                final boolean b = parsableByteArray2.readUnsignedByte() == 1;
                if (b) {
                    final int unsignedByte = parsableByteArray2.readUnsignedByte();
                    final byte[] array = new byte[16];
                    parsableByteArray2.readBytes(array, 0, array.length);
                    trackFragment.definesEncryptionData = true;
                    trackFragment.trackEncryptionBox = new TrackEncryptionBox(b, unsignedByte, array);
                }
            }
        }
    }
    
    private static ChunkIndex parseSidx(final ParsableByteArray parsableByteArray, long n) {
        parsableByteArray.setPosition(8);
        final int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        final long unsignedInt = parsableByteArray.readUnsignedInt();
        long n3;
        if (fullAtomVersion == 0) {
            final long unsignedInt2 = parsableByteArray.readUnsignedInt();
            final long n2 = parsableByteArray.readUnsignedInt() + n;
            n = unsignedInt2;
            n3 = n2;
        }
        else {
            final long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            n3 = parsableByteArray.readUnsignedLongToLong() + n;
            n = unsignedLongToLong;
        }
        parsableByteArray.skipBytes(2);
        final int unsignedShort = parsableByteArray.readUnsignedShort();
        final int[] array = new int[unsignedShort];
        final long[] array2 = new long[unsignedShort];
        final long[] array3 = new long[unsignedShort];
        final long[] array4 = new long[unsignedShort];
        final long scaleLargeTimestamp = Util.scaleLargeTimestamp(n, 1000000L, unsignedInt);
        int i = 0;
        long n4 = n;
        n = n3;
        long scaleLargeTimestamp2 = scaleLargeTimestamp;
        while (i < unsignedShort) {
            final int int1 = parsableByteArray.readInt();
            if ((Integer.MIN_VALUE & int1) != 0x0) {
                throw new ParserException("Unhandled indirect reference");
            }
            final long unsignedInt3 = parsableByteArray.readUnsignedInt();
            array[i] = (int1 & Integer.MAX_VALUE);
            array2[i] = n;
            array4[i] = scaleLargeTimestamp2;
            n4 += unsignedInt3;
            scaleLargeTimestamp2 = Util.scaleLargeTimestamp(n4, 1000000L, unsignedInt);
            array3[i] = scaleLargeTimestamp2 - array4[i];
            parsableByteArray.skipBytes(4);
            n += array[i];
            ++i;
        }
        return new ChunkIndex(array, array2, array3, array4);
    }
    
    private static long parseTfdt(final ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1) {
            return parsableByteArray.readUnsignedLongToLong();
        }
        return parsableByteArray.readUnsignedInt();
    }
    
    private static FragmentedMp4Extractor$TrackBundle parseTfhd(final ParsableByteArray parsableByteArray, final SparseArray<FragmentedMp4Extractor$TrackBundle> sparseArray, int sampleDescriptionIndex) {
        parsableByteArray.setPosition(8);
        final int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        final int int1 = parsableByteArray.readInt();
        if ((sampleDescriptionIndex & 0x4) == 0x0) {
            sampleDescriptionIndex = int1;
        }
        else {
            sampleDescriptionIndex = 0;
        }
        final FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle = (FragmentedMp4Extractor$TrackBundle)sparseArray.get(sampleDescriptionIndex);
        if (fragmentedMp4Extractor$TrackBundle == null) {
            return null;
        }
        if ((fullAtomFlags & 0x1) != 0x0) {
            final long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            fragmentedMp4Extractor$TrackBundle.fragment.dataPosition = unsignedLongToLong;
            fragmentedMp4Extractor$TrackBundle.fragment.auxiliaryDataPosition = unsignedLongToLong;
        }
        final DefaultSampleValues defaultSampleValues = fragmentedMp4Extractor$TrackBundle.defaultSampleValues;
        if ((fullAtomFlags & 0x2) != 0x0) {
            sampleDescriptionIndex = parsableByteArray.readUnsignedIntToInt() - 1;
        }
        else {
            sampleDescriptionIndex = defaultSampleValues.sampleDescriptionIndex;
        }
        int n;
        if ((fullAtomFlags & 0x8) != 0x0) {
            n = parsableByteArray.readUnsignedIntToInt();
        }
        else {
            n = defaultSampleValues.duration;
        }
        int n2;
        if ((fullAtomFlags & 0x10) != 0x0) {
            n2 = parsableByteArray.readUnsignedIntToInt();
        }
        else {
            n2 = defaultSampleValues.size;
        }
        int n3;
        if ((fullAtomFlags & 0x20) != 0x0) {
            n3 = parsableByteArray.readUnsignedIntToInt();
        }
        else {
            n3 = defaultSampleValues.flags;
        }
        fragmentedMp4Extractor$TrackBundle.fragment.header = new DefaultSampleValues(sampleDescriptionIndex, n, n2, n3);
        return fragmentedMp4Extractor$TrackBundle;
    }
    
    private static void parseTraf(final Atom$ContainerAtom atom$ContainerAtom, final SparseArray<FragmentedMp4Extractor$TrackBundle> sparseArray, int i, final byte[] array) {
        if (atom$ContainerAtom.getChildAtomOfTypeCount(Atom.TYPE_trun) != 1) {
            throw new ParserException("Trun count in traf != 1 (unsupported).");
        }
        final FragmentedMp4Extractor$TrackBundle tfhd = parseTfhd(atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_tfhd).data, sparseArray, i);
        if (tfhd != null) {
            final TrackFragment fragment = tfhd.fragment;
            final long nextFragmentDecodeTime = fragment.nextFragmentDecodeTime;
            tfhd.reset();
            long tfdt = nextFragmentDecodeTime;
            if (atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_tfdt) != null) {
                tfdt = nextFragmentDecodeTime;
                if ((i & 0x2) == 0x0) {
                    tfdt = parseTfdt(atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_tfdt).data);
                }
            }
            parseTrun(tfhd, tfdt, i, atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_trun).data);
            final Atom$LeafAtom leafAtomOfType = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
            if (leafAtomOfType != null) {
                parseSaiz(tfhd.track.sampleDescriptionEncryptionBoxes[fragment.header.sampleDescriptionIndex], leafAtomOfType.data, fragment);
            }
            final Atom$LeafAtom leafAtomOfType2 = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_saio);
            if (leafAtomOfType2 != null) {
                parseSaio(leafAtomOfType2.data, fragment);
            }
            final Atom$LeafAtom leafAtomOfType3 = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_senc);
            if (leafAtomOfType3 != null) {
                parseSenc(leafAtomOfType3.data, fragment);
            }
            final Atom$LeafAtom leafAtomOfType4 = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_sbgp);
            final Atom$LeafAtom leafAtomOfType5 = atom$ContainerAtom.getLeafAtomOfType(Atom.TYPE_sgpd);
            if (leafAtomOfType4 != null && leafAtomOfType5 != null) {
                parseSgpd(leafAtomOfType4.data, leafAtomOfType5.data, fragment);
            }
            int size;
            Atom$LeafAtom atom$LeafAtom;
            for (size = atom$ContainerAtom.leafChildren.size(), i = 0; i < size; ++i) {
                atom$LeafAtom = atom$ContainerAtom.leafChildren.get(i);
                if (atom$LeafAtom.type == Atom.TYPE_uuid) {
                    parseUuid(atom$LeafAtom.data, fragment, array);
                }
            }
        }
    }
    
    private static Pair<Integer, DefaultSampleValues> parseTrex(final ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return (Pair<Integer, DefaultSampleValues>)Pair.create((Object)parsableByteArray.readInt(), (Object)new DefaultSampleValues(parsableByteArray.readUnsignedIntToInt() - 1, parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readInt()));
    }
    
    private static void parseTrun(final FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle, long nextFragmentDecodeTime, int n, final ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        final int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        final Track track = fragmentedMp4Extractor$TrackBundle.track;
        final TrackFragment fragment = fragmentedMp4Extractor$TrackBundle.fragment;
        final DefaultSampleValues header = fragment.header;
        final int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if ((fullAtomFlags & 0x1) != 0x0) {
            fragment.dataPosition += parsableByteArray.readInt();
        }
        boolean b;
        if ((fullAtomFlags & 0x4) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        int n2 = header.flags;
        if (b) {
            n2 = parsableByteArray.readUnsignedIntToInt();
        }
        int n3;
        if ((fullAtomFlags & 0x100) != 0x0) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if ((fullAtomFlags & 0x200) != 0x0) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        final boolean b2 = (fullAtomFlags & 0x400) != 0x0;
        int n5;
        if ((fullAtomFlags & 0x800) != 0x0) {
            n5 = 1;
        }
        else {
            n5 = 0;
        }
        long scaleLargeTimestamp;
        if (track.editListDurations != null && track.editListDurations.length == 1 && track.editListDurations[0] == 0L) {
            scaleLargeTimestamp = Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000L, track.timescale);
        }
        else {
            scaleLargeTimestamp = 0L;
        }
        fragment.initTables(unsignedIntToInt);
        final int[] sampleSizeTable = fragment.sampleSizeTable;
        final int[] sampleCompositionTimeOffsetTable = fragment.sampleCompositionTimeOffsetTable;
        final long[] sampleDecodingTimeTable = fragment.sampleDecodingTimeTable;
        final boolean[] sampleIsSyncFrameTable = fragment.sampleIsSyncFrameTable;
        final long timescale = track.timescale;
        boolean b3;
        if (track.type == Track.TYPE_vide && (n & 0x1) != 0x0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        long n8;
        for (int i = 0; i < unsignedIntToInt; ++i, nextFragmentDecodeTime += n8) {
            int n6;
            if (n3 != 0) {
                n6 = parsableByteArray.readUnsignedIntToInt();
            }
            else {
                n6 = header.duration;
            }
            int n7;
            if (n4 != 0) {
                n7 = parsableByteArray.readUnsignedIntToInt();
            }
            else {
                n7 = header.size;
            }
            if (i == 0 && b) {
                n = n2;
            }
            else if (b2) {
                n = parsableByteArray.readInt();
            }
            else {
                n = header.flags;
            }
            if (n5 != 0) {
                sampleCompositionTimeOffsetTable[i] = (int)(parsableByteArray.readInt() * 1000 / timescale);
            }
            else {
                sampleCompositionTimeOffsetTable[i] = 0;
            }
            sampleDecodingTimeTable[i] = Util.scaleLargeTimestamp(nextFragmentDecodeTime, 1000L, timescale) - scaleLargeTimestamp;
            sampleSizeTable[i] = n7;
            sampleIsSyncFrameTable[i] = ((n >> 16 & 0x1) == 0x0 && (!b3 || i == 0));
            n8 = n6;
        }
        fragment.nextFragmentDecodeTime = nextFragmentDecodeTime;
    }
    
    private static void parseUuid(final ParsableByteArray parsableByteArray, final TrackFragment trackFragment, final byte[] array) {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(array, 0, 16);
        if (!Arrays.equals(array, FragmentedMp4Extractor.PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            return;
        }
        parseSenc(parsableByteArray, 16, trackFragment);
    }
    
    private void processAtomEnded(final long n) {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == n) {
            this.onContainerAtomRead(this.containerAtoms.pop());
        }
        this.enterReadingAtomHeaderState();
    }
    
    private boolean readAtomHeader(final ExtractorInput extractorInput) {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        if (this.atomSize == 1L) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        }
        final long n = extractorInput.getPosition() - this.atomHeaderBytesRead;
        if (this.atomType == Atom.TYPE_moof) {
            for (int size = this.trackBundles.size(), i = 0; i < size; ++i) {
                final TrackFragment fragment = ((FragmentedMp4Extractor$TrackBundle)this.trackBundles.valueAt(i)).fragment;
                fragment.auxiliaryDataPosition = n;
                fragment.dataPosition = n;
            }
        }
        if (this.atomType == Atom.TYPE_mdat) {
            this.currentTrackBundle = null;
            this.endOfMdatPosition = this.atomSize + n;
            if (!this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.haveOutputSeekMap = true;
            }
            this.parserState = 2;
            return true;
        }
        if (shouldParseContainerAtom(this.atomType)) {
            final long n2 = extractorInput.getPosition() + this.atomSize - 8L;
            this.containerAtoms.add(new Atom$ContainerAtom(this.atomType, n2));
            if (this.atomSize == this.atomHeaderBytesRead) {
                this.processAtomEnded(n2);
            }
            else {
                this.enterReadingAtomHeaderState();
            }
        }
        else if (shouldParseLeafAtom(this.atomType)) {
            if (this.atomHeaderBytesRead != 8) {
                throw new ParserException("Leaf atom defines extended atom size (unsupported).");
            }
            if (this.atomSize > 2147483647L) {
                throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
            }
            this.atomData = new ParsableByteArray((int)this.atomSize);
            System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, 8);
            this.parserState = 1;
        }
        else {
            if (this.atomSize > 2147483647L) {
                throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }
    
    private void readAtomPayload(final ExtractorInput extractorInput) {
        final int n = (int)this.atomSize - this.atomHeaderBytesRead;
        if (this.atomData != null) {
            extractorInput.readFully(this.atomData.data, 8, n);
            this.onLeafAtomRead(new Atom$LeafAtom(this.atomType, this.atomData), extractorInput.getPosition());
        }
        else {
            extractorInput.skipFully(n);
        }
        this.processAtomEnded(extractorInput.getPosition());
    }
    
    private void readEncryptionData(final ExtractorInput extractorInput) {
        FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle = null;
        long auxiliaryDataPosition = Long.MAX_VALUE;
        for (int size = this.trackBundles.size(), i = 0; i < size; ++i) {
            final TrackFragment fragment = ((FragmentedMp4Extractor$TrackBundle)this.trackBundles.valueAt(i)).fragment;
            if (fragment.sampleEncryptionDataNeedsFill && fragment.auxiliaryDataPosition < auxiliaryDataPosition) {
                auxiliaryDataPosition = fragment.auxiliaryDataPosition;
                fragmentedMp4Extractor$TrackBundle = (FragmentedMp4Extractor$TrackBundle)this.trackBundles.valueAt(i);
            }
        }
        if (fragmentedMp4Extractor$TrackBundle == null) {
            this.parserState = 3;
            return;
        }
        final int n = (int)(auxiliaryDataPosition - extractorInput.getPosition());
        if (n < 0) {
            throw new ParserException("Offset to encryption data was negative.");
        }
        extractorInput.skipFully(n);
        fragmentedMp4Extractor$TrackBundle.fragment.fillEncryptionData(extractorInput);
    }
    
    private boolean readSample(final ExtractorInput extractorInput) {
        int n = 2;
        if (this.parserState == 3) {
            if (this.currentTrackBundle == null) {
                this.currentTrackBundle = getNextFragmentRun(this.trackBundles);
                if (this.currentTrackBundle == null) {
                    final int n2 = (int)(this.endOfMdatPosition - extractorInput.getPosition());
                    if (n2 < 0) {
                        throw new ParserException("Offset to end of mdat was negative.");
                    }
                    extractorInput.skipFully(n2);
                    this.enterReadingAtomHeaderState();
                    return false;
                }
                else {
                    final int n3 = (int)(this.currentTrackBundle.fragment.dataPosition - extractorInput.getPosition());
                    if (n3 < 0) {
                        throw new ParserException("Offset to sample data was negative.");
                    }
                    extractorInput.skipFully(n3);
                }
            }
            this.sampleSize = this.currentTrackBundle.fragment.sampleSizeTable[this.currentTrackBundle.currentSampleIndex];
            if (this.currentTrackBundle.fragment.definesEncryptionData) {
                this.sampleBytesWritten = this.appendSampleEncryptionData(this.currentTrackBundle);
                this.sampleSize += this.sampleBytesWritten;
            }
            else {
                this.sampleBytesWritten = 0;
            }
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        final TrackFragment fragment = this.currentTrackBundle.fragment;
        final Track track = this.currentTrackBundle.track;
        final TrackOutput output = this.currentTrackBundle.output;
        final int currentSampleIndex = this.currentTrackBundle.currentSampleIndex;
        if (track.nalUnitLengthFieldLength != -1) {
            final byte[] data = this.nalLength.data;
            data[0] = 0;
            data[2] = (data[1] = 0);
            final int nalUnitLengthFieldLength = track.nalUnitLengthFieldLength;
            final int n4 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < this.sampleSize) {
                if (this.sampleCurrentNalBytesRemaining == 0) {
                    extractorInput.readFully(this.nalLength.data, n4, nalUnitLengthFieldLength);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    output.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    this.sampleSize += n4;
                }
                else {
                    final int sampleData = output.sampleData(extractorInput, this.sampleCurrentNalBytesRemaining, false);
                    this.sampleBytesWritten += sampleData;
                    this.sampleCurrentNalBytesRemaining -= sampleData;
                }
            }
        }
        else {
            while (this.sampleBytesWritten < this.sampleSize) {
                this.sampleBytesWritten += output.sampleData(extractorInput, this.sampleSize - this.sampleBytesWritten, false);
            }
        }
        final long samplePresentationTime = fragment.getSamplePresentationTime(currentSampleIndex);
        if (!fragment.definesEncryptionData) {
            n = 0;
        }
        boolean b;
        if (fragment.sampleIsSyncFrameTable[currentSampleIndex]) {
            b = true;
        }
        else {
            b = false;
        }
        final int sampleDescriptionIndex = fragment.header.sampleDescriptionIndex;
        byte[] array = null;
        if (fragment.definesEncryptionData) {
            if (fragment.trackEncryptionBox != null) {
                array = fragment.trackEncryptionBox.keyId;
            }
            else {
                array = track.sampleDescriptionEncryptionBoxes[sampleDescriptionIndex].keyId;
            }
        }
        output.sampleMetadata(samplePresentationTime * 1000L, (b ? 1 : 0) | n, this.sampleSize, 0, array);
        final FragmentedMp4Extractor$TrackBundle currentTrackBundle = this.currentTrackBundle;
        ++currentTrackBundle.currentSampleIndex;
        if (this.currentTrackBundle.currentSampleIndex == fragment.length) {
            this.currentTrackBundle = null;
        }
        this.parserState = 3;
        return true;
    }
    
    private static boolean shouldParseContainerAtom(final int n) {
        return n == Atom.TYPE_moov || n == Atom.TYPE_trak || n == Atom.TYPE_mdia || n == Atom.TYPE_minf || n == Atom.TYPE_stbl || n == Atom.TYPE_moof || n == Atom.TYPE_traf || n == Atom.TYPE_mvex || n == Atom.TYPE_edts;
    }
    
    private static boolean shouldParseLeafAtom(final int n) {
        return n == Atom.TYPE_hdlr || n == Atom.TYPE_mdhd || n == Atom.TYPE_mvhd || n == Atom.TYPE_sidx || n == Atom.TYPE_stsd || n == Atom.TYPE_tfdt || n == Atom.TYPE_tfhd || n == Atom.TYPE_tkhd || n == Atom.TYPE_trex || n == Atom.TYPE_trun || n == Atom.TYPE_pssh || n == Atom.TYPE_saiz || n == Atom.TYPE_saio || n == Atom.TYPE_senc || n == Atom.TYPE_sbgp || n == Atom.TYPE_sgpd || n == Atom.TYPE_uuid || n == Atom.TYPE_elst || n == Atom.TYPE_mehd || n == Atom.TYPE_emsg;
    }
    
    @Override
    public final void init(final ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        if (this.sideloadedTrack != null) {
            final FragmentedMp4Extractor$TrackBundle fragmentedMp4Extractor$TrackBundle = new FragmentedMp4Extractor$TrackBundle(extractorOutput.track(0));
            fragmentedMp4Extractor$TrackBundle.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, (Object)fragmentedMp4Extractor$TrackBundle);
            this.extractorOutput.endTracks();
        }
    }
    
    protected void parseEmsg(final ParsableByteArray parsableByteArray, final long n) {
    }
    
    @Override
    public final int read(final ExtractorInput extractorInput, final PositionHolder positionHolder) {
        while (true) {
            switch (this.parserState) {
                default: {
                    if (this.readSample(extractorInput)) {
                        return 0;
                    }
                    continue;
                }
                case 0: {
                    if (!this.readAtomHeader(extractorInput)) {
                        return -1;
                    }
                    continue;
                }
                case 1: {
                    this.readAtomPayload(extractorInput);
                    continue;
                }
                case 2: {
                    this.readEncryptionData(extractorInput);
                    continue;
                }
            }
        }
    }
    
    @Override
    public final void seek() {
        for (int size = this.trackBundles.size(), i = 0; i < size; ++i) {
            ((FragmentedMp4Extractor$TrackBundle)this.trackBundles.valueAt(i)).reset();
        }
        this.containerAtoms.clear();
        this.enterReadingAtomHeaderState();
    }
}
