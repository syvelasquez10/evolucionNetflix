// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;
import java.io.DataInputStream;

public class ISCTrack extends Box
{
    public static final String USER_TYPE_ITRK = "com.netflix.itrk";
    private ISCHeader iscHeader;
    private MasterIndex masterIndex;
    private VersionInfo versionInfo;
    
    public ISCTrack(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader);
        if (!this.getBoxHeader().isUserType("com.netflix.itrk")) {
            throw new IllegalStateException("ISCTrack does not have expected user type value!");
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Content size of box in bytes: " + boxHeader.getContentSizeInBytes());
        }
        this.loadBoxes(dataInputStream);
        Log.d("nf_subtitles_imv2", "All boxes loaded...");
        this.verifyBoxes();
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.itrk".equals(boxHeader.getUserType());
    }
    
    private void loadBoxes(final DataInputStream dataInputStream) {
    Label_0158_Outer:
        while (true) {
            while (true) {
            Label_0044_Outer:
                while (true) {
                    long n3 = 0L;
                Block_5_Outer:
                    while (true) {
                        Label_0172: {
                            try {
                                long contentSizeInBytes = this.getBoxHeader().contentSizeInBytes;
                                if (Log.isLoggable()) {
                                    Log.d("nf_subtitles_imv2", "Content to be parsed: " + contentSizeInBytes);
                                }
                                break Label_0172;
                                final int n2;
                                int n = n2;
                                contentSizeInBytes = n3;
                                // iftrue(Label_0044:, !Log.isLoggable())
                                // iftrue(Label_0163:, n == 0)
                                while (true) {
                                    Block_4: {
                                        Block_6: {
                                            break Block_6;
                                            break Block_4;
                                        }
                                        Log.d("nf_subtitles_imv2", "Left to parse: " + n3);
                                        n = n2;
                                        contentSizeInBytes = n3;
                                        continue Block_5_Outer;
                                        Log.d("nf_subtitles_imv2", "Content left to be parsed: " + n3);
                                        break Block_5_Outer;
                                    }
                                    n3 = contentSizeInBytes - this.readBox(dataInputStream);
                                    continue Label_0158_Outer;
                                }
                            }
                            // iftrue(Label_0177:, !Log.isLoggable())
                            catch (Throwable t) {
                                Log.w("nf_subtitles_imv2", "Either error or we do not have anything else to read!", t);
                                return;
                            }
                            final int n2 = 0;
                            continue Label_0044_Outer;
                        }
                        int n = 1;
                        continue Label_0158_Outer;
                    }
                    if (n3 > 0L) {
                        final int n2 = 1;
                        continue Label_0044_Outer;
                    }
                    break;
                }
                continue;
            }
        }
        Label_0163: {
            Log.d("nf_subtitles_imv2", "Clean exit for loadBoxes...");
        }
    }
    
    private long readBox(final DataInputStream dataInputStream) {
        final BoxHeader boxHeader = new BoxHeader(dataInputStream);
        long n2;
        if (ISCHeader.isThisBox(boxHeader)) {
            if (this.iscHeader != null) {
                Log.e("nf_subtitles_imv2", "We found ISC header and it already exist!");
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Parse ISC header: " + boxHeader);
            }
            this.iscHeader = new ISCHeader(boxHeader, dataInputStream);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "ISC header found: " + this.iscHeader);
            }
            final long n = n2 = this.iscHeader.getBoxHeader().getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Consumed content for ISC header: " + n);
                n2 = n;
            }
        }
        else if (VersionInfo.isThisBox(boxHeader)) {
            if (this.versionInfo != null) {
                Log.e("nf_subtitles_imv2", "We found version info and it already exist!");
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Parse VersionInfo: " + boxHeader);
            }
            this.versionInfo = new VersionInfo(boxHeader, dataInputStream);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "VersionInfo found: " + this.versionInfo);
            }
            final long n3 = n2 = this.versionInfo.getBoxHeader().getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Consumed content for version info: " + n3);
                return n3;
            }
        }
        else if (MasterIndex.isThisBox(boxHeader)) {
            if (this.masterIndex != null) {
                Log.e("nf_subtitles_imv2", "We found master index and it already exist!");
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Parse MasterIndex: " + boxHeader);
            }
            this.masterIndex = new MasterIndex(boxHeader, dataInputStream);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "MasterIndex found: " + this.masterIndex);
            }
            final long n4 = n2 = this.masterIndex.getBoxHeader().getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Consumed content for MasterIndex : " + n4);
                return n4;
            }
        }
        else {
            final long contentSizeInBytes = boxHeader.getContentSizeInBytes();
            final long sizeInBytes = boxHeader.getSizeInBytes();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Uknown box: " + boxHeader);
                Log.d("nf_subtitles_imv2", "Skip it to nex box for [B]: " + contentSizeInBytes);
            }
            final long skip = dataInputStream.skip(contentSizeInBytes);
            n2 = sizeInBytes;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Really skipped [B]: " + skip);
                return sizeInBytes;
            }
        }
        return n2;
    }
    
    private BoxHeader readBoxHeader(final DataInputStream dataInputStream, final int n) {
        if (dataInputStream.available() >= n) {
            return new BoxHeader(dataInputStream);
        }
        return null;
    }
    
    private void verifyBoxes() {
        if (this.iscHeader == null) {
            throw new IllegalStateException("ISCHeader is missing!");
        }
        if (this.versionInfo == null) {
            throw new IllegalStateException("VersionInfo is missing!");
        }
        if (this.masterIndex == null) {
            throw new IllegalStateException("MasterIndex is missing!");
        }
    }
    
    public ISCHeader getHeader() {
        return this.iscHeader;
    }
    
    public MasterIndex getMasterIndex() {
        return this.masterIndex;
    }
    
    public VersionInfo getVersionInfo() {
        return this.versionInfo;
    }
    
    @Override
    public String toString() {
        return "ISCTrack{iscHeader=" + this.iscHeader + ", versionInfo=" + this.versionInfo + ", masterIndex=" + this.masterIndex + "} " + super.toString();
    }
}
