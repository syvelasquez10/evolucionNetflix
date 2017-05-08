// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.MediaFormat;

public final class Track
{
    public static final int TYPE_sbtl;
    public static final int TYPE_soun;
    public static final int TYPE_subt;
    public static final int TYPE_text;
    public static final int TYPE_vide;
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final int id;
    public final MediaFormat mediaFormat;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    public final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final long timescale;
    public final int type;
    
    static {
        TYPE_vide = Util.getIntegerCodeForString("vide");
        TYPE_soun = Util.getIntegerCodeForString("soun");
        TYPE_text = Util.getIntegerCodeForString("text");
        TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
        TYPE_subt = Util.getIntegerCodeForString("subt");
    }
    
    public Track(final int id, final int type, final long timescale, final long movieTimescale, final long durationUs, final MediaFormat mediaFormat, final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes, final int nalUnitLengthFieldLength, final long[] editListDurations, final long[] editListMediaTimes) {
        this.id = id;
        this.type = type;
        this.timescale = timescale;
        this.movieTimescale = movieTimescale;
        this.durationUs = durationUs;
        this.mediaFormat = mediaFormat;
        this.sampleDescriptionEncryptionBoxes = sampleDescriptionEncryptionBoxes;
        this.nalUnitLengthFieldLength = nalUnitLengthFieldLength;
        this.editListDurations = editListDurations;
        this.editListMediaTimes = editListMediaTimes;
    }
}
