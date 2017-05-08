// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.util.Arrays;
import com.netflix.mediaclient.Log;
import java.io.DataInputStream;

public class SegmentEncryptionInfo extends FullBox
{
    public static final String USER_TYPE = "com.netflix.senc";
    private byte defaultIVSize;
    private SegmentEncryptionInfo$ImageEncryptionInfo[] mImageEncryptions;
    private int sampleCount;
    
    public SegmentEncryptionInfo(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader, dataInputStream);
        if (!this.getBoxHeader().isUserType("com.netflix.senc")) {
            throw new IllegalStateException("SegmentIndex does not have expected user type value!");
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Content size of box in bytes: " + boxHeader.getContentSizeInBytes());
        }
        this.sampleCount = dataInputStream.readInt();
        this.defaultIVSize = dataInputStream.readByte();
        this.mImageEncryptions = new SegmentEncryptionInfo$ImageEncryptionInfo[this.sampleCount];
        for (int i = 0; i < this.sampleCount; ++i) {
            this.mImageEncryptions[i] = new SegmentEncryptionInfo$ImageEncryptionInfo(dataInputStream, this.defaultIVSize, null);
        }
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.senc".equals(boxHeader.getUserType());
    }
    
    public byte getDefaultIVSize() {
        return this.defaultIVSize;
    }
    
    public SegmentEncryptionInfo$ImageEncryptionInfo[] getImageEncryptions() {
        return this.mImageEncryptions;
    }
    
    public int getSampleCount() {
        return this.sampleCount;
    }
    
    @Override
    public String toString() {
        return "SegmentEncryptionInfo{sampleCount=" + this.sampleCount + ", defaultIVSize=" + this.defaultIVSize + ", mImageEncryptions=" + Arrays.toString(this.mImageEncryptions) + "} " + super.toString();
    }
}
