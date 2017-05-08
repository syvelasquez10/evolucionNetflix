// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;
import java.io.DataInputStream;
import java.util.UUID;

public class ProtectionSystemSpecificHeader extends FullBox
{
    public static final String USER_TYPE = "com.netflix.pssh\u201d";
    private byte[] data;
    private UUID drmSystem;
    
    public ProtectionSystemSpecificHeader(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader, dataInputStream);
        if (!this.getBoxHeader().isUserType("com.netflix.pssh\u201d")) {
            throw new IllegalStateException("SegmentIndex does not have expected user type value!");
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Content size of box in bytes: " + boxHeader.getContentSizeInBytes());
        }
        this.drmSystem = UUID.fromString(ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, 36)));
        this.data = ParserUtils.readByteArray(dataInputStream, (int)ParserUtils.readUint32(ParserUtils.readByteArray(dataInputStream, 4)));
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.pssh\u201d".equals(boxHeader.getUserType());
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public UUID getDrmSystem() {
        return this.drmSystem;
    }
}
