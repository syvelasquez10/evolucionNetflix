// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;
import java.io.DataInputStream;

public class ISCSegment extends Box
{
    public static final String USER_TYPE_ISEG = "com.netflix.iseg";
    private SegmentEncryptionInfo segmentEncryptionInfo;
    private SegmentIndex segmentIndex;
    
    public ISCSegment(final BoxHeader boxHeader, final int n, final int n2, final DataInputStream dataInputStream) {
        super(boxHeader);
        if (!this.getBoxHeader().isUserType("com.netflix.iseg")) {
            throw new IllegalStateException("ISCSegment does not have expected user type value!");
        }
        this.loadBoxes(dataInputStream, n, n2);
        this.verifyBoxes();
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.iseg".equals(boxHeader.getUserType());
    }
    
    private void loadBoxes(final DataInputStream dataInputStream, final int n, final int n2) {
    Label_0039_Outer:
        while (true) {
            while (true) {
                Label_0108: {
                    try {
                        long contentSizeInBytes = this.getBoxHeader().contentSizeInBytes;
                        int i = 1;
                        while (i != 0) {
                            final long n3 = contentSizeInBytes - this.readBox(dataInputStream, n, n2);
                            if (n3 <= 0L) {
                                break Label_0108;
                            }
                            final int n4 = 1;
                            i = n4;
                            contentSizeInBytes = n3;
                            if (!Log.isLoggable()) {
                                continue Label_0039_Outer;
                            }
                            Log.d("nf_subtitles_imv2", "Left to parse: " + n3);
                            i = n4;
                            contentSizeInBytes = n3;
                        }
                        break;
                    }
                    catch (Throwable t) {
                        Log.w("nf_subtitles_imv2", "Either error or we do not have anything else to read!", t);
                        return;
                    }
                }
                final int n4 = 0;
                continue;
            }
        }
        Log.d("nf_subtitles_imv2", "Clean exit for loadBoxes...");
    }
    
    private long readBox(final DataInputStream dataInputStream, final int n, final int n2) {
        final BoxHeader boxHeader = new BoxHeader(dataInputStream);
        long n3;
        if (SegmentIndex.isThisBox(boxHeader)) {
            if (this.segmentIndex != null) {
                Log.e("nf_subtitles_imv2", "We found segment index and it already exist!");
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Parse segment index: " + boxHeader);
            }
            this.segmentIndex = new SegmentIndex(boxHeader, n, n2, dataInputStream, this);
            n3 = this.segmentIndex.getBoxHeader().getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "SegmentIndex: " + this.segmentIndex);
                n3 = n3;
            }
        }
        else {
            if (!SegmentEncryptionInfo.isThisBox(boxHeader)) {
                final long contentSizeInBytes = boxHeader.getContentSizeInBytes();
                final long sizeInBytes = boxHeader.getSizeInBytes();
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Uknown box: " + boxHeader);
                    Log.d("nf_subtitles_imv2", "Skip it to nex box for [B]: " + contentSizeInBytes);
                }
                final long skip = dataInputStream.skip(contentSizeInBytes);
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Really skipped [B]: " + skip);
                }
                return sizeInBytes;
            }
            if (this.segmentEncryptionInfo != null) {
                Log.e("nf_subtitles_imv2", "We found segment encryption info and it already exist!");
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Parse segmentEncryptionInfo: " + boxHeader);
            }
            this.segmentEncryptionInfo = new SegmentEncryptionInfo(boxHeader, dataInputStream);
            final long n4 = n3 = this.segmentEncryptionInfo.getBoxHeader().getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "SegmentEncryptionInfo: " + this.segmentEncryptionInfo);
                return n4;
            }
        }
        return n3;
    }
    
    private void verifyBoxes() {
        if (this.segmentIndex == null) {
            throw new IllegalStateException("Segment index is missing!");
        }
        if (this.segmentEncryptionInfo == null) {
            Log.d("nf_subtitles_imv2", "Encrypted info not found!");
        }
    }
    
    public SegmentEncryptionInfo$ImageEncryptionInfo getImageEncryptionInfoForImage(final int n) {
        if (this.segmentEncryptionInfo == null) {
            Log.d("nf_subtitles_imv2", "Segment encryption info is null, no encryption.");
        }
        else {
            if (this.segmentEncryptionInfo.getImageEncryptions() == null || this.segmentEncryptionInfo.getImageEncryptions().length < 1) {
                Log.d("nf_subtitles_imv2", "Segment encryption info not found, no encryption.");
                return null;
            }
            if (this.segmentEncryptionInfo.getImageEncryptions().length > n) {
                return this.segmentEncryptionInfo.getImageEncryptions()[n];
            }
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_imv2", "Segment encryption info not found for index " + n + " only " + this.segmentEncryptionInfo.getImageEncryptions().length + " entries found! We should NOT be here");
                return null;
            }
        }
        return null;
    }
    
    public SegmentEncryptionInfo getSegmentEncryptionInfo() {
        return this.segmentEncryptionInfo;
    }
    
    public SegmentIndex getSegmentIndex() {
        return this.segmentIndex;
    }
    
    @Override
    public String toString() {
        return "ISCSegment{segmentIndex=" + this.segmentIndex + ", segmentEncryptionInfo=" + this.segmentEncryptionInfo + "} " + super.toString();
    }
}
