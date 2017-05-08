// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.io.DataInputStream;

public class VersionInfo extends FullBox
{
    public static final String USER_TYPE_VINF = "com.netflix.vinf";
    private String builtWithLibraryVersion;
    
    public VersionInfo(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader, dataInputStream);
        if (!this.getBoxHeader().isExtendedType()) {
            throw new IllegalStateException("VersionInfo is supposed to be extended type!");
        }
        if (!this.getBoxHeader().isUserType("com.netflix.vinf")) {
            throw new IllegalStateException("VersionInfo does not have expected user type value!");
        }
        this.builtWithLibraryVersion = ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, (int)this.getBoxHeader().getContentSizeInBytes()));
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.vinf".equals(boxHeader.getUserType());
    }
    
    @Override
    public String toString() {
        return "VersionInfo{builtWithLibraryVersion='" + this.builtWithLibraryVersion + '\'' + "} " + super.toString();
    }
}
