// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import java.nio.ByteBuffer;
import java.util.Arrays;
import com.google.android.exoplayer.util.Util;
import android.annotation.TargetApi;
import java.util.Collections;
import com.google.android.exoplayer.util.Assertions;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class MediaFormat implements Parcelable
{
    public static final Parcelable$Creator<MediaFormat> CREATOR;
    public final boolean adaptive;
    public final int bitrate;
    public final int channelCount;
    public final long durationUs;
    public final int encoderDelay;
    public final int encoderPadding;
    private android.media.MediaFormat frameworkMediaFormat;
    private int hashCode;
    public final int height;
    public final List<byte[]> initializationData;
    public final String language;
    public final int maxHeight;
    public final int maxInputSize;
    public final int maxWidth;
    public final String mimeType;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int rotationDegrees;
    public final int sampleRate;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final String trackId;
    public final int width;
    
    static {
        CREATOR = (Parcelable$Creator)new MediaFormat$1();
    }
    
    MediaFormat(final Parcel parcel) {
        int n = 1;
        this.trackId = parcel.readString();
        this.mimeType = parcel.readString();
        this.bitrate = parcel.readInt();
        this.maxInputSize = parcel.readInt();
        this.durationUs = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.language = parcel.readString();
        this.subsampleOffsetUs = parcel.readLong();
        parcel.readList((List)(this.initializationData = new ArrayList<byte[]>()), (ClassLoader)null);
        this.adaptive = (parcel.readInt() == 1);
        this.maxWidth = parcel.readInt();
        this.maxHeight = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        if (parcel.readInt() == 0) {
            n = 0;
        }
        byte[] byteArray;
        if (n != 0) {
            byteArray = parcel.createByteArray();
        }
        else {
            byteArray = null;
        }
        this.projectionData = byteArray;
        this.stereoMode = parcel.readInt();
    }
    
    MediaFormat(final String trackId, final String s, final int bitrate, final int maxInputSize, final long durationUs, final int width, final int height, final int rotationDegrees, final float pixelWidthHeightRatio, final int channelCount, final int sampleRate, final String language, final long subsampleOffsetUs, final List<byte[]> list, final boolean adaptive, final int maxWidth, final int maxHeight, final int pcmEncoding, final int encoderDelay, final int encoderPadding, final byte[] projectionData, final int stereoMode) {
        this.trackId = trackId;
        this.mimeType = Assertions.checkNotEmpty(s);
        this.bitrate = bitrate;
        this.maxInputSize = maxInputSize;
        this.durationUs = durationUs;
        this.width = width;
        this.height = height;
        this.rotationDegrees = rotationDegrees;
        this.pixelWidthHeightRatio = pixelWidthHeightRatio;
        this.channelCount = channelCount;
        this.sampleRate = sampleRate;
        this.language = language;
        this.subsampleOffsetUs = subsampleOffsetUs;
        List<byte[]> emptyList = list;
        if (list == null) {
            emptyList = Collections.emptyList();
        }
        this.initializationData = emptyList;
        this.adaptive = adaptive;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.pcmEncoding = pcmEncoding;
        this.encoderDelay = encoderDelay;
        this.encoderPadding = encoderPadding;
        this.projectionData = projectionData;
        this.stereoMode = stereoMode;
    }
    
    public static MediaFormat createAudioFormat(final String s, final String s2, final int n, final int n2, final long n3, final int n4, final int n5, final List<byte[]> list, final String s3) {
        return createAudioFormat(s, s2, n, n2, n3, n4, n5, list, s3, -1);
    }
    
    public static MediaFormat createAudioFormat(final String s, final String s2, final int n, final int n2, final long n3, final int n4, final int n5, final List<byte[]> list, final String s3, final int n6) {
        return new MediaFormat(s, s2, n, n2, n3, -1, -1, -1, -1.0f, n4, n5, s3, Long.MAX_VALUE, list, false, -1, -1, n6, -1, -1, null, -1);
    }
    
    public static MediaFormat createImageFormat(final String s, final String s2, final int n, final long n2, final List<byte[]> list, final String s3) {
        return new MediaFormat(s, s2, n, -1, n2, -1, -1, -1, -1.0f, -1, -1, s3, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
    }
    
    public static MediaFormat createTextFormat(final String s, final String s2, final int n, final long n2, final String s3) {
        return createTextFormat(s, s2, n, n2, s3, Long.MAX_VALUE);
    }
    
    public static MediaFormat createTextFormat(final String s, final String s2, final int n, final long n2, final String s3, final long n3) {
        return new MediaFormat(s, s2, n, -1, n2, -1, -1, -1, -1.0f, -1, -1, s3, n3, null, false, -1, -1, -1, -1, -1, null, -1);
    }
    
    public static MediaFormat createVideoFormat(final String s, final String s2, final int n, final int n2, final long n3, final int n4, final int n5, final List<byte[]> list) {
        return createVideoFormat(s, s2, n, n2, n3, n4, n5, list, -1, -1.0f, null, -1);
    }
    
    public static MediaFormat createVideoFormat(final String s, final String s2, final int n, final int n2, final long n3, final int n4, final int n5, final List<byte[]> list, final int n6, final float n7) {
        return new MediaFormat(s, s2, n, n2, n3, n4, n5, n6, n7, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
    }
    
    public static MediaFormat createVideoFormat(final String s, final String s2, final int n, final int n2, final long n3, final int n4, final int n5, final List<byte[]> list, final int n6, final float n7, final byte[] array, final int n8) {
        return new MediaFormat(s, s2, n, n2, n3, n4, n5, n6, n7, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, array, n8);
    }
    
    @TargetApi(16)
    private static final void maybeSetIntegerV16(final android.media.MediaFormat mediaFormat, final String s, final int n) {
        if (n != -1) {
            mediaFormat.setInteger(s, n);
        }
    }
    
    @TargetApi(16)
    private static final void maybeSetStringV16(final android.media.MediaFormat mediaFormat, final String s, final String s2) {
        if (s2 != null) {
            mediaFormat.setString(s, s2);
        }
    }
    
    public MediaFormat copyAsAdaptive(final String s) {
        return new MediaFormat(s, this.mimeType, -1, -1, this.durationUs, -1, -1, -1, -1.0f, -1, -1, null, Long.MAX_VALUE, null, true, this.maxWidth, this.maxHeight, -1, -1, -1, null, this.stereoMode);
    }
    
    public MediaFormat copyWithMaxVideoDimensions(final int n, final int n2) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subsampleOffsetUs, this.initializationData, this.adaptive, n, n2, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }
    
    public MediaFormat copyWithSubsampleOffsetUs(final long n) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, n, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (this.getClass() == o.getClass()) {
                    final MediaFormat mediaFormat = (MediaFormat)o;
                    b2 = b;
                    if (this.adaptive == mediaFormat.adaptive) {
                        b2 = b;
                        if (this.bitrate == mediaFormat.bitrate) {
                            b2 = b;
                            if (this.maxInputSize == mediaFormat.maxInputSize) {
                                b2 = b;
                                if (this.durationUs == mediaFormat.durationUs) {
                                    b2 = b;
                                    if (this.width == mediaFormat.width) {
                                        b2 = b;
                                        if (this.height == mediaFormat.height) {
                                            b2 = b;
                                            if (this.rotationDegrees == mediaFormat.rotationDegrees) {
                                                b2 = b;
                                                if (this.pixelWidthHeightRatio == mediaFormat.pixelWidthHeightRatio) {
                                                    b2 = b;
                                                    if (this.maxWidth == mediaFormat.maxWidth) {
                                                        b2 = b;
                                                        if (this.maxHeight == mediaFormat.maxHeight) {
                                                            b2 = b;
                                                            if (this.channelCount == mediaFormat.channelCount) {
                                                                b2 = b;
                                                                if (this.sampleRate == mediaFormat.sampleRate) {
                                                                    b2 = b;
                                                                    if (this.pcmEncoding == mediaFormat.pcmEncoding) {
                                                                        b2 = b;
                                                                        if (this.encoderDelay == mediaFormat.encoderDelay) {
                                                                            b2 = b;
                                                                            if (this.encoderPadding == mediaFormat.encoderPadding) {
                                                                                b2 = b;
                                                                                if (this.subsampleOffsetUs == mediaFormat.subsampleOffsetUs) {
                                                                                    b2 = b;
                                                                                    if (Util.areEqual(this.trackId, mediaFormat.trackId)) {
                                                                                        b2 = b;
                                                                                        if (Util.areEqual(this.language, mediaFormat.language)) {
                                                                                            b2 = b;
                                                                                            if (Util.areEqual(this.mimeType, mediaFormat.mimeType)) {
                                                                                                b2 = b;
                                                                                                if (this.initializationData.size() == mediaFormat.initializationData.size()) {
                                                                                                    b2 = b;
                                                                                                    if (Arrays.equals(this.projectionData, mediaFormat.projectionData)) {
                                                                                                        b2 = b;
                                                                                                        if (this.stereoMode == mediaFormat.stereoMode) {
                                                                                                            for (int i = 0; i < this.initializationData.size(); ++i) {
                                                                                                                b2 = b;
                                                                                                                if (!Arrays.equals(this.initializationData.get(i), mediaFormat.initializationData.get(i))) {
                                                                                                                    return b2;
                                                                                                                }
                                                                                                            }
                                                                                                            return true;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @SuppressLint({ "InlinedApi" })
    @TargetApi(16)
    public final android.media.MediaFormat getFrameworkMediaFormatV16() {
        if (this.frameworkMediaFormat == null) {
            final android.media.MediaFormat frameworkMediaFormat = new android.media.MediaFormat();
            frameworkMediaFormat.setString("mime", this.mimeType);
            maybeSetStringV16(frameworkMediaFormat, "language", this.language);
            maybeSetIntegerV16(frameworkMediaFormat, "max-input-size", this.maxInputSize);
            maybeSetIntegerV16(frameworkMediaFormat, "width", this.width);
            maybeSetIntegerV16(frameworkMediaFormat, "height", this.height);
            maybeSetIntegerV16(frameworkMediaFormat, "rotation-degrees", this.rotationDegrees);
            maybeSetIntegerV16(frameworkMediaFormat, "max-width", this.maxWidth);
            maybeSetIntegerV16(frameworkMediaFormat, "max-height", this.maxHeight);
            maybeSetIntegerV16(frameworkMediaFormat, "channel-count", this.channelCount);
            maybeSetIntegerV16(frameworkMediaFormat, "sample-rate", this.sampleRate);
            maybeSetIntegerV16(frameworkMediaFormat, "encoder-delay", this.encoderDelay);
            maybeSetIntegerV16(frameworkMediaFormat, "encoder-padding", this.encoderPadding);
            for (int i = 0; i < this.initializationData.size(); ++i) {
                frameworkMediaFormat.setByteBuffer("csd-" + i, ByteBuffer.wrap(this.initializationData.get(i)));
            }
            if (this.durationUs != -1L) {
                frameworkMediaFormat.setLong("durationUs", this.durationUs);
            }
            this.frameworkMediaFormat = frameworkMediaFormat;
        }
        return this.frameworkMediaFormat;
    }
    
    @Override
    public int hashCode() {
        final int n = 0;
        if (this.hashCode == 0) {
            int hashCode;
            if (this.trackId == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.trackId.hashCode();
            }
            int hashCode2;
            if (this.mimeType == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.mimeType.hashCode();
            }
            final int bitrate = this.bitrate;
            final int maxInputSize = this.maxInputSize;
            final int width = this.width;
            final int height = this.height;
            final int rotationDegrees = this.rotationDegrees;
            final int floatToRawIntBits = Float.floatToRawIntBits(this.pixelWidthHeightRatio);
            final int n2 = (int)this.durationUs;
            int n3;
            if (this.adaptive) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int maxWidth = this.maxWidth;
            final int maxHeight = this.maxHeight;
            final int channelCount = this.channelCount;
            final int sampleRate = this.sampleRate;
            final int pcmEncoding = this.pcmEncoding;
            final int encoderDelay = this.encoderDelay;
            final int encoderPadding = this.encoderPadding;
            int hashCode3;
            if (this.language == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.language.hashCode();
            }
            int n4 = (hashCode3 + ((((((((n3 + ((((((((hashCode2 + (hashCode + 527) * 31) * 31 + bitrate) * 31 + maxInputSize) * 31 + width) * 31 + height) * 31 + rotationDegrees) * 31 + floatToRawIntBits) * 31 + n2) * 31) * 31 + maxWidth) * 31 + maxHeight) * 31 + channelCount) * 31 + sampleRate) * 31 + pcmEncoding) * 31 + encoderDelay) * 31 + encoderPadding) * 31) * 31 + (int)this.subsampleOffsetUs;
            for (int i = n; i < this.initializationData.size(); ++i) {
                n4 = Arrays.hashCode(this.initializationData.get(i)) + n4 * 31;
            }
            this.hashCode = (n4 * 31 + Arrays.hashCode(this.projectionData)) * 31 + this.stereoMode;
        }
        return this.hashCode;
    }
    
    @Override
    public String toString() {
        return "MediaFormat(" + this.trackId + ", " + this.mimeType + ", " + this.bitrate + ", " + this.maxInputSize + ", " + this.width + ", " + this.height + ", " + this.rotationDegrees + ", " + this.pixelWidthHeightRatio + ", " + this.channelCount + ", " + this.sampleRate + ", " + this.language + ", " + this.durationUs + ", " + this.adaptive + ", " + this.maxWidth + ", " + this.maxHeight + ", " + this.pcmEncoding + ", " + this.encoderDelay + ", " + this.encoderPadding + ")";
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        parcel.writeString(this.trackId);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.maxInputSize);
        parcel.writeLong(this.durationUs);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeString(this.language);
        parcel.writeLong(this.subsampleOffsetUs);
        parcel.writeList((List)this.initializationData);
        if (this.adaptive) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeInt(this.maxWidth);
        parcel.writeInt(this.maxHeight);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        if (this.projectionData != null) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.projectionData != null) {
            parcel.writeByteArray(this.projectionData);
        }
        parcel.writeInt(this.stereoMode);
    }
}
